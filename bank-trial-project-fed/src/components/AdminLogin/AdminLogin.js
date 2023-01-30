import React, {useEffect} from 'react'
import { TextField, Button } from '@mui/material'
import styles from "./AdminLogin.module.css"
import { useFormik } from 'formik'
import * as Yup from "yup"
import { useDispatch, useSelector } from 'react-redux'
import { adminLogin } from '../../redux-actions/adminClientActions'
// import { useNavigate } from 'react-router-dom'

function AdminLogin() {
    const dispatch = useDispatch();
    // const navigate = useNavigate();
    
    const loggedUser = useSelector((state) => state.adminLogin);
    const {userInfo} = loggedUser;

    const formik = useFormik({
        initialValues: {
            userName: "",
            password: ""
        },
        validationSchema: Yup.object({
            userName: Yup.string().min(5).required("Required"),
            password: Yup.string().min(5).required("Required")
        }),
        onSubmit : () => {
            const loginDetails = {
                userName: formik.values.userName,
                password: formik.values.password
            }
            dispatch({type: "adminLogin"}, adminLogin(loginDetails, dispatch));
        }
    });
    useEffect (() => {
        if(userInfo){
            alert(userInfo)
        }
    }, [userInfo])
    return (
        <div className={styles["admin-login-container"]}>
            <div className={styles["header-container"]}>
                <h1>Welcome Back!</h1>
                <h3>Administrator Login</h3>
            </div>
            <form onSubmit={formik.handleSubmit}>
                <div className={styles["textfield-container"]}>
                    <div className={styles["textfield"]}>
                        <TextField 
                            id="userName" 
                            label="Username" 
                            name="userName"
                            variant="outlined" 
                            size="small" 
                            value={formik.values.userName} 
                            onChange={formik.handleChange}/>
                        <p>{formik.errors.userName? "Please enter a valid username": ""}</p>
                    </div>
                    <div className={styles["textfield"]}>
                        <TextField 
                            id="password" 
                            label="Password" 
                            type="password"
                            name="password"
                            variant="outlined" 
                            size="small" 
                            value={formik.values.password} 
                            onChange={formik.handleChange}/>
                        <p>{formik.errors.password? "Please enter your password": ""}</p>
                    </div>
                </div>
                <div className={styles["button-container"]}>
                    <div className={styles["button"]}>
                        <Button 
                            type='submit'
                            variant="contained">Login</Button>
                    </div>
                </div>
            </form>
        </div>
    )
}

export default AdminLogin