import axios from 'axios';

// const BASE_URL = 'http://localhost:8080'
const BASE_URL = 'https://i8a406.p.ssafy.io/api'

const api = axios.create({
    baseURL: BASE_URL
});

api.interceptors.request.use(
    function (config) {
        const accessToken = localStorage.getItem('accessToken');
    if (accessToken) {
        config.headers['Authorization'] = 'Bearer ' + accessToken;
    }
    return config;
    },
    function (error) {
        return Promise.reject(error);
    }
)

api.interceptors.response.use(
    (res) => res,
    async (err) => {
        const { config, response: { status } } = err;

        if (status === 401) {
            const accessToken = localStorage.getItem('accessToken');  
            const refreshToken = localStorage.getItem('refreshToken');   

            await axios.get({ 
                url: BASE_URL+`/user/reissue`,
                method: "POST",
                datas: {
                    accessToken: accessToken,
                    refreshToken: refreshToken,
                },
            })
            .then((res)=>{
                localStorage.setItem('accessToken',accessToken);
                localStorage.setItem('refreshToken',refreshToken);
            })
            .catch((err)=>{
                window.location.href = "/";
            });
            return Promise.reject(err);
        }        
        return Promise.reject(err);
    }
)

export default api;



/**
    import api from "../../utils/axiosInstance";

    api.get("/board");

 */