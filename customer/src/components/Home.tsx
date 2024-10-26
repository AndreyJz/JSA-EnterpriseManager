import React, { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import Service from './Service';
import Carousel from './Carousel';
import { useCart } from '../context/CartContext';
import axios from 'axios';


function Home() {
  const { addToCart } = useCart();
  const [servicedata, setData] = useState<any[]>([]);
  const [companiesData, setCompanies] = useState<any[]>([]);
  const [loading, setLoading] = useState<boolean>(true);
  const [error, setError] = useState<string | null>(null);
  const [loading1, setLoading1] = useState<boolean>(true);
  const [error1, setError1] = useState<string | null>(null);
  const [showAlert, setShowAlert] = useState<boolean>(false); // Estado para la alerta


  const getData = async () => {
    try {
      const response = await axios.get<any[]>("http://localhost:8081/api/Service Branches");
      setData(response.data);
    } catch (err) {
      if (axios.isAxiosError(err)) {
        console.error("Error con Axios:", err.message);
        setError("Error al obtener datos del servidor.");
      } else {
        console.error("Error inesperado:", err);
        setError("Ocurrió un error inesperado.");
      }
    } finally {
      setLoading(false);
      
    }
  };
  const getCompanies = async () => {
    try {
      const responseCompany = await axios.get<any[]>("http://localhost:8081/api/Companies");
      setCompanies(responseCompany.data);
    } catch (err) {
      if (axios.isAxiosError(err)) {
        console.error("Error con Axios:", err.message);
        setError1("Error al obtener datos del servidor.");
      } else {
        console.error("Error inesperado:", err);
        setError1("Ocurrió un error inesperado.");
      }
    } finally {
      setLoading1(false);
    }
  };

  useEffect(() => {
    getData();
    getCompanies();
  }, []);

  if (loading) return <p>Loading...</p>;
  if (error) return <p>{error}</p>;
  if (loading1) return <p>Loading...</p>;
  if (error1) return <p>{error1}</p>;


  const handleAddToCart = (service: any) => {
    addToCart(service); 
    setShowAlert(true);  // Mostrar la alerta
    setTimeout(() => setShowAlert(false), 1000); // Ocultar después de 1 segundos
  };

  
  return (
    <div className="bg-gray-100">
      <section className="hero text-black py-20 px-20">
        <div className="container mx-auto px-4 flex flex-col md:flex-row items-center">
          <div className="md:w-4/5 mb-8 md:mb-0">
            <h1 className="text-5xl font-bold mb-4">Empower Your Business with Our Enterprise Solutions</h1>
            <p className="text-2xl mb-6">
              Streamline operations, boost productivity, and drive growth with our cutting-edge JSA products and services.
            </p>
            <div className="space-x-4">
              <Link to="/contact" className="bg-black text-white px-6 py-3 rounded-full font-semibold hover:bg-blue-100 hover:text-blue-600 transition duration-300">Contact Us</Link>
              <Link to="/about" className="border-2 border-black px-6 py-3 rounded-full font-semibold hover:bg-white hover:text-blue-600 transition duration-300">About Us</Link>
            </div>
          </div>
          <div className=" md:w-full flex justify-center items-center">
            <img src="../../src/images/testhome.png" alt="Enterprise Solutions" className="rounded-lg md:w-2/3" />
          </div>
        </div>
      </section>

      <section className="featured-products py-16">
        <div className="container mx-auto px-4">
          <h2 className="text-3xl font-bold text-center mb-12">Featured Services</h2>
          <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-8">
            {servicedata.map((service) => (
              <Service
              key={`${service.id.branchId}-${service.id.serviceId}`}
              serviceData={service}
              onAddToCart={() => handleAddToCart(service)} // Usar la función que maneja la alerta
            />
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

      {showAlert && (
          <div className="fixed bottom-3 right-0 transform -translate-x-1/2 bg-green-500 text-white px-4 py-2 rounded">
            Item added successfully!
          </div>
        )}

    </div>
  );
}

export default Home;