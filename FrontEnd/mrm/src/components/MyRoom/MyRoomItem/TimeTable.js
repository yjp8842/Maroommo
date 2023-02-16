import React from 'react';
import { Box } from '@mui/system';
import { ResponsivePie } from '@nivo/pie';


function TimeTable (props) {
  const propsList = [...props.doingList, ...props.doneList];

  console.log("todo 타임 테이블");
  console.log(props, propsList);

  const offset = 1000 * 60 * 60 * 9;
  const now = new Date();

  const todos = [];
  let totalTime = 0;
  propsList.forEach((todo, todo_index) => {
    var tot = 0;
    const size = todo.todoTimes.length;
    todo.todoTimes.forEach((time, time_index) => {
      if (time.totalSec && time.totalSec > 60 && time.startTime < time.endTime) {
        tot += time.totalSec;
      }
      else if (time_index === size - 1 && time.totalSec === 0) {
        const sTime = new Date((new Date(time.startTime)).getTime() - offset);

        console.log("마지막 인자");
        console.log(time, sTime, now, now-sTime);
      }
    });
    totalTime += tot;
    console.log(todo_index, todo, tot);
    todos.push({
      "id":todo.content,
      "label":todo.content,
      "value":tot
    })
  })

  todos.forEach((todo)=>{
    todo.value = ((todo.value / totalTime) * 100).toFixed(2);
  })
  console.log("todos");
  console.log(todos);

  return (
    <Box
      sx={{
        width: "300px",
        height: "300px",
        // marginTop: "25px",
        borderRadius: "150px",
        backgroundColor: "#FFFFFF",
        boxShadow: "5px 5px 8px rgba(0, 0, 0, 0.35)"
      }}>
      <ResponsivePie
      data={todos}
      margin={{ top: 0, right: 0, bottom: 0, left: 0 }}
      cornerRadius={3}
      // activeOuterRadiusOffset={8}
      colors={{ scheme: 'blue_green' }}
      borderWidth={1}
      borderColor={{
          from: 'color',
          modifiers: [
              [
                  'darker',
                  '0.5'
              ]
          ]
      }}
      enableArcLinkLabels={false}
      arcLinkLabelsSkipAngle={10}
      arcLinkLabelsTextColor="#333333"
      arcLinkLabelsThickness={2}
      arcLinkLabelsColor={{ from: 'color' }}
      arcLabelsSkipAngle={10}
      arcLabelsTextColor={{
          from: 'color',
          modifiers: [
              [
                  'darker',
                  2
              ]
          ]
      }}
      theme={{
        /**
         * label style (bar에 표현되는 글씨)
         */
        labels: {
            text: {
                fontSize: 20,
                fill: '#000000',
            },
        },
    }}
      
    />

    </Box>
  );
}

export default TimeTable;