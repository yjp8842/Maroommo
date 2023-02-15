// import Axios from "axios";
import history from "../utils/history";
// import { commentActions } from "../slice/commentSlice";
// import { questionCommentActions } from "../slice/questionCommentSlice";
// import { put } from "redux-saga/effects";
import api from "../utils/axiosInstance";

export function* registerAnswerAsync(action) {
  const data = action.payload;
  console.log('답변 등록')
  console.log(data)

  const response = yield api.post('/answer', data)
  console.log(response)

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
  yield api.delete(`/answer/${answerId}/hd`)
  history.go(0);
}