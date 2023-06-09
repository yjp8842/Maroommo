import React, { useEffect } from "react";

import { Box } from "@mui/system";
import Moment from 'moment';

import { TimeList } from "../TimeList/TimeList";
import { Chart } from "react-google-charts"
import { id } from "date-fns/locale";


function TimeTableBox (props) {

  const offset = 1000 * 60 * 60 * 9;
  const now = new Date();
  
  const data = [[
    { type: "string", id: "user" },
    { type: "string", id: "text" },
    { type: "date", id: "Start" },
    { type: "date", id: "End" },
  ],
    [
      "[이름]",
      "[할일]",
      new Date(now.getFullYear(),now.getMonth(), now.getDate()), 
      new Date(now.getFullYear(),now.getMonth(), now.getDate(), 23,59,59)
    ]  
  ];
  
  // console.log("time table");
  // console.log(props);

  if(props.group.todayTodoTimes){
    const size = props.group.todayTodoTimes.length;
    props.group.todayTodoTimes.map((todo, index) => {
      // console.log(index,todo)
      if(todo.totalSec && todo.totalSec > 60 && todo.startTime < todo.endTime){
        data.push([todo.userNickname,
          todo.content,
          new Date((new Date(todo.startTime)).getTime() - offset),
          new Date((new Date(todo.endTime)).getTime() - offset) 
        ]);
      }
      if(index === size-1 && todo.totalSec === 0) {
        // console.log("마지막 투두")
        data.push([todo.userNickname,
          todo.content,
          new Date((new Date(todo.startTime)).getTime() - offset),
          new Date()
        ]);
      }
      return true;
    })
  }

  // console.log("필터링!");
  // console.log(data);
  
    return (
      <Box
        sx={{
          width: "970px",
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
          data={data}
          
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