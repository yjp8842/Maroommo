import React from "react";
import { Box } from "@mui/system";
import Moment from 'moment';

import { TimeList } from "../TimeList/TimeList";
import { Chart } from "react-google-charts"
import { id } from "date-fns/locale";


function TimeTableBox (props) {

  const data1 = [
    [
      { type: "string", id: "user" },
      { type: "string", id: "text" },
      { type: "date", id: "Start" },
      { type: "date", id: "End" },
    ],
  ];
  
  if(props.group.todayTodoTimes){
    const size = props.group.todayTodoTimes.length;
    props.group.todayTodoTimes.map((todo, index) => {
      if(todo.totalSec > 60 && todo.startTime < todo.endTime){
        data1.push([todo.userNickname,
          todo.content,
          new Date(todo.startTime),
          new Date(todo.endTime) 
        ]);
      }
      if(index === size-1 && todo.totalSec === 0) {
        // console.log("마지막 투두")
        data1.push([todo.userNickname,
          todo.content,
          new Date(todo.startTime),
          new Date() 
        ]);
      }
      return true;
    })
  }

  // console.log("필터링!");
  // console.log(data1);
  
    return (
      <Box
        sx={{
          width: "950px",
          height: "250px",
          marginTop: "20px",
          paddingY: '20px',
          borderRadius: "30px",
          backgroundColor: "#FFFFFF",
          boxShadow: "5px 5px 8px rgba(0, 0, 0, 0.35)",
          display: 'flex',
          flexDirection: 'column',
          justifyContent: 'center',
          alignItems: 'center'
        }}>
        <h3>Time Table</h3>
        <hr align="center" width="85%"/>   
        <br></br>
        {/* <TimeList /> */}
        <Chart
          sx={{
            marginTop: "5px",
            // paddingX: '10px',
            display: 'flex'
          }}
          chartType="Timeline"
          data={data1}
          
          width="850px"
          height="230px"
          options={{
            timeline: {
              // colorByRowLabel: true,
              rowLabelStyle: {
                fontName: "Helvetica",
                fontSize: 20,
                color: "#603913",
              },
              barLabelStyle: { fontName: "Garamond", fontSize: 14 },
            },
          }}
        /> 
      </Box>
    )
    
}

export default TimeTableBox;