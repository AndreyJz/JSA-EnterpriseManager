import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import styled from 'styled-components';
import Navbar from './components/Navbar';
import Sidebar from './components/Sidebar';
import EntityList from './components/EntityList';
import EntityDetails from './components/EntityDetails';
import Tasks from './components/Tasks';
import Footer from './components/Footer';

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

const App: React.FC = () => {
  return (
    <Router>
      <AppContainer>
        <Navbar />
        <ContentWrapper>
          <Sidebar />
          <MainContent>
            <Routes>
              <Route path="/" element={<h1>Welcome to Admin Dashboard</h1>} />
              <Route path="/tasks" element={<Tasks />} />
              <Route path="/:entity" element={<EntityList />} />
              <Route path="/:entity/:id" element={<EntityDetails />} />
            </Routes>
          </MainContent>
        </ContentWrapper>
        <Footer />
      </AppContainer>
    </Router>
  );
};

export default App;