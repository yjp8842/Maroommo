import React from 'react';
import { Box } from '@mui/system';

class StudyTime extends React.Component {
  render() {
    return (
      <Box
        sx={{
          width: "900px",
          height: "180px",
          marginLeft: "15px",
          borderRadius: "30px",
          backgroundColor: "#FFFFFF",
          boxShadow: "5px 5px 8px rgba(0, 0, 0, 0.35)",
          paddingX: "20px",
          paddingY: "20px"
        }}>
        <h3>총 공부 20시간</h3>
      </Box>
    );
  }
}

export default StudyTime;