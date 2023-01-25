import React from "react";
import Modal from "./Modal";
// import styled from "styled-components";
import image from "../../assets/cat.png";

function FirstModal({ onClose }) {
  return (
    <Modal onClose={onClose}>
      <img src={image} alt="smile" />
      <h1>This is a Modal Dialog</h1>
      <button onClick={onClose}>Close</button>
    </Modal>
  );
}

export default FirstModal;