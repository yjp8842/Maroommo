import React from 'react';
import styled from 'styled-components';
import TodoItem from './TodoItem';
import { useTodoState } from './TodoContext';

const TodoListBlock = styled.div`
  flex: 1;
  padding: 20px 32px;
  padding-bottom: 48px;
  overflow-y: auto;
`;

function TodoList() {
  const todos = useTodoState();
  return (
    <TodoListBlock>
      {todos.map(todo => (
        <TodoItem
          Todo_id={todo.Todo_id}
          Group_id={todo.Group_id}
          tag={todo.tag}
          text={todo.text}
          done={todo.done}
          key={todo.Todo_id}
        />
      ))}
    </TodoListBlock>
  );
}

export default TodoList;
