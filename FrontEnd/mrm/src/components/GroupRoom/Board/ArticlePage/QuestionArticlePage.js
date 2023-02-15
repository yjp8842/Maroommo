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
  const { user, group, id, title, content, createTime, user_id, status, answers, picture  } = useSelector((state) => ({
    user: state.userInfoReducers.user,
    group: state.groupInfoReducers.group,
    id: state.questionArticleReducers.id,
    title: state.questionArticleReducers.title,
    content: state.questionArticleReducers.content,
    createTime: state.questionArticleReducers.createTime,
    user_id: state.questionArticleReducers.user_id,
    status: state.questionArticleReducers.status,
    answers: state.questionArticleReducers.answers,
    picture: state.questionArticleReducers.picture
  }),
  shallowEqual);
  console.log('questionarticle page 출력 ')
  console.log(id, title, content)
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
      user_id: 'hd',
      content: AnswerValue,
      date: Date.now(),
      question_id: id,
    };
    dispatch(answerActions.registerAnswer(answer))
  };

  const onDeleteAnswer = (answerId) => {
    dispatch(answerActions.deleteAnswer(answerId))
  };

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
              answers={answers}
              picture={picture}
              handleDeleteClick={onDeleteClick}
              handleAnswer={<Answer
              answer={AnswerValue}
              handleAnswerChange={onAnswerChange}
              handleAnswerSubmit={onAnswerSubmit}/>}
              loadAnswers={answers}
              deleteAnswer={onDeleteAnswer}
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
            {group.users.map((user, index) => {
              return (<GroupMemberList user={user}/>)
            })}
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







// import React, {useEffect, useState} from "react";
// import { useDispatch, useSelector, shallowEqual } from "react-redux";
// // import { articleActions } from "../../../../slice/articleSlice";
// import { questionArticleActions } from "../../../../slice/questionArticleSlice";
// import { useParams } from "react-router-dom";
// // import ArticleDetail from "./Sections/ArticleDetail";
// import QuestionArticleDetail from "./Sections/QuestionArticleDetail";

// // import Comment from "./Sections/Comment";
// import QuestionComment from "./Sections/QuestionComment";
// // import { commentActions } from "../../../../slice/commentSlice";
// import { questionCommentActions } from "../../../../slice/questionCommentSlice";


// function QuestionArticlePage() {

//   const params = useParams();
//   console.log(params)
//   console.log(params.questionArticleId)
//   const dispatch = useDispatch();

//   useEffect(() => {
//     dispatch(questionArticleActions.getQuestionArticle(params.questionArticleId));
//     dispatch(questionCommentActions.getQuestionComments(params.questionArticleId));
//   }, [params.questionArticleId]);

//   // , [{articleId}]);_
// // categorysub_id, content, createtime, hit, picture, title, user_id
//   const { id, title, content, date, user_id, status, answers  } = useSelector((state) => ({
//     id: state.questionArticleReducers.id,
//     title: state.questionArticleReducers.title,
//     content: state.questionArticleReducers.content,
//     date: state.questionArticleReducers.date,
//     user_id: state.questionArticleReducers.user_id,
//     status: state.questionArticleReducers.status,
//     answers: state.questionArticleReducers.answers,
//   }),
//   shallowEqual);
//   // const date = useSelector((state) => state.articleReducers.date);
  
//   const views = useSelector((state) => state.questionArticleReducers.views);
//   // console.log(title)

//   // const stateForProps = useSelector((state) =>
//   // state.articleReducers)

//   const onDeleteClick = () => {
//     if (!window.confirm('삭제하시겠습니까?')) return false;
//     dispatch(questionArticleActions.deleteQuestionArticle(id))
//   } 

//   //댓글
//   const [QuestionCommentValue, setQuestionCommentValue] = useState("");

//   const questionComments = useSelector((state) =>
//   state.questionCommentReducers.questionComments);
//   console.log(questionComments)

//   const onQuestionCommentChange = (e) => {
//     setQuestionCommentValue(e.currentTarget.value);
//   };

//   const onQuestionCommentSubmit = () => {
//     if (
//       QuestionCommentValue === '' ||
//       QuestionCommentValue === null ||
//       QuestionCommentValue === undefined
//     ) {
//       alert('댓글을 입력하세요.');
//       return false;
//     }
//     const questionComment = {
//       id: 0,
//       content: QuestionCommentValue,
//       date: Date.now(),
//       questionArticleId: id,
//     };
//     dispatch(questionCommentActions.registerQuestionComment(questionComment))
//   };

//   const onDeleteQuestionComment = (questionCommentId) => {
//     dispatch(questionCommentActions.deleteQuestionComment(questionCommentId))
//   };

//   return (
//     <div>
//       <div>
//         <QuestionArticleDetail
//           id={id}
//           title={title}
//           content={content}
//           views={views}
//           date={date}
//           user_id={user_id}
//           status={status}
//           answers={answers}
//           handleDeleteClick={onDeleteClick}
//           handleQuestionComment={<QuestionComment
//             questionComment={QuestionCommentValue}
//             handleQuestionCommentChange={onQuestionCommentChange}
//             handleQuestionCommentSubmit={onQuestionCommentSubmit}/>}
//           loadQuestionComments={questionComments}
//           deleteQuestionComment={onDeleteQuestionComment}
//           />
//       </div>
//       {/* <div>
//         <Link to={`/group/board/register?isForEdit=true`}>
//           <button>수정</button>
//         </Link>
//         <Link>
//           <button>삭제</button>
//         </Link>
//       </div> */}
//     </div>
//       )
//     }
// // {match.params.articleId}

// export default QuestionArticlePage;