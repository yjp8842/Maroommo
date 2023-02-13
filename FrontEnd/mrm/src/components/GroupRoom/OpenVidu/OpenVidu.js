import React, { Component } from "react";
import { OpenVidu } from "openvidu-browser";
import axios from "axios";
import styled from "styled-components";
import UserVideoComponent from "./UserVideoComponent";
import VideocamOutlinedIcon from "@mui/icons-material/VideocamOutlined";
import MicOutlinedIcon from "@mui/icons-material/MicOutlined";
import HeadsetIcon from "@mui/icons-material/Headset";
import VideocamOffOutlinedIcon from "@mui/icons-material/VideocamOffOutlined";
import MicOffIcon from "@mui/icons-material/MicOff";
import HeadsetOffIcon from "@mui/icons-material/HeadsetOff";
import CallEndIcon from "@mui/icons-material/CallEnd";
// import ChatIcon from "@mui/icons-material/Chat";
import ScreenshotMonitorIcon from '@mui/icons-material/ScreenshotMonitor';
import SportsEsportsIcon from '@mui/icons-material/SportsEsports';
import ScreenShareIcon from '@mui/icons-material/ScreenShare';

import OpenViduChat from "./OpenViduChat";
import OpenViduQnA from "./OpenViduQnA";

import "./OpenVidu.css";

// server url
const APPLICATION_SERVER_URL = "https://i8a406.p.ssafy.io:8085/";

// 테스트할때 아이디는 OPENVIDUAPP
// 비번 a406

const StreamContainerWrapper = styled.div`
  display: grid;
  place-items: center;
  ${(props) =>
    props.primary
      ? `
    grid-template-columns: repeat(1, 1fr);
    grid-template-rows: repeat(1, 1fr);
    `
      : `
    grid-template-columns: repeat(3, 1fr);
    `}
  grid-gap: 20px;
  height: 100%;
  padding: 10px;
  @media screen and (max-width: 800px) {
    background-color: red;
  }
`;

const Icon = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
  width: 50px;
  height: 50px;
  border-radius: 50%;
  background-color: #333;
  color: white;
  cursor: pointer;
  transition: 0.1s;
  &:hover {
    background-color: #3c4043;
  }
  ${(props) =>
    props.primary &&
    `
      background-color: red;
      color: white;
      &:hover{
          background-color: red;
      }
    `}
