// import Axios from "axios";
import history from "../utils/history";
// import { commentActions } from "../slice/commentSlice";
// import { questionCommentActions } from "../slice/questionCommentSlice";
// import { put } from "redux-saga/effects";
import api from "../utils/axiosInstance";

export function* registerAnswerAsync(action) {
  // const data = action.payload;
  // const BASE_URL = "https://i8a406.p.ssafy.io";


  // yield Axios.post(BASE_URL + `/api/answer/`, data);
  const data = action.payload;
  yield api.post('/answer', data)

  history.go(0); // refresh
}

// export function* getQuestionCommentsAsync(action) {
//   const questionArticleId = action.payload;

//   const response = yield Axios.get(
//     `/api/questionComment?questionArticleId=${questionArticleId}`
//   );

//   yield put(questionCommentActions.getQuestionCommentsAsync(response.data));
// }

export function* deleteAnswerAsync(action) {
  const answerId = action.payload;
  // const BASE_URL = "https://i8a406.p.ssafy.io";

  // yield Axios.delete(BASE_URL + `/api/answer/${answerId}`);
  yield api.delete(`/answer/${answerId}`)
  history.go(0);
}