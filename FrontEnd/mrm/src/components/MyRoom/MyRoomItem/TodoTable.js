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
export default function TodoTable() {
  

  const {tododata} = useSelector((state) => 
  ({
    tododata: state.userInfoReducers.user.doing
  }))

  const {donedata} = useSelector((state) =>({
    donedata: state.userInfoReducers.user.done
  }))

  const todoList = [];
  tododata.map((list) => {
    return todoList.push(list)
  })
  donedata.map((list) => {
    return todoList.push(list)
  })

  // console.log(todoList);

  // console.log(todoList[0].roomName, todoList[0].content, todoList[0].state, todoList[0].startTime)

  // const rows = [

    // todoList.map((todo) => {
    //   return createData( todo.roomName, todo.content, todo.state, todo.startTime)
    // })
    // createData(todoList.roomName, todoList.content, todoList.state, todoList.startTime)
    // createData('리액트', '리액트 강의 3강 듣기', <BiCheckboxChecked/>, '2023-01-20'),
    // createData('알고리즘', '백준 1234번 풀기', <BiCheckboxChecked/>, '2023-01-21'),
    // createData('프론트엔드', '프론트 짱', <BiCheckboxMinus/>, '2023-01-22'),
    // createData('백엔드', '백엔드 바보', <BiCheckboxChecked/>, '2023-01-23'),
    // createData('테트리스', '조현동 이기기', <BiCheckboxMinus/>, '2023-01-24'),
  // ];

  // console.log("rows ===", rows);

  const text = (data) => {
    var resText  = "";
    data.tags.map((tag) =>{
      resText += "["+tag+"]";
    })
    console.log(resText);
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