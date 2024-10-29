import React, {useEffect, useState} from 'react';
import styled from 'styled-components';
import { Link } from 'react-router-dom';
import {UserUpdate} from "../../types";

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
    const [user, setUser] = useState<UserUpdate | null>(null);

    useEffect(() => {
        const fetchUserProfile = async () => {
            const token = localStorage.getItem('token');
            try {
                const response = await fetch('http://localhost:8081/auth/profile', {
                    method: 'GET',
                    headers: {
                        'Content-Type': 'application/json',
                        'Authorization': `Bearer ${token}`,
                    },
                });
                if (response.ok) {
                    const userProfile = await response.json();
                    setUser(userProfile);
                } else {
                    console.error('Error fetching profile:', response.statusText);
                }
            } catch (error) {
                console.error('Error during fetch:', error);
            }
        };

        fetchUserProfile();
    }, []);

  return (
    <NavbarContainer>
      <Logo>{user?.role.name} Dashboard</Logo>
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