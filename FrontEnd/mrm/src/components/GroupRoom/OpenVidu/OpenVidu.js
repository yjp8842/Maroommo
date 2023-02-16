import React, { Component } from "react";
import { Link } from 'react-router-dom';
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

// import OpenViduChat from "./OpenViduChat";

import { Fab, Tooltip } from '@mui/material';
import SendIcon from '@mui/icons-material/Send';
import './OpenViduChat.css';

import "./OpenVidu.css";
import { connect } from "react-redux";
import html2canvas from 'html2canvas';

// server url
const APPLICATION_SERVER_URL = "https://i8a406.p.ssafy.io:8085/";

// 테스트할때 아이디는 OPENVIDUAPP
// 비번 a406

const StreamContainerWrapper = styled.div`
  display: grid;
  place-items: center;
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
    function noEvent(event) { // 새로 고침 방지
      if (event.keyCode === 116) {
          // event.keyCode = 2;
          return false;
      } else if (event.ctrlKey
              && (event.keyCode === 78 || event.keyCode === 82)) {
          return false;
      }
    }
    document.onkeydown = noEvent;

    // const subShareScreen = document.getElementById('subshare');
    
    return (
      <div className="container">

        {/* 총 9명이 넘게 들어왔을 경우 */}
        {this.state.subscribers.length > 8 ? (
          <div className="alert">
            <div>
              <p>인원이 초과되었습니다.</p>
              <Link to={`/group/`+this.props.group.id} onClick={() => {this.leaveSession();}}>확인</Link>
            </div>
          </div>
        ) : (
          
          this.state.session !== undefined ? (
            <div className="whole">
              <div className="middle">
                <div className="left">
                  <div className="video-container">


                    {/* 내가 화면 공유 했을 때 */}
                    {this.state.isShare === true ? (
                      <div className="width1" ref={this.userRef}>
                        {this.state.publisher !== undefined ? (
                          <div className="share-screen">
                            <div className="share-sub">
                              {this.state.subscribers.map((sub, i) => (
                                <div className="share-subs"><UserVideoComponent streamManager={sub} key={sub.stream.streamId} /></div>
                              ))}
                            </div>
                            <div className="share-pub" id="chart_box">
                              <UserVideoComponent
                                streamManager={this.state.mainStreamManager}
                                key={this.state.mainStreamManager.stream.streamId}
                              />
                            </div>
                          </div>
                        ) : null}
                      </div>
                    ) : (

                      // 내가 화면 공유 하지 않았을 때
                      <>
                        {this.state.subscribers.map((sub, i) => (
                          <>
                            {/* sub 중에 화면 공유한 사람이 있을 때 */}
                            {this.state.subscribers[i].stream.typeOfVideo === 'SCREEN' ? (
                              <div className="width1" ref={this.userRef} id="subshare">
                                <div className="share-screen">
                                  <div className="share-sub">
                                    {this.state.subscribers.map((sub, i) => (
                                      <div className="share-subs">
                                        <UserVideoComponent streamManager={this.state.publisher} key={this.state.publisher.stream.streamId} />
                                        <UserVideoComponent streamManager={sub} key={sub.stream.streamId} />
                                      </div>
                                    ))}
                                  </div>
                                  <div className="share-pub" id="chart_box">
                                    <UserVideoComponent
                                      streamManager={this.state.subscribers[i]}
                                      key={this.state.subscribers[i].stream.streamId}
                                    />
                                  </div>
                                </div>
                              </div>
                            ) : 
                              // null
                              (
                                // subShareScreen === null && 
                                this.state.subscribers.length < 1 ? (
                                  <StreamContainerWrapper
                                    className="width1"
                                    ref={this.userRef}
                                  >
                                    {this.state.publisher !== undefined ? (
                                      <div className="stream-container under-one">
                                        <UserVideoComponent
                                          streamManager={this.state.publisher}
                                          key={this.state.publisher.stream.streamId}
                                        />
                                        {/* {this.state.subscribers.map((sub, i) => (
                                          <UserVideoComponent streamManager={sub} key={sub.stream.streamId} />
                                        ))} */}
                                      </div>
                                    ) : null}
                                  </StreamContainerWrapper>
                                ) : (
        
                                  // 총 2명일 때
                                  // subShareScreen === null && 
                                  this.state.subscribers.length < 2 ? (
                                    <StreamContainerWrapper
                                      className="width1"
                                      ref={this.userRef}
                                    >
                                      {this.state.publisher !== undefined ? (
                                        <div className="stream-container under-two">
                                          <UserVideoComponent
                                            streamManager={this.state.publisher}
                                            key={this.state.publisher.stream.streamId}
                                          />
                                          {this.state.subscribers.map((sub, i) => (
                                            <UserVideoComponent streamManager={sub} key={sub.stream.streamId} />
                                          ))}
                                        </div>
                                      ) : null}
                                    </StreamContainerWrapper>
                                  ) : (
                                    
                                    // 총 3-4명일 때
                                    // subShareScreen === null && 
                                    this.state.subscribers.length < 4 ? (
                                      <StreamContainerWrapper
                                      className="width2"
                                        ref={this.userRef}
                                      >
                                        {this.state.publisher !== undefined ? (
                                          <div className="stream-container under-four">
                                            <UserVideoComponent
                                              streamManager={this.state.publisher}
                                              key={this.state.publisher.stream.streamId}
                                            />
                                            {this.state.subscribers.map((sub, i) => (
                                              <UserVideoComponent streamManager={sub} key={sub.stream.streamId} />
                                            ))}
                                          </div>
                                        ) : null}
                                      </StreamContainerWrapper>
                                    ) : (
        
                                      // 총 5-6명일 때
                                      // subShareScreen === null && 
                                      this.state.subscribers.length < 6 ? (
                                        <StreamContainerWrapper
                                        className="width1"
                                          ref={this.userRef}
                                        >
                                          {this.state.publisher !== undefined ? (
                                            <div className="stream-container under-six">
                                              <UserVideoComponent
                                                streamManager={this.state.publisher}
                                                key={this.state.publisher.stream.streamId}
                                              />
                                              {this.state.subscribers.map((sub, i) => (
                                                <UserVideoComponent streamManager={sub} key={sub.stream.streamId} />
                                              ))}
                                            </div>
                                          ) : null}
                                        </StreamContainerWrapper>
                                      ) : (
        
                                        // 총 7-9명일 때
                                        // subShareScreen === null && 
                                        this.state.subscribers.length < 9 ? (
                                          <StreamContainerWrapper
                                            className="width2"
                                              ref={this.userRef}
                                            >
                                            {this.state.publisher !== undefined ? (
                                              <div className="stream-container under-nine">
                                                <UserVideoComponent
                                                  streamManager={this.state.publisher}
                                                  key={this.state.publisher.stream.streamId}
                                                />
                                                {this.state.subscribers.map((sub, i) => (
                                                  <UserVideoComponent streamManager={sub} key={sub.stream.streamId} />
                                                ))}
                                              </div>
                                            ) : null}
                                          </StreamContainerWrapper>
                                        ) : (
        
                                          null
        
                                        )
                                      )
                                    )
                                  )
                                )
                              )
                            }
                          </>
                        ))}
                        

                        
                      </>
                      

                    )}


                    
  
  
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
  
                    <Link to={`/group/`+this.props.group.id} onClick={() => {this.leaveSession();}}>
                      <Icon style={{ marginLeft: '100px', marginRight: '100px' }} primary 
                      //   onClick={() => {
                      //     this.leaveSession();
                      //     // window.close();  // session 나가면서 윈도우 창 꺼지도록
                      // }}
                      >
                        <CallEndIcon />
                      </Icon>
                    </Link>
  
                    <Icon 
                      onClick={() => 
                      {
                        // 공유하는 버튼 눌렀을 때 이전에 publish 하던거 unpublish하고
                        // 새로운 publisher 만들어서 publish 하도록.
                        

                        this.state.session.unpublish(this.state.publisher);
                        let newPublisher = this.OV.initPublisher("html-element-id", {
                          audioSource: undefined,
                          videoSource: "screen", // 웹캠 기본 값으로
                          publishAudio: true,
                          publishVideo: true,
                          resolution: "640x360",
                          frameRate: 30,
                          insertMode: "APPEND",
                          mirror: "false",
                        });
          
                        newPublisher.once('accessAllowed', (event) => {
                          newPublisher.stream.getMediaStream().getVideoTracks()[0].addEventListener('ended', () => {
                            console.log('User pressed the "Stop sharing" button');
                            // 공유 중지했을 때
                            this.setState({ isShare: false });
                            this.state.session.unpublish(newPublisher);
                            this.state.session.publish(this.state.publisher);
                            this.setState({ mainStreamManager: this.state.publisher })
                          });
  
                          // 공유했을 때
                          this.setState({ isShare: true });
                          this.state.session.publish(newPublisher);
                          this.setState({ mainStreamManager: newPublisher, newPublisher });
                        
                        });
                        
                        // 공유하지 못했을 때
                        newPublisher.once('accessDenied', (event) => {
                          console.warn('ScreenShare: Access Denied');
                          this.state.session.publish(this.state.publisher);
                          // this.setState({ isShare: false });
                        });
                      }}
                    >
                      <ScreenShareIcon />
                    </Icon>
                    <Icon onClick={this.sreenShot}>
                      <ScreenshotMonitorIcon />
                    </Icon>
                    <Icon>
                      <SportsEsportsIcon />
                    </Icon>
                  </div>
                </div>
              </div>
  
  
              <div className="openvidu-chat">
                <div className="openvidu-chat-name"><h2>채팅</h2></div>
              
                <div id="chatContainer">
                  <div id="chatComponent">
                    <div className="message-wrap" ref={this.chatScroll}>
                      {this.state.messageList.map((data, i) => (
                        <div
                          key={i}
                          id="remoteUsers"
                        >
                          <div className="msg-detail">
                            <div className="msg-info">
                              <p> {data.nickname}</p>
                            </div>
                            <div className="msg-content">
                              <span className="triangle" />
                              <p className="text">{data.message}</p>
                            </div>
                          </div>
                        </div>
                      ))}
                    </div>
  
                    <div id="messageInput">
                      <textarea
                        placeholder="Send a message"
                        id="chatInput"
                        value={this.state.message}
                        onChange={this.handleChange}
                        onKeyPress={this.handlePressKey}
                      />
                      <Tooltip title="Send message">
                        <Fab size="small" id="sendButton" onClick={this.sendMessage}>
                          <SendIcon />
                        </Fab>
                      </Tooltip>
                    </div>
                  </div>
                </div>
  
              </div>
            </div>
          ) : (
            null
          )
        )}

      </div>
    );
  }

  constructor(props) {
    super(props);
    this.userRef = React.createRef();

    this.state = {
      mySessionId: this.props.group.id.toString(),  // store에 저장된 roomId
      myUserName: this.props.user.nickname,  // store에 저장된 nickname
      session: undefined,
      mainStreamManager: undefined,
      publisher: undefined, // 로컬 웹캠 스트림
      subscribers: [], // 다른 사용자의 활성 스트림
      isMike: true,
      isCamera: true,
      isSpeaker: true,
      isShare: false,
      messageList: [],
      message: '',
      isSubShare: false,
    };

    this.joinSession = this.joinSession.bind(this);
    this.leaveSession = this.leaveSession.bind(this);
    this.handleMainVideoStream = this.handleMainVideoStream.bind(this);
    this.onbeforeunload = this.onbeforeunload.bind(this);
    this.handleToggle = this.handleToggle.bind(this);

    this.chatScroll = React.createRef();
    this.handleChange = this.handleChange.bind(this);
    this.handlePressKey = this.handlePressKey.bind(this);
    this.sendMessage = this.sendMessage.bind(this);
  }

  sreenShot(target) {
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

  componentDidMount(e) {
    // this.leaveSession();
    // window.addEventListener("beforeunload", this.onbeforeunload);
    if (this.state.subscribers.length < 9) {
      this.joinSession();
    } else {
      return;
    }
  }

  componentWillUnmount() {
    window.removeEventListener("beforeunload", this.onbeforeunload);
  }

  onbeforeunload(e) {
    this.leaveSession();
  }

  handleChange(event) {
    this.setState({ message: event.target.value });
  }

  handlePressKey(event) {
    if (event.key === 'Enter') {
      this.sendMessage();
    }
  }

  sendMessage() {
    console.log(this.state.message);
    if (this.state.message) {
      let message = this.state.message.replace(/ +(?= )/g, '');
      if (message !== '' && message !== ' ') {
        const data = { message: message, nickname: this.state.myUserName, streamId: this.state.mainStreamManager.stream.streamId };
        this.state.mainStreamManager.stream.session.signal({
          data: JSON.stringify(data),
          type: 'chat',
        });
      }
    }
    this.setState({ message: '' });
  }

  scrollToBottom() {
    setTimeout(() => {
      try {
        this.chatScroll.current.scrollTop = this.chatScroll.current.scrollHeight;
      } catch (err) {}
    }, 20);
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

    this.setState(
      {
        session: this.OV.initSession(),
      },
      () => {
        let mySession = this.state.session;
        
        // 채팅
        mySession.on('signal:chat', (event) => {
          const data = JSON.parse(event.data);
          let messageList = this.state.messageList;
          messageList.push({ connectionId: event.from.connectionId, nickname: data.nickname, message: data.message });
          this.setState({ messageList: messageList });
          this.scrollToBottom();
        });

        // Session 객체가 각각 새로운 stream에 대해 구독 후, subscribers 상태값 업뎃
        mySession.on("streamCreated", (e) => {
          let subscriber = mySession.subscribe(e.stream, undefined);
          var subscribers = this.state.subscribers;
          subscribers.push(subscriber);

          this.setState({ subscribers });

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
          if (this.userRef.current.children.length !== null) {
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
          }
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

        this.getToken(this.state.mySessionId).then((token) => {
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
                resolution: "640x360",
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
  };

  async getToken(mySessionId) {
    return this.createSession(mySessionId).then((sessionId) => 
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
          console.log(typeof(this.props.group.id))
          console.log(typeof(this.props.user.nickname))
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
  };
};

const getStoreData = (state) => ({
  user: state.userInfoReducers.user, 
  group: state.groupInfoReducers.group
})

export default connect(getStoreData)(OpenChat);