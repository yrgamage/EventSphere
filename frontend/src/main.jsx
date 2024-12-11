import { createRoot } from 'react-dom/client'
import App from './App.jsx'
import { StrictMode } from 'react';
import {
  createBrowserRouter,
  RouterProvider,
} from "react-router-dom";
import VendorPage from './Pages/VendorPage.jsx';
import CustomerPage from './Pages/CustomerPage.jsx';
import SignupPage from './Forms/Login/signin.jsx';
import Parameteres from './Pages/Parameteres.jsx';
import Home from './Pages/Home.jsx';

const router = createBrowserRouter([
  {
    path: '/',
    element: <div><App /></div>
  },
  {
    path: '/home',
    element: <div><Home /></div>
  },
  {
    path: '/vendor',
    element: <div><VendorPage/></div>
  },
  {
    path: '/customer',
    element: <div><CustomerPage /></div>
  },
  {
    path: '/parameteres',
    element: <div><Parameteres /></div>
  },
  {
    path: '/signin',
    element: <div><SignupPage /></div>
  }
]);

createRoot(document.getElementById('root')).render(
  <StrictMode>
    <RouterProvider router={router}/>
  </StrictMode>
)
