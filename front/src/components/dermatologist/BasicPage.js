import NavBar from "../other/NavBar.js";
import { BrowserRouter as Router, Route, Switch } from "react-router-dom";
import EditProfile from "./EditProfile.js";
import VacationRequest from "./VacationRequest.js";
import WorkCalendar from "./WorkCalendar.js";
import WriteReport from "./WriteReport.js";
import ExaminedPatients from "./ExaminedPatients.js";
import ScheduleAppointment from "./SheduleAppointment.js";
import FutureExaminations from "./FutureExaminations.js";
import HomePage from "./HomePage.js";
import Examinations from "./Examinations.js";
import { useState } from "react";
const BasicPage = () => {

  const userId = localStorage.getItem("userId");
  const [redirection,setRedirection] = useState(false)
  return (
    <Router>
      <Switch>
        <div>
        {(userId !== undefined && userId !== null) && <NavBar user={"dermatologist"} />}
          <Route
            path="/dermatologist/editProfile"
            component={EditProfile}
          ></Route>
          <Route
            path="/dermatologist/vacationRequest"
            component={VacationRequest}
          ></Route>
          <Route path="/dermatologist/workCalendar">
            <WorkCalendar></WorkCalendar>
          </Route>
          <Route path="/dermatologist/writeReport">
            <WriteReport></WriteReport>
          </Route>
          <Route path="/dermatologist/homePage">
            <HomePage />
          </Route>
          <Route path="/dermatologist/examinations">
            <Examinations />
          </Route>
        </div>
      </Switch>
    </Router>
  );
};

export default BasicPage;
