import { useState } from "react";
import { Box } from "@mui/system";

// nav
export function NavItem(props) {
  const [open, setOpen] = useState(false);
  return (
    <div>
      <Box
        onClick={() => setOpen(!open)}
        sx={{
          display: 'flex',
          alignItems: 'center',
          justifyContent: 'center',
          width: "250px",
          height: "80px",
          marginTop: "20px",
          borderRadius: "20px",
          backgroundColor: "#FFFFFF",
          boxShadow: "5px 5px 8px rgba(0, 0, 0, 0.35)",
          ':hover': {
            cursor: 'pointer'
          }
      }}>
        <h2>React 공부방</h2>
      </Box>
      {open && props.children}
    </div>
  );
}
