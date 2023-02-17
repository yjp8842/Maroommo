import React, { Component } from 'react';
import OpenViduVideoComponent from './OvVideo';
import './UserVideo.css';

export default class UserVideoComponent extends Component {

  getNicknameTag() {
    // Gets the nickName of the user
    return JSON.parse(this.props.streamManager.stream.connection.data).clientData;
  }

  render() {
    return (
      <>
        {this.props.streamManager !== undefined ? (
          <div className="streamcomponent">
            <p>{this.getNicknameTag()}</p>
            <OpenViduVideoComponent streamManager={this.props.streamManager} />
          </div>
        ) : null}
      </>
    );
  }
}
