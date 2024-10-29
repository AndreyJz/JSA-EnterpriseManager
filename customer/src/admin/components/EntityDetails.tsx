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
const H2 = styled.h2`
  font-size:2.5em;
  font-weight:600;

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

const NON_EDITABLE_FIELDS = ['id', 'creationDate', 'date', 'orderDate', 'assignDate','serviceBranch', 'password','username'];

const SELECT_FIELD_MAPPINGS: { [key: string]: { endpoint: string, labelKey: string } } = {
  emailType: { endpoint: 'Email_Type', labelKey: 'name' },
  phoneType: { endpoint: 'Phone_Type', labelKey: 'name' },
  personType: { endpoint: 'Person_Type', labelKey: 'name' },
  branch: { endpoint: 'Branches', labelKey: 'name' },
  city: { endpoint: 'Cities', labelKey: 'name' },
  company: { endpoint: 'Companies', labelKey: 'name' },
  region: { endpoint: 'Regions', labelKey: 'name' },
  companyType: { endpoint: 'Company_Type', labelKey: 'description' },
  serviceOrder: { endpoint: 'Service_Order', labelKey: 'id' },
  workOrder: { endpoint: 'Work_Orders', labelKey: 'workOrderNum' },
  approvalStatus: { endpoint: 'Approval_Status', labelKey: 'name' },
  person: { endpoint: 'Person', labelKey: 'id' },
  employee: { endpoint: 'Person', labelKey: 'id' },
  customer: { endpoint: 'Person', labelKey: 'id' },
  orderStatus: { endpoint: 'Order_Status', labelKey: 'name' },
  role: { endpoint: 'Roles', labelKey: 'name' },
  service: { endpoint: 'Services', labelKey: 'name' },
  workOrderDetailStatus: { endpoint: 'Work_Detail_Status', labelKey: 'name' },
  supply: { endpoint: 'Supply', labelKey: 'name' },
  country: { endpoint: 'Countries', labelKey: 'name' }
  // Add more mappings as needed
};

const EntityDetails: React.FC = () => {
  const { entity, id } = useParams<{ entity: string; id: string }>();
  const navigate = useNavigate();
  const [details, setDetails] = useState<any>(null);
  const [editedDetails, setEditedDetails] = useState<any>(null);
  const [isEditing, setIsEditing] = useState(false);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState<string | null>(null);
  const [selectOptions, setSelectOptions] = useState<{ [key: string]: any[] }>({});

  useEffect(() => {
    fetchDetails();
  }, [entity, id]);

  useEffect(() => {
    if (isEditing) {
      fetchSelectOptions();
    }
  }, [isEditing]);

  const fetchSelectOptions = async () => {
    const token = localStorage.getItem('token');
    const fields = entityFields[entity || ''] || [];
    
    const optionsPromises = fields.map(async (field) => {
      const mapping = SELECT_FIELD_MAPPINGS[field.name];
      if (mapping) {
        try {
          const response = await axios.get(`http://localhost:8081/api/${mapping.endpoint}`, {
            headers: { Authorization: `Bearer ${token}` }
          });
          return { [field.name]: response.data };
        } catch (error) {
          console.error(`Error fetching options for ${field.name}:`, error);
          return { [field.name]: [] };
        }
      }
      return null;
    });

    const resolvedOptions = await Promise.all(optionsPromises);
    const combinedOptions = resolvedOptions.reduce((acc, curr) => ({
      ...acc,
      ...(curr || {})
    }), {});

    setSelectOptions(combinedOptions);
  };

  const fetchDetails = async () => {
    try {
      setLoading(true);
      const token = localStorage.getItem('token');
      const response = await axios.get(
        `http://localhost:8081/api/${entity}/${id?.replaceAll('-', '/')}`,
        { headers: { Authorization: `Bearer ${token}` } }
      );
      setDetails(response.data);
      setEditedDetails(response.data);
      setError(null);
    } catch (err: any) {
      setError(err.response?.data?.message || 'Error fetching details');
    } finally {
      setLoading(false);
    }
  };

  const prepareUpdateData = () => {
    const updateData: any = { ...editedDetails };

    if (entity === 'Person') {
      return {
        id: updateData.id,
        name: updateData.name,
        lastname: updateData.lastname,
        username: updateData.username,
        password: updateData.password,
        date: updateData.date,
        branch: updateData.branch && typeof updateData.branch === 'object' ? { id: updateData.branch.id } : updateData.branch,
        personType: updateData.personType && typeof updateData.personType === 'object' ? { id: updateData.personType.id } : updateData.personType,
        role: updateData.role
      };
    }
    
    // Procesar campos select para asegurar que solo se envíe el ID
    Object.keys(SELECT_FIELD_MAPPINGS).forEach(fieldName => {
      if (updateData[fieldName] && typeof updateData[fieldName] === 'object') {
        updateData[fieldName] = { id: updateData[fieldName].id };
      }
    });

    // Casos especiales por entidad
    if (entity === 'Email') {
      if (updateData.person && typeof updateData.person === 'object') {
        updateData.person = { id: updateData.person.id };
      }
      if (updateData.emailType && typeof updateData.emailType === 'object') {
        updateData.emailType = { id: updateData.emailType.id };
      }
    }

    if (entity === 'Phone') {
      if (updateData.person && typeof updateData.person === 'object') {
        updateData.person = { id: updateData.person.id };
      }
      if (updateData.phoneType && typeof updateData.phoneType === 'object') {
        updateData.phoneType = { id: updateData.phoneType.id };
      }
    }

    if (entity === 'Person') {
      updateData.branch = { id: editedDetails.branch.id };
      updateData.lastname = editedDetails.lastname;
      updateData.name = editedDetails.name;
      updateData.date = editedDetails.date;
      updateData.password = editedDetails.password;
      updateData.personType = { id: editedDetails.personType.id };
      updateData.repeatedPassword = editedDetails.repeatedPassword; // Asumido, puede ser cambiado según tu lógica
      updateData.role = { id: editedDetails.role.id };
      updateData.username = editedDetails.username;
    }

    // Agregar más casos especiales según sea necesario

    return updateData;
  };

  const handleDelete = async () => {
    if (!window.confirm('Are you sure you want to delete this item?')) {
      return;
    }

    try {
      const token = localStorage.getItem('token');
      await axios.delete(`http://localhost:8081/api/${entity}/${id?.replaceAll('-', '/')}`, {
        headers: { Authorization: `Bearer ${token}` }
      });
      navigate(`/admin/${entity}`);
    } catch (err: any) {
      setError(err.response?.data?.message || 'Error deleting item');
    }
  };
  const handleUpdate = async () => {
    if (!isEditing) {
      setIsEditing(true);
      return;
    }

    try {
      const token = localStorage.getItem('token');
      const updateData = prepareUpdateData();
      console.log(updateData);
      await axios.put(
        `http://localhost:8081/api/${entity}/${id?.replaceAll('-', '/')}`,
        updateData,
        { headers: { Authorization: `Bearer ${token}` } }
      );
      setIsEditing(false);
      fetchDetails();
    } catch (err: any) {
      setError(err.response?.data?.message || 'Error updating item');
    }
  };

  const handleCancel = () => {
    setIsEditing(false);
    setEditedDetails(details);
  };

  const handleInputChange = (fieldName: string, value: any) => {
    setEditedDetails((prev: any) => ({
      ...prev,
      [fieldName]: value
    }));
  };

  const renderField = (field: entityField) => {
    const value = isEditing ? editedDetails[field.name] : details[field.name];
    const isReadOnly = NON_EDITABLE_FIELDS.includes(field.name);

    if (!isEditing || isReadOnly) {
      return formatValue(details[field.name], field.type, field.name);
    }

    // Handle select fields
    if (SELECT_FIELD_MAPPINGS[field.name]) {
      const options = selectOptions[field.name] || [];
      const mapping = SELECT_FIELD_MAPPINGS[field.name];
      return (
        <select
          value={value?.id || value || ''}
          onChange={(e) => handleInputChange(field.name, { id: e.target.value })}
          className="w-full p-2 border rounded"
        >
          <option value="">Select {field.name}</option>
          {options.map((option: any) => (
            <option key={option.id} value={option.id}>
              {option[mapping.labelKey]}
            </option>
          ))}
        </select>
      );
    }

    // Handle other editable fields
    switch (field.type) {
      case 'boolean':
        return (
          <input
            type="checkbox"
            checked={value}
            onChange={(e) => handleInputChange(field.name, e.target.checked)}
            className="w-4 h-4"
          />
        );
      case 'number':
        return (
          <input
            type="number"
            value={value || ''}
            onChange={(e) => handleInputChange(field.name, e.target.value)}
            className="w-full p-2 border rounded"
          />
        );
      default:
        return (
          <input
            type="text"
            value={value || ''}
            onChange={(e) => handleInputChange(field.name, e.target.value)}
            className="w-full p-2 border rounded"
          />
        );
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

    // Handle nested objects
    if (typeof value === 'object') {
      // Solo intentar casos especiales si fieldName está definido
      if (fieldName) {
        const normalizedFieldName = fieldName.toLowerCase();

        // Casos especiales para Email
        if (normalizedFieldName === 'emailtype' && value.name) {
          return value.name;
        }
        if (normalizedFieldName === 'workorder') {
          return value.workOrderNum;
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
      <H2>{entity?.replace(/_/g, ' ')} Details</H2>

      {details && entityFields[entity || '']?.map((field) => (
        <AttributeRow key={field.name}>
          <AttributeLabel>{getDisplayName(field.name)}:</AttributeLabel>
          <AttributeValue>
            {renderField(field)}
          </AttributeValue>
        </AttributeRow>
      ))}

      <ButtonContainer>
        <Button onClick={handleUpdate}>
          {isEditing ? 'Confirm' : 'Update'}
        </Button>
        {isEditing && (
          <Button
            onClick={handleCancel}
            color="#808080"
            hoverColor="#666666"
          >
            Cancel
          </Button>
        )}
        {!isEditing && (
          <Button
            onClick={handleDelete}
            color="#d32f2f"
            hoverColor="#b71c1c"
          >
            Delete
          </Button>
        )}
      </ButtonContainer>
    </DetailsContainer>
  );
};

export default EntityDetails;