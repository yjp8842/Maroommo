import * as React from 'react';
import { useState } from 'react';
import { requestLogin } from './SignInLogic';
import { Link } from 'react-router-dom';
import { useDispatch, useSelector } from "react-redux";

import './Login.css';
import { FindId } from './FindId';
import { FindPwd } from './FindPwd';

import { FormControl, FormHelperText, Button, CssBaseline, TextField, Paper, Box, Grid, Typography, Modal } from '@mui/material';
import { createTheme, ThemeProvider } from '@mui/material/styles';

const theme = createTheme();

export default function SignInSide() {
  const handleSubmit = (event) => {
    event.preventDefault();
    const data = new FormData(event.currentTarget);
    console.log({
      id: data.get('id'),
      password: data.get('password'),
    });
  };

  const {id, email, myRooms} = useSelector((state) =>({id:state.userInfoReducers.user.id, email:state.userInfoReducers.user.email, myRooms:state.userInfoReducers.user.myRooms}) )
  console.log('@@@@', id, email, myRooms)
  // id와 password가 빈칸인지 체크하는 함수
  const [idError, setIdError] = useState('');
  const [passwordError, setPasswordError] = useState('');
  const [verificationError, setVerificationError] = useState('')

  const dispatch = useDispatch()

  function loginCheck(e) {
    const idCheck = document.getElementById('id')
    const passwordCheck = document.getElementById('password')
    if (!idCheck.value) {
      setIdError('아이디를 입력해주세요');
      idCheck.focus();
      e.preventDefault();
      return;
    } else {
      setIdError('');
    }

    if (!passwordCheck.value) {
      setPasswordError('비밀번호를 입력해주세요');
      passwordCheck.focus();
      e.preventDefault();
      return;
    } else {
      setPasswordError('');
    }
  }
    
  function findPwd(e) {
    const verificationCheck = document.getElementById('verificaition_number')

    if (!verificationCheck.value) {
      setVerificationError('인증번호를 입력해주세요');
      verificationCheck.focus();
      e.preventDefault();
      return;
    } else {
      setVerificationError('');
    }
  }


  // 아이디, 비번 찾기 모달 띄움
  const [idopen, idSetOpen] = React.useState(false);
  const [pwdopen, pwdSetOpen] = React.useState(false);
  const idHandleOpen = () => idSetOpen(true);
  const idHandleClose = () => idSetOpen(false);
  const passwordHandleOpen = () => pwdSetOpen(true);
  const passwordHandleClose = () => pwdSetOpen(false);

  return (
    <ThemeProvider theme={theme}>
      <Grid container component="main" sx={{ height: '100vh', fontFamily: "GangwonEdu_OTFBoldA" }}>
        <CssBaseline />
        <Grid
          item
          xs={false}
          sm={4}
          md={7}
          sx={{
            display: 'flex', 
            flexDirection: 'column', 
            // alignItems: 'center', 
            justifyContent: 'center', 
            bgcolor: '#FAF6E9',
          }}
        > 
          <div className='inbox'>
            <img src='images/logomrm.png' alt='logo' className='imgbox' />
            <div className='fontbox'>
              <h1 className='main-font'>마룸모</h1>
              <h1>마이 룸에서 하는 스터디 모임</h1>
            </div>
          </div>
        </Grid>
        <Grid item xs={12} sm={8} md={5} component={Paper} elevation={6} square sx={{ display: 'flex', flexDirection: 'column', justifyContent: 'center', alignItems: 'center', bgcolor: '#FAF6E9' }}>
          <Box
            sx={{
              my: 8,
              mx: 4,
              display: 'flex',
              flexDirection: 'column',
              alignItems: 'center',
              justifyContent: 'center',
              // bgcolor: '#FFFFFF',
              // height: '400px',
              // width: '550px',
              // borderRadius :'50px',
              // boxShadow: "5px 5px 4px rgba(0, 0, 0, 0.25)"
            }}
          >
            
            <h1>로그인</h1>
            <Link to='/myroom'>마이룸</Link>
            <Box noValidate onSubmit={handleSubmit} sx={{ mt: 3, width: "30vw" }}>
              <FormControl component="fieldset" variant='standard' sx={{width: "30vw"}}>
                <Box spacing={2} sx={{display: "flex", justifyContent: "center"}}>
                  <Box container sx={{display: 'flex', flexDirection: 'column', width: "15vw"}}>
                    <TextField
                      margin="normal"
                      required
                      id="id"
                      label="아이디"
                      name="id"
                      autoComplete="id"
                      autoFocus
                      sx={{
                        bgcolor: '#FFFFFF',
                      }}
                    />
                    <FormHelperText>{idError}</FormHelperText>
                    <TextField
                      margin="normal"
                      required
                      // fullWidth
                      name="password"
                      label="비밀번호"
                      type="password"
                      id="password"
                      autoComplete="current-password"
                      sx={{
                        bgcolor: '#FFFFFF',

                      }} 
                    />
                    <FormHelperText>{passwordError}</FormHelperText>

                  </Box>

                  <Button
                    type="submit"
                    sx={{ 
                      mt: 2,
                      mb: 2,
                      ml: 2,
                      backgroundColor: '#FFFFFF', 
                      color: '#000000', 
                      fontFamily: "GangwonEdu_OTFBoldA",
                      boxShadow: "5px 5px 4px rgba(0, 0, 0, 0.15)"
                    }}
                    onClick={() => {
                      requestLogin(dispatch);
                      loginCheck();
                    }}
                    >
                    로그인
                  </Button>

                </Box>
                <Grid container sx={{mt: 5, display: 'flex', justifyContent: 'center'}}>
                  <Grid item xs={3} sx={{textAlign: 'center'}}>
                    <Link href="#" underline='hover' variant="body2" onClick={idHandleOpen}>
                      아이디 찾기
                    </Link>
                    <Modal
                      open={idopen}
                      onClose={idHandleClose}
                      aria-labelledby="modal-modal-title"
                      aria-describedby="modal-modal-description"
                    >
                      <div className="style1">
                        <Typography 
                          id="modal-modal-title"
                          sx={{
                            textAlign: "center",
                            fontFamily: 'GangwonEdu_OTFBoldA',
                            fontSize: "30px",
                            color: '#FFFFFF'
                          }}
                        >
                          아이디 찾기
                        </Typography>
                        <TextField
                          required
                          id="name-id"
                          placeholder='이름'
                          sx={{
                            marginTop: "20px",
                            width: "80%",
                            borderRadius: "10px",
                            bgcolor: '#FFFFFF'
                          }}
                        />
                        <TextField
                          required
                          id="email-id"
                          placeholder='이메일 주소'
                          sx={{
                            marginTop: "20px",
                            width: "80%",
                            borderRadius: "10px",
                            bgcolor: '#FFFFFF'
                          }}
                        />
                        <Button
                          onClick={FindId}
                          type="submit"
                          sx={{
                            width: "80px",
                            height: "50px",
                            marginTop: "30px",
                            bgcolor: '#FFFFFF',
                            color: "#000000",
                            fontSize: "15px",
                            boxShadow: "5px 5px 8px rgba(0, 0, 0, 0.35)",
                            // border: "1px solid #000000",
                            borderRadius: "15px",
                            ":hover": {
                              bgcolor: 'c4c4c4'
                            }
                          }}
                        >
                          <p>확인</p>
                        </Button>
                      </div>
                    </Modal>
                  </Grid>
                  <Grid item xs={3} sx={{textAlign: 'center'}}>
                    <Link href="#" underline='hover' variant="body2" onClick={passwordHandleOpen}>
                      비밀번호 찾기
                    </Link>
                    <Modal
                      open={pwdopen}
                      onClose={passwordHandleClose}
                      aria-labelledby="modal-modal-title"
                      aria-describedby="modal-modal-description"
                    >
                      <div className="style2">
                        <Typography 
                          id="modal-modal-title"
                          sx={{
                            textAlign: "center",
                            fontFamily: 'GangwonEdu_OTFBoldA',
                            fontSize: "30px",
                            color: '#FFFFFF'
                          }}
                        >
                          비밀번호 찾기
                        </Typography>
                        <TextField
                          required
                          id="name-pwd"
                          placeholder='이름'
                          sx={{
                            marginTop: "20px",
                            width: "80%",
                            border: "1px solid #000000",
                            borderRadius: "10px",
                            bgcolor: '#FFFFFF'
                          }}
                        />
                        <TextField
                          required
                          id="id-pwd"
                          placeholder='아이디'
                          sx={{
                            marginTop: "20px",
                            width: "80%",
                            border: "1px solid #000000",
                            borderRadius: "10px",
                            bgcolor: '#FFFFFF'
                          }}
                        />
                        <TextField
                          required
                          id="email-pwd"
                          placeholder='이메일 주소'
                          sx={{
                            marginTop: "20px",
                            width: "80%",
                            border: "1px solid #000000",
                            borderRadius: "10px",
                            bgcolor: '#FFFFFF'
                          }}
                        />
                        <Button
                          onClick={FindPwd}
                          type="submit"
                          sx={{
                            width: "80px",
                            height: "50px",
                            marginTop: "20px",
                            color: "#000000",
                            fontSize: "15px",
                            boxShadow: "5px 5px 8px rgba(0, 0, 0, 0.35)",
                            // border: "1px solid #000000",
                            borderRadius: "15px",
                            bgcolor: '#FFFFFF'
                          }}
                        >확인</Button>
                      </div>
                    </Modal>
                  </Grid>
                  <Grid item xs={3} sx={{textAlign: 'center'}}>
                    <Link href='#' underline='hover' varient='body2' to="/signup">
                      회원가입
                    </Link>
                  </Grid>
                </Grid>
              </FormControl>
            </Box>
          </Box>
        </Grid>
      </Grid>
    </ThemeProvider>
  );
}