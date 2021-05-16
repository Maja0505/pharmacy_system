import Button from "@material-ui/core/Button";
import Dialog from "@material-ui/core/Dialog";
import DialogTitle from "@material-ui/core/DialogTitle";
import DialogContent from "@material-ui/core/DialogContent";
import DialogActions from "@material-ui/core/DialogActions";
import { Link } from "react-router-dom";

const AppointmentInfoDialog = ({
  openDialog,
  setOpenDialog,
  appointment,
  changeAppointmentToMissed,
}) => {
  const closeDialog = () => {
    setOpenDialog(false);
  };

  const writeReport = () => {
    localStorage.setItem(
      "PatientForPharmacistReport",
      JSON.stringify({
        Id: appointment.PatientId,
        FirstName: appointment.Subject.split(" ")[0],
        LastName: appointment.Subject.split(" ")[1],
        AppointmentId: appointment.Id,
        Email: appointment.PatientEmail,
        PhoneNumber: appointment.PatientPhoneNumber,
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
            <td>{appointment.Subject}</td>
          </tr>
          <tr>
            <td>Email :</td>
            <td>{appointment.PatientEmail}</td>
          </tr>
          <tr>
            <td>Phone number :</td>
            <td>{appointment.PatientPhoneNumber}</td>
          </tr>
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
            <td>Start time :</td>
            {appointment.StartTime !== undefined && (
              <td>{appointment.StartTime.toString().split(" ")[4]}</td>
            )}
          </tr>
          <tr>
            <td>End time :</td>
            {appointment.EndTime !== undefined && (
              <td>{appointment.EndTime.toString().split(" ")[4]}</td>
            )}
          </tr>
          <tr>
            <td>Pharmacy :</td>
            <td>{appointment.PharmacyName}</td>
          </tr>
          <tr>
            <td>Location :</td>
            <td>{appointment.Location}</td>
          </tr>
          <tr>
            {appointment.ColorID === 3 && (
              <td style={{ color: "#ff9800" }}>
                <b>Expired</b>
              </td>
            )}
            {appointment.ColorID === 1 && (
              <td style={{ color: "#e51e36" }}>
                <b>Missed</b>
              </td>
            )}
            {appointment.ColorID === 2 && (
              <>
                <td style={{ color: "#4caf50" }}>
                  <b>Reserved</b>
                </td>
                <td>
                  <Button
                    autoFocus
                    color="primary"
                    onClick={() => changeAppointmentToMissed(appointment.Id)}
                  >
                    Set to missed
                  </Button>
                </td>
              </>
            )}
          </tr>
        </tbody>
      </table>
    </DialogContent>
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
        APPOINTMENT INFO
      </DialogTitle>
      {AppointmentDialogContent}
      <DialogActions>
        <Button autoFocus color="secondary" onClick={closeDialog}>
          Close
        </Button>
        {appointment.ColorID === 2 && now < appointment.EndTime && (
          <Button autoFocus color="primary" onClick={writeReport}>
            <Link
              to="/pharmacist/writeReport"
              style={{ textDecoration: "none" }}
            >
              Write report
            </Link>
          </Button>
        )}
      </DialogActions>
    </Dialog>
  );
};

AppointmentInfoDialog.defaultProps = {
  openDialog: false,
};

export default AppointmentInfoDialog;
