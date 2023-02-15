import React, { useState, useReducer, useCallback, useRef } from 'react';
import styles from './styles/Content.module.css';
import AllBox from './AllBox';
import TodoDnD from '../DnD/TodoDnD';

import ScheduleButton from "../ScheduleThings/ScheduleButton"

import TodoButton from './TodoButton';



export const TodoDispatch = React.createContext(null);


function Content() {

  return (
    <div className={styles.content}>
      <AllBox title='ToDoList'>
        <TodoButton />
        <ScheduleButton />
        <TodoDnD />
      </AllBox>
    </div>
  );
}

export default Content;