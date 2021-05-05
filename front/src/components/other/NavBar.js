import { AppBar, Typography, Toolbar, Grid } from "@material-ui/core";
import { Link } from "react-router-dom";

const NavBar = ({ user }) => {
  const NavBarForPharmacist = (
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
                href="/pharmacist/vacationRequest"
                style={{ color: "#fafafa", textDecoration: "none" }}
              >
                Vacation Request
              </a>
            </Typography>
          </Grid>
          <Grid item xs={3}>
            <Typography>
              <a
                href="/pharmacist/editProfile"
                style={{ color: "#fafafa", textDecoration: "none" }}
              >
                Profile
              </a>
            </Typography>
          </Grid>
          <Grid item xs={3}>
            <Typography>
              <a href="/" style={{ color: "#fafafa", textDecoration: "none" }}>
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
      <Grid container spacing={1}>
        <Grid item xs={6}>
          <Typography>Hay {user}</Typography>
        </Grid>
        <Grid item xs={6} container spacing={1} style={{ textAlign: "right" }}>
          <Grid item xs={3}></Grid>
          <Grid item xs={3}>
            <Typography>
              <Link
                to="/dermatologist/vacationRequest"
                style={{ color: "#fafafa", textDecoration: "none" }}
              >
                Vacation request
              </Link>
            </Typography>
          </Grid>
          <Grid item xs={3}>
            <Typography>
              <Link
                to="/dermatologist/editProfile"
                style={{ color: "#fafafa", textDecoration: "none" }}
              >
                Profile
              </Link>
            </Typography>
          </Grid>
          <Grid item xs={3}>
            <Typography>
              <a href="/" style={{ color: "#fafafa", textDecoration: "none" }}>
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
      </AppBar>
    </>
  );
};

export default NavBar;
