import React from "react";
// import { Link } from "react-router-dom";
import styled from "styled-components";

function BoardList(props) {
  console.log('success');


  return (
    <div>
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
        {props.board.content.map((article) => (
          <thead>
            <tr key={article.id}>
              <td>{article.id}</td>
              <Article onClick={() => props.handleArticleTitleClick(article.id)} // 추가
                >
                  {article.title}
                  {/* &nbsp;
                  {props.commentLength[article.id] > 0 &&
                    `[${props.commentLength[article.id]}]`} */}
                </Article>
              <td>{article.user}</td>  
              <td>{article.views}</td>
              <td>{new Date(article.createTime).toLocaleString()}</td>
            </tr>
          </thead>
        ))}
      </table>
    </div>
  );
}

const Article = styled.td`
  cursor: pointer;
  &:hover {
    text-decoration: underline;
  }
`

export default BoardList;