import React, { useState } from 'react';
import styled from 'styled-components';
// import { MdAdd } from 'react-icons/md';
import { useTodoDispatch, useTodoNextId } from './TodoContext';
import { TodoProvider } from './TodoContext'

function TodoCreate({ onClose }) {
  const handleClose = () => {
    onClose?.();
  };

  // const [open, setOpen] = useState(false);
  const [value1, setValue1] = useState('');
  const [value2, setValue2] = useState('');
  const [value3, setValue3] = useState('');
  const dispatch = useTodoDispatch();
  const nextId = useTodoNextId();

  // const onToggle = () => setOpen(!open);
  const onChange1 = e => setValue1(e.target.value);
  const onChange2 = e => setValue2(e.target.value);
  const onChange3 = e => setValue3(e.target.value);
  const onSubmit = e => {
    e.preventDefault();
    dispatch({
      todo: {
        Todo_id: nextId.current,
        Group_id: value1,
        tag: value2,
        text: value3,
        done: false
      }
    });
    nextId.current += 1;
    // setOpen(false);
    setValue1('');
  };

  return (

    <TodoProvider>
      <Overlay>
        <ModalWrap onSubmit={onSubmit}>
            <InputWithLabel
                autoFocus
                label="| Group_id"
                onChange={onChange1}
                Group_id={value1}
                placeholder="그룹명"
              />
              <InputWithLabel
                autoFocus
                label="| Tag"
                onChange={onChange2}
                tag={value2}
                placeholder="태그"
              />
              <InputWithLabel
                autoFocus
                label="| Todo"
                onChange={onChange3}
                text={value3}
                placeholder="내용"
              />

              <CButton onClick={handleClose}>취소</CButton>      
        </ModalWrap>
      </Overlay>
    </TodoProvider>
  );
}

export default React.memo(TodoCreate);


// {/* <CircleButton onClick={onToggle} open={open}>
// <MdAdd />
// </CircleButton> */}

// const CircleButton = styled.button`
//   background: #38d9a9;
//   &:hover {
//     background: #63e6be;
//   }
//   &:active {
//     background: #20c997;
//   }

//   z-index: 5;
//   cursor: pointer;
//   width: 80px;
//   height: 80px;
//   display: block;
//   align-items: center;
//   justify-content: center;
//   font-size: 60px;
//   position: absolute;
//   left: 50%;
//   bottom: 0px;
//   transform: translate(-50%, 50%);
//   color: white;
//   border-radius: 50%;
//   border: none;
//   outline: none;
//   display: flex;
//   align-items: center;
//   justify-content: center;

//   transition: 0.125s all ease-in;
//   ${props =>
//     props.open &&
//     css`
//       background: #ff6b6b;
//       &:hover {
//         background: #ff8787;
//       }
//       &:active {
//         background: #fa5252;
//       }
//       transform: translate(-50%, 50%) rotate(45deg);
//     `}
// `;

// const InsertFormPositioner = styled.div`
//   width: 100%;
//   position: absolute;
// `;

// const InsertForm = styled.form`
//   background: #f8f9fa;
//   padding-left: 32px;
//   padding-top: 32px;
//   padding-right: 32px;
//   padding-bottom: 72px;

//   border-bottom-left-radius: 16px;
//   border-bottom-right-radius: 16px;
//   border-top: 1px solid #e9ecef;
// `;

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

const Label = styled.div` 
  // float: left;
  text-align: left;
  font-size: 20px;
  color: white;
  margin-bottom: 0.5rem;
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
  z-index: 9999;
`;

const ModalWrap = styled.div`
  position: absolute;
  width: 650px;
  height: 550px;
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


          // <InsertForm onSubmit={onSubmit}>
          //   <InputWithLabel>
          //   </InputWithLabel>
          // <Input
          //     autoFocus
          //     onChange={onChange}
          //     value={value}
          //     placeholder="Group_id"
          //   />
          //   <Input
          //     autoFocus
          //     onChange={onChange}
          //     value={value}
          //     placeholder="Tag"
          //   />
          //   <Input
          //     autoFocus
          //     onChange={onChange}
          //     value={value}
          //     placeholder="할 일"
          //   />
          // </InsertForm>