import "./uploader.css";
import {Button} from "@mui/material";

const PictureUploader = (props) => {

  let inputRef;

  return (
    <div className="uploader-wrapper">
      <input type="file" accept="image/*"
        onChange={props.saveImage}
        // 클릭할 때 마다 file input의 value를 초기화 하지 않으면 버그가 발생할 수 있다
        // 사진 등록을 두개 띄우고 첫번째에 사진을 올리고 지우고 두번째에 같은 사진을 올리면 그 값이 남아있음!
        onClick={(e) => e.target.value = null}
        ref={refParam => inputRef = refParam}
        style={{display: "none"}}
      />
      <div className="img-wrapper">
        <img src={props.image.preview_URL} alt="img"/>
      </div>

      <div className="upload-button">
        <Button type="primary" variant="contained" onClick={() => inputRef.click()}>
          사진찾기
        </Button>
        <Button color="error" variant="contained" onClick={props.deleteImage}>
          제거하기
        </Button>
        {/* <Button color="success" variant="contained" onClick={sendImageToServer}>
          업로드
        </Button> */}
      </div>
    </div>
  );
}

export default PictureUploader;