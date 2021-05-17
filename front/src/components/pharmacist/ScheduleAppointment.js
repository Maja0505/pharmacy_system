import "../css/WorkSchedule.css";
import {
  Grid,
  Card,
  CardContent,
  Typography,
  Snackbar,
} from "@material-ui/core";
import {
  ScheduleComponent,
  Day,
  Week,
  WorkWeek,
  Inject,
  ViewsDirective,
  ViewDirective,
} from "@syncfusion/ej2-react-schedule";

import Alert from "@material-ui/lab/Alert";

import AppointmentCreateDialog from "./AppointmentCreateDialog.js";
import AppointmentInfoDialog from "./AppointmentInfoDialog";

import axios from "axios";

import { Redirect } from "react-router-dom";
import { useEffect, useState } from "react";
import { makeStyles } from "@material-ui/core/styles";

const useStyles = makeStyles({
  cart: {
    fontSize: 15,
    backgroundColor: "#3f51b5",
    color: "#FFFFFF",
  },
});

const ScheduleAppointment = ({ pharmacyInfo }) => {
  const [data, setData] = useState([]);

  const [openCreateDialog, setOpenCreateDialog] = useState(false);
  const [openInfoDialog, setOpenInfoDialog] = useState(false);
  const [openAlertSuccsess, setOpenAlertSuccsess] = useState(false);
  const [openAlertUnsuccsess, setOpenAlertUnsuccses] = useState(false);
  const alertTextSuccsess = useState("Success create appointent!");
  const alertTextUnsuccsess = useState(
    "Patient have other appointment in same time or time of appointment is not valid!"
  );

  const [workingDates, setWorkingDates] = useState([]);
  const [appointment, setAppointment] = useState({});
  const [appointmentInfo, setAppointmentInfo] = useState({});
  const [patientInfo, setPatientInfo] = useState({ Id: -1 });

  const schedule = true;

  const handleCloseAlert = (event, reason) => {
    if (reason === "clickaway") {
      return;
    }
    setOpenAlertSuccsess(false);
    setOpenAlertUnsuccses(false);
  };

  const onPopupOpen = (args) => {
    args.cancel = true;
    if (
      args.data.startTime > new Date() &&
      args.data.patientEmail === undefined
    ) {
      setAppointment({
        startTime: new Date(args.data.startTime),
        endTime: new Date(args.data.endTime),
      });
      setOpenCreateDialog(true);
    }
  };

  const appointmentInfoClick = (e) => {
    setOpenInfoDialog(true);
    setAppointmentInfo(e.event);
  };

  useEffect(() => {
    setPatientInfo(
      JSON.parse(localStorage.getItem("PatientForPharmacistReport"))
    );

    if (
      JSON.parse(localStorage.getItem("PatientForPharmacistReport")) === null
    ) {
      return;
    }

    axios
      .get("http://localhost:8080/api/workingHours/allPharmacistWorkingHours/6")
      .then((res) => {
        res.data.map((workDay) => {
          addToWorkingDates(workDay);
        });
      });

    axios
      .get(
        "http://localhost:8080/api/pharmacistAppointment/allFutureReserved/6"
      )
      .then((res) => {
        addAppointmentsToData(res.data);
      });
  }, []);

  const addAppointmentsToData = (appointments) => {
    setData((previousState) => [...previousState, ...appointments]);
  };

  const addToWorkingDates = async (workDay) => {
    setWorkingDates((oldArray) => [
      ...oldArray,
      {
        startTime: new Date(workDay.workingStartTime),
        endTime: new Date(workDay.workingEndTime),
      },
    ]);
  };

  const [workingHoursForDate, setWorkingHoursForDate] = useState(null);
  const [haveWorkingHoursForSelectedDate, setHaveWorkingHoursForSelectedDate] =
    useState(false);

  const getWorkHoursForDay = async (e) => {
    setHaveWorkingHoursForSelectedDate(false);
    setWorkingHoursForDate({
      date: e.startTime,
    });
    workingDates.map((workingDate) => {
      if (
        new Date(workingDate.startTime).getDate() ===
        new Date(e.startTime).getDate()
      ) {
        setWorkingHoursForDate({
          date: workingDate.startTime,
          startWorkTime: workingDate.startTime,
          endWorkTime: workingDate.endTime,
        });
        setHaveWorkingHoursForSelectedDate(true);
      }
    });
  };

  const classes = useStyles();

  const pharmacistWorkTimeCard = (
    <>
      {workingHoursForDate !== null &&
        haveWorkingHoursForSelectedDate === true && (
          <>
            <Card className={classes.cart}>
              <CardContent>
                <Typography>
                  <b>Pharmacist work time for</b>
                </Typography>
                <Typography style={{ marginTop: "3%" }}>
                  <b>
                    {workingHoursForDate.date.toString().split(" ")[1] +
                      " " +
                      workingHoursForDate.date.toString().split(" ")[2] +
                      " " +
                      workingHoursForDate.date.toString().split(" ")[3]}
                  </b>
                </Typography>
                <Typography style={{ marginTop: "10%" }}>
                  Start Time :{" "}
                  {workingHoursForDate.startWorkTime.toString().split(" ")[4]}
                </Typography>
                <Typography style={{ marginTop: "3%" }}>
                  End Time :{" "}
                  {workingHoursForDate.endWorkTime.toString().split(" ")[4]}
                </Typography>
              </CardContent>
            </Card>
          </>
        )}
      {workingHoursForDate !== null &&
        haveWorkingHoursForSelectedDate !== true && (
          <>
            <Card className={classes.cart}>
              <CardContent>
                <Typography>
                  <b>Pharmacist doesn't work</b>
                </Typography>
                <Typography>
                  <b>
                    {workingHoursForDate.date.toString().split(" ")[1] +
                      " " +
                      workingHoursForDate.date.toString().split(" ")[2] +
                      " " +
                      workingHoursForDate.date.toString().split(" ")[3]}
                  </b>
                </Typography>
              </CardContent>
            </Card>
          </>
        )}
      {workingHoursForDate === null && (
        <>
          <Card className={classes.cart}>
            <CardContent>
              <Typography>
                <b>Click on some cell in calendad</b>
              </Typography>
              <Typography>
                <b>to create appointment</b>
              </Typography>
            </CardContent>
          </Card>
        </>
      )}
    </>
  );

  const patientPersonalInfoCard = (
    <>
      {patientInfo !== undefined && (
        <Card className={classes.cart}>
          <CardContent>
            <Typography>
              <b>Patient personal info</b>
            </Typography>
            <Typography style={{ marginTop: "5%" }}>
              First name : {patientInfo.FirstName}
            </Typography>
            <Typography style={{ marginTop: "3%" }}>
              Last name : {patientInfo.LastName}
            </Typography>
            <Typography style={{ marginTop: "3%" }}>
              Email : {patientInfo.Email}
            </Typography>
            <Typography style={{ marginTop: "3%" }}>
              Phone number : {patientInfo.PhoneNumber}
            </Typography>
          </CardContent>
        </Card>
      )}
    </>
  );

  return (
    <>
      {JSON.parse(localStorage.getItem("PatientForPharmacistReport")) ===
        null && <Redirect to="/pharmacist" />}
      {JSON.parse(localStorage.getItem("PatientForPharmacistReport")) !==
        null && (
        <div>
          <Grid container spacing={0} style={{ marginTop: "3%" }}>
            <Grid item xs={1} />
            <Grid item xs={8}>
              <ScheduleComponent
                eventSettings={{
                  dataSource: data,
                  fields: {
                    id: "id",
                    subject: { name: "subject", title: "Event Name" },
                    location: { name: "location", title: "Event Location" },
                    description: {
                      name: "description",
                      title: "Event Description",
                    },
                    startTime: { name: "startTime", title: "Start Duration" },
                    endTime: { name: "endTime", title: "End Duration" },
                  },
                }}
                timeFormat="HH:mm"
                height="500px"
                width="90%"
                popupOpen={onPopupOpen}
                eventClick={(e) => appointmentInfoClick(e)}
                cellClick={(e) => getWorkHoursForDay(e)}
              >
                <ViewsDirective>
                  <ViewDirective option="Day" />
                  <ViewDirective option="Week" />
                  <ViewDirective option="WorkWeek" />
                </ViewsDirective>
                <Inject services={[Day, Week, WorkWeek]} />
              </ScheduleComponent>
              {haveWorkingHoursForSelectedDate === true && (
                <AppointmentCreateDialog
                  openDialog={openCreateDialog}
                  setOpenDialog={setOpenCreateDialog}
                  appointment={appointment}
                  data={data}
                  setData={setData}
                  setAppointment={setAppointment}
                  patient={patientInfo}
                  pharmacy={pharmacyInfo}
                  workingHoursForDate={workingHoursForDate}
                  setOpenAlertSuccsess={setOpenAlertSuccsess}
                  setOpenAlertUnsuccses={setOpenAlertUnsuccses}
                />
              )}
              <AppointmentInfoDialog
                openDialog={openInfoDialog}
                setOpenDialog={setOpenInfoDialog}
                appointment={appointmentInfo}
              />
            </Grid>
            <Grid item xs={2}>
              <div>
                {pharmacistWorkTimeCard}
                <div style={{ marginTop: "20%" }}>
                  {patientPersonalInfoCard}
                </div>
              </div>
            </Grid>
          </Grid>
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
            schedule={schedule}
          >
            <Alert severity="error">{alertTextUnsuccsess}</Alert>
          </Snackbar>
        </div>
      )}
    </>
  );
};

export default ScheduleAppointment;
