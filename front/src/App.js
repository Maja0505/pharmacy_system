import "./App.css";
import "./components/css/Button.css";
import "./components/css/Header.css";
import React, { useState, useEffect } from 'react';
 
import { BrowserRouter as Router, Route, Switch } from "react-router-dom";
import Login from "./components/other/LoginPage";
import PatientHomePage from "./components/patient/BasicPage";
import DermatologistHomePage from "./components/dermatologist/BasicPage";
import PharmacistHomePage from "./components/pharmacist/BasicPage";
import SupplierHomePage from "./components/supplier/HomePage";
import SystemAdminHomePage from "./components/systemAdmin/HomePage";
import RegistrationPage from "./components/other/RegistrationPage";
import ConfirmationPage from "./components/other/ConfirmationPage"
import Profile from './components/patient/Profile'
import Start from './components/other/StartPage'
import Pharmacies from "./components/patient/Pharmacies"
import Medicines from "./components/other/Medicine";
import NavBar from "./components/other/NavBar.js";

function App() {
  const [windowWidth, setWindowWidth] = useState(window.innerWidth);
  const userId = localStorage.getItem("userId");

  useEffect(() => {
      const handleWindowResize = () => {
          setWindowWidth(window.innerWidth);
      };
      
      window.addEventListener('resize', handleWindowResize);

      return () => {
          window.removeEventListener('resize', handleWindowResize);
      }
  }, []);

  return (
    <div className="App">
      
      <Router >
        <div>{
          (userId === undefined || userId === null) &&
        <NavBar user="unregistred" />
                    
          }
<Switch>
            <Route exact path="/" component={Start}></Route>
            <Route exact path="/pharmacies" component={Pharmacies}></Route>
            <Route exact path="/medicines" component={Medicines}></Route>

            <Route exact path="/login" component={Login}></Route>
            <Route
              exact
              path="/confirmAccount/:token"
              component={ConfirmationPage}
            ></Route>
            <Route path="/patient" component={PatientHomePage}></Route>
            <Route
              path="/dermatologist"
              component={DermatologistHomePage}
            ></Route>
            <Route path="/pharmacist" component={PharmacistHomePage}></Route>
            <Route path="/supplier" component={SupplierHomePage}></Route>
            <Route path="/system-admin" component={SystemAdminHomePage}></Route>
            <Route path="/registerPatient" component={RegistrationPage}></Route>
            <Route
                path="/profile"
                ><Profile isRefresh = {true}/></Route>
          </Switch>
        </div>
      </Router>
    </div>
  )
}

export default App;
