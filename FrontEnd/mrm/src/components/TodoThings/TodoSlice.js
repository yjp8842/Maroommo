import { createSlice } from "@reduxjs/toolkit";



export const userTodoSlice = createSlice({

  name: 'userTodo',
  initialState: {
    newTodo:{},


  },
  reducers: {
    saveTodoInfo: (state, {payload}) => {
      console.log('userTodo 저장 액션 호출')
      // console.log(data)
      state.newTodo = payload
    },

  }
})


export const userTodoReducers = userTodoSlice.reducer;
export const userTodoActions = userTodoSlice.actions;