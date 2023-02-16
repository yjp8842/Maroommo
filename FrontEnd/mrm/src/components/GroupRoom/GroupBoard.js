import React, { useEffect, useRef, useState } from "react";

import { Grid } from '@mui/material';
import { Box } from '@mui/system';

import PageIcon from '../MyRoom/MyRoomItem/PageIcon';
import GroupProfile from './GroupRoomItem/GroupProfile';
import CalendarBox from '../Calendar/Calendar';
import MenuBtn from './GroupRoomItem/MenuBtn';
import GroupMemberList from './GroupRoomItem/GroupMemberList';

import { Link, useParams } from 'react-router-dom';
import { useDispatch, useSelector } from 'react-redux';
import { boardActions } from '../../slice/boardSlice';
import BoardList from './Board/ArticlePage/Sections/BoardList';
import styled from "styled-components";
import RoomModal from "../Modal/Group/RoomModal";

import OpenChatRoom from './OpenVidu/OpenChatRoom';
import './Group.css';
import Pagination from "./Pagination";
import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell from '@mui/material/TableCell';
import TableContainer from '@mui/material/TableContainer';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import Paper from '@mui/material/Paper';

import api from "../../utils/axiosInstance";


const GroupBoard = () => {
  const handleOpenNewTab = (url) => {
    window.open(url, "_blank", "noopener, noreferrer");
  };
  
  const dispatch = useDispatch();
  const params = useParams();
  const groupId = params.groupId;

  useEffect(() => {

    api.get(`/board?room_id=${groupId}&page=0&size=30`)
      .then((res) => {
        console.log("게시글 목록 가져오기");
        console.log(res);
        dispatch(boardActions.getBoardSuccess(res.data));
      })
      .catch((err) => {
        console.log(err);
        dispatch(boardActions.getBoardFailed(err));
      });
    // 보드에서 가져오기
    // dispatch(boardActions.getBoard());
  }, [groupId]);

  const {user, board, group, isLoading, isSuccess, error } = 
  useSelector((state) => ({
    user: state.userInfoReducers.user,
    board: state.boardReducers.board,
    group: state.groupInfoReducers.group,
    isLoading: state.boardReducers.isLoading,
    isSuccess: state.boardReducers.isSuccess,
    error: state.boardReducers.error}));

  const [limit, setLimit] = useState(10)
  const [page, setPage] = useState(1)
  const offset = (page - 1) * limit;
  const [isOpen, setIsOpen] = useState(false);
  
  const onClickButton = () => {
    setIsOpen(true);
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
            flexDirection: 'column'
          }}>

          <Box sx={{mt:5}}>
            <h1>게시판</h1>
            <br></br>
            <TableContainer component={Paper} sx={{ border:'2px solid black', borderRadius:'25px', tableLayout:'auto'}}>
              <Table sx={{ height:'65vh', width:"60vw", minWidth: 650  }} aria-label="simple table">
                <TableHead 
                  >
                  <TableRow >
                    <TableCell sx={{backgroundColor:'#ebe5d1',fontSize:'1.5rem', border:'2px solid black', borderTop:'0px', borderLeft:'0px'}} align='center'>번호</TableCell>
                    <TableCell sx={{backgroundColor:'#ebe5d1',fontSize:'1.5rem', border:'2px solid black', borderTop:'0px',}} align='center'>제목</TableCell>
                    <TableCell sx={{backgroundColor:'#ebe5d1',fontSize:'1.5rem', border:'2px solid black', borderTop:'0px',}} align='center'>작성자</TableCell>
                    <TableCell sx={{backgroundColor:'#ebe5d1',fontSize:'1.5rem', border:'2px solid black', borderTop:'0px',}} align='center'>조회수</TableCell>
                    <TableCell sx={{backgroundColor:'#ebe5d1',fontSize:'1.5rem', border:'2px solid black', borderTop:'0px', borderRight:'0px'}} align='center'>작성일</TableCell>
                  </TableRow>
                </TableHead>
                <TableBody>
                    {
                      board.content
                        ? board.content.slice(offset, offset + limit).map((article, index) => {
                          return (
                            <TableRow
                              key={article.id}
                            >
                              <TableCell sx={{ fontSize: '1.2rem', borderTop: '1px solid black', borderBottom: '1px solid black', borderRight: '1px solid black' }} align='center' component="th" scope="row">
                                {article.id}
                              </TableCell>
                              <TableCell sx={{ fontSize: '1.2rem', border: '1px solid black' }} align='center'>
                                <Link to={`/group/${groupId}/board/article/${article.id}`}>
                                  {article.title}
                                </Link>
                              </TableCell>
                              <TableCell sx={{ fontSize: '1.2rem', border: '1px solid black' }} align='center'>{article.user}</TableCell>
                              <TableCell sx={{ fontSize: '1.2rem', border: '1px solid black' }} align='center'>{article.views}</TableCell>
                              <TableCell sx={{ fontSize: '1.2rem', border: '1px solid black', borderRight: '0px' }} align='center'>{new Date(article.createTime).toLocaleString()}</TableCell>
                            </TableRow>
                          )
                        })
                        : <TableRow></TableRow>
                    }
                </TableBody>
              </Table>
            </TableContainer>
            <Pagination limit={limit} page={page} setPage={setPage} total={board.content ? board.content.length : 1}/>

            {/* 룸아이디 넣는 식으로 수정해야함 */}
            <Box
              sx={{
                width: "80px",
                height: "30px",
                // marginTop: "20px",
                // paddingY: '20px',
                borderRadius: "30px",
                backgroundColor: "#FFFFFF",
                boxShadow: "5px 5px 8px rgba(0, 0, 0, 0.35)",
                display: 'flex',
                flexDirection: 'column',
                justifyContent: 'center',
                alignItems: 'center'
                }}>
                <Link to={`/group/${groupId}/board/register?isForEdit=false`}>
                  글쓰기
                </Link>
            </Box>
          </Box>
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
              justifyContent: 'center'
            }}>
              <h2>탈퇴하기</h2>
          </Box>
        </Box>
      </Box>
    </Grid>
  );
};

export default GroupBoard;


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