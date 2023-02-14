import axios from "axios";

let globalUserId = "";

export function FindPwd(isEmailCode) {
  if(!isEmailCode){
    alert("이메일 인증을 완료해주세요");
    return;
  }
  const namePwd = document.getElementById('name').value;
  const idPwd = document.getElementById('id').value;
  const emailPwd = document.getElementById('email').value;

  console.log(idPwd+", "+namePwd+", "+emailPwd);
  axios({
    method:"POST",
    url: 'https://i8a406.p.ssafy.io/api/user/help/pw',
    data:{
        "id": idPwd,
        "name": namePwd,
        "email": emailPwd,
      }
  })
  .then((res) => {
    globalUserId = res.data.userId;
    const expires = new Date();    
    expires.setHours(expires.getHours() + 10);
    set_cookie('userId', res.data.userId, );
    window.location.replace('/modifyPwd');
  })
  .catch((err) => {
    alert("입력한 회원 정보가 일치하지 않습니다");
   })
}

export async function SendEmailCode(setSendCode) {
  const email = document.getElementById('email').value;
  console.log("이메일 전송중...");

  await axios.get('https://i8a406.p.ssafy.io/api/user/help/'+email)
  .then((res) => {
    console.log("이메일 전송 완료");
    setSendCode(res.data.emailCode)
    console.log(res.data.emailCode)
  })
  .catch((err) => {
    console.log(err);
  })  
}

export function checkEmailCode(sendCode, setIsEmailCode) {
  const code = document.getElementById('code').value;
  console.log(code+", "+sendCode);
  
  if(code === sendCode){                
    setIsEmailCode(true);
    alert("이메일 인증에 성공하였습니다");
  }
  else{
    setIsEmailCode(false);
    alert("잘못된 인증코드를 입력하셨습니다");
  }
}

export async function modifyPwd() {
  const AfterPassword = document.getElementById('AfterPassword').value;  
  const id = get_cookie("userId");
  console.log("비밀번호 변경 id : "+id+", "+AfterPassword);
  const modifyPwd = {
    id: id,
    password: AfterPassword
  }

  await axios.patch('https://i8a406.p.ssafy.io/api/user/help/pw',modifyPwd)
  .then((res) => {
    // login 화면으로 가야함
    window.location.replace("/"); 
  })
  .catch((err) => {
    console.log(err);
    alert("비밀번호 변경 중 오류가 발생했습니다");
  })
}

//쿠키 저장하는 함수
function set_cookie(name, value, unixTime) {
  var date = new Date();
  date.setTime(date.getTime() + unixTime);
  document.cookie = encodeURIComponent(name) + '=' + encodeURIComponent(value) + ';expires=' + date.toUTCString() + ';path=/';
}

//쿠키 값 가져오는 함수
function get_cookie(name) {
  var value = document.cookie.match('(^|;) ?' + name + '=([^;]*)(;|$)');
  return value? value[2] : null;
}

// //쿠키 삭제하는 함수
// function delete_cookie(name) {
//   document.cookie = encodeURIComponent(name) + '=; expires=Thu, 01 JAN 1999 00:00:10 GMT';
// }