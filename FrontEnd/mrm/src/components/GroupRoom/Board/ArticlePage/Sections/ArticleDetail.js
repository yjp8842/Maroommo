import React from 'react'
import { Link } from 'react-router-dom';


function ArticleDetail(props) {
  return (
    <div style={{ width: "80%", margin: "3rem auto" }}>
      <div style={{ margin: "2rem auto" }}>
        <a href="/group/board">
          <button type="primary">목록</button>
        </a>
      </div>
      <div style={{ textAlign: "center" }}>
        <h2>게시글</h2>
      </div>
      <div>
        <table>
          <colgroup>
            <col width="10%" />
            <col width="40%" />
            <col width="10%" />
            <col width="40%" />
          </colgroup>
          <tbody>
            <tr>
              <th>번호</th>
              <td>{props.id}</td>
              <th>조회수</th>
              <td>{props.views}</td>
            </tr>
            <tr>
              <th>제목</th>
              <td colSpan="3">{props.title}</td>
            </tr>
            <tr>
              <th>내용</th>
              <td colSpan="3">{props.content}</td>
            </tr>
            <tr>
              <th>작성일</th>
              <td colSpan="3">{new Date(props.date).toLocaleString()}</td>
            </tr>
          </tbody>
        </table>
      </div>
      <div>
          <div style={{ margin: "2rem auto" }}>
            <Link to={`/group/board/register?isForEdit=true`}>
              <button>수정</button>
            </Link>
          </div>
          <div style={{ margin: "auto" }}>
            <Link>
              <button onClick={props.handleDeleteClick}>삭제</button>
            </Link>
        </div>
        <div>
          {props.handleComment}
        </div>
        <br></br>
        <p>댓글</p>
        <div>
        {props.loadComments.length > 0 &&
          props.loadComments.map((comment) => (
          <div
            style={{
              width: "100%",
              backgroundColor: "lightsteelblue",
              border: "1px dotted black",
            }}
            key='commentform'
          >
            <span key={comment.id}>
              <span>{comment.content}</span>
              <span style={{ float: "right" }}>
                {new Date(comment.date).toLocaleString()}&nbsp;
                <span style={{cursor:'pointer'}} 
                  onClick={() => props.deleteComment(comment.id)}>[X]</span>
              </span>
            </span>
          </div>
        ))}
      </div>
      </div>
    </div>
  );
}

export default ArticleDetail;