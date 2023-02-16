import React from "react";
import { useSelector } from "react-redux";
import './TodoInput.css';

export default function TodoInput() {

  const {todolist, donelist} = useSelector((state) =>
  ({
    todolist: state.userInfoReducers.user.doing,
    donelist: state.userInfoReducers.user.done
  }))

  console.log("todo input");
  console.log(todolist, donelist);

  // todolist.forEach((todo) => {
  //   if(todo.startTime)
  // })


  const countTodo = Object.keys(todolist).length;

  const countDone = Object.keys(donelist).length;

  // console.log("Todo, Done === ", countTodo, countDone)

  return (
    <div className="bigbox">
      <div className="box">
        <div className="inbox-1">
          <div className="circle_top"><h2>{countTodo}</h2></div>
          <h3>할일</h3>
        </div>

        <div className="diagonal"></div>

        <div className="inbox-2">
          <div className="inbox-3">
            <h3>완료</h3>
            <div className="circle_bot"><h2>{countDone}</h2></div>
          </div>
        </div>
      </div>
    </div>
  )
}