import { AppBar, Typography, Toolbar, Grid } from "@material-ui/core";
import { Link } from "react-router-dom";

const NavBar = ({ user }) => {
  const NavBarForPharmacist = (
    <Toolbar>
      <Grid container spacing={1}>
        <Grid item xs={3}>
          <Typography>Hay {user}</Typography>
        </Grid>
        <Grid item xs={3}></Grid>
        <Grid item xs={6} container spacing={1} style={{ textAlign: "right" }}>
          <Grid item xs={2} />
          <Grid item xs={2}>
            <Typography>
              <Link
                to="/pharmacist/examinedPatients"
                style={{ color: "#fafafa", textDecoration: "none" }}
              >
                Examined patients
              </Link>
            </Typography>
          </Grid>
          <Grid item xs={2}>
            <Typography>
              <Link
                to="/pharmacist/workCalendar"
                style={{ color: "#fafafa", textDecoration: "none" }}
              >
                Work calendar
              </Link>
            </Typography>
          </Grid>
          <Grid item xs={2}>
            <Typography>
              <Link
                to="/pharmacist/vacationRequest"
                style={{ color: "#fafafa", textDecoration: "none" }}
              >
                Vacation Request
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
        <Grid item xs={3}>
          <Typography>Hay {user}</Typography>
        </Grid>
        <Grid item xs={3}></Grid>
        <Grid item xs={6} container spacing={1} style={{ textAlign: "right" }}>
          <Grid item xs={2} />
          <Grid item xs={2}>
            <Typography>
              <Link
                to="/dermatologist/examinedPatients"
                style={{ color: "#fafafa", textDecoration: "none" }}
              >
                Examined patients
              </Link>
            </Typography>
          </Grid>
          <Grid item xs={2}>
            <Typography>
              <Link
                to="/dermatologist/workCalendar"
                style={{ color: "#fafafa", textDecoration: "none" }}
              >
                Work calendar
              </Link>
            </Typography>
          </Grid>
          <Grid item xs={2}>
            <Typography>
              <Link
                to="/dermatologist/vacationRequest"
                style={{ color: "#fafafa", textDecoration: "none" }}
              >
                Vacation request
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
