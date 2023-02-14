// import { Fragment } from 'react';
import React from 'react';

import { Grid } from '@mui/material';
import { Box } from '@mui/system';

import HomePage from '../MyRoom/MyRoomItem/PageIcon';
import { Link } from 'react-router-dom';
import GroupProfile from './GroupRoomItem/GroupProfile';
import CalendarBox from '../Calendar/Calendar';
import HomeBtn from './GroupRoomItem/HomeBtn';
import ChatRoom from './GroupRoomItem/ChatRoom';
import { NavItem } from './GroupRoomItem/Category';

import './GroupRoomItem/Category.css';

import QuestionList from './Board/ArticlePage/Sections/QuestionList';
import { useNavigate } from 'react-router-dom';

import { useEffect } from 'react';

import { useDispatch, useSelector } from 'react-redux';
import { questionActions } from '../../slice/questionSlice';


const GroupQnA = () => {

  const navigate = useNavigate();
  const onQuestionArticleTitleClick = (id) => {
    {/* 룸아이디 넣는 식으로 수정해야함 */}
    const path = `/group/1/question/questionArticle/${id}`;
    navigate(path)
  }

  const dispatch = useDispatch();
  useEffect(() => {
    dispatch(questionActions.getQuestion());
  }, [dispatch]);

  const {question, isLoading, isSuccess, error } = 
  useSelector((state) => ({
    question: state.questionReducers.question,
    isLoading: state.questionReducers.isLoading,
    isSuccess: state.questionReducers.isSuccess,
    error: state.questionReducers.error}));
    // console.log('333333333')
    // console.log(question.content)
    // console.log(question.content.length)

  return (
    <Grid container>
      <Box
        sx={{
          width: "5vw",
          minHeight: "100vh",
          display: "flex",
          flexDirection: "column",
          alignItems: "center",
          backgroundColor: "#4A4A4A",
        }}>
        <Box>
          {/* 해당 userId의 경로로 이동할 수 있도록 변경해야함 */}
          <Link to={`/myroom`}><HomePage /></Link>
        </Box>
        <Box
          sx={{
            width: "4vw",
            height: "5px",
            backgroundColor: "#FFFFFF",
            borderRadius: "10px"
          }}>
        </Box>
        <Box
          sx={{
            height: "100%",
            display: "flex",
            flexDirection: "column",
            justifyContent: "space-between"
          }}>
          <Box>
            <HomePage />
          </Box>
          <Box>
            <Box
              sx={{
                width: "4rem",
                height: "4rem",
                marginTop: "25px",
                marginBottom: "25px",
                backgroundColor: "#FFFFFF",
                borderRadius: "15px",
                transform: "rotate(45deg)",
                boxShadow: "5px 5px 8px rgba(0, 0, 0, 0.35)",
                ":hover": {
                  transform: "rotate(0)",
                  transition: "0.8s",
                }
              }}>
            </Box>
          </Box>
        </Box>
      </Box>

      <Box
        sx={{
          width: "95vw",
          display: "flex",
          justifyContent: "space-between",
        }}>
        <Box
          sx={{
            width: "15vw",
            height: "98vh",
            paddingY: "1vh",
            display: "flex",
            flexDirection: "column",
            alignItems: "center",
            backgroundColor: "#ebe5d1",
          }}>
          <GroupProfile />
          {/* 해당 groupId의 경로로 이동할 수 있도록 변경해야함 */}
          {/* 룸아이디 넣는 식으로 수정해야함 */}
          <Link to={`/group/1`}><HomeBtn /></Link>
          <Link to={`/group/1/chat`}><ChatRoom /></Link>
          

          <NavItem>
            {/** 하위에 있는 메뉴가 열립니다. **/}
            {/* <ul> */}
            <div className='category-box'>
              {/* 룸아이디 넣는 식으로 수정해야함 */}
              <Link to={`/group/1/board`}><li>게시판</li></Link> 
              <li>화상회의</li>  
              {/* 룸아이디 넣는 식으로 수정해야함 */}
              <Link to={`/group/1/question`}><li>Q&A</li></Link>   
            </div>
            {/* </ul> */}
          </NavItem>

        </Box>
        
        <Box
          sx={{
            display: 'flex',
            flexDirection: 'column'
          }}>

          <Box sx={{mt:5}}>
            <h1>Q&A</h1>
            <br></br>
          {/* 완료여부 포함된 board..어떻게 넣지? */}
          <div style={{ width: "80%", margin: "3rem auto" }}>
            {error ? (
              <h2>에러 발생: {error}</h2>
            ) : isSuccess && question.content.length <= 0 ? (
              <p> 조회할 내용이 없습니다. </p>
            ) 
            : isSuccess && question.content.length > 0 ? (
              <QuestionList 
                question={question}
                // handleDeleteClick={onDeleteClick}
                handleQuestionArticleTitleClick={onQuestionArticleTitleClick} />
            ) : (
              <p> 목록을 불러오는 중입니다. </p>
            )}
            </div>
            <Link to='/group/1/question/register?isForEdit=false'>
              <button>글쓰기</button>
            </Link>
          </Box>
        </Box>

        <Box
          sx={{
            width: "15vw",
            height: "98vh",
            paddingY: "1vh",
            display: "flex",
            flexDirection: "column",
            alignItems: "center",
            backgroundColor: "#ebe5d1",
          }}>
          <CalendarBox />
          <Box
            sx={{
              width: "250px",
              height: "550px",
              marginTop: "20px",
              paddingY: '20px',
              borderRadius: "30px",
              backgroundColor: "#FFFFFF",
              boxShadow: "5px 5px 8px rgba(0, 0, 0, 0.35)",
              display: 'flex',
              justifyContent: 'center'
            }}>
            <h2>그룹 인원</h2>
          </Box>
          <Box
            sx={{
              width: "250px",
              height: "80px",
              marginTop: "20px",
              borderRadius: "30px",
              backgroundColor: "#FFFFFF",
              border: '5px solid #c45c5c',
              boxShadow: "5px 5px 8px rgba(0, 0, 0, 0.35)",
              display: 'flex',
              alignItems: 'center',
              justifyContent: 'center'
            }}>
              <h2>탈퇴하기</h2>
          </Box>
        </Box>
      </Box>
    </Grid>
  );
};

export default GroupQnA;