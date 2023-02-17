import { createSlice } from "@reduxjs/toolkit";

export const scheduleSlice = createSlice({

  name: 'schedule',
  initialState: {
    schedules:{},

  },
  reducers: {
    saveSchedule: (state, {payload}) => {
      console.log('schedules 저장 액션 호출')
      state.schedules = payload
    },

  }
})


export const scheduleReducers = scheduleSlice.reducer;
export const scheduleActions = scheduleSlice.actions;