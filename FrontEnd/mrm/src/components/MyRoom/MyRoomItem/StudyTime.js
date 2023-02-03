import React from 'react';
import { Box } from '@mui/system';
import StudyTimeChart from './StudyTimeChart';

class StudyTime extends React.Component {
  render() {
    return (
      <Box
        sx={{
          width: "860px",
          height: "140px",
          // marginLeft: "15px",
          borderRadius: "30px",
          backgroundColor: "#FFFFFF",
          boxShadow: "5px 5px 8px rgba(0, 0, 0, 0.35)",
          paddingX: "40px",
          paddingY: "40px"
        }}>
        
        <h3>총 공부 20시간</h3>
        <StudyTimeChart/>
      </Box>
    );
  }
}

export default StudyTime;