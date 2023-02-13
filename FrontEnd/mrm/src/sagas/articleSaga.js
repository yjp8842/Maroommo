import { call, put } from "redux-saga/effects";
// import Axios from "axios";
import { articleActions } from "../slice/articleSlice";
import history from "../utils/history";
import axios from "axios";
import api from "../utils/axiosInstance";


export function* registerArticleAsync(action) {
  const data = action.payload;
  console.log(data)
  // console.log(data.categorysub_id)
  // console.log(data.title)
  // console.log(data.picture.nam)
  // const BASE_URL = "https://i8a406.p.ssafy.io";
  // const response = yield Axios.post(BASE_URL + `/api/board/categorySub_id=${data.categorysub_id}&content=${data.content}&title=${data.title}&user_id=${data.user_id}`, data );
  // const headers= { "Content-Type": "multipart/form-data"}
  // const response = yield Axios.post(BASE_URL + `/api/board?categorySub_id=${data.categorysub_id}&content=${data.content}&title=${data.title}&user_id=${data.user_id}`, param, headers);

  // const response = yield axios({
  //   method:'post',
  //   url: BASE_URL + `/api/board?categorySub_id=${data.categorysub_id}&content=${data.content}&title=${data.title}&user_id=${data.user_id}`,
  //   data: data.picture,
  //   // headers: { "Content-Type": "multipart/form-data"}
  // })

  const response = yield api.post(
    `/board?categorySub_id=${data.categorysub_id}&content=${data.content}&title=${data.title}&user_id=${data.user_id}`,
    data.picture
  )

  alert("저장되었습니다.");
  console.log(response)
  console.log(response.data.newBoard.id)
  // console.log(data.picture, data.picture.type);

  // history.push(`/article/${response.data.id}`);

  history.push(`/group/board/article/${response.data.newBoard.id}`, response.data.newBoard.id);
}


export function* getArticleAsync(action) {
  const id = action.payload;
  // const BASE_URL = "https://i8a406.p.ssafy.io";

  // const response = yield Axios.get(BASE_URL + `/api/board/${id}`);

  // const request = yield Axios.put(`/api/board/${id}`, {
  //   ...response.data,
  //   views: parseInt(response.data.views) + 1,
  // });
  const response = yield api.get(`/board/${id}`)
  console.log('보드갸져와')
  console.log(response.data)
  yield put(articleActions.getArticleAsync(response.data));
}

export function* fetchArticleAsync(action) {
  console.log(action);
  const id = action.payload;
  // const BASE_URL = "https://i8a406.p.ssafy.io";


  // const response = yield Axios.get(BASE_URL + `/api/board/${id}`);
  const response = yield api.get(`/board/${id}`)

  yield put(articleActions.getArticleAsync(response.data));


}

export function* updateArticleAsync(action) {
  const article = action.payload;
  // const BASE_URL = "https://i8a406.p.ssafy.io";

  console.log('updateasync호출')
  console.log(article)
  // const response = yield Axios.patch(
  //   BASE_URL + `/api/board`,
  //   article
  // );
  const response =
  // yield api.post(`/board/update`, article);
  yield api.post(
    `/board/update?id=${article.id}&content=${article.content}&title=${article.title}&user_id=${article.user_id}`,
    article.picture
  )

  
  alert("저장되었습니다.");

  console.log(response.data.board);

  // history.push(`/article/${response.data.id}`);

  history.push(`/group/board/article/${response.data.board.id}`, response.data.board.id);
}

export function* deleteArticleAsync(action) {
  const id = action.payload;
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
  yield api.delete(`/board/${id}/hd`)
  alert("삭제되었습니다.");

  history.push(`/group/board`);
}