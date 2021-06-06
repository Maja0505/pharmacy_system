import { makeStyles } from "@material-ui/core/styles";
import ButtonBase from "@material-ui/core/ButtonBase";
import Typography from "@material-ui/core/Typography";
import MedicineReservation from "../../images/medicineReservation.jpg";
import WorkCalendar from "../../images/workCalendar.png";
import VacationRequest from "../../images/vacationRequest.png";
import Examinations from "../../images/examinations.png";
import background from "../../images/doctor.jpg";
import { Redirect } from "react-router-dom";
import { useState } from "react";
import { useHistory } from "react-router-dom";

const images = [
  {
    url: MedicineReservation,
    title: "Medicine reservations",
    width: "20%",
  },
  {
    url: WorkCalendar,
    title: "Work calendar",
    width: "20%",
  },
];

const images2 = [
  {
    url: Examinations,
    title: "Examinations",
    width: "20%",
  },
  {
    url: VacationRequest,
    title: "Vacation request",
    width: "20%",
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

const HomePage = () => {
  const classes = useStyles();
  let history = useHistory();
  const [redirection, setRedirection] = useState(false);
  const handleClickButton = (title) => {
    if (title === "Medicine reservations") {
      history.push("/pharmacist/medicineReservations");
    }
    if (title === "Work calendar") {
      history.push("/pharmacist/workCalendar");
    }
    if (title === "Vacation request") {
      history.push("/pharmacist/vacationRequest");
    }
    if (title === "Examinations") {
      history.push("/pharmacist/examinations");
    }
  };

  return (
    <div
      style={{
        backgroundImage: `url(${background})`,
        height: "753px",
        backgroundSize: "cover",
        backgroundRepeat: "no-repeat",
        backgroundPosition: "center",
      }}
    >
      {redirection === true && <Redirect to="/login"></Redirect>}
      <div>
        {images.map((image) => (
          <ButtonBase
            focusRipple
            key={image.title}
            className={classes.image}
            focusVisibleClassName={classes.focusVisible}
            style={{
              width: image.width,
              marginTop: "6%",
              marginRight: "7%",
              marginLeft: "7%",
            }}
            onClick={() => handleClickButton(image.title)}
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
      <div>
        {images2.map((image) => (
          <ButtonBase
            focusRipple
            key={image.title}
            className={classes.image}
            focusVisibleClassName={classes.focusVisible}
            style={{
              width: image.width,
              marginTop: "5%",
              marginRight: "7%",
              marginLeft: "7%",
            }}
            onClick={() => handleClickButton(image.title)}
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
    </div>
  );
};

export default HomePage;
