//OGRANICENJA I ALERTE

import React from 'react';
import { makeStyles } from '@material-ui/core/styles';
import CssBaseline from '@material-ui/core/CssBaseline';
import AppBar from '@material-ui/core/AppBar';
import Toolbar from '@material-ui/core/Toolbar';
import Paper from '@material-ui/core/Paper';
import Stepper from '@material-ui/core/Stepper';
import Step from '@material-ui/core/Step';
import StepLabel from '@material-ui/core/StepLabel';
import Button from '@material-ui/core/Button';
import Link from '@material-ui/core/Link';
import Typography from '@material-ui/core/Typography';
import DatePickerForSchedulePharmacistAppointment from './DatePickerForSchedulePharmacistAppointment';
import PharmacieTableWithFreePharmacist from './PharmacieTableWithFreePharmacist';
import PharmacistsTableForSchedulePharmacistAppointment from './PharmacistsTableForSchedulePharmacistAppointment'
import axios from "axios";
import Pharmacies from './Pharmacies';



const useStyles = makeStyles((theme) => ({
 
  layout: {
    width: 'auto',
    marginLeft: theme.spacing(2),
    marginRight: theme.spacing(2),
    marginTop: theme.spacing(2),
    [theme.breakpoints.up(600 + theme.spacing(2) * 2)]: {
      width: 1000,
      marginLeft: 'auto',
      marginRight: 'auto'
    },
  },
  stepper: {
    padding: theme.spacing(3, 0, 5),
  },
  buttons: {
    display: 'flex',
    justifyContent: 'flex-end',
  },
  button: {
    marginTop: theme.spacing(3),
    marginLeft: theme.spacing(1),
  },
}));





export default function Checkout() {
  const steps = ['Choose date for schedule', 'Choose pharmacy for appointment', 'Review your order'];

  const classes = useStyles();
  const [activeStep, setActiveStep] = React.useState(0);

  const handleNext = (step) => {

    switch (step) {
      case 0:
        HandleClickChooseDate()   
        break 
      case 1:
        HandleClickGetFreePharmacistsForSelectedPharmacy()
        break
      case 2:
        return HandleClickSaveReservationForSelectedPharmacist()
        break
      default:
        throw new Error('Unknown step');
    }
   
    setActiveStep(activeStep + 1);
  };

  const handleBack = () => {
    setActiveStep(activeStep - 1);
  };

  const [date,setDate] = React.useState({})
  const [time,setTime] = React.useState({})
  const [duration,setDuration] = React.useState()
  const [pharmacies,setPharmacies] = React.useState([])
  const [selectedPharmacy,setSelectedPharmacy] = React.useState()
  const [selectedPharmacist,setSelectedPharmacist] = React.useState()

  const [pharmacists,setPharmacists] = React.useState([])





  function getStepContent(step) {
    switch (step) {
      case 0:
        return <DatePickerForSchedulePharmacistAppointment date = {date} time = {time} duration = {duration} setDate = {setDate} setTime = {setTime} setDuration = {setDuration} setPharmacies = {setPharmacies} />;
      case 1:
        return <PharmacieTableWithFreePharmacist pharmacies = {pharmacies} setSelectedPharmacy = {setSelectedPharmacy}/>;
      case 2:
        return <PharmacistsTableForSchedulePharmacistAppointment pharmacists = {pharmacists} setSelectedPharmacist = {setSelectedPharmacist} />;
      default:
        throw new Error('Unknown step');
    }
  }

  const makeDate = () => {
    let year = date.toString().split(" ")[3];
    let monthString = date.toString().split(" ")[1];
    let day = date.toString().split(" ")[2];
    let month = "";

    switch (monthString) {
      case "Jan":
        month = "01";
        break;
      case "Feb":
        month = "02";
        break;
      case "Mar":
        month = "03";
        break;
      case "Apr":
        month = "04";
        break;
      case "May":
        month = "05";
        break;
      case "Jun":
        month = "06";
        break;
      case "Jul":
        month = "07";
        break;
      case "Aug":
        month = "08";
        break;
      case "Sep":
        month = "09";
        break;
      case "Oct":
        month = "10";
        break;
      case "Nov":
        month = "11";
        break;
      case "Dec":
        month = "12";
        break;
    }

    return year + "-" + month + "-" + day;
  };


  const HandleClickChooseDate = () => {
    var dateParse = makeDate(date)
    
    var timeDTO = {
      'startTime': dateParse + 'T' + time.toString().split(" ")[4],
      'duration' : duration
    }
    axios
        .put(
          "http://localhost:8080/api/pharmacy/free",timeDTO
           
        )
        .then((res) => {
          setPharmacies(res.data)
          console.log(res)
        });
  }

  const HandleClickGetFreePharmacistsForSelectedPharmacy = () => {
    var dateParse = makeDate(date)
    
    var timeDTO = {
      'startTime': dateParse + 'T' + time.toString().split(" ")[4],
      'duration' : duration
    }
    axios
        .put(
          "http://localhost:8080/api/pharmacist/free/" + selectedPharmacy.id,timeDTO
           
        )
        .then((res) => {
          setPharmacists(res.data)
        });
  }

  const HandleClickSaveReservationForSelectedPharmacist = () => {
    var dateParse = makeDate(date)
    
    var timeDTO = {
      'startTime': dateParse + 'T' + time.toString().split(" ")[4],
      'duration' : duration
    }
    axios
    .post(
      "http://localhost:8080/api/pharmacistAppointment/book/" + selectedPharmacist.id + '/1',timeDTO
       
    )
    .then((res) => {
     console.log('uspesnooo')
    });
  }

  const ValidateNextButton = (step) => {
    switch (step) {
      case 0:
        return date != undefined && time != undefined && duration != undefined
      case 1:
        return selectedPharmacy != undefined
      case 2:
        return selectedPharmacist != undefined
      default:
        throw new Error('Unknown step');
    }
  }
  return (
    <React.Fragment>
      <CssBaseline />
      <main className={classes.layout}>
       
          <Typography component="h1" variant="h4" align="center">
            Pharmacist appointment reservation form
          </Typography>
          <Stepper activeStep={activeStep} className={classes.stepper}>
            {steps.map((label) => (
              <Step key={label}>
                <StepLabel>{label}</StepLabel>
              </Step>
            ))}
          </Stepper>
          <React.Fragment>
            {activeStep === steps.length ? (
              <React.Fragment>
                <Typography variant="h5" gutterBottom>
                  Thank you for your order.
                </Typography>
                <Typography variant="subtitle1">
                  Your order number is #2001539. We have emailed your order confirmation, and will
                  send you an update when your order has shipped.
                </Typography>
              </React.Fragment>
            ) : (
              <React.Fragment>
                {getStepContent(activeStep)}
                <div className={classes.buttons}>
                  {activeStep !== 0 && (
                    <Button onClick={handleBack} className={classes.button}>
                      Back
                    </Button>
                  )}
                  <Button
                    variant="contained"
                    color="primary"
                    onClick={() => handleNext(activeStep)}
                    className={classes.button}
                    disabled = {!ValidateNextButton(activeStep)}
                  >
                    {activeStep === steps.length - 1 ? 'Place order' : 'Next'}
                  </Button>
                </div>
              </React.Fragment>
            )}
          </React.Fragment>
      </main>
    </React.Fragment>
  );
}