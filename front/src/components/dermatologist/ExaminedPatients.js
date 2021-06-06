import {
  Table,
  TableBody,
  TableCell,
  TableHead,
  TableRow,
  Grid,
  TextField,
  TableContainer,
} from "@material-ui/core";
import { ArrowDropDown, ArrowDropUp } from "@material-ui/icons";
import Autocomplete from "@material-ui/lab/Autocomplete";
import { useState, useEffect } from "react";
import { makeStyles } from "@material-ui/core/styles";
import axios from "axios";
import {URL} from "../other/components"
import {Redirect} from "react-router-dom"

const useStyles = makeStyles((theme) => ({
  table: {
    marginTop: "5%",
  },
  hederRow: {
    background: "#4051bf",
  },
  hederCell: {
    cursor: "pointer",
    color: "#ffffff",
    position: "sticky",
    top: 0,
    background: "#4051bf",
  },
  icons: {
    cursor: "pointer",
  },
}));

const ExaminedPatients = () => {
  const token = localStorage.getItem("token");
  const userId = localStorage.getItem("userId");
  const [redirection,setRedirection] = useState(false)
  const [pharmacies, setPharmacies] = useState([]);

  const classes = useStyles();

  const [rows, setRows] = useState([]);

  const [copyRows, setCopyRows] = useState({});

  useEffect(() => {
    axios
      .get(URL + "/api/pharmacy/getPharmacies/" + userId, {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      })
      .then((res) => {
        setPharmacies(res.data);
        addPastAppointmentForSelectedPharmacy(res.data[0].pharmacyId);
      }).catch((error) => {
        if(error.response.status === 401){
          setRedirection(true)
        }
      });
  }, []);

  const [firstNameAsc, setFirstNameAsc] = useState({
    counter: -1,
    asc: true,
  });

  const [lastNameAsc, setLastNameAsc] = useState({
    counter: -1,
    asc: true,
  });

  const [emailAsc, setEmail] = useState({
    counter: -1,
    asc: true,
  });

  const [startTimeAsc, setStartTime] = useState({
    counter: -1,
    asc: true,
  });

  const [endTimeAsc, setEndTimeAsc] = useState({
    counter: -1,
    asc: true,
  });

  const [priceAsc, setPrice] = useState({
    counter: -1,
    asc: true,
  });

  const sortByFirstName = () => {
    setLastNameAsc({ asc: true, counter: -1 });
    setEmail({ asc: true, counter: -1 });
    setStartTime({ asc: true, counter: -1 });
    setEndTimeAsc({ asc: true, counter: -1 });
    setPrice({ asc: true, counter: -1 });

    if (firstNameAsc.asc === true) setFirstNameAsc({ counter: 0, asc: false });
    else setFirstNameAsc({ counter: 0, asc: true });

    axios
      .put(
        URL + "/api/appointment/sortByPatientFirstName/" +
          (firstNameAsc.asc ? "asc" : "desc"),
        rows,
        {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        }
      )
      .then((res) => {
        setRows(res.data);
      }).catch((error) => {
        if(error.response.status === 401){
          setRedirection(true)
        }
      });
  };

  const sortByLastName = () => {
    setFirstNameAsc({ asc: true, counter: -1 });
    setEmail({ asc: true, counter: -1 });
    setStartTime({ asc: true, counter: -1 });
    setEndTimeAsc({ asc: true, counter: -1 });
    setPrice({ asc: true, counter: -1 });

    if (lastNameAsc.asc === true) setLastNameAsc({ counter: 0, asc: false });
    else setLastNameAsc({ counter: 0, asc: true });

    axios
      .put(
        URL + "/api/appointment/sortByPatientLastName/" +
          (lastNameAsc.asc ? "asc" : "desc"),
        rows,
        {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        }
      )
      .then((res) => {
        setRows(res.data);
      }).catch((error) => {
        if(error.response.status === 401){
          setRedirection(true)
        }
      });
  };

  const sortByEmail = () => {
    setFirstNameAsc({ asc: true, counter: -1 });
    setLastNameAsc({ asc: true, counter: -1 });
    setStartTime({ asc: true, counter: -1 });
    setEndTimeAsc({ asc: true, counter: -1 });
    setPrice({ asc: true, counter: -1 });

    if (emailAsc.asc === true) setEmail({ counter: 0, asc: false });
    else setEmail({ counter: 0, asc: true });

    axios
      .put(
        URL + "/api/appointment/sortByPatientEmail/" +
          (emailAsc.asc ? "asc" : "desc"),
        rows,
        {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        }
      )
      .then((res) => {
        setRows(res.data);
      }).catch((error) => {
        if(error.response.status === 401){
          setRedirection(true)
        }
      });
  };

  const sortByStartTime = () => {
    setFirstNameAsc({ asc: true, counter: -1 });
    setLastNameAsc({ asc: true, counter: -1 });
    setEmail({ asc: true, counter: -1 });
    setEndTimeAsc({ asc: true, counter: -1 });
    setPrice({ asc: true, counter: -1 });

    if (startTimeAsc.asc === true) setStartTime({ counter: 0, asc: false });
    else setStartTime({ counter: 0, asc: true });

    axios
      .put(
        URL + "/api/appointment/sortByAppointmentStartTime/" +
          (startTimeAsc.asc ? "asc" : "desc"),
        rows,
        {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        }
      )
      .then((res) => {
        setRows(res.data);
      }).catch((error) => {
        if(error.response.status === 401){
          setRedirection(true)
        }
      });
  };

  const sortByEndTime = () => {
    setFirstNameAsc({ asc: true, counter: -1 });
    setLastNameAsc({ asc: true, counter: -1 });
    setEmail({ asc: true, counter: -1 });
    setStartTime({ asc: true, counter: -1 });
    setPrice({ asc: true, counter: -1 });

    if (endTimeAsc.asc === true) setEndTimeAsc({ counter: 0, asc: false });
    else setEndTimeAsc({ counter: 0, asc: true });

    axios
      .put(
        URL + "/api/dermatologistAppointment/sortByAppointmentEndTime/" +
          (endTimeAsc.asc ? "asc" : "desc"),
        rows,
        {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        }
      )
      .then((res) => {
        setRows(res.data);
      }).catch((error) => {
        if(error.response.status === 401){
          setRedirection(true)
        }
      });
  };

  const sortByPrice = () => {
    setFirstNameAsc({ asc: true, counter: -1 });
    setLastNameAsc({ asc: true, counter: -1 });
    setEmail({ asc: true, counter: -1 });
    setStartTime({ asc: true, counter: -1 });
    setEndTimeAsc({ asc: true, counter: -1 });

    if (priceAsc.asc === true) setPrice({ counter: 0, asc: false });
    else setPrice({ counter: 0, asc: true });

    axios
      .put(
        URL + "/api/appointment/sortByAppointmentPrice/" +
          (priceAsc.asc ? "asc" : "desc"),
        rows,
        {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        }
      )
      .then((res) => {
        setRows(res.data);
      }).catch((error) => {
        if(error.response.status === 401){
          setRedirection(true)
        }
      });
  };

  const serchPatient = (e) => {
    e = e.trim();
    setRows(
      copyRows.filter(
        (row) =>
          row.patientFirstName.toLowerCase().includes(e.toLowerCase()) ||
          row.patientLastName.toLowerCase().includes(e.toLowerCase()) ||
          row.patientEmail.toLowerCase().includes(e.toLowerCase()) ||
          e.toLowerCase().includes(row.patientFirstName.toLowerCase()) ||
          e.toLowerCase().includes(row.patientLastName.toLowerCase()) ||
          e.toLowerCase().includes(row.patientEmail.toLowerCase())
      )
    );
  };

  const [haveNextPage, setHaveNextPage] = useState(true);

  const addPastAppointmentForSelectedPharmacy = (pharmacyId) => {
    axios
      .get(
        URL + "/api/dermatologistAppointment/allPastAppointmentByDermatologistAndPharmacy/" +
          userId +
          "/" +
          pharmacyId,
        {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        }
      )
      .then((res) => {
        setRows(res.data);
        setCopyRows(res.data);
      }).catch((error) => {
        if(error.response.status === 401){
          setRedirection(true)
        }
      });
  };

  const handleChangePharmacy = async (pharmacy) => {
    setRows([]);
    setCopyRows([]);
    setFirstNameAsc({ asc: true, counter: -1 });
    setLastNameAsc({ asc: true, counter: -1 });
    setEmail({ asc: true, counter: -1 });
    setStartTime({ asc: true, counter: -1 });
    setEndTimeAsc({ asc: true, counter: -1 });
    setPrice({ asc: true, counter: -1 });
    addPastAppointmentForSelectedPharmacy(pharmacy.pharmacyId);
  };

  const TableHeader = (
    <TableHead>
      <TableRow className={classes.hederRow}>
        <TableCell className={classes.hederCell} onClick={sortByFirstName}>
          First Name{" "}
          {firstNameAsc.asc && firstNameAsc.counter !== -1 && <ArrowDropDown />}{" "}
          {!firstNameAsc.asc && firstNameAsc.counter !== -1 && <ArrowDropUp />}
        </TableCell>
        <TableCell className={classes.hederCell} onClick={sortByLastName}>
          Last Name{" "}
          {lastNameAsc.asc && lastNameAsc.counter !== -1 && <ArrowDropDown />}{" "}
          {!lastNameAsc.asc && lastNameAsc.counter !== -1 && <ArrowDropUp />}
        </TableCell>
        <TableCell className={classes.hederCell} onClick={sortByEmail}>
          Email {emailAsc.asc && emailAsc.counter !== -1 && <ArrowDropDown />}{" "}
          {!emailAsc.asc && emailAsc.counter !== -1 && <ArrowDropUp />}
        </TableCell>
        <TableCell className={classes.hederCell} onClick={sortByStartTime}>
          Start Time{" "}
          {startTimeAsc.asc && startTimeAsc.counter !== -1 && <ArrowDropDown />}{" "}
          {!startTimeAsc.asc && startTimeAsc.counter !== -1 && <ArrowDropUp />}
        </TableCell>
        <TableCell className={classes.hederCell} onClick={sortByEndTime}>
          End Time{" "}
          {endTimeAsc.asc && endTimeAsc.counter !== -1 && <ArrowDropDown />}{" "}
          {!endTimeAsc.asc && endTimeAsc.counter !== -1 && <ArrowDropUp />}
        </TableCell>
        <TableCell className={classes.hederCell} onClick={sortByPrice}>
          Price ($)
          {priceAsc.asc && priceAsc.counter !== -1 && <ArrowDropDown />}{" "}
          {!priceAsc.asc && priceAsc.counter !== -1 && <ArrowDropUp />}
        </TableCell>
      </TableRow>
    </TableHead>
  );

  const TableContent = (
    <TableBody>
      {rows.map((row, index) => (
        <TableRow key={index}>
          <TableCell>{row.patientFirstName}</TableCell>
          <TableCell>{row.patientLastName}</TableCell>
          <TableCell>{row.patientEmail}</TableCell>
          <TableCell>
            {row.appointmentStartTime.split("T")[0] +
              " " +
              row.appointmentStartTime.split("T")[1].split(":")[0] +
              ":" +
              row.appointmentStartTime.split("T")[1].split(":")[1]}
          </TableCell>
          <TableCell>
            {row.appointmentEndTime.split("T")[0] +
              " " +
              row.appointmentEndTime.split("T")[1].split(":")[0] +
              ":" +
              row.appointmentEndTime.split("T")[1].split(":")[1]}
          </TableCell>
          <TableCell>{row.appointmentPrice}</TableCell>
        </TableRow>
      ))}
    </TableBody>
  );

  const SearchPart = (
    <Grid container className={classes.table}>
      <Grid item xs={2}></Grid>
      <Grid item xs={2}>
        {pharmacies.length !== 0 && (
          <Autocomplete
            id="controllable-states-demo"
            size="small"
            options={pharmacies}
            getOptionLabel={(option) => option.pharmacyName}
            defaultValue={pharmacies.find((v) => v.pharmacyName[0])}
            disableClearable
            onChange={(event, value) => handleChangePharmacy(value)}
            renderInput={(params) => (
              <TextField {...params} label="Pharmacy" variant="outlined" />
            )}
          />
        )}
      </Grid>
      <Grid item xs={6} style={{ margin: "auto", textAlign: "right" }}>
        <TextField
          id="outlined-search"
          label="Search patient by first name/last name/email"
          type="search"
          size="small"
          variant="outlined"
          style={{ width: "60%" }}
          onChange={(e) => serchPatient(e.target.value)}
        />
      </Grid>
      <Grid item xs={2}></Grid>
      <Grid item xs={2} />
    </Grid>
  );

  return (
    <div>
      {SearchPart}
      <Grid container spacing={1}>
        <Grid item xs={2} />
        <Grid item xs={8}>
          <TableContainer style={{ height: "500px", marginTop: "2%" }}>
            <Table>
              {TableHeader}
              {TableContent}
            </Table>
          </TableContainer>
        </Grid>
        <Grid item xs={2}></Grid>
      </Grid>
    </div>
  );
};

export default ExaminedPatients;
