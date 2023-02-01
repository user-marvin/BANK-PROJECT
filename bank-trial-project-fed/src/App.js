import './App.css';
import { Routes, Route } from 'react-router-dom';
import AdminLogin from './components/AdminLogin/AdminLogin';
import ClientLogin from './components/ClientLogin/ClientLogin';
import AdminDashBoard from './components/AdminDashBoard/AdminDashBoard';
import DisplayAllAccounts from './components/AdminDashBoard/DashboardComponents/AllAccounts/DisplayAllAccounts';
import { useSelector } from 'react-redux';
import { useEffect } from 'react';
function App() {

  return (
    <div className='App'>
      <Routes>
          <Route path="/" element={<ClientLogin/>}/>
          <Route path="/adminLogin" element={<AdminLogin/>}/>
          <Route path="/adminDashBoard" element={<AdminDashBoard/>}/>
          <Route path="/allAccounts" element={<DisplayAllAccounts/>}/>
      </Routes>
    </div>
  );
}

export default App;
