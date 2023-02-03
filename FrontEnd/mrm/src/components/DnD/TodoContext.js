import React, { createContext, useReducer, useContext, useRef } from 'react';

const TodoStateContext = createContext(null);
const TodoDispatchContext = createContext(null);
const TodoNextIdContext = createContext(null);

function todoReducer(state, action) {
  switch (action.type) {
    case 'CREATE':
      return state.concat(action.todo);
    case 'TOGGLE':
      return state.map(todo =>
        todo.id === action.id ? { ...todo, done: !todo.done } : todo
      );
    case 'REMOVE':
      return state.filter(todo => todo.Todo_id !== action.Todo_id);
    default:
      return state;
  }
}

const initialTodos = [
  {
    Todo_id: 1,
    Group_id: 1,
    tag : 'React',
    text: 'Chapter2 강의 듣기',
    done: true
  },
  {
    Todo_id: 2,
    Group_id: 1,
    tag : 'React',
    text: 'Chapter1 실습',
    done: true
  },
  { Todo_id: 3,
    Group_id: 2,
    tag : 'CS',
    text: 'Chapter2 강의 듣기',
    done: false },
  { Todo_id: 4,
    Group_id: 2,
    tag : 'CS',
    text: 'Chapter1 정리',
    done: false }
];

export function TodoProvider({ children }) {
  const [state, dispatch] = useReducer(todoReducer, initialTodos);
  const nextId = useRef(5);

  return (
    <TodoStateContext.Provider value={state}>
      <TodoDispatchContext.Provider value={dispatch}>
        <TodoNextIdContext.Provider value={nextId}>
          {children}
        </TodoNextIdContext.Provider>
      </TodoDispatchContext.Provider>
    </TodoStateContext.Provider>
  );
}

export function useTodoState() {
  return useContext(TodoStateContext);
}

export function useTodoDispatch() {
  return useContext(TodoDispatchContext);
}

export function useTodoNextId() {
  return useContext(TodoNextIdContext);
}
