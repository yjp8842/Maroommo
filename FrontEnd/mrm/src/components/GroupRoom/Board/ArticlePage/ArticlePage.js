import React, { useEffect, useRef, useState } from "react";
import api from "../../../../utils/axiosInstance";

import { Grid } from '@mui/material';
import { Box } from '@mui/system';

import { useDispatch, useSelector, shallowEqual } from "react-redux";
import { Link, useParams } from 'react-router-dom';
import { articleActions } from "../../../../slice/articleSlice";

import PageIcon from '../../../MyRoom/MyRoomItem/PageIcon';
import ArticleDetail from "./Sections/ArticleDetail";
import GroupProfile from '../../GroupRoomItem/GroupProfile';
import CalendarBox from '../../../Calendar/Calendar';
import MenuBtn from '../../GroupRoomItem/MenuBtn';
import GroupMemberList from '../../GroupRoomItem/GroupMemberList';

import Comment from "./Sections/Comment";
import { commentActions } from "../../../../slice/commentSlice";


function ArticlePage() {
  const dispatch = useDispatch();
	const params = useParams();
  const groupId = params.groupId;
  const articleId = params.articleId;

  useEffect(() => {
    // dispatch(articleActions.getArticle(articleId));
    // dispatch(commentActions.getComments(articleId));

    api.get(`/board/${articleId}`)
      .then((res) => {
        // console.log("게시글 불러오기");
        // console.log(res);
        dispatch(articleActions.getArticleDetail(res.data));
      })
      .catch((err) => {
        console.log(err);
      });
    
  }, [articleId]);

  const { user, group, id, title, content, picture, createTime, user_id, comments  } = useSelector((state) => ({
    user: state.userInfoReducers.user,
    group: state.groupInfoReducers.group,
    id: state.articleReducers.id,
    title: state.articleReducers.title,
    content: state.articleReducers.content,
    createTime: state.articleReducers.createTime,
    user_id: state.articleReducers.user_id,
    comments: state.articleReducers.comments,
    picture: state.articleReducers.picture
  }), shallowEqual);
  
  // const date = useSelector((state) => state.articleReducers.date);
  // console.log('article page 출력 ')
  // console.log(id, title, content)
  const views = useSelector((state) => state.articleReducers.views);
  // console.log(title)

  // const stateForProps = useSelector((state) =>
  // state.articleReducers)

  const onDeleteClick = () => {
    if (!window.confirm('삭제하시겠습니까?')) return false;
    dispatch(articleActions.deleteArticle(id))
  } 

  //댓글
  const [CommentValue, setCommentValue] = useState("");

  // const comments = useSelector((state) =>
  // state.commentReducers.comments);
  // console.log(comments)

  const onCommentChange = (e) => {
    setCommentValue(e.currentTarget.value);
  };

  const onCommentSubmit = (e) => {
    e.preventDefault()
    if (
      CommentValue === '' ||
      CommentValue === null ||
      CommentValue === undefined
    ) {
      alert('댓글을 입력하세요.');
      return false;
    }
    const comment = {
      // id: 0,
      user_id: 'hd',
      content: CommentValue,
      date: Date.now(),
      board_id: id,
    };
    dispatch(commentActions.registerComment(comment))
  };

  const onDeleteComment = (commentId) => {
    dispatch(commentActions.deleteComment(commentId))
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
            <ArticleDetail
              groupId={groupId}
              id={id}
              title={title}
              content={content}
              views={views}
              createTime={createTime}
              user_id={user_id}
              comments={comments}
              picture={picture}
              handleDeleteClick={onDeleteClick}
              handleComment={<Comment
                comment={CommentValue}
                handleCommentChange={onCommentChange}
                handleCommentSubmit={onCommentSubmit}/>}
              loadComments={comments}
              deleteComment={onDeleteComment}
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
  );
};
// {match.params.articleId}

export default ArticlePage;