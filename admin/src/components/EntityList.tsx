import React, { useState } from 'react';
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
  cities: [
    { name: 'name', type: 'text' },
    { name: 'region_id', type: 'number' },
  ],
  countries: [
    { name: 'name', type: 'text' },
  ],
  // Add more entities and their fields here
};

const EntityList: React.FC = () => {
  const { entity } = useParams<{ entity: string }>();
  const [searchTerm, setSearchTerm] = useState('');
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [newEntityData, setNewEntityData] = useState<{ [key: string]: string }>({});

  // This is a mock function. In a real application, you would fetch data from your API.
  const getEntities = (entityName: string) => {
    return Array.from({ length: 10 }, (_, i) => ({
      id: i + 1,
      name: `${entityName} ${i + 1}`,
    }));
  };

  const entities = getEntities(entity || '');
  const filteredEntities = entities.filter(item =>
    item.name.toLowerCase().includes(searchTerm.toLowerCase())
  );

  const handleAddEntity = () => {
    // Here you would typically send a request to your backend to add a new entity
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
      <EntityGrid>
        {filteredEntities.map((item) => (
          <EntityCard key={item.id} to={`/${entity}/${item.id}`}>
            {item.name}
          </EntityCard>
        ))}
      </EntityGrid>
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