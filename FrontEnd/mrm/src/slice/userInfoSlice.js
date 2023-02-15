import { createSlice } from "@reduxjs/toolkit";



export const userInfoSlice = createSlice({

  name: 'userInfo',
  initialState: {
    user:{
      doing:[],
      done:[],
      schedules:[],
    },


  },
  reducers: {
    saveUserInfo: (state, {payload}) => {
      console.log('userInfo 저장 액션 호출');
      // console.log(data)
      state.user = payload;
    },
    saveUserMemo: (state, {payload}) => {
      console.log('userMemo 저장 액션 호출');
      state.user.userMemo = payload;
    },
    modifyUserInfo: (state, {payload}) => {
      console.log('modifyUserInfo 저장 액션 호출');
      state.user.nickname = payload.nickname;
      state.user.intro = payload.intro;
      state.user.profile = payload.profile;
    },    
    saveMyRoomInfo: (state, {payload}) => {
      console.log('saveMyRoomInfo 저장 액션 호출');
      state.user.myRooms = payload.myRooms;
      state.user.schedules = payload.schedules;
      console.log(state.user);
    },
  }
})


export const userInfoReducers = userInfoSlice.reducer;
export const userInfoActions = userInfoSlice.actions;