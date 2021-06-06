import Profile from './Profile'
import HomePage from './HomePage'
import HomePage2 from './HomePage2'

import NavBar from "../other/NavBar.js";
import { BrowserRouter as Router, Route, Switch } from "react-router-dom";
import Pharmacies from './Pharmacies'
import PharmacyHome from './PharmacyHome'

import DermatologistAppointmentHistory from './DermatologistAppointmentHistory'
import PharmacistAppointmentHistory from './PharmacistAppointmentHistory'

import DermatologistReservaedAppointment from './DermatologistReservaedAppointment'
import PharmacistReservedAppointment from './PharmacistReservedAppointment'
import MedicineReservations from './MedicineReservations'
import PharmacyProfilePage from './PharmacyProfilePage'
import SchedulePharmacistAppointment from './SchedulePharmacistAppointment'
import EPrescriptionList from './EPrescriptionList'
import SubscriptionPharmacy from './SubscriptionPharmacy'
import Rating from './Rating'
import Complaint from './Complaint'
import MedicineHome from './MedicineHome'
import AppointmentsHome from './AppointmentsHome'
import Medicine from './Medicine'



import React from 'react';



const BasicPage = () => {
    const userId = localStorage.getItem("userId");
    return (
    <Router>
        <Switch>
            <div>
            {(userId !== undefined && userId !== null) && <NavBar user="patient" />}
                <Route
                path="/patient/profile"
                component={Profile}
                ></Route>
                 <Route
                path="/patient/home"
                component={HomePage}
                ></Route>
                  <Route
                path="/patient/home2"
                component={HomePage2}
                ></Route>
                  <Route path='/patient/pharmacies' component={PharmacyHome}></Route>
                  <Route path='/patient/medicines' component={MedicineHome}></Route>
                  <Route path='/patient/appointments' component={AppointmentsHome}></Route>

                  <Route path='/patient/dermatologistAppointment' component={DermatologistAppointmentHistory}></Route>
                  <Route path='/patient/pharmacistAppointment' component={PharmacistAppointmentHistory}></Route>
                  <Route path='/patient/dermatologistReservedAppointment' component={DermatologistReservaedAppointment}></Route>
                  <Route path='/patient/pharmacistReservedAppointment' component={PharmacistReservedAppointment}></Route>
                  <Route path='/patient/medicineReservations' component={MedicineReservations}></Route>
                  <Route path='/patient/pharmacyProfilePage/:id' render={(props) => <PharmacyProfilePage {...props} />}></Route>
                  <Route path='/patient/schedulePharmacistAppointment' component={SchedulePharmacistAppointment}></Route>
                  <Route path='/patient/ePrescriptionList' component={EPrescriptionList}></Route>
                  <Route path='/patient/subscriptionPharmacy' component={SubscriptionPharmacy}></Route>
                  <Route path='/patient/rating' component={Rating}></Route>
                  <Route path='/patient/complaint' component={Complaint}></Route>
                  <Route path='/patient/pharmacies/all' component={Pharmacies}></Route>


            </div>
        </Switch>
    </Router>
    )
}

export default BasicPage
