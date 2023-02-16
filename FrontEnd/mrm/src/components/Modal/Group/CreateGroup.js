import React, { useState } from "react";
import styled from "styled-components";
import api from "../../../utils/axiosInstance";
import { useSelector, shallowEqual, useDispatch } from "react-redux";
import { userInfoActions } from "../../../slice/userInfoSlice";
import { groupInfoActions} from "../../../slice/groupInfoSlice";
import { scheduleActions } from "../../../slice/scheduleSlice";


function CreateRoomModal({ onClose }) {
  const handleClose = () => {
    onClose?.();
  };

  const {user} = useSelector((state) => 
  ({
    user: state.userInfoReducers.user,
  }), shallowEqual)

  const dispatch = useDispatch();

  const [ groupName, setGroupName ] = useState('')

  const onChangegroupName= e => {
    setGroupName(e.target.value)
  }

  const [ groupIntro, setGroupIntro ] = useState('')

  const onChangeGroupIntro= e => {
    setGroupIntro(e.target.value)
  }

  const onSubmitJoinRoom = (event) => {
    console.log("groupName ===", groupName)
    console.log("groupIntro ===", groupIntro)
    const data = {
      name: groupName,
      intro: groupIntro
    };

    console.log(data);

    api.post(`/room`, data)
    .then((res) => {
      console.log("그룹 생성 완료!");
      console.log(res);
      dispatch(userInfoActions.saveMyRoomInfo(res.data.myRoomInfo));
      dispatch(scheduleActions.saveSchedule(res.data.myRoomInfo.schedules));
      alert("그룹 생성을 완료하였습니다.");
      onClose?.();
    })
    .catch((err) => {
      console.log(err);
      alert("그룹 생성 중 오류가 발생했습니다.");
    });

  }

  return (
    <Overlay>
      <ModalWrap>
        <Contents>
          <h1>마룸모 생성하기</h1>

          <InputWithLabel onChange={onChangegroupName} label="| 그룹명" name="roomname" placeholder="예시) React 기초반" type="roomname"/>
          <InputWithLabel onChange={onChangeGroupIntro} label="| 한줄소개" name="introduction" placeholder="예시) React를 시작하는 사람들의 모임입니다. " type="introduction"/>

          <div>
            <CButton onClick={handleClose}>뒤로</CButton>
            <CButton onClick={() => {
              onSubmitJoinRoom()}}
              >생성하기</CButton>
          </div>
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
  height: 580px;
  display: flex;
  align-items: center;
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
  margin-top: 20px;
  margin-left: 20px;
  background-color: #ffffff;
  border-radius: 15px;
  box-Shadow: 5px 5px 8px;
  cursor: pointer;
  &:hover {
    background-color: #898989;
  }
`;

export default CreateRoomModal;