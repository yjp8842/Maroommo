import React, { useRef, useReducer, useMemo, useCallback, useState } from 'react';
import styled, { css } from 'styled-components';

import { useTodoDispatch, useTodoNextId } from './TodoContext';
import { TodoProvider } from './TodoContext'

import CreateTodos from './CreateTodos';
import UserList from './UserList';
import useInputs from './useInputs';
import dayjs from 'dayjs';

function countActiveUsers(users) {
  // console.log('활성 사용자 수를 세는중...');
  return users.filter(user => user.active).length;
}

const initialState = {
  users: [
    {
      id: 1,
      Group_id: 1,
      Todo_tag : 'React',
      Todo_text: 'Chapter2 강의 듣기',
      active: 1,
      Year: 2023, Month: 2, Day: 9
    },
    {
      id: 2,
      Group_id: 1,
      Todo_tag : 'React',
      Todo_text: 'Chapter1 실습',
      active: 0,
      Year: 2023, Month: 2, Day: 9
    },
    {
      id: 3,
      Group_id: 2,
      Todo_tag : 'CS',
      Todo_text: 'Chapter2 강의 듣기',
      active: 0,
      Year: 2023, Month: 2, Day: 9
    }
  ]
};

function reducer(state, action) {
  switch (action.type) {
    case 'CREATE_USER':
      return {
        users: state.users.concat(action.user)
      };
    case 'TOGGLE_USER':
      return {
        ...state,
        users: state.users.map(user =>
          user.id === action.id ? { ...user, active: !user.active } : user
        )
      };
    case 'REMOVE_USER':
      return {
        ...state,
        users: state.users.filter(user => user.id !== action.id)
      };
    default:
      return state;
  }
}

export const UserDispatch = React.createContext(null);

function TodoCreate({ onClose }) {
  const handleClose = () => {
    onClose?.();
  };

  const [ { Group_id, Todo_tag, Todo_text }, onChange, onReset] = useInputs({
    username: '',
    email: ''
  });
  const [state, dispatch] = useReducer(reducer, initialState);
  const nextId = useRef(4);

  const { users } = state;

  const [selectedDate, setSelectedDate] = React.useState(dayjs('2023-04-17'));
  
  const Year = selectedDate.$y;
  const Month = selectedDate.$M + 1;
  const Day = selectedDate.$D;

  const onCreate = useCallback(() => {
    dispatch({
      type: 'CREATE_USER',
      user: {
        id: nextId.current,
        Group_id,
        Todo_tag,
        Todo_text,
        Year, Month, Day
      }
    });
    onReset();
    nextId.current += 1;
  }, [Group_id, Todo_tag,  Todo_text, Year, Month, Day, onReset]);

  const count = useMemo(() => countActiveUsers(users), [users]);

  console.log("selectedDate == ", Year, Month, Day);

  // // const [open, setOpen] = useState(false);
  // const [value, setValue] = useState('');
  // const dispatch = useTodoDispatch();
  // const nextId = useTodoNextId();

  // // const onToggle = () => setOpen(!open);
  // const onChange = e => setValue(e.target.value);
  // const onSubmit = e => {
  //   e.preventDefault();
  //   dispatch({
  //     todo: {
  //       Todo_id: nextId.current,
  //       Group_id: value1,
  //       tag: value2,
  //       text: value3,
  //       done: false
  //     }
  //   });
  //   nextId.current += 1;
  //   // setOpen(false);
  //   setValue('');
  // };

  return (

    <UserDispatch.Provider value={dispatch}>
    <CreateTodos
      Group_id={Group_id}
      Todo_tag={Todo_tag}
      Todo_text={Todo_text}
      selectedDate={selectedDate}
      setSelectedDate={setSelectedDate}
      onChange={onChange}
      onCreate={onCreate}
    />
    {/* <DayPicker
      active={new Date()}
      selected={selected}
      onSelect={setSelected}
      onChange={onchange}
      // onDayClick={(day) => this.setState({ day })}
    /> */}
    <UserList users={users} />
    <div>Doing : {count}</div>
  </UserDispatch.Provider>

    // <TodoProvider>
    //   <Overlay>
    //     <ModalWrap onSubmit={onSubmit}>
    //         {/* <InputWithLabel
    //             autoFocus
    //             label="| Group_id"
    //             onChange={onChange1}
    //             Group_id={value1}
    //             placeholder="그룹명"
    //           />
    //           <InputWithLabel
    //             autoFocus
    //             label="| Tag"
    //             onChange={onChange2}
    //             tag={value2}
    //             placeholder="태그"
    //           />
    //           <InputWithLabel
    //             autoFocus
    //             label="| Todo"
    //             onChange={onChange3}
    //             text={value3}
    //             placeholder="내용"
    //           /> */}

    //           <CButton onClick={handleClose}>취소</CButton>      
    //     </ModalWrap>
    //   </Overlay>
    // </TodoProvider>
  );
}

export default React.memo(TodoCreate);

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