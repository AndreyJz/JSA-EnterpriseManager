import React, { useState, useEffect } from 'react';
import { useParams, Link } from 'react-router-dom';
import styled from 'styled-components';
import axios from 'axios';

const EntityGrid = styled.div`
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(200px, 1fr));
  gap: 30px;
`;

const EntityCard = styled(Link)`
  display: flex;
  justify-content: center;
  align-items: center;
  height: 150px;
  background-color: #1e88e5;
  color: white;
  text-decoration: none;
  font-size: 18px;
  border-radius: 4px;
  transition: background-color 0.3s;
  overflow-x:hidden;
  padding: 20px;

  &:hover {
    background-color: #1565c0;
  }
`;

const SearchBar = styled.input`
  width: 100%;
  padding: 10px;
  margin-bottom: 20px;
  font-size: 16px;
`;

const AddButton = styled.button`
  position: fixed;
  bottom: 20px;
  right: 20px;
  width: 50px;
  height: 50px;
  border-radius: 50%;
  background-color: #4CAF50;
  color: white;
  font-size: 24px;
  border: none;
  cursor: pointer;
  display: flex;
  justify-content: center;
  align-items: center;
  
  &:hover {
    background-color: #45a049;
  }
`;

const Modal = styled.div`
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.5);
  display: flex;
  justify-content: center;
  align-items: center;
`;

const ModalContent = styled.div`
  background-color: white;
  padding: 20px;
  border-radius: 4px;
  width: 300px;
`;

const Input = styled.input`
  width: 100%;
  padding: 10px;
  margin-bottom: 10px;
  border-radius:5px;

`;

const Button = styled.button`
  padding: 10px;
  background-color: #4CAF50;
  color: white;
  border: none;
  cursor: pointer;
  width: 100%;
  border-radius:5px;

  &:hover {
    background-color: #45a049;
  }
`;

interface EntityField {
  name: string;
  type: string;
}

const entityFields: { [key: string]: EntityField[] } = {
  "Approval Status": [
    { name: 'Name', type: 'text' },
  ],
  Branches: [
    { name: 'Name', type: 'text' },
    { name: 'Nit', type: 'text' },
    { name: 'City', type: 'number' },
    { name: 'Company', type: 'number' },
  ],
  Cities: [
    { name: 'Name', type: 'text' },
    { name: 'Region', type: 'number' },
  ],
  Companies: [
    { name: 'Name', type: 'text' },
    { name: 'CompanyType', type: 'number' },
  ],
  "Company Type": [
    { name: 'Description', type: 'text' },
  ],
  Countries: [
    { name: 'Name', type: 'text' },
  ],
  Email: [
    { name: 'Mail', type: 'text' },
    { name: 'EmailType', type: 'number' },
    { name: 'PersonId', type: 'text' },
  ],
  "Email Type": [
    { name: 'Name', type: 'text' },
  ],
  "Order Details": [
    { name: 'Value', type: 'number' },
    { name: 'Branch', type: 'number' },
    { name: 'Service', type: 'number' },
    { name: 'ServiceOrder', type: 'number' },
  ],
  "Order Status": [
    { name: 'Name', type: 'text' },
  ],
  Person: [
    { name: 'Id', type: 'text' },
    { name: 'Name', type: 'text' },
    { name: 'Lastname', type: 'text' },
    { name: 'Username', type: 'text' },
    { name: 'Password', type: 'text' },
    { name: 'Role', type: 'select' },
    { name: 'Branch', type: 'number' },
    { name: 'PersonType', type: 'number' },
  ],
  "Person Supply": [
    { name: 'Quantity', type: 'number' },
    { name: 'Supply', type: 'number' },
    { name: 'PersonId', type: 'text' },
  ],
  "Person Type": [
    { name: 'Name', type: 'text' },
  ],
  Phone: [
    { name: 'Number', type: 'text' },
    { name: 'PhoneType', type: 'number' },
    { name: 'PersonId', type: 'text' },
  ],
  "Phone Type": [
    { name: 'Name', type: 'text' },
  ],
  Regions: [
    { name: 'Name', type: 'text' },
    { name: 'Country', type: 'number' },
  ],
  "Service Approval": [
    { name: 'Report', type: 'text' },
    { name: 'Solution', type: 'text' },
    { name: 'ApprovalStatus', type: 'number' },
    { name: 'Branch', type: 'number' },
    { name: 'Service', type: 'number' },
    { name: 'WorkOrder', type: 'number' },
  ],
  "Service Branches": [
    { name: 'Value', type: 'number' },
    { name: 'Branch', type: 'number' },
    { name: 'Service', type: 'number' },
  ],
  "Service Order": [
    { name: 'OrderStatus', type: 'number' },
    { name: 'CustomerId', type: 'text' },
    { name: 'EmployeeId', type: 'text' },
  ],
  Services: [
    { name: 'Name', type: 'text' },
    { name: 'NeedSupply', type: 'boolean' },
  ],
  Supply: [
    { name: 'Name', type: 'text' },
    { name: 'Barcode', type: 'text' },
    { name: 'Price', type: 'number' },
    { name: 'Stock', type: 'number' },
    { name: 'Max-Stock', type: 'number' },
    { name: 'Min-Stock', type: 'number' },
  ],
  "Supply Service": [
    { name: 'Quantity', type: 'number' },
    { name: 'Branch', type: 'number' },
    { name: 'Service', type: 'number' },
    { name: 'Supply', type: 'number' },
  ],
  "Work Order Detail": [
    { name: 'Branch', type: 'number' },
    { name: 'Service', type: 'number' },
    { name: 'DetailStatus', type: 'number' },
    { name: 'WorkOrder', type: 'number' },
    { name: 'EmployeeId', type: 'text' },
  ],
  "Work Detail Status": [
    { name: 'Name', type: 'text' },
  ],
  "Work Orders": [
    { name: 'OrderNumber', type: 'text' },
    { name: 'ServiceOrder', type: 'number' },
  ],
};

