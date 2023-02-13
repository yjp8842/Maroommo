import React from 'react'
import { Link } from 'react-router-dom';


function ArticleDetail(props) {
  console.log('--------------')
  console.log('article detail 출력 : ', props)
  console.log('--------------')
  return (
    <div style={{ width: "80%", margin: "3rem auto" }}>
      <div style={{ margin: "2rem auto" }}>
        <a href="/group/board">
          <button type="primary">목록</button>
        </a>
        <button></button>
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
              <td colSpan="3">{props.picture}</td>
              <img src={`/images/${props.picture}`} alt='logo' className='imgbox' />
            </tr>
            <tr>
              <th>작성자</th>
              <td colSpan="3">{props.user_id}</td>
            </tr>
            <tr>
              <th>작성일</th>
              <td colSpan="3">{props.createTime}</td>
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
          props.loadComments.map((comments) => (
          <div
            style={{
              width: "100%",
              backgroundColor: "lightsteelblue",
              border: "1px dotted black",
            }}
            key='commentform'
          >
            <span key={comments.id}>
              <span>{comments.content}</span>
              <span>{comments.user_id}</span>
              <span style={{ float: "right" }}>
                {comments.createtime}&nbsp;
                <span style={{cursor:'pointer'}} 
                  onClick={() => props.deleteComment(comments.id)}>[X]</span>
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