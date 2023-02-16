
import { createSlice } from "@reduxjs/toolkit";

export const questionArticleSlice = createSlice({
  // categorysub_id 로 그룹 정보까지...
  name: "questionArticle",
  initialState: {
    id: 0,
    title: "",
    content: "",
    views: 0,
    createTime: "",
    editDate: "",
    user_id: "",
    status: 0,
    answers:[],
    good: 0,
    picture: null
  },
  reducers: {
    registerQuestionArticle: (state, { payload: questionArticle }) => {
      console.log('register 액션 호출'); // saga에서 감시용
    },
    getQuestionArticle: (state, { payload: id }) => {
      console.log('조회 액션 호출'); // saga에서 감시용
    },
    getQuestionArticleAsync: (state, { payload: questionArticle }) => {
      console.log('질문상세리듀서호출')
      console.log('question article slice 호출 : ',Object.values(questionArticle)[0]); // saga에서 호출용
      console.log(Object.values(questionArticle)[0].status)
      return {
        ...state,
        id: Object.values(questionArticle)[0].id,
        title: Object.values(questionArticle)[0].title,
        content: Object.values(questionArticle)[0].content,
        createTime: Object.values(questionArticle)[0].createTime,
        views: Object.values(questionArticle)[0].views,
        user_id: Object.values(questionArticle)[0].user_id,
        status: Object.values(questionArticle)[0].status,
        answers: Object.values(questionArticle)[0].answers,
        good: Object.values(questionArticle)[0].good,
        picture: Object.values(questionArticle)[0].picture
      };
    },
    fetchQuestionArticle: (state, {payload: id}) => {
      console.log("게시글 조회 액션 호출 -- fetchQuestionArticle"); // saga에서 감시용
    },
    updateQuestionArticle: (state, { payload: questionArticle }) => {
      console.log("게시글 수정 액션 호출 -- updateQuestionArticle"); // saga에서 감시용
    },
    updateStatus: (state, { payload: questionArticle }) => {
      console.log("상태 수정 액션 호출 -- updateStatus"); // saga에서 감시용
    },

    deleteQuestionArticle: (state, { payload: id }) => {
      console.log("게시글 삭제 액션 호출 -- deleteQuestionArticle"); // saga 에서 감시용
    },
    changeQuestionRegisterInput: (state, { payload }) => {
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

export const questionArticleReducers = questionArticleSlice.reducer;
export const questionArticleActions = questionArticleSlice.actions;