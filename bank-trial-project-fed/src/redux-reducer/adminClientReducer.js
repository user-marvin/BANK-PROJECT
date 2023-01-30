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
        logout(state, action){
            state.userInfo = null;
            state.error = null;
        },
    },
    
});

export const adminLoginActions = adminLoginReducer.actions;