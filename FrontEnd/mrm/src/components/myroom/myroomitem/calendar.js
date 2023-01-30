import React, { useState } from 'react';
import { Box } from '@mui/system';
import CalendarModal from '../../Modal/TodoModal'
import styled from "styled-components";

const CalendarBox = () => {

  const [isOpen, setIsOpen] = useState(false);

  const onClickButton = () => {
    setIsOpen(true);
  };

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
    <Button onClick={onClickButton}>+</Button>
      {isOpen && (<CalendarModal
        open={isOpen}
        onClose={() => {
          setIsOpen(false);
        }}
      />)}
    </Box>
  );
}

const Button = styled.button`
  font-size: 40px;
  padding: 10px 20px;
  border: none;
  background-color: #ffffff;
  border-radius: 10px;
  color: black;
  font-style: italic;
  font-weight: 200;
  cursor: pointer;
  &:hover {
    background-color: #fac2be;
  }
`;

export default CalendarBox;