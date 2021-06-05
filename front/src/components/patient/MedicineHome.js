import React, { useEffect, useState } from "react";
import Button from "@material-ui/core/Button";
import { BrowserRouter as Router, Route, Switch,Link } from "react-router-dom";
import Pharmacies from './Pharmacies'
import SubscriptionPharmacy from './SubscriptionPharmacy'
import Medicines from "./Medicine";
import MedicineReservations from './MedicineReservations'



const MedicineHome = () => {

    const [reservations,setReservation] = useState(false)
    const [MedicinesAll,setMedicines] = useState(true)


    const HandleAllClickButton = () => {
      if(!reservations){
        setReservation(true)
        setMedicines(false)
      }
    }

    const HandleFollowedClickButton = () => {
        if(!MedicinesAll){
            setReservation(false)
            setMedicines(true)
          }
      }
 

    const MedicineReservation = (
        <MedicineReservations></MedicineReservations>
    )

    const MedicinePage = (
        <Medicines></Medicines>
    )
    return (
      
             <div style={{marginTop:"5%"}}>
                <Button variant= {MedicinesAll ? "outlined" : "contained"}  color="primary" size="large" onClick={HandleFollowedClickButton}>
                All medicines
                </Button>
                <Button  variant= {reservations ? "outlined" : "contained"} size="large" color="primary"  onClick={HandleAllClickButton}>
                Medicine reservations
                </Button>

                {reservations && MedicineReservation}
                {MedicinesAll && MedicinePage}

            </div>

     
      
    )
}

export default MedicineHome
