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
          backgroundColor: "#dcdcdc",
          boxShadow: "3px 3px 5px rgba(0, 0, 0, 0.35)",
        }}>
        <h2>{props.user.nickname}</h2>
      </Box>
    )
}

export default GroupMemberList;