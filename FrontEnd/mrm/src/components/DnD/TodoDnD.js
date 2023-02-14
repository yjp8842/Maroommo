import React from 'react';
import Draggable from './Draggable';
import DragGroup from './DragGroup';
import Droppable from './Droppable';
import styled from 'styled-components';

import { userTodoActions } from '../TodoThings/TodoSlice';
import { useSelector } from 'react-redux';
import { id } from 'date-fns/locale';
// import axios from 'axios';
  
//tags 파트 배열 -> 스트링

function TodoDnD() {


  // const { todo_id, todo_content, todo_tag } = useSelector((state) =>
  // ({
  //   todo_id: state.userTodoReducers.newTodo.id,
  //   todo_content: state.userTodoReducers.newTodo.content,
  //   todo_tag: state.userTodoReducers.newTodo.tags
  // }))


  const {todolist, donelist} = useSelector((state) => 
  ({
    todolist: state.userInfoReducers.user.doing,
    donelist: state.userInfoReducers.user.done
  }))

  // console.log("doinglist ===", todolist);
  // console.log("donelist ===", donelist);

  const [todobox, setTodoBox] = React.useState([
    { content: 'CS',
    tag: 'tag3' },
    { content: 'React2',
    tag: 'tag2' },
    // {
    //   content: todo_content,
    //   tag: todo_tag
    // }
  ]);

  const [doingbox, setDoingBox] = React.useState([
    ]);

  const [donebox, setDoneBox] = React.useState([
    { content: 'React1',
      tag: 'tag1'  },
    ]);

  const handleTodoBox = (item, monitor, state) => {
    if (state.find((each) => (each.content === item.content))) return;
    // remove from DoneBox
    setDoneBox((prev) => {
      const index = prev.findIndex((each) => each.content === item.content);
      const copy = [...prev];
      copy.splice(index, 1);
      const index1 = prev.findIndex((each) => each.tag === item.tag);
      const copy1 = [...prev];
      copy1.splice(index1, 1);
      return copy1;
    });
    // add to todobox
    setTodoBox((prev) => {
      return [...prev, { content: item.content, tag: item.tag }];
    });
  };

  const handleDoingBox = (item, monitor, state) => {
    if (state.find((each) => each.content === item.content)) return;
    // remove from todobox
    setTodoBox((prev) => {
      const index = prev.findIndex((each) => each.content === item.content);
      const copy = [...prev];
      copy.splice(index, 1);
      const index1 = prev.findIndex((each) => each.tag === item.tag);
      const copy1 = [...prev];
      copy1.splice(index1, 1);
      return copy1;
    });
    // add to DoneBox
    setDoingBox((prev) => {
      return [...prev, { content: item.content, tag: item.tag }];
    });
  };

  const handleDoneBox = (item, monitor, state) => {
    if (state.find((each) => each.content === item.content)) return;
    // remove from todobox
    setDoingBox((prev) => {
      const index = prev.findIndex((each) => each.content === item.content);
      const copy = [...prev];
      copy.splice(index, 1);
      const index1 = prev.findIndex((each) => each.tag === item.tag);
      const copy1 = [...prev];
      copy1.splice(index1, 1);
      return copy1;
    });
    // add to DoneBox
    setDoneBox((prev) => {
      return [...prev, { content: item.content, tag: item.tag }];
    });
  };

  return (
    <TodoDiv>
      <Droppable
        accept='drag-3'
        handleDrop={handleTodoBox}
        content='To do'
        state={todobox}
      >
        <DragGroup>
          {todolist.map((drag) => (
            <Draggable
              key={drag.content}
              type='drag-3'
              content={drag.content}
              tag={drag.tags}
              item={{ content: drag.content, tag: drag.tag }}
              state={todobox}
            />
          ))}
        </DragGroup>
      </Droppable>

      <Droppable
        accept='drag-3'
        handleDrop={handleDoingBox}
        content='Doing'
        state={doingbox}
      >
        <DragGroup>
          {doingbox.map((drag) => (
            <Draggable
              key={drag.content}
              type='drag-3'
              content={drag.content}
              tag={drag.tags}
              item={{ content: drag.content, tag: drag.tag }}
              state={doingbox}
            />
          ))}
        </DragGroup>
      </Droppable>

      <Droppable
        accept='drag-3'
        handleDrop={handleDoneBox}
        content='Done'
        state={donebox}
      >
        <DragGroup>
          {donelist.map((drag) => (
            <Draggable
              key={drag.content}
              type='drag-3'
              content={drag.content}
              tag={drag.tags}
              item={{ content: drag.content, tag: drag.tag }}
              state={donebox}
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



// const [ TodoData, setTodoData ] = useState(null);

// const onClick = async () => {
//   try {
//     const response = await axios.get(
//       ""
//     );
//     setTodoData(response.Tododata);
//   } catch (e) {
//     console.log(e);
//   }
// };