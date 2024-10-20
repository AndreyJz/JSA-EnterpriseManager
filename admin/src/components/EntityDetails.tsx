import React from 'react';
import { useParams } from 'react-router-dom';
import styled from 'styled-components';

const DetailsContainer = styled.div`
  background-color: #f5f5f5;
  padding: 20px;
  border-radius: 4px;
`;

const AttributeRow = styled.div`
  display: flex;
  margin-bottom: 10px;
`;

const AttributeLabel = styled.div`
  font-weight: bold;
  width: 150px;
`;

const AttributeValue = styled.div`
  flex-grow: 1;
`;

const Button = styled.button`
  margin-right: 10px;
  padding: 10px 20px;
  background-color: #1e88e5;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  transition: background-color 0.3s;

  &:hover {
    background-color: #1565c0;
  }
`;

const EntityDetails: React.FC = () => {
  const { entity, id } = useParams<{ entity: string; id: string }>();

  // This is a mock function. In a real application, you would fetch data from your API.
  const getEntityDetails = (entityName: string, entityId: string) => {
    return {
      id: entityId,
      name: `${entityName} ${entityId}`,
      attribute1: 'Value 1',
      attribute2: 'Value 2',
      attribute3: 'Value 3',
      attribute4: 'Value 4',
    };
  };

  const details = getEntityDetails(entity || '', id || '');

  const handleUpdate = () => {
    // Implement update logic
    console.log('Update');
  };

  const handleDelete = () => {
    // Implement delete logic
    console.log('Delete');
  };

  return (
    <DetailsContainer>
      <h2>{entity?.replace('_', ' ')} Details</h2>
      {Object.entries(details).map(([key, value]) => (
        <AttributeRow key={key}>
          <AttributeLabel>{key}:</AttributeLabel>
          <AttributeValue>{value}</AttributeValue>
        </AttributeRow>
      ))}
      <div>
        <Button onClick={handleUpdate}>UPDATE</Button>
        <Button onClick={handleDelete}>DELETE</Button>
      </div>
    </DetailsContainer>
  );
};

export default EntityDetails;