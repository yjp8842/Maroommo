import React, { useState, useReducer, useCallback, useRef } from 'react';
import styles from './styles/Content.module.css';
// import AllBox from './AllBox';
// import TodoDnD from '../DnD/TodoDnD';
import CreateScheduleModal from './CreateScheduleModal'
import ScheduleList from './ScheduleList';

import dayjs from 'dayjs';
import styled from "styled-components";
import useInputs from './useInputs';
// import ScheduleModal from '../Modal/Schedule/ScheduleModal'


const initialState = {
  schedules: [
    // {
    //   id: 1,
    //   roomId: 1,
    //   content: 'Chapter2 강의 듣기',
    //   active: 1,
    //   year: 2023, month: 2, day: 9
    // },
    // {
    //   id: 2,
    //   roomId: 1,
    //   content: 'Chapter1 실습',
    //   active: 0,
    //   year: 2023, month: 2, day: 9
    // },
    // {
    //   id: 3,
    //   roomId: 2,
    //   content: 'Chapter2 강의 듣기',
    //   active: 0,
    //   year: 2023, month: 2, day: 9
    // }
  ]
};

function reducer(state, action) {
  switch (action.type) {
    case 'CREATE_SCHEDULE':
      return {
        schedules: state.schedules.concat(action.schedule)
      };
    case 'TOGGLE_SCHEDULE':
      return {
        ...state,
        schedules: state.schedules.map(schedule =>
          schedule.id === action.id ? { ...schedule, active: !schedule.active } : schedule
        )
      };
    case 'REMOVE_SCHEDULE':
      return {
        ...state,
        schedules: state.schedules.filter(schedule => schedule.id !== action.id)
      };
    default:
      return state;
  }
}

export const ScheduleDispatch = React.createContext(null);


function ScheduleButton() {

  const [isOpen, setIsOpen] = useState(false);

  const onClickButton = () => {
    setIsOpen(true);
  };

  const [ { roomId, content }, onChange, onReset] = useInputs({
    schedulename: '',
    email: ''
  });
  
  const [selectedDate, setSelectedDate] = React.useState(dayjs('2023-04-17'));
  const nextId = useRef(4);

  const [state, dispatch] = useReducer(reducer, initialState);
  const { schedules } = state;
  
  const year = selectedDate.$y;
  const month = selectedDate.$M + 1;
  const day = selectedDate.$D;

  const onCreate = useCallback(() => {
    dispatch({
      type: 'CREATE_SCHEDULE',
      schedule: {
        id: nextId.current,
        roomId,
        content,
        year, month, day
      }
    });
    onReset();
    nextId.current += 1;
  }, [roomId,  content, year, month, day, onReset]);

  return (
    <div>
      <ScheduleDispatch.Provider value={dispatch}>
        <Button onClick={onClickButton}>+ 일정</Button>
          {isOpen && (<CreateScheduleModal
                    open={isOpen}
                    onClose={() => {
                    setIsOpen(false);
                    }}
                    roomId={roomId}
                    content={content}
                    selectedDate={selectedDate}
                    setSelectedDate={setSelectedDate}
                    onChange={onChange}
                    onCreate={onCreate}
                />)}
          <ScheduleList schedules={schedules} />
        </ScheduleDispatch.Provider>
        {/* <ScheduleDnD /> */}
    </div>
  );
}

export default ScheduleButton;


const Button = styled.button`
font-size: 20px;
padding: 10px 20px;
margin: 0px 10px 0px 10px;
border: 0.5px solid gray;
background-color: #ffffff;
border-radius: 10px;
color: black;
font-weight: 200;
box-Shadow: 2px 2px 2px;
cursor: pointer;
&:hover {
  background-color: #fac2be;
}
`;