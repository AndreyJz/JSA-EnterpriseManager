import React from 'react'
import ReactDOM from 'react-dom/client'
import NavBar from './components/navbar/NavBar.jsx'
import Footer from './components/footer/footer.jsx'
import ListService from './components/listservice/ListServices.jsx'
import Home from './components/homepage/home.jsx'
import Cart from './components/cart/Cart.jsx'
import './index.css'

ReactDOM.createRoot(document.getElementById('root')).render(
  <React.StrictMode>
    <NavBar />
    <Home/>
    <Footer />
  </React.StrictMode>,
)
