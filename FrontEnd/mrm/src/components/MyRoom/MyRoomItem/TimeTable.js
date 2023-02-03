import React from 'react';
import { Box } from '@mui/system';
import { ResponsivePie } from '@nivo/pie';

const data = [
  {
    "id": "react공부",
    "label": "react공부",
    "value": 16,
    "color": "hsl(124, 70%, 50%)"
  },
  {
    "id": "알고리즘",
    "label": "알고리즘",
    "value": 20,
    "color": "hsl(124, 70%, 50%)"
  },


  {    
    "id": "면접스터디",
    "label": "면접스터디",
    "value": 8,
    "color": "hsl(4, 70%, 50%)"
  },
  {    
    "id": "CS공부",
    "label": "CS공부",
    "value": 12,
    "color": "hsl(4, 70%, 50%)"
  }

]

class TimeTable extends React.Component {
  render() {
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
        data={data}
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
        // legends={[
        //     {
        //         anchor: 'bottom',
        //         direction: 'row',
        //         justify: false,
        //         translateX: 0,
        //         translateY: 56,
        //         itemsSpacing: 0,
        //         itemWidth: 100,
        //         itemHeight: 18,
        //         itemTextColor: '#999',
        //         itemDirection: 'left-to-right',
        //         itemOpacity: 1,
        //         symbolSize: 18,
        //         symbolShape: 'circle',
        //         effects: [
        //             {
        //                 on: 'hover',
        //                 style: {
        //                     itemTextColor: '#000'
        //                 }
        //             }
        //         ]
        //     }
        // ]}
      />

      </Box>
    );
  }
}

export default TimeTable;