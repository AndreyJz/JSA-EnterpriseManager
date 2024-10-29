import { useState } from 'react';
import { ChevronDown, ChevronUp } from 'lucide-react';
import { ServiceStatus, OrderDetail } from '../types';

interface ServiceListProps {
  services: ServiceStatus[];
}

export function ServiceList({ services }: ServiceListProps) {
  const [expandedServices, setExpandedServices] = useState<number[]>([]);
  const [serviceDetails, setServiceDetails] = useState<{ [key: number]: OrderDetail[] }>({});
  const [loading, setLoading] = useState<{ [key: number]: boolean }>({});

  const formatDate = (dateString: string) => {
    return new Date(dateString).toLocaleDateString('en-US', {
      year: 'numeric',
      month: 'short',
      day: 'numeric',
      hour: '2-digit',
      minute: '2-digit'
    });
  };

  const fetchServiceDetails = async (serviceId: number) => {
    const token = localStorage.getItem('token');
    if (!token) {
      console.error('No authentication token found');
      return;
    }

    setLoading(prev => ({ ...prev, [serviceId]: true }));

    try {
      const response = await fetch(`http://localhost:8081/api/Order_Details/ServiceOrder_${serviceId}`, {
        headers: {
          'Authorization': `Bearer ${token}`,
          'Content-Type': 'application/json'
        }
      });

      if (!response.ok) {
        throw new Error('Failed to fetch service details');
      }

      const details = await response.json();
      setServiceDetails(prev => ({ ...prev, [serviceId]: details }));
    } catch (error) {
      console.error('Error fetching service details:', error);
    } finally {
      setLoading(prev => ({ ...prev, [serviceId]: false }));
    }
  };

  const toggleServiceExpansion = async (serviceId: number) => {
    if (expandedServices.includes(serviceId)) {
      setExpandedServices(prev => prev.filter(id => id !== serviceId));
    } else {
      setExpandedServices(prev => [...prev, serviceId]);
      if (!serviceDetails[serviceId]) {
        await fetchServiceDetails(serviceId);
      }
    }
  };

  return (
    <div className="bg-gray-50 p-6 rounded-lg shadow-sm">
      <h2 className="text-2xl font-semibold mb-6 text-gray-800">Purchases</h2>
      <div className="space-y-4">
        {services.map(service => (
          <div key={service.id} className="bg-white rounded-lg shadow-sm overflow-hidden">
            <div 
              className="flex items-center justify-between p-4 hover:bg-gray-50 transition-colors cursor-pointer"
              onClick={() => toggleServiceExpansion(service.id)}
            >
              <div className="flex items-center space-x-4">
                <span className="text-lg font-medium text-gray-700">#{service.id}</span>
                <span className="text-gray-600">{formatDate(service.orderDate)}</span>
              </div>
              {expandedServices.includes(service.id) ? 
                <ChevronUp className="text-gray-500 w-5 h-5" /> : 
                <ChevronDown className="text-gray-500 w-5 h-5" />
              }
            </div>
            
            {expandedServices.includes(service.id) && (
              <div className="border-t border-gray-100 p-4">
                {loading[service.id] ? (
                  <div className="flex justify-center py-4">
                    <div className="animate-spin rounded-full h-6 w-6 border-b-2 border-gray-900"></div>
                  </div>
                ) : serviceDetails[service.id] ? (
                  <div className="space-y-3">
                    {serviceDetails[service.id].map((detail, index) => (
                      <div key={index} className="p-3 bg-gray-50 rounded-lg">
                        <div className="grid grid-cols-3 gap-4">
                          <div>
                            <p className="text-sm text-gray-500">Service</p>
                            <p className="font-medium text-gray-900">{detail.serviceBranch.service.name}</p>
                          </div>
                          <div>
                            <p className="text-sm text-gray-500">Branch</p>
                            <p className="font-medium text-gray-900">{detail.serviceBranch.branch.name}</p>
                          </div>
                          <div>
                            <p className="text-sm text-gray-500">Value</p>
                            <p className="font-medium text-gray-900">
                              ${detail.serviceBranch.serviceValue.toLocaleString('en-US', {
                                minimumFractionDigits: 2,
                                maximumFractionDigits: 2
                              })}
                            </p>
                          </div>
                        </div>
                      </div>
                    ))}
                  </div>
                ) : (
                  <p className="text-gray-500 text-center py-2">No details available</p>
                )}
              </div>
            )}
          </div>
        ))}
      </div>
    </div>
  );
}