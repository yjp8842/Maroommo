import React from 'react';
import Draggable from './Draggable';
import DragGroup from './DragGroup';
import Droppable from './Droppable';
import styled from 'styled-components';

// import axios from 'axios';

function TodoDnD() {

  const [box1, setBox1] = React.useState([
    { text: 'CS',
    tag: 'tag3' },
    { text: 'React2',
    tag: 'tag2' },
  ]);
  const [box2, setBox2] = React.useState([
    { text: 'React1',
      tag: 'tag1'  },
    ]);
  // const [box3, setBox3] = React.useState([
  //   ]);

  const handleBox1 = (item, monitor, state) => {
    if (state.find((each) => (each.text === item.text))) return;
    // remove from box2
    setBox2((prev) => {
      const index = prev.findIndex((each) => each.text === item.text);
      const copy = [...prev];
      copy.splice(index, 1);
      const index1 = prev.findIndex((each) => each.tag === item.tag);
      const copy1 = [...prev];
      copy1.splice(index1, 1);
      return copy1;
    });
    // add to box1
    setBox1((prev) => {
      return [...prev, { text: item.text, tag: item.tag }];
    });
  };

  const handleBox2 = (item, monitor, state) => {
    if (state.find((each) => each.text === item.text)) return;
    // remove from box1
    setBox1((prev) => {
      const index = prev.findIndex((each) => each.text === item.text);
      const copy = [...prev];
      copy.splice(index, 1);
      const index1 = prev.findIndex((each) => each.tag === item.tag);
      const copy1 = [...prev];
      copy1.splice(index1, 1);
      return copy1;
    });
    // add to box2
    setBox2((prev) => {
      return [...prev, { text: item.text, tag: item.tag }];
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
              key={drag.text}
              type='drag-3'
              text={drag.text}
              tag={drag.tag}
              item={{ text: drag.text, tag: drag.tag }}
              state={box1}
            />
          ))}
        </DragGroup>
      </Droppable>

      <Droppable
        accept='drag-3'
        handleDrop={handleBox2}
        text='Done'
        state={box2}
      >
        <DragGroup>
          {box2.map((drag) => (
            <Draggable
              key={drag.text}
              type='drag-3'
              text={drag.text}
              tag={drag.tag}
              item={{ text: drag.text, tag: drag.tag  }}
              state={box2}
            />
          ))}
        </DragGroup>
      </Droppable>
{/* 
      <Droppable
        accept='drag-3'
        handleDrop={handleBox3}
        text='Done'
        state={box3}
      >
        <DragGroup>
          {box2.map((drag) => (
            <Draggable
              key={drag.text}
              type='drag-3'
              text={drag.text}
              tag={drag.tag}
              item={{ text: drag.text, tag: drag.tag  }}
              state={box2}
            />
          ))}
        </DragGroup>
      </Droppable> */}
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