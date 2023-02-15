import axios from "axios";

export function postCategoryData({ name }) {

    const category = {
        // roomId: roomId,
        name: name
    };

    console.log()

    const BASE_URL = 'https://i8a406.p.ssafy.io';   
    const url = BASE_URL + '/category';

    
    axios
        .post(url, category)
        .then((response)=> {
            console.log(response);
        })
    
}