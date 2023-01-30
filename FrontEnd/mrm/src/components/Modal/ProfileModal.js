import React from "react";
import styled from "styled-components";
import PictureUploader from "../ImageUpload/PictureUploader";

function Modal({ onClose }) {

  const handleClose = () => {
    onClose?.();
  };

  return (
      <Overlay>
        <ModalWrap>
          <Contents>
            <h1>프로필 수정하기</h1>

            <ProfilePicture>
                <PictureUploader />
            </ProfilePicture>
            <InputWithLabel label="| 사용자명" name="username" placeholder="김싸피" type="username"/>
            <InputWithLabel label="| 한줄소개" name="introduction" placeholder="같이 취뽀하자!" type="introduction"/>
            <CButton onClick={handleClose}>닫기</CButton>
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

const ProfilePicture = styled.div`
    margin: 30px;
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
  width: 830px;
  height: 740px;
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
  margin-top: 20px;
  vertical-align: middle;
  h1 {
    color: white;
    font-size: 50px;
    font-weight: 600;
  }
`;

const CButton = styled.button`
  float : right;
  width: 130px;
  height: 70px;
  margin: 30px;
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

export default Modal;
