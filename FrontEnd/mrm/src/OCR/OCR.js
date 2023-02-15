import React from "react";
import axios from "axios";
import html2canvas from 'html2canvas';

export const OCR = () =>{
    
    const sreenShot = (target) => {
        target = document.getElementById('chart_box')
        if (target != null) {
            var t = target;
            html2canvas(t).then(function(canvas) {
                var myImg = canvas.toDataURL("image/png");
                var blobBin = atob(myImg.split(',')[1]);	// base64 데이터 디코딩
                var array = [];
                for (var i = 0; i < blobBin.length; i++) {
                    array.push(blobBin.charCodeAt(i));
                }
                var file = new Blob([new Uint8Array(array)], {type: 'image/png'});
    
    
                var formdata = new FormData();
                formdata.append("file", file);
                console.log('여기까지 옴')
                axios({
                    method : 'post',
                    url : 'https://i8a406.p.ssafy.io/ocr/',
                    data : formdata,
                    processData : false,	// data 파라미터 강제 string 변환 방지!!
                    contentType : false,	// application/x-www-form-urlencoded; 방지!!
                }).then((res) => {
                    console.log(res.data)
                }).catch((err) =>
                    console.log(err))

            });
        }
    }
    
    return (
    <div>
        <div id="chart_box">
                <img src="images/test.PNG" />
        </div>
        <button id="btn" onClick={sreenShot}>보내기</button>
    </div>
    )
}
