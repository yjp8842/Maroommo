import React, { useState, useEffect } from "react";
import RegisterOrEditQuestion from "./RegisterOrEditQuestion";
import { useDispatch, useSelector, shallowEqual } from "react-redux";
// import { articleActions } from "../../../slice/articleSlice";
import { questionArticleActions } from "../../../slice/questionArticleSlice";
import { useLocation, useParams } from "react-router-dom";




function RegisterQuestionPage (props) {
  console.log(props);

  const {id, views, date, editDate, title, content, picture, status} = useSelector((state) =>
  ({
    id: state.questionArticleReducers.id,
    views: state.questionArticleReducers.views,
    date: state.questionArticleReducers.date,
    editDate: state.questionArticleReducers.editDate,  
    title: state.questionArticleReducers.title,
    content: state.questionArticleReducers.content,
    picture: state.questionArticleReducers.picture,
    status: state.questionArticleReducers.status
  }), shallowEqual)

  const formData = new FormData()

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
    const paramsSearch = new URLSearchParams(search).get('search');
    const isRegisterForEdit = paramsSearch.split("=")[1]

    if (isRegisterForEdit === 'true') {
      console.log('true')
      dispatch(questionArticleActions.fetchQuestionArticle(id))
      setIsForUpdate(true);
    } else {
      console.log('false')
    }
    setTitleValue(title);
    setContentValue(content);
  }, []);

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
  const [image, setImage] = useState({name: ""})
  const onImageChange = (event) => {
    console.log("event ======", event)
    console.log("event.target", event.target)
    console.log("event.target.files[0]", event.target.files[0])
    setImage(()=>event.target.files[0])
    console.log("image", image)
  }

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

    const formdata = new FormData();
    formdata.append('picture', image)
    const questionArticleForRegister = {title: title, content: content, categorysub_id: 1, user_id: 'hd', picture: formdata};

    const questionArticleForUpdate = {content: content, id: id, status:status, picture: formdata, title: title, user_id:'hd'};

    if (IsForUpdate) {
      console.log('업데이트 ㄱㄱ')
      dispatch(questionArticleActions.updateQuestionArticle(questionArticleForUpdate)); // 추가
    } else {
      console.log('작성 ㄱㄱ')
      dispatch(questionArticleActions.registerQuestionArticle(questionArticleForRegister));
    } 

    // const questionArticle = { title: title, content: content, views: views, date: date, categorysub_id:1, user_id:'hd', editDate: IsForUpdate ? Date.now() : editDate };
    // if (IsForUpdate) {
    //   dispatch(questionArticleActions.updateQuestionArticle(questionArticle)); // 추가
    // } else {
    //   dispatch(questionArticleActions.registerQuestionArticle(questionArticle));
    // } 
  }


  return (
    <>
      <RegisterOrEditQuestion
        titleValue={title}
        contentValue={content}
        categorysub_id='1'
        user_id='hd'
        handleRegisterChange={onRegisterChange}
        onImageHandler={onImageChange}
        handleSubmit={onSubmitQuestionArticle}
        updateRequest={IsForUpdate}
        formData = {formData}
        picture={image.name}
        status={status}
        />
    </>
    
  )
}

export default RegisterQuestionPage;