import React from 'react';
import { Box } from '@mui/system';

class CalendarBox extends React.Component {
  render() {
    return (
      <Box
        sx={{
          width: "250px",
          height: "220px",
          marginLeft: "15px",
          borderRadius: "30px",
          backgroundColor: "#FFFFFF",
          boxShadow: "5px 5px 8px rgba(0, 0, 0, 0.35)"
        }}>
      </Box>
    );
  }
}

export default CalendarBox;