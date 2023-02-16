import React, { useEffect, useState } from "react";
import {useDispatch, useSelector } from "react-redux";
import styled from "styled-components";
import PictureUploader from "../../ImageUpload/PictureUploader";
import { groupInfoActions } from "../../../slice/groupInfoSlice";
import api from "../../../utils/axiosInstance";

function GroupProfileModal({ onClose }) {
  const dispatch = useDispatch();

  const {user, group} = useSelector((state) => ({
    user: state.userInfoReducers.user,
    group: state.groupInfoReducers.group,
  }))

  const handleClose = () => {
    onClose?.();
  };

  const [nameValue, setNameValue] = useState(group.name)
  const [introValue, setIntroValue] = useState(group.intro)

  const [image, setImage] = useState({
    image_file: group.profile,
    preview_URL: group.profile ? 'images/'+group.profile : 'images/user.jpg',
  });

  const saveImage = (e) => {
    e.preventDefault();
    if(e.target.files[0]){
      // 새로운 이미지를 올리면 createObjectURL()을 통해 생성한 기존 URL을 폐기
      URL.revokeObjectURL(image.preview_URL);
      const preview_URL = URL.createObjectURL(e.target.files[0]);
      setImage(() => (
        {
          image_file: e.target.files[0],
          preview_URL: preview_URL
        }
      ))
      console.log('saveImage작동')
      console.log(image)
    }
  }

  const deleteImage = () => {
    // createObjectURL()을 통해 생성한 기존 URL을 폐기
    URL.revokeObjectURL(image.preview_URL);
    setImage({
      image_file: "",
      preview_URL: "images/user.jpg",
    });
  }

  useEffect(()=> {
    // 컴포넌트가 언마운트되면 createObjectURL()을 통해 생성한 기존 URL을 폐기
    return () => {
      URL.revokeObjectURL(image.preview_URL)
    }
  })

  const onChangeName= e => {
    setNameValue(e.target.value)
  }
  const onChangeIntro= e => {
    setIntroValue(e.target.value)
  }

  const onSubmitProfile = (event) => {
    event.preventDefault();

    // console.log(introValue, nameValue)
    // console.log('이건 프로필 이미지',image)

    const formdata = new FormData();
    formdata.append('profile', image.image_file)
    // console.log(formdata)

    
    if(image.image_file){
      api.post(
        `/room/modify?intro=${introValue}&name=${nameValue}&roomId=${group.id}`,
        formdata, {
          // headers : {
          //   "Content-Type": 'multipart/form-data'
          // }
        })
        .then((res)=>{
        console.log('이거 res!!',res);
        // console.log(formdata)
        dispatch(groupInfoActions.modifyGroupInfo(res.data.room));

        alert('그룹 정보가 수정되었습니다'); 
        // window.location.reload();

      })
      .catch((err) => {
        alert('수정 중 오류가 발생했습니다.');
      })
    }
    else{      
      api.post(
        `/room/modify?intro=${introValue}&name=${nameValue}&roomId=${group.id}`)
        .then((res)=>{
          console.log('없는 res!!',res);
        // console.log(res);
        // console.log(formdata)
        alert('그룹 정보가 수정되었습니다');
        // window.location.reload();
      })
      .catch((err) => {
        alert('수정 중 오류가 발생했습니다.');
      })
    }

    // window.location.reload();
  }


  return (
    <div>
      <Overlay>
        <ModalWrap>
          <Contents>
            <h1>프로필 수정하기</h1>
  
            <ProfilePicture>
              <PictureUploader image={image} saveImage={saveImage} deleteImage={deleteImage}  />
            </ProfilePicture>
            <form>

              <InputWithLabel onChange={onChangeName}  label="| 그룹명" id="groupname" placeholder={group.name} name='groupname'/>
              <InputWithLabel onChange={onChangeIntro} label="| 한줄소개" id="groupintro" placeholder={group.intro} name='groupintro' />

              
              <InputWithLabel label="| 초대링크" id="groupcode" value={`https://i8a406.p.ssafy.io/api/room/enter/${group.id}?roomCode=${group.code}`} name='groupcode' />
              {/* <Label> : {}</Label> */}
              <CButton type="submit" onClick={onSubmitProfile}>수정</CButton>
              <CButton onClick={handleClose}>취소</CButton>
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
