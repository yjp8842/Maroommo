import './App.css';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import MyRoom from './components/MyRoom/MyRoom';
import GroupRoom from './components/GroupRoom/GroupRoom';
import SignInSide from './components/Login/SignIn';
import SignUp from './components/Login/SignUp';
import GroupChat from './components/GroupRoom/GroupChat';
import GroupBoard from './components/GroupRoom/GroupBoard';
import GroupQnA from './components/GroupRoom/GroupQnA';
import ArticlePage from './components/GroupRoom/Board/ArticlePage/ArticlePage';
import RegisterPage from './components/GroupRoom/Board/RegisterPage';
import QuestionArticlePage from './components/GroupRoom/Board/ArticlePage/QuestionArticlePage';
import RegisterQuestionPage from './components/GroupRoom/Board/RegisterQuestionPage';
import history from './utils/history';

import FindIdPage from './components/Login/FindId';
import FindPwdPage from './components/Login/FindPwd';
import ModifyPwdPage from './components/Login/ModifyPwd'
import SuccessPage from './components/Login/SuccessPage';
// import { Box } from '@mui/system';
// import { Fragment } from 'react';

function App() {
  return (
    <BrowserRouter history={history}>
      <Routes>
        <Route path='/' element={<SignInSide/>}/>
        <Route path='/signup' element={<SignUp/>}/>
        <Route path="/myroom" element={<MyRoom />} />
        <Route path="/group/:groupId" element={<GroupRoom />} />
        <Route path="/group/:groupId/chat" element={<GroupChat />} />
        <Route path="/group/1/board" element={<GroupBoard />} />
        <Route path="/group/:groupId/question" element={<GroupQnA />} />
        <Route path="/group/:groupId/board/article/:articleId" element={<ArticlePage/>}/>
        <Route path="/group/:groupId/question/questionArticle/:questionArticleId" element={<QuestionArticlePage/>}/>
        <Route path="/group/:groupId/board/register" element={<RegisterPage/>}/>
        <Route path="/group/:groupId/question/register" element={<RegisterQuestionPage/>}/>
        <Route path='/findId' element={<FindIdPage/>}/>
        <Route path='/findPwd' element={<FindPwdPage/>}/>
        <Route path='/modifyPwd' element={<ModifyPwdPage/>}/>
        <Route path="/oauth2/social/success" element={<SuccessPage/>}/>

      </Routes>
    </BrowserRouter>

  );
}

export default App;
