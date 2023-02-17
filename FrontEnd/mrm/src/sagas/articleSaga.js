import { call, put } from "redux-saga/effects";
import { articleActions } from "../slice/articleSlice";
import history from "../utils/history";
import axios from "axios";
import api from "../utils/axiosInstance";



export function* registerArticleAsync(action) {
  const data = action.payload;
  console.log('----------');
  console.log('articleSage data 출력 : ',data)
  console.log('----------');
  const response = yield api.post(
    `/board?room_id=${data.groupId}&content=${data.content}&title=${data.title}`,
    data.picture
  )

  alert("저장되었습니다.");
  console.log('----------');
  console.log('response 출력 : ',response)
  console.log('----------');


  data.navigate(`/group/${data.groupId}/board`);

  // history.push(`/group/${data.room_id}/board/article/${response.data.newBoard.id}`, response.data.newBoard.id);
  // // eslint-disable-next-line no-restricted-globals
  // location.reload();
}


export function* getArticleAsync(action) {
  const id = action.payload;
  const response = yield api.get(`/board/${id}`)
  console.log('보드 상세조회 출력 : ',response.data)
  yield put(articleActions.getArticleAsync(response.data));
}

export function* fetchArticleAsync(action) {
  console.log(action);
  const id = action.payload;
  const response = yield api.get(`/board/${id}`)

  yield put(articleActions.getArticleAsync(response.data));


}

export function* updateArticleAsync(action) {
  const article = action.payload;
  console.log('updateArticleAsync article 호출 : ', article)
  const response = yield api.post(
    `/board/update?id=${article.id}&content=${article.content}&title=${article.title}`,
    article.picture
  )
  
  alert("저장되었습니다.");
  console.log(response);
  console.log(action);

  article.navigate(`/group/${article.groupId}/board/article/${article.id}`);
  // history.push(`/group/1/board/article/${response.data.board.id}`, response.data.board.id);
  // // eslint-disable-next-line no-restricted-globals
  // location.reload();
}

export function* deleteArticleAsync(action) {
  const data = action.payload;
  // const BASE_URL = "https://i8a406.p.ssafy.io";
  // 현재 접속 아이디, 글의 아이디를 받아서
  console.log('삭제해!!!')
  // 현재 상태에서 접속된 유저 아이디 받아서 밑에 넣어야됨
//   if (user_id === article.user_id) {
// yield Axios.delete(BASE_URL + `/api/board/${id}/hd`);

//   alert("삭제되었습니다.");

//   history.push(`/group/board`);
//   } else {
//     alert('자신의~~ ')
//   }
  // yield Axios.delete(BASE_URL + `/api/board/${id}/hd`);

  // 아이디 값 불러오게 바꿔!!!
  yield api.delete(`/board/${data.id}`)
  alert("삭제되었습니다.");

  data.navigate(`/group/${data.groupId}/board`)
  // history.push(`/group/1/board`);
  // // eslint-disable-next-line no-restricted-globals
  // location.reload();
}