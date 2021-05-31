import { AppBar, Typography, Toolbar, Grid } from "@material-ui/core";
import { Link } from "react-router-dom";

const setPharmacistPatientReport = () => {
  localStorage.setItem("PatientForPharmacistReport", JSON.stringify(null));
};

const setDermatologistPatientReport = () => {
  localStorage.setItem("PatientForDermatologistReport", JSON.stringify(null));
};

const NavBar = ({ user }) => {
  const NavBarForPharmacist = (
    <Toolbar>
      <Grid container>
        <Grid item xs={3}>
          <Typography>Hay {user}</Typography>
        </Grid>
        <Grid item xs={8} container style={{ textAlign: "right" }}>
          <Grid item xs={2}>
            <Typography>
              <Link
                to="/pharmacist/medicineReservations"
                style={{ color: "#fafafa", textDecoration: "none" }}
              >
                Medicine reservations
              </Link>
            </Typography>
          </Grid>
          <Grid item xs={2}>
            <Typography>
              <Link
                to="/pharmacist/futureExaminations"
                style={{ color: "#fafafa", textDecoration: "none" }}
              >
                Future examinations
              </Link>
            </Typography>
          </Grid>
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
              <a
                href="/"
                style={{ color: "#fafafa", textDecoration: "none" }}
                onClick={setPharmacistPatientReport}
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
        <Grid item xs={3}>
          <Typography>Hay {user}</Typography>
        </Grid>
        <Grid item xs={8} container style={{ textAlign: "right" }}>
          <Grid item xs={2}>
            <Typography>
              <Link
                to="/dermatologist/futureExaminations"
                style={{ color: "#fafafa", textDecoration: "none" }}
              >
                Future examinations
              </Link>
            </Typography>
          </Grid>
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
              <a
                href="/"
                style={{ color: "#fafafa", textDecoration: "none" }}
                onClick={setDermatologistPatientReport}
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
        {user === "patient" && NavBarForPatient}
      </AppBar>
    </>
  );
};

export default NavBar;
