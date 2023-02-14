import { createSlice } from "@reduxjs/toolkit";



export const scheduleSlice = createSlice({

  name: 'schedule',
  initialState: {
    schedule:{},


  },
  reducers: {
    saveSchedule: (state, {payload}) => {
      console.log('schedule 저장 액션 호출')
      // console.log(data)
      // 추가하는 식..?
      state.schedule = payload
    },

  }
})


export const scheduleReducers = scheduleSlice.reducer;
export const scheduleActions = scheduleSlice.actions;