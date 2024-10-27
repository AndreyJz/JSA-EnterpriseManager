import React, { useEffect, useState } from 'react';
import Company from './Company';
import axios from 'axios';



function Companies() {
  const [companiesData, setCompanies] = useState<any[]>([]);
  const [loading, setLoading] = useState<boolean>(true);
  const [error, setError] = useState<string | null>(null);


  const getCompanies = async () => {
    try {
      const responseCompany = await axios.get<any[]>("http://localhost:8081/api/Companies");
      setCompanies(responseCompany.data);
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

  useEffect(() => {
    getCompanies();
  }, []);

  if (loading) return <p>Loading...</p>;
  if (error) return <p>{error}</p>;


  return (
    <div className="bg-gray-100 py-16">
      <div className="container mx-auto px-4">
        <h1 className="text-4xl font-bold text-center mb-12">Our Partner Companies</h1>
        <div className="grid grid-cols-2 md:grid-cols-3 lg:grid-cols-4 gap-8">
          {companiesData.map((company, index) => (
            <Company key={index} companyData={company} />
          ))}
        </div>
      </div>
    </div>
  );
}

export default Companies;