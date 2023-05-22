# 마룸모 (마이 룸에서 하는 스터디 모임)

하나의 플랫폼에서 진행되는 스터디 활동

## Table of Content

1. [서비스 개요](#1-서비스-개요)
1. [기술 스택](#2-기술-스택)
1. [한눈에 보는 시스템 아키텍처](#3-한눈에-보는-시스템-아키텍처)
1. [팀원 소개](#4-팀원-소개)
1. [BackEnd Structure](#5-backend-structure)
1. [FrontEnd Structure](#6-frontend-structure)
1. [Wire Frames](#7-wire-frames)
1. [ERD](#8-erd)

## 1. 서비스 개요

저희 서비스가 추구하는 목표는 **하나의 스터디 플랫폼에서 모든 것을 해결하자**입니다.

- 자신의 할일와 일정 관리를 간편하게 할 수 있고, 통계를 한 눈에 볼 수 있습니다
- 독립적인 스터디 룸을 만들어 스터디원들과 소통, 자료 공유, 일정 관리 등을 할 수 있습니다.
- 스터디 룸에서는 실시간 채팅, 화상 채팅, 게시판, 질문 게시판 등을 제공합니다

## 2. 기술 스택

### FrontEnd

| <div align="center"><img src="./assets/readme/html.svg" alt="HTML5" width="50px" height="50px" /></div> | <div align="center"><img src="./assets/readme/css.svg" alt="CSS3" width="50px" height="50px" /></div> | <div align="center"><img src="./assets/readme/js.png" alt="JavaScript" width="50px" height="50px" /></div> |
| :-----------------------------------------------------------------------------------------------------: | :--------------------------------------------------------------------------------------------------: | :-------------------------------------------------------------------------------------------------------: |
|                                      &nbsp;&nbsp;HTML5&nbsp;&nbsp;                                      |                         &nbsp;&nbsp;&nbsp;&nbsp;CSS3&nbsp;&nbsp;&nbsp;&nbsp;                         |                                                JavaScript                                                 |

| <div align="center"><img src="./assets/readme/react.svg" alt="React" width="50px" height="50px" /> </div> | <div align="center"><img src="./assets/readme/redux-cover-imgage.jpg" alt="Recoil" width="50px" height="50px" /> </div> |
| :------------------------------------------------------------------------------------------------------: | :--------------------------------------------------------------------------------------------------------: |
|                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;React&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;                     |                     &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Redux&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;                     |                                                        Styled-components                                                        |

| <div align="center"><img src="./assets/readme/axios.png" alt="axios" width="100px" height="50px" /> </div> | <div align="center"><img src="./assets/readme/openvidu.png" alt="OpenVidu" width="100px" height="50px" /> </div> |
| :-------------------------------------------------------------------------------------------------------: | :-------------------------------------------------------------------------------------------------------------: |
|                                                   AXIOS                                                   |                                                    OpenVidu                                                     |

### BackEnd

| <div align="center"><img src="./assets/readme/java.svg" alt="java" width="50px" height="50px" /> </div> | <div align="center"><img src="./assets/readme/springboot.png" alt="springboot" width="100px" height="50px" /> </div> | <div align="center"><img src="./assets/readme/springsecurity.png" alt="springsecurity" width="100px" height="50px" /></div> | <div align="center"><img src="./assets/readme/gradle.png" alt="gradle" width="50px" height="50px" /></div> | <div align="center"><img src="./assets/readme/hibernate.png" alt="hibernate" width="50px" height="50px" /></div> |
| :----------------------------------------------------------------------------------------------------: | :-----------------------------------------------------------------------------------------------------------------: | :------------------------------------------------------------------------------------------------------------------------: | :-------------------------------------------------------------------------------------------------------: | :-------------------------------------------------------------------------------------------------------------: |
|                                                  Java                                                  |                                                     Spring-Boot                                                     |                                                      Spring-Security                                                       |                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Gradle&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;                     |                                  &nbsp;&nbsp;&nbsp;Hibernate&nbsp;&nbsp;&nbsp;                                  |

| <div align="center"><img src="./assets/readme/mysql.png" alt="mysql" width="70px" height="50px" /> </div> | <div align="center"><img src="./assets/readme/websocket.svg" alt="websocket" width="50px" height="50px" /></div> | <div align="center"><img src="./assets/readme/mongo.png" alt="redis" width="70px" height="50px" /></div> | <div align="center"><img src="./assets/readme/swagger.png" alt="swagger" width="50px" height="50px" /></div> | 
| :--------------------------------------------------------------------------------------------------------: | :---------------------------------------------------------------------------------------------------------: | :-------------------------------------------------------------------------------------------------------------: | :-----------------------------------------------------------------------------------------------------: 
|                                                  MySQL                                                   |                                                    Websocket                                                    |                                                  mongoDB                                                  |                                                  swagger                                                  |

### DevOps

| <div align="center"><img src="./assets/readme/docker.png" alt="docker" width="50px" height="50px" /> </div> | <div align="center"><img src="./assets/readme/jenkins.png" alt="jenkins" width="50px" height="50px" /></div> | <div align="center"><img src="./assets/readme/nginx.png" alt="nginx" width="50px" height="50px" /></div> | <div align="center"><img src="./assets/readme/ec2.png" alt="EC2" width="50px" height="50px" /></div> | <div align="center"><img src="./assets/readme/s3.png" alt="S3" width="50px" height="50px" /></div> |
| :--------------------------------------------------------------------------------------------------------: | :---------------------------------------------------------------------------------------------------------: | :-----------------------------------------------------------------------------------------------------: | :-------------------------------------------------------------------------------------------------: | :-----------------------------------------------------------------------------------------------: |
|                                                   Docker                                                   |                                                   Jenkins                                                   |                                                  NginX                                                  |                                               AWS EC2                                               |                                              AWS S3                                               |

## 3. 한눈에 보는 시스템 아키텍처

![system architecture](/assets/readme/system_architecture.png)

## 4. 팀원 소개

이상민 (팀장): Backend 총괄 + Frontend 개발 + 프로젝트 관리


조현동 (팀원): Backend 개발 + 배포


오준호 (팀원): Backend 개발 + DB 관리


박유진 (팀원): Frontend 총괄 + 디자인


전태영 (팀원): Frontend 개발 + 발표


곽준영 (팀원): Frontend 개발

## 5. BackEnd Structure

```
BackEnd
.
└── main                           
    ├── java
    │   └── com
    │       └── a406
    │           └── mrm
    │               ├── common            
    │               │   ├── filter
    │               │   ├── handler
    │               │   └── util
    │               ├── config              
    │               │   ├── auth
    │               │   ├── jwt
    │               │   └── oauth
    │               │       └── provider
    │               ├── model            
    │               │    ├── entity
    │               │    └── dto
    │               ├── controller          
    │               ├── repository         
    │               └── service           
    └── resources                           
        ├── README.md
        ├── application.properties
        └── email.properties

```

## 6. FrontEnd Structure

```
FrontEnd
.
├── public                 
│   └── images          
└── src               
    ├── OCR    
    ├── sagas           
    ├── sagas        
    ├── slice              
    └── components     
        ├── Calendar    
        │   └── DatePicker   
        ├── Category   
        ├── DnD   
        │   └── styles   
        ├── GroupRoom   
        │   ├── Board   
        │   │   └── ArticlePage   
        │   ├── Chat   
        │   ├── GroupRoomItem   
        │   ├── TimeList  
        │   └── OpenVidu    
        ├── ImageUpload   
        ├── Login   
        ├── Modal   
        │   ├── Group   
        │   ├── Profile   
        │   └── Todo    
        ├── MyRoom   
        │   └── MyRoomItem   
        ├── ScheduleThings
        │   └── styles   
        └── TodoThings     
            └── styles   

```

## 7. Wire Frames

- 로그인 화면 (회원가입, 아이디 찾기, 비밀번호 찾기)

  <img src="./assets/wireframes/login.PNG"/>

  <table>
    <tr>
      <td><img src="./assets/wireframes/signup.PNG" width="400px" height="200px"/></td>
      <td><img src="./assets/wireframes/findId.PNG" width="400px" height="200px"/></td>
      <td><img src="./assets/wireframes/findPwd.PNG" width="400px" height="200px"/></td>
    </tr>
  </table>


- 초기 메인 화면 (회원 가입후 첫 로그인 시)
  <img src="./assets/wireframes/myPage_init.PNG"/>

- 그룹 생성 및 참가
  <img src="./assets/wireframes/room_create_join.PNG"/>

- 활성화 된 메인 화면 (자신의 todo list와 통계, 메모, 일정을 한눈에 볼 수 있다)
  <img src="./assets/wireframes/myPage_after.PNG"/>

- 프로필 보기 및 수정
  <img src="./assets/wireframes/profile.PNG"/>

- 참가한 room의 화면 (게시판 종류, todo 관리, 메모, time table, 참가 인원 목록 등이 있다)
  <img src="./assets/wireframes/roomPage.PNG"/>

- 할일, 일정 생성 (원하는 날짜의 todo와 일정을 추가할 수 있다)
  <img src="./assets/wireframes/create_todo.PNG"/>
  <img src="./assets/wireframes/create_schedule.PNG"/>

- 일정 화면 (오른쪽 상단 달력 클릭 시 확인할 수 있다)
  <img src="./assets/wireframes/schedule.PNG"/> 

- 채팅방 입장 화면
  <img src="./assets/wireframes/chat.PNG"/>

- 화상 채팅방 입장 화면
  <img src="./assets/wireframes/camChat.PNG"/>

- 게시판 입장 화면
  <img src="./assets/wireframes/board.PNG"/>

- Q&A 게시판 입장 화면
  <img src="./assets/wireframes/qna.PNG"/>

## 8. ERD

![MRM ERD](/assets/readme/mrm_erd.png)
