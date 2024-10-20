import { useState } from 'react';
import './NavBar.css';

function NavBar() {
  // Estado para controlar si el menú está visible o no
  const [isMenuVisible, setMenuVisible] = useState(false);

  // Función para mostrar el menú
  const showMenu = () => {
    setMenuVisible(true);
  };

  // Función para ocultar el menú
  const hideMenu = () => {
    setMenuVisible(false);
  };

  return (
    <nav className="navbar">
      {/* Botón para mostrar el menú en móvil */}
      <div className="menu-toggle" id="mobile-menu" onClick={showMenu}>
        <i className="fas fa-bars"></i>
      </div>
      
      {/* Logo de la empresa */}
      <div className="logo">
        <a href="#">JSA Services</a>
      </div>
      
      {/* Lista de enlaces, controlada por el estado isMenuVisible */}
      <ul className={`nav-links ${isMenuVisible ? 'visible' : ''}`} id="nav-list">
        <li><a href="#" className="closemenu" onClick={hideMenu}>x</a></li>
        <li><a href="#">Services</a></li>
        <li><a href="#">Companies</a></li>
        <li><a href="#">About Us</a></li>
      </ul>

      {/* Íconos del carrito de compras y perfil */}
      <div className="nav-icons">
        <a href="#" className="icon">
          <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2" strokeLinecap="round" strokeLinejoin="round" className="lucide lucide-shopping-cart h-5 w-5">
            <circle cx="8" cy="21" r="1"></circle>
            <circle cx="19" cy="21" r="1"></circle>
            <path d="M2.05 2.05h2l2.66 12.42a2 2 0 0 0 2 1.58h9.78a2 2 0 0 0 1.95-1.57l1.65-7.43H5.12"></path>
          </svg>
        </a>
        <a href="#" className="icon">
          <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2" strokeLinecap="round" strokeLinejoin="round" className="lucide lucide-user h-5 w-5">
            <path d="M19 21v-2a4 4 0 0 0-4-4H9a4 4 0 0 0-4 4v2"></path>
            <circle cx="12" cy="7" r="4"></circle>
          </svg>
        </a>
      </div>
    </nav>
  );
}

export default NavBar;
