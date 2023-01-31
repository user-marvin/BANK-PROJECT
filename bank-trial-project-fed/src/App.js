import './App.css';
import { Routes, Route } from 'react-router-dom';
import AdminLogin from './components/AdminLogin/AdminLogin';
import ClientLogin from './components/ClientLogin/ClientLogin';
import { useSelector } from 'react-redux';
import { useEffect } from 'react';
function App() {


  const loggedUser = useSelector((state) => state.clientLogin)
  const {userInfo, error} = loggedUser;

  useEffect(() => {
    // if(userInfo!=null){
    //   console.log(userInfo)
    // }

    // if(error!=null){
    //   console.log(loggedUser)
    // }
  }, [userInfo, error])



  return (
    <div className='App'>
      <Routes>
          <Route path="/" element={<ClientLogin/>}/>
          <Route path="/adminLogin" element={<AdminLogin/>}/>
      </Routes>
    </div>
  );
}

export default App;
