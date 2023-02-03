import React from "react";
// import styled from "styled-components";
// import DatePicker from 'react-datepicker'
import "react-datepicker/dist/react-datepicker.css"
// import { formatDate } from "../../utils";
// import { PERIOD } from "../DatePicker/constants";
// import Dropdown from "../DatePicker/Dropdown";
// import CustomDatePicker from "../DatePicker/DatePicker";
// import TodoToggle from "./TodoToggle";

// import TodoTemplate from "../DnD/TodoTemplate";
// import TodoHead from "../DnD/TodoHead";
// import TodoList from "../DnD/TodoList";
import TodoCreate from "../../DnD/TodoCreate";
import { TodoProvider } from "../../DnD/TodoContext";
import './TodoModal.css';

  function Modal({ onClose }) {
  //   const handleClose = () => {
  //     onClose?.();
  //   };

  // const [isDropdownOpen, setIsDropdownOpen] = useState(false);
  // const [startDate, setStartDate] = useState(new Date());

  // const toggleDropdown = () => {
  //   setIsDropdownOpen((prev) => !prev);
  // };


  return (

      <TodoProvider>
        <TodoCreate />
      </TodoProvider>
    
    
    // <Overlay>
    //   <ModalWrap>
    //     <Contents>
    //       <h1>내 일정 추가하기</h1>

    //       <TodoToggle />

    //       <InputWithLabel label="| 할 일" name="todo" placeholder="React Chapter1 과제" type="todo"/>
          
    //       <InBox>
    //         <CustomDatePicker
    //           selectedDate={startDate}
    //           setSelectedDate={setStartDate}
    //         />
    //       </InBox>

    //       <CButton onClick={handleClose}>취소</CButton>
    //       <CButton onClick={handleClose}>참가</CButton>
    //     </Contents>
    //   </ModalWrap>
    // </Overlay>
  );
}

// const InBox = styled.div`
//   display: flex;
//   justify-content: space-between;
//   align-items: center;
//   margin-top: 30px;
// `

// const Overlay = styled.div`
//   position: fixed;
//   top: 0;
//   left: 0;
//   height: 100%;
//   width: 100%;
//   background: rgba(0, 0, 0, 0.2);
//   z-index: 9999;
// `;

// const ModalWrap = styled.div`
//   position: absolute;
//   width: 650px;
//   height: 550px;
//   display: flex;
//   flex-direction: column;
//   align-items: center;
//   justify-content: center;
//   border-radius: 30px;
//   background-color: #4A4A4A;
//   top: 50%;
//   left: 50%;
//   box-Shadow: 5px 5px 8px;
//   transform: translate(-50%, -50%);
// `;

// const Contents = styled.div`
//   position: absolute;
//   // vertical-align: middle;
//   text-align: center;
//   h1 {
//     color: white;
//     font-size: 40px;
//     font-weight: 600;
//     // margin-top: 60px;
//     // margin-bottom: 20px;
//   }
// `;

// const CButton = styled.button`
//   // float : right;
//   width: 110px;
//   height: 60px;
//   margin-top : 50px;
//   margin-left: 20px;
//   font-size: 20px;
//   border: none;
//   background-color: #ffffff;
//   border-radius: 20px;
//   box-Shadow: 5px 5px 8px;
//   cursor: pointer;
//   &:hover {
//     background-color: #898989;
//   }
// `;

// const Label = styled.div` 
//   // float: left;
//   text-align: left;
//   font-size: 20px;
//   color: white;
//   margin-bottom: 0.5rem;
// `;

// const Input = styled.input`
//   width: 450px;
//   height: 50px;
//   // width: 100%;
//   outline: none;
//   border-radius: 15px;
//   line-height: 2.5rem;
//   font-size: 20px;
//   padding-left: 1rem;
//   padding-right: 0.5rem;
// `;

// const Wrapper = styled.div`
//   & + & {
//     margin-top: 1rem;
//   }
// `;

// const InputWithLabel = ({label, ...rest}) => (
//   <Wrapper>
//     <Label>{label}</Label>
//     <Input {...rest}/>
//   </Wrapper>
// );

export default Modal;



// const [isDropdownOpen, setIsDropdownOpen] = useState(false);
// const [selectedPeriod, setSelectedPeriod] = useState(PERIOD[3].name);
// const [startDate, setStartDate] = useState(new Date());
// const [endDate, setEndDate] = useState(new Date());
  // const onClickPeriod = (e) => {
  //   const { value } = e.target;
  //   setSelectedPeriod(value);
  //   setDateRange(value);
  //   toggleDropdown();
  // };

  // const setDateRange = (period) => {
  //   const start = new Date(formatDate(new Date()));

  //   if (period === "1주일") {
  //     start.setDate(start.getDate() - 7);
  //   } else if (period.includes("개월")) {
  //     start.setMonth(start.getMonth() - Number(period[0]));
  //   }

  //   setStartDate(period === "전체" ? new Date("2022-01-01") : start);
  //   setEndDate(new Date(formatDate(new Date())));
  // };