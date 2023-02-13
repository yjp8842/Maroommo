import axios from "axios";

export function FindId() {
  const findName = document.getElementById('name').value;
  const findEmail = document.getElementById('email').value;

  axios({
    method:"POST",
    url: 'https://i8a406.p.ssafy.io/api/user/help/id',
    data:{
        "name": findName,
        "email": findEmail,
    }
  })
  .then((res) => {
    if(res.data.isExist){
      alert("아이디는 "+res.data.userId+"입니다."); 
      window.location.replace("/"); 
    }
    else{
      alert("잘못된 회원 정보를 입력하였습니다.");
    }
  })
  .catch((err) => {
    alert("잘못된 회원 정보를 입력하였습니다.");
  })
}