import NavBar from "../other/NavBar.js";
import { BrowserRouter as Router, Route, Switch } from "react-router-dom";
import EditProfile from "./EditProfile.js";
import VacationRequest from "./VacationRequest.js";

const HomePage = () => {
  return (
    <Router>
      <Switch>
        <div>
          <NavBar user={"dermatologist"} />
          <h1>Dermatologist</h1>
          <Route
            path="/dermatologist/editProfile"
            component={EditProfile}
          ></Route>
          <Route
            path="/dermatologist/vacationRequest"
            component={VacationRequest}
          ></Route>
        </div>
      </Switch>
    </Router>
  );
};

export default HomePage;
