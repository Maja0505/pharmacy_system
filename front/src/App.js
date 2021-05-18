import "./App.css";
import "./components/css/Button.css";
import "./components/css/Header.css";

import { BrowserRouter as Router, Route, Switch } from "react-router-dom";
import Login from "./components/other/LoginPage";
import PatientHomePage from "./components/patient/BasicPage";
import DermatologistHomePage from "./components/dermatologist/HomePage";
import PharmacistHomePage from "./components/pharmacist/HomePage";
import SupplierHomePage from "./components/supplier/HomePage";
import SystemAdminHomePage from "./components/systemAdmin/HomePage";
import RegistrationPage from "./components/other/RegistrationPage";
import ConfirmationPage from "./components/other/ConfirmationPage"

function App() {
  return (
    <div>
      <Router>
        <div className="App">
          <Switch>
            <Route exact path="/login" component={Login}></Route>
            <Route exact path="/confirmAccount/:token" component={ConfirmationPage}></Route>
            <Route path="/patient" component={PatientHomePage}></Route>
            <Route
              path="/dermatologist"
              component={DermatologistHomePage}
            ></Route>
            <Route path="/pharmacist" component={PharmacistHomePage}></Route>
            <Route path="/supplier" component={SupplierHomePage}></Route>
            <Route path="/system-admin" component={SystemAdminHomePage}></Route>
            <Route path="/registerPatient" component={RegistrationPage}></Route>
          </Switch>
        </div>
      </Router>
    </div>
  );
}

export default App;
