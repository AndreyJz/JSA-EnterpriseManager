import React, { useState } from 'react';
import { Link, useNavigate } from 'react-router-dom'; // Importa useNavigate
import { UserUpdate } from '../types';
import { User } from 'lucide-react';

function Login() {
  const [formData, setFormData] = useState({
    username: '',
    password: '',
  });
  const [userInfo, setUser] = useState<UserUpdate | null>(null);
  const navigate = useNavigate(); 

  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { name, value } = e.target;
    setFormData(prevState => ({
      ...prevState,
      [name]: value,
    }));
  };

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
  
    try {
      // POST para el log in
      const response = await fetch('http://localhost:8081/auth/login', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({
          username: formData.username,
          password: formData.password,
        }),
      });
  
      // Si el login es exitoso
      if (response.ok) {
        const data = await response.json();
        console.log('Login successful:', data);
        localStorage.setItem("token", data.jwt);
        const token = localStorage.getItem('token');
      
        try {
          const profileResponse = await fetch('http://localhost:8081/auth/profile', {
            method: 'GET',
            headers: {
              'Content-Type': 'application/json',
              'Authorization': `Bearer ${token}`
            }
          });
          if (profileResponse.ok) {
            const userProfile = await profileResponse.json();
            console.log('User Profile:', userProfile.id);
            setUser(userProfile);
            
            // Verifica el rol y redirige si es necesario
            
            if (userProfile.role.id === 1) {
              navigate('/customer', { replace: true }); 
            }else {
              navigate('/admin', { replace: true });
            }
          } else {
            console.error('Error fetching profile:', profileResponse.statusText);
          }
        } catch (error) {
          console.error('Error during profile fetch:', error);
        }
      } else {
        console.log('Login failed');
        alert('Email or password is incorrect');
      }
    } catch (error) {
      console.error('There was an error with the login request:', error);
      alert('Something went wrong. Please try again later.');
    }
  };

  return (
    <div className="bg-gray-100 py-16">
      <div className="container mx-auto px-4">
        <div className="max-w-md mx-auto bg-white p-8 rounded-lg shadow-md">
          <h2 className="text-3xl font-bold text-center mb-6">Login</h2>
          <form onSubmit={handleSubmit}>
            <div className="mb-4">
              <label htmlFor="username" className="block text-gray-700 font-bold mb-2">User</label>
              <input
                type="username"
                id="username"
                name="username"
                value={formData.username}
                onChange={handleChange}
                className="w-full px-3 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
                required
              />
            </div>
            <div className="mb-6">
              <label htmlFor="password" className="block text-gray-700 font-bold mb-2">Password</label>
              <input
                type="password"
                id="password"
                name="password"
                value={formData.password}
                onChange={handleChange}
                className="w-full px-3 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
                required
              />
            </div>
            <button
              type="submit"
              className="w-full bg-black text-white px-4 py-2 rounded-md hover:bg-blue-700 transition duration-300"
            >
              Log In
            </button>
          </form>
          <p className="mt-4 text-center">
            Don't have an account? <Link to="/signup" className="text-blue-600 hover:underline">Sign Up</Link>
          </p>
        </div>
      </div>
    </div>
  );
}

export default Login;
