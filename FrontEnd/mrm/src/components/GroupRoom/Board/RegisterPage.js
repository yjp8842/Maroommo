import React, { useEffect, useRef, useState } from "react";

import { Grid } from '@mui/material';
import { Box } from '@mui/system';

import PageIcon from '../../MyRoom/MyRoomItem/PageIcon';
import GroupProfile from '../GroupRoomItem/GroupProfile';
import CalendarBox from '../../Calendar/Calendar';
import MenuBtn from '../GroupRoomItem/MenuBtn';
import GroupMemberList from '../GroupRoomItem/GroupMemberList';

import RegisterOrEdit from "./RegisterOrEdit";
import { useDispatch, useSelector, shallowEqual } from "react-redux";
import { Link, useParams,useLocation,useNavigate } from 'react-router-dom';

import { articleActions } from "../../../slice/articleSlice";
import styled from "styled-components";
import RoomModal from "../../Modal/Group/RoomModal";

function RegisterPage (props) {
  console.log("RegisterPage");
  console.log(props);
  const navigate = useNavigate();

  const dispatch = useDispatch();
	const params = useParams();
  const groupId = params.groupId;

  const {user, id, group, views, date, editDate, title, content, picture} = useSelector((state) =>
  ({
    user: state.userInfoReducers.user,
    group: state.groupInfoReducers.group,
    id: state.articleReducers.id,
    views: state.articleReducers.views,
    date: state.articleReducers.date,
    editDate: state.articleReducers.editDate,  
    title: state.articleReducers.title,
    content: state.articleReducers.content,
    picture: state.articleReducers.picture,
  }), shallowEqual)
  

  const [TitleValue, setTitleValue] = useState('')
  const [ContentValue, setContentValue] = useState('')
  const [isOpen, setIsOpen] = useState(false);
  
  const onClickButton = () => {
    setIsOpen(true);
  };

  // 새 글인지 수정인지
  const [IsForUpdate, setIsForUpdate] = useState(false);
  const search = useLocation();

  //여기 체크
  useEffect(() => {
    const paramsSearch = new URLSearchParams(search).get('search');
    const isRegisterForEdit = paramsSearch.split("=")[1]

    if (isRegisterForEdit === 'true') {
      console.log('true')
      setTitleValue(title);
      setContentValue(content);
      dispatch(articleActions.fetchArticle(id))
      setIsForUpdate(true);
    } else {
      setTitleValue('');
      setContentValue('');
      console.log('false')
    }
    // setTitleValue(title);
    // setContentValue(content);
  }, []);

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

  const formdata = new FormData();
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

    // const formdata = new FormData();
    formdata.append('picture', image)
    console.log(formdata, '이건 폼데이터')
    const articleForRegister = {
      content: content, picture: formdata, title: title, user_id: user.id, groupId:groupId, navigate:navigate
    };

    const articleForUpdate = {
      content: content, id: id, picture: formdata, title: title, user_id: user.id, groupId:groupId, navigate:navigate
    };

    if (IsForUpdate) {
      console.log('업데이트 ㄱㄱ');
      console.log(articleForUpdate);

      dispatch(articleActions.updateArticle(articleForUpdate)); // 추가
      // navigate(`/group/${groupId}/board`);
    } else {
      
      dispatch(articleActions.registerArticle(articleForRegister));
      // navigate(`/group/${groupId}/board`);
    } 

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
        <Box
          sx={{
            mt: 5
          }}>
            <RegisterOrEdit
              id={id}
              titleValue={title}
              contentValue={content}
              groupId={groupId}
              userId={user.id}
              handleRegisterChange={onRegisterChange}
              onImageHandler={onImageChange}
              handleSubmit={onSubmitArticle}
              updateRequest={IsForUpdate}
              formData = {formdata}
              picture={image.name}
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

export default RegisterPage;
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