import React from 'react'
import { Link } from 'react-router-dom';


function QuestionArticleDetail(props) {
  console.log('--------------')
  console.log('questionarticle detail 출력 : ', props)
  console.log('--------------')
  return (
    <div style={{ width: "80%", margin: "3rem auto" }}>
      <div style={{ margin: "2rem auto" }}>
        {/* 룸 아이디 넣는식으로 수정 */}
        <a href="/group/1/question">
          <button type="primary">목록</button>
        </a>
        <button></button>
      </div>
      <div style={{ textAlign: "center" }}>
        <h2>질문</h2>
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
              <th>해결/미해결</th>
              <td colSpan="3">{props.status}</td>
            </tr>
            <tr>
              <th>제목</th>
              <td colSpan="3">{props.title}</td>
            </tr>
            <tr>
              <th>내용</th>
              <td colSpan="3">{props.content}</td>
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
            {/* roomid 넣는식으로 수정 */}
            <Link to={`/group/1/question/register?isForEdit=true`}>
              <button>수정</button>
            </Link>
          </div>
          <div style={{ margin: "auto" }}>
            <Link>
              <button onClick={props.handleDeleteClick}>삭제</button>
            </Link>
        </div>
        <div>
          {props.handleAnswer}
        </div>
        <br></br>
        <p>댓글</p>
        <div>
        {props.loadAnswers.length > 0 &&
          props.loadAnswers.map((answers) => (
          <div
            style={{
              width: "100%",
              backgroundColor: "lightsteelblue",
              border: "1px dotted black",
            }}
            key='answerform'
          >
            <span key={answers.id}>
              <span>{answers.content}</span>
              <span>{answers.good}</span>
              <span style={{ float: "right" }}>
                {answers.createTime}&nbsp;
                <span style={{cursor:'pointer'}} 
                  onClick={() => props.deleteAnswer(answers.id)}>[X]</span>
              </span>
            </span>
          </div>
        ))}
      </div>
      </div>
    </div>
  );
}

export default QuestionArticleDetail;