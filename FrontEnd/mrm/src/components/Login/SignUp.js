import * as React from 'react';
import {useState} from 'react';

import './Login.css';

import Button from '@mui/material/Button';
import CssBaseline from '@mui/material/CssBaseline';
import TextField from '@mui/material/TextField';
import Link from '@mui/material/Link';
import Grid from '@mui/material/Grid';
import { Paper } from '@mui/material';
import Box from '@mui/material/Box';

import { createTheme, ThemeProvider } from '@mui/material/styles';
import styled from 'styled-components';
import FormHelperText from '@mui/material/FormHelperText';

import { postSignUpData } from './SignUpLogic';
// import SignInSide from './SignInSide'
// import axios from 'axios';
// import { useNavigate } from 'react-router-dom';

// function Copyright(props) {
//   return (
//     <Typography variant="body2" color="text.secondary" align="center" {...props}>
//       {'Copyright © '}
//       <Link color="inherit" href="https://mui.com/">
//         Your Website
//       </Link>{' '}
//       {new Date().getFullYear()}
//       {'.'}
//     </Typography>
//   );
// }

const FormHelperTexts = styled(FormHelperText)`
width: 100%;
padding-left: 16px;
font-weight: 700 !important;
color: #d32f2f !important;
`;


const theme = createTheme();

