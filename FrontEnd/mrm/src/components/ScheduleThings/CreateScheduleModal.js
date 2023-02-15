import React, { useState } from "react";
import styled from "styled-components";

import "react-datepicker/dist/react-datepicker.css"
import { useSelector, useDispatch } from 'react-redux';

import Box from '@mui/material/Box';
import { AdapterDayjs } from '@mui/x-date-pickers/AdapterDayjs';
import { LocalizationProvider } from '@mui/x-date-pickers/LocalizationProvider';
import { DatePicker } from '@mui/x-date-pickers/DatePicker';

import { postScheduleData } from "./ScheduleLogic";

const CreateScheduleModal = ({ onClose }) => {
    const handleClose = () => {
      onClose?.();
    }; 
    const dispatch = useDispatch()
    
    const {user, group} = useSelector((state) => ({
      user: state.userInfoReducers.user,
      group: state.groupInfoReducers.group
    }))
  
    const [ roomId, setroomId ] = useState('');
    const [ content, setcontent ] = useState('');
    const [ selectedDate, setSelectedDate ] = useState('');

    // const onChange1 = e => {setroomId(e.target.value)}
    const onChange2 = e => {setcontent(e.target.value)}

    return (
      <Overlay>
        <ModalWrap>
        <InputWithLabel 
          id="roomId"
          label="| 그룹명" 
          // placeholder={group.name}
          value={group.name}
          // onChange={onChange1}
        />
        <InputWithLabel 
          id="content"
          label="| 내용" 
          placeholder="Chapter1 수강"
          onChange={onChange2}
        />
         
        <LocalizationProvider dateAdapter={AdapterDayjs}>
          <DatePicker
            label="Custom input"
            value={selectedDate}
            onChange={(newValue) => {
              setSelectedDate(newValue);
            }}
            renderInput={({ inputRef, inputProps, InputProps }) => (
            <Box sx={{ display: 'flex', alignItems: 'center' }}>
                <InputWithLabel 
                  id="date"
                  label="| 날짜" 
                  ref={inputRef} {...inputProps} 
                />
              {/* <input ref={inputRef} {...inputProps} /> */}
              {InputProps?.endAdornment}
            </Box>
              )}
              />
          </LocalizationProvider>

        <CloseButton onClick={() => {
          postScheduleData({ user, group, content, selectedDate,dispatch });
          handleClose();}}
          >등록</CloseButton>
        <CloseButton onClick={handleClose}>뒤로</CloseButton>

        </ModalWrap>
      </Overlay>
      );
    };
  
  export default React.memo(CreateScheduleModal);

const Overlay = styled.div`
  position: fixed;
  top: 0;
  left: 0;
  height: 100%;
  width: 100%;
  background: rgba(0, 0, 0, 0.2);
  z-index: 100;
`;

const ModalWrap = styled.div`
  position: absolute;
  width: 650px;
  height: 550px;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  border-radius: 30px;
  background-color: #4A4A4A;
  top: 50%;
  left: 50%;
  box-Shadow: 5px 5px 8px;
  transform: translate(-50%, -50%);
`;

const Label = styled.div` 
  // float: left;
  text-align: left;
  font-size: 20px;
  color: white;
  margin-bottom: 0.5rem;
`;

const Input = styled.input`
  width: 350px;
  height: 60px;
  outline: none;
  border-radius: 15px;
  // line-height: 2.5rem;
  font-size: 20px;
  padding-left: 1rem;
  padding-right: 0.5rem;
`;

const Wrapper = styled.div`
    & + & {
        margin-top: 1rem;
    }
`;

const InputWithLabel = ({label, ...rest}) => (
  <Wrapper>
    <Label>{label}</Label>
    <Input {...rest}/>
  </Wrapper>
);

const CloseButton = styled.button`
  width: 110px;
  height: 60px;
  font-size: 20px;
  margin-top: 10px;
  // margin-left: 20px;
  background-color: #ffffff;
  border-radius: 15px;
  box-Shadow: 5px 5px 8px;
  cursor: pointer;
  &:hover {
    background-color: #898989;
  }
`;

 
// <input
// name="roomId"
// placeholder="그룹명"
// onChange={onChange}
// value={roomId}
// />
// <input
// name="Schedule_tag"
// placeholder="태그"
// onChange={onChange}
// value={Schedule_tag}
// />
// <input
// name="content"
// placeholder="내용"
// onChange={onChange}
// value={content}
// />