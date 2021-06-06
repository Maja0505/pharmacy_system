import Button from "@material-ui/core/Button";
import Dialog from "@material-ui/core/Dialog";
import DialogTitle from "@material-ui/core/DialogTitle";
import DialogContent from "@material-ui/core/DialogContent";
import DialogActions from "@material-ui/core/DialogActions";
import { Link } from "react-router-dom";
import { Redirect } from "react-router-dom";
import { useState } from "react";
const AppointmentInfoDialog = ({
  openDialog,
  setOpenDialog,
  appointment,
  changeAppointmentToMissed,
  schedule,
}) => {
  const token = localStorage.getItem("token");
  const userId = localStorage.getItem("userId");
  const [redirection, setRedirection] = useState(false);
  const closeDialog = () => {
    setOpenDialog(false);
  };

  const writeReport = () => {
    localStorage.setItem(
      "PatientForDermatologistReport",
      JSON.stringify({
        Id: appointment.patientId,
        FirstName: appointment.subject.split(" ")[0],
        LastName: appointment.subject.split(" ")[1],
        AppointmentId: appointment.id,
        Email: appointment.patientEmail,
        PhoneNumber: appointment.patientPhoneNumber,
        PharmacyId: appointment.pharmacyId,
        PharmacyName: appointment.pharmacyName,
        PharamcyLocation: appointment.location,
        AppointmentStartTime: appointment.dermatologistAppointmentStartTime,
        AppointmentEndTime: appointment.dermatologistAppointmentEndTime,
      })
    );
  };

  const now = new Date();

  const AppointmentDialogContent = (
    <DialogContent dividers>
      <table>
        <tbody>
          <tr>
            <td>Patient :</td>
            <td>{appointment.subject}</td>
          </tr>
          <tr>
            <td>Email :</td>
            <td>{appointment.patientEmail}</td>
          </tr>
          <tr>
            <td>Phone number :</td>
            <td>{appointment.patientPhoneNumber}</td>
          </tr>
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
            <td>Start time :</td>
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
            <td>End time :</td>
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
          <tr>
            <td>Pharmacy :</td>
            <td>{appointment.pharmacyName}</td>
          </tr>
          <tr>
            <td>Location :</td>
            <td>{appointment.location}</td>
          </tr>
          {schedule === false && (
            <>
              <tr>
                {appointment.colorId === 3 && (
                  <td style={{ color: "#ff9800" }}>
                    <b>Expired</b>
                  </td>
                )}
                {appointment.colorId === 1 && (
                  <td style={{ color: "#e51e36" }}>
                    <b>Missed</b>
                  </td>
                )}
                {appointment.colorId === 2 && (
                  <>
                    <td style={{ color: "#4caf50" }}>
                      <b>Reserved</b>
                    </td>
                    <td>
                      <Button
                        autoFocus
                        color="primary"
                        onClick={() =>
                          changeAppointmentToMissed(appointment.id)
                        }
                      >
                        Set to missed
                      </Button>
                    </td>
                  </>
                )}
              </tr>
            </>
          )}
        </tbody>
      </table>
    </DialogContent>
  );

  return (
    <>
      {redirection === true && <Redirect to="/login"></Redirect>}
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
          APPOINTMENT INFO
        </DialogTitle>
        {AppointmentDialogContent}
        <DialogActions>
          <Button autoFocus color="secondary" onClick={closeDialog}>
            Close
          </Button>
          {appointment.colorId === 2 &&
            schedule === false &&
            now < appointment.dermatologistAppointmentEndTime && (
              <Button autoFocus color="primary" onClick={writeReport}>
                <Link
                  to="/dermatologist/writeReport"
                  style={{ textDecoration: "none" }}
                >
                  Write report
                </Link>
              </Button>
            )}
        </DialogActions>
      </Dialog>
    </>
  );
};

AppointmentInfoDialog.defaultProps = {
  openDialog: false,
};

export default AppointmentInfoDialog;
