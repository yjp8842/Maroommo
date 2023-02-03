import React from "react";
import styled from "styled-components";
// import Box from '@mui/material/Box';

function JoinRoomModal({ onClose }) {
  const handleClose = () => {
    onClose?.();
  };

  return (
    <Overlay>
      <ModalWrap>
        <Contents>
          <h1>마룸모 참가하기</h1>
          <InputWithLabel label="| 초대링크" name="URL" placeholder="maroommo.com/ssafyA406" type="URL"/>
          <InputWithLabel label="| 비밀번호" name="password" placeholder="비밀번호" type="password"/>

          <BtnDiv>
            <CButton onClick={handleClose}>뒤로</CButton>
            <CButton onClick={handleClose}>참가하기</CButton>
          </BtnDiv>
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


// const Button = styled.button`
//   width: 400px;
//   height: 100px;
//   padding: 2%;
//   margin: 20px;
//   font-size: 24px;
//   background-color: #ffffff;
//   border-radius: 25px;
//   color: black;
//   font-weight: 200;
//   box-Shadow: 5px 5px 8px;
//   cursor: pointer;
//   &:hover {
//     background-color: #fac2be;
//   }
// `;

const Overlay = styled.div`
  // position: fixed;
  top: 0;
  left: 0;
  height: 100%;
  width: 100%;
  background: rgba(0, 0, 0, 0.2);
  z-index: 9999;
`;

const ModalWrap = styled.div`
  position: absolute;
  width: 600px;
  height: 550px;
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
  margin-top: 50px;
  // margin-left: 20px;
  background-color: #ffffff;
  border-radius: 15px;
  box-Shadow: 5px 5px 8px;
  cursor: pointer;
  &:hover {
    background-color: #898989;
  }
`;

const BtnDiv = styled.div`
  width: 40%;
  display: flex;
  justify-content: space-between;
`

export default JoinRoomModal;
