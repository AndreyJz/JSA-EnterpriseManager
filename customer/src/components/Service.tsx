import React from 'react';
import { ServiceData } from '../types';

interface ServiceProps {
serviceData: ServiceData
	
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