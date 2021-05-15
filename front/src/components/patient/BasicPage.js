import React from 'react'
import Profile from './Profile'
import HomePage from './HomePage'
import NavBar from "../other/NavBar.js";
import { BrowserRouter as Router, Route, Switch } from "react-router-dom";



const BasicPage = () => {
    return (
    <Router>
        <Switch>
            <div>
                <NavBar user={"patient"} />
                <Route
                path="/patient/Profile"
                component={Profile}
                ></Route>
                 <Route
                path="/patient/HomePage"
                component={HomePage}
                ></Route>
               
            </div>
        </Switch>
    </Router>
    )
}

export default BasicPage
