import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import styled from 'styled-components';
import NavBar from './components/NavBar';
import Footer from './components/Footer';
import Home from './components/Home';
import Services from './components/Services';
import Companies from './components/Companies';
import AboutUs from './components/AboutUs';
import Cart from './components/Cart';
import ContactUs from './components/ContactUs';
import Login from './components/Login';
import SignUp from './components/SignUp';
import Customer from './components/Customer';
import { CartProvider } from './context/CartContext';
import Navbar from "./admin/components/Navbar.tsx";
import Sidebar from "./admin/components/Sidebar.tsx";
import Tasks from "./admin/components/Tasks.tsx";
import EntityList from "./admin/components/EntityList.tsx";
import EntityDetails from "./admin/components/EntityDetails.tsx";

const AppContainer = styled.div`
  display: flex;
  flex-direction: column;
  min-height: 100vh;
`;

const ContentWrapper = styled.div`
  display: flex;
  flex-grow: 1;
`;

const MainContent = styled.main`
  flex-grow: 1;
  margin-left: 5em;
  padding: 20px;
`;

function App() {
  return (
      <Router>
        <Routes>
          {/* Rutas del sitio principal */}
          <Route path="/*" element={
            <CartProvider>
              <div className="flex flex-col min-h-screen">
                <NavBar />
                <main className="flex-grow">
                  <Routes>
                    <Route index element={<Home />} />
                    <Route path="services" element={<Services />} />
                    <Route path="companies" element={<Companies />} />
                    <Route path="about" element={<AboutUs />} />
                    <Route path="cart" element={<Cart />} />
                    <Route path="contact" element={<ContactUs />} />
                    <Route path="login" element={<Login />} />
                    <Route path="signup" element={<SignUp />} />
                    <Route path="customer" element={<Customer />} />
                  </Routes>
                </main>
                <Footer />
              </div>
            </CartProvider>
          } />

          {/* Rutas del Dashboard de Admin */}
          <Route path="/admin/*" element={
            <AppContainer>
              <Navbar />
              <ContentWrapper>
                <Sidebar />
                <MainContent>
                  <Routes>
                    <Route index element={<h1>Welcome to Admin Dashboard</h1>} />
                    <Route path="/tasks" element={<Tasks />} />
                    <Route path="/:entity" element={<EntityList />} />
                    <Route path="/:entity/:id" element={<EntityDetails />} />
                  </Routes>
                </MainContent>
              </ContentWrapper>
              <Footer />
            </AppContainer>
          } />
        </Routes>
      </Router>
  );
}

export default App;
