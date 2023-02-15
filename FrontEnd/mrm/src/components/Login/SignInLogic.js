import axios from "axios";
// import { useSelector } from "react-redux";
import { scheduleActions } from "../../slice/scheduleSlice";
import { userInfoActions} from "../../slice/userInfoSlice";

export function requestLogin(dispatch, navigate) {

  const id = document.getElementById('id').value;
  const password = document.getElementById('password').value;

  const axiosBody = {
    id:id,
    password:password
  };
  console.log(axiosBody)

  axios
    .post("https://i8a406.p.ssafy.io/api/user/login",
          axiosBody)
    .then((res) => {
      console.log("로그인 성공!");
      console.log(res);

      localStorage.setItem("accessToken", res.data.token.accessToken);
      localStorage.setItem("refreshToken", res.data.token.refreshToken);

      // user 정보를 store에다가 저장하는 로직을 추가해야해
      dispatch(userInfoActions.saveUserInfo(res.data.user))
      dispatch(scheduleActions.saveSchedule(res.data.user.schedules))

      console.log('로그인시 받은 정보로 updateUserInfo 호출')
      alert('로그인 되었습니다.')
      // -> myRoom으로 이동시키는 로직 추가
      navigate(`/myroom`);
    })
    .catch((err)=>{
      console.log(err);

      // 입력한 아이디 비밀번호가 잘못되었습니다 식으로의 안내 메시지를 alret 창으로 띄우면 될듯?      
      alert('아이디 또는 비밀번호를 잘못 입력하였습니다.');      
    });
}