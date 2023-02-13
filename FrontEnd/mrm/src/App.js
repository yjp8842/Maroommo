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
// import { Box } from '@mui/system';
// import { Fragment } from 'react';

function App() {
  return (
    <BrowserRouter history={history}>
      <Routes>
        <Route path='/' element={<SignInSide/>}/>
        <Route path='/signup' element={<SignUp/>}/>
        <Route path="/myroom" element={<MyRoom />} />
        <Route path="/group" element={<GroupRoom />} />
        <Route path="/group/chat" element={<GroupChat />} />
        <Route path="/group/board" element={<GroupBoard />} />
        <Route path="/group/question" element={<GroupQnA />} />
        <Route path="/group/board/article/:articleId" element={<ArticlePage/>}/>
        <Route path="/group/question/questionArticle/:questionArticleId" element={<QuestionArticlePage/>}/>
        <Route path="/group/board/register" element={<RegisterPage/>}/>
        <Route path="/group/question/register" element={<RegisterQuestionPage/>}/>

      </Routes>
    </BrowserRouter>

  );
}

export default App;