export default function SignUp() {

  const [emailError, setEmailError] = useState('');
  const [passwordState, setPasswordState] = useState('');
  const [passwordError, setPasswordError] = useState('');
  const [nameError, setNameError] = useState('');
  const [idError, setIdError] = useState('');
  const [nicknameError, setNicknameError] = useState('');
  // const [signUPError, setSignUpError] = useState('')
  // const navigate = useNavigate();

  // 데이터 보내기...
  // const onhandlePost = async (data) => {
  //   const {name, id, email, password} = data;
  //   const postData = {name, id, email, password};

  //   await axios
  //     .post('/member/join', postData)
  //     .then(function(response) {
  //       console.log(response, '성공');
  //       navigate.pushState('/login');
  //     })
  //     .catch(function(err) {
  //       console.log(err);
  //       setSignUpError('회원가입 실패')
  //     });

  // }

  const handleSubmit = (event) => {
    event.preventDefault();
    const data = new FormData(event.currentTarget);
    const joinData = {
      email: data.get('email'),
      name: data.get('name'),
      id: data.get('id'),
      nickname: data.get('nickname'),
      password: data.get('password'),
      rePassword: data.get('rePassword'),

    };
    const {email, name, id, nickname,  password, rePassword} = joinData;

// 유효성 체크
    const emailRegex = /([\w-.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;
    if (!emailRegex.test(email)) {
      setEmailError('올바른 이메일 형식이 아닙니다.');
    } else {
      setEmailError('');
    }
// 비밀번호 : 영어+숫자 8자이상
    const passwordRegex = /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,}$/;
    if (!passwordRegex.test(password)) {
      setPasswordState('영문자+숫자 조합으로 8자리 이상 입력해주세요')
    } else {
      setPasswordState('');
    }

    if (password !== rePassword) {
      setPasswordError('비밀번호가 일치하지 않습니다.');
    } else {
      setPasswordError('');
    }

    const nameRegex = /^[가-힣a-zA-Z]+$/;
    if (!nameRegex.test(name) || name.length < 1) {
      setNameError('올바른 이름을 입력해 주세요.')
    } else {
      setNameError('');
    }

// 아이디 : 6-20자 영어+숫자
    const idRegex = /^[a-z]+[a-z0-9]{5,19}$/g;
    if (!idRegex.test(id) || id.length < 1) {
      setIdError('올바른 아이디를 입력해 주세요.')
    } else {
      setIdError('');
    }

// 닉네임- 2자 이상 16자 이하, 영어 또는 숫자 또는 한글로 구성

// * 특이사항 : 한글 초성 및 모음은 허가하지 않는다.
    const nicknameRegex = /^(?=.*[a-z0-9가-힣])[a-z0-9가-힣]{2,16}$/g;
    if (!nicknameRegex.test(nickname) || nickname.length < 1) {
      setNicknameError('올바른 닉네임을 입력해 주세요.')
    } else {
      setNicknameError('');
    }

    // if (
    //   emailRegex.test(email) &&
    //   passwordRegex.test(password) &&
    //   password === rePassword &&
    //   nameRegex.test(name) &&
    //   idRegex.test(id)
    // ) {
    //   onhandlePost(joinData);
    // }
  };

  return (
    <ThemeProvider theme={theme}>
      <Grid container component="main" sx={{height: '100vh'}}>
        <CssBaseline />
        <Grid
          item
          xs={false}
          sm={4}
          md={7}
          // sx={{
          //   backgroundImage: 'url(https://source.unsplash.com/random)',
          //   backgroundRepeat: 'no-repeat',
          //   backgroundColor: (t) =>
          //     t.palette.mode === 'light' ? t.palette.grey[50] : t.palette.grey[900],
          //   backgroundSize: 'cover',
          //   backgroundPosition: 'center',
          // }}
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
        
        <Grid item xs={12} sm={8} md={5} component={Paper} elevation={6} backgroundColor='#FAF6E9' square>

          <Box
            sx={{
              my: 8,
              mx: 4,
              display: 'flex',
              flexDirection: 'column',
              alignItems: 'center',
              justifyContent: 'center'
            }}
          >

            <h1>회원가입</h1>
            <Box component="form" noValidate onSubmit={handleSubmit} sx={{ mt: 3 }}>
              <Grid container spacing={2} justifyContent='center'>
                <Grid item xs={8}>
                  <TextField
                    margin='normal'
                    autoComplete="given-name"
                    name="name"
                    required
                    fullWidth
                    id="name"
                    label="이름"
                    autoFocus
                    error={nameError !== '' || false}
                    sx={{
                      bgcolor: '#FFFFFF'
                    }}
                  />
                </Grid>  
                <FormHelperTexts>{nameError}</FormHelperTexts>
                <Grid item xs={8}>
                  <TextField
                    margin='normal'
                    autoComplete="아이디"
                    name="id"
                    required
                    fullWidth
                    id="id"
                    label="아이디"
                    error={idError !== '' || false}
                    sx={{
                      bgcolor: '#FFFFFF'
                    }}
                  />
                  {/* 닉네임 넣기 */}
                </Grid>  
                <FormHelperTexts>{idError}</FormHelperTexts>
                <Grid item xs={8}>
                  <TextField
                    margin='normal'
                    autoComplete="닉네임"
                    name="nickname"
                    required
                    fullWidth
                    id="nickname"
                    label="닉네임"
                    
                    error={nicknameError !== '' || false}
                    sx={{
                      bgcolor: '#FFFFFF'
                    }}
                  />
                  {/* 닉네임 넣기 */}
                </Grid>  
                <FormHelperTexts>{idError}</FormHelperTexts>
                <Grid item xs={8}>
                  <TextField
                    margin='normal'
                    required
                    fullWidth
                    id="email"
                    label="Email Address"
                    name="email"
                    autoComplete="이메일 주소"
                    error={emailError !== '' || false}
                    sx={{
                      bgcolor: '#FFFFFF'
                    }}
                  />
                </Grid>  
                <FormHelperTexts>{emailError}</FormHelperTexts>
                <Grid item xs={8}>
                  <TextField
                    margin='normal'
                    required
                    fullWidth
                    name="password"
                    label="비밀번호 (영문자+숫자 조합으로 8자리 이상)"
                    type="password"
                    id="password"
                    autoComplete="new-password"
                    error={passwordState !== '' || false}
                    sx={{
                      bgcolor: '#FFFFFF'
                    }}
                  />
                </Grid>
                <FormHelperTexts>{passwordState}</FormHelperTexts>
                <Grid item xs={8}>
                  <TextField
                    margin='normal'
                    required
                    fullWidth
                    name="rePassword"
                    label="비밀번호 확인"
                    type="password"
                    id="rePassword"
                    autoComplete="new-password"
                    error={passwordError !== '' || false}
                    sx={{
                      bgcolor: '#FFFFFF'
                    }}
                  />
                </Grid>
                <FormHelperTexts>{passwordError}</FormHelperTexts>
                <Grid item xs={8}>
                  <Button
                    onClick={postSignUpData}
                    type="submit"
                    fullWidth
                    variant="contained"
                    sx={{ 
                      mt: 3, 
                      mb: 2, 
                      bgcolor: '#FFFFFF', 
                      color: '#000000', 
                      fontFamily: "GangwonEdu_OTFBoldA", 
                      boxShadow: "5px 5px 4px rgba(0, 0, 0, 0.15)" 
                    }}
                  >
                    회원가입
                  </Button>
                  {/* <FormHelperTexts>{signUPError}</FormHelperTexts> */}
                  <Grid container justifyContent="flex-end">
                    <Grid item>
                      <Link href="./" variant="body2" underline='hover'>
                        이미 가입하셨다면 로그인하세요
                      </Link>
                    </Grid>
                  </Grid>
                </Grid>  
              </Grid> 
            </Box>
          </Box>
          </Grid>
        {/* <Copyright sx={{ mt: 5 }} /> */}
      </Grid>
    </ThemeProvider>
  );
}
