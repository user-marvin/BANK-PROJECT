import { configureStore } from "@reduxjs/toolkit";

import { adminLoginReducer } from "../redux-reducer/adminClientReducer";

export const store = configureStore({
    reducer: {
        adminLogin: adminLoginReducer.reducer
    },
    middleware: (getDefaultMiddleware) =>
    getDefaultMiddleware({
      serializableCheck: false,
    }),
})

