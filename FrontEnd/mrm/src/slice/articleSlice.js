
import { createSlice } from "@reduxjs/toolkit";

export const articleSlice = createSlice({
  // categorysub_id 로 그룹 정보까지...
  name: "article",
  initialState: {
    id: 0,
    title: "",
    content: "",
    views: 0,
    createTime: "",
    editDate: "",
    categorysub_id: "",
    user_id: "",
    picture: null,
    comments: []
  },
  reducers: {
    registerArticle: (state, { payload: article }) => {
      console.log('register 액션 호출'); // saga에서 감시용
    },
    getArticle: (state, { payload: id }) => {
      console.log('조회 액션 호출'); // saga에서 감시용
    },
    getArticleAsync: (state, { payload: article }) => {
      console.log('33333333')
      console.log(Object.values(article)[0])
      return {
        ...state,
        id: Object.values(article)[0].id,
        title: Object.values(article)[0].title,
        content: Object.values(article)[0].content,
        createTime: Object.values(article)[0].createTime,
        editDate: Object.values(article)[0].editDate,
        views: Object.values(article)[0].views,
        user_id: Object.values(article)[0].user_id,
        picture: Object.values(article)[0].picture,
        comments: Object.values(article)[0].comments
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