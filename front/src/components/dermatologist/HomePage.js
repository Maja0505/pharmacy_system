import NavBar from "../other/NavBar.js";
import { BrowserRouter as Router, Route, Switch } from "react-router-dom";
import EditProfile from "./EditProfile.js";
import VacationRequest from "./VacationRequest.js";
import WorkCalendar from "./WorkCalendar.js";
import WriteReport from "./WriteReport.js";
import ExaminedPatients from "./ExaminedPatients.js";
import ScheduleAppointment from "./SheduleAppointment.js";

const HomePage = () => {
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
            <WorkCalendar></WorkCalendar>
          </Route>
          <Route path="/dermatologist/writeReport">
            <WriteReport></WriteReport>
          </Route>
          <Route path="/dermatologist/examinedPatients">
            <ExaminedPatients />
          </Route>
          <Route path="/dermatologist/scheduleAppointment">
            <ScheduleAppointment />
          </Route>
        </div>
      </Switch>
    </Router>
  );
};

export default HomePage;
