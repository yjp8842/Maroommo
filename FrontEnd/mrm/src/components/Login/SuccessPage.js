import api from "../../utils/axiosInstance";

function SuccessPage() {
    const params = new URLSearchParams(window.location.search);
    let accessToken = params.get("accessToken");

    localStorage.clear();
    localStorage.setItem("accessToken", accessToken);
    
    accessToken = "Bearer " + accessToken;

    api.get("/oauth/user")
        .then((response) => {
            if (response.status === 200) {
                console.log('로그인에 성공하셨습니다!');
                console.log(response);
                localStorage.setItem("refreshToken", response.data.refreshToken);

                // response.data.user 정보를 store에다가 저장하는 로직 추가해야 한다


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