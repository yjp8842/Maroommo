import { combineReducers } from "redux";
import { articleReducers } from "./articleSlice";
import { boardReducers } from "./boardSlice";
import { commentReducers } from "./commentSlice";
import { questionReducers } from "./questionSlice";
import { questionArticleReducers } from "./questionArticleSlice";
// import { questionCommentReducers } from "./questionCommentSlice";
import { answerReducers } from "./answerSlice";
import { userInfoReducers } from "./userInfoSlice";

const rootReducer = combineReducers({articleReducers, boardReducers, commentReducers, questionReducers, questionArticleReducers, answerReducers, userInfoReducers})


export default rootReducer;