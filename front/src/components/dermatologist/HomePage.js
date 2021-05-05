import NavBar from "../other/NavBar.js";
import { BrowserRouter as Router, Route, Switch } from "react-router-dom";
import EditProfile from "./EditProfile.js";

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
        </div>
      </Switch>
    </Router>
  );
};

export default HomePage;
