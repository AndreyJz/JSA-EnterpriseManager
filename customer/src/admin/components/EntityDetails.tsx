import React, { useEffect, useState } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import styled from 'styled-components';
import axios from 'axios';

const DetailsContainer = styled.div`
  background-color: #f5f5f5;
  padding: 20px;
  border-radius: 4px;
`;

const AttributeRow = styled.div`
  display: flex;
  margin-bottom: 10px;
  padding: 10px;
  background-color: white;
  border-radius: 4px;
  box-shadow: 0 1px 3px rgba(0, 0, 0, 0.1);
`;

const AttributeLabel = styled.div`
  font-weight: bold;
  width: 150px;
  color: #333;
`;

const AttributeValue = styled.div`
  flex-grow: 1;
  color: #666;
`;

const Button = styled.button`
  margin-right: 10px;
  padding: 10px 20px;
  background-color: ${props => props.color || '#1e88e5'};
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  transition: background-color 0.3s;
  &:hover {
    background-color: ${props => props.hoverColor || '#1565c0'};
  }
`;

const ButtonContainer = styled.div`
  margin-top: 20px;
  display: flex;
  gap: 10px;
`;

const LoadingSpinner = styled.div`
  text-align: center;
  padding: 20px;
  color: #666;
`;

const ErrorMessage = styled.div`
  color: #d32f2f;
  background-color: #ffebee;
  padding: 10px;
  border-radius: 4px;
  margin-bottom: 20px;
`;

interface entityField {
  name: string;
  type: string;
}

const entityFields: { [key: string]: entityField[] } = {
  "Approval_Status": [
    { name: 'id', type: 'text' },
    { name: 'name', type: 'text' },
  ],
  "Branches": [
    { name: 'id', type: 'text' },
    { name: 'creationDate', type: 'dateTime' },
    { name: 'name', type: 'text' },
    { name: 'nit', type: 'text' },
    { name: 'city', type: 'number' },
    { name: 'company', type: 'number' },
  ],
  "Cities": [
    { name: 'id', type: 'text' },
    { name: 'name', type: 'text' },
    { name: 'region', type: 'number' },
  ],
  "Companies": [
    { name: 'id', type: 'text' },
    { name: 'name', type: 'text' },
    { name: 'companyType', type: 'number' },
  ],
  "Company_Type": [
    { name: 'id', type: 'text' },
    { name: 'description', type: 'text' },
  ],
  "Countries": [
    { name: 'id', type: 'text' },
    { name: 'name', type: 'text' },
  ],
  "Email": [
    { name: 'id', type: 'text' },
    { name: 'mail', type: 'text' },
    { name: 'emailType', type: 'number' },
    { name: 'person', type: 'text' },
  ],
  "Email_Type": [
    { name: 'id', type: 'text' },
    { name: 'name', type: 'text' },
  ],
  "Order_Details": [
    { name: 'id', type: 'text' },
    { name: 'serviceValue', type: 'number' },
    { name: 'serviceBranch', type: 'text' },
    { name: 'serviceOrder', type: 'number' },
  ],
  "Order_Status": [
    { name: 'id', type: 'text' },

    { name: 'name', type: 'text' },
  ],
  "Person": [
    { name: 'id', type: 'text' },
    { name: 'name', type: 'text' },
    { name: 'lastname', type: 'text' },
    { name: 'username', type: 'text' },
    { name: 'password', type: 'text' },
    { name: 'role', type: 'select' },
    { name: 'date', type: 'dateTime' },
    { name: 'branch', type: 'number' },
    { name: 'personType', type: 'number' },
  ],
  "Person_Supply": [
    { name: 'person', type: 'text' },
    { name: 'supply', type: 'text' },
    { name: 'quantity', type: 'number' },
  ],
  "Person_Type": [
    { name: 'id', type: 'text' },

    { name: 'name', type: 'text' },
  ],
  "Phone": [
    { name: 'id', type: 'text' },

    { name: 'number', type: 'text' },
    { name: 'phoneType', type: 'number' },
    { name: 'person', type: 'text' },
  ],
  "Phone_Type": [
    { name: 'id', type: 'text' },

    { name: 'name', type: 'text' },
  ],
  "Regions": [
    { name: 'id', type: 'text' },

    { name: 'name', type: 'text' },
    { name: 'country', type: 'number' },
  ],
  "Service_Approval": [
    { name: 'id', type: 'text' },

    { name: 'report', type: 'text' },
    { name: 'solution', type: 'text' },
    { name: 'approvalStatus', type: 'number' },
    { name: 'serviceBranch', type: 'text' },
    { name: 'workOrder', type: 'number' },
  ],
  "Service_Branches": [
    { name: 'branch', type: 'text' },
    { name: 'service', type: 'text' },
    { name: 'serviceValue', type: 'number' },
  ],
  "Service_Order": [
    { name: 'id', type: 'text' },

    { name: 'orderDate', type: 'dateTime' },
    { name: 'orderStatus', type: 'number' },
    { name: 'customer', type: 'text' },
    { name: 'employee', type: 'text' },
  ],
  "Services": [
    { name: 'id', type: 'text' },

    { name: 'name', type: 'text' },
    { name: 'requiresSupply', type: 'boolean' },
  ],
  "Supply": [
    { name: 'id', type: 'text' },

    { name: 'name', type: 'text' },
    { name: 'barcode', type: 'text' },
    { name: 'price', type: 'number' },
    { name: 'stock', type: 'number' },
    { name: 'stockMax', type: 'number' },
    { name: 'stockMin', type: 'number' },
  ],
  "Supply_Service": [

    { name: 'serviceBranch', type: 'number' },
    { name: 'supply', type: 'number' },
    { name: 'quantity', type: 'number' },
  ],
  "Work_Order_Detail": [
    { name: 'id', type: 'text' },
    { name: 'serviceBranch', type: 'number' },

    { name: 'date', type: 'number' },
    { name: 'workOrderDetailStatus', type: 'number' },
    { name: 'workOrder', type: 'number' },
    { name: 'person', type: 'text' },
  ],
  "Work_Detail_Status": [
    { name: 'id', type: 'text' },

    { name: 'name', type: 'text' },
  ],
  "Work_Orders": [
    { name: 'id', type: 'text' },

    { name: 'workOrderNum', type: 'text' },
    { name: 'assignDate', type: 'text' },
    { name: 'serviceOrder', type: 'number' },
  ],
};


