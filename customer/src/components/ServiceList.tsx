import { useState } from 'react';
import { ChevronDown, ChevronUp } from 'lucide-react';
import { ServiceStatus, ServiceDetails } from '../types';

interface ServiceListProps {
userId: number;
services: ServiceStatus[];
}

export function ServiceList({ userId, services }: ServiceListProps) {
const [expandedServices, setExpandedServices] = useState<number[]>([]);
const [serviceDetails, setServiceDetails] = useState<{ [key: number]: ServiceDetails }>({});

const getStatusColor = (status: ServiceStatus['status']) => {
    switch (status) {
    case 'active': return 'bg-green-500';
    case 'pending': return 'bg-yellow-500';
    case 'inactive': return 'bg-red-500';
    default: return 'bg-gray-500';
    }
};

const toggleServiceExpansion = async (serviceId: number) => {
    setExpandedServices(prev => 
    prev.includes(serviceId) 
        ? prev.filter(id => id !== serviceId)
        : [...prev, serviceId]
    );

    if (!serviceDetails[serviceId]) {
    try {
        // Test URL - Replace with your actual API endpoint
        const response = await fetch(`https://jsonplaceholder.typicode.com/users/${userId}/services/${serviceId}/details`);
        const details = await response.json();
        setServiceDetails(prev => ({ ...prev, [serviceId]: details }));
    } catch (error) {
        console.error('Error fetching service details:', error);
    }
    }
};

return (
    <div className="bg-gray-100 p-6 rounded-lg">
    <h2 className="text-2xl font-semibold mb-4">Services</h2>
    <ul>
        {services.map(service => (
        <li key={service.id} className="mb-4">
            <div 
            className="flex items-center justify-between bg-white p-4 rounded cursor-pointer"
            onClick={() => toggleServiceExpansion(service.id)}
            >
            <div className="flex items-center">
                <div className={`w-4 h-4 rounded-full ${getStatusColor(service.status)} mr-4`}></div>
                <span>{service.title}</span>
            </div>
            {expandedServices.includes(service.id) ? <ChevronUp /> : <ChevronDown />}
            </div>
            {expandedServices.includes(service.id) && (
            <div className="bg-gray-50 p-4 rounded mt-2">
                {serviceDetails[service.id] ? (
                <>
                    <p><strong>Description:</strong> {serviceDetails[service.id].description}</p>
                    <p><strong>Start Date:</strong> {serviceDetails[service.id].startDate}</p>
                    <p><strong>End Date:</strong> {serviceDetails[service.id].endDate}</p>
                </>
                ) : (
                <p>Loading service details...</p>
                )}
            </div>
            )}
        </li>
        ))}
    </ul>
    </div>
);
}