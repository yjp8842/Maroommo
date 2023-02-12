import { createSlice } from "@reduxjs/toolkit";

export const questionSlice = createSlice({
  name: "question",
  initialState: {
    question: [],
    isLoading: true,
    isSuccess: false,
    error: null,
  },
  reducers: {
    getQuestion: (state, { payload }) => {
      console.log("getQuestion 액션 호출");
    },
    getQuestionSuccessAsync: (state, { payload: data }) => {
      console.log('saga에서 put 액션 호출 - getQuestionSuccessAsync')
      return {
        ...state,
        question: data,
        isSuccess: true,
        isLoading: false,
      };
    },
    getQuestionFailedAsync: (state, { payload: error }) => {
      console.log("saga에서 put 액션 호출 -- getQuestionFailedAsync");
      return {
        ...state,
        isLoading: false,
        error: error,
      };
    }
  }  
});

export const questionReducers = questionSlice.reducer;
export const questionActions = questionSlice.actions;