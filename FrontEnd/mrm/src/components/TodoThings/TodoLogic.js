import axios from "axios";
import { userInfoActions } from "../../slice/userInfoSlice"
import api from "../../utils/axiosInstance";

// import history from "../../utils/history";

export function postTodoData({ user, group, tags, content, selectedDate, dispatch }) {

    const year = selectedDate.$y;
    const month = selectedDate.$M + 1;
    const day = selectedDate.$D;
    const reqTags = [];
    reqTags.push(tags);
    const numRoomId = group.id;

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


    
    api.post(`/todo`, todo)
        .then((response)=> {
            console.log(response);
            dispatch(userInfoActions.createMytodo(response.data))
            alert("할일 생성을 완료하였습니다")
            // history.push('/group/1')
        })
        .catch((err) => {
            console.log(err);
            alert("할일 생성 중 오류가 발생했습니다")
    })
    
}