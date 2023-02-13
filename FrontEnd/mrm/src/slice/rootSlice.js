import { combineReducers } from "redux";
import { articleReducers } from "./articleSlice";
import { boardReducers } from "./boardSlice";
import { commentReducers } from "./commentSlice";
import { questionReducers } from "./questionSlice";
import { questionArticleReducers } from "./questionArticleSlice";
// import { questionCommentReducers } from "./questionCommentSlice";
import { answerReducers } from "./answerSlice";
import { userInfoReducers } from "./userInfoSlice";

import { persistReducer } from "redux-persist";
import storage from "redux-persist/lib/storage";

const persistConfig = {
  key: 'root',
  storage,

}


export const rootReducer = combineReducers({articleReducers, boardReducers, commentReducers, questionReducers, questionArticleReducers, answerReducers, userInfoReducers})
export default persistReducer(persistConfig, rootReducer);

// export default rootReducer;

// export const persistReducer(persistConfig, rootReducer);