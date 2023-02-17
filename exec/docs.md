# 사용한 JVM, 웹서버, WAS 제품등의 종류와 설정값, 버전(IDE버전 포함 기재) 
```
- MySQL
    - 설치 버전 : 8.0.31
        
        [MySQL :: Begin Your Download](https://dev.mysql.com/downloads/file/?id=514518)
        
    - 설치 가이드
        
        [MySQL 다운로드 및 설치하기(MySQL Community 8.0)](https://hongong.hanbit.co.kr/mysql-%EB%8B%A4%EC%9A%B4%EB%A1%9C%EB%93%9C-%EB%B0%8F-%EC%84%A4%EC%B9%98%ED%95%98%EA%B8%B0mysql-community-8-0/)
        
    - 계정 정보
        - 계정 : root
        - 비밀번호 : root1234
    - database 생성
        - create database if not exists `ssafy_web_db` collate utf8mb4_general_ci;
    - maria db
        - Hostname : [stg-yswa-kr-practice-db-master.mariadb.database.azure.com](http://stg-yswa-kr-practice-db-master.mariadb.database.azure.com/)
        - User Name : S08P11A406
        - Password : M6UIc80F2X

- Mongo DB
    - 설치 버전 : 6.0.4
    - mongodb+srv://S08P13A406:GGgdFbTJAY@ssafy.ngivl.mongodb.net/S08P13A406?authSource=admin
    
- 인텔리제이
    - **Community Edition** 설치
        
        [다운로드 IntelliJ IDEA: 우수성과 인체 공학이 담긴 JetBrains Java IDE](https://www.jetbrains.com/ko-kr/idea/download/#section=windows)
        
- Open JDK
    - 11 버전 설치
        
        [Java Platform, Standard Edition 11 Reference Implementations](https://jdk.java.net/java-se-ri/11)
        
    - 설치 가이드
        
        [[Java] OpenJDK 11 설치하기(JDK Builds from Oracle)](https://kjchoi.co.kr/16)
```
- 사용한 웹서버 : 
    - IDE : 
        - Intellij 
        - vscode 
        - 
    - tomcat 
    
    - Nginx 
    ### ec2 nginx 
    ```
    server{
    #    if ($host = i8a406.p.ssafy.io) {
    #        return 301 https://$host$request_uri;
    #    } # managed by Certbot

      listen 80; #80포트로 받을 때
      server_name i8a406.p.ssafy.io;
      return 301 https://i8a406.p.ssafy.io$request_uri;



    }
    server {
      listen 443 ssl;
      server_name i8a406.p.ssafy.io;
      # ssl 인증서 적용하
        ssl_certificate /etc/letsencrypt/live/i8a406.p.ssafy.io/fullchain.pem; # managed by Certbot
        ssl_certificate_key /etc/letsencrypt/live/i8a406.p.ssafy.io/privkey.pem; # managed by Certbot

      location /openroom/ {
        proxy_pass https://i8a406.p.ssafy.io:8085/;
      }

      location / {
        proxy_pass http://i8a406.p.ssafy.io:8082;
        charset utf-8;
      }
      location /ocr/ {
        proxy_pass http://i8a406.p.ssafy.io:8083/;
        charset utf-8;
      }

      location /api/ { # location 이후 특정 url을 처리하는 방법을 정의
        proxy_pass http://i8a406.p.ssafy.io:8081/; # Request에 대해 어디로 리다이렉트하는지
        proxy_redirect off;
        charset utf-8;
        proxy_http_version 1.1;
        proxy_set_header Connection "upgrade";
        proxy_set_header Upgrade $http_upgrade;
        proxy_set_header Host $http_host;
        proxy_set_header X-Real-IP $remote_addr;
        proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
        proxy_set_header X-Forwarded-Proto $scheme;
        proxy_set_header X-NginX-Proxy true;
        proxy_buffer_size 128k;
        proxy_buffers 4 256k;
        proxy_busy_buffers_size 256k;
     }
    }
    ```

    ### front nginx 
    ```
    server{
    listen 80;
    location / {
        root /app/build;
        index index.html index.htm;
        try_files $uri $uri/ /index.html;
        }
    }
    ```


    - Docker 
    
    - domain : i8a406.p.ssafy.io 

