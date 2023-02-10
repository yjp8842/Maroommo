FROM python:3.10-slim-buster

ENV PYTHONUNBUFFERED=1
WORKDIR /ocr 
COPY . /ocr 
COPY requirements.txt /ocr/
 
RUN pip install -r requirements.txt

RUN apt-get update \
    && apt-get -y install tesseract-ocr \ 
    && apt-get -y install ffmpeg libsm6 libxext6
    
 
CMD ["python", "main.py"]