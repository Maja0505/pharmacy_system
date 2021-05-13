import NavBar from "../other/NavBar.js";
import { BrowserRouter as Router, Route, Switch } from "react-router-dom";
import ExaminedPatients from "./ExaminedPatients.js";
import EditProfile from "./EditProfile.js";
import VacationRequest from "./VacationRequest.js";
import WorkCalendar from "./WorkCalendar.js";
import WriteReport from "./WriteReport.js";

import { useState } from "react";

const HomePage = () => {
  const [patientForReport, setPatientForReport] = useState({
    Id: -1,
    FirstName: "",
    LastName: "",
    AppointmentId: "",
  });

  return (
    <Router>
      <Switch>
        <div>
          <NavBar user={"pharmacist"} />
          <Route
            path="/pharmacist/examinedPatients"
            component={ExaminedPatients}
          ></Route>
          <Route path="/pharmacist/editProfile" component={EditProfile}></Route>
          <Route
            path="/pharmacist/vacationRequest"
            component={VacationRequest}
          ></Route>
          <Route path="/pharmacist/workCalendar">
            <WorkCalendar setPatient={setPatientForReport}></WorkCalendar>
          </Route>
          <Route path="/pharmacist/writeReport">
            <WriteReport patient={patientForReport}></WriteReport>
          </Route>
        </div>
      </Switch>
    </Router>
  );
};

export default HomePage;
