import { createSlice } from "@reduxjs/toolkit";



export const groupInfoSlice = createSlice({

  name: 'groupInfo',
  initialState: {
    group:{},
  },
  reducers: {
    saveGroupInfo: (state, {payload}) => {
      console.log('saveGroupInfo 저장 액션 호출')
      state.group = payload
    }
  }
})


export const groupInfoReducers = groupInfoSlice.reducer;
export const groupInfoActions = groupInfoSlice.actions;




