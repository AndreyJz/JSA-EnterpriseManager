import React, { useState, useEffect } from 'react';
import { useParams, Link } from 'react-router-dom';
import styled from 'styled-components';

const EntityGrid = styled.div`
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(200px, 1fr));
  gap: 20px;
`;

const EntityCard = styled(Link)`
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100px;
  background-color: #1e88e5;
  color: white;
  text-decoration: none;
  font-size: 18px;
  border-radius: 4px;
  transition: background-color 0.3s;

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
`;

const Button = styled.button`
  padding: 10px;
  background-color: #4CAF50;
  color: white;
  border: none;
  cursor: pointer;
  width: 100%;

  &:hover {
    background-color: #45a049;
  }
`;

interface EntityField {
  name: string;
  type: string;
}

const entityFields: { [key: string]: EntityField[] } = {
  approval_status: [
    { name: 'id', type: 'number' },
    { name: 'name', type: 'text' },
  ],
  branches: [
    { name: 'id', type: 'number' },
    { name: 'name', type: 'text' },
    { name: 'nit', type: 'text' },
    { name: 'creation_date', type: 'datetime' },
    { name: 'city_id', type: 'number' },
    { name: 'company_id', type: 'number' },
  ],
  cities: [
    { name: 'id', type: 'number' },
    { name: 'name', type: 'text' },
    { name: 'region_id', type: 'number' },
  ],
  companies: [
    { name: 'id', type: 'number' },
    { name: 'name', type: 'text' },
    { name: 'company_type_id', type: 'number' },
  ],
  company_types: [
    { name: 'id', type: 'number' },
    { name: 'description', type: 'text' },
  ],
  countries: [
    { name: 'id', type: 'number' },
    { name: 'name', type: 'text' },
  ],
  email: [
    { name: 'id', type: 'number' },
    { name: 'mail', type: 'text' },
    { name: 'email_type_id', type: 'number' },
    { name: 'person_id', type: 'text' },
  ],
  email_type: [
    { name: 'id', type: 'number' },
    { name: 'name', type: 'text' },
  ],
  order_details: [
    { name: 'id', type: 'number' },
    { name: 'service_value', type: 'number' },
    { name: 'service_branch_branch_id', type: 'number' },
    { name: 'service_branch_service_id', type: 'number' },
    { name: 'service_order_id', type: 'number' },
  ],
  order_status: [
    { name: 'id', type: 'number' },
    { name: 'name', type: 'text' },
  ],
  person: [
    { name: 'id', type: 'text' },
    { name: 'name', type: 'text' },
    { name: 'lastname', type: 'text' },
    { name: 'password', type: 'text' },
    { name: 'date', type: 'datetime' },
    { name: 'username', type: 'text' },
    { name: 'role', type: 'select' },
    { name: 'branch_id', type: 'number' },
    { name: 'person_type_id', type: 'number' },
  ],
  person_supply: [
    { name: 'quantity', type: 'number' },
    { name: 'supply_id', type: 'number' },
    { name: 'person_id', type: 'text' },
  ],
  person_type: [
    { name: 'id', type: 'number' },
    { name: 'name', type: 'text' },
  ],
  phone: [
    { name: 'id', type: 'number' },
    { name: 'number', type: 'text' },
    { name: 'phone_type_id', type: 'number' },
    { name: 'person_id', type: 'text' },
  ],
  phone_type: [
    { name: 'id', type: 'number' },
    { name: 'name', type: 'text' },
  ],
  regions: [
    { name: 'id', type: 'number' },
    { name: 'name', type: 'text' },
    { name: 'country_id', type: 'number' },
  ],
  service_approval: [
    { name: 'id', type: 'number' },
    { name: 'report', type: 'text' },
    { name: 'solution', type: 'text' },
    { name: 'approval_status_id', type: 'number' },
    { name: 'service_branch_branch_id', type: 'number' },
    { name: 'service_branch_service_id', type: 'number' },
    { name: 'work_order_id', type: 'number' },
  ],
  service_branches: [
    { name: 'service_value', type: 'number' },
    { name: 'branch_id', type: 'number' },
    { name: 'service_id', type: 'number' },
  ],
  service_order: [
    { name: 'id', type: 'number' },
    { name: 'order_date', type: 'datetime' },
    { name: 'order_status_id', type: 'number' },
    { name: 'customer_id', type: 'text' },
    { name: 'employee_id', type: 'text' },
  ],
  services: [
    { name: 'id', type: 'number' },
    { name: 'name', type: 'text' },
    { name: 'requires_supply', type: 'boolean' },
  ],
  supply: [
    { name: 'id', type: 'number' },
    { name: 'name', type: 'text' },
    { name: 'barcode', type: 'text' },
    { name: 'price', type: 'number' },
    { name: 'stock', type: 'number' },
    { name: 'stock_max', type: 'number' },
    { name: 'stock_min', type: 'number' },
  ],
  supply_service: [
    { name: 'quantity', type: 'number' },
    { name: 'service_branches_branch_id', type: 'number' },
    { name: 'service_branches_service_id', type: 'number' },
    { name: 'supply_id', type: 'number' },
  ],
  work_order_detail: [
    { name: 'id', type: 'number' },
    { name: 'date', type: 'datetime' },
    { name: 'service_branch_branch_id', type: 'number' },
    { name: 'service_branch_service_id', type: 'number' },
    { name: 'work_order_detail_status_id', type: 'number' },
    { name: 'work_order_id', type: 'number' },
    { name: 'employee_id', type: 'text' },
  ],
  work_order_detail_status: [
    { name: 'id', type: 'number' },
    { name: 'name', type: 'text' },
  ],
  work_orders: [
    { name: 'id', type: 'number' },
    { name: 'work_order_num', type: 'text' },
    { name: 'assign_date', type: 'datetime' },
    { name: 'service_order_id', type: 'number' },
  ],
};

