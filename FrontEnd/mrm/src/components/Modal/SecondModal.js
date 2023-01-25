import React from "react";
import Modal from "./Modal";
// import styled from "styled-components";

function SecondModal({ onClose }) {
  return (
    <Modal onClose={onClose}>
        <h2> Hello !</h2>
        <p>
          ...
        </p>
    </Modal>
  );
}

export default SecondModal;