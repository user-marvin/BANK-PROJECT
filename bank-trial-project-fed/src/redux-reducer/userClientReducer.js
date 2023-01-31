import { createSlice } from "@reduxjs/toolkit";

export const clientLoginReducer = createSlice({
    name: "clientLogin",
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
            state.error = action.payload.error;
        },
        logout(state){
            state.userInfo = null;
            state.error = null;
        },
    },
});

export const clientLoginActions = clientLoginReducer.actions;