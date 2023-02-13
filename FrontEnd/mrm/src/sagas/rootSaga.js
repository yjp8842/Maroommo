import { takeEvery, takeLatest } from "redux-saga/effects";
import { articleActions } from "../slice/articleSlice";
import { boardActions } from "../slice/boardSlice";
import { registerArticleAsync, getArticleAsync } from "./articleSaga";
import { getBoardAsync } from "./boardSaga";
import { fetchArticleAsync, updateArticleAsync, deleteArticleAsync } from "./articleSaga";
// import {fetchArticle}
import { commentActions } from "../slice/commentSlice";
import { registerCommentAsync, getCommentsAsync, deleteCommentAsync } from "./commentSaga";

import { questionArticleActions } from "../slice/questionArticleSlice";
import { questionActions } from "../slice/questionSlice";
import { deleteQuestionArticleAsync, fetchQuestionArticleAsync, getQuestionArticleAsync, registerQuestionArticleAsync, updateQuestionArticleAsync } from "./questionArticleSaga";
import { getQuestionAsync } from "./questionSaga";
// import { fetchQuestionArticleAsync, updateQuestionArticleAsync, deleteQuestionArticleAsync } from "./questionArticleSaga";
import { answerActions } from "../slice/answerSlice";
import { registerAnswerAsync, deleteAnswerAsync } from "./answerSaga";
import { updateUserInfoAsync } from "./userInfoSaga";
import { userInfoActions } from "../slice/userInfoSlice";

const { registerArticle, getArticle, fetchArticle, updateArticle, deleteArticle } = articleActions;
const { registerQuestionArticle, getQuestionArticle, fetchQuestionArticle, updateQuestionArticle, deleteQuestionArticle } = questionArticleActions
const {getBoard} = boardActions;
const {getQuestion} = questionActions;
// const {fetchArticle} = articleActions;
const { registerComment, deleteComment } = commentActions;
const { registerAnswer, deleteAnswer } = answerActions;
const { updateUserInfo} = userInfoActions


export default function* rootWatcher() {
  yield takeLatest(registerArticle.type, registerArticleAsync);
  yield takeEvery(getArticle.type, getArticleAsync);
  yield takeEvery(getBoard.type, getBoardAsync);
  yield takeEvery(fetchArticle.type, fetchArticleAsync);
  yield takeLatest(updateArticle.type, updateArticleAsync);
  yield takeLatest(deleteArticle.type, deleteArticleAsync);

  yield takeLatest(registerComment.type, registerCommentAsync);
  // yield takeEvery(getComments.type, getCommentsAsync);
  yield takeLatest(deleteComment.type, deleteCommentAsync);


  yield takeLatest(registerQuestionArticle.type, registerQuestionArticleAsync);
  yield takeEvery(getQuestionArticle.type, getQuestionArticleAsync);
  yield takeEvery(getQuestion.type, getQuestionAsync);

  yield takeEvery(fetchQuestionArticle.type, fetchQuestionArticleAsync);
  yield takeLatest(updateQuestionArticle.type, updateQuestionArticleAsync);
  yield takeLatest(deleteQuestionArticle.type, deleteQuestionArticleAsync);


  yield takeLatest(registerAnswer.type, registerAnswerAsync);
  // yield takeEvery(getQuestionComments.type, getQuestionCommentsAsync);
  yield takeLatest(deleteAnswer.type, deleteAnswerAsync);
  // yield takeLatest(updateUserInfo.type, updateUserInfoAsync);



}

