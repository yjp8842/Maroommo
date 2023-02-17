// import * as React from 'react';
// // import { Link } from "react-router-dom";
// import styled from "styled-components";
// import Pagination from "../../../Pagination";

// import Table from '@mui/material/Table';
// import TableBody from '@mui/material/TableBody';
// import TableCell from '@mui/material/TableCell';
// import TableContainer from '@mui/material/TableContainer';
// import TableHead from '@mui/material/TableHead';
// import TableRow from '@mui/material/TableRow';
// import Paper from '@mui/material/Paper';
// import { Box } from '@mui/system';

// function BoardList(props) {
//   console.log('success');

//   return (
//     <Box>
//       <TableContainer component={Paper} sx={{ border:'2px solid black', borderRadius:'25px', tableLayout:'auto'}}>
//       <Table sx={{ height:'60vh', minWidth: 650  }} aria-label="simple table">
//         <TableHead 
//           >
//           <TableRow >
//             <TableCell sx={{backgroundColor:'#ebe5d1',fontSize:'1.5rem', border:'2px solid black', borderTop:'0px', borderLeft:'0px'}} align='center'>번호</TableCell>
//             <TableCell sx={{backgroundColor:'#ebe5d1',fontSize:'1.5rem', border:'2px solid black', borderTop:'0px',}} align='center'>제목</TableCell>
//             <TableCell sx={{backgroundColor:'#ebe5d1',fontSize:'1.5rem', border:'2px solid black', borderTop:'0px',}} align='center'>작성자</TableCell>
//             <TableCell sx={{backgroundColor:'#ebe5d1',fontSize:'1.5rem', border:'2px solid black', borderTop:'0px',}} align='center'>조회수</TableCell>
//             <TableCell sx={{backgroundColor:'#ebe5d1',fontSize:'1.5rem', border:'2px solid black', borderTop:'0px', borderRight:'0px'}} align='center'>작성일</TableCell>
//           </TableRow>
//         </TableHead>
//         <TableBody>
//             {
//               board.content
//                 ? board.content.slice(offset, offset + limit).map((article, index) => {
//                   return (
//                     <TableRow
//                       key={article.id}
//                     >
//                       <TableCell sx={{ fontSize: '1.2rem', borderTop: '1px solid black', borderBottom: '1px solid black', borderRight: '1px solid black' }} align='center' component="th" scope="row">
//                         {article.id}
//                       </TableCell>
//                       <Link to={`/group/${groupId}/board/article/${article.id}`}><TableCell sx={{ fontSize: '1.2rem', border: '1px solid black' }} align='center'>{article.title}</TableCell></Link>
//                       <TableCell sx={{ fontSize: '1.2rem', border: '1px solid black' }} align='center'>{article.user}</TableCell>
//                       <TableCell sx={{ fontSize: '1.2rem', border: '1px solid black' }} align='center'>{article.views}</TableCell>
//                       <TableCell sx={{ fontSize: '1.2rem', border: '1px solid black', borderRight: '0px' }} align='center'>{new Date(article.createTime).toLocaleString()}</TableCell>
//                     </TableRow>
//                   )
//                 })
//                 : <TableRow></TableRow>
//             }
//         </TableBody>
//       </Table>
//     </TableContainer>
//     <footer>
//       <Pagination
//         total={props.board.data.content.length}
//         limit={props.limit}
//         page={props.page}
//         setPage={props.setPage}
//       />
//     </footer>      
//   </Box>
    
//     // <div>
//     //   <table>
//     //     <colgroup>
//     //       <col width="10%" />
//     //       <col width="35%" />
//     //       <col width="15%" />
//     //       <col width="10%" />
//     //       <col width="15%" />
//     //     </colgroup>
//     //     <thead>
//     //       <tr>
//     //         <th>번호</th>
//     //         <th>제목</th>
//     //         <th>작성자</th>
//     //         <th>조회수</th>
//     //         <th>작성일</th>
//     //       </tr>
//     //     </thead>
//     //     {props.board.content.slice(props.offset, props.offset+props.limit).map((article, index) => {
//     //       return (
//     //         <thead>
//     //           <tr key={article.id}>
//     //             <td>{article.id}</td>
//     //               <Link to={`/group/${props.groupId}/board/article/${article.id}`}>
//     //                 {/* <Article> */}
//     //                   {article.title}
//     //                 {/* </Article> */}
//     //               </Link>
//     //             <td>{article.user}</td>
//     //             <td>{article.views}</td>
//     //             <td>{new Date(article.createTime).toLocaleString()}</td>
//     //           </tr>
//     //           </thead>
//     //       )
//     //     })}
//     //   </table>
//     //   <footer>
//     //     <Pagination
//     //       total={props.board.data.content.length}
//     //       limit={props.limit}
//     //       page={props.page}
//     //       setPage={props.setPage}
//     //     />
//     //   </footer>
//     // </div>
//   );
// }

// const Article = styled.td`
//   cursor: pointer;
//   &:hover {
//     text-decoration: underline;
//   }
// `
// export default BoardList;