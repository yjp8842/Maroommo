import axios from "axios";

export function postSubCategoryData({ name }) {

    const categorysub = {
        // category_id: category_id,
        name: name,
        // subtype: subtype
    };

    console.log()

    const BASE_URL = 'https://i8a406.p.ssafy.io';   
    const url = BASE_URL + '/categorysub';

    
    axios
        .post(url, categorysub)
        .then((response)=> {
            console.log(response);
        })
    
}