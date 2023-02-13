import { call, put } from "redux-saga/effects";
import Axios from "axios";
import { questionActions } from "../slice/questionSlice";
import api from "../utils/axiosInstance";


export function* getQuestionAsync() {
  // const BASE_URL = "https://i8a406.p.ssafy.io";

  try {
    const response = yield api.get(`/question?categorySub_id=1&size=10`);
    console.log(response);
    yield put(questionActions.getQuestionSuccessAsync(response.data));
  } catch (e) {
    yield put(questionActions.getQuestionFailedAsync(e.message));
  }
}

