import axios from "axios";
import { adminLoginActions, 
    getAllAccountsActions,  
    getAccountActions,
    registerAccountActions,
    updateAccountActions,
    archiveAccountActions,
    deleteAccountActions } from "../redux-reducer/adminClientReducer";
export const adminLogin = (loginDetails, dispatch) =>{
    axios.post("http://localhost:8080/api/adminClient/adminLogin", loginDetails).then(res => {
        dispatch(adminLoginActions.loginSuccess({userInfo: res.data}))
        localStorage.setItem("userInfo", JSON.stringify(res.data))
    }).catch(err => {
        if(err.request.status === 400){
            dispatch(adminLoginActions.loginFail({ error: "Username or password incorrect" }));
        }
    });
}

export const logout = (dispatch) => {
    dispatch(adminLoginActions.logout());
    localStorage.removeItem("userInfo");
}

export const getAllClient = (dispatch) => {
    axios.get("http://localhost:8080/api/adminClient/viewAllClient").then(res => {
        dispatch(getAllAccountsActions.getAllAccountssuccess({allAccounts: res.data}))
    }).catch(err => {
        dispatch(getAllAccountsActions.getAllAccountsFail({error: err.message}))
    });
}
//----------------------------------------------------------------
export const getClient = (userName, dispatch) => {
    axios.get(`http://localhost:8080/api/adminClient/viewClient/${userName}`).then(res => {
        dispatch(getAccountActions.getAccountsuccess({account: res.data}))
    }).catch(err => {
        dispatch(getAccountActions.getAccountFail({error: err.message}))
    });
}

export const registerClient = (newClient, dispatch) => {
    axios.post(`http://localhost:8080/api/adminClient/registerClient`, newClient).then(res => {
        dispatch(registerAccountActions.registerAccountsuccess({account: res.data}))
        getAllClient(dispatch)
    }).catch(err => {
        dispatch(registerAccountActions.registerAccountFail({error: err.message}))
    });
}

export const updateClient = (updatedClient, dispatch) => {
    axios.put("http://localhost:8080/api/adminClient/updateClient", updatedClient).then(res => {
        dispatch(updateAccountActions.updateAccountsuccess({account: res.data}))
        getAllClient(dispatch)
    }).catch(err => {
        dispatch(updateAccountActions.updateAccountFail({error: err.message}))
    });
}

export const archiveClient = (clientId, dispatch) => {
    axios.put(`http://localhost:8080/api/adminClient/archiveClient/${clientId}`).then(res => {
        dispatch(archiveAccountActions.archiveAccountsuccess({account: res.data}))
        getAllClient(dispatch)
    }).catch(err => {
        dispatch(archiveAccountActions.archiveAccountFail({error: err.message}))
    });
}

export const deleteClient = (clientId, dispatch) => {
    axios.delete(`http://localhost:8080/api/adminClient/deleteClient/${clientId}`).then(res => {
        dispatch(deleteAccountActions.deleteAccountsuccess({status: res.data}))
        getAllClient(dispatch)
    }).catch(err => {
        dispatch(deleteAccountActions.deleteAccountFail({error: err.message}))
    });
}