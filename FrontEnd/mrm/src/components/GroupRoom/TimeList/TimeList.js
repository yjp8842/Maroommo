import React from "react";
import { Chart } from "react-google-charts"

export const data = [
    [
      { type: "string", id: "user" },
      { type: "string", id: "text" },
      { type: "date", id: "Start" },
      { type: "date", id: "End" },
    ],
    [
      "Magnolia",
      "Beginning JavaScript",
      new Date(0, 0, 0, 12, 0, 0),
      new Date(0, 0, 0, 13, 30, 0),
    ],
    [
      "Magnolia",
      "Intermediate JavaScript",
      new Date(0, 0, 0, 14, 0, 0),
      new Date(0, 0, 0, 15, 30, 0),
    ],
    [
      "Magnolia",
      "Advanced JavaScript",
      new Date(0, 0, 0, 16, 0, 0),
      new Date(0, 0, 0, 17, 30, 0),
    ],
    [
      "Willow",
      "Beginning Google Charts",
      new Date(0, 0, 0, 12, 30, 0),
      new Date(0, 0, 0, 14, 0, 0),
    ],
    [
      "Willow",
      "Intermediate Google Charts",
      new Date(0, 0, 0, 14, 30, 0),
      new Date(0, 0, 0, 16, 0, 0),
    ],
    [
      "Willow",
      "Advanced Google Charts",
      new Date(0, 0, 0, 16, 30, 0),
      new Date(0, 0, 0, 20, 0, 0),
    ],
  ];
  
  export function TimeList() {

    
    return (
      <>
        {/* <h2>Color By Row Label</h2> */}
        {/* <Chart
          chartType="Timeline"
          data={data}
          width="95%"
          height="300px"
          options={{
            timeline: {
              colorByRowLabel: true,
            },
          }}
        /> */}
        {/* <h2>Same Colors for all bars</h2>
        <Chart
          chartType="Timeline"
          data={data}
          width="100%"
          height="400px"
          options={{
            timeline: {
              singleColor: "#8d8",
            },
          }}
        />
        <h2>Setting Background Color</h2>
        <Chart
          chartType="Timeline"
          data={data}
          width="100%"
          height="400px"
          options={{
            timeline: { colorByRowLabel: true },
            backgroundColor: "#ffd",
          }}
        />
        <h2>Setting Individual Bar Colors</h2>
        <Chart
          chartType="Timeline"
          data={data}
          width="100%"
          height="400px"
          options={{
            colors: ["#cbb69d", "#603913", "#c69c6e"],
          }}
        />
        <h2>Changing the fonts</h2>*/}
        <Chart
          chartType="Timeline"
          data={data}
          
          width="98%"
          height="400px"
          options={{
            timeline: {
              colorByRowLabel: true,
              rowLabelStyle: {
                fontName: "Helvetica",
                fontSize: 20,
                color: "#603913",
              },
              barLabelStyle: { fontName: "Garamond", fontSize: 14 },
            },
          }}
        /> 
      </>
    );
  }
  