import uuid
import os
import pytesseract
import uvicorn
from fastapi.middleware.cors import CORSMiddleware
from fastapi import FastAPI,UploadFile

app = FastAPI(
    docs_url=f"/ocr_docs",
    openapi_url="/openapi.json",
    redoc_url=None,
)
app.openapi_version = "3.0.0"

origins = [
    "http://i8a406.p.ssafy.io:8083/",
    "https://i8a406.p.ssafy.io:8083/", 
    "http://i8a406.p.ssafy.io/",
    "https://i8a406.p.ssafy.io/"
]
app.add_middleware(
    CORSMiddleware,
    allow_origins = origins, 
    allow_credentials = True,
    allow_methods = ["*"],
    allow_headers = ["*"]
)

@app.post("/")
async def Ocr(file: UploadFile):
    UPLOAD_DIR = "/img_dir/"  
    
    content = await file.read()
    filename = f"{str(uuid.uuid4())}.jpg"  # uuid로 유니크한 파일명으로 변경
    with open(os.path.join(UPLOAD_DIR, filename), "wb") as fp:
        fp.write(content)  

    config = ('-l kor+eng --oem 3 --psm 11')
    text = pytesseract.image_to_string(UPLOAD_DIR+filename, config=config)
    os.remove(UPLOAD_DIR+filename)
    return text

if __name__ == "__main__":
    uvicorn.run(app=app, host='0.0.0.0', port=8083)
    # uvicorn.run(app,host='localhost', port= 8083)