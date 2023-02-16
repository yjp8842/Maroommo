import React from 'react';
import { Box } from '@mui/system';
import { Avatar } from '@mui/material';
// import { green } from '@mui/material/colors';

function UserPageIcon(props) {
  console.log(props)

  const getProfile = props.user.profile;
  console.log(getProfile)
  return (
    <Box
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
        {/* {props.picture
        ? <img src={`/images/${props.picture}`} alt='logo' className='imgbox'></img>
        : 

} */}
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
  );
}


export default UserPageIcon;