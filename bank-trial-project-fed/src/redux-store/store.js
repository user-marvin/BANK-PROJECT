import { configureStore } from "@reduxjs/toolkit";

import { 
  adminLoginReducer, 
  getAllAccountsReducer, 
  getAccountReducer,
  registerAccountReducer,
  updateAccountReducer,
  archiveAccountReducer, 
  deleteAccountReducer
 } from "../redux-reducer/adminClientReducer";
import { clientLoginReducer } from "../redux-reducer/userClientReducer";
export const store = configureStore({
    reducer: {
        adminLogin: adminLoginReducer.reducer,
        getAllAccount: getAllAccountsReducer.reducer,
        getAccount: getAccountReducer.reducer,
        registerAccount: registerAccountReducer.reducer,
        updateAccount: updateAccountReducer.reducer,
        archiveAccount: archiveAccountReducer.reducer,
        deleteAccount: deleteAccountReducer.reducer,
        clientLogin: clientLoginReducer.reducer,
    },
    middleware: (getDefaultMiddleware) =>
    getDefaultMiddleware({
      serializableCheck: false,
    }),
})

