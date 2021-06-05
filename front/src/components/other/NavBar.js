import { AppBar, Typography, Toolbar, Grid } from "@material-ui/core";
import { Link } from "react-router-dom";

const clearLocalStorage = () => {
  localStorage.clear();
};

const NavBar = ({ user }) => {
  const NavBarForPharmacist = (
    <Toolbar>
      <Grid container>
        <Grid item xs={6}></Grid>
        <Grid item xs={6} container style={{ textAlign: "right" }}>
          <Grid item xs={2} />
          <Grid item xs={2} />
          <Grid item xs={2}>
            <Typography>
              <Link
                to="/pharmacist/homePage"
                style={{ color: "#fafafa", textDecoration: "none" }}
              >
                Home page
              </Link>
            </Typography>
          </Grid>
          <Grid item xs={2}>
            <Typography>
              <Link
                to="/pharmacist/editProfile"
                style={{ color: "#fafafa", textDecoration: "none" }}
              >
                Profile
              </Link>
            </Typography>
          </Grid>
          <Grid item xs={2}>
            <Typography>
              <a
                href="/"
                style={{ color: "#fafafa", textDecoration: "none" }}
                onClick={clearLocalStorage}
              >
                Sing out
              </a>
            </Typography>
          </Grid>
        </Grid>
      </Grid>
    </Toolbar>
  );

  const NavBarForDermatologist = (
    <Toolbar>
      <Grid container>
        <Grid item xs={6}></Grid>
        <Grid item xs={6} container style={{ textAlign: "right" }}>
          <Grid item xs={2} />
          <Grid item xs={2} />
          <Grid item xs={2}>
            <Typography>
              <Link
                to="/dermatologist/homePage"
                style={{ color: "#fafafa", textDecoration: "none" }}
              >
                Home page
              </Link>
            </Typography>
          </Grid>
          <Grid item xs={2}>
            <Typography>
              <Link
                to="/dermatologist/editProfile"
                style={{ color: "#fafafa", textDecoration: "none" }}
              >
                Profile
              </Link>
            </Typography>
          </Grid>
          <Grid item xs={2}>
            <Typography>
              <a
                href="/"
                style={{ color: "#fafafa", textDecoration: "none" }}
                onClick={clearLocalStorage}
              >
                Sing out
              </a>
            </Typography>
          </Grid>
        </Grid>
      </Grid>
    </Toolbar>
  );

  const NavBarForPatient = (
    <Toolbar>
      <Grid container spacing={1}>
        <Grid item xs={6}>
          <Typography>Hay {user}</Typography>
        </Grid>
        <Grid item xs={6} container spacing={1} style={{ textAlign: "right" }}>
          <Grid item xs={3}></Grid>
          <Grid item xs={3}>
            <Typography>
              <a
                href="/patient/Profile"
                style={{ color: "#fafafa", textDecoration: "none" }}
              >
                Profile
              </a>
            </Typography>
          </Grid>
          <Grid item xs={3}>
            <Typography>
              <a
                href="/patient/HomePage"
                style={{ color: "#fafafa", textDecoration: "none" }}
              >
                Home Page
              </a>
            </Typography>
          </Grid>
          <Grid item xs={3}>
            <Typography>
              <a
                href="/"
                style={{ color: "#fafafa", textDecoration: "none" }}
                onClick={clearLocalStorage}
              >
                Sing out
              </a>
            </Typography>
          </Grid>
        </Grid>
      </Grid>
    </Toolbar>
  );

  return (
    <>
      <AppBar position="static">
        {user === "pharmacist" && NavBarForPharmacist}
        {user === "dermatologist" && NavBarForDermatologist}
        {user === "patient" && NavBarForPatient}
      </AppBar>
    </>
  );
};

export default NavBar;
