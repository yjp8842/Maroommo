import React, { useState, useReducer, useCallback, useRef } from 'react';
import styles from './styles/Content.module.css';
import AllBox from './AllBox';
import TodoDnD from '../DnD/TodoDnD';
import CreateTodoModal from './CreateTodoModal'
import { useSelector, useDispatch } from "react-redux";
import { userInfoActions} from "../../slice/userInfoSlice";
// import TodoList from './TodoList';
import api from "../../utils/axiosInstance";

import dayjs from 'dayjs';
import styled from "styled-components";
import useInputs from './useInputs';
// import TodoModal from '../Modal/Todo/TodoModal'

// function countDoneTodos(todos) {
//   // console.log('활성 사용자 수를 세는중...');
//   return todos.filter(todo => todo.done).length;
// }

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


function Content() {

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
    <div className={styles.content}>
      <AllBox title='ToDoList'>
      <TodoDispatch.Provider value={dispatch}>
        <Button onClick={onClickButton}>+</Button>
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
          {/* <TodoList todos={todos} />
          <div>Doing : {count}</div> */}
        </TodoDispatch.Provider>
        <TodoDnD />
      </AllBox>
    </div>
  );
}

export default Content;


const Button = styled.button`
font-size: 20px;
padding: 10px 20px;
border: none;
background-color: #ffffff;
border-radius: 10px;
color: black;
font-style: italic;
font-weight: 200;
cursor: pointer;
&:hover {
  background-color: #fac2be;
}
`;