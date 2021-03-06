import Button from "@material-ui/core/Button";
import Dialog from "@material-ui/core/Dialog";
import DialogTitle from "@material-ui/core/DialogTitle";
import DialogContent from "@material-ui/core/DialogContent";
import DialogActions from "@material-ui/core/DialogActions";

import { ComboBoxComponent } from "@syncfusion/ej2-react-dropdowns";

import { useState } from "react";
import { Redirect } from "react-router-dom";
import axios from "axios";
import { URL } from "../other/components";

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
  const token = localStorage.getItem("token");
  const userId = localStorage.getItem("userId");
  const [redirection, setRedirection] = useState(false);
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
          URL + "/api/pharmacistAppointment/bookByPharmacist",
          {
            staffId: userId,
            pharmacyId: patient.PharmacyId,
            patientId: patient.Id,
            appointmentStartTime: makeDate(a.startTime),
            appointmentEndTime: null,
            appointmentDuration: appointmentDuration,
            staffWorkStartTime: null,
            staffWorkEndTime: null,
          },
          {
            headers: {
              Authorization: `Bearer ${token}`,
            },
          }
        )
        .then((res) => {
          setOpenAlertSuccsess(true);
          setOpenDialog(false);
          createManualApppointment(a);
        })
        .catch((error) => {
          if (error.response.status === 401) {
            setRedirection(true);
          }
          setOpenAlertUnsuccses(true);
        });
    }
  };

  const createManualApppointment = (a) => {
    setData((oldData) => [
      ...oldData,
      {
        subject: patient.FirstName + " " + patient.LastName,
        patientEmail: patient.Email,
        patientPhoneNumber: patient.PhoneNumber,
        startTime: a.startTime,
        endTime: new Date(
          new Date(a.startTime).setMinutes(
            new Date(a.startTime).getMinutes() + appointmentDuration
          )
        ),
        pharmacyName: patient.PharmacyName,
        location: patient.PharamcyLocation,
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
            {appointment.startTime !== undefined && (
              <td>
                {appointment.startTime.toString().split(" ")[1] +
                  " " +
                  appointment.startTime.toString().split(" ")[2] +
                  " " +
                  appointment.startTime.toString().split(" ")[3]}
              </td>
            )}
          </tr>
          <tr>
            <td>Start Time :</td>
            {appointment.startTime !== undefined && (
              <td>{appointment.startTime.toString().split(" ")[4]}</td>
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
    </>
  );
};

AppointmentCreateDialog.defaultProps = {
  openDialog: false,
};

export default AppointmentCreateDialog;
