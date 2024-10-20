import React from 'react';
import { Link } from 'react-router-dom';
import styled from 'styled-components';

const SidebarContainer = styled.nav`
  top: 0;
  padding: 0;
  width: 5em;
  height: 100vh;
  position: fixed;
  background-color: hsl(256, 12%, 12%);
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
  padding: 0 0.5em;
  width: 100%;
  cursor: pointer;
`;

const SidebarLink = styled(Link)`
  display: flex;
  align-items: center;
  padding: 1em 0;
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
  width: calc(5rem - 1em - 8px);
  display: flex;
  justify-content: center;
  align-items: center;
`;

const LinkText = styled.span`
  margin-left: 1em;
  opacity: 0;
  transition: opacity 0.35s cubic-bezier(0.175, 0.685, 0.32, 1);

  ${SidebarContainer}:hover & {
    opacity: 1;
  }
`;

const entities = [
  'approval_status', 'branches', 'cities', 'companies', 'company_types',
  'countries', 'email', 'email_type', 'order_details', 'order_status',
  'person', 'person_supply', 'person_type', 'phone', 'phone_type',
  'regions', 'service_approval', 'service_branches', 'service_order',
  'services', 'supply', 'supply_service', 'work_order_detail',
  'work_order_detail_status', 'work_orders'
];

const Sidebar: React.FC = () => {
  return (
    <SidebarContainer>
      <SidebarList>
        {entities.map((entity) => (
          <SidebarItem key={entity}>
            <SidebarLink to={`/${entity}`}>
              <IconWrapper>
                <ion-icon name="folder-outline"></ion-icon>
              </IconWrapper>
              <LinkText>{entity.replace('_', ' ')}</LinkText>
            </SidebarLink>
          </SidebarItem>
        ))}
      </SidebarList>
    </SidebarContainer>
  );
};

export default Sidebar;