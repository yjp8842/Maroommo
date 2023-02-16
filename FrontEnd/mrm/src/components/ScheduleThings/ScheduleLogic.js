import api from "../../utils/axiosInstance";
import { scheduleActions } from "../../slice/scheduleSlice";

export function postScheduleData({ user, group, content, selectedDate, dispatch, navigate }) {

    const year = selectedDate.$y;
    const month = selectedDate.$M + 1;
    const day = selectedDate.$D;


    const schedule = {
        roomId: group.id,
        content: content,
        userId: user.id,
        year: year,
        month: month,
        day: day,
    };
    
    api.post(`/schedule`, schedule)
        .then((response)=> {
            console.log(response);
            dispatch(scheduleActions.saveSchedule(response.data.newSchedule))
            alert("일정 생성을 완료하였습니다")
            window.location.reload();
        })
        .catch((err) => {
            console.log(err);
            alert("일정 생성 중 오류가 발생했습니다")
            window.location.reload();
    })
    
}