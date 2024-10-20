import React from 'react';
import Service from './Service';
import { useCart } from '../context/CartContext';

const allServicesData = [
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
  },
  {
    id: 5,
    title: "Network Infrastructure Setup",
    price: 5999,
    description: "Complete network infrastructure design and implementation.",
  },
  {
    id: 6,
    title: "Data Backup and Recovery",
    price: 1499,
    description: "Robust data backup and disaster recovery solutions.",
  },
];

function Services() {
  const { addToCart } = useCart();

  return (
    <div className="bg-gray-100 py-16">
      <div className="container mx-auto px-4">
        <h1 className="text-4xl font-bold text-center mb-12">Our Services</h1>
        <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-8">
          {allServicesData.map((service) => (
            <Service key={service.id} serviceData={service} onAddToCart={() => addToCart(service)} />
          ))}
        </div>
      </div>
    </div>
  );
}

export default Services;