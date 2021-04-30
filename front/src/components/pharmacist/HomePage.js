import NavBar from "../other/NavBar.js";
import { BrowserRouter as Router, Route, Switch } from "react-router-dom";
import Appointment from "./Appointment.js";
import EditProfile from "./EditProfile.js";

const HomePage = () => {
  const currentURL = window.location.href.split("/");

  return (
    <Router>
      <Switch>
        <div>
          <NavBar user={"pharmacist"} />
          {currentURL.length < 5 && (
            <a href="/pharmacist/appointment">
              <button>View all appointments</button>
            </a>
          )}

          <Route path="/pharmacist/appointment" component={Appointment}></Route>
          <Route path="/pharmacist/editProfile" component={EditProfile}></Route>
        </div>
      </Switch>
    </Router>
  );
};

export default HomePage;
