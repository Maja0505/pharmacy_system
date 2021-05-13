import "../css/WorkSchedule.css";
import AppointmentInfoDialog from "./AppointmentInfoDialog.js";
import {
  ScheduleComponent,
  Day,
  Week,
  WorkWeek,
  Month,
  Agenda,
  Inject,
  ResourcesDirective,
  ResourceDirective,
} from "@syncfusion/ej2-react-schedule";
import axios from "axios";
import { useEffect, useState } from "react";

const WorkCalendar = ({ setPatient }) => {
  const [data, setData] = useState([]);

  const resourceDataSource = [
    { Id: 1, Color: "#e51e36", Name: "missed" },
    { Id: 2, Color: "#4caf50", Name: "open" },
    { Id: 3, Color: "#ff9800", Name: "expired" },
  ];

  const onPopupOpen = (args) => {
    args.cancel = true;
  };

  useEffect(() => {
    axios
      .get("http://localhost:8080/api/pharmacistAppointment/allMissed/6")
      .then((res) => {
        addAppointmentsToData(res.data, 1);
      });
    axios
      .get("http://localhost:8080/api/pharmacistAppointment/allExpired/6")
      .then((res) => {
        addAppointmentsToData(res.data, 3);
      });
    axios
      .get("http://localhost:8080/api/pharmacistAppointment/allReserved/6")
      .then((res) => {
        addAppointmentsToData(res.data, 2);
      });
  }, []);

  const addAppointmentsToData = (appointments, colorId) => {
    appointments.map((a) =>
      setData((oldDatas) => [
        ...oldDatas,
        {
          Id: a.id,
          Subject: a.patientFirstName + " " + a.patientLastName,
          Location:
            a.pharmacyForDermatologistAppointment.pharmacyAddress.city +
            ", " +
            a.pharmacyForDermatologistAppointment.pharmacyAddress.streetName +
            " " +
            a.pharmacyForDermatologistAppointment.pharmacyAddress.streetNumber,
          StartTime: a.pharmacistAppointmentStartTime,
          EndTime: a.pharmacistAppointmentEndTime,
          ColorID: colorId,
          PatientEmail: a.patientEmail,
          PatientFirstName: a.patientFirstName,
          PatientId: a.patientId,
          PatientLastName: a.patientLastName,
          PatientPhoneNumber: a.patientPhoneNumber,
          AppointmentPrice: a.appointmentPrice,
          PharmacyName: a.pharmacyForDermatologistAppointment.pharmacyName,
        },
      ])
    );
  };

  const [appointmentInfo, setAppointmentInfo] = useState({});

  const [openDialog, setOpenDialog] = useState(false);

  const appointmentClick = async (e) => {
    var appointment = e.event;
    setAppointmentInfo(appointment);
    setOpenDialog(true);
  };

  const changeAppointmentToMissed = async (id) => {
    axios
      .put(
        "http://localhost:8080/api/pharmacistAppointment/changeStatusToMissed/" +
          id
      )
      .then((res) => {
        setOpenDialog(false);
        setData(
          data.map((appointment) =>
            appointment.Id === id ? { ...appointment, ColorID: 1 } : appointment
          )
        );
      })
      .catch((error) => {
        setOpenDialog(false);
        alert("You can only set missed for appointment which is in past!");
      });
  };

  return (
    <div style={{ marginLeft: "15%", marginTop: "3%" }}>
      <h1 style={{ width: "80%" }}>Work calendar</h1>
      <ScheduleComponent
        eventSettings={{ dataSource: data }}
        popupOpen={onPopupOpen}
        timeFormat="HH:mm"
        height="500px"
        width="80%"
        eventClick={(e) => appointmentClick(e)}
      >
        <ResourcesDirective>
          <ResourceDirective
            field="ColorID"
            idField="Id"
            colorField="Color"
            textField="Name"
            dataSource={resourceDataSource}
          ></ResourceDirective>
        </ResourcesDirective>
        <Inject services={[Day, Week, WorkWeek, Month, Agenda]} />
      </ScheduleComponent>
      <AppointmentInfoDialog
        openDialog={openDialog}
        setOpenDialog={setOpenDialog}
        appointment={appointmentInfo}
        changeAppointmentToMissed={changeAppointmentToMissed}
        setPatient={setPatient}
      ></AppointmentInfoDialog>
    </div>
  );
};

export default WorkCalendar;