`;

class OpenChat extends Component {
  render() {
    return (
      <div className="container">
        {/* <Header>
          <StudyTitle>Java Study</StudyTitle>
        </Header> */}
        {this.state.session === undefined ? (
          <div className="middle">
            <div
              style={{
                position: "absolute",
                right: "0",
                left: "0",
                width: "300px",
                margin: "auto",
                height: "300px",
              }}
              id="join"
            >
              <div style={{ textAlign: 'center', marginTop: '400px' }}>
                <h1 style={{ color: "white" }}> 세션 참가하기 </h1>
                <form
                  style={{ display: "flex", justifyContent: "center" }}
                  className="form-group"
                  onSubmit={this.joinSession}
                >
                  <p className="text-center">
                    <input
                      className="btn btn-lg btn-success"
                      name="commit"
                      type="submit"
                      value="JOIN"
                    />
                  </p>
                </form>
              </div>
            </div>
          </div>
        ) : null}

        {this.state.session !== undefined ? (
          <div className="whole">
            <div className="middle">
              <div className="left">
                <div className="video-container">
                  {this.state.isShare === false ? (
                    <StreamContainerWrapper
                      ref={this.userRef}
                    >
                      {this.state.publisher !== undefined ? (
                        <div className="stream-container" key={this.state.publisher.stream.streamId}>
                          <UserVideoComponent
                            streamManager={this.state.publisher}
                          />
                        </div>
                      ) : null}
                      {this.state.subscribers.map((sub, i) => (
                        <div className="stream-container" key={sub.stream.streamId}>
                          <UserVideoComponent streamManager={sub} />
                        </div>
                      ))}
                    </StreamContainerWrapper>
                  ) : 
                    <div className="share">
                      {this.state.publisher !== undefined ? (
                        <div className="stream-container" key={this.state.publisher.stream.streamId}>
                          <UserVideoComponent
                            streamManager={this.state.publisher}
                          />
                        </div>
                      ) : null}
                      {this.state.subscribers.map((sub, i) => (
                        <div className="stream-container" key={sub.stream.streamId}>
                          <UserVideoComponent streamManager={sub} />
                        </div>
                      ))}
                    </div>
                  }
                </div>
              </div>

              <div className="bottom">
                <div className="bottom-box">
                  <Icon
                    primary={!this.state.isCamera}
                    onClick={() => this.handleToggle("camera")}
                  >
                    {this.state.isCamera ? (
                      <VideocamOutlinedIcon />
                    ) : (
                      <VideocamOffOutlinedIcon />
                    )}
                  </Icon>

                  <Icon
                    primary={!this.state.isMike}
                    onClick={() => this.handleToggle("mike")}
                  >
                    {this.state.isMike ? <MicOutlinedIcon /> : <MicOffIcon />}
                  </Icon>

                  <Icon
                    primary={!this.state.isSpeaker}
                    onClick={() => this.handleToggle("speaker")}
                  >
                    {this.state.isSpeaker ? <HeadsetIcon /> : <HeadsetOffIcon />}
                  </Icon>

                  <Icon style={{ marginLeft: '100px', marginRight: '100px' }} primary onClick={() => {
                    this.leaveSession();
                    window.close();  // session 나가면서 윈도우 창 꺼지도록
                  }}>
                    <CallEndIcon />
                  </Icon>

                  <Icon 
                    onClick={() => this.setState({ isShare: !this.state.isShare })}
                    // onClick={() => {
                    //   let publisher = this.OV.initPublisher("html-element-id", {
                    //     audioSource: undefined,
                    //     videoSource: "screen", // 웹캠 기본 값으로
                    //     publishAudio: true,
                    //     publishVideo: true,
                    //     resolution: "640x480",
                    //     frameRate: 30,
                    //     insertMode: "APPEND",
                    //     mirror: "false",
                    //   });
        
                    //   // this.mySession.publish(publisher);
        
                      
                    //   publisher.once('accessAllowed', (event) => {
                    //     publisher.stream.getMediaStream().getVideoTracks()[0].addEventListener('ended', () => {
                    //       console.log('User pressed the "Stop sharing" button');
                    //     });
                    //     this.mySession.publish(publisher);
                    //     this.setState({ mainStreamManager: publisher, publisher });
            
                    //   });
              
                    //   publisher.once('accessDenied', (event) => {
                    //     console.warn('ScreenShare: Access Denied');
                    //   });
                    // }}
                  >
                    <ScreenShareIcon />
                  </Icon>
                  <Icon>
                    <ScreenshotMonitorIcon />
                  </Icon>
                  <Icon>
                    <SportsEsportsIcon />
                  </Icon>
                </div>
              </div>
            </div>


            <div className="openvidu-chat">
              {/* <ChatBox /> */}
              <div className="toggle-btn">
                <button className="openvidu-toggle1" onClick={() => this.setState({ isChat: !this.state.isChat, isQnA: false })}>채팅</button>
                <button className="openvidu-toggle2" onClick={() => this.setState({ isQnA: !this.state.isQnA, isChat: false })}>Q&A</button>
              </div>
              {this.state.isChat === true ? (
                <OpenViduChat />
              ) : null}
              {this.state.isQnA === true ? (
                <OpenViduQnA />
              ) : null}
            </div>
          </div>
        ) : null}
      </div>
    );
  }

  constructor(props) {
    super(props);
    this.userRef = React.createRef();

    this.state = {
      mySessionId: "1",
      myUserName: "유진",  // userId
      session: undefined,
      mainStreamManager: undefined,
      publisher: undefined, // 로컬 웹캠 스트림
      subscribers: [], // 다른 사용자의 활성 스트림
      isMike: true,
      isCamera: true,
      isSpeaker: true,
      isChat: false,
      isQnA: false,
      isShare: false
    };

    this.joinSession = this.joinSession.bind(this);
    this.leaveSession = this.leaveSession.bind(this);
    this.handleMainVideoStream = this.handleMainVideoStream.bind(this);
    this.onbeforeunload = this.onbeforeunload.bind(this);
    this.handleToggle = this.handleToggle.bind(this);
  }

  componentDidMount() {
    // this.leaveSession();
    window.addEventListener("beforeunload", this.onbeforeunload);
    // 스터디방에서 화상회의 입장 -> props로 roomId로 받으면 세션id 업뎃 user 정보 전역변수 가져옴 -> 상태값 업뎃
  }

  componentWillUnmount() {
    window.removeEventListener("beforeunload", this.onbeforeunload);
  }

  onbeforeunload(e) {
    this.leaveSession();
  }

  // 화상회의 나갈때
  leaveSession() {
    const mySession = this.state.session;

    if (mySession) {
      mySession.disconnect();
    }

    this.OV = null;
    this.setState({
      session: undefined,
      subscribers: [],
      mySessionId: undefined,
      myUserName: undefined,
      mainStreamManager: undefined,
      publisher: undefined,
    });
  }

  deleteSubscriber(streamManager) {
    let subscribers = this.state.subscribers;
    let index = subscribers.indexOf(streamManager, 0);
    if (index > -1) {
      subscribers.splice(index, 1);
      this.setState({ subscribers: subscribers });
    }
  }

  handleMainVideoStream(stream) {
    if (this.state.mainStreamManager !== stream) {
      this.setState({ mainStreamManager: stream });
    }
  }

  handleToggle(kind) {
    if (this.state.publisher) {
      // eslint-disable-next-line default-case
      switch (kind) {
        case "camera":
          this.setState({ isCamera: !this.state.isCamera }, () => {
            console.log(this.state.publisher);
            this.state.publisher.publishVideo(this.state.isCamera);
          });
          break;

        case "speaker":
          this.setState({ isSpeaker: !this.state.isSpeaker }, () => {
            this.state.subscribers.forEach((s) =>
              s.subscribeToAudio(this.state.isSpeaker)
            );
          });
          break;

        case "mike":
          this.setState({ isMike: !this.state.isMike }, () => {
            this.state.publisher.publishAudio(this.state.isMike);
          });
          break;
      }
    }
  }

  joinSession() {
    // OpenVidu 객체 생성
    this.OV = new OpenVidu();

    // this.OV.setAdvancedConfiguration({
    //   publisherSpeakingEventsOptions: {
    //     interval: 50,
    //     threshold: -75,
    //   },
    // });

    this.setState(
      {
        session: this.OV.initSession(),
      },
      () => {
        let mySession = this.state.session;

        // Session 객체가 각각 새로운 stream에 대해 구독 후, subscribers 상태값 업뎃
        mySession.on("streamCreated", (e) => {
          // OpenVidu -> Session -> 102번째 줄 확인 UserVideoComponent를 사용하기 때문에 2번째 인자로 HTML
          // 요소 삽입X
          let subscriber = mySession.subscribe(e.stream, undefined);
          var subscribers = this.state.subscribers;
          subscribers.push(subscriber);

          this.setState({ subscribers });

          console.log(subscribers);
        });

        // 사용자가 화상회의를 떠나면 Session 객체에서 소멸된 stream을 받아와 subscribers 상태값 업뎃
        mySession.on("streamDestroyed", (e) => {
          this.deleteSubscriber(e.stream.streamManager);
        });

        // 서버 측에서 비동기식 오류 발생 시 Session 객체에 의해 트리거되는 이벤트
        mySession.on("exception", (exception) => {
          console.warn(exception);
        });

        // 발언자 감지했을 때
        mySession.on("publisherStartSpeaking", (event) => {
          for (let i = 0; i < this.userRef.current.children.length; i++) {
            if (
              JSON.parse(event.connection.data).clientData ===
              this.userRef.current.children[i].innerText
            ) {
              this.userRef.current.children[i].style.borderStyle = "solid";
              this.userRef.current.children[i].style.borderColor = "#1773EA";
            }
          }
          console.log(
            "User " + event.connection.connectionId + " start speaking"
          );
        });

        // 발언을 멈췄을 때
        mySession.on("publisherStopSpeaking", (event) => {
          console.log(
            "User " + event.connection.connectionId + " stop speaking"
          );
          for (let i = 0; i < this.userRef.current.children.length; i++) {
            if (
              JSON.parse(event.connection.data).clientData ===
              this.userRef.current.children[i].innerText
            ) {
              this.userRef.current.children[i].style.borderStyle = "none";
            }
          }
        });

        this.getToken().then((token) => {
          mySession
            .connect(token, {
              clientData: this.state.myUserName,
            })
            .then(() => {
              let publisher = this.OV.initPublisher(undefined, {
                audioSource: undefined,
                videoSource: undefined, // 웹캠 기본 값으로
                publishAudio: true,
                publishVideo: true,
                resolution: "640x480",
                frameRate: 30,
                insertMode: "APPEND",
                mirror: "false",
              });

              mySession.publish(publisher);

              this.setState({ mainStreamManager: publisher, publisher });
            })
            .catch((error) => {
              console.log("세션 연결 오류", error.code, error.message);
            });
        });
      }
    );
  }

  async getToken() {
    return this.createSession(this.state.mySessionId).then((sessionId) =>
      this.createToken(sessionId)
    );
  }

  async createSession(sessionId) {
    return new Promise((resolve, reject) => {
      let data = JSON.stringify({ customSessionId: sessionId });

      // headers에 들어갈 내용
      // Authorization: Basic EncodeBase64(OPENVIDUAPP:<YOUR_SECRET>)
      // Content-Type: application/json
      // btoa() - Base64 Encoding

      axios
        .post(APPLICATION_SERVER_URL + "openvidu/api/sessions", data, {
          headers: {
            // 권한
            Authorization: `Basic ${btoa(
              `OPENVIDUAPP:a406`
            )}`,
            "Content-Type": "application/json",
          },
        })
        .then((res) => {
          resolve(res.data.id);
        })
        .catch((res) => {
          let error = Object.assign({}, res);

          if (error?.response?.status === 409) {
            resolve(sessionId);
          } else if (
            window.confirm(
              'No connection to OpenVidu Server. This may be a certificate error at "' +
              APPLICATION_SERVER_URL +
                '"\n\nClick OK to navigate and accept it. If no certifica' +
                "te warning is shown, then check that your OpenVidu Server is up and running at" +
                ' "' +
                APPLICATION_SERVER_URL +
                '"'
            )
          ) {
            // window.location.assign(OPENVIDU_SERVER_URL + "accept-certificate");
          }
        });
    });
  }

  async createToken(sessionId) {
    return new Promise((resolve, reject) => {
      let data = {};

      // headers에 들어갈 내용
      // Authorization: Basic EncodeBase64(OPENVIDUAPP:<YOUR_SECRET>)
      // Content-Type: application/json
      // btoa() - Base64 Encoding

      axios
        .post(
          APPLICATION_SERVER_URL+`openvidu/api/sessions/${sessionId}/connection`,
          data,
          {
            headers: {
              // 권한
              Authorization: `Basic ${btoa(
                `OPENVIDUAPP:a406`
              )}`,
              "Content-Type": "application/json",
            },
          }
        )
        .then((res) => {
          resolve(res.data.token);
        })
        .catch((error) => reject(error));
    });
  }
}

export default OpenChat;