const EntityList: React.FC = () => {
  const { entity } = useParams<{ entity: string }>();
  const [searchTerm, setSearchTerm] = useState('');
  const [entities, setEntities] = useState<{ id: number; name: string }[]>([]);
  const [isLoading, setIsLoading] = useState(false);
  const [error, setError] = useState<string | null>(null);
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [newEntityData, setNewEntityData] = useState<{ [key: string]: string }>({});

  useEffect(() => {
    if (entity) {
      setIsLoading(true);
      setError(null);
      
      fetch(`http://localhost:8081/api/${entity}`)
        .then((response) => {
          if (!response.ok) {
            throw new Error('Failed to fetch data');
          }
          return response.json();
        })
        .then((data) => {
          setEntities(data);
          setIsLoading(false);
        })
        .catch((error) => {
          setError(error.message);
          setIsLoading(false);
        });
    }
  }, [entity]);

  const filteredEntities = entities.filter(item =>
    item.name.toLowerCase().includes(searchTerm.toLowerCase())
  );

  const handleAddEntity = () => {
    // Aquí se enviaría una solicitud para agregar una nueva entidad
    console.log(`Adding new ${entity}:`, newEntityData);
    setNewEntityData({});
    setIsModalOpen(false);
  };

  const handleInputChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setNewEntityData({
      ...newEntityData,
      [e.target.name]: e.target.value,
    });
  };

  const closeModal = (e: React.MouseEvent<HTMLDivElement>) => {
    if (e.target === e.currentTarget) {
      setIsModalOpen(false);
    }
  };

  return (
    <div>
      <h2>{entity?.replace('_', ' ')}</h2>
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
          {filteredEntities.map((item) => (
            <EntityCard key={item.id} to={`/${entity}/${item.id}`}>
              {item.name}
            </EntityCard>
          ))}
        </EntityGrid>
      )}

      <AddButton onClick={() => setIsModalOpen(true)}>+</AddButton>
      
      {isModalOpen && (
        <Modal onClick={closeModal}>
          <ModalContent>
            <h3>Add New {entity?.replace('_', ' ').slice(0, -1)}</h3>
            {entityFields[entity || '']?.map((field) => (
              <Input
                key={field.name}
                type={field.type}
                name={field.name}
                placeholder={field.name.replace('_', ' ')}
                value={newEntityData[field.name] || ''}
                onChange={handleInputChange}
              />
            ))}
            <Button onClick={handleAddEntity}>Add {entity?.replace('_', ' ').slice(0, -1)}</Button>
          </ModalContent>
        </Modal>
      )}
    </div>
  );
};

export default EntityList;
