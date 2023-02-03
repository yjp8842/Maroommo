import * as React from 'react';
import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell from '@mui/material/TableCell';
import TableContainer from '@mui/material/TableContainer';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import Paper from '@mui/material/Paper';
import { BiCheckboxChecked } from 'react-icons/bi';
import { BiCheckboxMinus } from 'react-icons/bi';

function createData(name, content, finished, launch) {
  return { name, content, finished, launch};
}

const rows = [
  createData('리액트', '리액트 강의 3강 듣기', <BiCheckboxChecked/>, '2023-01-20'),
  createData('알고리즘', '백준 1234번 풀기', <BiCheckboxChecked/>, '2023-01-21'),
  createData('프론트엔드', '프론트 짱', <BiCheckboxMinus/>, '2023-01-22'),
  createData('백엔드', '백엔드 바보', <BiCheckboxChecked/>, '2023-01-23'),
  createData('테트리스', '조현동 이기기', <BiCheckboxMinus/>, '2023-01-24'),
];
// 카테고리 내용 완료 날짜
// 체크박스 조건문으로 바꾸기
export default function TodoTable() {
  return (
    <TableContainer component={Paper} sx={{ border:'2px solid black', borderRadius:'25px', tableLayout:'auto'}}>
      <Table sx={{ height:'50vh', minWidth: 650  }} aria-label="simple table">
        <TableHead 
          >
          <TableRow >
            <TableCell sx={{backgroundColor:'#ebe5d1',fontSize:'1.5rem', border:'2px solid black', borderTop:'0px', borderLeft:'0px'}} align='center'>카테고리</TableCell>
            <TableCell sx={{backgroundColor:'#ebe5d1',fontSize:'1.5rem', border:'2px solid black', borderTop:'0px',}} align='center'>내용</TableCell>
            <TableCell sx={{backgroundColor:'#ebe5d1',fontSize:'1.5rem', border:'2px solid black', borderTop:'0px',}} align='center'>완료</TableCell>
            <TableCell sx={{backgroundColor:'#ebe5d1',fontSize:'1.5rem', border:'2px solid black', borderTop:'0px', borderRight:'0px'}} align='center'>날짜</TableCell>
          </TableRow>
        </TableHead>
        <TableBody>
          {rows.map((row) => (
            <TableRow
              key={row.name}
              
            >
              <TableCell sx={{fontSize:'1.2rem', borderTop:'1px solid black', borderBottom:'1px solid black', borderRight:'1px solid black'}} align='center' component="th" scope="row">
                {row.name}
              </TableCell>
              <TableCell sx={{fontSize:'1.2rem', border:'1px solid black'}} align='center'>{row.content}</TableCell>
              <TableCell sx={{fontSize:'1.2rem', border:'1px solid black'}} align='center'>{row.finished}</TableCell>
              <TableCell sx={{fontSize:'1.2rem', border:'1px solid black', borderRight:'0px'}} align='center'>{row.launch}</TableCell>
            </TableRow>
          ))}
        </TableBody>
      </Table>
    </TableContainer>
  );
}