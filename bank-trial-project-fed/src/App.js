import './App.css';
import { Routes, Route } from 'react-router-dom';
import AdminLogin from './components/AdminLogin/AdminLogin';


function App() {
  return (
    <div className='App'>
      <Routes>
          <Route path="/adminLogin" element={<AdminLogin/>}/>
      </Routes>
    </div>
  );
}

export default App;
