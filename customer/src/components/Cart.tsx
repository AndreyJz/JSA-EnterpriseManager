import React, { useState, useEffect } from 'react';
import { useCart } from '../context/CartContext';
import { Link, useNavigate } from 'react-router-dom';
import { loadStripe } from '@stripe/stripe-js';
import { Elements } from '@stripe/react-stripe-js';
import CheckoutForm from './CheckoutForm';

const stripePromise = loadStripe('pk_test_51QEHZiLNN21xADSgOxFbVEwsKq38GtbmclI5Nw2sTaxxvVMZsR75VewPA0mm7pNs9LooIw1AKXDBCoPDwcAOBdvY00rZh8A5K7');

function Cart() {
  const { cart, removeFromCart, clearCart, updateQuantity } = useCart();
  const [isLoggedIn, setIsLoggedIn] = useState(false);
  const [showPopup, setShowPopup] = useState(false);
  const [showConfirmation, setShowConfirmation] = useState(false);
  const [showCheckoutPopup, setShowCheckoutPopup] = useState(false);
  const navigate = useNavigate();

  useEffect(() => {

    const fetchUserProfile = async () => {
      const token = localStorage.getItem('token'); // Obtener el token del localStorage
      
      try {
        const response = await fetch('http://localhost:8081/auth/profile', {
          method: 'GET',
          headers: {
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${token}` // Enviar el token en el encabezado de autorización
          }
        });
        if (response.ok) {
          const userProfile = await response.json(); // Procesar la respuesta
          console.log('User Profile:', userProfile.id); // Aquí puedes manejar la información del perfil
          // Puedes almacenar esta información en el estado o hacer algo con ella
        } else {
          console.error('Error fetching profile:', response.statusText);
        }
      } catch (error) {
        console.error('Error during fetch:', error);
      }
    };

    const checkIfLoggedIn = async () => {
      const token = localStorage.getItem('token');
      if (!token) {
        setIsLoggedIn(false);
        return;
      }

      try {

        const response = await fetch(`http://localhost:8081/auth/validate-token?jwt=${token}`);    
        console.log(response);
        if (response.ok) {
          const isAuthenticated = await response.json(); // Esperamos un booleano
          setIsLoggedIn(isAuthenticated);
        } else {
          setIsLoggedIn(false);
        }
      } catch (error) {
        console.error('Error fetching login status:', error);
        setIsLoggedIn(false);
      }
    };

    checkIfLoggedIn(), fetchUserProfile();
  }, []);



  const total = cart.reduce((sum, item) => sum + item.serviceValue * item.quantity, 0);

  const handleCheckout = () => {
    if (isLoggedIn) {
      setShowConfirmation(true);
    } else {
      setShowPopup(true);
    }
  };

  const handleConfirmPurchase = () => {
    setShowConfirmation(false);
    setShowCheckoutPopup(true);
  };

  const handleOutsideClick = (event: React.MouseEvent<HTMLDivElement, MouseEvent>, closeFunction: { (value: React.SetStateAction<boolean>): void; (value: React.SetStateAction<boolean>): void; (value: React.SetStateAction<boolean>): void; (arg0: boolean): void; }) => {
    if (event.target === event.currentTarget) {
      closeFunction(false);
    }
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
    <div className="container mx-auto px-4 py-8 w-3/4">
      <h2 className="text-2xl font-bold mb-4">Your Cart</h2>
      <div className="space-y-4">
        {cart.map((item) => (
          <div key={item.service.id} className="flex justify-between items-center border-b pb-4">
            <div>
              <h3 className="text-lg font-semibold">{item.service.name}</h3>
              <p className="text-gray-600">${item.serviceValue} x {item.quantity}</p>
            </div>
            <div className="flex items-center">
              <button onClick={() => updateQuantity(item.service.id, item.quantity - 1)} className="bg-gray-200 px-2 py-1 rounded-l">-</button>
              <span className="bg-gray-100 px-4 py-1">{item.quantity}</span>
              <button onClick={() => updateQuantity(item.service.id, item.quantity + 1)} className="bg-gray-200 px-2 py-1 rounded-r">+</button>
              <button onClick={() => removeFromCart(item.service.id)} className="ml-4 text-red-600 hover:text-red-800">Remove</button>
            </div>
          </div>
        ))}
      </div>
      <div className="mt-8">
        <p className="text-xl font-bold">Total: ${total.toFixed(2)}</p>
        <div className="mt-4">
          <button onClick={handleCheckout} className="bg-green-600 text-white px-4 py-2 rounded hover:bg-green-700 transition duration-300">
            Proceed to Checkout
          </button>
        </div>
        <button onClick={clearCart} className="mt-4 bg-red-600 text-white px-4 py-2 rounded hover:bg-red-700 transition duration-300">
          Clear Cart
        </button>
      </div>

      {showPopup && (
        <div onClick={(e) => handleOutsideClick(e, setShowPopup)} className="fixed inset-0 bg-gray-800 bg-opacity-50 flex justify-center items-center">
          <div className="bg-white p-8 rounded shadow-md text-center">
            <p className="text-lg font-bold mb-4">You must be logged in to proceed with the payment</p>
            <button onClick={() => navigate('/login')} className="bg-blue-600 text-white px-4 py-2 rounded hover:bg-blue-700 transition duration-300">
              Go to Login
            </button>
          </div>
        </div>
      )}

      {showConfirmation && (
        <div onClick={(e) => handleOutsideClick(e, setShowConfirmation)} className="fixed inset-0 bg-gray-800 bg-opacity-50 flex justify-center items-center">
          <div className="bg-white p-8 rounded shadow-md text-center">
            <p className="text-lg font-bold mb-4">Are you sure you want to complete your purchase?</p>
            <div className="space-x-4">
              <button onClick={handleConfirmPurchase} className="bg-green-600 text-white px-4 py-2 rounded hover:bg-green-700 transition duration-300">
                Yes, proceed
              </button>
              <button onClick={() => setShowConfirmation(false)} className="bg-red-600 text-white px-4 py-2 rounded hover:bg-red-700 transition duration-300">
                Cancel
              </button>
            </div>
          </div>
        </div>
      )}

      {showCheckoutPopup && (
        <div onClick={(e) => handleOutsideClick(e, setShowCheckoutPopup)} className="fixed inset-0 bg-gray-800 bg-opacity-50 flex justify-center items-center">
          <div className="bg-white p-8 rounded shadow-md max-w-md w-full">
            <h2 className="text-xl font-bold mb-4 text-center">Complete your Payment</h2>
            <Elements stripe={stripePromise}>
              <CheckoutForm amount={total} />
            </Elements>
            <button onClick={() => setShowCheckoutPopup(false)} className="mt-4 bg-red-600 text-white px-4 py-2 rounded hover:bg-red-700 transition duration-300 w-full">
              Cancel
            </button>
          </div>
        </div>
      )}
    </div>
  );
}

export default Cart;
