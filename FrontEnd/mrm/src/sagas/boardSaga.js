import { call, put } from "redux-saga/effects";
// import Axios from "axios";
import {boardActions} from '../slice/boardSlice'
import api from "../utils/axiosInstance";


export function* getBoardAsync() {
  // const BASE_URL = "https://i8a406.p.ssafy.io";

  // pagination, size 손보기!!!!
  // room_id 데이터 받아와야함
  try {
    const response = yield api.get(`/board?room_id=1&page=0&size=30`);
    console.log('----------');
    console.log('boardSaga response 출력 : ',response.data);
    console.log('----------');
    yield put(boardActions.getBoardSuccessAsync(response));
  } catch (e) {
    yield put(boardActions.getBoardFailedAsync(e.message));
  }
}

// 댓글 개수 실패ㅠㅠ
// import { put } from "redux-saga/effects";
// import Axios from "axios";
// import { boardActions } from "../slice/boardSlice";

// export function* getBoardAsync() {
//   try {
//     const responseForBoard = yield Axios.get(`http://localhost:4000/board/`);
//     const responseForComment = yield Axios.get(
//       `http://localhost:4000/comment/`
//     );

//     const boardData = responseForBoard.data;

//     if (responseForComment.data.length > 0) {
//       for (var article in responseForBoard.data) {
//         const comments = [];
//         for (var comment in responseForComment.data) {
//           if (
//             responseForComment.data[comment].articleId ===
//             responseForBoard.data[article].id
//           ) {
//             comments.push(responseForComment.data[comment].id);
//           }
//         }
//         boardData[article]["comments"] = comments;
//       }
//     }

//     yield put(boardActions.getBoardSuccessAsync(boardData));
//   } catch (e) {
//     yield put(boardActions.getBoardFailedAsync(e.message));
//   }
// }