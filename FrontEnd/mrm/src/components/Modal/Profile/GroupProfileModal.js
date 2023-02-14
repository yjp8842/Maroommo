import React from "react";
import styled from "styled-components";
import PictureUploader from "../../ImageUpload/PictureUploader";

function GroupProfileModal({ onClose }) {

  const handleClose = () => {
    onClose?.();
  };

  const pushData = () => {
    let groupname = document.getElementById('groupname').value;
    let groupintro = document.getElementById('groupintro').value;
    if (groupname && groupintro) {
      localStorage.setItem('groupname', groupname);
      localStorage.setItem('groupintro', groupintro);
    }
  }

  let nameValue = localStorage.getItem('groupname');
  let introValue = localStorage.getItem('groupintro');

  return (
    <div>
      <Overlay>
        <ModalWrap>
          <Contents>
            <h1>프로필 수정하기</h1>
  
            <ProfilePicture>
              <PictureUploader />
            </ProfilePicture>
            <form>
            
              <InputWithLabel label="| 사용자명" id="groupname" placeholder={nameValue} />
              <InputWithLabel label="| 한줄소개" id="groupintro" placeholder={introValue} />
            
              <CButton onClick={handleClose}>취소</CButton>
              <CButton type="submit" onClick={pushData}>수정</CButton>
            </form>
          </Contents>
        </ModalWrap>
      </Overlay>
    </div>
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
  width: 450px;
  height: 50px;
  // width: 100%;
  outline: none;
  border-radius: 15px;
  line-height: 2.5rem;
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
  width: 680px;
  height: 750px;
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

const Contents = styled.div`
  // position: absolute;
  // margin-top: 20px;
  // vertical-align: middle;
  text-align: center;
  h1 {
    color: white;
    font-size: 40px;
    font-weight: 600;
    // text-align: center;
  }
`;

const CButton = styled.button`
  // float : right;
  width: 110px;
  height: 60px;
  margin-top: 20px;
  margin-left: 10px;
  // margin: 30px;
  font-size: 20px;
  // border: none;
  background-color: #ffffff;
  border-radius: 20px;
  box-Shadow: 5px 5px 8px;
  cursor: pointer;
  &:hover {
    background-color: #898989;
  }
`;

export default GroupProfileModal;
