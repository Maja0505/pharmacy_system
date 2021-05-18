import Button from "@material-ui/core/Button";
import Dialog from "@material-ui/core/Dialog";
import DialogTitle from "@material-ui/core/DialogTitle";
import DialogContent from "@material-ui/core/DialogContent";
import DialogActions from "@material-ui/core/DialogActions";

import { TimePickerComponent } from "@syncfusion/ej2-react-calendars";

import axios from "axios";

const AppointmentCreateDialog = ({
  openDialog,
  setOpenDialog,
  appointment,
  setAppointment,
  data,
  setData,
  patient,
  pharmacy,
  predefinedAppointment,
  workingHoursForDate,
  setOpenAlertSuccsess,
  setOpenAlertUnsuccses,
}) => {
  const closeDialog = () => {
    setOpenDialog(false);
  };

  const changeAppointmentEndTime = (e) => {
    setAppointment({ StartTime: appointment.StartTime, EndTime: e.value });
  };

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

  const createAppointment = (a) => {
    if (a.EndTime !== null) {
      if (predefinedAppointment === false) {
        axios
          .post(
            "http://localhost:8080/api/dermatologistAppointment/bookByDermatologist",
            {
              staffId: 8,
              pharmacyId: 1,
              patientId: patient.Id,
              appointmentStartTime: makeDate(a.StartTime),
              appointmentEndTime: makeDate(a.EndTime),
              appointmentDuration: null,
              staffWorkStartTime: makeDate(workingHoursForDate.startWorkTime),
              staffWorkEndTime: makeDate(workingHoursForDate.endWorkTime),
            }
          )
          .then((res) => {
            setOpenAlertSuccsess(true);
            setOpenDialog(false);
            createManualApppointment(a);
          })
          .catch((error) => {
            setOpenAlertUnsuccses(true);
          });
      } else {
        axios
          .put(
            "http://localhost:8080/api/dermatologistAppointment/book/" +
              a.id +
              "/" +
              patient.Id
          )
          .then((res) => {
            setOpenAlertSuccsess(true);
            setOpenDialog(false);
            createPredefinedApppointment(a);
          })
          .catch((error) => {
            setOpenAlertUnsuccses(true);
          });
      }
    }
  };

  const createManualApppointment = (a) => {
    setData((oldData) => [
      ...oldData,
      {
        subject: patient.FirstName + " " + patient.LastName,
        patientEmail: patient.Email,
        patientPhoneNumber: patient.PhoneNumber,
        dermatologistAppointmentStartTime: a.StartTime,
        dermatologistAppointmentEndTime: a.EndTime,
        pharmacyName: pharmacy !== undefined ? pharmacy.name : "DODATI APOTEKU",
        location: pharmacy !== undefined ? pharmacy.address : "DODATI ADRESU",
      },
    ]);
  };

  const createPredefinedApppointment = (a) => {
    console.log(data);
    setData(
      data.map((d) =>
        d.id === a.id
          ? {
              ...d,
              subject: patient.FirstName + " " + patient.LastName,
              patientEmail: patient.Email,
              patientPhoneNumber: patient.PhoneNumber,
              colorId: 5,
            }
          : d
      )
    );
  };

  const ManualCreateDialog = (
    <>
      <table>
        <tbody>
          <tr>
            <td>Date :</td>
            {appointment.StartTime !== undefined && (
              <td>
                {appointment.StartTime.toString().split(" ")[1] +
                  " " +
                  appointment.StartTime.toString().split(" ")[2] +
                  " " +
                  appointment.StartTime.toString().split(" ")[3]}
              </td>
            )}
          </tr>
          <tr>
            <td>Start Time :</td>
            {appointment.StartTime !== undefined && (
              <td>{appointment.StartTime.toString().split(" ")[4]}</td>
            )}
          </tr>
          <tr>
            <td>End Time :</td>
            <td>
              <TimePickerComponent
                id="timepicker"
                placeholder="Select a end time"
                format="HH:mm:ss"
                min={
                  new Date(
                    new Date(appointment.StartTime).setMinutes(
                      new Date(appointment.StartTime).getMinutes() + 15
                    )
                  )
                }
                showClearButton={false}
                step={15}
                allowEdit={false}
                value={appointment.EndTime}
                onChange={(e) => changeAppointmentEndTime(e)}
              />
            </td>
          </tr>
        </tbody>
      </table>
    </>
  );

  const PredefinedCreateDialog = (
    <>
      <table>
        <tbody>
          <tr>
            <td>Date :</td>
            {appointment.dermatologistAppointmentStartTime !== undefined && (
              <td>
                {appointment.dermatologistAppointmentStartTime
                  .toString()
                  .split(" ")[1] +
                  " " +
                  appointment.dermatologistAppointmentStartTime
                    .toString()
                    .split(" ")[2] +
                  " " +
                  appointment.dermatologistAppointmentStartTime
                    .toString()
                    .split(" ")[3]}
              </td>
            )}
          </tr>
          <tr>
            <td>Start Time :</td>
            {appointment.dermatologistAppointmentStartTime !== undefined && (
              <td>
                {
                  appointment.dermatologistAppointmentStartTime
                    .toString()
                    .split(" ")[4]
                }
              </td>
            )}
          </tr>
          <tr>
            <td>End Time :</td>
            {appointment.dermatologistAppointmentEndTime !== undefined && (
              <td>
                {
                  appointment.dermatologistAppointmentEndTime
                    .toString()
                    .split(" ")[4]
                }
              </td>
            )}
          </tr>
        </tbody>
      </table>
    </>
  );

  return (
    <Dialog
      onClose={closeDialog}
      aria-labelledby="customized-dialog-title"
      open={openDialog}
    >
      <DialogTitle
        id="customized-dialog-title"
        onClose={closeDialog}
        style={{ color: "#1a237e", margin: "auto" }}
      >
        CREATE APPOINTMENT
      </DialogTitle>
      <DialogContent>
        {predefinedAppointment === false && ManualCreateDialog}
        {predefinedAppointment === true && PredefinedCreateDialog}
      </DialogContent>
      <DialogActions>
        <Button autoFocus color="secondary" onClick={closeDialog}>
          Close
        </Button>
        <Button
          autoFocus
          color="primary"
          onClick={() => createAppointment(appointment)}
        >
          Create appointment
        </Button>
      </DialogActions>
    </Dialog>
  );
};

AppointmentCreateDialog.defaultProps = {
  openDialog: false,
};

export default AppointmentCreateDialog;
