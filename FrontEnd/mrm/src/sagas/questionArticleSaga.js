import { call, put } from "redux-saga/effects";
// import Axios from "axios";
// import { articleActions } from "../slice/articleSlice";
import { questionArticleActions } from "../slice/questionArticleSlice";
import history from "../utils/history";
import api from "../utils/axiosInstance";


export function* registerQuestionArticleAsync(action) {
  const data = action.payload;
  // const BASE_URL = "https://i8a406.p.ssafy.io";

  const response = yield api.post(`/question`, data);

  alert("저장되었습니다.");

  console.log(response.data);

  // history.push(`/article/${response.data.id}`);

  history.push(`/group/question/questionArticle/${response.data.id}`, response.data.id);
}


export function* getQuestionArticleAsync(action) {
  const id = action.payload;
  // const BASE_URL = "https://i8a406.p.ssafy.io";

  const response = yield api.get(`/question/${id}`);

  // const request = yield Axios.put(`/api/question/${id}`, {
  //   ...response.data,
  //   views: parseInt(response.data.views) + 1,})
  ;
  console.log('qna가져와')
  yield put(questionArticleActions.getQuestionArticleAsync(response.data));
}

export function* fetchQuestionArticleAsync(action) {
  // const BASE_URL = "https://i8a406.p.ssafy.io";

  console.log(action);
  const id = action.payload;

  const response = yield api.get(`/question/${id}`);
  console.log(response)
  yield put(questionArticleActions.getQuestionArticleAsync(response.data));
}

export function* updateQuestionArticleAsync(action) {
  // const BASE_URL = "https://i8a406.p.ssafy.io";

  const questionArticle = action.payload;

  const response = yield api.put(
    `/question/${questionArticle.id}`,
    questionArticle
  );

  alert("저장되었습니다.");

  console.log(response.data.id);

  // history.push(`/article/${response.data.id}`);

  history.push(`/group/question/questionArticle/${response.data.id}`, response.data.id);
}

export function* deleteQuestionArticleAsync(action) {
  const id = action.payload;
  // const BASE_URL = "https://i8a406.p.ssafy.io";

  
  yield api.delete(`/question/${id}`);

  alert("삭제되었습니다.");

  history.push(`/group/question`);
}