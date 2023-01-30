import React, { useState } from "react";
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

            <CButton onClick={handleClose}>뒤로</CButton>
            <CButton onClick={handleClose}>참가하기</CButton>
          </Contents>
        </ModalWrap>
      </Overlay>
  );
}

function CreateRoomModal({ onClose }) {
  const handleClose = () => {
    onClose?.();
  };

  return (
      <Overlay>
        <ModalWrap>
          <Contents>
            <h1>마룸모 생성하기</h1>

            <InputWithLabel label="| 그룹명" name="roomname" placeholder="React 기초반" type="roomname"/>
            <InputWithLabel label="| 비밀번호" name="password" placeholder="***********" type="password"/>
            <InputWithLabel label="| 한줄소개" name="introduction" placeholder="React를 시작하는 사람들의 모임입니다. " type="introduction"/>


              <CButton onClick={handleClose}>뒤로</CButton>

              <CButton onClick={handleClose}>생성하기</CButton>
          </Contents>
        </ModalWrap>
      </Overlay>
  );
}




export default function RoomModal({ onClose }) {
  const handleClose = () => {
    onClose?.();
  };
  
  const [CreateroomisOpen, setIsOpen1] = useState(false);

  const [JoinroomisOpen, setIsOpen2] = useState(false);

  const onClickButton1 = () => {
    setIsOpen1(true);
  };

  const onClickButton2 = () => {
    setIsOpen2(true);
  };

  return (
      <Overlay>
        <ModalWrap>
          <Contents>
            <h1>마룸모 시작하기</h1>

              <Button onClick={onClickButton1}>그룹 참가하기 &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; ▶</Button>
              {CreateroomisOpen && (<JoinRoomModal
                open={CreateroomisOpen}
                onClose={() => {
                  setIsOpen1(false);
                }}
              />)}

              <Button onClick={onClickButton2}>그룹 생성하기 &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; ▶</Button>
              {JoinroomisOpen && (<CreateRoomModal
                open={JoinroomisOpen}
                onClose={() => {
                  setIsOpen2(false);
                }}
              />)}

              <CButton onClick={handleClose}>뒤로</CButton>
          </Contents>
        </ModalWrap>
      </Overlay>
  );
}

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


const Button = styled.button`
  width: 450px;
  height: 100px;
  padding: 2%;
  margin: 20px;
  font-family: 'GangwonEdu_OTFBoldA';
  font-size: 24px;
  background-color: #ffffff;
  border-radius: 20px;
  color: black;
  font-weight: 200;
  box-Shadow: 5px 5px 8px;
  cursor: pointer;
  &:hover {
    background-color: #fac2be;
  }
`;

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
  width: 600px;
  height: 600px;
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