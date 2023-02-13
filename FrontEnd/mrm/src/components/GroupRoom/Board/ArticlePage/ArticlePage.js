import React, {useEffect, useState} from "react";
import { useDispatch, useSelector, shallowEqual } from "react-redux";
import { articleActions } from "../../../../slice/articleSlice";
import { useParams } from "react-router-dom";
import ArticleDetail from "./Sections/ArticleDetail";

import Comment from "./Sections/Comment";
import { commentActions } from "../../../../slice/commentSlice";


function ArticlePage() {

  const params = useParams();
  console.log(params)
  console.log(params.articleId)

  const dispatch = useDispatch();

  useEffect(() => {
    dispatch(articleActions.getArticle(params.articleId));
    // dispatch(commentActions.getComments(params.articleId));
  }, [params.articleId]);

  // , [{articleId}]);
// categorysub_id, content, createtime, hit, picture, title, user_id
  const { id, title, content, picture, createTime, user_id, comments  } = useSelector((state) => ({
    id: state.articleReducers.id,
    title: state.articleReducers.title,
    content: state.articleReducers.content,
    createTime: state.articleReducers.createTime,
    user_id: state.articleReducers.user_id,
    comments: state.articleReducers.comments,
    picture: state.articleReducers.picture
  }),
  shallowEqual);
  // const date = useSelector((state) => state.articleReducers.date);
  console.log('4444')
  console.log(id, title, content)
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
      articleId: id,
    };
    dispatch(commentActions.registerComment(comment))
  };

  const onDeleteComment = (commentId) => {
    dispatch(commentActions.deleteComment(commentId))
  };

  return (
    <div>
      <div>
        <ArticleDetail
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
      </div>
      {/* <div>
        <Link to={`/group/board/register?isForEdit=true`}>
          <button>수정</button>
        </Link>
        <Link>
          <button>삭제</button>
        </Link>
      </div> */}
    </div>
      )
    }
// {match.params.articleId}

export default ArticlePage;