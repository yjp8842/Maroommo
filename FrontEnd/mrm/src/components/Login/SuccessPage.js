import api from "../../utils/axiosInstance";
import { scheduleActions } from "../../slice/scheduleSlice";
import { userInfoActions} from "../../slice/userInfoSlice";
import history from "../../utils/history";

import { useDispatch } from "react-redux";

function SuccessPage() {
    const dispatch = useDispatch();
    
    const params = new URLSearchParams(window.location.search);
    let accessToken = params.get("accessToken");

    localStorage.clear();
    localStorage.setItem("accessToken", accessToken);
    
    accessToken = "Bearer " + accessToken;

    api.get("/oauth/user")
        .then((res) => {
            if (res.status === 200) {
                console.log('로그인에 성공하셨습니다!');
                console.log(res);
                localStorage.setItem("refreshToken", res.data.refreshToken);

                // response.data.user 정보를 store에다가 저장하는 로직 추가해야 한다
                dispatch(userInfoActions.saveUserInfo(res.data.user))
                dispatch(scheduleActions.saveSchedule(res.data.user.schedules))


                // 모든 초기 정보들을 저장했으니 myRoom으로 이동해야 한다


                // window.location.replace("/myroom");
            }
            else {
                console.log('문제가 생겼습니다');
                // 로그인 화면으로 이동해야 함
                // window.location.replace("/");
            }
        })
        .catch(() => {
            console.log('로그인에 실패하셨습니다.');
            // 로그인 화면으로 이동해야 함
            // window.location.replace("/");
        })

    return;
}

export default SuccessPage;