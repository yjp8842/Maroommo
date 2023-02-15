import React from "react";
import { Link } from "react-router-dom";


function RegisterOrEditQuestion(props) {
  return (
    <div style={{ maxWidth: "700px", margin: "2rem auto" }}>
      <form enctype="multipart/form-data" onSubmit={props.handleSubmit}>
        <br/>
        <div style={{ maxWidth: "700px", margin: "2rem" }}>
          <label>제목 :</label>
          <br></br>
          <input onChange={props.handleRegisterChange}
            value={props.titleValue} type='text' name='title'/>
          <hr></hr>
            <textarea onChange={props.handleRegisterChange}
            value={props.contentValue} name='content'/>
          <hr></hr>
          {/* <PictureUploader2 formData={props.formData}/> */}
          {/* <input type='file'>파일2</input> */}
        </div>
        <input onChange={props.onImageHandler} type="file" name="picture" 
        accept="image/*,audio/*,video/mp4,video/x-m4v,application/pdf,application/vnd.ms-excel,application/vnd.openxmlformats-officedocument.spreadsheetml.sheet,application/vnd.ms-powerpoint,application/vnd.openxmlformats-officedocument.presentationml.presentation,.csv"/>
        <button onClick={props.handleSubmit}>
          {props.updateRequest ? "수정" : "등록"}
        </button>
      </form>
        <Link to= {props.updateRequest
            ? `/group/${props.groupId}/question/questionArticle/${props.id}`
            : `/group/${props.groupId}/question`}>   
          <button>뒤로가기</button>
        </Link>
    </div>
  )
}

export default RegisterOrEditQuestion;