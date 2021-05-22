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
  ResourcesDirective,
  ResourceDirective,
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
    backgroundColor: "#bed5e7",
  },
});

const SheduleAppointment = ({ pharmacyInfo }) => {
  const [data, setData] = useState([]);
  const resourceDataSource = [{ Id: 4, Color: "#8c9290", Name: "open" }];

  const schedule = true;

  const [openCreateDialog, setOpenCreateDialog] = useState(false);
  const [openInfoDialog, setOpenInfoDialog] = useState(false);
  const [predefinedAppointment, setPredefinedAppointment] = useState(false);
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

  const handleCloseAlert = (event, reason) => {
    if (reason === "clickaway") {
      return;
    }
    setOpenAlertSuccsess(false);
    setOpenAlertUnsuccses(false);
  };

  const onPopupOpen = (args) => {
    args.cancel = true;
    if (args.data.startTime > new Date()) {
      setAppointment({
        StartTime: new Date(args.data.startTime),
        EndTime: new Date(args.data.endTime),
      });
      setOpenCreateDialog(true);
      setPredefinedAppointment(false);
    }
  };

  const appointmentInfoClick = (e) => {
    if (e.event.patientEmail !== null) {
      setOpenInfoDialog(true);
      setAppointmentInfo(e.event);
    } else {
      setAppointment(e.event);
      setOpenCreateDialog(true);
      setPredefinedAppointment(true);
    }
  };

  useEffect(() => {
    setPatientInfo(
      JSON.parse(localStorage.getItem("PatientForDermatologistReport"))
    );

    if (
      JSON.parse(localStorage.getItem("PatientForDermatologistReport")) === null
    ) {
      return;
    }
    axios.get("http://localhost:8080/api/workingHours/all/8/1").then((res) => {
      res.data.map((workDay) => {
        addToWorkingDates(workDay);
      });
    });

    axios
      .get(
        "http://localhost:8080/api/dermatologistAppointment/allFutureOpen/8/1"
      )
      .then((res) => {
        addAppointmentsToData(res.data);
      });

    axios
      .get(
        "http://localhost:8080/api/dermatologistAppointment/allFutureReserved/8/1"
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

  const dermatologistWorkTimeCard = (
    <>
      {workingHoursForDate !== null &&
        haveWorkingHoursForSelectedDate === true && (
          <>
            <Card className={classes.cart} variant="outlined">
              <CardContent>
                <Typography>
                  <b>Dermatologist work time for</b>
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
            <Card className={classes.cart} variant="outlined">
              <CardContent>
                <Typography>
                  <b>Dermatologist doesn't work</b>
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
          <Card className={classes.cart} variant="outlined">
            <CardContent>
              <Typography>
                <b>Pick some of open appointment</b>
              </Typography>
              <Typography>
                <b>or create manually appointment</b>
              </Typography>
              <Typography>
                <b>clicking on some cell in calendar</b>
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
        <Card className={classes.cart} variant="outlined">
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
      {JSON.parse(localStorage.getItem("PatientForDermatologistReport")) ===
        null && <Redirect to="/dermatologist" />}
      {JSON.parse(localStorage.getItem("PatientForDermatologistReport")) !==
        null && (
        <div>
          <Grid container spacing={0} style={{ marginTop: "3%" }}>
            <Grid item xs={1} />
            <Grid item xs={8}>
              <ScheduleComponent
                eventSettings={{
                  dataSource: data,
                  fields: {
                    subject: { name: "subject", title: "Subject" },
                    startTime: {
                      name: "dermatologistAppointmentStartTime",
                      title: "Start Duration",
                    },
                    endTime: {
                      name: "dermatologistAppointmentEndTime",
                      title: "End Duration",
                    },
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
                <ResourcesDirective>
                  <ResourceDirective
                    field="colorId"
                    idField="Id"
                    colorField="Color"
                    textField="Name"
                    dataSource={resourceDataSource}
                  ></ResourceDirective>
                </ResourcesDirective>
                <Inject services={[Day, Week, WorkWeek]} />
              </ScheduleComponent>
              {(haveWorkingHoursForSelectedDate === true ||
                predefinedAppointment === true) && (
                <AppointmentCreateDialog
                  openDialog={openCreateDialog}
                  setOpenDialog={setOpenCreateDialog}
                  appointment={appointment}
                  data={data}
                  setData={setData}
                  setAppointment={setAppointment}
                  patient={patientInfo}
                  pharmacy={pharmacyInfo}
                  predefinedAppointment={predefinedAppointment}
                  workingHoursForDate={workingHoursForDate}
                  setOpenAlertSuccsess={setOpenAlertSuccsess}
                  setOpenAlertUnsuccses={setOpenAlertUnsuccses}
                />
              )}
              <AppointmentInfoDialog
                openDialog={openInfoDialog}
                setOpenDialog={setOpenInfoDialog}
                appointment={appointmentInfo}
                schedule={schedule}
              />
            </Grid>
            <Grid item xs={2}>
              <div>
                {dermatologistWorkTimeCard}
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
          >
            <Alert severity="error">{alertTextUnsuccsess}</Alert>
          </Snackbar>
        </div>
      )}
    </>
  );
};

export default SheduleAppointment;
