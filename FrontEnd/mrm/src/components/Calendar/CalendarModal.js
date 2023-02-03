import React from "react";

import CalendarApp from "./CalendarApp";
// import Button from '@mui/material/Button';
// import Typography from '@mui/material/Typography';
import Modal from '@mui/material/Modal';
import CalendarMini from "./CalendarMini";

import './CalendarModal.css';

export default function CalendarModal() {

  const [open, setOpen] = React.useState(false);
  const handleOpen = () => setOpen(true);
  const handleClose = () => setOpen(false);

  return (
    <div>
      {/* <Button onClick={handleOpen}>달력 </Button> */}
      <CalendarMini />
      <div className="click-div" onClick={handleOpen}></div>
      <Modal
        open={open}
        onClose={handleClose}
        aria-labelledby="modal-modal-title"
        aria-describedby="modal-modal-description"
      >
        <CalendarApp/>
      </Modal>
    </div>
  )
}