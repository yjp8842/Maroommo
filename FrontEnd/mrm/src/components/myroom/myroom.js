// import { Fragment } from 'react';
import React, { useState } from 'react';
import { Grid } from '@mui/material';
import { Box } from '@mui/system';
import HomePage from './myroomitem/homepage';
import Profile from './myroomitem/profile';
import StudyTime from './myroomitem/studytime';
import Todo from './myroomitem/todo';
import CalendarBox from './myroomitem/calendar';
import Memo from './myroomitem/memo';
import TimeTable from './myroomitem/timetable';
import Choice from './myroomitem/choice';
import styled from "styled-components";
import Modal from "../Modal/Modal"
// import classes from '../rooms/myroom.css';
// import mealsImage from '../../assets/meals.jpg';

const MyroomButton = () => {

  const [isOpen, setIsOpen] = useState(false);

  const onClickButton = () => {
    setIsOpen(true);
  };

  return (
    <Grid container>
      <Box
        sx={{
          width: "5vw",
          minHeight: "100vh",
          display: "flex",
          flexDirection: "column",
          alignItems: "center",
          backgroundColor: "#4A4A4A",
        }}>
        <Box>
          <HomePage />
        </Box>
        <Box
          sx={{
            width: "4vw",
            height: "5px",
            backgroundColor: "#FFFFFF",
            borderRadius: "10px"
          }}>
        </Box>
        <Box
          sx={{
            height: "100%",
            display: "flex",
            flexDirection: "column",
            justifyContent: "space-between"
          }}>
          <Box>
            <HomePage />
            <HomePage />
            <HomePage />
          </Box>
          <Box>
            <AppWrap>
              <Button onClick={onClickButton}>+</Button>
              {isOpen && (<Modal
                open={isOpen}
                onClose={() => {
                  setIsOpen(false);
                }}
              />)}
            </AppWrap>
          </Box>
        </Box>
      </Box>
      <Box
        sx={{
          display: "flex",
          flexDirection: "column",
        }}>
        <Box
          sx={{
            width: "95vw",
            height: "25vh",
            display: "flex",
            alignItems: "center",
            // justifyContent: "center",
            backgroundColor: "#ebe5d1",
          }}>
          <Profile />
          <StudyTime />
          <Todo />
          <CalendarBox />
        </Box>
        <Box
          sx={{
            width: "90vw",
            height: "75vh",
            display: "flex",
          }}>
          <Box
            sx={{
              width: "65vw",
              height: "75vh",
              display: "flex",
              flexDirection: "column",
              alignItems: "center",
              justifyContent: "space-evenly",
            }}>
            <Box
              sx={{
                width: "50vw",
                height: "10vh",
                display: "flex",
              }}>
              <Choice />
              <Box
                sx={{
                  width: "5px",
                  height: "5vw",
                  backgroundColor: "#FFFFFF",
                  marginX: "15px",
                  boxShadow: "2px 2px 4px rgba(0, 0, 0, 0.2)",
                  borderRadius: "10px"
                }}>
              </Box>
              <Choice />
              <Choice />
            </Box>
            <Box
              sx={{
                width: "50vw",
                height: "50vh",
                // marginTop: "15px",
                borderRadius: "30px",
                backgroundColor: "#FFFFFF",
                boxShadow: "5px 5px 8px rgba(0, 0, 0, 0.35)"
              }}>
            </Box>
          </Box>
          <Box
            sx={{
              width: "25vw",
              height: "75vh",
              display: "flex",
              flexDirection: "column",
              alignItems: "center",
              justifyContent: "space-evenly"
            }}>
            <Memo />
            <h2>TIME TABLE</h2>
            <TimeTable />
          </Box>
        </Box>
      </Box>
    </Grid>
  );
};

const Button = styled.button`
  font-size: 40px;
  padding: 10px 20px;
  border: none;
  background-color: #ffffff;
  border-radius: 10px;
  color: black;
  font-style: italic;
  font-weight: 200;
  cursor: pointer;
  &:hover {
    background-color: #fac2be;
  }
`;

const AppWrap = styled.div`
  text-align: center;
  margin: 50px auto;
`;

export default MyroomButton;