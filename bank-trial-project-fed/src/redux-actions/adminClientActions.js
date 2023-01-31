import axios from "axios";
import { adminLoginActions, getAllAccountsActions } from "../redux-reducer/adminClientReducer";

export const adminLogin = (loginDetails, dispatch) =>{
    axios.post("http://localhost:8080/api/adminClient/adminLogin", loginDetails).then(res => {
        console.log(res.data)
        dispatch(adminLoginActions.loginSuccess({userInfo: res.data}))
        localStorage.setItem("userInfo", JSON.stringify(res.data))
    }).catch(err => {
        console.log(err.message);
        dispatch(adminLoginActions.loginFail({ error: err.message }));
    });
}

export const logout = (dispatch) => {
    dispatch(adminLoginActions.logout());
    localStorage.removeItem("userInfo");
}

export const getAllClient = (dispatch) => {
    axios.get("http://localhost:8080/api/adminClient/viewAllClient").then(res => {
        console.log(res.data)
        dispatch(getAllAccountsActions.getAllAccountsSucess({allAccounts: res.data}))
    }).catch(err => {
        console.log(err.message);
        dispatch(getAllAccountsActions.getAllAccountsFail({error: err.message}))
    });
}