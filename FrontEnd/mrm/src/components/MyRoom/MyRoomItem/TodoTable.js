import * as React from 'react';
import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell from '@mui/material/TableCell';
import TableContainer from '@mui/material/TableContainer';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import Paper from '@mui/material/Paper';
import { BiCheckboxChecked } from 'react-icons/bi';
import { BiCheckbox } from 'react-icons/bi';
import { useSelector } from 'react-redux';

// function createData(name, text, finished, launch) {
//   return { name, text, finished, launch};
// }

// 카테고리 내용 완료 날짜
// 체크박스 조건문으로 바꾸기
export default function TodoTable(props) {
  
  const todoList = [...props.doingList, ...props.doneList];

  const text = (data) => {
    var resText  = "";
    data.tags.map((tag) => {
      if (tag !== "")
        resText += "[" + tag + "]";
    })
    // console.log(resText);
    resText += data.content;
    return resText;
  }

  const done = (done) => {
    if (done.state == 2)
    return <BiCheckboxChecked/>
    else
      return <BiCheckbox/>
  }

  return (
    <TableContainer component={Paper} sx={{ border:'2px solid black', borderRadius:'25px', tableLayout:'auto'}}>
      <Table sx={{ height:'60vh', minWidth: 650  }} aria-label="simple table">
        <TableHead 
          >
          <TableRow >
            <TableCell sx={{backgroundColor:'#ebe5d1',fontSize:'1.5rem', border:'2px solid black', borderTop:'0px', borderLeft:'0px'}} align='center'>그룹</TableCell>
            <TableCell sx={{backgroundColor:'#ebe5d1',fontSize:'1.5rem', border:'2px solid black', borderTop:'0px',}} align='center'>내용</TableCell>
            <TableCell sx={{backgroundColor:'#ebe5d1',fontSize:'1.5rem', border:'2px solid black', borderTop:'0px',}} align='center'>완료</TableCell>
            <TableCell sx={{backgroundColor:'#ebe5d1',fontSize:'1.5rem', border:'2px solid black', borderTop:'0px', borderRight:'0px'}} align='center'>날짜</TableCell>
          </TableRow>
        </TableHead>
        <TableBody>
          {todoList.map((row) => (
            <TableRow
              key={row.name}
              
            >
              <TableCell sx={{fontSize:'1.2rem', borderTop:'1px solid black', borderBottom:'1px solid black', borderRight:'1px solid black'}} align='center' component="th" scope="row">
                {row.roomName}
              </TableCell>
              <TableCell sx={{fontSize:'1.2rem', border:'1px solid black'}} align='center'>{text(row)}
                {/* {row.tags.map((tag) => (
                return
              ))}{row.content} */}
              </TableCell>
              <TableCell sx={{fontSize:'1.2rem', border:'1px solid black'}} align='center'>{done(row)}</TableCell>
              <TableCell sx={{fontSize:'1.2rem', border:'1px solid black', borderRight:'0px'}} align='center'>{row.startTime}</TableCell>
            </TableRow>
          ))}
        </TableBody>
      </Table>
    </TableContainer>
  );
}