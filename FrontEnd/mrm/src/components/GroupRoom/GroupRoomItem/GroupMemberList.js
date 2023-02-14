import React from "react"
import { Box } from '@mui/system';

function GroupMemberList(props) {
    return (
      <Box
        sx={{
          display: 'flex',
          alignItems: 'center',
          justifyContent: 'center',
          width: "220px",
          height: "40px",
          marginTop: "10px",
          borderRadius: "20px",
          backgroundColor: "#FFFFFF",
        //   boxShadow: "5px 5px 8px rgba(0, 0, 0, 0.35)",
        }}>
        <h2>- {props.user.name}</h2>
      </Box>
    )
}

export default GroupMemberList;