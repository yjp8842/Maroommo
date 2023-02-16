import React, { useState, useReducer, useCallback, useRef } from 'react';
import styles from './styles/Content.module.css';
import AllBox from './AllBox';
import TodoDnD from '../DnD/TodoDnD';
import CreateTodoModal from './CreateTodoModal'

import dayjs from 'dayjs';
import styled from "styled-components";
import useInputs from './useInputs';


const initialState = {
  todos: [
    {
      id: 1,
      roomId: 1,
      tags : 'React',
      content: 'Chapter2 강의 듣기',
      done: 1,
      year: 2023, month: 2, day: 9
    },
    {
      id: 2,
      roomId: 1,
      tags : 'React',
      content: 'Chapter1 실습',
      done: 0,
      year: 2023, month: 2, day: 9
    },
    {
      id: 3,
      roomId: 2,
      tags : 'CS',
      content: 'Chapter2 강의 듣기',
      done: 0,
      year: 2023, month: 2, day: 9
    }
  ]
};

function reducer(state, action) {
  switch (action.type) {
    case 'CREATE_TODO':
      return {
        todos: state.todos.concat(action.todo)
      };
    case 'TOGGLE_TODO':
      return {
        ...state,
        todos: state.todos.map(todo =>
          todo.id === action.id ? { ...todo, done: !todo.done } : todo
        )
      };
    case 'REMOVE_TODO':
      return {
        ...state,
        todos: state.todos.filter(todo => todo.id !== action.id)
      };
    default:
      return state;
  }
}

export const TodoDispatch = React.createContext(null);


function TodoButton() {

  const [isOpen, setIsOpen] = useState(false);

  const onClickButton = () => {
    setIsOpen(true);
  };

  const [ { done, roomId, tags, content }, onChange, onReset] = useInputs({
    todoname: '',
    email: ''
  });
  
  const [selectedDate, setSelectedDate] = React.useState(dayjs('2023-04-17'));
  const nextId = useRef(4);

  const [state, dispatch] = useReducer(reducer, initialState);
  // const { todos } = state;
  
  const year = selectedDate.$y;
  const month = selectedDate.$M + 1;
  const day = selectedDate.$D;

  const onCreate = useCallback(() => {
    dispatch({
      type: 'CREATE_TODO',
      todo: {
        id: nextId.current,
        done,
        roomId,
        tags,
        content,
        year, month, day
      }
    });
    onReset();
    nextId.current += 1;
  }, [roomId, tags, done, content, year, month, day, onReset]);


  // const count = useMemo(() => countDoneTodos(todos), [todos]);

  return (
    <div>
        <TodoDispatch.Provider value={dispatch}>
        <Button onClick={onClickButton}>+ 할일</Button>
            {isOpen && (<CreateTodoModal
                    open={isOpen}
                    onClose={() => {
                    setIsOpen(false);
                    }}
                    roomId={roomId}
                    tags={tags}
                    content={content}
                    selectedDate={selectedDate}
                    setSelectedDate={setSelectedDate}
                    onChange={onChange}
                    onCreate={onCreate}
                />)}
        </TodoDispatch.Provider>
    </div>
  );
}

export default TodoButton;

const Button = styled.button`
font-size: 20px;
padding: 10px 20px;
margin: 0px 10px 0px 10px;
border: 0.5px solid gray;
background-color: #ffffff;
border-radius: 10px;
color: black;
font-weight: 200;
box-Shadow: 2px 2px 2px;
cursor: pointer;
&:hover {
  background-color: #fac2be;
}
`;