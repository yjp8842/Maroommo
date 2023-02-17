import * as React from 'react';
import { useState } from 'react';
import { requestLogin } from './SignInLogic';
import { Link, useNavigate } from 'react-router-dom';

import { useDispatch, useSelector } from "react-redux";

import './Login.css';

import { FormControl, FormHelperText, Button, CssBaseline, TextField, Paper, Box, Grid, Typography, Modal } from '@mui/material';
import { createTheme, ThemeProvider } from '@mui/material/styles';

const theme = createTheme();

export default function SignInSide() {
  const navigate = useNavigate();
  localStorage.clear();

  const handleSubmit = (event) => {
    event.preventDefault();
    const data = new FormData(event.currentTarget);
    console.log({
      id: data.get('id'),
      password: data.get('password'),
    });
  };

  const activeButton = () => {
    requestLogin(dispatch, navigate);
    loginCheck();
  }

  const activeEnter = (e) => {
    if(e.key === "Enter") {
      activeButton();
    }
  }
  // id와 password가 빈칸인지 체크하는 함수
  const [idError, setIdError] = useState('');
  const [passwordError, setPasswordError] = useState('');

  const dispatch = useDispatch();
  
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
            {/* <Link to='/myroom'>마이룸</Link> */}
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
                      onKeyDown={(e) => activeEnter(e)}
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
                      onKeyDown={(e) => activeEnter(e)}
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
                      activeButton();
                    }}
                    >
                    로그인
                  </Button>

                </Box>
                <Grid container sx={{mt: 5, display: 'flex', justifyContent: 'center'}}>
                  <Grid item xs={3} sx={{textAlign: 'center'}}>
                    <Link href="#" underline='hover' variant="body2" to="/findId">
                      아이디 찾기
                    </Link>
                  </Grid>
                  <Grid item xs={3} sx={{textAlign: 'center'}}>
                    <Link href="#" underline='hover' variant="body2"to="/findPwd">
                      비밀번호 찾기
                    </Link>
                  </Grid>
                  <Grid item xs={3} sx={{textAlign: 'center'}}>
                    <Link href='#' underline='hover' varient='body2' to="/signup">
                      회원가입
                    </Link>
                  </Grid>
                </Grid>
                
                <Grid container sx={{mt: 4, display: 'flex', justifyContent: 'center'}}>
                  
                    {/* <Button> */}
                      <a href='https://i8a406.p.ssafy.io/api/oauth2/authorization/google'>
                        <img className="googleLogin" 
                            alt="google_login"                       
                            src="images/google_login.png"
                            // height="80px"
                            width="340px" 
                            />
                      </a>
                    {/* </Button> */}
                </Grid>
                    
                <Grid container sx={{ display: 'flex', justifyContent: 'center'}}>
                    {/* <Button> */}
                      <a href='https://i8a406.p.ssafy.io/api/oauth2/authorization/kakao'>
                        <img className="kakaoLogin" 
                            alt="kakao_login"                       
                            src="images/kakao_login.png"
                            // height="80px"
                            width="340px" 
                            />
                      </a>
                    {/* </Button> */}
                </Grid>
                
                <Grid container sx={{ display: 'flex', justifyContent: 'center'}}>
                    {/* <Button> */}
                      <a href='https://i8a406.p.ssafy.io/api/oauth2/authorization/naver'>
                        <img className="naverLogin" 
                            alt="naver_login"                       
                            src="images/naver_login.png"
                            // height="80px"
                            width="340px" 
                            />
                      </a>
                    {/* </Button> */}
                </Grid>


              </FormControl>
            </Box>
          </Box>
        </Grid>
      </Grid>
    </ThemeProvider>
  );
}