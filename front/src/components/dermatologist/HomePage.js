import NavBar from "../other/NavBar.js";
import { BrowserRouter as Router, Route, Switch } from "react-router-dom";
import EditProfile from "./EditProfile.js";
import VacationRequest from "./VacationRequest.js";
import WorkCalendar from "./WorkCalendar.js";
import WriteReport from "./WriteReport.js";
import ExaminedPatients from "./ExaminedPatients.js";

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
          <NavBar user={"dermatologist"} />
          <Route
            path="/dermatologist/editProfile"
            component={EditProfile}
          ></Route>
          <Route
            path="/dermatologist/vacationRequest"
            component={VacationRequest}
          ></Route>
          <Route path="/dermatologist/workCalendar">
            <WorkCalendar setPatient={setPatientForReport}></WorkCalendar>
          </Route>
          <Route path="/dermatologist/writeReport">
            <WriteReport patient={patientForReport}></WriteReport>
          </Route>
          <Route path="/dermatologist/examinedPatients">
            <ExaminedPatients />
          </Route>
        </div>
      </Switch>
    </Router>
  );
};

export default HomePage;
