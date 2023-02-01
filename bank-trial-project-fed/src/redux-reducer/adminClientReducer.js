import { createSlice } from "@reduxjs/toolkit";

export const adminLoginReducer = createSlice({
    name: "adminLogin",
    initialState: {
        userInfo: localStorage.getItem("userInfo")
        ? JSON.parse(localStorage.getItem("userInfo"))
        : null,
        error: null,
    },
    reducers: {
        loginSuccess(state, action){
            state.userInfo = action.payload.userInfo;
            state.error = null;
        },
        loginFail(state, action){
            state.isLoading = false;
            state.error = action.payload.error;
        },
        logout(state){
            state.userInfo = null;
            state.error = null;
        },
    },
    
});

export const adminLoginActions = adminLoginReducer.actions;

export const getAllAccountsReducer = createSlice({
    name: "getAllAccountsReducer",
    initialState:{
        allAccounts: null,
        error: null
    },
    reducers: {
        getAllAccountssuccess(state, action){
            state.allAccounts = action.payload.allAccounts;
            state.error = null
        },
        getAllAccountsFail(state, action){
            state.allAccounts = null;
            state.error = action.payload.error
        }
    }
});

export const getAllAccountsActions = getAllAccountsReducer.actions;

export const getAccountReducer = createSlice({
    name: "getAccountReducer",
    initialState:{
        account: null,
        error: null
    },
    reducers: {
        getAccountsuccess(state, action){
            state.account = action.payload.account;
            state.error = null
        },
        getAccountFail(state, action){
            state.account = null;
            state.error = action.payload.error
        }
    }
});

export const getAccountActions = getAccountReducer.actions;

export const registerAccountReducer = createSlice({
    name: "registerAccountReducer",
    initialState:{
        account: null,
        error: null
    },
    reducers: {
        registerAccountsuccess(state, action){
            state.account = action.payload.account;
            state.error = null
        },
        registerAccountFail(state, action){
            state.account = null;
            state.error = action.payload.error
        }
    }
});

export const registerAccountActions = registerAccountReducer.actions;

export const updateAccountReducer = createSlice({
    name: "updateAccountReducer",
    initialState:{
        account: null,
        error: null
    },
    reducers: {
        updateAccountsuccess(state, action){
            state.account = action.payload.account;
            state.error = null
        },
        updateAccountFail(state, action){
            state.account = null;
            state.error = action.payload.error
        }
    }
});

export const updateAccountActions = updateAccountReducer.actions;

export const archiveAccountReducer = createSlice({
    name: "archiveAccountReducer",
    initialState:{
        account: null,
        error: null
    },
    reducers: {
        archiveAccountsuccess(state, action){
            state.account = action.payload.account;
            state.error = null
        },
        archiveAccountFail(state, action){
            state.account = null;
            state.error = action.payload.error
        }
    }
});

export const archiveAccountActions = archiveAccountReducer.actions;

export const deleteAccountReducer = createSlice({
    name: "deleteAccountReducer",
    initialState:{
        status: null,
        error: null
    },
    reducers: {
        deleteAccountsuccess(state, action){
            state.status = action.payload.status;
            state.error = null
        },
        deleteAccountFail(state, action){
            state.status = null;
            state.error = action.payload.error
        }
    }
});

export const deleteAccountActions = deleteAccountReducer.actions;