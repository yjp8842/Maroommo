import React from 'react';
import { Box } from '@mui/system';
import PersonIcon from '@mui/icons-material/Person';

class Profile extends React.Component {
  render() {
    return (
      <Box
        sx={{
          width: "250px",
          height: "220px",
          marginLeft: "15px",
          borderRadius: "30px",
          backgroundColor: "#FFFFFF",
          boxShadow: "5px 5px 8px rgba(0, 0, 0, 0.35)",
          display: "flex",
          flexDirection: "column",
          alignItems: "center",
          justifyContent: "center",
        }}>
        <PersonIcon
          sx={{
            width: "120px",
            height: "120px",
          }}>
        </PersonIcon>
        <h2>SSAFY</h2>
        <h4>같이 취뽀하자</h4>
      </Box>
    );
  }
}

export default Profile;