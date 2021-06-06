import { BrowserRouter as Router, Route, Switch,useHistory,useLocation } from "react-router-dom";



import React from 'react';
import { makeStyles } from '@material-ui/core/styles';
import ButtonBase from '@material-ui/core/ButtonBase';
import Typography from '@material-ui/core/Typography';
import background from "../../images/doctor.jpg";

const images = [
  {
    url: 'https://i1.wp.com/konsultanpendidikan.com/wp-content/uploads/2019/04/info-lengkap-kuliah-jurusan-pharmacy-and-pharmacology-di-uk.jpg?fit=626%2C417&ssl=1',
    title: 'Pharmacy',
    width: '20%',
    
  },
  {
    url: 'data:image/jpeg;base64,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',
    title: 'Medicines',
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
const StartPage = () => {



    const classes = useStyles();
    let history = useHistory();
    
    const HandleClickButton = (title) => {

        if(title === 'Pharmacy'){
            history.push('/pharmacies')
        }
        else if(title === 'Medicines'){
            history.push('/medicines')
        }
        else if(title === 'E-Prescriptions'){
            history.push('/patient/ePrescriptionList')
        }else if(title === 'Rating'){
            history.push('/patient/rating')
        }else if(title === 'Appointments'){
            history.push('/patient/appointments')
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
      
            </div>
    </>
  
    )
}

export default StartPage
