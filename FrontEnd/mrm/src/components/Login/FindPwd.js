import axios from "axios";

export function FindPwd() {
  const namePwd = document.getElementById('name-pwd').value;
  const idPwd = document.getElementById('id-pwd').value;
  const emailPwd = document.getElementById('email-pwd').value;
  axios({
    method:"GET",
    url: '/user/help/pw',
    data:{
        "name": namePwd,
        "id": idPwd,
        "email": emailPwd,
    }
  })
  .then((res) => {
    console.log(res);
  })
  .catch((err) => {
    console.log(err);
  })
}