import React, { useState } from 'react';

import styled from 'styled-components';
import Calendar from 'react-calendar';
import { useSelector } from 'react-redux';
import './CalendarApp.css'
import TodoButton from '../TodoThings/TodoButton';
import ScheduleButton from '../ScheduleThings/ScheduleButton';
// import moment from 'moment';

export default function CalendarApp() {
  const [value, onChange] = useState(new Date());
  const moment = require('moment');

  // 일정 날짜 받아서 하이라이트..
  // https://velog.io/@khy226/%EB%A6%AC%EC%95%A1%ED%8A%B8-%EC%95%B1%EC%97%90-%EB%8B%AC%EB%A0%A5react-calendar-%EC%A0%81%EC%9A%A9%ED%95%98%EA%B8%B0
  // const [mark, setMark] = useState([]);
  // const {data} = useQuery()

  // const marks_red = [
  //   '2023-02-20',
  //   '2023-02-21',
  //   '2023-02-22',
  //   '2023-02-23',
  // ];

  // const marks_blue = [
  //   '2023-02-26',
  //   '2023-02-27',
  // ];

  const {todolist, schedulelist} = useSelector((state) =>
  ({
    todolist: state.userInfoReducers.user.doing,
    schedulelist: state.userInfoReducers.user.schedules,
  }))

  const todo_schedule_list = [];

  todolist.map((todo,index) => {
    return todo_schedule_list.push({
      content:todo.content,
      startTime:todo.startTime,
      state:todo.state
    })    
  })
  
  schedulelist.map((schedule,index) => {
    return todo_schedule_list.push({
      content:schedule.content,
      startTime:schedule.startTime,
      state:-1
    })    
  })
  

  return (
    <div>
    <Calendar
      onChange={onChange} value={value} locale='ko-KR'
      formatDay={(locale, date) => moment(date).format('DD')}
      className='mx-auto w-full text-sm border-b'
      selectRange={false}
      tileContent={({date, view}) => {
        return (
          (todo_schedule_list.map((value, index) => {
            return (value.startTime === moment(date).format('YYYY-MM-DD') 
              ? 
              <div className='flex justify-center items-center absoluteDiv'>
                {value.state === -1 
                ? <Schedulecontent>{value.content}</Schedulecontent>
                : (value.state === 0) 
                  ? <Todocontent>{value.content}</Todocontent>
                  : (value.state === 1) 
                    ? <Doingcontent>{value.content}</Doingcontent>
                    : <Donecontent>{value.content}</Donecontent>
                }                
              </div>
              : <div></div>)
          }))
        )

        // if (todolist.find((x) => x.startTime === moment(date).format('YYYY-MM-DD'))) {
        //   return (
        //     <>
        //       <div className='flex justify-center items-center absoluteDiv'>
        //         {/* <div className='red_dot'></div> */}
        //         <Todocontent>{x.content}</Todocontent>
        //       </div>
        //     </>
        //   )
        // }
        // if (schedulelist.find((x) => x.startTime === moment(date).format('YYYY-MM-DD'))) {
        //   return (
        //     <>
        //       <div className='flex justify-center items-center absoluteDiv'>
        //         {/* <div className='blue_dot'></div> */}
        //         <Schedulecontent>알고리즘 스터디</Schedulecontent>
        //       </div>
        //     </>
        //   )
        // }
      }}
    />
    <Plusbox>
      <TodoButton />
      <ScheduleButton />
    </Plusbox>
    </div>
  );
}

const Todocontent = styled.div`
// width: 170px;
height: 20px;
width: 90%;
outline: none;
border-radius: 15px;
font-size: 15px;
color: white;
background-color: gray;
padding: 5px 5px 0px 10px;
`

const Doingcontent = styled.div`
// width: 170px;
height: 20px;
width: 90%;
outline: none;
border-radius: 15px;
font-size: 15px;
color: white;
background-color: red;
padding: 5px 5px 0px 10px;
`

const Donecontent = styled.div`
// width: 170px;
height: 20px;
width: 90%;
outline: none;
border-radius: 15px;
font-size: 15px;
color: white;
background-color: blue;
padding: 5px 5px 0px 10px;
`

const Schedulecontent = styled.div`
// width: 170px;
height: 20px;
width: 90%;
outline: none;
border-radius: 15px;
font-size: 15px;
color: white;
background-color: green;
padding: 5px 5px 0px 10px;
`

const Plusbox = styled.div`
  justify-content: center;
`