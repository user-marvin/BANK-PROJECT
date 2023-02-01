import React, {useEffect, useState} from 'react'
import { TextField, Button } from '@mui/material'
import styles from "./ClientLogin.module.css"
import { useFormik } from 'formik'
import * as Yup from "yup"
import { useDispatch, useSelector } from 'react-redux'
import { clientLogin } from '../../redux-actions/userClientActions'
import Swal from 'sweetalert2'
function ClientLogin() {
    const dispatch = useDispatch();

    const loggedUser = useSelector((state) => state.clientLogin)
    const {userInfo, error} = loggedUser;
    const [submitClicked, setSubmitClicked] = useState(false);
    const formik = useFormik({
        initialValues: {
            userName: "",
            password: ""
        },
        validationSchema: Yup.object({
            userName: Yup.string().min(5).required("required"),
            password: Yup.string().min(5).required("required")
        }),
        onSubmit : () => {
            if(formik.values.userName.length>=5 && formik.values.password.length>=5){
                console.log("You click submit")
                const loginDetails = {
                    userName: formik.values.userName,
                    password: formik.values.password
                }
                dispatch({type: "clientLogin"}, clientLogin(loginDetails, dispatch));
            }
            setSubmitClicked(true)
        }
    });
    useEffect (() => {
        if(userInfo!=null){
            console.log(userInfo)
        }
        if(error!=null && submitClicked == true){
            Swal.fire({
                title: error,
                text: null,
                icon: 'warning',
                showCancelButton: false,
                confirmButtonText: 'Ok',
                cancelButtonText: null
            });
            setSubmitClicked(false)
        }
    }, [userInfo, error, submitClicked])
    return (
        <div className={styles["client-login-container"]}>
            <div className={styles["header-container"]}>
                <h1>Welcome Back!</h1>
                <h3>User Login</h3>
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
                        <p>{formik.errors.userName? "Please enter a valid username": null}</p>
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
                        <p>{formik.errors.password? "Please enter your password": null}</p>
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

export default ClientLogin