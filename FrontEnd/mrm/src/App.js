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
        <Route path="/group/qna" element={<GroupQnA />} />
        <Route path="/group/board/article/:articleId" element={<ArticlePage/>}/>
        <Route path="/group/board/register" element={<RegisterPage/>}/>
      </Routes>
    </BrowserRouter>
  );
}

export default App;
