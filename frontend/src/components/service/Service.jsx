import React from 'react';
import './Service.css';

function Service({ serviceData }) {
    const { title, price, description } = serviceData;

    return (
        <div className="service-card">
        <h3 className="service-title">{title}</h3>
        <div className="service-description">
            <p>{description}</p>
        </div>
        <div className="service-info">
            <h4 className="service-price">${price}</h4>
            <button className="add-to-cart-btn">Add to Cart</button>
        </div>
        </div>
    );
}

export default Service;
