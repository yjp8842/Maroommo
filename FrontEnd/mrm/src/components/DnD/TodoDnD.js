import React from 'react';
import Draggable from './Draggable';
import DragGroup from './DragGroup';
import Droppable from './Droppable';
import styled from 'styled-components';

import api from '../../utils/axiosInstance';
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
    ? todolist.push({ 
      id: todo.id,
      text: todo.content,
      doingTimeId: -1
    })
    : doinglist.push({ 
      id: todo.id,
      text: todo.content,
      doingTimeId: -1
    })
  }))

  done_list.map((todo) => {
    return donelist.push({
      id: todo.id,
      text: todo.content
    })
  })

  console.log("todolist === ", todolist);

  const [box1, setBox1] = React.useState(todolist);
  const [box2, setBox2] = React.useState(doinglist);
  const [box3, setBox3] = React.useState(donelist);

  const handleBox1 = (item, monitor, state) => {
    if (state.find((each) => each.id === item.id)) return;

    const data = {
      "doingId": -1,
      "doingTimeId": item.doingTimeId,
      "doneId": -1,
      "todoId": item.id
    }

    api.patch(`/todo/state`, data)
    .then((res) => {
      console.log("todo로 이동!");
      console.log(data);
      console.log(item);
      // remove from box2
      setBox2((prev) => {
        const index = prev.findIndex((each) => each.id === item.id);
        const copy = [...prev];
        copy.splice(index, 1);
        return copy;
      });
      // add to box1
      setBox1((prev) => {
        return [...prev,item];
      });
    })
    .catch((err) => {
      console.log(err);
    })
  };

  const handleBox2 = (item, monitor, state) => {
    if (state.find((each) => each.id === item.id)) return;

    const temp = box2[0] ? box2[0] : null;
    
    const data = {
      "doingId": item.id,
      "doingTimeId": temp ? temp.doingTimeId : -1,
      "doneId": -1,
      "todoId": temp ? temp.id : -1
    }

    api.patch(`/todo/state`, data)
    .then((res) => {
      console.log("doing으로 이동!");
      console.log(data);
      console.log(item);
      console.log(res);
      item.doingTimeId = res.data.doingTimeId;
      console.log(item);

      if(temp !== null){   
        console.log("doing에 todo있어요!"); 
        // remove from box2
        setBox2((prev) => {
          const index = prev.findIndex((each) => each.id === temp.id);
          const copy = [...prev];
          copy.splice(index, 1);
          return copy;
        });
        // add to box1
        setBox1((prev) => {
          return [...prev, temp];
        });
      }
  
      // remove from box1
      setBox1((prev) => {
        const index = prev.findIndex((each) => each.id === item.id);
        const copy = [...prev];
        copy.splice(index, 1);
        return copy;
      });
      // add to box2
      setBox2((prev) => {
        return [...prev, item];
      });


    })
    .catch((err) => {
      console.log(err);
    })

  };

  const handleBox3 = (item, monitor, state) => {
    if (state.find((each) => each.id === item.id)) return;
    
    const data = {
      "doingId": -1,
      "doingTimeId": -1,
      "doneId": item.id,
      "todoId": -1
    }

    api.patch(`/todo/state`, data)
    .then((res) => {
      console.log("done으로 이동!");
      console.log(data);
      console.log(item);
      // remove from box2
      setBox2((prev) => {
        const index = prev.findIndex((each) => each.id === item.id);
        const copy = [...prev];
        copy.splice(index, 1);
        return copy;
      });
      // add to box3
      setBox3((prev) => {
        return [...prev, item];
      });
    })
    .catch((err) => {
      console.log(err);
    })

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
              item={drag}
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
              item={drag}
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
              item={drag}
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