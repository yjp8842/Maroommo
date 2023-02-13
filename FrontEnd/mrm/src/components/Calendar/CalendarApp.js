import React, { useState } from 'react';
import Calendar from 'react-calendar';
import './CalendarApp.css'
// import moment from 'moment';

export default function CalendarApp() {
  const [value, onChange] = useState(new Date());
  const moment = require('moment');

  // 일정 날짜 받아서 하이라이트..
  // https://velog.io/@khy226/%EB%A6%AC%EC%95%A1%ED%8A%B8-%EC%95%B1%EC%97%90-%EB%8B%AC%EB%A0%A5react-calendar-%EC%A0%81%EC%9A%A9%ED%95%98%EA%B8%B0
  // const [mark, setMark] = useState([]);
  // const {data} = useQuery()
  const marks_red = [
    '2023-02-20',
    '2023-02-21',
    '2023-02-22',
    '2023-02-23',
  ];

  const marks_blue = [
    '2023-02-26',
    '2023-02-27',
  ];

  return (
    <Calendar
      onChange={onChange} value={value} locale='ko-KR'
      formatDay={(locale, date) => moment(date).format('DD')}
      className='mx-auto w-full text-sm border-b'
      selectRange={false}
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
  );
}