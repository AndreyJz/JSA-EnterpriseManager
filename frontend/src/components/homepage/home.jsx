    import React from 'react';
    import Service from '../service/Service';
    import './Home.css';

    const servicesData = [
    {
        title: "Enterprise Server Solution",
        price: "4999",
        description: "High-performance server solution for large enterprises.",
    },
    {
        title: "Business Analytics Software",
        price: "1999",
        description: "Tools to analyze and manage your business data.",
    },
    {
        title: "Corporate Security Package",
        price: "2999",
        description: "Comprehensive security solutions for businesses.",
    },
    {
        title: "Corporate Security Package",
        price: "2999",
        description: "Comprehensive security solutions for businesses.",
    }
    ];

    function Home() {
    return (
        <div className="home">
        <section className="hero">
            <div className="hero-text">
            <h1>Empower Your Business with Our Enterprise Solutions</h1>
            <p>
                Streamline operations, boost productivity, and drive growth with our cutting-edge JSA products and services.
            </p>
            <div className="hero-buttons">
                <button className="explore-btn">Contact Us</button>
                <button className="demo-btn">Schedule a Demo</button>
            </div>
            </div>
            <div className="hero-image">
            <img src="src\assets\testhome.png" alt="Laptop showcasing business analytics" />
            </div>
        </section>

        <section className="featured-products">
            <h2>Featured Services</h2>
            <div className="services-list">
            {servicesData.map((service, index) => (
                <Service key={index} serviceData={service} />
            ))}
            </div>
        </section>

        <section className="browse-category">
            <h2>companies</h2>
            <div className="categories">
            <div className="category">Hardware</div>
            <div className="category">Software</div>
            <div className="category">Security</div>
            <div className="category">Consulting</div>
            </div>
        </section>
        </div>
    );
    }

    export default Home;
