import * as React from 'react';
import ToggleButtonGroup from '@mui/material/ToggleButtonGroup';
import ToggleButton from '@mui/material/ToggleButton';
import { createTheme } from '@mui/material/styles';

export default function TodoToggle() {
  const [alignment, setAlignment] = React.useState('web');

  const handleChange = (event, newAlignment) => {
    setAlignment(newAlignment);
  };

  return (
    <ToggleButtonGroup
      theme={theme}
      color="primary"
      value={alignment}
      exclusive
      onChange={handleChange}
      aria-label="Platform"
      sx={{
        width: '100%',
        marginY: '30px'
      }}
    >
      <ToggleButton value="todo" theme={theme} sx={{width: '80px', fontSize: '18px'}}>할 일</ToggleButton>
      <ToggleButton value="schedule" theme={theme} sx={{width: '80px', fontSize: '18px'}}>일정</ToggleButton>
    </ToggleButtonGroup>
  );
}

const theme = createTheme({
  palette: {
    primary: {
      light: '#ffffff',
      main: '#fffdf6',
      dark: '#fffdf6',
      contrastText: '#ffffff',
    },
    secondary: {
      light: '#ffffff',
      main: '#ffffff',
      dark: '#ffffff',
      contrastText: '#ffffff',
    },
  },
});