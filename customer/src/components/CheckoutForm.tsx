import React, { useState } from 'react';
import { CardElement, useStripe, useElements } from '@stripe/react-stripe-js';
import { useCart } from '../context/CartContext';
import { X, Printer, Check } from 'lucide-react'

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
  const [showInvoice, setShowInvoice] = useState(false);
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
      
        let contador: number = item.quantity
        while (contador>=1) {
          const response = await fetch('http://localhost:8081/api/Order_Details', {
            method: 'POST',
            headers: {
              'Content-Type': 'application/json',
              'Authorization': `Bearer ${token}`
            },
        
            body: JSON.stringify({
              
                serviceBranch: {
                  id: {
                    branchId: item.id.branchId,
                    serviceId: item.id.serviceId
                  },
                  service: {
                    id: item.id.serviceId
                  },
                  branch: {
                    id: item.id.branchId
                }},
                serviceOrder: {
                  id: serviceOrderId},
                serviceValue: item.serviceValue
            })
          });
          if (!response.ok) throw new Error(`Failed to insert Order Detail: ${response.statusText}`);
          contador--
        }
      };
        }
      


  //orden de trabajo
  const insertWorkOrder = async (token: string | null, serviceOrderId: number) => {
    const response = await fetch('http://localhost:8081/api/Work_Orders', {
      method: 'POST',
      headers: {
        
        'Content-Type': 'application/json',
        'Authorization': `Bearer ${token}`
      },
      body: JSON.stringify({ 
        
          workOrderNum: userId+new Date().toISOString() ,
          assignDate: new Date().toISOString(),
          serviceOrder: {
            id: serviceOrderId
          } })
    });
    if (!response.ok) throw new Error(`Failed to create Work Order: ${response.statusText}`);
    return await response.json();
  };

  //detalles orden de trabajo
  const insertWorkOrderDetails = async (token: string | null, workOrderId: number, cart: any[]) => {
    for (const item of cart) {
      let contador: number = item.quantity
      while (contador>=1) {
      const response = await fetch('http://localhost:8081/api/Work_Order_Detail', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
          'Authorization': `Bearer ${token}`
        },
        body: JSON.stringify({

    workOrder: {
      id: workOrderId
    },
    person: {
      id: userId
    },
    date: new Date().toISOString(),
    workOrderDetailStatus: {
      id: 1
    },
    serviceBranch: {
      id: {
        branchId: item.id.branchId,
        serviceId: item.id.serviceId
      },
      service: {
        id: item.id.serviceId
      },
      branch: {
        id: item.id.branchId
      }
    }
  
        })
      });
      if (!response.ok) throw new Error(`Failed to insert Work Order Detail: ${response.statusText}`);
      contador--
    }
    }
  };

  //aprobaciones de servicio
  const insertServiceApprovals = async (token: string | null, workOrderId: number, cart: any[]) => {
    for (const item of cart) {
      console.log(item)
      let contador: number = item.quantity
      while (contador>=1) {
      const response = await fetch('http://localhost:8081/api/Service_Approval', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
          'Authorization': `Bearer ${token}`
        },
        body: JSON.stringify({
          workOrder: {
            id: workOrderId
          },
          serviceBranch: {
            id: {
              branchId: item.id.branchId,
              serviceId: item.id.serviceId
            },
            service: {
              id: item.id.serviceId
            },
            branch: {
              id: item.id.branchId}
          },
          report: "",
          solution: "",
          approvalStatus: {
            id: 1
          }

        })
      });
      if (!response.ok) throw new Error(`Failed to insert Service Approval: ${response.statusText}`);
      contador--
    }
    }
  };
  const closeReceipt = () => {
    setShowInvoice(false)
    setShowInvoice(false); // Cierra el recibo
    setError(null);        // Limpia cualquier error
    clearCart();           // Vacía el carrito
  };

  const handlePrint = () => {
    window.print()
  }

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
          // IMPLEMETALO AQUI
          setShowInvoice(true); 

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
    
  }

  return (
    <div>
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
        {/* Modal de la Factura */}
      {showInvoice && (
          <div className={`fixed inset-0 z-50 flex items-center justify-center bg-black bg-opacity-50 transition-opacity duration-300 ${showInvoice ? 'opacity-100' : 'opacity-0'}`}>
          <div className="w-full max-w-lg mx-4 bg-white shadow-xl rounded-lg overflow-hidden">
            <div className="p-6">
              <div className="flex items-center justify-between pb-4 border-b">
                <h2 className="text-2xl font-bold text-gray-800">✅ Thank you for your purchase!</h2>
                <button
                  onClick={closeReceipt}
                  className="text-gray-500 hover:text-gray-700 transition-colors duration-200"
                  aria-label="Close receipt"
                >
                  <X className="h-6 w-6" />
                </button>
              </div>
              <div className="py-4">
                <p className="text-sm text-gray-600 mb-4">Here's your receipt:</p>
                <ul className="space-y-2">
                  {cart.map((item, index) => (
                    <li key={index} className="flex justify-between items-center py-2 border-b last:border-b-0">
                      <span className="font-medium text-gray-800">{item.service.name}</span>
                      <span className="text-sm text-gray-600">{item.branch.name}</span>
                      <span className="font-semibold text-gray-800">{item.quantity} x ${item.serviceValue.toFixed(2)}</span>
                    </li>
                  ))}
                </ul>
              </div>
              <div className="pt-4 border-t flex justify-between items-center">
                <div className="text-lg font-bold text-gray-800">Total: ${amount.toFixed(2)}</div>
                <div className="flex space-x-2">
                  <button
                    onClick={handlePrint}
                    className="flex items-center px-4 py-2 bg-gray-200 text-gray-800 rounded hover:bg-gray-300 transition-colors duration-200"
                  >
                    <Printer className="mr-2 h-4 w-4" />
                    Print
                  </button>
                  <button
                    onClick={closeReceipt}
                    className="px-4 py-2 bg-blue-600 text-white rounded hover:bg-blue-700 transition-colors duration-200"
                  >
                    Close
                  </button>
                </div>
              </div>
            </div>
          </div>
        </div>
      )}
  </div>
  );
}

export default CheckoutForm;
