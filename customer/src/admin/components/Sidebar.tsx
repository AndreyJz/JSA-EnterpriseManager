import React from 'react';
import { Link } from 'react-router-dom';
import styled from 'styled-components';

const SidebarContainer = styled.nav`
  top: 0;
  padding-bottom: 3em;
  width: 3.3em;
  height: 100vh;
  position: fixed;
  background-color: #111111;
  transition: width 0.35s cubic-bezier(0.175, 0.685, 0.32, 1);
  overflow-y: auto;
  overflow-x: hidden;

  &:hover {
    width: 16em;
  }
`;

const SidebarList = styled.ul`
  margin: 0;
  padding: 0;
  list-style-type: none;
`;

const SidebarItem = styled.li`
  padding: 0 0.1em;
  width: 100%;
  cursor: pointer;
  
`;

const SidebarLink = styled(Link)`
  display: flex;
  align-items: center;
  padding: 2em 0em 0em 0.3em;
  width: 100%;
  color: hsl(0, 0%, 50%);
  text-decoration: none;
  transition: all 0.2s cubic-bezier(0.175, 0.685, 0.32, 1);

  &:hover {
    color: hsl(0, 0%, 100%);
    background: hsl(257, 11%, 16%);
  }
`;

const IconWrapper = styled.div`
  width: clamp(2em,2vw,5em);
  display: flex;
  justify-content: center;
  align-items: center;
`;

const LinkText = styled.span`
  margin-left: 1em;
  opacity: 0;
  transition: opacity 0.35s cubic-bezier(0.175, 0.685, 0.32, 1);
  display:none;

  ${SidebarContainer}:hover & {
    opacity: 1;
    display:block;
  }
`;

const entities = [
  'Approval_Status', 'Branches', 'Cities', 'Companies', 'Company_Type',
  'Countries', 'Email', 'Email_Type', 'Order_Details', 'Order_Status',
  'Person', 'Person_Supply', 'Person_Type', 'Phone', 'Phone_Type',
  'Regions', 'Service_Approval', 'Service_Branches', 'Service_Order',
  'Services', 'Supply', 'Supply_Service','Work_Orders', 'Work_Order_Detail',
  'Work_Detail_Status'
];

const Sidebar: React.FC = () => {
  return (
    <SidebarContainer>
      <SidebarList>
        {entities.map((entity) => ( 
          <SidebarItem key={entity}>
            <SidebarLink to={`/admin/${entity}`}>
              <IconWrapper>
                <ion-icon name="folder-outline"></ion-icon>
              </IconWrapper>
              <LinkText>{(entity.replace('_', ' ')).replace('_',' ')}</LinkText>
            </SidebarLink>
          </SidebarItem>
        ))}
      </SidebarList>
    </SidebarContainer>
  );
};

export default Sidebar;