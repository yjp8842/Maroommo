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

import { FindPwd, SendEmailCode, checkEmailCode } from './FindPwdLogic';

const FormHelperTexts = styled(FormHelperText)`
width: 100%;
padding-left: 16px;
font-weight: 700 !important;
color: #d32f2f !important;
`;

const theme = createTheme();

export default function FindPwdPage() {

  const [emailError, setEmailError] = useState('');
  const [nameError, setNameError] = useState('');
  const [idError, setIdError] = useState('');
  const [codeError, setCodeError] = useState('');
  const [Visible, setVisible] = useState(false);
  const [sendCode, setSendCode] = useState('');
  const [isEmailCode, setIsEmailCode] = useState(false);

  const handleSubmit = (event) => {
    event.preventDefault();
    const data = new FormData(event.currentTarget);
    const findPwdData = {
        id: data.get('id'),
        name: data.get('name'),
        email: data.get('email'),
        code: data.get('code')
    };
    const {id, name, email, code} = findPwdData;

// 유효성 체크
    if (sendCode === 'error') {
      setEmailError('이메일 인증 코드 발송에 실패했습니다');
    } else {
      setEmailError('');
    }

    // 아이디 : 6-20자 영어+숫자
    const idRegex = /^[a-z]+[a-z0-9]{5,19}$/g;
    if (!idRegex.test(id) || id.length < 1) {
    setIdError('올바른 아이디를 입력해 주세요.')
    } else {
    setIdError('');
    }
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

            <h1>비밀번호 찾기</h1>
            <Box component="form" noValidate onSubmit={handleSubmit} sx={{ mt: 3 }}>
              <Grid container spacing={2} justifyContent='center'>
                <Grid item xs={8}>
                  <TextField
                    margin='normal'
                    autoComplete="given-id"
                    name="id"
                    required
                    fullWidth
                    id="id"
                    label="아이디"
                    autoFocus
                    error={idError !== '' || false}
                    sx={{
                      bgcolor: '#FFFFFF'
                    }}
                  />
                </Grid>  
                <FormHelperTexts>{idError}</FormHelperTexts>
                <Grid item xs={8}>
                  <TextField
                    margin='normal'
                    autoComplete="given-name"
                    name="name"
                    required
                    fullWidth
                    id="name"
                    label="이름"
                    error={nameError !== '' || false}
                    sx={{
                      bgcolor: '#FFFFFF'
                    }}
                  />
                </Grid>  
                <FormHelperTexts>{nameError}</FormHelperTexts>
                <Grid item xs={6}>
                  <TextField
                    fullWidth
                    margin='normal'
                    required
                    id="email"
                    label="이메일 주소"
                    name="email"
                    autoComplete="이메일 주소"
                    error={emailError !== '' || false}
                    sx={{
                      bgcolor: '#FFFFFF'
                    }}
                  />
                </Grid>                  
                <Grid item xs={2}>
                  <Button
                  onClick={()=>{
                    SendEmailCode(setSendCode)                           
                    setVisible(true)
                    }}
                  fullWidth
                    variant="contained"
                    sx={{ 
                    mt: 3, 
                    mb: 2, 
                    bgcolor: '#FFFFFF', 
                    color: '#000000', 
                    fontFamily: "GangwonEdu_OTFBoldA", 
                    boxShadow: "5px 5px 4px rgba(0, 0, 0, 0.15)" 
                    }}>
                        이메일 전송
                    </Button>
                </Grid>
                <FormHelperTexts>{emailError}</FormHelperTexts>
                {Visible && 
                <Grid item xs={6}>
                  <TextField
                    margin='normal'
                    autoComplete="given-code"
                    name="code"
                    required
                    fullWidth
                    id="code"
                    label="이메일 인증 코드를 입력하세요"
                    error={nameError !== '' || false}
                    sx={{
                      bgcolor: '#FFFFFF'
                    }}
                  />
                </Grid>      
                }           
                {Visible && 
                <Grid item xs={2}>
                  <Button
                    fullWidth
                    onClick={()=>{
                        checkEmailCode(sendCode, setIsEmailCode);
                    }}
                    variant="contained"
                    sx={{ 
                    mt: 3, 
                    mb: 2, 
                    bgcolor: '#FFFFFF', 
                    color: '#000000', 
                    fontFamily: "GangwonEdu_OTFBoldA", 
                    boxShadow: "5px 5px 4px rgba(0, 0, 0, 0.15)" 
                    }}>
                        인증 확인
                    </Button>
                </Grid> 
                }           
                {Visible && 
                <FormHelperTexts>{codeError}</FormHelperTexts>
                }
                <Grid item xs={8}>
                  <Button
                    onClick={()=>{
                        FindPwd(isEmailCode);
                    }}
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
                    비밀번호 찾기
                  </Button>
                  {/* <FormHelperTexts>{signUPError}</FormHelperTexts> */}
                  <Grid container justifyContent="flex-end">
                    <Grid item>
                        <Link href="./" variant="body2" underline='hover'>
                            <Button
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
                                    돌아가기
                            </Button>
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
