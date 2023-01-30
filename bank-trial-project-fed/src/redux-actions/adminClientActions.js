import axios from "axios";
import { adminLoginActions } from "../redux-reducer/adminClientReducer";

var loginDetails = [];
export const adminLogin = (loginDetails, dispatch) =>{
    
    axios.post("http://localhost:8080/api/adminClient/adminLogin", loginDetails).then(res => {
        console.log(res.data)
        dispatch(adminLoginActions.loginSuccess({userInfo: res.data}))
        localStorage.setItem("userName", JSON.stringify(res.data))
    }).catch(err => {
        console.log(err.message);
        dispatch(adminLoginActions.loginFail({ error: err.message }));
    });
}