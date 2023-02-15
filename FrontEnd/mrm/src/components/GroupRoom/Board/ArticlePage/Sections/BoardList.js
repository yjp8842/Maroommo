import { Link } from "@mui/material";
import React from "react";
// import { Link } from "react-router-dom";
import styled from "styled-components";
import Pagination from "../../../Pagination";

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
        {props.board.content.slice(props.offset, props.offset+props.limit).map((article, index) => {
          return (
            <thead>
              <tr key={article.id}>
                <td>{article.id}</td>
                  <Link to={`/group/${props.groupId}/board/article/${article.id}`}>
                    {/* <Article> */}
                      {article.title}
                    {/* </Article> */}
                  </Link>
                <td>{article.user}</td>
                <td>{article.views}</td>
                <td>{new Date(article.createTime).toLocaleString()}</td>
              </tr>
              </thead>
          )
        })}
      </table>
      <footer>
        <Pagination
          total={props.board.data.content.length}
          limit={props.limit}
          page={props.page}
          setPage={props.setPage}
        />
      </footer>
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