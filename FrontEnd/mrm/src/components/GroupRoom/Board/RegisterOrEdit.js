import React from "react";
import { Link } from "react-router-dom";
// import PictureUploader2 from "../../ImageUpload/PictureUploader";

function RegisterOrEdit(props) {
  // console.log(props.picture)
  return (
    <div style={{ width: "40vw", height: '60vh', margin: "2rem auto" }}>
      <form enctype="multipart/form-data" onSubmit={props.handleSubmit}>
        <br/>
        <div style={{ width: "40vw", display: 'flex', flexDirection: 'column' }}>
          <div style={{ width: '43vw', display: 'flex', justifyContent: 'flex-end' }}>
            <input onChange={props.onImageHandler} type="file" name="picture"
              accept="image/*,audio/*,video/mp4,video/x-m4v,application/pdf,application/vnd.ms-excel,application/vnd.openxmlformats-officedocument.spreadsheetml.sheet,application/vnd.ms-powerpoint,application/vnd.openxmlformats-officedocument.presentationml.presentation,.csv"/>
          </div>
          <div style={{ width: '100%', display: 'flex', justifyContent: 'center', marginTop: '2vh' }}>
            <label style={{ fontSize: '1.5rem', paddingTop: '1.5vh' }}>제목 :</label>
            <input onChange={props.handleRegisterChange}
              value={props.titleValue} type='text' name='title' style={{ width: '34vw', marginLeft: '1vw', height: '4vh', borderRadius: '10px', border: 'none', boxShadow: "5px 5px 8px rgba(0, 0, 0, 0.35)", padding: '10px 20px', fontSize: '1rem' }}/>
          </div>
          {/* <hr></hr> */}
          <div style={{ width: '100%', display: 'flex', justifyContent: 'center', marginTop: '3vh' }}>
            <label style={{ fontSize: '1.5rem', paddingTop: '1.5vh' }}>내용 :</label>
            <textarea onChange={props.handleRegisterChange}
            value={props.contentValue} name='content' style={{ width: '34vw', marginLeft: '1vw', height: '41vh', resize: 'none', borderRadius: '10px', border: 'none', boxShadow: "5px 5px 8px rgba(0, 0, 0, 0.35)", padding: '30px 20px', fontSize: '1rem' }}/>
          </div>
          {/* <hr></hr> */}
          {/* <PictureUploader2 formData={props.formData}/> */}
          {/* <input type='file'>파일2</input> */}
        </div>
        <div style={{ width: '36vw', display: 'flex', justifyContent: 'space-between', marginLeft: '4vw', marginTop: '2vh' }}>
          <Link to= {props.updateRequest
              ? `/group/${props.groupId}/board/article/${props.id}`
              : `/group/${props.groupId}/board`}>   
            <button style={{ width: '4vw', height: '4vh', borderRadius: '14px', border: 'none', boxShadow: "5px 5px 8px rgba(0, 0, 0, 0.35)", backgroundColor: 'white' }}>뒤로가기</button>
          </Link>
          
          <button onClick={props.handleSubmit} style={{ width: '4vw', height: '4vh', borderRadius: '14px', border: 'none', boxShadow: "5px 5px 8px rgba(0, 0, 0, 0.35)", backgroundColor: 'white' }}>
            {props.updateRequest ? "수정" : "등록"}
          </button>
        </div>
      </form>
    </div>
  )
}

export default RegisterOrEdit