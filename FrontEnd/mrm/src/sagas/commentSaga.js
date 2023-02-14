// import Axios from "axios";
import history from "../utils/history";
import api from "../utils/axiosInstance";
// import { commentActions } from "../slice/commentSlice";
// import { put } from "redux-saga/effects";

export function* registerCommentAsync(action) {
  const data = action.payload;
  console.log('댓글등록')
  console.log(data)
  // const BASE_URL = "https://i8a406.p.ssafy.io";

  yield api.post(`/comment/`, data);

  history.go(0); // refresh
}

// export function* getCommentsAsync(action) {
//   const articleId = action.payload;

//   const response = yield Axios.get(
//     `/api/comment?articleId=${articleId}`
//   );

//   yield put(commentActions.getCommentsAsync(response.data));
// }

export function* deleteCommentAsync(action) {
  const commentId = action.payload;
  // const BASE_URL = "https://i8a406.p.ssafy.io";

  yield api.delete(`/comment/${commentId}/hd`);

  history.go(0);
}