import React, { useEffect, useState } from "react";
import Button from "@material-ui/core/Button";
import { BrowserRouter as Router, Route, Switch,Link } from "react-router-dom";
import Pharmacies from './Pharmacies'
import SubscriptionPharmacy from './SubscriptionPharmacy'



const PharmacyHome = () => {

    const [allButton,setAllButton] = useState(true)
    const [followed,setFollowedButton] = useState(false)
    const userId = localStorage.getItem("userId");



    const HandleAllClickButton = () => {
      if(!allButton){
        setAllButton(true)
        setFollowedButton(false)
      }
    }

    const HandleFollowedClickButton = () => {
        if(!followed){
            setAllButton(false)
            setFollowedButton(true)
          }
      }
 

    const AllPharmacies = (
        <Pharmacies></Pharmacies>
    )

    const Subscription = (
        <SubscriptionPharmacy></SubscriptionPharmacy>
    )
    return (
      
             <div>
               {userId !== null && <>
                <Button  style={{marginTop:"5%"}} variant= {allButton ? "outlined" : "contained"} size="large" color="primary"  onClick={HandleAllClickButton}>
                Show all pharmacies
                </Button>
                <Button  style={{marginTop:"5%"}} variant= {followed ? "outlined" : "contained"}  color="primary" size="large" onClick={HandleFollowedClickButton}>
                My followed pharmacies
                </Button>
               </>}
                
                
                {allButton && AllPharmacies}
                {followed && Subscription}

            </div>

     
      
    )
}

export default PharmacyHome
