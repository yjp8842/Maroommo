import axios from "axios";
import { userInfoActions } from "../../slice/userInfoSlice"

// import history from "../../utils/history";

export function postTodoData({ roomId, tags, content, selectedDate, dispatch }) {

    const year = selectedDate.$y;
    const month = selectedDate.$M + 1;
    const day = selectedDate.$D;
    const reqTags = [];
    reqTags.push(tags);
    const numRoomId = Number(roomId);

    const todo = {
        roomId: numRoomId,
        tags: reqTags,
        content: content,
        year: year,
        month: month,
        day: day,
    };

    console.log("todo")
    console.log(todo)

    const BASE_URL = 'https://i8a406.p.ssafy.io';   
    const url = BASE_URL + '/api/todo/hd';

    
    axios
        .post(url, todo)
        .then((response)=> {
            console.log(response);
            // console.log(response.data.newTodo.id);
            // console.log(response.data.newTodo.tags);
            // console.log(response.data.newTodo.content);
            // console.log(response.data.newTodo.startTime);

            dispatch(userInfoActions.saveTodoInfo(response.data.newTodo))
            
            // history.push('/group/1')
        })
    
}