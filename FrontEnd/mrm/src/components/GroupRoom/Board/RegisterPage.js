import React, { useState, useEffect } from "react";
import RegisterOrEdit from "./RegisterOrEdit";
import { useDispatch, useSelector, shallowEqual } from "react-redux";
import { articleActions } from "../../../slice/articleSlice";
import { useLocation, useParams } from "react-router-dom";




function RegisterPage (props) {
  console.log(props);

  const {id, views, date, editDate, title, content} = useSelector((state) =>
  ({
    id: state.articleReducers.id,
    views: state.articleReducers.views,
    date: state.articleReducers.date,
    editDate: state.articleReducers.editDate,  
    title: state.articleReducers.title,
    content: state.articleReducers.content,
  }), shallowEqual)

  // const {title} = useSelector((state) => ({
  //   title: state.articleReducers.title,
  // }))
  // const {content} = useSelector((state) => ({
  //   content: state.articleReducers.content,
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
      dispatch(articleActions.fetchArticle(params.articleId))
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
    dispatch(articleActions.changeRegisterInput({ name: name, value: value }));
  };

  const onSubmitArticle = (event) => {
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
    const article = { title: title, content: content, views: views, date: date, editDate: IsForUpdate ? Date.now() : editDate };
    if (IsForUpdate) {
      dispatch(articleActions.updateArticle(article)); // 추가
    } else {
      dispatch(articleActions.registerArticle(article));
    } 
  }


  return (
    <>
      <RegisterOrEdit
        titleValue={title}
        contentValue={content}
        handleRegisterChange={onRegisterChange}
        handleSubmit={onSubmitArticle}
        updateRequest={IsForUpdate}
        />
    </>
  )
}

export default RegisterPage;