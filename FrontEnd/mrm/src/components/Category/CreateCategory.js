import React, { useState } from "react";
import styled from "styled-components";
// import Box from '@mui/material/Box';
// import JoinRoomModal from "./JoinGroup";
// import CreateRoomModal from "./CreateGroup";

import { postCategoryData } from "./CategoryLogic";

export default function CategoryModal({ onClose }) {
  const handleClose = () => {
    onClose?.();
  };

  const [ name, setname ] = useState('');
  
  const onChange = e => {setname(e.target.value)}

  return (
    <Overlay>
      <ModalWrap>
        <Contents>
            <h1>카테고리 생성하기</h1>
            <InputWithLabel 
            id="name"
            label="| 이름" 
            placeholder="카테고리"
            onChange={onChange}
            />
            <CButton onClick={() => {
                // postCalendarData({ name, roomId });
                handleClose();}}
            >생성</CButton>
            <CButton onClick={() => {
            postCategoryData({ name });
            handleClose();}}
            >뒤로</CButton>
        </Contents>
      </ModalWrap>
    </Overlay>
  );
}

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
  width: 570px;
  height: 500px;
  display: flex;
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
  width: 600px;
  height: 550px;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  border-radius: 50px;
  h1 {
    color: white;
    font-size: 40px;
    font-weight: 600;
    margin-bottom: 20px;
  }
`;

const CButton = styled.button`
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