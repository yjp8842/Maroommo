import React, { useState } from 'react';
import Calendar from 'react-calendar';
import './CalendarMini.css'
// import moment from 'moment';

export default function CalendarMini() {
  const [value, onChange] = useState(new Date());
  const moment = require('moment');

  // 일정 날짜 받아서 하이라이트..
  // const [mark, setMark] = useState([]);
  // const {data} = useQuery()
  const marks_red = [
    '2023-01-20',
    '2023-01-21',
    '2023-01-22',
    '2023-01-23',
  ];

  const marks_blue = [
    '2023-01-26',
    '2023-01-27',
  ]

  return (
    <div className='mini'>
      <Calendar 
        onChange={onChange} value={value} locale='ko-KR'
        formatDay={(locale, date) => moment(date).format('DD')}
        className='mx-auto w-full text-sm border-b'
        tileContent={({date, view}) => {
          if (marks_red.find((x) => x === moment(date).format('YYYY-MM-DD'))) {
            return (
              <>
                <div className='flex justify-center items-center absoluteDiv'>
                  <div className='red_dot'></div>
                </div>
              </>
            )
          }
          if (marks_blue.find((x) => x === moment(date).format('YYYY-MM-DD'))) {
            return (
              <>
                <div className='flex justify-center items-center absoluteDiv'>
                  <div className='blue_dot'></div>
                </div>
              </>
            )
          }
        }}
      />

    </div>
  );
}