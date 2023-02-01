import React, {useEffect} from 'react'
import styles from "./AdminDashBoard.module.css"
import AllAccounts from './DashboardComponents/AllAccounts/AllAccounts'
import AllBankAccounts from './DashboardComponents/AllBankAccounts/AllBankAccounts'
import OpenAccountForUser from './DashboardComponents/OpenAccountForUser/AllBankAccounts'
import OpenBankAccForUser from './DashboardComponents/OpenBankAccForUser/AllBankAccounts'
import LogoutIcon from '@mui/icons-material/Logout';
import { useNavigate } from 'react-router-dom'
import { Button, IconButton } from '@mui/material'
import Swal from 'sweetalert2'
import { useDispatch, useSelector } from 'react-redux'
import { logout, getAllClient } from '../../redux-actions/adminClientActions'
import DashboardIcon from '@mui/icons-material/Dashboard';


function AdminDashBoard() {
    const navigate = useNavigate();
    const dispatch = useDispatch();
    const user = useSelector((state) => state.adminLogin);
    const {userInfo} = user;
    
    const handleLogout = () =>{
        Swal.fire({
            title: 'Are you sure you want to logout?',
            text: null,
            icon: 'warning',
            showCancelButton: true,
            confirmButtonText: 'Logout',
            cancelButtonText: 'Cancel'
        }).then((result) => {
            if (result.value) {
               dispatch(logout(dispatch))
            }
        });      
    }
    useEffect(() => { 
        if(userInfo==null){
            navigate("/adminLogin")
        }
    }, [userInfo, navigate])
    
    const getAllUsers = () => {
        navigate("/allAccounts")
    }
    return (
        <div className={styles["dashboard-main-container"]}>
            <div className={styles["nav-bar-container"]}>
                <div className={styles["dashboard-icon-container"]}>
                    <IconButton aria-label="dashboard" color='primary'>
                        <DashboardIcon sx={ {fontSize: 30} }/>
                    </IconButton>
                </div>
                <div className={styles["transactions-container"]}>
                    <div className={styles["box-container"]} onClick={()=>getAllUsers()}><AllAccounts/></div>
                    <div className={styles["box-container"]}><AllBankAccounts/></div>
                    <div className={styles["box-container"]}><OpenAccountForUser/></div>
                    <div className={styles["box-container"]}><OpenBankAccForUser/></div>
                </div>
                <div className={styles["logout-container"]}>
                    <Button 
                        variant="outlined" 
                        endIcon={<LogoutIcon/>}
                        onClick={() => handleLogout()}>
                        Logout
                    </Button>
                </div>
            </div>
        </div>
    ) 
}

export default AdminDashBoard