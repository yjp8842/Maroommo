import React, { useEffect, useState } from "react";
import { shallowEqual, useDispatch, useSelector } from "react-redux";
import { useNavigate } from 'react-router-dom';
// import { Form } from "react-router-dom";
import styled from "styled-components";
import PictureUploader from "../../ImageUpload/PictureUploader";
import { userInfoActions } from "../../../slice/userInfoSlice";
import api from "../../../utils/axiosInstance";

function UserProfileModal({ onClose }) {
  const navigate = useNavigate();

  const handleClose = () => {
    onClose?.();
  };

  const {user} = useSelector((state) => ({
    user: state.userInfoReducers.user
  }), shallowEqual)

  const dispatch = useDispatch()
  const [nicknameValue, setNicknameValue] = useState(user.nickname)
  const [introValue, setIntroValue] = useState(user.intro)

  const [image, setImage] = useState({
    image_file: "",
    preview_URL: 'images/user.jpg',
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

  const onChangeNickname= e => {
    setNicknameValue(e.target.value)
  }
  const onChangeIntro= e => {
    setIntroValue(e.target.value)
  }

  const onSubmitProfile = (event) => {
    event.preventDefault();
    // console.log(introValue, nicknameValue)
    // console.log('이건 프로필 이미지',image.image_file)

    const formdata = new FormData();
    formdata.append('profileImage', image.image_file)
    // console.log(formdata)
    // for (let key of formdata.entries()) {
    //     console.log("이것은 폼 데이터",key[0], key[1])
    // }

    if(image.image_file){
      api.post(
        `/room/user?intro=${introValue}&nickname=${nicknameValue}&name=${user.name}`,
        formdata, {
          headers : {
            "Content-Type": 'multipart/form-data'
          }
        })
        .then((res)=>{
        // console.log(res);
        // console.log(formdata)
        userInfoActions.modifyUserInfo(res.data.user);
        alert('회원 정보가 수정되었습니다'); 
        window.location.reload();
      })
      .catch((err) => {
        alert('수정 중 오류가 발생했습니다.');
      })
    }
    else{      
      api.post(
        `/room/user?intro=${introValue}&nickname=${nicknameValue}&name=${user.name}`)
        .then((res)=>{
        // console.log(res);
        // console.log(formdata)
        userInfoActions.modifyUserInfo(res.data.user);
        alert('회원 정보가 수정되었습니다');
        window.location.reload();
      })
      .catch((err) => {
        alert('수정 중 오류가 발생했습니다.');
      })
    }


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
            
              <InputWithLabel onChange={onChangeNickname} label="| 사용자명" id="username" placeholder={user.nickname} name='nickname'/>
              <InputWithLabel onChange={onChangeIntro} label="| 한줄소개" id="userintro" placeholder={user.intro} name='intro' />
            
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

export default UserProfileModal;