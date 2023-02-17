import { createSlice } from "@reduxjs/toolkit";

export const answerSlice = createSlice({
  name: "answer",
  initialState: {
    id: 0,
    content: "",
    date: Date.now(),
    questionArticleId: 0,
    answers: [],
    user_id: "",
    good: 0,
  },
  reducers: {
    registerAnswer: (state, { payload: answer }) => {
      console.log("답변 등록 액션 호출 -- registerAnswer"); // saga 애서 감시용
    },
    getAnswers: (state, { payload: questionArticleId }) => {
      console.log("답변 불러오기 액션 호출 -- getAnswers"); // saga 에서 감시용
    },
    getAnswersAsync: (state, { payload: list }) => {
      return {
        ...state,
        answers: list,
      };
    },
    deleteAnswer: (state, { payload: id }) => {
      console.log("답변 삭제 액션 호출 -- deleteAnswers"); // saga 에서 감시용
    },
  },
});

export const answerReducers = answerSlice.reducer;
export const answerActions = answerSlice.actions;