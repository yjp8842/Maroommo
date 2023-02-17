import React from 'react';
import { Box } from '@mui/system';
import { useSelector } from "react-redux";
import TodoInput from './TodoInput';

function Todo (props) {

  console.log("todo input");
  console.log(props);
  // todolist.forEach((todo) => {
  //   if(todo.startTime)
  // })

  // console.log("Todo, Done === ", countTodo, countDone)

  return (
    <Box
      sx={{
        width: "14vw",
        height: "25vh",
        // marginRight: "2vw",
        // width: "300px",
        // height: "220px",
        // marginLeft: "15px",
        borderRadius: "30px",
        backgroundColor: "#FFFFFF",
        boxShadow: "5px 5px 8px rgba(0, 0, 0, 0.35)"
        
      }}>
      <div className="bigbox">
        <div className="box">
          <div className="inbox-1">
            <div className="circle_top"><h2>{props.doing}</h2></div>
            <h3>할일</h3>
          </div>
  
          <div className="diagonal"></div>
  
          <div className="inbox-2">
            <div className="inbox-3">
              <h3>완료</h3>
              <div className="circle_bot"><h2>{props.done}</h2></div>
            </div>
          </div>
        </div>
      </div>
    </Box>
  );
}

export default Todo;