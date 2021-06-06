import { Button, Card, CardContent, Grid, Typography } from "@material-ui/core";

import { makeStyles } from "@material-ui/core/styles";
import { URL } from "../other/components";

import axios from "axios";
import { Redirect } from "react-router-dom";
import { useState } from "react";

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
  const [redirection, setRedirection] = useState(false);
  const classes = useStyles();

  const changeAppointmentToMissed = () => {
    axios
      .put(
        URL +
          "/api/pharmacistAppointment/changeStatusToMissed/" +
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
          "PatientForPharmacistReport",
          JSON.stringify(null)
        );
      })
      .catch((error) => {
        if (error.response.status === 401) {
          setRedirection(true);
        }
        alert(
          "Appointment not start yet.\nYou can only set status missed for appointment which is started and not finished yet!"
        );
      });
  };

  return (
    <div>
      {redirection === true && <Redirect to="/login"></Redirect>}
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
