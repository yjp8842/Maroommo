import React, { useState } from "react";
import styled from "styled-components";
// import Box from '@mui/material/Box';
import JoinRoomModal from "./JoinGroup";
import CreateRoomModal from "./CreateGroup";

// function JoinRoomModal({ onClose }) {
//   const handleClose = () => {
//     onClose?.();
//   };

//   return (
//     <Overlay>
//       <ModalWrap>
//         <Contents>
//           <h1>마룸모 참가하기</h1>
//           <InputWithLabel label="| 초대링크" name="URL" placeholder="maroommo.com/ssafyA406" type="URL"/>
//           <InputWithLabel label="| 비밀번호" name="password" placeholder="비밀번호" type="password"/>

//           <div>
//             <CButton onClick={handleClose}>뒤로</CButton>
//             <CButton onClick={handleClose}>참가하기</CButton>
//           </div>
//         </Contents>
//       </ModalWrap>
//     </Overlay>
//   );
// }

// function CreateRoomModal({ onClose }) {
//   const handleClose = () => {
//     onClose?.();
//   };

//   return (
//     <Overlay>
//       <ModalWrap>
//         <Contents>
//           <h1>마룸모 생성하기</h1>

//           <InputWithLabel label="| 그룹명" name="roomname" placeholder="React 기초반" type="roomname"/>
//           <InputWithLabel label="| 비밀번호" name="password" placeholder="***********" type="password"/>
//           <InputWithLabel label="| 한줄소개" name="introduction" placeholder="React를 시작하는 사람들의 모임입니다. " type="introduction"/>

//           <div>
//             <CButton onClick={handleClose}>뒤로</CButton>
//             <CButton onClick={handleClose}>생성하기</CButton>
//           </div>
//         </Contents>
//       </ModalWrap>
//     </Overlay>
//   );
// }

export default function RoomModal({ onClose }) {
  const handleClose = () => {
    onClose?.();
  };
  
  const [JoinroomisOpen, setIsOpen1] = useState(false);
  
  const [CreateroomisOpen, setIsOpen2] = useState(false);

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
          {JoinroomisOpen && (<JoinRoomModal
            open={JoinroomisOpen}
            onClose={() => {
              setIsOpen1(false);
            }}
          />)}

          <Button onClick={onClickButton2}>그룹 생성하기 &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; ▶</Button>
          {CreateroomisOpen && (<CreateRoomModal
            open={CreateroomisOpen}
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

const Button = styled.button`
  width: 400px;
  height: 100px;
  padding: 2%;
  margin: 20px;
  font-size: 24px;
  background-color: #ffffff;
  border-radius: 25px;
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