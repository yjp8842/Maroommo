import React, {useEffect, useState} from "react";
import { useDispatch, useSelector, shallowEqual } from "react-redux";
// import { articleActions } from "../../../../slice/articleSlice";
import { questionArticleActions } from "../../../../slice/questionArticleSlice";
import { useParams } from "react-router-dom";
// import ArticleDetail from "./Sections/ArticleDetail";
import QuestionArticleDetail from "./Sections/QuestionArticleDetail";

// import Comment from "./Sections/Comment";
import Answer from "./Sections/Answer";
// import { commentActions } from "../../../../slice/commentSlice";
// import { questionCommentActions } from "../../../../slice/questionCommentSlice";
import {answerActions} from '../../../../slice/answerSlice'

function QuestionArticlePage() {

  const params = useParams();
  console.log(params)
  console.log(params.questionArticleId)
  const dispatch = useDispatch();

  useEffect(() => {
    dispatch(questionArticleActions.getQuestionArticle(params.questionArticleId));
    dispatch(answerActions.getAnswers(params.questionArticleId));
  }, [params.questionArticleId]);
  console.log('getQuestionArticle 호출')

  // , [{articleId}]);_
// categorysub_id, content, createtime, hit, picture, title, user_id
  const { id, title, content, date, user_id, status, answers  } = useSelector((state) => ({
    id: state.questionArticleReducers.id,
    title: state.questionArticleReducers.title,
    content: state.questionArticleReducers.content,
    createTime: state.questionArticleReducers.createTime,
    user_id: state.questionArticleReducers.user_id,
    status: state.questionArticleReducers.status,
    answers: state.questionArticleReducers.answers,
  }),
  shallowEqual);
  // const date = useSelector((state) => state.articleReducers.date);
  
  const views = useSelector((state) => state.questionArticleReducers.views);
  // console.log(title)

  // const stateForProps = useSelector((state) =>
  // state.articleReducers)

  const onDeleteClick = () => {
    if (!window.confirm('삭제하시겠습니까?')) return false;
    dispatch(questionArticleActions.deleteQuestionArticle(id))
  } 

  //댓글
  const [AnswerValue, setAnswerValue] = useState("");

  

  const onAnswerChange = (e) => {
    setAnswerValue(e.currentTarget.value);
  };

  const onAnswerSubmit = () => {
    if (
      AnswerValue === '' ||
      AnswerValue === null ||
      AnswerValue === undefined
    ) {
      alert('댓글을 입력하세요.');
      return false;
    }
    const answer = {
      id: 0,
      content: AnswerValue,
      date: Date.now(),
      questionArticleId: id,
    };
    dispatch(answerActions.registerQuestionComment(answer))
  };

  const onDeleteAnswer = (answerId) => {
    dispatch(answerActions.deleteAnswer(answerId))
  };

  return (
    <div>
      <div>
        <QuestionArticleDetail
          id={id}
          title={title}
          content={content}
          views={views}
          date={date}
          user_id={user_id}
          status={status}
          answers={answers}
          handleDeleteClick={onDeleteClick}
          handleAnswer={<Answer
            answer={AnswerValue}
            handleAnswerChange={onAnswerChange}
            handleAnswerSubmit={onAnswerSubmit}/>}
          loadAnswers={answers}
          deleteAnswer={onDeleteAnswer}
          />
      </div>
      {/* <div>
        <Link to={`/group/board/register?isForEdit=true`}>
          <button>수정</button>
        </Link>
        <Link>
          <button>삭제</button>
        </Link>
      </div> */}
    </div>
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