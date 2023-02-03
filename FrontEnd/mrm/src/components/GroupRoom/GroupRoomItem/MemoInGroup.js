import React from "react";
import { Box } from "@mui/system";

class MemoBox extends React.Component {
  render() {
    return (
      <Box
        sx={{
          display: 'flex',
          justifyContent: 'space-evenly',
          width: '1000px'
        }}>
        <Box
          sx={{
            width: "450px",
            height: "250px",
            marginTop: "20px",
            paddingY: '20px',
            borderRadius: "30px",
            backgroundColor: "#FFFFFF",
            boxShadow: "5px 5px 8px rgba(0, 0, 0, 0.35)",
            display: 'flex',
            justifyContent: 'center'
          }}>
            <h3>My MEMO</h3>
        </Box>
        <Box
          sx={{
            width: "450px",
            height: "250px",
            marginTop: "20px",
            paddingY: '20px',
            borderRadius: "30px",
            backgroundColor: "#FFFFFF",
            boxShadow: "5px 5px 8px rgba(0, 0, 0, 0.35)",
            display: 'flex',
            justifyContent: 'center'
          }}>
            <h3>Group MEMO</h3>
        </Box>
      </Box>
    )
  }
}

export default MemoBox;