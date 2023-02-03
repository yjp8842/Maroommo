import { createSlice } from "@reduxjs/toolkit";

export const commentSlice = createSlice({
  name: "comment",
  initialState: {
    id: 0,
    content: "",
    date: Date.now(),
    articleId: 0,
    comments: [],
  },
  reducers: {
    registerComment: (state, { payload: comment }) => {
      console.log("댓글 등록 액션 호출 -- registerComment"); // saga 애서 감시용
    },
    getComments: (state, { payload: articleId }) => {
      console.log("댓글 불러오기 액션 호출 -- getComments"); // saga 에서 감시용
    },
    getCommentsAsync: (state, { payload: list }) => {
      return {
        ...state,
        comments: list,
      };
    },
    deleteComment: (state, { payload: id }) => {
      console.log("댓글 삭제 액션 호출 -- deleteComments"); // saga 에서 감시용
    },
  },
});

export const commentReducers = commentSlice.reducer;
export const commentActions = commentSlice.actions;