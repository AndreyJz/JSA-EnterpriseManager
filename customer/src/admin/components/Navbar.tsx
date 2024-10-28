import React from 'react';
import styled from 'styled-components';
import { Link } from 'react-router-dom';

const NavbarContainer = styled.nav`
  background-color: #111111;
  color: white;
  padding: 10px 6em;

  display: flex;
  justify-content: space-between;
  align-items: center;
`;

const Logo = styled.div`
  font-size: 1.5em;
  font-weight: bold;
`;

const NavItems = styled.div`
  display: flex;
  align-items: center;
`;

const NavLink = styled(Link)`
  color: white;
  text-decoration: none;
  margin-left: 20px;
  &:hover {
    text-decoration: underline;
  }
`;

const UserIcon = styled.div`
  cursor: pointer;
  margin-left: 20px;
`;

const Navbar: React.FC = () => {
  return (
    <NavbarContainer>
      <Logo>Admin Dashboard</Logo>
      <NavItems>
        <NavLink to="/admin/tasks">Tasks</NavLink>
        <UserIcon onClick={() => console.log('Logout')}>
          <ion-icon name="person-circle-outline" size="large"></ion-icon>
        </UserIcon>
      </NavItems>
    </NavbarContainer>
  );
};

export default Navbar;