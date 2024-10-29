import React, { useState } from 'react';
import { CardElement, useStripe, useElements } from '@stripe/react-stripe-js';
import { useCart } from '../context/CartContext';

interface CheckoutFormProps {
  amount: number;
  userId: string;
  cart: any[];
}

function CheckoutForm({ amount, userId, cart }: CheckoutFormProps) {
  console.log(cart);
  const stripe = useStripe();
  const elements = useElements();
  const [error, setError] = useState<string | null>(null);
  const [processing, setProcessing] = useState(false);
  const [succeeded, setSucceeded] = useState(false);
  const { clearCart } = useCart();

  // Función para insertar una orden de servicio
  const insertServiceOrder = async (token: string | null, userId: string) => {
    const response = await fetch('http://localhost:8081/api/Service_Order', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
        'Authorization': `Bearer ${token}`
      },
      body: JSON.stringify({ 
        
        orderDate: new Date().toISOString(),
        customer: {
          id: userId
        },
        employee: {
          id: "P001"
        },
        orderStatus: {
          id: 1
        }
      })
    });
    if (!response.ok) throw new Error(`Failed to create Service Order: ${response.statusText}`);
    return await response.json();
  };

  //detalles de la orden de servicio
  const insertOrderDetails = async (token: string | null, serviceOrderId: number, cart: any[]) => {
    for (const item of cart) {
      console.log(item)
      const response = await fetch('http://localhost:8081/api/Order_Details', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
          'Authorization': `Bearer ${token}`
        },
        body: JSON.stringify({
          
            serviceBranch: {
              id: {
                branchId: item.id.branchid,
                serviceId: item.id.serviceid
              },
              service: {
                id: item.id.serviceid
              },
              branch: {
                id: item.id.branchid
            }},
            serviceOrder: {
              id: serviceOrderId},
            serviceValue: item.serviceValue
        })
      });
      if (!response.ok) throw new Error(`Failed to insert Order Detail: ${response.statusText}`);
    }
  };

  //orden de trabajo
  const insertWorkOrder = async (token: string | null, serviceOrderId: number) => {
    const response = await fetch('http://localhost:8081/api/Work_Order', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
        'Authorization': `Bearer ${token}`
      },
      body: JSON.stringify({ serviceOrder: { id: serviceOrderId } })
    });
    if (!response.ok) throw new Error(`Failed to create Work Order: ${response.statusText}`);
    return await response.json();
  };

  //detalles orden de trabajo
  const insertWorkOrderDetails = async (token: string | null, workOrderId: number, cart: any[]) => {
    for (const item of cart) {
      const response = await fetch('http://localhost:8081/api/Work_Order_Details', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
          'Authorization': `Bearer ${token}`
        },
        body: JSON.stringify({
          workOrder: { id: workOrderId },
          service: { id: item.serviceId },
          quantity: item.quantity
        })
      });
      if (!response.ok) throw new Error(`Failed to insert Work Order Detail: ${response.statusText}`);
    }
  };

  //aprobaciones de servicio
  const insertServiceApprovals = async (token: string | null, workOrderId: number, cart: any[]) => {
    for (const item of cart) {
      const response = await fetch('http://localhost:8081/api/Service_Approval', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
          'Authorization': `Bearer ${token}`
        },
        body: JSON.stringify({
          workOrder: { id: workOrderId },
          service: { id: item.serviceId }
        })
      });
      if (!response.ok) throw new Error(`Failed to insert Service Approval: ${response.statusText}`);
    }
  };

  // Manejador de envío del formulario
  const handleSubmit = async (event: React.FormEvent) => {
    event.preventDefault();

    if (!stripe || !elements) {
      return;
    }

    setProcessing(true);
    const cardElement = elements.getElement(CardElement);

    if (cardElement) {
      try {
        const { error, paymentMethod } = await stripe.createPaymentMethod({
          type: 'card',
          card: cardElement,
        });

        if (error) {
          setError(error.message || 'An error occurred');
          setProcessing(false);
        } else {
          const token = localStorage.getItem('token');

          const response = await fetch('http://localhost:8081/api/Payment/create-payment-intent', {
            method: 'POST',
            headers: {
              'Content-Type': 'application/json',
              'Authorization': `Bearer ${token}`
            },
            body: JSON.stringify({ amount: amount * 100 }) // convertir el monto a centavos
          });

          if (!response.ok) {
            const errorData = await response.text();
            console.error(`Failed to create payment intent: ${response.status} - ${errorData}`);
            throw new Error(`Failed to create payment intent: ${response.statusText}`);
          }

          const data = await response.json();
          const clientSecret = data.clientSecret;

          console.log('Payment Intent created:', clientSecret);

          // Inserción en la base de datos
          const serviceOrderData = await insertServiceOrder(token, userId);
          await insertOrderDetails(token, serviceOrderData.id, cart);

          const workOrderData = await insertWorkOrder(token, serviceOrderData.id);
          await insertWorkOrderDetails(token, workOrderData.id, cart);

          await insertServiceApprovals(token, workOrderData.id, cart);

          // Marcar como exitoso y limpiar el carrito
          setSucceeded(true);
          setError(null);
          clearCart();
        }
      } catch (err) {
        console.error('Payment error:', err);
        setError('An unexpected error occurred. Please try again.');
      } finally {
        setProcessing(false);
      }
    }
  };

  if (succeeded) {
    return (
        <div className="text-center">
          <h2 className="text-2xl font-bold text-green-600 mb-4">Payment Successful!</h2>
          <p>Thank you for your purchase.</p>
        </div>
    );
  }

  return (
      <form onSubmit={handleSubmit} className="mt-4">
        <CardElement
            options={{
              style: {
                base: {
                  fontSize: '16px',
                  color: '#424770',
                  '::placeholder': { color: '#aab7c4' },
                },
                invalid: { color: '#9e2146' },
              },
            }}
        />
        {error && <div className="text-red-600 mt-2">{error}</div>}
        <button
            type="submit"
            disabled={!stripe || processing}
            className="mt-4 bg-blue-600 text-white px-4 py-2 rounded hover:bg-blue-700 transition duration-300 disabled:opacity-50"
        >
          {processing ? 'Processing...' : `Pay $${amount.toFixed(2)}`}
        </button>
      </form>
  );
}

export default CheckoutForm;
