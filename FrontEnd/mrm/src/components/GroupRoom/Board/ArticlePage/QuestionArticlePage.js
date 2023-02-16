import React, { useEffect, useRef, useState } from "react";
import { useDispatch, useSelector, shallowEqual } from "react-redux";
import { Link, useParams } from 'react-router-dom';

import { Grid } from '@mui/material';
import { Box } from '@mui/system';

import PageIcon from '../../../MyRoom/MyRoomItem/PageIcon';
import GroupProfile from '../../GroupRoomItem/GroupProfile';
import CalendarBox from '../../../Calendar/Calendar';
import MenuBtn from '../../GroupRoomItem/MenuBtn';
import GroupMemberList from '../../GroupRoomItem/GroupMemberList';

import { questionArticleActions } from "../../../../slice/questionArticleSlice";
import { answerActions } from '../../../../slice/answerSlice'

import QuestionArticleDetail from "./Sections/QuestionArticleDetail";
import Answer from "./Sections/Answer";
import styled from "styled-components";
import RoomModal from "../../../Modal/Group/RoomModal";
import { questionActions } from "../../../../slice/questionSlice";

function QuestionArticlePage() {

  const dispatch = useDispatch();
  const params = useParams();
  const groupId = params.groupId;
  const questionArticleId = params.questionArticleId;

  useEffect(() => {
    dispatch(questionArticleActions.getQuestionArticle(questionArticleId));
    dispatch(answerActions.getAnswers(questionArticleId));
  }, [questionArticleId]);

  // , [{articleId}]);_
// categorysub_id, content, createtime, hit, picture, title, user_id
  const { user, group, id, title, content, createTime, user_id, answers, picture  } = useSelector((state) => ({
    user: state.userInfoReducers.user,
    group: state.groupInfoReducers.group,
    id: state.questionArticleReducers.id,
    title: state.questionArticleReducers.title,
    content: state.questionArticleReducers.content,
    createTime: state.questionArticleReducers.createTime,
    user_id: state.questionArticleReducers.user_id,
    
    answers: state.questionArticleReducers.answers,
    picture: state.questionArticleReducers.picture
  }),
  shallowEqual);
  //여기 보고 있어씀
  let status = useSelector((state) => (
    state.questionArticleReducers.status
  ))
  console.log('questionarticle page 출력 ')
  // console.log(id, title, content)
  console.log(user_id)
  const views = useSelector((state) => state.questionArticleReducers.views);
  // console.log(title)

  // const stateForProps = useSelector((state) =>
  // state.articleReducers)

  const onDeleteClick = () => {
    if (!window.confirm('삭제하시겠습니까?')) return false;
    dispatch(questionArticleActions.deleteQuestionArticle(id))
  } 

  //답변
  const [AnswerValue, setAnswerValue] = useState("");

  const onAnswerChange = (e) => {
    setAnswerValue(e.currentTarget.value);
  };

  const onAnswerSubmit = (e) => {
    e.preventDefault()
    if (
      AnswerValue === '' ||
      AnswerValue === null ||
      AnswerValue === undefined
    ) {
      alert('답변을 입력하세요.');
      return false;
    }
    const answer = {
      // id: 0,
      user_id: user,
      content: AnswerValue,
      date: Date.now(),
      question_id: id,
    };
    dispatch(answerActions.registerAnswer(answer))
  };

  const onDeleteAnswer = (answerId) => {
    dispatch(answerActions.deleteAnswer(answerId))
  };
  const [isOpen, setIsOpen] = useState(false);
  
  const onClickButton = () => {
    setIsOpen(true);
  };


  const [finished, setFinished] = useState(status)
  

  const onChangeStatus = () => {
    if (user.id === user_id) {
      // if (finished === 1) {
      //   setFinished(()=>0)
      //   console.log('ㅎ')
      // } 
      if (finished === 0) {
        setFinished(1)
        console.log('미해결>해결')
        console.log(finished)
      } else if (finished === 1) {
        setFinished(0)
        console.log('해결>미해결')
      }
    } else {
      console.log(user.id,'###', user_id)
      console.log()
      alert('작성자만 변경할 수 있습니다.')
    }
    console.log('상태 수정해줘')
    // console.log(statusForUpdate)
    const statusForUpdate = {
      id: id, status: finished, user_id: user_id
    };
    dispatch(questionArticleActions.updateStatus(statusForUpdate))

  }

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
          <Link to={`/myroom`}><PageIcon room={{}}/></Link>
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
            {user.myRooms.map((room, index) => {
              return (<Link to={`/group/`+room.id}><PageIcon room={room}/></Link>)
            })}
          </Box>
          <Box>
            <AppWrap>
              <Button onClick={onClickButton}>+</Button>
              {isOpen && (<RoomModal
                open={isOpen}
                onClose={() => {
                  setIsOpen(false);
                }}
              />)}
            </AppWrap>
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
            width: "288px",
            height: "98vh",
            paddingY: "1vh",
            display: "flex",
            flexDirection: "column",
            alignItems: "center",
            backgroundColor: "#ebe5d1",
          }}>
          <GroupProfile />
          {/* 해당 groupId의 경로로 이동할 수 있도록 변경해야함 */}
          <Link to={`/group/${groupId}`}><MenuBtn name={"Home"} /></Link>
          <Link to={`/group/${groupId}/chat`}><MenuBtn name={"채팅방"} /></Link>
          <Link to={`/group/${groupId}/openvidu`}><MenuBtn name={"화상채팅방"} /></Link>
          <Link to={`/group/${groupId}/board`}><MenuBtn name={"게시판"} /></Link>
          <Link to={`/group/${groupId}/question`}><MenuBtn name={"Q&A"} /></Link>
        </Box>
        
        <Box
          sx={{
            display: 'flex',
            flexDirection: 'column',
            alignItems: 'center',
          }}>

          <Box sx={{mt:5}}>
            <QuestionArticleDetail
              groupId={groupId}
              id={id}
              title={title}
              content={content}
              views={views}
              createTime={createTime}
              user_id={user_id}
              status={status}
              finished={finished}
              nickname={user.nickname}
              answers={answers}
              picture={picture}
              handleDeleteClick={onDeleteClick}
              handleAnswer={<Answer
              answer={AnswerValue}
              handleAnswerChange={onAnswerChange}
              handleAnswerSubmit={onAnswerSubmit}/>}
              loadAnswers={answers}
              deleteAnswer={onDeleteAnswer}
              onChangeStatus={onChangeStatus}
              />
          </Box>
        </Box>   

        <Box
          sx={{
            width: "288px",
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
              flexDirection: 'column',
              justifyContent: 'center',
              alignItems: 'center'
            }}>
            <h3>그룹 인원</h3>
            <hr align="center" width="80%"/>     
            {group.users 
            ? group.users.map((user, index) => {
              return (<GroupMemberList user={user}/>)
            })
            : <div></div>
            }
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
              justifyContent: 'center',
              ':hover': {
                cursor: 'pointer'
              }
            }}>
            <h2>탈퇴하기</h2>
          </Box>
        </Box>
      </Box>
    </Grid>
  )
}
// {match.params.articleId}

export default QuestionArticlePage;





const Button = styled.button`
  font-size: 40px;
  padding: 10px 20px;
  border: none;
  background-color: #ffffff;
  border-radius: 10px;
  color: black;
  font-weight: 200;
  cursor: pointer;
  &:hover {
    background-color: #fac2be;
  }
`;

const AppWrap = styled.div`
  text-align: center;
  margin: 50px auto;
`;