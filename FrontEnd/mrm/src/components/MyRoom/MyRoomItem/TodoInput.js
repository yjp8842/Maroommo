import React from "react";
import './TodoInput.css';

export default function TodoInput() {
  return (
    <div className="bigbox">
      <div className="box">
        <div className="inbox-1">
          <div className="circle_top"><h2>13</h2></div>
          <h3>할일</h3>
        </div>

        <div className="diagonal"></div>

        <div className="inbox-2">
          <div className="inbox-3">
            <h3>완료</h3>
            <div className="circle_bot"><h2>17</h2></div>
          </div>
        </div>
      </div>
    </div>
  )
}