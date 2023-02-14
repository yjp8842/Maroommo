import { call, put } from "redux-saga/effects";
// import Axios from "axios";
// import { articleActions } from "../slice/articleSlice";
import { questionArticleActions } from "../slice/questionArticleSlice";
import history from "../utils/history";
import api from "../utils/axiosInstance";


export function* registerQuestionArticleAsync(action) {
  const data = action.payload;
  console.log(data)
  console.log(data.picture)
  // const BASE_URL = "https://i8a406.p.ssafy.io";

  // const response = yield api.post(`/question`, data);

  const response = yield api.post(
    `/question?categorySub_id=${data.categorysub_id}&content=${data.content}&title=${data.title}&user_id=${data.user_id}`,
    data.picture
  )


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
  console.log('---------------------')
  console.log('questionarticle 출력 :',questionArticle)
  console.log('---------------------')

  const response = yield api.post(
    `/question/update?id=${questionArticle.id}&content=${questionArticle.content}&title=${questionArticle.title}&user_id=${questionArticle.user_id}&status=${questionArticle.status}`,
    questionArticle.picture
  );


  alert("저장되었습니다.");


  history.push(`/group/question/questionArticle/${response.data.question.id}`);
}

export function* deleteQuestionArticleAsync(action) {
  const id = action.payload;
  console.log('삭제해!!!')
  // const BASE_URL = "https://i8a406.p.ssafy.io";

  
  yield api.delete(`/question/${id}/hd`);

  alert("삭제되었습니다.");

  history.push(`/group/question`);
}