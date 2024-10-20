// Cart.js
import React, { useState } from 'react';
import Item from '/src/components/cartitems/itemcart.jsx';
import './Cart.css';


const Cart = () => {
  const [cartItems, setCartItems] = useState([
    { id: 1, name: 'CAMISETA MYSTICAL ORCHID NEGRO', size: 'L', price: 190000, quantity: 1 },
    { id: 2, name: 'camiseta crop HARMONY BLOOM BLANCO', size: 'L', price: 170000, quantity: 1 },
    { id: 3, name: 'Bandeja weedgreen blanco', size: 'GRANDE', price: 20000, quantity: 2 },
    { id: 4, name: 'CAMISETA WHITE ELEGANCE ORCHID BLANCO', size: 'S', price: 190000, quantity: 1 }
  ]);

  const updateQuantity = (id, newQuantity) => {
    setCartItems(cartItems.map(item => 
      item.id === id ? { ...item, quantity: Math.max(newQuantity, 1) } : item
    ));
  };

  const removeItem = (id) => {
    setCartItems(cartItems.filter(item => item.id !== id));
  };

  const total = cartItems.reduce((acc, item) => acc + item.price * item.quantity, 0);

  return (
    <div className="cart">
      <h2>Carrito</h2>
      {cartItems.map(item => (
        <Item key={item.id} item={item} updateQuantity={updateQuantity} removeItem={removeItem} />
      ))}
      <div className="cart-total">
        <h3>Total: ${total.toLocaleString('es-CO')} COP</h3>
        <button className="checkout-button">Continuar</button>
      </div>
    </div>
  );
};

export default Cart;
