import React, { useState } from "react";
import styled from "styled-components";
// import Box from '@mui/material/Box';
import TextField from '@mui/material/TextField';

function JoinRoomModal({ onClose }) {
  const handleClose = () => {
    onClose?.();
  };

  return (
      <Overlay>
        <ModalWrap>
          <Contents>
            <h1>마룸모 참가하기</h1>

            <div>
              <TextField
              id="outlined-textarea"
              label="Multiline Placeholder"
              placeholder="Placeholder"
              multiline
              />
            </div>

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
  
  const [isOpen, setIsOpen] = useState(false);

  const onClickButton = () => {
    setIsOpen(true);
  };

  return (
      <Overlay>
        <ModalWrap>
          <Contents>
            <h1>마룸모 시작하기</h1>

              <Button onClick={onClickButton}>그룹 참가하기 &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; ▶</Button>
              {isOpen && (<JoinRoomModal
                open={isOpen}
                onClose={() => {
                  setIsOpen(false);
                }}
              />)}

              <Button onClick={onClickButton}>그룹 생성하기 &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; ▶</Button>
              {isOpen && (<CreateRoomModal
                open={isOpen}
                onClose={() => {
                  setIsOpen(false);
                }}
              />)}

              <CButton onClick={handleClose}>뒤로</CButton>
          </Contents>
        </ModalWrap>
      </Overlay>
  );
}


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
  margin-top: 30px;
  margin-right: 70px;
  margin-bottom: 30px;
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