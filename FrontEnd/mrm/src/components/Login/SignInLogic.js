import axios from "axios";

export function requestLogin() {
  const id = document.getElementById('id').value;
  const password = document.getElementById('password').value;

  const axiosBody = {
    id:id,
    password:password
  };

  axios
    .post("https://i8a406.p.ssafy.io/api/user/login",
    // .post("http://localhost:8080/user/login",
          axiosBody)
    .then((res) => {
      // console.log("로그인 성공!");
      // console.log(res);

      localStorage.setItem("accessToken", res.data.accessToken);
      localStorage.setItem("refreshToken", res.data.refreshToken);

      // user 정보를 store에다가 저장하는 로직을 추가해야해


      // -> myRoom으로 이동시키는 로직 추가



    })
    .catch((err)=>{
      console.log(err);

      // 입력한 아이디 비밀번호가 잘못되었습니다 식으로의 안내 메시지를 alret 창으로 띄우면 될듯?
      
      
    });
}