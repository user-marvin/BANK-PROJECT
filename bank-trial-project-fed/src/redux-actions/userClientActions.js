import axios from "axios";
import { clientLoginActions } from "../redux-reducer/userClientReducer";

export const clientLogin = (loginDetails, dispatch) =>{

    axios.post("http://localhost:8080/api/userClient/clientLogin", loginDetails).then(res => {
        dispatch(clientLoginActions.loginSuccess({userInfo: res.data}))
        localStorage.setItem("userInfo", JSON.stringify(res.data))
    }).catch(err => {
        if(err.request.status === 400){
            dispatch(clientLoginActions.loginFail({ error: "Username or password incorrect" }));
        }
    });
}