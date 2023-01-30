import React, { useState } from "react";
import styled from "styled-components";
import './TodoToggle.css';
import { formatDate } from "../../utils";
import { PERIOD } from "../DatePicker/constants";
import Dropdown from "../DatePicker/Dropdown";
import CustomDatePicker from "../DatePicker/DatePicker";
//import ToggleButton from '@mui/material/ToggleButton';
//import ToggleButtonGroup from '@mui/material/ToggleButtonGroup';

function Modal({ onClose }) {
  // const [alignment, setAlignment] = React.useState('web');

  // const handleChange = (event, newAlignment) => {
  //   setAlignment(newAlignment);
  // };

  const handleClose = () => {
    onClose?.();
  };

  const [isDropdownOpen, setIsDropdownOpen] = useState(false);
  const [selectedPeriod, setSelectedPeriod] = useState(PERIOD[3].name);
  const [startDate, setStartDate] = useState(new Date());
  const [endDate, setEndDate] = useState(new Date());

  const toggleDropdown = () => {
    setIsDropdownOpen((prev) => !prev);
  };

  const onClickPeriod = (e) => {
    const { value } = e.target;
    setSelectedPeriod(value);
    setDateRange(value);
    toggleDropdown();
  };

  const setDateRange = (period) => {
    const start = new Date(formatDate(new Date()));

    if (period === "1주일") {
      start.setDate(start.getDate() - 7);
    } else if (period.includes("개월")) {
      start.setMonth(start.getMonth() - Number(period[0]));
    }

    setStartDate(period === "전체" ? new Date("2022-01-01") : start);
    setEndDate(new Date(formatDate(new Date())));
  };

  return (
    <Overlay>
    <ModalWrap>
      <Contents>
        <h1>내 일정 추가하기</h1>
        {/* <ToggleButtonGroup
        color="primary"
        value={alignment}
        exclusive
        onChange={handleChange}
        aria-label="Platform"
        >
        <ToggleButton value="할 일">할 일</ToggleButton>
        <ToggleButton value="일정">일정</ToggleButton>
        </ToggleButtonGroup> */}
        <div className="switch-button">
            <input className="switch-button-checkbox" type="checkbox"></input>
            <label className="switch-button-label" for=""><span class="switch-button-label-span">Photo</span></label>
        </div>
        <InputWithLabel label="| 할 일" name="todo" placeholder="React Chapter1 과제" type="todo"/>

        <Dropdown
          isDropdownOpen={isDropdownOpen}
          toggleDropdown={toggleDropdown}
          selectedPeriod={selectedPeriod}
          onClickPeriod={onClickPeriod}
        />
        <CustomDatePicker
          selectedDate={startDate}
          setSelectedDate={setStartDate}
          selectedPeriod={selectedPeriod}
        />
        <span>-</span>
        <CustomDatePicker
          selectedDate={endDate}
          setSelectedDate={setEndDate}
          selectedPeriod={selectedPeriod}
        />

        <CButton onClick={handleClose}>뒤로</CButton>
        <CButton onClick={handleClose}>참가하기</CButton>
      </Contents>
    </ModalWrap>
  </Overlay>
  );
}

const Overlay = styled.div`
  position: fixed;
  top: 0;
  left: 0;
  height: 100%;
  width: 100%;
  background: rgba(0, 0, 0, 0.2);
  z-index: 9999;
`;

const ModalWrap = styled.div`
  position: absolute;
  width: 900px;
  height: 700px;
  display: flex;
  flex-direction: column;
  align-content: center;
  align-items: center;
  justify-content: center;
  border-radius: 50px;
  background-color: #4A4A4A;
  top: 50%;
  left: 50%;
  box-Shadow: 5px 5px 8px;
  transform: translate(-50%, -50%);
`;

const Contents = styled.div`
  position: absolute;
  vertical-align: middle;
  h1 {
    color: white;
    font-size: 50px;
    font-weight: 600;
    margin-top: 60px;
    margin-bottom: 55px;
  }
`;

const CButton = styled.button`
  float : right;
  width: 130px;
  height: 70px;
  margin: 30px;
  margin-top : 70px;
  font-size: 24px;
  border: none;
  background-color: #ffffff;
  border-radius: 10px;
  font-family: 'GangwonEdu_OTFBoldA';
  box-Shadow: 5px 5px 8px;
  cursor: pointer;
  &:hover {
    background-color: #898989;
  }
`;

const Label = styled.div` 
  float: left;
  font-size: 2rem;
  color: white;
  font-family: 'GangwonEdu_OTFBoldA';
  margin-bottom: 0.5rem;
`;

const Input = styled.input`
    width: 450px;
    height: 50px;
    width: 100%;
    outline: none;
    border-radius: 20px;
    line-height: 2.5rem;
    font-size: 1.5rem;
    font-family: 'GangwonEdu_OTFBoldA';
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

export default Modal;
