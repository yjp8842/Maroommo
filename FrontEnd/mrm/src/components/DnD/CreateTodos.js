import React from 'react';

// import 'react-daypicker/lib/DayPicker.css';
// import DayPicker  from 'react-daypicker';
// import ReactDatePicker from 'react-datepicker';

import dayjs from 'dayjs';
import Box from '@mui/material/Box';
import { AdapterDayjs } from '@mui/x-date-pickers/AdapterDayjs';
import { LocalizationProvider } from '@mui/x-date-pickers/LocalizationProvider';
import { DatePicker } from '@mui/x-date-pickers/DatePicker';

const CreateTodos = ({ Group_id, Todo_tag, Todo_text, Year, Month, Day, selectedDate, setSelectedDate, onChange, onCreate }) => {
  
  // const [selectedDate, setSelectedDate] = React.useState(dayjs('2022-04-07'));

  return (
      <div>
        <input
          name="Group_id"
          placeholder="그룹명"
          onChange={onChange}
          value={Group_id}
        />
        <input
          name="Todo_tag"
          placeholder="태그"
          onChange={onChange}
          value={Todo_tag}
        />
        <input
          name="Todo_text"
          placeholder="내용"
          onChange={onChange}
          value={Todo_text}
        />
        <LocalizationProvider dateAdapter={AdapterDayjs}>
          <DatePicker
            label="Custom input"
            value={selectedDate}
            onChange={(newValue) => {
              setSelectedDate(newValue);
            }}
            renderInput={({ inputRef, inputProps, InputProps }) => (
            <Box sx={{ display: 'flex', alignItems: 'center' }}>
              <input ref={inputRef} {...inputProps} />
              {InputProps?.endAdornment}
            </Box>
        )}
      />
    </LocalizationProvider>
        {/* <DayPicker
          active={new Date()}
          selected={selected}
          onSelect={setSelected}
          onChange={onchange}
          // onDayClick={(day) => this.setState({ day })}
        /> */}
        <button onClick={onCreate}>등록</button>
      </div>
    );
  };

export default React.memo(CreateTodos);
  

// const CreateUser = ({ Todo_id, Group_id, Tag, Todo, onChange, onCreate }) => {
//   return (
//     <div>
//       <input
//         name="Todo_id"
//         placeholder="계정명"
//         onChange={onChange}
//         value={Todo_id}
//       />
//       <input
//         name="Group_id"
//         placeholder="이메일"
//         onChange={onChange}
//         value={Group_id}
//       />
//       <input
//         name="tag"
//         placeholder="태그"
//         onChange={onChange}
//         value={Tag}
//       />
//       <input
//         name="Todo"
//         placeholder="내용"
//         onChange={onChange}
//         value={Todo}
//       />
//       <button onClick={onCreate}>등록</button>
//     </div>
//   );
// };