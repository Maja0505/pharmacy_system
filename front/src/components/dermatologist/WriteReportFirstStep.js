import { Button, Card, CardContent, Grid, Typography } from "@material-ui/core";

import { makeStyles } from "@material-ui/core/styles";

import axios from "axios";

const useStyles = makeStyles({
  cart: {
    fontSize: 15,
    backgroundColor: "#bed5e7",
    width: "80%",
    margin: "auto",
  },
  button: {
    margin: "auto",
  },
});

const WriteReportFirstStep = ({ appointment, setAppointment }) => {
  const token = localStorage.getItem("token");
  const userId = localStorage.getItem("userId");

  const classes = useStyles();

  const changeAppointmentToMissed = () => {
    axios
      .put(
        "http://localhost:8080/api/dermatologistAppointment/changeStatusToMissed/" +
          appointment.AppointmentId,
        {},
        {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        }
      )
      .then((res) => {
        setAppointment(null);
        localStorage.setItem(
          "PatientForDermatologistReport",
          JSON.stringify(null)
        );
      });
  };

  return (
    <div>
      {appointment.FirstName !== undefined && (
        <>
          <Grid container spacing={1} style={{ marginTop: "1%" }}>
            <Grid item xs={2} />
            <Grid item xs={4}>
              <Card className={classes.cart} variant="outlined">
                <CardContent>
                  <Typography>
                    <b>Patient personal info</b>
                  </Typography>
                  <Typography>First name : {appointment.FirstName}</Typography>
                  <Typography>Last name : {appointment.LastName}</Typography>
                  <Typography>Email : {appointment.Email}</Typography>
                  <Typography>
                    Phone number : {appointment.PhoneNumber}
                  </Typography>
                </CardContent>
              </Card>
            </Grid>
            <Grid item xs={4}>
              <Card className={classes.cart} variant="outlined">
                <CardContent>
                  <Typography>
                    <b>Appointment info</b>
                  </Typography>
                  <Typography>
                    Date :{" "}
                    {appointment.AppointmentStartTime.toString()
                      .split("-")[2]
                      .split("T")[0] +
                      "." +
                      appointment.AppointmentStartTime.toString().split(
                        "-"
                      )[1] +
                      "." +
                      appointment.AppointmentStartTime.toString().split("-")[0]}
                  </Typography>
                  <Typography>
                    Start time :{" "}
                    {
                      appointment.AppointmentStartTime.toString()
                        .split("T")[1]
                        .split(".")[0]
                    }
                  </Typography>
                  <Typography>
                    End Time :{" "}
                    {
                      appointment.AppointmentEndTime.toString()
                        .split("T")[1]
                        .split(".")[0]
                    }
                  </Typography>
                  <Typography>
                    Pharmacy name : {appointment.PharmacyName}
                  </Typography>
                  <Typography>
                    Location : {appointment.PharamcyLocation}
                  </Typography>
                </CardContent>
              </Card>
            </Grid>
          </Grid>
          <Grid container spacing={1} style={{ marginTop: "1%" }}>
            <Grid item xs={2} />
            <Grid item xs={4} />
            <Grid item xs={4}>
              <Typography className={classes.button}>
                <b>Patient didn't appeared </b>
                <Button
                  variant="contained"
                  color="secondary"
                  className={classes.button}
                  onClick={changeAppointmentToMissed}
                >
                  MISSED
                </Button>
              </Typography>
            </Grid>
          </Grid>
        </>
      )}
    </div>
  );
};

export default WriteReportFirstStep;
