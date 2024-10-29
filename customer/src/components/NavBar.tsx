import React, { useState, useEffect, useRef } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import { User, ShoppingCart, Menu, X, Layout, Settings } from 'lucide-react';
import { useCart } from '../context/CartContext';

function NavBar() {
  // Estados
  const [isMenuVisible, setMenuVisible] = useState(false);
  const [isUserMenuOpen, setIsUserMenuOpen] = useState(false);
  const { cart } = useCart();
  const navigate = useNavigate();
  const dropdownRef = useRef(null);

  // Efecto para cerrar el menú al hacer clic fuera
  useEffect(() => {
    const handleClickOutside = (event: { target: any; }) => {
      if (dropdownRef.current && !dropdownRef.current.contains(event.target)) {
        setIsUserMenuOpen(false);
      }
    };

    document.addEventListener('mousedown', handleClickOutside);
    return () => document.removeEventListener('mousedown', handleClickOutside);
  }, []);

  // Handlers
  const toggleMenu = () => setMenuVisible(!isMenuVisible);

  const handleUserNavigation = async () => {
    const token = localStorage.getItem('token');
    if (!token) {
      navigate('/login');
      return;
    }

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
        if (userProfile.role.id === 2) {
          setIsUserMenuOpen(!isUserMenuOpen);
        } else {
          navigate('/customer');
        }
      } else {
        navigate('/login');
      }
    } catch (error) {
      console.error('Error during fetch:', error);
      navigate('/login');
    }
  };

  // Componentes del NavBar
  const Logo = () => (
    <Link to="/" className="text-2xl font-bold tracking-wider hover:text-gray-300 transition-colors">
      JSA Services
    </Link>
  );

  const DesktopMenu = () => (
    <div className="hidden md:flex space-x-8">
      <Link to="/services" className="hover:text-gray-300 transition-colors font-medium">Services</Link>
      <Link to="/companies" className="hover:text-gray-300 transition-colors font-medium">Companies</Link>
      <Link to="/about" className="hover:text-gray-300 transition-colors font-medium">About Us</Link>
    </div>
  );

  const CartIcon = () => (
    <Link to="/cart" className="relative hover:text-gray-300 transition-colors">
      <ShoppingCart className="h-6 w-6" />
      {cart.length > 0 && (
        <span className="absolute -top-2 -right-2 bg-red-500 text-white rounded-full w-5 h-5 flex items-center justify-center text-xs font-bold">
          {cart.reduce((total, item) => total + item.quantity, 0)}
        </span>
      )}
    </Link>
  );

  const UserMenu = () => (
    <div className="relative" ref={dropdownRef}>
      <button 
        onClick={handleUserNavigation} 
        className="p-2 rounded-full hover:bg-gray-800 transition-colors focus:outline-none focus:ring-2 focus:ring-gray-600"
      >
        <User className="h-6 w-6" />
      </button>

      {isUserMenuOpen && (
        <div className="absolute right-0 mt-2 w-56 rounded-lg shadow-lg bg-white text-gray-800 py-2 border border-gray-200 z-50">
          <Link
            to="/admin"
            className="block px-4 py-3 hover:bg-gray-100 flex items-center gap-3 transition-colors"
            onClick={() => setIsUserMenuOpen(false)}
          >
            <Settings size={18} />
            <span className="font-medium">Admin Panel</span>
          </Link>
          <Link
            to="/customer"
            className="block px-4 py-3 hover:bg-gray-100 flex items-center gap-3 transition-colors"
            onClick={() => setIsUserMenuOpen(false)}
          >
            <Layout size={18} />
            <span className="font-medium">Dashboard</span>
          </Link>
        </div>
      )}
    </div>
  );

  const MobileMenuButton = () => (
    <button 
      onClick={toggleMenu} 
      className="md:hidden p-2 rounded-full hover:bg-gray-800 transition-colors focus:outline-none focus:ring-2 focus:ring-gray-600"
    >
      {isMenuVisible ? <X className="h-6 w-6" /> : <Menu className="h-6 w-6" />}
    </button>
  );

  const MobileMenu = () => (
    isMenuVisible && (
      <div className="md:hidden mt-4 bg-gray-900 rounded-lg overflow-hidden">
        <Link 
          to="/services" 
          className="block px-4 py-3 hover:bg-gray-800 transition-colors" 
          onClick={toggleMenu}
        >
          Services
        </Link>
        <Link 
          to="/companies" 
          className="block px-4 py-3 hover:bg-gray-800 transition-colors" 
          onClick={toggleMenu}
        >
          Companies
        </Link>
        <Link 
          to="/about" 
          className="block px-4 py-3 hover:bg-gray-800 transition-colors" 
          onClick={toggleMenu}
        >
          About Us
        </Link>
      </div>
    )
  );

  return (
    <nav className="bg-black text-white py-4 px-6 relative">
      <div className="container mx-auto">
        <div className="flex justify-between items-center">
          <Logo />
          <DesktopMenu />
          <div className="flex items-center space-x-6">
            <CartIcon />
            <UserMenu />
            <MobileMenuButton />
          </div>
        </div>
        <MobileMenu />
      </div>
    </nav>
  );
}

export default NavBar;