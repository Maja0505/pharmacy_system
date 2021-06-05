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
              <Link
                to="/patient/profile"
                style={{ color: "#fafafa", textDecoration: "none" }}
              >
                Profile
              </Link>
            </Typography>
          </Grid>
          <Grid item xs={3}>
            <Typography>
              <Link
                to="/patient/home2"
                style={{ color: "#fafafa", textDecoration: "none" }}
              >
                Home Page
              </Link>
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

    const NavBarForUnregistred = (
    <Toolbar>
      <Grid container spacing={1}>
        <Grid item xs={6}>
          <Typography>Hay {user}</Typography>
        </Grid>
        <Grid item xs={6} container spacing={1} style={{ textAlign: "right" }}>
          <Grid item xs={3}></Grid>
          <Grid item xs={3}>
            <Typography>
              <Link
                to="/"
                style={{ color: "#fafafa", textDecoration: "none" }}
              >
                Home 
              </Link>
            </Typography>
          </Grid>
          <Grid item xs={3}>
            <Typography>
              <Link
                to="/login"
                style={{ color: "#fafafa", textDecoration: "none" }}
              >
                Sign in
              </Link>
            </Typography>
          </Grid>
          <Grid item xs={3}>
            <Typography>
              <Link to="/registerPatient" style={{ color: "#fafafa", textDecoration: "none" }}>
               Sign up
              </Link>
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
        {(user === "unregistred" || user === undefined) && NavBarForUnregistred}
      </AppBar>
    </>
  );
};

export default NavBar;
