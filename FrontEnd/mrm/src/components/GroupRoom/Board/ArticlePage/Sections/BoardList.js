import React from "react";
import { Link } from "react-router-dom";
import styled from "styled-components";

function BoardList(props) {
  console.log(props.board);

  return (
    <div>
      <table>
        <colgroup>
          <col width="10%" />
          <col width="45%" />
          <col width="15%" />
          <col width="30%" />
        </colgroup>
        <thead>
          <tr>
            <th>번호</th>
            <th>제목</th>
            <th>조회수</th>
            <th>작성일</th>
          </tr>
        </thead>
        {props.board.map((article) => (
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
              <td>{article.views}</td>
              <td>{new Date(article.date).toLocaleString()}</td>
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