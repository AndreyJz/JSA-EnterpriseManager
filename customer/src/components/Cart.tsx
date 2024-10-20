import React from 'react';
import { useCart } from '../context/CartContext';
import { Link } from 'react-router-dom';
import { loadStripe } from '@stripe/stripe-js';
import { Elements } from '@stripe/react-stripe-js';
import CheckoutForm from './CheckoutForm';

const stripePromise = loadStripe('your_stripe_publishable_key');

function Cart() {
  const { cart, removeFromCart, clearCart, updateQuantity } = useCart();

  const total = cart.reduce((sum, item) => sum + item.price * item.quantity, 0);

  if (cart.length === 0) {
    return (
      <div className="container mx-auto px-4 py-8">
        <h2 className="text-2xl font-bold mb-4">Your Cart</h2>
        <p>Your cart is empty. <Link to="/" className="text-blue-600 hover:underline">Continue shopping</Link></p>
      </div>
    );
  }

  return (
    <div className="container mx-auto px-4 py-8">
      <h2 className="text-2xl font-bold mb-4">Your Cart</h2>
      <div className="space-y-4">
        {cart.map((item) => (
          <div key={item.id} className="flex justify-between items-center border-b pb-4">
            <div>
              <h3 className="text-lg font-semibold">{item.title}</h3>
              <p className="text-gray-600">${item.price} x {item.quantity}</p>
            </div>
            <div className="flex items-center">
              <button
                onClick={() => updateQuantity(item.id, item.quantity - 1)}
                className="bg-gray-200 px-2 py-1 rounded-l"
              >
                -
              </button>
              <span className="bg-gray-100 px-4 py-1">{item.quantity}</span>
              <button
                onClick={() => updateQuantity(item.id, item.quantity + 1)}
                className="bg-gray-200 px-2 py-1 rounded-r"
              >
                +
              </button>
              <button
                onClick={() => removeFromCart(item.id)}
                className="ml-4 text-red-600 hover:text-red-800"
              >
                Remove
              </button>
            </div>
          </div>
        ))}
      </div>
      <div className="mt-8">
        <p className="text-xl font-bold">Total: ${total.toFixed(2)}</p>
        <div className="mt-4">
          <Elements stripe={stripePromise}>
            <CheckoutForm amount={total} />
          </Elements>
        </div>
        <button
          onClick={clearCart}
          className="mt-4 bg-red-600 text-white px-4 py-2 rounded hover:bg-red-700 transition duration-300"
        >
          Clear Cart
        </button>
      </div>
    </div>
  );
}

export default Cart;