import React from 'react';

interface ServiceProps {
  serviceData: {
		"id": {
			"branchId": number,
			"serviceId": number
		},
		"service": {
			"id": number,
			"name": string,
			"requiresSupply": boolean
		},
		"branch": {
			"id": number,
			"name": string,
			"nit": string,
			"creationDate": string,
			"city": {
				"id": number,
				"name": string,
				"region": {
					"id": number,
					"name": string,
					"country": {
						"id": number,
						"name": string
					}
				}
			},
			"company": {
				"id": number,
				"name": string,
				"companyType": {
					"id": number,
					"description": string
				}
			}
		},
		"serviceValue":number
	}
  onAddToCart: () => void;
}

function Service({ serviceData, onAddToCart }: ServiceProps) {
  return (
    <div className="bg-white p-6 rounded-lg shadow-md">
      <h3 className="text-xl font-semibold mb-2">{serviceData.service.name}</h3>
      <p className="text-gray-600 mb-4">{serviceData.branch.company.name}</p>
      <div className="flex justify-between items-center">
        <span className="text-2xl font-bold">${serviceData.serviceValue}</span>
        <button
          onClick={onAddToCart}
          className="bg-black text-white px-4 py-2 rounded hover:bg-blue-700 transition duration-300"
        >
          Add to Cart
        </button>
      </div>
    </div>
  );
}

export default Service;