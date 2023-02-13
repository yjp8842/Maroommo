import axios from "axios";

export function postScheduleData({ roomId, content, selectedDate }) {
    // const roomId = document.getElementById("roomId").value;
    // const tags = document.getElementById("tags").value;
    // const content = document.getElementById("content").value;
    // const date = document.getElementById("date").value;
    // // const Month = document.getElementById("date").value.$M + 1;
    // // const Day = document.getElementById("date").value.$D;

    // console.log(roomId);
    // console.log(tags);
    // console.log(content);
    // console.log(selectedDate);
    // console.log(Year);
    // console.log(Month);
    // console.log(Day);

    const Year = selectedDate.$y;
    const Month = selectedDate.$M + 1;
    const Day = selectedDate.$D;


    const todo = {
        roomId: roomId,
        content: content,
        Year: Year,
        Month: Month,
        Day: Day,
    };

    console.log(todo)

    const url = '/todo/testId';

    
    // axios
    //     .post(url, todo)
    //     .then((response)=> {

    //     })
    
}