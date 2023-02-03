import React from 'react';
import { Box } from '@mui/system';
import CalendarModal from './CalendarModal';
// import TodoModal from '../../Modal/TodoModal';
// import styled from "styled-components";

const CalendarBox = () => {

  // const [isOpen, setIsOpen] = useState(false);

  // const onClickButton = () => {
  //   setIsOpen(true);
  // };
  return (
    <Box
      >
      <CalendarModal/>
      {/* 달력에서 할일/일정 추가하는 버튼 */}
      {/* <Button onClick={onClickButton}>+</Button>
      {isOpen && (<TodoModal
        open={isOpen}
        onClose={() => {
          setIsOpen(false);
        }}
      />)} */}
    </Box>
  );
}

// const Button = styled.button`
//   font-size: 40px;
//   padding: 10px 20px;
//   border: none;
//   background-color: #ffffff;
//   border-radius: 10px;
//   color: black;
//   font-style: italic;
//   font-weight: 200;
//   cursor: pointer;
//   &:hover {
//     background-color: #fac2be;
//   }
// `;

export default CalendarBox;