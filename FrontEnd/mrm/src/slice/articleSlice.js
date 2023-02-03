
import { createSlice } from "@reduxjs/toolkit";

export const articleSlice = createSlice({
  name: "article",
  initialState: {
    id: 0,
    title: "",
    content: "",
    views: 0,
    date: Date.now(),
    editDate: "",
  },
  reducers: {
    registerArticle: (state, { payload: article }) => {
      console.log('register 액션 호출'); // saga에서 감시용
    },
    getArticle: (state, { payload: id }) => {
      console.log('조회 액션 호출'); // saga에서 감시용
    },
    getArticleAsync: (state, { payload: article }) => {
      console.log(article); // saga에서 호출용
      return {
        ...state,
        id: article.id,
        title: article.title,
        content: article.content,
        date: article.date,
        editDate: article.editDate,
        views: article.views,
      };
    },
    fetchArticle: (state, {payload: id}) => {
      console.log("게시글 조회 액션 호출 -- fetchArticle"); // saga에서 감시용
    },
    updateArticle: (state, { payload: article }) => {
      console.log("게시글 수정 액션 호출 -- updateArticle"); // saga에서 감시용
    },
    deleteArticle: (state, { payload: id }) => {
      console.log("게시글 삭제 액션 호출 -- deleteArticle"); // saga 에서 감시용
    },
    changeRegisterInput: (state, { payload }) => {
      switch (payload.name) {
        case "title":
          return {
            ...state,
            title: payload.value,
          };

        case "content":
          return {
            ...state,
            content: payload.value,
          };

        default:
          break;
      }
    },
  },
});

export const articleReducers = articleSlice.reducer;
export const articleActions = articleSlice.actions;