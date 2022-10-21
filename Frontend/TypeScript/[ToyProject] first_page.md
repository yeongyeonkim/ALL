토이프로젝트에서 사용할 애플리케이션의 첫 페이지 레이아웃 작성하였다.

```typescript
import * as React from "react";
import CssBaseline from "@mui/material/CssBaseline";
import Link from "@mui/material/Link";
import Grid from "@mui/material/Grid";
import Box from "@mui/material/Box";
import Container from "@mui/material/Container";
import Avatar from "@mui/material/Avatar";
import { createTheme, ThemeProvider } from "@mui/material/styles";

const theme = createTheme();

export default function SignIn() {
  const handleSubmit = (event: React.FormEvent<HTMLFormElement>) => {
    event.preventDefault();
    const data = new FormData(event.currentTarget);
    console.log({
      email: data.get("email"),
      password: data.get("password")
    });
  };

  return (
    <ThemeProvider theme={theme}>
      <Container component="main" maxWidth="xs" sx={{ bgcolor: "#cfe8fc" }}>
        <CssBaseline />
        <Box
          sx={{
            marginTop: 8,
            display: "flex",
            flexDirection: "column",
            backgroundColor: "000000",
            height: (theme) => theme.spacing(15)
          }}
        >
          <Avatar
            alt="Remy Sharp"
            src="../../public/image/Typescript_logo.png"
            sx={{ width: "1/2" }}
          />{" "}
          asd
        </Box>
        <Box
          sx={{
            marginTop: 8,
            display: "flex",
            flexDirection: "column",
            alignItems: "center",
            backgroundColor: "white"
          }}
        >
          <Box
            component="form"
            onSubmit={handleSubmit}
            noValidate
            sx={{ mt: 10 }}
          >
            <Grid container spacing={2}>
              <Grid item xs={5} sx={{ backgroundColor: "#C0C0C0" }}>
                <Link href="#" variant="body2" underline="none" color="black">
                  게스트
                </Link>
              </Grid>
              <Grid item xs={2} sx={{ backgroundColor: "green" }}></Grid>
              <Grid item xs={5} sx={{ backgroundColor: "#C0C0C0" }}>
                <Link href="#" variant="body2" underline="none" color="black">
                  로그인
                </Link>
              </Grid>
              <Grid item xs={4}></Grid>
              <Grid item xs={4} sx={{ backgroundColor: "333333" }}>
                <Link href="#" variant="body2" color="#000000">
                  가입하기
                </Link>
              </Grid>
              <Grid item xs={4}></Grid>
            </Grid>
          </Box>
        </Box>
      </Container>
    </ThemeProvider>
  );
}


```

* flexDirection
  * display 값을 flex 혹은 inline-flex로 지정한다.

```
ABC 아이템이 있다고 생각 0은 여백
1. row
ABCOOO

2. row-reverse
000ABC

3. column
A
B
C

4. column-reverse
C
B
A
```

