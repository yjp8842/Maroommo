import React, { useState, useEffect } from "react";
import RegisterOrEdit from "./RegisterOrEdit";
import { useDispatch, useSelector, shallowEqual } from "react-redux";
import { articleActions } from "../../../slice/articleSlice";
import { useLocation, useParams } from "react-router-dom";




function RegisterPage (props) {
  console.log(props);

  const {id, views, date, editDate, title, content, picture} = useSelector((state) =>
  ({
    id: state.articleReducers.id,
    views: state.articleReducers.views,
    date: state.articleReducers.date,
    editDate: state.articleReducers.editDate,  
    title: state.articleReducers.title,
    content: state.articleReducers.content,
    picture: state.articleReducers.picture,
  }), shallowEqual)
  
const formData = new FormData()

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
    const paramsSearch = new URLSearchParams(search).get('search');
    const isRegisterForEdit = paramsSearch.split("=")[1]

    if (isRegisterForEdit === 'true') {
      console.log('true')
      dispatch(articleActions.fetchArticle(id))
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
    dispatch(articleActions.changeRegisterInput({ name: name, value: value }));
  };
  const [image, setImage] = useState({name: ""})
  const onImageChange = (event) => {
    console.log("event ======", event)
    console.log("event.target", event.target)
    console.log("event.target.files[0]", event.target.files[0])
    setImage(()=>event.target.files[0])
    console.log("image", image)
  }

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

    // const articleForRegister = {title: title, content: content, categorysub_id: 1, user_id: 'hd', editDate: IsForUpdate ? Date.now() : editDate };
    // const articleForRegister = {title: title, content: content, categorysub_id: 1, user_id: 'hd', picture: image};
    // const formdata = new FormData();

    const formdata = new FormData();
    formdata.append('picture', image)
    const articleForRegister = {title: title, content: content, categorysub_id: 1, user_id: 'hd', picture: formdata};

    const articleForUpdate = {content: content, id: id, picture: formdata, title: title, user_id:'hd'};

    if (IsForUpdate) {
      console.log('업데이트 ㄱㄱ')
      dispatch(articleActions.updateArticle(articleForUpdate)); // 추가
    } else {
      console.log('작성 ㄱㄱ')
      dispatch(articleActions.registerArticle(articleForRegister));
    } 

  }


  return (
    <>
      <RegisterOrEdit
        titleValue={title}
        contentValue={content}
        categorysub_id='1'
        user_id='hd'
        handleRegisterChange={onRegisterChange}
        onImageHandler={onImageChange}
        handleSubmit={onSubmitArticle}
        updateRequest={IsForUpdate}
        formData = {formData}
        picture={image.name}
        />
    </>
  )
}

export default RegisterPage;