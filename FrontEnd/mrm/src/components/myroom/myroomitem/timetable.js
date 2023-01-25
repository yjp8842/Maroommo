import React from 'react';
import { Box } from '@mui/system';

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
      </Box>
    );
  }
}

export default TimeTable;