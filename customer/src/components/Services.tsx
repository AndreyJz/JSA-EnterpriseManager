import React, { useEffect, useState } from 'react';
import axios from "axios";
import Service from './Service';
import { useCart } from '../context/CartContext';

function Services() {
  const [data, setData] = useState<any[]>([]);
  const [loading, setLoading] = useState<boolean>(true);
  const [error, setError] = useState<string | null>(null);
  const [searchTerm, setSearchTerm] = useState<string>('');
  const [filterType, setFilterType] = useState<string>('all'); // 'all', 'service', 'company'
  const { addToCart } = useCart();
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

  useEffect(() => { getData(); }, []);

  // Filtrar los servicios basados en el término de búsqueda y el tipo de filtro
  const filteredServices = data.filter((service) => {
    const searchTermLower = searchTerm.toLowerCase();
    
    switch(filterType) {
      case 'service':
        return service.service.name.toLowerCase().includes(searchTermLower);
      case 'company':
        return service.branch.company.name.toLowerCase().includes(searchTermLower);
      default:
        return (
          service.service.name.toLowerCase().includes(searchTermLower) ||
          service.branch.company.name.toLowerCase().includes(searchTermLower)
        );
    }
  });

  // Manejar la acción de añadir al carrito y mostrar la alerta
  const handleAddToCart = (service: any) => {
    addToCart(service); 
    setShowAlert(true);  // Mostrar la alerta
    setTimeout(() => setShowAlert(false), 1000); // Ocultar después de 1 segundos
  };

  if (loading) return <p>Loading...</p>;
  if (error) return <p>{error}</p>;

  return (
    <div className="bg-gray-100 py-16">
      <div className="container mx-auto px-4">
        <h1 className="text-4xl font-bold text-center mb-8">Our Services</h1>
        
        {/* Barra de búsqueda con filtro */}
        <div className="mb-8 max-w-3xl mx-auto">
          <div className="flex gap-4 justify-center">
            <div className='flex items-center justify-center'>
              <p>Filter:</p>
            </div>
            <select
              value={filterType}
              onChange={(e) => setFilterType(e.target.value)}
              className="px-4 py-2 rounded-lg border border-gray-300 focus:outline-none focus:border-blue-500"
            >
              <option value="all">None</option>
              <option value="service">Service</option>
              <option value="company">Company</option>
            </select>

            <input
              type="text"
              placeholder={
                filterType === 'service' 
                  ? "Search by service..." 
                  : filterType === 'company' 
                    ? "Search by company..." 
                    : "Search by service or company..."
              }
              value={searchTerm}
              onChange={(e) => setSearchTerm(e.target.value)}
              className="w-full max-w-md px-4 py-2 rounded-lg border border-gray-300 focus:outline-none focus:border-blue-500"
            />

            {searchTerm && (
              <button
                onClick={() => {
                  setSearchTerm('');
                  setFilterType('all');
                }}
                className="px-4 py-2 bg-gray-200 rounded-lg hover:bg-gray-300"
              >
                Limpiar
              </button>
            )}
          </div>
        </div>

        {/* Grid de servicios */}
        <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-8">
          {filteredServices.length > 0 ? (
            filteredServices.map((service) => (
              <Service
                key={`${service.id.branchId}-${service.id.serviceId}`}
                serviceData={service}
                onAddToCart={() => handleAddToCart(service)} // Usar la función que maneja la alerta
              />
            ))
          ) : (
            <p className="col-span-full text-center text-gray-500">
              No se encontraron servicios que coincidan con tu búsqueda.
            </p>
          )}
        </div>

        {/* Mostrar alerta si showAlert es true */}
        {showAlert && (
          <div className="fixed top-3 right-1 transform -translate-x-1/2 bg-green-500 text-white px-4 py-2 rounded">
            Item added successfully!
          </div>
        )}
      </div>
    </div>
  );
}

export default Services;
