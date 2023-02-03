import React, { useState } from 'react';
import styles from './styles/Content.module.css';
import Box from './Box';
import TodoDnD from './TodoDnD';


import styled from "styled-components";
import TodoModal from '../Modal/Todo/TodoModal'

function Content() {

  const [isOpen, setIsOpen] = useState(false);

  const onClickButton = () => {
    setIsOpen(true);
  };

  return (
    <div className={styles.content}>
      <Box title='ToDoList'>
        <Button onClick={onClickButton}>+</Button>
          {isOpen && (<TodoModal
            open={isOpen}
            onClose={() => {
              setIsOpen(false);
            }}
          />)}
        <TodoDnD />
      </Box>
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