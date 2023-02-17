import React, { useContext } from 'react';
import { ScheduleDispatch } from './ScheduleButton';

const Schedule = React.memo(function Schedule({ schedule }) {
  const dispatch = useContext(ScheduleDispatch);

  return (
    <div>
        <span>[{schedule.id}]</span>
      <b
        style={{
          cursor: 'pointer',
          color: schedule.active ? 'green' : 'black'
        }}
        onClick={() => {
          dispatch({ type: 'TOGGLE_SCHEDULE', id: schedule.id });
        }}
      >
        {schedule.content}
      </b>
      &nbsp;
      <span>({schedule.roomId})</span>
      <span>/{schedule.Year}</span>
      <span>/{schedule.Month}</span>
      <span>/{schedule.Day}/</span>
      <button
        onClick={() => {
          dispatch({ type: 'REMOVE_SCHEDULE', id: schedule.id });
        }}
      >
        삭제
      </button>
    </div>
  );
});

function ScheduleList({ schedules }) {
  return (
    <div>
      {schedules.map(schedule => (
        <Schedule schedule={schedule} key={schedule.id} />
      ))}
    </div>
  );
}

export default React.memo(ScheduleList);
