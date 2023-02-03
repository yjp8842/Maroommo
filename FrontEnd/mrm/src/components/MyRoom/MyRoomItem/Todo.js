import React from 'react';
import { Box } from '@mui/system';
import TodoInput from './TodoInput';

class Todo extends React.Component {
  render() {
    return (
      <Box
        sx={{
          width: "300px",
          height: "220px",
          // marginLeft: "15px",
          borderRadius: "30px",
          backgroundColor: "#FFFFFF",
          boxShadow: "5px 5px 8px rgba(0, 0, 0, 0.35)"
          
        }}>
        <TodoInput/>
      </Box>
    );
  }
}

export default Todo;