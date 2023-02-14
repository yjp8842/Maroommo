import axios from "axios";

export function postScheduleData({ roomId, content, selectedDate }) {

    const year = selectedDate.$y;
    const month = selectedDate.$M + 1;
    const day = selectedDate.$D;


    const schedule = {
        roomId: roomId,
        content: content,
        year: year,
        month: month,
        day: day,
    };

    const BASE_URL = 'https://i8a406.p.ssafy.io';   
    const url = BASE_URL + '/api/schedule/testId';

    
    axios
        .post(url, schedule)
        .then((response)=> {
            console.log(response);
        })
    
}