// Helper function to transform form data to API format
const transformFormDataToApiFormat = (entity: string, formData: { [key: string]: string }) => {
  switch (entity) {
    case 'Regions':
      return {
        name: formData.Name,
        country: {
          id: parseInt(formData.Country)
        }
      };
    case 'Approval Status':
    case 'Work Detail Status':
    case 'Phone Type':
    case 'Person Type':
    case 'Order Status':
    case 'Email Type':
    case 'Countries':
      return {
        name: formData.Name
      };
    case 'Cities':
      return {
        name: formData.Name,
        region: {
          id: parseInt(formData.Region)
        }
      };
    case 'Branches':
      return {
        name: formData.Name,
        nit: formData.Nit,
        city: {
          id: parseInt(formData.City)
        },
        company: {
          id: parseInt(formData.Company)
        }
      };
    case 'Companies':
      return {
        name: formData.Name,
        companyType: {
          id: parseInt(formData.CompanyType)
        }
      };
    case 'Person':
      return {
        id: formData.Id,
        name: formData.Name,
        lastname: formData.Lastname,
        username: formData.Username,
        password: formData.Password,
        role: formData.Role,
        branch: {
          id: parseInt(formData.Branch)
        },
        personType: {
          id: parseInt(formData.PersonType)
        }
      };
    case 'Email':
      return {
        mail: formData.Mail,
        emailType: {
          id: parseInt(formData.EmailType)
        },
        person: {
          id: formData.PersonId
        }
      };
    case 'Order Details':
      return {
        value: parseInt(formData.Value),
        branch: {
          id: parseInt(formData.Branch)
        },
        service: {
          id: parseInt(formData.Service)
        },
        serviceOrder: {
          id: parseInt(formData.ServiceOrder)
        }
      };
    case 'Person Supply':
      return {
        quantity: parseInt(formData.Quantity),
        supply: {
          id: parseInt(formData.Supply)
        },
        person: {
          id: formData.PersonId
        }
      };
    case 'Phone':
      return {
        number: formData.Number,
        phoneType: {
          id: parseInt(formData.PhoneType)
        },
        person: {
          id: formData.PersonId
        }
      };
    case 'Service Approval':
      return {
        report: formData.Report,
        solution: formData.Solution,
        approvalStatus: {
          id: parseInt(formData.ApprovalStatus)
        },
        branch: {
          id: parseInt(formData.Branch)
        },
        service: {
          id: parseInt(formData.Service)
        },
        workOrder: {
          id: parseInt(formData.WorkOrder)
        }
      };
    case 'Service Branches':
      return {
        value: parseInt(formData.Value),
        branch: {
          id: parseInt(formData.Branch)
        },
        service: {
          id: parseInt(formData.Service)
        }
      };
    case 'Service Order':
      return {
        orderStatus: {
          id: parseInt(formData.OrderStatus)
        },
        customerId: formData.CustomerId,
        employeeId: formData.EmployeeId
      };
    case 'Services':
      return {
        name: formData.Name,
        needSupply: formData.NeedSupply === 'true'
      };
    case 'Supply':
      return {
        name: formData.Name,
        barcode: formData.Barcode,
        price: parseFloat(formData.Price),
        stock: parseInt(formData.Stock),
        maxStock: parseInt(formData['Max-Stock']),
        minStock: parseInt(formData['Min-Stock'])
      };
    case 'Supply Service':
      return {
        quantity: parseInt(formData.Quantity),
        branch: {
          id: parseInt(formData.Branch)
        },
        service: {
          id: parseInt(formData.Service)
        },
        supply: {
          id: parseInt(formData.Supply)
        }
      };
    case 'Work Order Detail':
      return {
        branch: {
          id: parseInt(formData.Branch)
        },
        service: {
          id: parseInt(formData.Service)
        },
        detailStatus: {
          id: parseInt(formData.DetailStatus)
        },
        workOrder: {
          id: parseInt(formData.WorkOrder)
        },
        employeeId: formData.EmployeeId
      };
    case 'Work Orders':
      return {
        orderNumber: formData.OrderNumber,
        serviceOrder: {
          id: parseInt(formData.ServiceOrder)
        }
      };
    default:
      return formData;
  }
};

