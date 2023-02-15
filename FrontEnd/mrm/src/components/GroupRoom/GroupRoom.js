import React, { useEffect, useRef, useState } from "react";

import { Grid } from '@mui/material';
import { Box } from '@mui/system';

import PageIcon from '../MyRoom/MyRoomItem/PageIcon';
import { Link, useParams } from 'react-router-dom';
import GroupProfile from './GroupRoomItem/GroupProfile';
import CalendarBox from '../Calendar/Calendar';
import MenuBtn from './GroupRoomItem/MenuBtn';
import GroupMemberList from './GroupRoomItem/GroupMemberList';
import TodoBox from './GroupRoomItem/TodoInGroup';
import TimeTableBox from './GroupRoomItem/TimeTableInGroup';
import { userInfoActions} from "../../slice/userInfoSlice";
import { groupInfoActions} from "../../slice/groupInfoSlice";
import { scheduleActions } from "../../slice/scheduleSlice";

import api from "../../utils/axiosInstance";
import * as StompJs from "@stomp/stompjs";
import * as SockJS from "sockjs-client";
import { useSelector, useDispatch } from "react-redux";

import './GroupRoomItem/Category.css';
import './GroupRoomItem/TextArea.css';

import OpenChatRoom from './OpenVidu/OpenChatRoom';
import './Group.css';

const GroupRoom = () => {
  const handleOpenNewTab = (url) => {
    window.open(url, "_blank", "noopener, noreferrer");
  }

  const dispatch = useDispatch();
	const params = useParams();
  const groupId = params.groupId;


  /*
  처음에 로그인하면 user가 store에 저장이 되고 myPage로 이동한다
  이후 groupPage로 이동을 하면 useEffect 시 


  */
  useEffect(() => {
    console.log("그룹 페이지 이동!")
    api.get(`/room/${groupId}/${user.id}`)
    .then((res) => {    
      console.log("그룹 페이지 이동!")

      console.log(res)
      dispatch(groupInfoActions.saveGroupInfo(res.data.moveRoomInfo));
      dispatch(scheduleActions.saveSchedule(res.data.moveRoomInfo.schedules));
      setGroupMemoContent(res.data.moveRoomInfo.roomMemo);
    })
    .catch((err) => {
      console.log(err);
    });        
  }, [groupId])

  const {user, group} = useSelector((state) => ({
    user: state.userInfoReducers.user,
    group: state.groupInfoReducers.group
  }))

  const client = useRef({});
  const [groupMemoContent, setGroupMemoContent] = useState(group.roomMemo);
  const [myMemoContent, setMyMemoContent] = useState(user.userMemo);

  const handleSetGroupMemo = (e) => {
    setGroupMemoContent(e.target.value);
    publish(e.target.value);
  };

  const handleSetMyMemo = (e) => {
    setMyMemoContent(e.target.value);
    saveMyMemo(e.target.value);
    dispatch(userInfoActions.saveUserMemo(e.target.value))
  };

  const saveMyMemo = (content) => {

    const data = {
      userId: user.id,
      content: content
    };

    api
    .post('/my/memo', data)
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
    api
    .get('/memo/room/'+group.id)
    .then(response => {
      var responseMemo = response.data.roomMemo;
      setGroupMemoContent(responseMemo.content);
    })
    .catch((err) => {
      console.log(err);
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
    client.current.subscribe('/sub/memo/room/'+group.id, (res) => {
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
        roomId: group.id,
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

          {/* <div className='openvidu-btn' onClick={() => handleOpenNewTab(`/group/${group.id}/openvidu`)}>
            <OpenChatRoom />
          </div> */}
          
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