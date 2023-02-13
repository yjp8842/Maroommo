import React, { useState, useEffect } from "react";
import RegisterOrEditQuestion from "./RegisterOrEditQuestion";
import { useDispatch, useSelector, shallowEqual } from "react-redux";
// import { articleActions } from "../../../slice/articleSlice";
import { questionArticleActions } from "../../../slice/questionArticleSlice";
import { useLocation, useParams } from "react-router-dom";




function RegisterQuestionPage (props) {
  console.log(props);

  const {id, views, date, editDate, title, content, } = useSelector((state) =>
  ({
    id: state.questionArticleReducers.id,
    views: state.questionArticleReducers.views,
    date: state.questionArticleReducers.date,
    editDate: state.questionArticleReducers.editDate,  
    title: state.questionArticleReducers.title,
    content: state.questionArticleReducers.content,
  }), shallowEqual)

  // const {title} = useSelector((state) => ({
  //   title: state.questionArticleReducers.title,
  // }))
  // const {content} = useSelector((state) => ({
  //   content: state.questionArticleReducers.content,
  // }))

  const dispatch = useDispatch();

  const [TitleValue, setTitleValue] = useState(title)
  const [ContentValue, setContentValue] = useState(content)

  // 새 글인지 수정인지
  const [IsForUpdate, setIsForUpdate] = useState(false);
  const params = useParams();
  const search = useLocation();

  //여기 체크
  useEffect(() => {
    const searchParams = new URLSearchParams(search);
    if (searchParams.get("isForEdit") === 'true') {
      dispatch(questionArticleActions.fetchQuestionArticle(params.questionArticleId))
      setIsForUpdate(true);
    }
    setTitleValue(title);
    setContentValue(content);
  }, [id]);

  // const onTitleChange = (event) => {
  //   setTitleValue(event.currentTarget.value)
  // }
  // console.log(TitleValue)
  // const onContentChange = (event) => [
  //   setContentValue(event.currentTarget.value)
  // ]
  // console.log(ContentValue)
  // 수정
  const onRegisterChange = (event) => {
    const { name, value } = event.target;
    dispatch(questionArticleActions.changeQuestionRegisterInput({ name: name, value: value }));
  };

  const onSubmitQuestionArticle = (event) => {
    event.preventDefault();

    if (title === "" || title === null || title === undefined) {
      alert("제목을 작성하십시오.");
      return false;
    }
    if (
      content === "" ||
      content === null ||
      content === undefined
    ) {
      alert("내용을 작성하십시오.");
      return false;
    }
    const questionArticle = { title: title, content: content, views: views, date: date, categorysub_id:1, user_id:'hd', editDate: IsForUpdate ? Date.now() : editDate };
    if (IsForUpdate) {
      dispatch(questionArticleActions.updateQuestionArticle(questionArticle)); // 추가
    } else {
      dispatch(questionArticleActions.registerQuestionArticle(questionArticle));
    } 
  }


  return (
    <>
      <RegisterOrEditQuestion
        titleValue={title}
        contentValue={content}
        categorysub_id='1'
        user_id='hd'
        handleRegisterChange={onRegisterChange}
        handleSubmit={onSubmitQuestionArticle}
        updateRequest={IsForUpdate}
        />
    </>
    
  )
}

export default RegisterQuestionPage;