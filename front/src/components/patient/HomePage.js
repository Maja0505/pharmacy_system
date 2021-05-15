import React from 'react'
import Button from '@material-ui/core/Button';
import ButtonGroup from '@material-ui/core/ButtonGroup';
import Grid from '@material-ui/core/Grid';
import Paper from '@material-ui/core/Paper';
import { makeStyles } from '@material-ui/core/styles';
import Pharmacies from './Pharmacies'
import DermatologistAppointmentHistory from './DermatologistAppointmentHistory'
import PharmacistAppointmentHistory from './PharmacistAppointmentHistory'

import DermatologistReservaedAppointment from './DermatologistReservaedAppointment'
import PharmacistReservedAppointment from './PharmacistReservedAppointment'
import MedicineReservations from './MedicineReservations'


import { BrowserRouter as Router, Route, Link } from "react-router-dom";



const useStyles = makeStyles((theme) => ({
    root: {
      flexGrow: 1,
    },
    paper: {
      height: 700,
      width: 1200,
    },
    control: {
      padding: theme.spacing(2),
    },
  }));

const HomePage = () => {

    const [spacing, setSpacing] = React.useState(10);
    const classes = useStyles();
    const [button1, setButton1] = React.useState(false);
    const [button2, setButton2] = React.useState(false);
    const [button3, setButton3] = React.useState(false);
    const [button4, setButton4] = React.useState(false);
    const [button5, setButton5] = React.useState(false);
    const [button6, setButton6] = React.useState(false);
    const [button7, setButton7] = React.useState(false);
    const [button8, setButton8] = React.useState(false);
    const [button9, setButton9] = React.useState(false);
    const [button10, setButton10] = React.useState(false);
    const [button11, setButton11] = React.useState(false);
    const [button12, setButton12] = React.useState(false);
    const [button13, setButton13] = React.useState(false);
    const [button14, setButton14] = React.useState(false);






 
   const HandleButton1 = () => {
     setButton2(false)
     setButton3(false)
     setButton4(false)
     setButton5(false)
     setButton7(false)

     if(!button1){
       setButton1(true)
     }
   }

   const HandleButton2 = () => {
    setButton1(false)
    setButton3(false)
    setButton4(false)
    setButton5(false)
    setButton7(false)



    if(!button2){
      setButton2(true)
    }
  }

  const HandleButton3 = () => {
    setButton1(false)
    setButton2(false)
    setButton4(false)
    setButton5(false)
    setButton7(false)



    if(!button3){
      setButton3(true)
    }
  }

  const HandleButton4 = () => {
    setButton1(false)
    setButton2(false)
    setButton3(false)
    setButton5(false)
    setButton7(false)



    if(!button4){
      setButton4(true)
    }
  }

  const HandleButton5 = () => {
    setButton1(false)
    setButton2(false)
    setButton3(false)
    setButton4(false)
    setButton7(false)



    if(!button5){
      setButton5(true)
    }
  }

  const HandleButton7 = () => {
    setButton1(false)
    setButton2(false)
    setButton3(false)
    setButton4(false)
    setButton5(false)



    if(!button7){
      setButton7(true)
    }
  }

    return (  

  <Router>
    <div>     
      <Grid container className={classes.root} spacing={2}>
        <Grid item xs={12}>
          <Grid container justify="center" spacing={spacing}>
            <Grid  item>
              <ButtonGroup
                orientation="vertical"
                color="primary"
                aria-label="vertical outlined primary button group"
                style={{
                top: 80,
                left: 0,
                width: "20%",
                height: "100%",
                position:'absolute'}}
                >
                <Button component={Link} style={{backgroundColor:button1 ? "white" : '#4051bf',color:button1 ? 'black' : 'white'}} to="/patient/HomePage/pharmacies" onClick={HandleButton1}>Pharmacies</Button>
                <Button component={Link} style={{backgroundColor:button2 ? "white" : '#4051bf',color:button2 ? 'black' : 'white'}} to="/patient/HomePage/dermatologistAppointment" onClick={HandleButton2}>History of dermatologist appointment</Button>
                <Button component={Link} style={{backgroundColor:button3 ? "white" : '#4051bf',color:button3 ? 'black' : 'white'}}  to="/patient/HomePage/pharmacistAppointment" onClick={HandleButton3}>History of pharmacist appointment</Button>
                <Button component={Link} style={{backgroundColor:button4 ? "white" : '#4051bf',color:button4 ? 'black' : 'white'}}  to="/patient/HomePage/dermatologistReservedAppointment" onClick={HandleButton4}>Dermatologist reserved appointments</Button>
                <Button component={Link} style={{backgroundColor:button5 ? "white" : '#4051bf',color:button5 ? 'black' : 'white'}}  to="/patient/HomePage/pharmacistReservedAppointment" onClick={HandleButton5}>Pharmacist reserved appointments</Button>
                <Button component={Link} style={{backgroundColor:button6 ? "white" : '#4051bf',color:button6 ? 'black' : 'white'}}>E-recipe</Button>
                <Button component={Link} style={{backgroundColor:button7 ? "white" : '#4051bf',color:button7 ? 'black' : 'white'}} to="/patient/HomePage/medicineReservations" onClick={HandleButton7} >Reserved medicines</Button>
                <Button component={Link} style={{backgroundColor:button8 ? "white" : '#4051bf',color:button8 ? 'black' : 'white'}}>Penals</Button>
                <Button component={Link} style={{backgroundColor:button9 ? "white" : '#4051bf',color:button9 ? 'black' : 'white'}}>Medicines from ERecipe</Button>
                <Button component={Link}  style={{backgroundColor:button10 ? "white" : '#4051bf',color:button10 ? 'black' : 'white'}}>Followed pharmaciest</Button>
                <Button component={Link} style={{backgroundColor:button11 ? "white" : '#4051bf',color:button11 ? 'black' : 'white'}}>Pisanje Zalbe</Button>
                <Button component={Link} style={{backgroundColor:button12 ? "white" : '#4051bf',color:button12 ? 'black' : 'white'}}>Preuzimanje leka</Button>
                <Button component={Link} style={{backgroundColor:button13 ? "white" : '#4051bf',color:button13 ? 'black' : 'white'}}>Zakazivanje kod farmacueuta</Button>
                <Button component={Link} style={{backgroundColor:button14 ? "white" : '#4051bf',color:button14 ? 'black' : 'white'}}>Zakazivanje kod dermatologa</Button>
              </ButtonGroup>
              </Grid>
              <Grid  item>
                <Paper className={classes.paper}>
                  <Route path='/patient/HomePage/pharmacies' component={Pharmacies}></Route>
                  <Route path='/patient/HomePage/dermatologistAppointment' component={DermatologistAppointmentHistory}></Route>
                  <Route path='/patient/HomePage/pharmacistAppointment' component={PharmacistAppointmentHistory}></Route>
                  <Route path='/patient/HomePage/dermatologistReservedAppointment' component={DermatologistReservaedAppointment}></Route>
                  <Route path='/patient/HomePage/pharmacistReservedAppointment' component={PharmacistReservedAppointment}></Route>
                  <Route path='/patient/HomePage/medicineReservations' component={MedicineReservations}></Route>

                </Paper>
              </Grid>
          </Grid>
        </Grid>
      </Grid>
    </div>

  </Router>

    )
}

export default HomePage
