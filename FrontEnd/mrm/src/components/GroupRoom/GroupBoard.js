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

import api from "../../utils/axiosInstance";

const GroupBoard = () => {
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

          <Box sx={{mt:5}}>
            <h1>게시판</h1>
            <br></br>

            {/* <div style={{ width: "80%", margin: "3rem auto" }}>
              {error
                ? (<h2>에러 발생: {error}</h2>)
                : isSuccess && board.content.length <= 0
                  ? (<p> 조회할 내용이 없습니다.</p>)
                  : isSuccess && board.content.length > 0
                    ? (<BoardList
                      board={board}
                      groupId={group.id}
                        // handleDeleteClick={onDeleteClick}
                        handleArticleTitleClick={onArticleTitleClick}
                      />)
                    : (<p> 목록을 불러오는 중입니다. </p>)
              }
            </div> */}

            <table>
              <colgroup>
                <col width="10%" />
                <col width="35%" />
                <col width="15%" />
                <col width="10%" />
                <col width="15%" />
              </colgroup>
              <thead>
                <tr>
                  <th>번호</th>
                  <th>제목</th>
                  <th>작성자</th>
                  <th>조회수</th>
                  <th>작성일</th>
                </tr>
              </thead>
              <tbody>
                {board.content.map((article, index) => {
                  return (
                    <tr>
                      <td>{article.id}</td>
                        <Link to={`/group/${groupId}/board/article/${article.id}`}>{article.title}</Link>
                      <td>{article.user}</td>
                      <td>{article.views}</td>
                      <td>{new Date(article.createTime).toLocaleString()}</td>
                    </tr>
                  )
                })}
              </tbody>
            </table>


            {/* 룸아이디 넣는 식으로 수정해야함 */}
            <Link to={`/group/${groupId}/board/register?isForEdit=false`}>
              <button>글쓰기</button>
            </Link>

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
            {group.users.map((user, index) => {
              return (<GroupMemberList user={user}/>)
            })}
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