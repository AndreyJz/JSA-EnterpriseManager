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

const Select = styled.select`
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

// Interfaces
interface EntityOption {
  id: number;
  name?: string;
  description?: string; // Para casos como Company_Type
}

interface SelectOptions {
  [key: string]: EntityOption[];
}

interface EntityField {
  name: string;
  type: string;
}

const entityFields: { [key: string]: EntityField[] } = {
  "Approval_Status": [
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
  "Company_Type": [
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
  "Email_Type": [
    { name: 'Name', type: 'text' },
  ],
  "Order_Details": [
    { name: 'Value', type: 'number' },
    { name: 'Branch', type: 'number' },
    { name: 'Service', type: 'number' },
    { name: 'ServiceOrder', type: 'number' },
  ],
  "Order_Status": [
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
  "Person_Supply": [
    { name: 'Quantity', type: 'number' },
    { name: 'Supply', type: 'number' },
    { name: 'PersonId', type: 'text' },
  ],
  "Person_Type": [
    { name: 'Name', type: 'text' },
  ],
  Phone: [
    { name: 'Number', type: 'text' },
    { name: 'PhoneType', type: 'number' },
    { name: 'PersonId', type: 'text' },
  ],
  "Phone_Type": [
    { name: 'Name', type: 'text' },
  ],
  Regions: [
    { name: 'Name', type: 'text' },
    { name: 'Country', type: 'number' },
  ],
  "Service_Approval": [
    { name: 'Report', type: 'text' },
    { name: 'Solution', type: 'text' },
    { name: 'ApprovalStatus', type: 'number' },
    { name: 'Branch', type: 'number' },
    { name: 'Service', type: 'number' },
    { name: 'WorkOrder', type: 'number' },
  ],
  "Service_Branches": [
    { name: 'Value', type: 'number' },
    { name: 'Branch', type: 'number' },
    { name: 'Service', type: 'number' },
  ],
  "Service_Order": [
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
  "Supply_Service": [
    { name: 'Quantity', type: 'number' },
    { name: 'Branch', type: 'number' },
    { name: 'Service', type: 'number' },
    { name: 'Supply', type: 'number' },
  ],
  "Work_Order_Detail": [
    { name: 'Branch', type: 'number' },
    { name: 'Service', type: 'number' },
    { name: 'DetailStatus', type: 'number' },
    { name: 'WorkOrder', type: 'number' },
    { name: 'EmployeeId', type: 'text' },
  ],
  "Work_Detail_Status": [
    { name: 'Name', type: 'text' },
  ],
  "Work_Orders": [
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
    case 'Approval_Status':
    case 'Work_Detail_Status':
    case 'Phone_Type':
    case 'Person_Type':
    case 'Order_Status':
    case 'Email_Type':
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
      console.log(formData)
      return {
        id: formData.Id,
        name: formData.Name,
        lastname: formData.Lastname,
        username: formData.Username,
        password: formData.Password,
        repeatedPassword: formData.Password,
        role: {
          id: parseInt(formData.Role)
        } ,
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
    case 'Order_Details':
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
    case 'Person_Supply':
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
    case 'Service_Approval':
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
    case 'Service_Branches':
      return {
        value: parseInt(formData.Value),
        branch: {
          id: parseInt(formData.Branch)
        },
        service: {
          id: parseInt(formData.Service)
        }
      };
    case 'Service_Order':
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
    case 'Supply_Service':
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
    case 'Work_Order_Detail':
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
    case 'Work_Orders':
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

// Configuración de los campos que requieren selects dinámicos
const dynamicSelectConfig: { [key: string]: { endpoint: string; valueField: string } } = {
  Role: { endpoint: 'Roles', valueField: 'name' },
  Country: { endpoint: 'Countries', valueField: 'name' },
  Region: { endpoint: 'Regions', valueField: 'name' },
  City: { endpoint: 'Cities', valueField: 'name' },
  Branch: { endpoint: 'Branches', valueField: 'name' },
  Company: { endpoint: 'Companies', valueField: 'name' },
  CompanyType: { endpoint: 'Company_Type', valueField: 'description' },
  PersonType: { endpoint: 'Person_Type', valueField: 'name' },
  EmailType: { endpoint: 'Email_Type', valueField: 'name' },
  PhoneType: { endpoint: 'Phone_Type', valueField: 'name' },
  DetailStatus: { endpoint: 'Work_Detail_Status', valueField: 'name' },
  OrderStatus: { endpoint: 'Order_Status', valueField: 'name' },
  ApprovalStatus: { endpoint: 'Approval_Status', valueField: 'name' },
  Service: { endpoint: 'Services', valueField: 'name' },
  Supply: { endpoint: 'Supply', valueField: 'name' }
};

const EntityList: React.FC = () => {
  const { entity } = useParams<{ entity: string }>();
  const [searchTerm, setSearchTerm] = useState('');
  const [entities, setEntities] = useState<{
    [x: string]: any; id?: number; name?: string
  }[]>([]);
  const [selectOptions, setSelectOptions] = useState<SelectOptions>({});
  const [isLoading, setIsLoading] = useState(false);
  const [error, setError] = useState<string | null>(null);
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [newEntityData, setNewEntityData] = useState<{ [key: string]: string }>({});
  const [submitError, setSubmitError] = useState<string | null>(null);
  const [loadingOptions, setLoadingOptions] = useState<boolean>(false);

  // Efecto para limpiar datos cuando cambia la entidad
  useEffect(() => {
    setEntities([]);
    setSearchTerm('');
    setError(null);
    setSelectOptions({});
    setNewEntityData({});
  }, [entity]);

  // Efecto para cargar los datos de la entidad principal
  useEffect(() => {
    if (entity) {
      setIsLoading(true);
      axios.get(`http://localhost:8081/api/${entity}`)
        .then((response) => {
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

  // Efecto para cargar opciones de selects cuando se abre el modal
  useEffect(() => {
    if (isModalOpen && entity) {
      loadRequiredSelectOptions();
    }
  }, [isModalOpen, entity]);

  // Función para cargar las opciones necesarias según los campos de la entidad
  const loadRequiredSelectOptions = async () => {
    if (!entity || !entityFields[entity]) return;

    setLoadingOptions(true);
    const fieldsToLoad = entityFields[entity]
      .filter(field => field.type === 'select' || field.type === 'number')
      .map(field => field.name);

    const loadPromises = fieldsToLoad.map(async fieldName => {
      const config = dynamicSelectConfig[fieldName];
      if (config) {
        try {
          const response = await axios.get(`http://localhost:8081/api/${config.endpoint}`);
          return { fieldName, options: response.data };
        } catch (error) {
          console.error(`Error loading options for ${fieldName}:`, error);
          return { fieldName, options: [] };
        }
      }
      return null;
    });

    const results = await Promise.all(loadPromises);
    const newOptions: SelectOptions = {};
    results.forEach(result => {
      if (result) {
        newOptions[result.fieldName] = result.options;
      }
    });

    setSelectOptions(newOptions);
    setLoadingOptions(false);
  };

    // Función auxiliar para obtener el ID antes del punto
    const getIdBeforeDot = (value: string): string => {
      return value.split('.')[0];
    };

  // Función auxiliar para validar item
  const isValidItem = (item: any): boolean => {
    if (!item) return false;
    
    switch (entity) {
      case "Work_Order_Detail":
        return item.id !== undefined && item.id !== null;
      case "Supply_Service":
        return item.serviceBranch?.branch && item.serviceBranch?.service && item.supply;
      case "Service_Branches":
        return item.branch && item.service;
      case "Person_Supply":
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
        case "Work_Order_Detail":
          return item.id?.toString();
        case "Person_Supply":
          return `${item.person?.id || 'na'}-${item.supply?.id || 'na'}`;
        case "Service_Branches":
          return `${item.branch?.id || 'na'}-${item.service?.id || 'na'}`;
        case "Supply_Service":
          return `${item.serviceBranch?.branch?.id || 'na'}-${item.serviceBranch?.service?.id || 'na'}-${item.supply?.id || 'na'}`;
        case "Service_Approval":
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
        case "Work_Order_Detail":
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
        case "Company_Type":
          return item.description || 'N/A';
        case "Email":
          return item.mail || 'N/A';
        case "Work_Orders":
          return item.workOrderNum || 'N/A';
        case "Order_Details":
        case "Person":
        case "Service_Order":
          return `${item.id?.toString() || 'N/A'}`;
        case "Person_Supply":
          return (
            <>
              Person: {item.person?.id || 'N/A'} <br />
              Supply: {item.supply?.barcode || 'N/A'}
            </>
          );
        case "Service_Branches":
          return (
            <>
              Branch: {item.branch?.id || 'N/A'} <br />
              Service: {item.service?.id || 'N/A'}
            </>
          );
        case "Service_Approval":
          return (
            <>
              Id: {item.id || 'N/A'} <br />
              WorkOrder: {item.workOrder?.id || 'N/A'}
            </>
          );
        case "Supply_Service":
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
      case "Work_Order_Detail":
        return (
          item.id?.toString().toLowerCase().includes(searchTermLower)||
          item.workOrder.workOrderNum.toString().toLowerCase().includes(searchTermLower)
        );
      case "Order_Details":
      case "Person":
      case "Service_Order":
        return item.id?.toString().toLowerCase().includes(searchTermLower);
      case "Email":
        return item.mail?.toLowerCase().includes(searchTermLower);
      case "Company_Type":
        return item.description?.toLowerCase().includes(searchTermLower);
      case "Person_Supply":
        return (
          item.person?.id?.toString().toLowerCase().includes(searchTermLower) ||
          item.supply?.barcode?.toLowerCase().includes(searchTermLower)
        );
      case "Supply":
        return (
          item.name?.toString().toLowerCase().includes(searchTermLower) ||
          item.barcode?.toLowerCase().includes(searchTermLower)
        );
      case "Supply_Service":
        return (
          item.serviceBranch?.service?.name?.toString().toLowerCase().includes(searchTermLower) 
        );
      case "Service_Branches":
        return (
          item.branch?.id?.toString().toLowerCase().includes(searchTermLower) ||
          item.service?.id?.toString().toLowerCase().includes(searchTermLower)
        );
      case "Service_Approval":
        return item.workOrder?.id?.toString().toLowerCase().includes(searchTermLower);
      case "Phone":
        return item.number?.toLowerCase().includes(searchTermLower);
      case "Work_Orders":
        return item.workOrderNum?.toLowerCase().includes(searchTermLower);
      default:
        return item.name?.toLowerCase().includes(searchTermLower);
    }
  });



  const handleInputChange = (e: React.ChangeEvent<HTMLInputElement | HTMLSelectElement>) => {
    const fieldName = e.target.name;
    const value = dynamicSelectConfig[fieldName] ? 
      getIdBeforeDot(e.target.value) : 
      e.target.value;

    setNewEntityData({
      ...newEntityData,
      [fieldName]: value,
    });
  };

  const renderInput = (field: EntityField) => {
    const config = dynamicSelectConfig[field.name];
    
    if (config && (field.type === 'select' || field.type === 'number')) {
      const options = selectOptions[field.name] || [];
      const currentValue = newEntityData[field.name];
      const displayValue = currentValue ? 
        `${currentValue}.${options.find(opt => opt.id.toString() === currentValue)?.[config.valueField] || ''}` : 
        '';

      return (
        <Select
          key={field.name}
          name={field.name}
          value={displayValue}
          onChange={handleInputChange}
        >
          <option value="">Select {field.name}...</option>
          {options.map(option => (
            <option 
              key={option.id} 
              value={`${option.id}.${option[config.valueField]}`}
            >
              {option[config.valueField]}
            </option>
          ))}
        </Select>
      );
    }

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
          <Select
            key={field.name}
            name={field.name}
            value={newEntityData[field.name] || ''}
            onChange={handleInputChange}
          >
            <option value="">Select...</option>
            <option value="true">Yes</option>
            <option value="false">No</option>
          </Select>
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
        const updatedResponse = await axios.get(`http://localhost:8081/api/${entity}`);
        setEntities(updatedResponse.data);
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
      <h2>{(entity.replace('_', ' ')).replace('_',' ')}</h2>
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
    <Modal onClick={(e) => e.target === e.currentTarget && setIsModalOpen(false)}>
      <ModalContent>
        <h3>Add New {(entity?.replace('_', ' ')).replace('_',' ')}</h3>
        {submitError && <ErrorMessage>{submitError}</ErrorMessage>}
        {loadingOptions ? (
          <p>Loading form options...</p>
        ) : (
          entityFields[entity || '']?.map((field) => renderInput(field))
        )}
        <Button onClick={handleAddEntity}>
          Add {(entity?.replace('_', ' ')).replace('_',' ')}
        </Button>
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