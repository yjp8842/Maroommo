import React from "react";
// import { Link } from "react-router-dom";
import styled from "styled-components";

function QuestionList(props) {
  console.log(props.question);

  return (
    <div>
      <table>
        <colgroup>
          <col width="10%" />
          <col width="15%" />
          <col width="35%" />
          <col width="15%" />
          <col width="10%" />
          <col width="15%" />
        </colgroup>
        <thead>
          <tr>
            <th>번호</th>
            <th>해결/미해결</th>
            <th>제목</th>
            <th>작성자</th>
            <th>조회수</th>
            <th>작성일</th>
          </tr>
        </thead>
        {props.question.content.map((questionArticle) => (
          <thead>
            <tr key={questionArticle.id}>
              <td>{questionArticle.id}</td>
              <td>{questionArticle.status}</td>

              {/* <td>{questionArticle.categorysub_id}</td> */}
              <QuestionArticle onClick={() => props.handleQuestionArticleTitleClick(questionArticle.id)} // 추가
                >
                  {questionArticle.title}
                  {/* &nbsp;
                  {props.commentLength[question.id] > 0 &&
                    `[${props.commentLength[question.id]}]`} */}
                </QuestionArticle>
              <td>{questionArticle.user_id}</td>
              <td>{questionArticle.views}</td>
              <td>{new Date(questionArticle.date).toLocaleString()}</td>
            </tr>
          </thead>
        ))}
      </table>
    </div>
  );
}

const QuestionArticle = styled.td`
  cursor: pointer;
  &:hover {
    text-decoration: underline;
  }
`

export default QuestionList;