const EntityList: React.FC = () => {
  const { entity } = useParams<{ entity: string }>();
  const [searchTerm, setSearchTerm] = useState('');
  const [entities, setEntities] = useState<{
    [x: string]: any; id?: number; name?: string
  }[]>([]);
  const [isLoading, setIsLoading] = useState(false);
  const [error, setError] = useState<string | null>(null);
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [newEntityData, setNewEntityData] = useState<{ [key: string]: string }>({});
  const [submitError, setSubmitError] = useState<string | null>(null);

  // Limpiar los datos cuando cambia la entidad
  useEffect(() => {
    setEntities([]);
    setSearchTerm('');
    setError(null);
  }, [entity]);

  // Efecto separado para cargar los datos
  useEffect(() => {
    if (entity) {
      setIsLoading(true);

      axios.get(`http://localhost:8081/api/${entity}`)
        .then((response) => {
          console.log('Fetched Data:', response.data);
          // Asegurarse de que los datos son válidos antes de establecerlos
          const validData = Array.isArray(response.data) ? response.data : [];
          setEntities(validData);
        })
        .catch((error) => {
          setError(error.message || 'Failed to fetch data');
        })
        .finally(() => {
          setIsLoading(false);
        });
    }
  }, [entity]);

  // Función auxiliar para validar item
  const isValidItem = (item: any): boolean => {
    if (!item) return false;
    
    switch (entity) {
      case "Work Order Detail":
        return item.id !== undefined && item.id !== null;
      case "Supply Service":
        return item.serviceBranch?.branch && item.serviceBranch?.service && item.supply;
      case "Service Branches":
        return item.branch && item.service;
      case "Person Supply":
        return item.person && item.supply;
      default:
        return true;
    }
  };

  // Función auxiliar para generar una key única
  const generateKey = (item: any): string => {
    if (!item) return `empty-${Date.now()}`;

    try {
      switch (entity) {
        case "Work Order Detail":
          return item.id?.toString();
        case "Person Supply":
          return `${item.person?.id || 'na'}-${item.supply?.id || 'na'}`;
        case "Service Branches":
          return `${item.branch?.id || 'na'}-${item.service?.id || 'na'}`;
        case "Supply Service":
          return `${item.serviceBranch?.branch?.id || 'na'}-${item.serviceBranch?.service?.id || 'na'}-${item.supply?.id || 'na'}`;
        case "Service Approval":
          return `${item.id || 'na'}`;

        default:
          if (item.id !== undefined) {
            return item.id;
          }
          return `${entity?.toLowerCase().replace(/\s+/g, '-')}-${Date.now()}-${Math.random().toString(36).substr(2, 9)}`;
      }
    } catch (error) {
      console.error('Error generating key:', error);
      return `fallback-${Date.now()}-${Math.random().toString(36).substr(2, 9)}`;
    }
  };

  // Función auxiliar para generar el contenido
  const generateContent = (item: any): React.ReactNode => {
    if (!isValidItem(item)) {
      return <span>Invalid Item</span>;
    }

    try {
      switch (entity) {
        case "Work Order Detail":
          return (
            <div>
              {item.id !== undefined 
                ? (
                    <>
                      {`Order: ${item.workOrder.workOrderNum}`}<br />
                      {`Detail: ${item.id}` }
                    </>
                  )
                : 'Invalid Work Order Detail'}
            </div>
          );
        case "Company Type":
          return item.description || 'N/A';
        case "Email":
          return item.mail || 'N/A';
        case "Work Orders":
          return item.workOrderNum || 'N/A';
        case "Order Details":
        case "Person":
        case "Service Order":
          return `${item.id?.toString() || 'N/A'}`;
        case "Person Supply":
          return (
            <>
              Person: {item.person?.id || 'N/A'} <br />
              Supply: {item.supply?.barcode || 'N/A'}
            </>
          );
        case "Service Branches":
          return (
            <>
              Branch: {item.branch?.id || 'N/A'} <br />
              Service: {item.service?.id || 'N/A'}
            </>
          );
        case "Service Approval":
          return (
            <>
              Id: {item.id || 'N/A'} <br />
              WorkOrder: {item.workOrder?.id || 'N/A'}
            </>
          );
        case "Supply Service":
          return (
            <>
              Branch: {item.serviceBranch?.branch?.name || 'N/A'} <br/>
              Service: {item.serviceBranch?.service?.name || 'N/A'} <br/>
              Supply: {item.supply?.name || 'N/A'}
            </>
          );
        case "Phone":
          return (
            <>
              Person: {item.person?.id || 'N/A'} <br />
              {item.number || 'N/A'}
            </>
          );
        default:
          return item.name || 'N/A';
      }
    } catch (error) {
      console.error('Error generating content:', error);
      return <span>Error displaying content</span>;
    }
  };

  const filteredEntities = entities.filter(item => {
    if (!isValidItem(item)) return false;
    
    const searchTermLower = searchTerm.toLowerCase();
    switch (entity) {
      case "Work Order Detail":
        return (
          item.id?.toString().toLowerCase().includes(searchTermLower)||
          item.workOrder.workOrderNum.toString().toLowerCase().includes(searchTermLower)
        );
      case "Order Details":
      case "Person":
      case "Service Order":
        return item.id?.toString().toLowerCase().includes(searchTermLower);
      case "Email":
        return item.mail?.toLowerCase().includes(searchTermLower);
      case "Company Type":
        return item.description?.toLowerCase().includes(searchTermLower);
      case "Person Supply":
        return (
          item.person?.id?.toString().toLowerCase().includes(searchTermLower) ||
          item.supply?.barcode?.toLowerCase().includes(searchTermLower)
        );
      case "Supply":
        return (
          item.name?.toString().toLowerCase().includes(searchTermLower) ||
          item.barcode?.toLowerCase().includes(searchTermLower)
        );
      case "Supply Service":
        return (
          item.serviceBranch?.service?.name?.toString().toLowerCase().includes(searchTermLower) 
        );
      case "Service Branches":
        return (
          item.branch?.id?.toString().toLowerCase().includes(searchTermLower) ||
          item.service?.id?.toString().toLowerCase().includes(searchTermLower)
        );
      case "Service Approval":
        return item.workOrder?.id?.toString().toLowerCase().includes(searchTermLower);
      case "Phone":
        return item.number?.toLowerCase().includes(searchTermLower);
      case "Work Orders":
        return item.workOrderNum?.toLowerCase().includes(searchTermLower);
      default:
        return item.name?.toLowerCase().includes(searchTermLower);
    }
  });



  const handleInputChange = (e: React.ChangeEvent<HTMLInputElement | HTMLSelectElement>) => {
    setNewEntityData({
      ...newEntityData,
      [e.target.name]: e.target.value,
    });
  };

  const renderInput = (field: EntityField) => {
    switch (field.type) {
      case 'datetime':
        return (
          <Input
            key={field.name}
            type="datetime-local"
            name={field.name}
            placeholder={field.name.replace(/_/g, ' ')}
            value={newEntityData[field.name] || ''}
            onChange={handleInputChange}
          />
        );
      case 'number':
        return (
          <Input
            key={field.name}
            type="number"
            name={field.name}
            placeholder={field.name.replace(/_/g, ' ')}
            value={newEntityData[field.name] || ''}
            onChange={handleInputChange}
          />
        );
      case 'boolean':
        return (
          <select
            key={field.name}
            name={field.name}
            value={newEntityData[field.name] || ''}
            onChange={handleInputChange}
          >
            <option value="">Select...</option>
            <option value="true">Yes</option>
            <option value="false">No</option>
          </select>
        );
      case 'select':
        if (field.name === 'role') {
          return (
            <select
              key={field.name}
              name={field.name}
              value={newEntityData[field.name] || ''}
              onChange={handleInputChange}
            >
              <option value="">Select Role...</option>
              <option value="ADMIN">Admin</option>
              <option value="USER">User</option>
              <option value="EMPLOYEE">Employee</option>
            </select>
          );
        }
        return (
          <Input
            key={field.name}
            type="text"
            name={field.name}
            placeholder={field.name.replace(/_/g, ' ')}
            value={newEntityData[field.name] || ''}
            onChange={handleInputChange}
          />
        );
      default:
        return (
          <Input
            key={field.name}
            type="text"
            name={field.name}
            placeholder={field.name.replace(/_/g, ' ')}
            value={newEntityData[field.name] || ''}
            onChange={handleInputChange}
          />
        );
    }
  };

  const handleAddEntity = async () => {
    try {
      setSubmitError(null);
      const transformedData = transformFormDataToApiFormat(entity || '', newEntityData);
      
      const response = await axios.post(
        `http://localhost:8081/api/${entity}`,
        transformedData
      );

      if (response.status === 201 || response.status === 200) {
        // Refresh the entity list
        const updatedResponse = await axios.get(`http://localhost:8081/api/${entity}`);
        setEntities(updatedResponse.data);
        
        // Clear form and close modal
        setNewEntityData({});
        setIsModalOpen(false);
      }
    } catch (error) {
      setSubmitError(error.response?.data?.message || 'Failed to add entity');
      console.error('Error adding entity:', error);
    }
  };

  const closeModal = (e: React.MouseEvent<HTMLDivElement>) => {
    if (e.target === e.currentTarget) {
      setIsModalOpen(false);
    }
  };

  return (
    <div>
      <h2>{entity}</h2>
      <SearchBar
        type="text"
        placeholder="Search..."
        value={searchTerm}
        onChange={(e) => setSearchTerm(e.target.value)}
      />

      {isLoading && <p>Loading...</p>}
      {error && <p>{error}</p>}

      {!isLoading && !error && (
        <EntityGrid>
          {filteredEntities.map((item) => {
            const key = generateKey(item);
            const content = generateContent(item);
            
            return (
              <EntityCard key={key} to={`/${entity}/${key}`}>
                {content}
              </EntityCard>
            );
          })}
        </EntityGrid>
      )}

<AddButton onClick={() => setIsModalOpen(true)}>+</AddButton>

{isModalOpen && (
  <Modal onClick={closeModal}>
    <ModalContent>
      <h3>Add New {entity}</h3>
      {submitError && <ErrorMessage>{submitError}</ErrorMessage>}
      {entityFields[entity || '']?.map((field) => renderInput(field))}
      <Button onClick={handleAddEntity}>Add {entity}</Button>
    </ModalContent>
  </Modal>
)}
</div>
);
};

const ErrorMessage = styled.div`
color: red;
margin: 10px 0;
padding: 10px;
border: 1px solid red;
border-radius: 4px;
background-color: rgba(255, 0, 0, 0.1);
`;

export default EntityList;