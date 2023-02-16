import React, { useState, useEffect } from 'react';

import { Grid } from '@mui/material';
import { Box } from '@mui/system';

import PageIcon from './MyRoomItem/PageIcon';
import Profile from './MyRoomItem/Profile';
import StudyTime from './MyRoomItem/StudyTime';
import Todo from './MyRoomItem/Todo';
import CalendarBox from '../Calendar/Calendar';
import Memo from './MyRoomItem/Memo';
import TimeTable from './MyRoomItem/TimeTable';
import Choice from './MyRoomItem/Choice';
import { Link } from 'react-router-dom';
import TodoTable from './MyRoomItem/TodoTable';

import RoomModal from "../Modal/Group/RoomModal";
import styled from "styled-components";
import { useSelector, shallowEqual, useDispatch } from 'react-redux';
import api from "../../utils/axiosInstance"
import { userInfoActions } from "../../slice/userInfoSlice";
import { scheduleActions } from "../../slice/scheduleSlice";
import "../GroupRoom/GroupRoomItem/TextArea.css";

const MyRoom = () => {
  const dispatch = useDispatch()

  useEffect(() => {
    api.get(`/room/my`)
    .then((res) => {   
      console.log("마이 페이지 이동!");  
      console.log(res);
      dispatch(userInfoActions.saveUserInfo(res.data.user));
      dispatch(userInfoActions.saveMyRoomInfo(res.data.myRoomInfo));
      dispatch(scheduleActions.saveSchedule(res.data.myRoomInfo.schedules));
    })
    .catch((err) => {
      console.log(err);
    });        
  }, [])

  const {user, doing_list, done_list} = useSelector((state) => 
  ({
    user: state.userInfoReducers.user,
    doing_list: state.userInfoReducers.user.doing,
    done_list: state.userInfoReducers.user.done
  }), shallowEqual)

  var doingList = [];
  var doneList = [];
  const now = new Date();
  const nowY = now.getFullYear();
  const nowM = now.getMonth();
  const nowD = now.getDate();

  // const nowYmd = now.getFullYear() + '-' + now.getMonth() + '-' + now.getDate();
  // const yesterdayYmd = now.getFullYear() + '-' + now.getMonth() + '-' + (now.getDate()-1);
  console.log("마이 룸")
  // console.log(nowYmd, yesterdayYmd);

  doing_list.map((todo, index) => {
    const startTodo = new Date(todo.startTime);
    // const ymd = startTodo.getFullYear() + '-' + startTodo.getMonth() + '-' + startTodo.getDate();
    // console.log(ymd);

    // if (ymd === nowYmd || ymd === yesterdayYmd)
    if (startTodo.getFullYear() < nowY || startTodo.getMonth() < nowM || startTodo.getDate() <= nowD)
      doingList.push(todo)
  })

  done_list.map((todo, index) => {
    const endTodo = new Date(todo.endTime);

    if (endTodo.getFullYear() === nowY && endTodo.getMonth() === nowM && endTodo.getDate() === nowD)
    doneList.push(todo)
  })
  console.log("todo list");
  console.log(doingList, doneList);


  const [isOpen, setIsOpen] = useState(false);
  const [myMemoContent, setMyMemoContent] = useState(user.userMemo);

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

  const onClickButton = () => {
    setIsOpen(true);
  };


  return (
    <Grid container>
      <Box
        sx={{
          width: "5vw",
          // minWidth: "100px",
          height: "100vh",
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
            height: "1vh",
            // minWidth: "80px",
            maxHeight: "5px",
            minHeight: "3px",
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
            {/* 해당 groupId의 경로로 이동할 수 있도록 변경해야함 */}
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
          display: "flex",
          flexDirection: "column",
        }}>
        <Box
          sx={{
            width: "95vw",
            height: "28vh",
            paddingTop: "2vh",
            display: "flex",
            justifyContent: "space-evenly",
            backgroundColor: "#ebe5d1",
          }}>
          <Box
            sx={{
              width: "90vw",
              height: "28vh",
              display: "flex",
            }}>
            <Profile 
              user={user}  
              />
            <StudyTime doingList={doingList} doneList={doneList} />
            <Todo doing={Object.keys(doingList).length} done={Object.keys(doneList).length} />
            <CalendarBox />            
          </Box>
        </Box>
        <Box
          sx={{
            width: "90vw",
            height: "70vh",
            display: "flex",
          }}>
          <Box
            sx={{
              width: "65vw",
              height: "70vh",
              display: "flex",
              flexDirection: "column",
              alignItems: "center",
              justifyContent: "space-evenly",
            }}>
            <Box
              sx={{
                width: "60vw",
                height: "60vh",
                borderRadius: "30px",
                backgroundColor: "#FFFFFF",
                boxShadow: "5px 5px 8px rgba(0, 0, 0, 0.35)"
              }}>
              <TodoTable doingList={doingList} doneList={doneList}
              sx={{
                fontSize:"55"
              }}/>
            </Box>
          </Box>
          <Box
            sx={{
              width: "26vw",
              height: "70vh",
              display: "flex",
              flexDirection: "column",
              alignItems: "center",
              justifyContent: "space-evenly"
            }}>
            <Box
              sx={{
                // width: "450px",
                // height: "250px",
                width: "26vw",
                height: "20vh",
                // marginTop: "20px",
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
                  className='memoArea'
                  id="myMemo" 
                  name="myMemo"
                  value={myMemoContent}
                  onChange={(e) => handleSetMyMemo(e)}
                  rows={20} 
                  cols={50}   
                >
                </textarea>
            </Box>
            {/* <h2>TIME TABLE</h2> */}
            <TimeTable
              doingList={doingList} doneList={doneList}/>
          </Box>
        </Box>
      </Box>
    </Grid>
  );
};

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

export default MyRoom;