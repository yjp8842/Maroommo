import React from 'react';
import Draggable from './Draggable';
import DragGroup from './DragGroup';
import Droppable from './Droppable';
import styled from 'styled-components';

import { userTodoActions } from '../TodoThings/TodoSlice';
import { useSelector } from 'react-redux';
import { id } from 'date-fns/locale';
// import axios from 'axios';
  
//tags 파트 배열 -> 스트링=

function TodoDnD() {

  const {todo_doing_list, done_list} = useSelector((state) => 
  ({
    todo_doing_list: state.userInfoReducers.user.doing,
    done_list: state.userInfoReducers.user.done
  }))

  const todolist = [];
  const doinglist = [];
  const donelist = [];

  todo_doing_list.map((todo => {
    return todo.state === 0
    ? todolist.push({ id: todo.id, text: todo.content })
    : doinglist.push({ id: todo.id, text : todo.content })
  }))

  done_list.map((todo) => {
    return donelist.push({text: todo.content})
  })

  console.log("todolist === ", todolist);

  const [box1, setBox1] = React.useState(todolist);
  const [box2, setBox2] = React.useState(doinglist);
  const [box3, setBox3] = React.useState(donelist);

  const handleBox1 = (item, monitor, state) => {
    if (state.find((each) => each.text === item.text)) return;
    // remove from box2
    setBox2((prev) => {
      const index = prev.findIndex((each) => each.text === item.text);
      const copy = [...prev];
      copy.splice(index, 1);
      return copy;
    });
    // add to box1
    setBox1((prev) => {
      return [...prev, { text: item.text }];
    });
  };

  const handleBox2 = (item, monitor, state) => {
    console.log(state)
    console.log(item)
    if (state.find((each) => each.text === item.text)) return;
    console.log("이동")
    // remove from box1
    setBox1((prev) => {
      const index = prev.findIndex((each) => each.text === item.text);
      const copy = [...prev];
      copy.splice(index, 1);
      return copy;
    });
    // add to box2
    setBox2((prev) => {
      return [...prev, { text: item.text }];
    });
  };

  const handleBox3 = (item, monitor, state) => {
    if (state.find((each) => each.text === item.text)) return;
    // remove from box2
    setBox2((prev) => {
      const index = prev.findIndex((each) => each.text === item.text);
      const copy = [...prev];
      copy.splice(index, 1);
      return copy;
    });
    // add to box3
    setBox3((prev) => {
      return [...prev, { text: item.text }];
    });
  };

  return (
    <TodoDiv>
      <Droppable
        accept='drag-3'
        handleDrop={handleBox1}
        text='To do'
        state={box1}
      >
        <DragGroup>
          {box1.map((drag) => (
            <Draggable
              key={drag.id}
              type='drag-3'
              text={drag.text}
              item={{ text: drag.text }}
              state={box1}
            />
          ))}
        </DragGroup>
      </Droppable>

      <Droppable
        accept='drag-3'
        handleDrop={handleBox2}
        text='Doing'
        state={box2}
      >
        <DragGroup>
          {box2.map((drag) => (
            <Draggable
              key={drag.id}
              type='drag-3'
              text={drag.text}
              item={{ text: drag.text }}
              state={box2}
            />
          ))}
        </DragGroup>
      </Droppable>

      <Droppable
        accept='drag-3'
        handleDrop={handleBox3}
        text='Done'
        state={box3}
      >
        <DragGroup>
          {box3.map((drag) => (
            <Draggable
              key={drag.id}
              type='drag-3'
              text={drag.text}
              item={{ text: drag.text }}
              state={box3}
            />
          ))}
        </DragGroup>
      </Droppable>
    </TodoDiv>
  );
}

const TodoDiv = styled.div`
  display: flex;
  width: 100%;
  height: 200px;
  `

export default TodoDnD;