import axios from "axios";

export function googleLogin() {
  axios({
    method:"GET",
    url: 'https://i8a406.p.ssafy.io/api/oauth2/authorization/google'
  })
    .then((res) => {      
      console.log(res);
  })
  .catch((err) => {
    console.log(err);
  })
}

export function kakaoLogin() {
    axios({
      method:"GET",
      url: 'https://i8a406.p.ssafy.io/api/oauth2/authorization/kakao'
    })
      .then((res) => {
      console.log(res);
    })
    .catch((err) => {
      console.log(err);
    })
}

export function naverLogin() {
  axios({
    method:"GET",
    url: 'https://i8a406.p.ssafy.io/api/oauth2/authorization/naver'
  })
    .then((res) => {      
      console.log(res);
  })
  .catch((err) => {
    console.log(err);
  })
}