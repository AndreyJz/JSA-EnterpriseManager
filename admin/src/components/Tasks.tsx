import React, { useState } from 'react';
import styled from 'styled-components';

const TasksContainer = styled.div`
  margin-top: 20px;
  max-height: calc(100vh - 200px);
  overflow-y: auto;
  padding-right: 10px;

  &::-webkit-scrollbar {
    width: 8px;
  }

  &::-webkit-scrollbar-track {
    background: transparent;
  }

  &::-webkit-scrollbar-thumb {
    background-color: rgba(0, 0, 0, 0.2);
    border-radius: 4px;
  }

  &::-webkit-scrollbar-thumb:hover {
    background-color: rgba(0, 0, 0, 0.3);
  }
`;

const TaskList = styled.ul`
  list-style-type: none;
  padding: 0;
`;

const TaskItem = styled.li`
  background-color: #f0f0f0;
  border-radius: 4px;
  margin-bottom: 10px;
  overflow: hidden;
`;

const TaskHeader = styled.div`
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 10px;
  cursor: pointer;
`;

const TaskName = styled.span`
  font-weight: bold;
`;

const StatusIndicator = styled.span<{ status: 'completed' | 'in-progress' | 'not-started' }>`
  width: 20px;
  height: 20px;
  border-radius: 50%;
  background-color: ${props => 
    props.status === 'completed' ? '#4CAF50' :
    props.status === 'in-progress' ? '#FFC107' :
    '#F44336'
  };
  cursor: pointer;
`;

const TaskDetails = styled.div`
  padding: 10px;
  background-color: #e0e0e0;
`;

const DetailItem = styled.p`
  margin: 5px 0;
`;

interface Task {
  id: number;
  name: string;
  status: 'completed' | 'in-progress' | 'not-started';
  startDate: string;
  dueDate: string;
  client: string;
  value: number;
  employee: string;
}

const Tasks: React.FC = () => {
  const [tasks, setTasks] = useState<Task[]>([
    { id: 1, name: 'Task 1', status: 'completed', startDate: '2023-05-01', dueDate: '2023-05-15', client: 'Client A', value: 1000, employee: 'John Doe' },
    { id: 2, name: 'Task 2', status: 'in-progress', startDate: '2023-05-05', dueDate: '2023-05-20', client: 'Client B', value: 1500, employee: 'Jane Smith' },
    { id: 3, name: 'Task 3', status: 'not-started', startDate: '2023-05-10', dueDate: '2023-05-25', client: 'Client C', value: 2000, employee: 'Bob Johnson' },
    { id: 4, name: 'Task 4', status: 'completed', startDate: '2023-05-15', dueDate: '2023-05-30', client: 'Client D', value: 2500, employee: 'Alice Brown' },
  ]);

  const [openTaskId, setOpenTaskId] = useState<number | null>(null);

  const toggleTaskDetails = (taskId: number) => {
    setOpenTaskId(openTaskId === taskId ? null : taskId);
  };

  const handleStatusChange = (taskId: number) => {
    const statusOrder: Task['status'][] = ['not-started', 'in-progress', 'completed'];
    setTasks(tasks.map(task => {
      if (task.id === taskId) {
        const currentIndex = statusOrder.indexOf(task.status);
        const nextStatus = statusOrder[(currentIndex + 1) % statusOrder.length];
        return { ...task, status: nextStatus };
      }
      return task;
    }));
  };

  return (
    <TasksContainer>
      <h2>Tasks</h2>
      <TaskList>
        {tasks.map(task => (
          <TaskItem key={task.id}>
            <TaskHeader onClick={() => toggleTaskDetails(task.id)}>
              <TaskName>{task.name}</TaskName>
              <StatusIndicator 
                status={task.status} 
                onClick={(e) => {
                  e.stopPropagation();
                  handleStatusChange(task.id);
                }}
              />
            </TaskHeader>
            {openTaskId === task.id && (
              <TaskDetails>
                <DetailItem><strong>Start Date:</strong> {task.startDate}</DetailItem>
                <DetailItem><strong>Due Date:</strong> {task.dueDate}</DetailItem>
                <DetailItem><strong>Client:</strong> {task.client}</DetailItem>
                <DetailItem><strong>Value:</strong> ${task.value}</DetailItem>
                <DetailItem><strong>Employee:</strong> {task.employee}</DetailItem>
              </TaskDetails>
            )}
          </TaskItem>
        ))}
      </TaskList>
    </TasksContainer>
  );
};

export default Tasks;