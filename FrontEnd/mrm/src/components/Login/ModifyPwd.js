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

import { modifyPwd } from './FindPwdLogic';

const FormHelperTexts = styled(FormHelperText)`
width: 100%;
padding-left: 16px;
font-weight: 700 !important;
color: #d32f2f !important;
`;

const theme = createTheme();

export default function ModifyPwdPage() {

  const [pwdError, setPwdError] = useState('');

  const handleSubmit = (event) => {
    event.preventDefault();
    const data = new FormData(event.currentTarget);
    const modifyPwdData = {
        AfterPassword: data.get('AfterPassword'),
        CheckAfterPassword: data.get('CheckAfterPassword'),
    };
    const {AfterPassword, CheckAfterPassword} = modifyPwdData;

    // 유효성 체크
    if (AfterPassword === CheckAfterPassword) {
        setPwdError('');
    } else {
        setPwdError('재입력한 비밀번호가 동일하지 않습니다');
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

            <h1>비밀번호 변경</h1>
            <Box component="form" noValidate onSubmit={handleSubmit} sx={{ mt: 3 }}>
              <Grid container spacing={2} justifyContent='center'>
                <Grid item xs={8}>
                  <TextField
                    type="password"
                    margin='normal'
                    name="AfterPassword"
                    required
                    fullWidth
                    id="AfterPassword"
                    label="변경할 비밀번호를 입력하세요"
                    autoFocus
                    sx={{
                      bgcolor: '#FFFFFF'
                    }}
                  />
                </Grid>  
                <Grid item xs={8}>
                  <TextField
                    type="password"
                    margin='normal'
                    required
                    fullWidth
                    id="CheckAfterPassword"
                    label="변경할 비밀번호를 재입력하세요"
                    name="CheckAfterPassword"
                    error={pwdError !== '' || false}
                    sx={{
                      bgcolor: '#FFFFFF'
                    }}
                  />
                </Grid>  
                <FormHelperTexts>{pwdError}</FormHelperTexts>
                <Grid item xs={8}>
                  <Button
                    onClick={modifyPwd}
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
                    비밀번호 변경하기
                  </Button>
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
                                    취소
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
