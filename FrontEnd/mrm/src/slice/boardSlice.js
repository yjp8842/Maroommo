import { createSlice } from "@reduxjs/toolkit";

export const boardSlice = createSlice({
  name: "board",
  initialState: {
    board: [],
    isLoading: true,
    isSuccess: false,
    error: null,
  },
  reducers: {
    getBoard: (state, { payload }) => {
      console.log("getBoard 액션 호출");
    },
    getBoardSuccessAsync: (state, { payload: data }) => {
      console.log('saga에서 put 액션 호출 - getBoardSuccessAsync')
      return {
        ...state,
        board: data,
        isSuccess: true,
        isLoading: false,
      };
      
    },
    getBoardFailedAsync: (state, { payload: error }) => {
      console.log("saga에서 put 액션 호출 -- getBoardFailedAsync");
      return {
        ...state,
        isLoading: false,
        error: error,
      };
    }
  }  
});

export const boardReducers = boardSlice.reducer;
export const boardActions = boardSlice.actions;