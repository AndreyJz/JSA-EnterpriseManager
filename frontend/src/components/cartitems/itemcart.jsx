// Item.js
import React from 'react';
import '/src/components/cart/Cart.jsx';
import '../cart/Cart.css';


const Item = ({ item, updateQuantity, removeItem }) => {
  return (
    <div className="cart-item">
      <div className="item-info">
        <h4>{item.name}</h4>
        <p>{`TALLA ${item.size}`}</p>
        <p>{`$${item.price}`}</p>
      </div>
      <div className="item-controls">
        <button onClick={() => updateQuantity(item.id, item.quantity - 1)}>-</button>
        <span>{item.quantity}</span>
        <button onClick={() => updateQuantity(item.id, item.quantity + 1)}>+</button>
        <p>{`$${item.price * item.quantity}`}</p>
      </div>
      <button className="remove-item" onClick={() => removeItem(item.id)}>
        Eliminar
      </button>
    </div>
  );
};

export default Item;
