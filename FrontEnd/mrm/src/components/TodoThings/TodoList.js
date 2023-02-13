import React, { useContext } from 'react';
import { TodoDispatch } from './TodoAll';

const Todo = React.memo(function Todo({ todo }) {
  const dispatch = useContext(TodoDispatch);

  return (
    <div>
        <span>[{todo.id}]</span>
      <b
        style={{
          cursor: 'pointer',
          color: todo.done ? 'green' : 'black'
        }}
        onClick={() => {
          dispatch({ type: 'TOGGLE_TODO', id: todo.id });
        }}
      >
        {todo.content}
      </b>
      &nbsp;
      <span>#{todo.tags}</span>
      <span>({todo.roomId})</span>
      <span>/{todo.Year}</span>
      <span>/{todo.Month}</span>
      <span>/{todo.Day}/</span>
      <button
        onClick={() => {
          dispatch({ type: 'REMOVE_TODO', id: todo.id });
        }}
      >
        삭제
      </button>
    </div>
  );
});

function TodoList({ todos }) {
  return (
    <div>
      {todos.map(todo => (
        <Todo todo={todo} key={todo.id} />
      ))}
    </div>
  );
}

export default React.memo(TodoList);
