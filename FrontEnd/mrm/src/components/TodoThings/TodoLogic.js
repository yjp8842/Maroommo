import axios from "axios";

export function postTodoData({ roomId, tags, content, selectedDate }) {

    const Year = selectedDate.$y;
    const Month = selectedDate.$M + 1;
    const Day = selectedDate.$D;


    const todo = {
        roomId: roomId,
        tags: tags,
        content: content,
        // date: date,
        Year: Year,
        Month: Month,
        Day: Day,
    };

    console.log(todo)

    const BASE_URL = 'https://i8a406.p.ssafy.io';   
    const url = BASE_URL + '/api/todo/testId';

    
    axios
        .post(url, todo)
        .then((response)=> {
            console.log(response);
        })
    
}