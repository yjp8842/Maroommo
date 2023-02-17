import api from "../../utils/axiosInstance";
import { useNavigate,useParams } from 'react-router-dom';

import { useDispatch } from "react-redux";
import { userInfoActions } from "../../slice/userInfoSlice";
import { groupInfoActions} from "../../slice/groupInfoSlice";
import { scheduleActions } from "../../slice/scheduleSlice";

function GroupJoinPage() {
    const params = useParams();
    const groupId = params.groupId;
    const roomCode = params.roomCode;
    const navigate = useNavigate();

    const dispatch = useDispatch();
    
    const url = `/room/enter/${groupId}?roomCode=${roomCode}`
    console.log(groupId, roomCode, url);

    api.get(url)
        .then((res) => {
            console.log("성공 ", res);  
            dispatch(userInfoActions.saveMyRoomInfo(res.data.myRoomInfo));
            dispatch(groupInfoActions.saveGroupInfo(res.data.moveRoomInfo));
            dispatch(scheduleActions.saveSchedule(res.data.moveRoomInfo.schedules));
            alert("마룸모에 참가하였습니다!");
            navigate(`/group/${res.data.moveRoomInfo.id}`);
        })
        .catch(() => {
            alert("잘못된 초대 링크를 입력하셨습니다.")
            // 로그인 화면으로 이동해야 함
            navigate("/myroom");
        })

    return;
}

export default GroupJoinPage;