import React from 'react';
import { Box } from '@mui/system';
import { ResponsivePie } from '@nivo/pie';


function TimeTable (props) {
  console.log("타임테이블");

  const todos = [];
  let totalTime = 0;
  props.todoList.map((todo) => {
    var tot = 0;
    todo.todoTimes.map((time) => (
      time.totalSec ? tot += time.totalSec : tot += 0
    ));
    totalTime += tot;
    todos.push({
      "id":todo.content,
      "label":todo.content,
      "value":tot
    })
    return (true)
  })

  todos.forEach((todo)=>{
    todo.value = ((todo.value / totalTime) * 100).toFixed(2);
  })

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
      sx={{width:'300px', height:'300px'}}
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
    />

    </Box>
  );
}

export default TimeTable;