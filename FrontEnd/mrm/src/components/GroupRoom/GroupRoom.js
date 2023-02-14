

import React, { useEffect, useRef, useState } from "react";

import { Grid } from '@mui/material';
import { Box } from '@mui/system';

import HomePage from '../MyRoom/MyRoomItem/PageIcon';
import { Link } from 'react-router-dom';
import GroupProfile from './GroupRoomItem/GroupProfile';
import CalendarBox from '../Calendar/Calendar';
import HomeBtn from './GroupRoomItem/HomeBtn';
import ChatRoom from './GroupRoomItem/ChatRoom';
import { NavItem } from './GroupRoomItem/Category';
import TodoBox from './GroupRoomItem/TodoInGroup';
import TimeTableBox from './GroupRoomItem/TimeTableInGroup';

import axios from "axios";
import * as StompJs from "@stomp/stompjs";
import * as SockJS from "sockjs-client";
import { useSelector } from "react-redux";

import './GroupRoomItem/Category.css';
import './GroupRoomItem/TextArea.css';

const ROOM_SEQ = 1;
const USER_ID = "hd";

const GroupRoom = () => {

  // const {roomId, myMemo} = useSelector((state) => ({
  //   roomId: state.userInfoReducers.room.roomId,
  //   myMemo: state.userInfoReducers.user.myMemo.content
  // }))

  const client = useRef({});
  const [groupMemoContent, setGroupMemoContent] = useState("");
  const [myMemoContent, setMyMemoContent] = useState("");

  const handleSetGroupMemo = (e) => {
    setGroupMemoContent(e.target.value);
    publish(e.target.value);
  };

  const handleSetMyMemo = (e) => {
    setMyMemoContent(e.target.value);
    saveMyMemo(e.target.value);
  };

  const saveMyMemo = (content) => {

    const data = {
      userId: USER_ID,
      content: content
    };

    axios.post('https://i8a406.p.ssafy.io/api/my/memo', data).then(response => {
      // axios.get('http://localhost:8080/memo/room/'+ROOM_SEQ).then(response => {
    })
    .catch((err) => {
      console.log("내 메모 저장 중 오류 발생");
    })
  }

  // roomId는 룸버튼을 눌렀을 때 roomId 정보를 store에 저장하는 방식으로 해야 하나..?
  // store에서 가져올 것 : userId, roomId

  useEffect(() => {
    connect();
    initRoom();

    return () => disconnect();
  }, []);

  const initRoom = () => {
    // ROOM_SEQ -> roomId로 바꾸기
    axios.get('https://i8a406.p.ssafy.io/api/memo/room/'+ROOM_SEQ).then(response => {
      // axios.get('http://localhost:8080/memo/room/'+ROOM_SEQ).then(response => {
      var responseMemo = response.data.roomMemo;
      setGroupMemoContent(responseMemo.content);
    })
    .catch((err) => {
      setGroupMemoContent("그룹 메모를 불러오지 못했습니다... 새로고침 해주세요");
    })
    
    // 테스트 용 my memo 불러오기 코드
    axios.get('https://i8a406.p.ssafy.io/api/memo/user/'+USER_ID).then(response => {
      // axios.get('http://localhost:8080/memo/room/'+ROOM_SEQ).then(response => {
      var responseMemo = response.data.userMemo;
      setMyMemoContent(responseMemo);
    })
    .catch((err) => {
      setMyMemoContent("나의 메모를 불러오지 못했습니다... 새로고침 해주세요");
    })
  }

  const connect = () => {
    client.current = new StompJs.Client({
      // brokerURL: "ws://localhost:8080/ws/websocket", // 웹소켓 서버로 직접 접속
      webSocketFactory: () => new SockJS("https://i8a406.p.ssafy.io/api/ws"), // proxy를 통한 접속
      // webSocketFactory: () => new SockJS("http://localhost:8080/ws"), // proxy를 통한 접속
      debug: function (str) {
        console.log(str);
      },
      reconnectDelay: 5000,
      heartbeatIncoming: 4000,
      heartbeatOutgoing: 4000,
      onConnect: () => {
        console.log('success');
        subscribe();
      },
      onStompError: (frame) => {
        console.error(frame);
      },
    });

    client.current.activate();
  };

  const disconnect = () => {
    client.current.deactivate();
  };

  // 메시지 받기 -> time 필요함
  const subscribe = () => {
    // ROOM_SEQ -> roomId로 바꾸기
    client.current.subscribe('/sub/memo/room/'+ROOM_SEQ, (res) => {
      setGroupMemoContent(JSON.parse(res.body).content);
    });
  };

  // 메시지 보내기 + time 보낼 필요없음
  const publish = (req) => {
    if (!client.current.connected) {
      return;
    }

    client.current.publish({
      destination: "/pub/room/memo",
      body: JSON.stringify({
        // ROOM_SEQ -> roomId로 바꾸기
        roomId: ROOM_SEQ,
        content: req
      }),
    });
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
          <Link to={`/group`}><HomeBtn /></Link>
          <Link to={`/group/chat`}><ChatRoom /></Link>
          

          <NavItem>
            {/* 하위 메뉴 열림 */}
            <div className='category-box'>
              {/* 룸아이디 넣는 식으로 수정해야함 */}
              <Link to={`/group/1/board`}><li>게시판</li></Link>  
              <li>화상회의</li>  
              {/* 룸아이디 넣는 식으로 수정해야함 */}
              <Link to={`/group/1/question`}><li>Q&A</li></Link>   
            </div>
          </NavItem>


        </Box>
        
        <Box
          sx={{
            display: 'flex',
            flexDirection: 'column',
            alignItems: 'center',
          }}>

          <TodoBox /><Box
        sx={{
          display: 'flex',
          justifyContent: 'space-evenly',
          width: '1000px'
        }}>
        <Box
          sx={{
            width: "450px",
            height: "250px",
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
            <h3>My MEMO</h3>
            <hr align="center" width="80%"/>   
            <textarea 
              id="myMemo" 
              name="myMemo"
              value={myMemoContent}
              onChange={(e) => handleSetMyMemo(e)}
              rows={20} 
              cols={50}   
            >
            </textarea>
        </Box>
        <Box
          sx={{
            width: "450px",
            height: "250px",
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
            <h3>Group MEMO</h3>
            <hr align="center" width="80%"/>   
            <textarea 
              id="roomMemo" 
              name="roomMemo"
              value={groupMemoContent}
              onChange={(e) => handleSetGroupMemo(e)}
              rows={20} 
              cols={50}   
            >
            </textarea>
        </Box> 
      </Box>
          <TimeTableBox />
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
              justifyContent: 'center'
            }}>
            <h3>그룹 인원</h3>
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
  );
};

export default GroupRoom;