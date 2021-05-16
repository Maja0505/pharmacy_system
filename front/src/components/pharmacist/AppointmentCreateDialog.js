import Button from "@material-ui/core/Button";
import Dialog from "@material-ui/core/Dialog";
import DialogTitle from "@material-ui/core/DialogTitle";
import DialogContent from "@material-ui/core/DialogContent";
import DialogActions from "@material-ui/core/DialogActions";

import { ComboBoxComponent } from "@syncfusion/ej2-react-dropdowns";

import { useState } from "react";

import axios from "axios";

const AppointmentCreateDialog = ({
  openDialog,
  setOpenDialog,
  appointment,
  setData,
  patient,
  pharmacy,
  setOpenAlertSuccsess,
  setOpenAlertUnsuccses,
}) => {
  const durationData = [15, 30, 45, 60, 75, 90, 105, 120];
  const [appointmentDuration, setAppointmentDuration] = useState(15);

  const closeDialog = () => {
    setOpenDialog(false);
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
      axios
        .post(
          "http://localhost:8080/api/pharmacistAppointment/bookByPharmacist",
          {
            staffId: 6,
            pharmacyId: 1,
            patientId: patient.Id,
            appointmentStartTime: makeDate(a.StartTime),
            appointmentEndTime: null,
            appointmentDuration: appointmentDuration,
            staffWorkStartTime: null,
            staffWorkEndTime: null,
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
    }
  };

  const createManualApppointment = (a) => {
    setData((oldData) => [
      ...oldData,
      {
        Subject: patient.FirstName + " " + patient.LastName,
        PatientEmail: patient.Email,
        PatientPhoneNumber: patient.PhoneNumber,
        StartTime: a.StartTime,
        EndTime: new Date(
          new Date(a.StartTime).setMinutes(
            new Date(a.StartTime).getMinutes() + appointmentDuration
          )
        ),
        PharmacyName: pharmacy !== undefined ? pharmacy.name : "DODATI APOTEKU",
        Location: pharmacy !== undefined ? pharmacy.address : "DODATI ADRESU",
      },
    ]);
  };

  const setDuration = (d) => {
    setAppointmentDuration(d);
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
            <td>Duration (minutes) :</td>
            <td>
              <ComboBoxComponent
                id="comboelement"
                dataSource={durationData}
                value={appointmentDuration}
                showClearButton={false}
                allowEdit={false}
                change={(e) => setDuration(e.value)}
              />
            </td>
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
      <DialogContent>{ManualCreateDialog}</DialogContent>
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
