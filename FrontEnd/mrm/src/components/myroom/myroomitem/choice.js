import React from 'react';
import { Box } from '@mui/system';

class Choice extends React.Component {
  render() {
    return (
      <Box
        type="button"
        sx={{
          width: "5rem",
          height: "5rem",
          marginLeft: "20px",
          marginRight: "20px",
          backgroundColor: "#FFFFFF",
          borderRadius: "15px",
          transform: "rotate(45deg)",
          boxShadow: "5px 5px 8px rgba(0, 0, 0, 0.35)",
          // border: "2px solid #4A4A4A",
          ":hover": {
            transform: "rotate(0)",
            transition: "0.8s"
          },
        }}>
      </Box>
    );
  }
}

export default Choice;