const EntityDetails: React.FC = () => {
  const { entity, id } = useParams<{ entity: string; id: string }>();
  const navigate = useNavigate();
  const [details, setDetails] = useState<any>(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState<string | null>(null);

  useEffect(() => {
    const fetchDetails = async () => {
      try {
        setLoading(true);
        const token = localStorage.getItem('token'); // Obtén el token desde localStorage

        const response = await axios.get(`http://localhost:8081/api/${entity}/${id?.replaceAll('-', '/')}`, {
          headers: {
            Authorization: `Bearer ${token}` // Agrega el token en el encabezado
          }
        });

        setDetails(response.data);
        setError(null);
      } catch (err: any) {
        setError(err.response?.data?.message || 'Error fetching details');
      } finally {
        setLoading(false);
      }
    };

    fetchDetails();
  }, [entity, id]);

  const handleDelete = async () => {
    if (!window.confirm('Are you sure you want to delete this item?')) {
      return;
    }

    try {
      const token = localStorage.getItem('token'); // Obtén el token desde localStorage

      await axios.delete(`http://localhost:8081/api/${entity}/${id}`, {
        headers: {
          Authorization: `Bearer ${token}` // Agrega el token en el encabezado
        }
      });

      navigate(`/admin/${entity}`);
    } catch (err: any) {
      setError(err.response?.data?.message || 'Error deleting item');
    }
  };

  const handleUpdate = () => {
    // Navigation to update page can be implemented here
    console.log('Update functionality to be implemented');
  };

  const getNestedValue = (obj: any, path: string) => {
    try {
      return path.split('.').reduce((current, key) => current[key], obj);
    } catch (error) {
      return null;
    }
  };

  const formatValue = (value: any, type: string, fieldName?: string): string => {
    if (value === null || value === undefined) return 'N/A';

    // Handle date formatting
    if (type === 'dateTime') {
      try {
        const date = new Date(value);
        return date.toLocaleString();
      } catch (e) {
        return value;
      }
    }

    if (entity === 'Order_Details' || entity === 'Supply_Service' || entity === 'Work_Order_Detail') {
      if (fieldName === 'serviceBranch') {
        return (
            <div>
              <strong>Service:</strong> {value?.service?.name || 'N/A'} <br />
              <strong>Branch:</strong> {value?.branch?.name || 'N/A'}
            </div>
        );
      }
    }
    if(entity ===  'Work_Order_Detail'){
      if (fieldName === 'workOrder'){
        return value.workOrderNum
      }
    }

    if (entity === 'Service_Approval') {
      if (fieldName === 'serviceBranch') {
        return (
            <div>
              <strong>Service:</strong> {value.service.name || 'N/A'} <br />
              <strong>Branch:</strong> {value.branch.name || 'N/A'}
            </div>
        );
      }
    }

    console.log(details)
    // Handle nested objects
    if (typeof value === 'object') {
      // Solo intentar casos especiales si fieldName está definido
      if (fieldName) {
        const normalizedFieldName = fieldName.toLowerCase();

        // Casos especiales para Email
        if (normalizedFieldName === 'emailtype' && value.name) {
          return value.name;
        }

        if (normalizedFieldName === 'person' && value.id) {
          return value.id;
        }
        if (normalizedFieldName === 'customer' && value.id) {
          return value.id;
        }
        if (normalizedFieldName === 'employee' && value.id) {
          return value.id;
        }

        // Puedes agregar más casos especiales aquí
        if (normalizedFieldName === 'phonetype' && value.name) {
          return value.name;
        }
      }

      // Manejo general de objetos
      if ('name' in value) return value.name;
      if ('description' in value) return value.description;
      if ('id' in value) return value.id.toString();
      return JSON.stringify(value);
    }

    return value.toString();
  };

  if (loading) {
    return <LoadingSpinner>Loading...</LoadingSpinner>;
  }

  if (error) {
    return <ErrorMessage>{error}</ErrorMessage>;
  }



  const getDisplayName = (fieldName: string): string => {
    // Convert camelCase to Title Case for display
    return fieldName
        .replace(/([A-Z])/g, ' $1')
        .replace(/^./, str => str.toUpperCase());
  };

  return (
      <DetailsContainer>
        <h2>{entity?.replace(/_/g, ' ')} Details</h2>

        {details && entityFields[entity || '']?.map((field) => (
            <AttributeRow key={field.name}>
              <AttributeLabel>{getDisplayName(field.name)}:</AttributeLabel>
              <AttributeValue>
                {formatValue(details[field.name], field.type,field.name)}
              </AttributeValue>
            </AttributeRow>
        ))}

        <ButtonContainer>
          <Button onClick={handleUpdate}>
            Update
          </Button>
          <Button
              onClick={handleDelete}
              color="#d32f2f"
              hoverColor="#b71c1c"
          >
            Delete
          </Button>
        </ButtonContainer>
      </DetailsContainer>
  );
};

export default EntityDetails;