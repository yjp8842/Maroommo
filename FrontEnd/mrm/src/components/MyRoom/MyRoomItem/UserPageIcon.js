import React from 'react';
import { Box } from '@mui/system';
import { Avatar } from '@mui/material';
// import { green } from '@mui/material/colors';
import "./Userhover.css";


function UserPageIcon(props) {
  console.log(props)

  const getProfile = props.user.profile;
  console.log("유저 프로필 사진")
  console.log(getProfile)
  return (
    <div>
      <Box
        className={`user-title`}
        sx={{
          width: "4rem",
          height: "4rem",
          marginTop: "25px",
          marginBottom: "25px",
          backgroundColor: "#FFFFFF",
          borderRadius: "15px",
          transform: "rotate(45deg)",
          boxShadow: "5px 5px 8px rgba(0, 0, 0, 0.35)",
          display: "flex",
          alignItems: "center",
          justifyContent: "center",
          ":hover": {
            transform: "rotate(0)",
            transition: "0.8s",
          },
        }}>
        <Avatar 
          sx={{ 
            transform: "rotate(-45deg)",
            ":hover": {
              transform: "rotate(0)",
              transition: "0.8s",
            }
          }}
          // src= {props.room.profile} 
          src= {getProfile ? `/images/${getProfile}` : "/images/user.jpg"} 
        >
        </Avatar>
      </Box>
      <div className="user-text">{props.user.nickname}</div>
    </div>
  );
}


export default UserPageIcon;