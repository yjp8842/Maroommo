import React, { useState } from "react";
import styled from "styled-components";

import MenuItem from '@mui/material/MenuItem';
import FormControl from '@mui/material/FormControl';
import Select from '@mui/material/Select';

import { postSubCategoryData } from "./SubCategoryLogic";

export default function SubCategoryModal({ onClose }) {
  const handleClose = () => {
    onClose?.();
  };

  const [ category, setcategory ] = useState('');
  const [ name, setname ] = useState('');
  
  const onChange1 = e => {setname(e.target.value)}

  const handleChange = (event) => {
    setcategory(event.target.value);
  };
  
  return (
    <Overlay>
      <ModalWrap>
        <Contents>
            <h1>서브 카테고리 생성하기</h1>

            {/* 드랍다운으로 카테고리 만들기 */}

            <FormControl sx={{ m: 1, minWidth: 120 }}>
                <Select
                id="demo-simple-select-autowidth"
                value={category}
                onChange={handleChange}
                displayEmpty
                inputProps={{ 'aria-label': 'Without label' }}
                >
                <MenuItem value="">
                    <em>None</em>
                </MenuItem>
                <MenuItem value={10}>Cat1</MenuItem>
                <MenuItem value={20}>Cat2</MenuItem>
                <MenuItem value={30}>Cat3</MenuItem>
                </Select>
            </FormControl>

            <InputWithLabel 
            id="name"
            label="| 이름" 
            placeholder="서브 카테고리"
            onChange={onChange1}
            />

            <CButton onClick={() => {
                postSubCategoryData({ name });
                handleClose();}}
            >생성</CButton>
            <CButton onClick={handleClose}>뒤로</CButton>
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
  z-index: 100;
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

// const dropdownname = styled.div` 
//   // float: left;
//   text-align: left;
//   font-size: 20px;
//   color: white;
//   margin-bottom: 0.5rem;
// `;