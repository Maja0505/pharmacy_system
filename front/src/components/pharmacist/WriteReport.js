import { Redirect } from "react-router-dom";

import { useState, useEffect } from "react";

import { makeStyles } from "@material-ui/core/styles";
import {
  Stepper,
  Step,
  StepLabel,
  Button,
  Typography,
  Paper,
  Snackbar,
} from "@material-ui/core";
import Alert from "@material-ui/lab/Alert";

import WriteReportFirstStep from "./WriteReportFirstStep.js";
import WriteReportSecondStep from "./WriteReportSecondStep.js";
import ScheduleAppointment from "./ScheduleAppointment.js";
import WriteReportThirdStep from "./WriteReportThirdStep.js";
import {URL} from "../other/components"


import axios from "axios";

const useStyles = makeStyles((theme) => ({
  paper: {
    background: "#bed5e7",
    margin: "auto",
  },
  schedule: {
    width: "80%",
    margin: "auto",
  },
  steper: {
    width: "80%",
    background: "#bed5e7",
    margin: "auto",
  },
  backButton: {
    marginRight: theme.spacing(1),
  },
  instructions: {
    marginTop: theme.spacing(1),
    marginBottom: theme.spacing(1),
  },
}));

const WriteReport = () => {
  const token = localStorage.getItem("token");
  const userId = localStorage.getItem("userId");

  const classes = useStyles();

  const [openAlertSuccsess, setOpenAlertSuccsess] = useState(false);
  const [openAlertUnsuccsess, setOpenAlertUnsuccses] = useState(false);
  const alertTextSuccsess = useState("Have enough medicine in pharmacy!");
  const alertTextUnsuccsess = useState(
    "Doesn't have enough medicine in storage or pacient is alergic on them"
  );

  const [recipeItems, setRecipeItems] = useState([]);

  const handleCloseAlert = (event, reason) => {
    if (reason === "clickaway") {
      return;
    }
    setOpenAlertSuccsess(false);
    setOpenAlertUnsuccses(false);
  };

  const [activeStep, setActiveStep] = useState(0);
  const steps = [
    "Patient and appointment info",
    "Write report info",
    "Add recipe",
    "Schedule another appointment",
  ];

  const [appointment, setAppointment] = useState({});

  const makeDate = (date) => {
    let year = date.toString().split(" ")[3];
    let monthString = date.toString().split(" ")[1];
    let day = date.toString().split(" ")[2];
    let HH = date.toString().split(" ")[4];
    let month = "";

    switch (monthString) {
      case "Jan":
        month = "01";
        break;
      case "Feb":
        month = "02";
        break;
      case "Mar":
        month = "03";
        break;
      case "Apr":
        month = "04";
        break;
      case "May":
        month = "05";
        break;
      case "Jun":
        month = "06";
        break;
      case "Jul":
        month = "07";
        break;
      case "Aug":
        month = "08";
        break;
      case "Sep":
        month = "09";
        break;
      case "Oct":
        month = "10";
        break;
      case "Nov":
        month = "11";
        break;
      case "Dec":
        month = "12";
        break;
    }

    return year + "-" + month + "-" + day + "T" + HH;
  };

  const [report, setReport] = useState({
    appointmentId: "",
    creationDate: makeDate(new Date()),
    reportInfo: "",
    recipeItemsDTO: [],
  });

  useEffect(() => {
    setAppointment(
      JSON.parse(localStorage.getItem("PatientForPharmacistReport"))
    );
  }, []);

  const handleNext = () => {
    setActiveStep((prevActiveStep) => prevActiveStep + 1);
    setReport({
      ...report,
      appointmentId: appointment.AppointmentId,
      recipeItemsDTO: recipeItems,
    });
  };

  const handleBack = () => {
    setActiveStep((prevActiveStep) => prevActiveStep - 1);
  };

  const finishReport = async () => {
    axios
      .post(URL + "/api/pharmacistReport/create", report, {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      })
      .then((res) => {
        localStorage.setItem(
          "PatientForPharmacistReport",
          JSON.stringify(null)
        );
        setAppointment(null);
      })
      .catch((error) => {
        alert("Conflict problem with recipe items please pick again");
        setActiveStep(2);
        setRecipeItems([]);
      });
  };

  const getSteperContent = (
    <>
      {activeStep === 0 && appointment !== null && (
        <WriteReportFirstStep
          appointment={appointment}
          setAppointment={setAppointment}
        />
      )}
      {activeStep === 1 && appointment !== null && (
        <WriteReportSecondStep
          appointment={appointment}
          report={report}
          setReport={setReport}
        />
      )}
      {activeStep === 2 && (
        <WriteReportThirdStep
          appointment={appointment}
          setOpenAlertSuccsess={setOpenAlertSuccsess}
          setOpenAlertUnsuccses={setOpenAlertUnsuccses}
          recipeItems={recipeItems}
          setRecipeItems={setRecipeItems}
        />
      )}
      {activeStep === 3 && <ScheduleAppointment />}
    </>
  );

  return (
    <>
      {appointment === null && <Redirect to="/pharmacist/workCalendar" />}

      {appointment !== null && (
        <div>
          <Paper className={classes.paper} elevation={0}>
            <Typography variant="h4" style={{ marginTop: "3%" }}>
              Write report
            </Typography>
            <Stepper
              className={classes.steper}
              activeStep={activeStep}
              alternativeLabel
            >
              {steps.map((label) => (
                <Step key={label}>
                  <StepLabel>{label}</StepLabel>
                </Step>
              ))}
            </Stepper>
            {getSteperContent}
            <div style={{ marginTop: "3%", marginBottom: "3%" }}>
              <Button
                disabled={activeStep === 0}
                onClick={handleBack}
                className={classes.backButton}
              >
                Back
              </Button>
              {activeStep === steps.length - 1 && (
                <Button
                  disabled={
                    activeStep === 1 &&
                    report.reportInfo !== undefined &&
                    report.reportInfo.trim() === ""
                  }
                  variant="contained"
                  color="primary"
                  onClick={finishReport}
                >
                  Finish report
                </Button>
              )}
              {activeStep !== steps.length - 1 && (
                <Button
                  disabled={
                    activeStep === 1 &&
                    report.reportInfo !== undefined &&
                    report.reportInfo.trim() === ""
                  }
                  variant="contained"
                  color="primary"
                  onClick={handleNext}
                >
                  Next
                </Button>
              )}
            </div>
          </Paper>
          <Snackbar
            open={openAlertSuccsess}
            autoHideDuration={1500}
            onClose={handleCloseAlert}
          >
            <Alert severity="success">{alertTextSuccsess}</Alert>
          </Snackbar>
          <Snackbar
            open={openAlertUnsuccsess}
            autoHideDuration={1500}
            onClose={handleCloseAlert}
          >
            <Alert severity="error">{alertTextUnsuccsess}</Alert>
          </Snackbar>
        </div>
      )}
    </>
  );
};

export default WriteReport;