# 빌드 시 사용되는 환경 변수 등의 주요 내용 상세 기재 
front 
# 배포 시 특이사항 기재 
```
    location /openroom/ { // openvidu 
        proxy_pass https://i8a406.p.ssafy.io:8085/;
      }

    location / { // front 
        proxy_pass http://i8a406.p.ssafy.io:8082;
        charset utf-8;
      }
    location /ocr/ { // ocr
        proxy_pass http://i8a406.p.ssafy.io:8083/;
        charset utf-8;
      }

    location /api/ { // backend 
        proxy_pass http://i8a406.p.ssafy.io:8081/; # Request에 대해 어디로 리다이렉트하는지
        }
```
# DB 접속 정보 등 프로젝트 (ERD)에 활용되는 주요 게정 및 프로퍼티가 정의된 파일 목록
```
# MySql DB
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.url=jdbc:mysql://i8a406.p.ssafy.io:3306/mydb?serverTimezone=UTC&characterEncoding=UTF-8
spring.datasource.username=root
spring.datasource.password=root1234

# Mongo DB
spring.data.mongodb.uri=mongodb+srv://S08P13A406:GGgdFbTJAY@ssafy.ngivl.mongodb.net/S08P13A406?authSource=admin
```

# 프로젝트에서 사용하는 외부 서비스 정보를 정리한 문서 
```
#google login
spring.security.oauth2.client.registration.google.client-id=436992256766-o9fr5g901l6bbmj4ej983g5776o0igt3.apps.googleusercontent.com
spring.security.oauth2.client.registration.google.client-secret=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
spring.security.oauth2.client.registration.google.scope=profile,email
spring.security.oauth2.client.registration.google.redirect-uri=https://i8a406.p.ssafy.io/api/login/oauth2/code/google

#facebook login
spring.security.oauth2.client.registration.facebook.client-id=2069263643273308
spring.security.oauth2.client.registration.facebook.client-secret=bd3e2fba081e82d2c94a984ee9327936
spring.security.oauth2.client.registration.facebook.scope=public_profile,email

#naver login
spring.security.oauth2.client.registration.naver.client-id=WiKKL6Vdzgpx6WtEMIBE
spring.security.oauth2.client.registration.naver.client-secret=D_wKUmpltB
spring.security.oauth2.client.registration.naver.scope=name,email
spring.security.oauth2.client.registration.naver.client-name=Naver
spring.security.oauth2.client.registration.naver.authorization-grant-type=authorization_code
spring.security.oauth2.client.registration.naver.redirect-uri=https://i8a406.p.ssafy.io/api/login/oauth2/code/naver

#kakao login
spring.security.oauth2.client.registration.kakao.client-id =298f66bfc16539a8591e5c68e6f5ef76
spring.security.oauth2.client.registration.kakao.scope = profile_nickname, account_email
spring.security.oauth2.client.registration.kakao.client-name = Kakao
spring.security.oauth2.client.registration.kakao.authorization-grant-type = authorization_code
spring.security.oauth2.client.registration.kakao.redirect-uri = https://i8a406.p.ssafy.io/api/login/oauth2/code/kakao
spring.security.oauth2.client.registration.kakao.client-authentication-method = POST

#provider-naver
spring.security.oauth2.client.provider.naver.authorization-uri=https://nid.naver.com/oauth2.0/authorize
spring.security.oauth2.client.provider.naver.token-uri=https://nid.naver.com/oauth2.0/token
spring.security.oauth2.client.provider.naver.user-info-uri=https://openapi.naver.com/v1/nid/me
spring.security.oauth2.client.provider.naver.user-name-attribute=response

# provider-naver
spring.security.oauth2.client.provider.kakao.authorization-uri=https://kauth.kakao.com/oauth/authorize
spring.security.oauth2.client.provider.kakao.token-uri=https://kauth.kakao.com/oauth/token
spring.security.oauth2.client.provider.kakao.user-info-uri=https://kapi.kakao.com/v2/user/me
spring.security.oauth2.client.provider.kakao.user-name-attribute=id

# Google Email
email.id=MyRoomMoim@gmail.com
email.password=yckjgekzolhifwge
mail.smtp.auth=true
mail.smtp.starttls.required=true
mail.smtp.starttls.enable=true
mail.smtp.socketFactory.class=javax.net.ssl.SSLSocketFactory
mail.smtp.socketFactory.fallback=false
mail.smtp.port=465
mail.smtp.socketFactory.port=465

```

#DB 덤프 파일 최신본 

- 

#시연 시나리오

```
1.   홈 화면
1-1.   기본적인 로그인/회원가입 기능 설명
1-2.   데이터 담겨있는 테스트 계정 로그인
2.   마이페이지
2-1.   전체적인 UI 설명
2-2.   좌측 하단 +버튼을 통한 그룹 생성 해보기
2-3.   미리 생성되어 있던 그룹 참가하기
3.   그룹페이지
3-1.   상단의 할일, 일정 기능 설명
3-2.   할일/일정 추가를 통한 달력, 타임테이블 구현 설명
3-3.   그룹 메모, 실시간 채팅, 게시판, QnA 를 통한 커뮤니티적 기능 설명
4.   화상페이지
4-1.   그룹원들과 실시간 화상 미팅 시연
4-2.   채팅, 화면공유, ocr 기능 시연
```