import NavBar from "../other/NavBar.js";
import { BrowserRouter as Router, Route, Switch } from "react-router-dom";
import EditProfile from "./EditProfile.js";
import VacationRequest from "./VacationRequest.js";
import WorkCalendar from "./WorkCalendar.js";
import WriteReport from "./WriteReport.js";
import ScheduleAppointment from "./ScheduleAppointment.js";
import MedicineReservation from "./MedicineReservation.js";
import Examinations from "./Examinations.js";
import HomePage from "./HomePage.js";

const BasicPage = () => {
  return (
    <Router>
      <Switch>
        <div>
          <NavBar user={"pharmacist"} />
          <Route path="/pharmacist/editProfile" component={EditProfile}></Route>
          <Route
            path="/pharmacist/vacationRequest"
            component={VacationRequest}
          ></Route>
          <Route path="/pharmacist/workCalendar">
            <WorkCalendar />
          </Route>
          <Route path="/pharmacist/writeReport">
            <WriteReport />
          </Route>
          <Route path="/pharmacist/scheduleAppointment">
            <ScheduleAppointment />
          </Route>
          <Route path="/pharmacist/medicineReservations">
            <MedicineReservation />
          </Route>
          <Route path="/pharmacist/homePage">
            <HomePage />
          </Route>
          <Route path="/pharmacist/examinations">
            <Examinations />
          </Route>
        </div>
      </Switch>
    </Router>
  );
};

export default BasicPage;
