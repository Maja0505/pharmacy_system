import DayPicker, { DateUtils } from "react-day-picker";
import "react-day-picker/lib/style.css";
import { useEffect, useState } from "react";
import {
  Grid,
  Radio,
  RadioGroup,
  FormControlLabel,
  FormControl,
  TextField,
  Snackbar,
  Button,
} from "@material-ui/core";
import axios from "axios";
import Alert from "@material-ui/lab/Alert";
import { URL } from "../other/components";

const VacationRequest = () => {
  const token = localStorage.getItem("token");
  const userId = localStorage.getItem("userId");

  const [disableDates, setDisableDates] = useState([
    {
      before: new Date(),
    },
  ]);

  useEffect(() => {
    getDisabledDates();
  }, []);

  const getDisabledDates = async () => {
    const res = await axios.get(
      URL + "/api/pharmacist/futureVacationRequest/" + userId,
      {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      }
    );
    res.data.map((vacationRequest) => createDisableDates(vacationRequest));
  };

  const createDisableDates = async (vacationRequest) => {
    let startYear = vacationRequest.vacationStartDate.split("-")[0];
    let startMonth = vacationRequest.vacationStartDate.split("-")[1];
    let startDay = vacationRequest.vacationStartDate.split("-")[2];

    let endYear = vacationRequest.vacationEndDate.split("-")[0];
    let endMonth = vacationRequest.vacationEndDate.split("-")[1];
    let endtDay = vacationRequest.vacationEndDate.split("-")[2];

    let startTime = new Date(startYear, startMonth - 1, startDay);
    let endTime = new Date(endYear, endMonth - 1, endtDay);
    startTime.setDate(startTime.getDate() - 1);
    endTime.setDate(endTime.getDate() + 1);

    setDisableDates((oldArray) => [
      ...oldArray,
      { after: startTime, before: endTime },
    ]);
  };

  const [from, setFrom] = useState(undefined);
  const [to, setTo] = useState(undefined);
  const [modifiers, setModifiers] = useState({
    start: from,
    end: to,
  });

  const handleDayClick = (day, isDisable = {}) => {
    if (isDisable.disabled) {
      return;
    }
    const range = DateUtils.addDayToRange(day, { from, to });
    setFrom(range.from);
    setTo(range.to);
  };

  const [typeOfVacation, setTypeOfVacation] = useState("Holiday");

  const [notes, setNotes] = useState("");

  const choseType = (e) => {
    setTypeOfVacation(e.target.value);
  };

  const changeNotes = (text) => {
    setNotes(text);
  };

  const makeDate = (date) => {
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

  const sendVacationRequest = async () => {
    var start = makeDate(from);
    var end = makeDate(to ? to : from);

    let vacationRequest = {
      vacationStartDate: start,
      vacationEndDate: end,
      vacationRequestNotes: notes,
      typeOfVacation: typeOfVacation,
      staffId: 6,
    };

    axios
      .post(URL + "/api/pharmacistVacationRequest/create", vacationRequest, {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      })
      .then((res) => {
        setOpenAlertSuccsess(true);
        getDisabledDates();
        setFrom(undefined);
        setTo(undefined);
        setNotes("");
        setTypeOfVacation("Holiday");
      })
      .catch(function (error) {
        setOpenAlertUnsuccses(true);
      });
  };

  const [openAlertSuccsess, setOpenAlertSuccsess] = useState(false);
  const [openAlertUnsuccsess, setOpenAlertUnsuccses] = useState(false);

  const alertTextSuccsess = useState("Success sent request!");
  const alertTextUnsuccsess = useState(
    "Bad request period pick the non disable dates !"
  );

  const handleCloseAlert = (event, reason) => {
    if (reason === "clickaway") {
      return;
    }
    setOpenAlertSuccsess(false);
    setOpenAlertUnsuccses(false);
  };

  return (
    <div>
      <Grid container spacing={1} style={{ marginTop: "3%" }}>
        <Grid item xs={2} />
        <Grid item xs={8}>
          <DayPicker
            className="Selectable"
            numberOfMonths={2}
            selectedDays={[from, { from, to }]}
            modifiers={modifiers}
            disabledDays={disableDates}
            onDayClick={(e, isDisable) => handleDayClick(e, isDisable)}
          />
        </Grid>
        <Grid item xs={2} />
      </Grid>

      <Grid container spacing={1}>
        <Grid item xs={2} />
        <Grid item xs={8} container spacing={1}>
          <Grid item xs={6}>
            <FormControl component="fieldset">
              <RadioGroup value={typeOfVacation} onChange={choseType}>
                <FormControlLabel
                  value="Holiday"
                  control={<Radio color="primary" />}
                  label="Holiday"
                />
                <FormControlLabel
                  value="Absence"
                  control={<Radio color="primary" />}
                  label="Absence"
                />
              </RadioGroup>
            </FormControl>
          </Grid>
          <Grid item xs={6}>
            <TextField
              id="outlined-multiline-static"
              label="Notes"
              multiline
              rows={4}
              onChange={(e) => changeNotes(e.target.value)}
              variant="outlined"
            />
          </Grid>
        </Grid>
      </Grid>

      <Grid container spacing={1} style={{ marginTop: "3%" }}>
        <Grid item xs={2} />
        <Grid item xs={8}>
          {from && (
            <Button
              variant="contained"
              color="primary"
              onClick={sendVacationRequest}
            >
              Sent request
            </Button>
          )}
        </Grid>
        <Grid item xs={2} />
      </Grid>
      <Snackbar
        open={openAlertSuccsess}
        autoHideDuration={1500}
        onClose={handleCloseAlert}
      >
        <Alert severity="success">{alertTextSuccsess}</Alert>
      </Snackbar>
      <Snackbar
        open={openAlertUnsuccsess}
        autoHideDuration={1500}
        onClose={handleCloseAlert}
      >
        <Alert severity="error">{alertTextUnsuccsess}</Alert>
      </Snackbar>
    </div>
  );
};

export default VacationRequest;
