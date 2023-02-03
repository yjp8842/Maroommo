import axios from "axios";

export function FindId() {
  const findName = document.getElementById('name-id').value;
  const findEmail = document.getElementById('email-id').value;
  axios({
    method:"GET",
    url: '/user/help/id',
    data:{
        "name": findName,
        "email": findEmail,
    }
  })
  .then((res) => {
    console.log(res);
  })
  .catch((err) => {
    console.log(err);
  })
}