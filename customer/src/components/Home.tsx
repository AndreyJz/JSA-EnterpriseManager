import React from 'react';
import { Link } from 'react-router-dom';
import Service from './Service';
import Carousel from './Carousel';
import { useCart } from '../context/CartContext';

const servicesData = [
  {
    id: 1,
    title: "Enterprise Server Solution",
    price: 4999,
    description: "High-performance server solution for large enterprises.",
  },
  {
    id: 2,
    title: "Business Analytics Software",
    price: 1999,
    description: "Tools to analyze and manage your business data.",
  },
  {
    id: 3,
    title: "Corporate Security Package",
    price: 2999,
    description: "Comprehensive security solutions for businesses.",
  },
  {
    id: 4,
    title: "Cloud Integration Services",
    price: 3499,
    description: "Seamless cloud integration for your enterprise.",
  }
];

const companiesData = [
  { name: "TechCorp", logo: "https://source.unsplash.com/random/200x200?tech" },
  { name: "DataSystems", logo: "https://source.unsplash.com/random/200x200?data" },
  { name: "SecureNet", logo: "https://source.unsplash.com/random/200x200?security" },
  { name: "CloudWave", logo: "https://source.unsplash.com/random/200x200?cloud" },
  { name: "InnovateTech", logo: "https://source.unsplash.com/random/200x200?innovation" },
  { name: "GlobalIT", logo: "https://source.unsplash.com/random/200x200?global" },
];


function Home() {
  const { addToCart } = useCart();

  return (
    <div className="bg-gray-100">
      <section className="hero text-black py-20">
        <div className="container mx-auto px-4 flex flex-col md:flex-row items-center">
          <div className="md:w-1/2 mb-8 md:mb-0">
            <h1 className="text-4xl font-bold mb-4">Empower Your Business with Our Enterprise Solutions</h1>
            <p className="text-xl mb-6">
              Streamline operations, boost productivity, and drive growth with our cutting-edge JSA products and services.
            </p>
            <div className="space-x-4">
              <Link to="/contact" className="bg-black text-white px-6 py-3 rounded-full font-semibold hover:bg-blue-100 hover:text-blue-600 transition duration-300">Contact Us</Link>
              <Link to="/about" className="border-2 border-black px-6 py-3 rounded-full font-semibold hover:bg-white hover:text-blue-600 transition duration-300">About Us</Link>
            </div>
          </div>
          <div className="md:w-1/2">
            <img src="../../src/images/testhome.png" alt="Enterprise Solutions" className="rounded-lg" />
          </div>
        </div>
      </section>

      <section className="featured-products py-16">
        <div className="container mx-auto px-4">
          <h2 className="text-3xl font-bold text-center mb-12">Featured Services</h2>
          <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-8">
            {servicesData.map((service) => (
              <Service key={service.id} serviceData={service} onAddToCart={() => addToCart(service)} />
            ))}
          </div>
        </div>
      </section>

      <section className="allied-companies py-16 bg-gray-200">
        <div className="container mx-auto px-4">
          <h2 className="text-3xl font-bold text-center mb-12">Allied Companies</h2>
          <Carousel items={companiesData} />
        </div>
      </section>
    </div>
  );
}

export default Home;