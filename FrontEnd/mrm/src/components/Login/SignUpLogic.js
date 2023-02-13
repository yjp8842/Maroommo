import axios from "axios";

export function postSignUpData() {

  const id = document.getElementById('id').value;
  const name = document.getElementById('name').value;
  const email = document.getElementById('email').value;
  const password = document.getElementById('password').value;
  const nickname = document.getElementById('nickname').value;

  const SignUpData = {
    id: id,
    name: name,
    email: email,
    password: password,
    nickname: nickname,
  };

  const url = 'https://i8a406.p.ssafy.io/api/user/signup';
  
  return (
    axios
      .post(url, SignUpData)
      .then((response) => {
        if (response.status >= 200 && response.status <= 204) {
          alert('가입에 성공하셨습니다!');
          window.location.replace("/"); 
        }
      })
      .catch(() => {
        alert('이미 가입된 아이디입니다.');
      })
  );
}