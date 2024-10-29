import React, { useState, useEffect, useRef } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import { User, LogOut, Settings, Layout } from 'lucide-react';

const Navbar = () => {
  const [isOpen, setIsOpen] = useState(false);
  const navigate = useNavigate();
  const menuRef = useRef(null);

  useEffect(() => {
    const handleClickOutside = (event) => {
      if (menuRef.current && !menuRef.current.contains(event.target)) {
        setIsOpen(false);
      }
    };

    document.addEventListener('mousedown', handleClickOutside);
    return () => {
      document.removeEventListener('mousedown', handleClickOutside);
    };
  }, []);

  const handleLogout = async () => {
    const token = localStorage.getItem('token');
    try {
      const response = await fetch('http://localhost:8081/auth/logout', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
          'Authorization': `Bearer ${token}`,
        },
      });
      if (response.ok) {
        localStorage.removeItem('token');
        navigate('/login', { replace: true });
      } else {
        console.error('Error in logout:', response.statusText);
      }
    } catch (error) {
      console.error('Error during logout:', error);
    }
  };

  return (
    <nav style={{backgroundColor: '#111111'}} className="text-white px-24 py-3 flex justify-between items-center relative">
      <Link 
        to="/admin" 
        className="text-2xl font-bold hover:text-gray-300 transition-colors"
      >
        Admin Dashboard
      </Link>
      


        <div className="relative" ref={menuRef}>
          <div 
            className="cursor-pointer hover:text-gray-300 transition-colors p-2"
            onClick={() => setIsOpen(!isOpen)}
          >
            <User size={24} />
          </div>

          {isOpen && (
            <div className="absolute right-0 mt-2 w-48 rounded-md shadow-lg bg-white text-gray-800">
              <div className="py-1">
                <Link
                  to="/"
                  className="block px-4 py-2 hover:bg-gray-100 flex items-center gap-2"
                  onClick={() => setIsOpen(false)}
                >
                  <Settings size={16} />
                  Customer View
                </Link>
                <button
                  onClick={() => {
                    handleLogout();
                    setIsOpen(false);
                  }}
                  className="w-full text-left px-4 py-2 hover:bg-gray-100 flex items-center gap-2"
                >
                  <LogOut size={16} />
                  Log Out
                </button>
              </div>
            </div>
          )}
        </div>
    </nav>
  );
};

export default Navbar;

