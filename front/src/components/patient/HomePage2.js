import Profile from './Profile'
import HomePage from './HomePage'
import NavBar from "../other/NavBar.js";
import { BrowserRouter as Router, Route, Switch,useHistory,useLocation } from "react-router-dom";
import pharmacy from "../../images/pharmay.jpg"
import medicine from "../../images/medicine.jpg"
import appointments from "../../images/appointment.jpg"
import ePrescription from "../../images/eprescription.png"
import rating from "../../images/rating.jpg"
import complaint from "../../images/complaint.png"




import background from "../../images/doctor.jpg";





import React from 'react';
import { makeStyles } from '@material-ui/core/styles';
import ButtonBase from '@material-ui/core/ButtonBase';
import Typography from '@material-ui/core/Typography';

const images = [
  {
    url: pharmacy,
    title: 'Pharmacy',
    width: '20%',
    
  },
  {
    url:medicine,
    title: 'Medicines',
    width: '20%',
  },
  {
    url: appointments,
    title: 'Appointments',
    width: '20%',
  },
];

const images2 = [
    {
      url: ePrescription,
      title: 'E-Prescriptions',
      width: '20%',
      
    },
    {
      url: rating,
      title: 'Rating',
      width: '20%',
    },
    {
      url : complaint,
      title: 'Complaint',
      width: '20%',
    },
  ];

  const useStyles = makeStyles((theme) => ({
    image: {
      position: "relative",
      height: 200,
      [theme.breakpoints.down("xs")]: {
        width: "100% !important",
        height: 100,
      },
      "&:hover, &$focusVisible": {
        zIndex: 1,
        "& $imageBackdrop": {
          opacity: 0.6,
        },
        "& $imageMarked": {
          opacity: 0,
        },
        "& $imageTitle": {
          border: "0px solid currentColor",
        },
      },
    },
    focusVisible: {},
    imageButton: {
      position: "absolute",
      left: 0,
      right: 0,
      top: 0,
      bottom: 0,
      display: "flex",
      alignItems: "center",
      justifyContent: "center",
      color: theme.palette.common.white,
    },
    imageSrc: {
      position: "absolute",
      left: 0,
      right: 0,
      top: 0,
      bottom: 0,
      backgroundSize: "cover",
      backgroundPosition: "center 40%",
    },
    imageBackdrop: {
      position: "absolute",
      left: 0,
      right: 0,
      top: 0,
      bottom: 0,
      backgroundColor: theme.palette.common.black,
      opacity: 0.2,
      transition: theme.transitions.create("opacity"),
    },
    imageTitle: {
      position: "relative",
      padding: `${theme.spacing(2)}px ${theme.spacing(4)}px ${
        theme.spacing(1) + 6
      }px`,
    },
    imageMarked: {
      height: 3,
      width: 18,
      backgroundColor: theme.palette.common.white,
      position: "absolute",
      bottom: -2,
      left: "calc(50% - 9px)",
      transition: theme.transitions.create("opacity"),
    },
  }));
const HomePage2 = () => {



    const classes = useStyles();
    let history = useHistory();
    
    const HandleClickButton = (title) => {

        if(title === 'Pharmacy'){
            history.push('/patient/pharmacies')
        }
        else if(title === 'Medicines'){
            history.push('/patient/medicines')
        }
        else if(title === 'E-Prescriptions'){
            history.push('/patient/ePrescriptionList')
        }else if(title === 'Rating'){
            history.push('/patient/rating')
        }else if(title === 'Appointments'){
            history.push('/patient/appointments')
        }else if(title === 'Complaint'){
          history.push('/patient/complaint')
      }
    }

    return (
<>
<div className={classes.root} style={{backgroundImage: `url(${background})` , height: "753px",
      backgroundSize: "cover",
      backgroundRepeat: "no-repeat",
      backgroundPosition: "center",}}>
 
    {images.map((image) => (
        <ButtonBase
          focusRipple
          key={image.title}
          className={classes.image}
          focusVisibleClassName={classes.focusVisible}
          style={{
            width: image.width, marginRight: '5%',marginLeft : '8%',marginTop:'10%'
          }}

          onClick={() => HandleClickButton(image.title)}
        >
          <span
            className={classes.imageSrc}
            style={{
              backgroundImage: `url(${image.url})`,
            }}
          />
          <span className={classes.imageBackdrop} />
          <span className={classes.imageButton}>
            <Typography
               component="span"
               variant="h4"
               color="initial"
              className={classes.imageTitle}
            >
              {image.title}
              <span className={classes.imageMarked} />
            </Typography>
          </span>
        </ButtonBase>
      ))}

{images2.map((image) => (
        <ButtonBase
          focusRipple
          key={image.title}
          className={classes.image}
          focusVisibleClassName={classes.focusVisible}
          style={{
            width: image.width, marginRight: '5%',marginLeft : '8%',marginTop:'10%'
          }}
          onClick={() => HandleClickButton(image.title)}
        >
          <span
            className={classes.imageSrc}
            style={{
              backgroundImage: `url(${image.url})`,
            }}
          />
          <span className={classes.imageBackdrop} />
          <span className={classes.imageButton}>
            <Typography
              component="span"
              variant="h4"
              color="initial"
              className={classes.imageTitle}
            >
              {image.title}
              <span className={classes.imageMarked} />
            </Typography>
          </span>
        </ButtonBase>
      ))}


               
            </div>
    </>
  
    )
}

export default HomePage2
