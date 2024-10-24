import React, { useState, useEffect } from 'react';
import { useCart } from '../context/CartContext';
import { Link, useNavigate } from 'react-router-dom';
import { loadStripe } from '@stripe/stripe-js';
import { Elements } from '@stripe/react-stripe-js';
import CheckoutForm from './CheckoutForm';

const stripePromise = loadStripe('your_stripe_publishable_key');

function Cart() {
  const { cart, removeFromCart, clearCart, updateQuantity } = useCart();
  const [isLoggedIn, setIsLoggedIn] = useState(false); // Verifica si está logueado
  const [showPopup, setShowPopup] = useState(false);  // Muestra el pop-up si no está logueado
  const [showConfirmation, setShowConfirmation] = useState(false); // Muestra la confirmación
  const [checkout, setCheckout] = useState(false); // Controla si se debe proceder con el checkout
  const navigate = useNavigate();  // Hook para redirigir al login

  // Verifica si el usuario está logueado
  useEffect(() => {
    const checkIfLoggedIn = async () => {
      try {
        const response = await fetch('http://localhost:8081/auth/validate-token');
        if (response.ok) {
          const data = await response.json();
          setIsLoggedIn(data.isAuthenticated); // Suponiendo que la respuesta del backend tiene este campo
        } else {
          setIsLoggedIn(false);
        }
      } catch (error) {
        console.error('Error fetching login status:', error);
        setIsLoggedIn(false);
      }
    };

    checkIfLoggedIn();
  }, []);

  const total = cart.reduce((sum, item) => sum + item.serviceValue * item.quantity, 0);

  // Función que verifica antes de proceder con el pago
  const handleCheckout = () => {
    if (isLoggedIn) {
      setShowConfirmation(true); // Mostrar la confirmación si está logueado
    } else {
      setShowPopup(true); // Mostrar el pop-up si no está logueado
    }
  };

  // Confirmar si desea proceder con la compra
  const handleConfirmPurchase = () => {
    setShowConfirmation(false);
    setCheckout(true); // Desplegar el formulario de pago si acepta
  };

  if (cart.length === 0) {
    return (
      <div className="container mx-auto px-4 py-8">
        <h2 className="text-2xl font-bold mb-4">Your Cart</h2>
        <p>Your cart is empty. <Link to="/Services" className="text-blue-600 hover:underline">Continue shopping</Link></p>
      </div>
    );
  }

  return (
    <div className="container mx-auto px-4 py-8 w-3/4 ">
      <h2 className="text-2xl font-bold mb-4">Your Cart</h2>
      <div className="space-y-4 ">
        {cart.map((item) => (
          <div key={item.service.id} className="flex justify-between items-center border-b pb-4">
            <div>
              <h3 className="text-lg font-semibold">{item.service.name}</h3>
              <p className="text-gray-600">${item.serviceValue} x {item.quantity}</p>
            </div>
            <div className="flex items-center">
              <button
                onClick={() => updateQuantity(item.service.id, item.quantity - 1)}
                className="bg-gray-200 px-2 py-1 rounded-l"
              >
                -
              </button>
              <span className="bg-gray-100 px-4 py-1">{item.quantity}</span>
              <button
                onClick={() => updateQuantity(item.service.id, item.quantity + 1)}
                className="bg-gray-200 px-2 py-1 rounded-r"
              >
                +
              </button>
              <button
                onClick={() => removeFromCart(item.service.id)}
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
          <button
            onClick={handleCheckout}  // Usar la función de verificación
            className="bg-green-600 text-white px-4 py-2 rounded hover:bg-green-700 transition duration-300"
          >
            Proceed to Checkout
          </button>
        </div>
        <button
          onClick={clearCart}
          className="mt-4 bg-red-600 text-white px-4 py-2 rounded hover:bg-red-700 transition duration-300"
        >
          Clear Cart
        </button>
      </div>

      {/* Popup de advertencia si no está logueado */}
      {showPopup && (
        <div className="fixed inset-0 bg-gray-800 bg-opacity-50 flex justify-center items-center">
          <div className="bg-white p-8 rounded shadow-md text-center">
            <p className="text-lg font-bold mb-4">You must be logged in to proceed with the payment</p>
            <button
              onClick={() => navigate('/login')}  // Redirigir al componente de login
              className="bg-blue-600 text-white px-4 py-2 rounded hover:bg-blue-700 transition duration-300"
            >
              Go to Login
            </button>
          </div>
        </div>
      )}

      {/* Confirmación de compra si está logueado */}
      {showConfirmation && (
        <div className="fixed inset-0 bg-gray-800 bg-opacity-50 flex justify-center items-center">
          <div className="bg-white p-8 rounded shadow-md text-center">
            <p className="text-lg font-bold mb-4">Are you sure you want to complete your purchase?</p>
            <div className="space-x-4">
              <button
                onClick={handleConfirmPurchase}  // Confirmar la compra
                className="bg-green-600 text-white px-4 py-2 rounded hover:bg-green-700 transition duration-300"
              >
                Yes, proceed
              </button>
              <button
                onClick={() => setShowConfirmation(false)}  // Cancelar
                className="bg-red-600 text-white px-4 py-2 rounded hover:bg-red-700 transition duration-300"
              >
                Cancel
              </button>
            </div>
          </div>
        </div>
      )}

      {/* Pasarela de pago */}
      {checkout && (
        <Elements stripe={stripePromise}>
          <CheckoutForm amount={total} />
        </Elements>
      )}
    </div>
  );
}

export default Cart;
