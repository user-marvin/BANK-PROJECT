import { configureStore } from "@reduxjs/toolkit";

import { adminLoginReducer } from "../redux-reducer/adminClientReducer";
import { clientLoginReducer } from "../redux-reducer/userClientReducer";
export const store = configureStore({
    reducer: {
        adminLogin: adminLoginReducer.reducer,
        clientLogin: clientLoginReducer.reducer,
    },
    middleware: (getDefaultMiddleware) =>
    getDefaultMiddleware({
      serializableCheck: false,
    }),
})

