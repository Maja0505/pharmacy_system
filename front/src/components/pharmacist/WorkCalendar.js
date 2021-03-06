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
import { URL } from "../other/components";
import { Redirect } from "react-router-dom";
const WorkCalendar = () => {
  const token = localStorage.getItem("token");
  const userId = localStorage.getItem("userId");
  const [redirection, setRedirection] = useState(false);
  const [data, setData] = useState([]);

  const resourceDataSource = [
    { Id: 1, Color: "#e51e36", Name: "missed" },
    { Id: 2, Color: "#4caf50", Name: "open" },
    { Id: 3, Color: "#ff9800", Name: "expired" },
  ];

  const schedule = false;

  const onPopupOpen = (args) => {
    args.cancel = true;
  };

  useEffect(async () => {
    axios
      .get(URL + "/api/pharmacistAppointment/allMissed/" + userId, {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      })
      .then((res) => {
        addAppointmentsToData(res.data);
      })
      .catch((error) => {
        if (error.response.status === 401) {
          setRedirection(true);
        }
      });
    axios
      .get(URL + "/api/pharmacistAppointment/allExpired/" + userId, {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      })
      .then((res) => {
        addAppointmentsToData(res.data);
      })
      .catch((error) => {
        if (error.response.status === 401) {
          setRedirection(true);
        }
      });
    axios
      .get(URL + "/api/pharmacistAppointment/allReserved/" + userId, {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      })
      .then((res) => {
        addAppointmentsToData(res.data);
      })
      .catch((error) => {
        if (error.response.status === 401) {
          setRedirection(true);
        }
      });
  }, []);

  const addAppointmentsToData = (appointments) => {
    setData((previousState) => [...previousState, ...appointments]);
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
        URL + "/api/pharmacistAppointment/changeStatusToMissed/" + id,
        {},
        {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        }
      )
      .then((res) => {
        setOpenDialog(false);
        setData(
          data.map((appointment) =>
            appointment.id === id ? { ...appointment, colorId: 1 } : appointment
          )
        );
      })
      .catch((error) => {
        if (error.response.status === 401) {
          setRedirection(true);
        }
        setOpenDialog(false);
        alert(
          "Appointment not start yet.\nYou can only set status missed for appointment which is started and not finished yet or for appointments in past!"
        );
      });
  };

  return (
    <div style={{ marginLeft: "15%", marginTop: "3%" }}>
      {redirection === true && <Redirect to="/login"></Redirect>}
      <h1 style={{ width: "80%" }}>Work calendar</h1>
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
            colorId: { name: "colorId", title: "Color Id" },
          },
        }}
        popupOpen={onPopupOpen}
        timeFormat="HH:mm"
        height="500px"
        width="80%"
        eventClick={(e) => appointmentClick(e)}
      >
        <ResourcesDirective>
          <ResourceDirective
            field="colorId"
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
        schedule={schedule}
      ></AppointmentInfoDialog>
    </div>
  );
};

export default WorkCalendar;
