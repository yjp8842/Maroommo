import React, { useEffect, useRef, useState } from "react";

import { Grid } from '@mui/material';
import { Box } from '@mui/system';

import PageIcon from '../MyRoom/MyRoomItem/PageIcon';
import { Link, useParams } from 'react-router-dom';
import GroupProfile from './GroupRoomItem/GroupProfile';
import CalendarBox from '../Calendar/Calendar';
import Chat from './Chat/Chat';
import { useSelector, useDispatch,  } from "react-redux";
import api from "../../utils/axiosInstance";
import GroupMemberList from './GroupRoomItem/GroupMemberList';
import MenuBtn from './GroupRoomItem/MenuBtn';
import { userInfoActions} from "../../slice/userInfoSlice";
import { groupInfoActions} from "../../slice/groupInfoSlice";

const GroupChat = () => {
  const dispatch = useDispatch();
	const params = useParams();
  const groupId = params.groupId;

  useEffect(() => {

    api.get(`/room/${groupId}/${user.id}`)
    .then((res) => {    
      console.log("이동!")
      dispatch(groupInfoActions.saveGroupInfo(res.data.moveRoomInfo))
    })
    .catch((err) => {
      console.log(err);
    });        
  }, [groupId])

  const {user, group} = useSelector((state) => ({
    user: state.userInfoReducers.user,
    group: state.groupInfoReducers.group
  }))

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
          <Link to={`/myroom`}><PageIcon /></Link>
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
              return (<Link to={`/group/`+room.id}><PageIcon/></Link>)
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
            width: "15vw",
            height: "98vh",
            paddingY: "1vh",
            display: "flex",
            flexDirection: "column",
            alignItems: "center",
            // justifyContent: "center",
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
            flexDirection: 'column'
          }}>

          <Chat />
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
              flexDirection: 'column',
              justifyContent: 'center',
              alignItems: 'center'
            }}>
            <h3>그룹 인원</h3>
            <hr align="center" width="80%"/>   
            {/* {group.users.map((user, index) => {
              return (<GroupMemberList user={user}/>)
            })} */}
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

export default GroupChat;
