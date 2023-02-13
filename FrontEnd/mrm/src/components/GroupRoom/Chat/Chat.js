import React, { useEffect, useRef, useState } from "react";
import * as StompJs from "@stomp/stompjs";
import * as SockJS from "sockjs-client";
import axios from "axios";
import "./Chat.css";
import { useSelector } from "react-redux";

const ROOM_SEQ = 1;

// 데이터 타입
// private int roomId;
// private String userId;
// private String userNickname;
// private String message;
// private LocalDateTime localDateTime;
// private String date;  2023년 02월 02일 (Thu)
// private String time;  PM 22시 13분

const Chat = () => {
  const client = useRef({});
  const [roomId, setRoomId] = useState("");
  const [userId, setUserId] = useState("");
  const [userNickname, setUserNickname] = useState("");
  const [message, setMessage] = useState("");
  let [chatMessages, setChatMessages] = useState([]);

  // roomId는 룸버튼을 눌렀을 때 roomId 정보를 store에 저장하는 방식으로 해야 하나..?
  // store에서 가져올 것 : userId, userNickname

  const {id, nickname} = useSelector((state) => ({
    id: state.userInfoReducers.user.id,
    nickname: state.userInfoReducers.user.nickname,
  }))

  useEffect(() => {
    connect();
    initRoom();

    // setRoomId();
    setUserId(id);
    setUserNickname(nickname);

    return () => disconnect();
  }, []);

  const initRoom = () => {
    // ROOM_SEQ -> roomId로 바꾸기
    axios.get('https://i8a406.p.ssafy.io/api/chat/room/'+ROOM_SEQ).then(response => {
      var list = [];
      response.data.chats.forEach(chat => {
        list.push({
          roomId: chat.roomId,
          userId: chat.userId,
          userNickname: chat.userNickname,
          message: chat.message,
          date: chat.date,
          time: chat.time
        })
      });
      setChatMessages(list);
    })
  }

  const connect = () => {
    client.current = new StompJs.Client({
      // brokerURL: "ws://localhost:8080/ws/websocket", // 웹소켓 서버로 직접 접속
      webSocketFactory: () => new SockJS("https://i8a406.p.ssafy.io/api/ws"), // proxy를 통한 접속
      debug: function (str) {
        console.log(str);
      },
      reconnectDelay: 5000,
      heartbeatIncoming: 4000,
      heartbeatOutgoing: 4000,
      onConnect: () => {
        console.log('success');
        subscribe();
      },
      onStompError: (frame) => {
        console.error(frame);
      },
    });

    client.current.activate();
  };

  const disconnect = () => {
    client.current.deactivate();
  };

  // chatMessages에 변화가 생겼을 때 스크롤 내려가도록.
  useEffect(() => {
    var chatInBox = document.getElementById('chatinbox');
    chatInBox.scrollTop = chatInBox.scrollHeight;
  }, [chatMessages])

  // 메시지 받기 -> time 필요함
  const subscribe = () => {
    // ROOM_SEQ -> roomId로 바꾸기
    client.current.subscribe('/sub/chat/room/'+ROOM_SEQ, (message) => {
      setChatMessages((chatMessages) => [
        ...chatMessages, JSON.parse(message.body)
      ]);
    });

    // 메시지 받았을 때 스크롤 내려가도록.
    var chatInBox = document.getElementById('chatinbox');
    chatInBox.scrollTop = chatInBox.scrollHeight;
  };

  // 메시지 보내기 + time 보낼 필요없음
  const publish = (message) => {
    if (!client.current.connected) {
      return;
    }

    client.current.publish({
      destination: "/pub/chat/message",
      body: JSON.stringify({
        // ROOM_SEQ -> roomId로 바꾸기
        roomId: ROOM_SEQ,
        userId: userId,
        userNickname: userNickname,
        message: message
      }),
    });

    setMessage("");
  };

  const onKeyPress = (e) => {
    if (e.key === 'Enter') {
      publish(message);
    }
  };

  // 만약 시간-분이 같다면 마지막 채팅에만 시간 출력 & 첫번째 채팅에만 userId 출력 (가능하다면)

  return (
    <div className="chatbox">
      {/* {chatMessages && chatMessages.length > 0 && ( */}
      <div className="chatinbox" id="chatinbox">
        {/* <div>{chatMessages[0].date}</div> */}

        {chatMessages.map((chatMessage, index) => {
          let displayDate = false;
          const isCreated = chatMessage.date;
  
          if (index > 0) {
            const nextCreated = chatMessages[index - 1].date;
  
            if (isCreated !== nextCreated) {
              displayDate = true;
            } else {
              displayDate = false;
            }
          } else {
            displayDate = true;
          }

          if (chatMessage.message.length > 0) {
            if (chatMessage.userId === userId) {
              if (displayDate) {
                return (
                  <div>
                    <div className="datebox">
                      <p>{isCreated}</p>
                    </div>
                    <div className="userchat">
                      <p className="time">{chatMessage.time}</p>
                      <div className="userchat-inbox">
                        <p>{chatMessage.userNickname}</p>
                        <p className="usermsg" key={index}>{chatMessage.message}</p>
                      </div>
                    </div>
                  </div>
                )
              } else {
                return (
                  <div className="userchat">
                    <p className="time">{chatMessage.time}</p>
                    <div className="userchat-inbox">
                      <p>{chatMessage.userNickname}</p>
                      <p className="usermsg" key={index}>{chatMessage.message}</p>
                    </div>
                  </div>
                )
              }
            } else {
              if (displayDate) {
                return (
                  <div>
                    <div className="datebox">
                      <p>{isCreated}</p>
                    </div>
                    <div className="receivechat">
                      <div className="receivechat-inbox">
                        <p>{chatMessage.userNickname}</p>
                        <p className="receivemsg" key={index}>{chatMessage.message}</p>
                      </div>
                      <p className="time">{chatMessage.time}</p>
                    </div>
                  </div>
                )
              } else {
                return (
                  <div className="receivechat">
                    <div className="receivechat-inbox">
                      <p>{chatMessage.userNickname}</p>
                      <p className="receivemsg" key={index}>{chatMessage.message}</p>
                    </div>
                    <p className="time">{chatMessage.time}</p>
                  </div>
                )
              }
            }
          } else {
            return 0;
          }
        })}
      </div>

      <div className="inputbox">
        {/* 된다면 엔터쳤을때 publish 되게끔 */}
        <input
          className="chatinput"
          type={"text"}
          placeholder={"메시지를 입력하세요"}
          value={message}
          onChange={(e) => setMessage(e.target.value)}
          onKeyPress={onKeyPress}
        />
        <button className="chatbtn" onClick={() => publish(message)}>보내기</button>
      </div>
    </div>
  );
};

export default Chat;