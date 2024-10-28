import React, { useState } from 'react';
import { CardElement, useStripe, useElements } from '@stripe/react-stripe-js';
import { useCart } from '../context/CartContext';

interface CheckoutFormProps {
  amount: number;
}

function CheckoutForm({ amount }: CheckoutFormProps) {
  const stripe = useStripe();
  const elements = useElements();
  const [error, setError] = useState<string | null>(null);
  const [processing, setProcessing] = useState(false);
  const [succeeded, setSucceeded] = useState(false);
  const { clearCart } = useCart();

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
            body: JSON.stringify({ amount: amount * 100 }), // convert amount to cents
          });

          if (!response.ok) {
            const errorData = await response.text();
            console.error(`Failed to create payment intent: ${response.status} - ${errorData}`);
            throw new Error(`Failed to create payment intent: ${response.statusText}`);
          }

          const data = await response.json();
          const clientSecret = data.clientSecret;

          console.log('Payment Intent created:', clientSecret);
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
