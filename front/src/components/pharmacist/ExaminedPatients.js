import {
  Table,
  TableBody,
  TableCell,
  TableHead,
  TableRow,
  Grid,
  TextField,
} from "@material-ui/core";
import {
  ArrowDropDown,
  ArrowDropUp,
  NavigateNext,
  NavigateBefore,
} from "@material-ui/icons";
import { useState, useEffect } from "react";
import { makeStyles } from "@material-ui/core/styles";
import axios from "axios";

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
  },
  icons: {
    cursor: "pointer",
  },
}));

const ExaminedPatients = () => {
  const token = localStorage.getItem("token");
  const userId = localStorage.getItem("userId");

  const classes = useStyles();

  const [rows, setRows] = useState([]);

  const [copyRows, setCopyRows] = useState({});

  const [currPage, setCurrPage] = useState(1);

  useEffect(() => {
    axios
      .get(
        "http://localhost:8080/api/pharmacistAppointment/allPastAppointment/" +
          userId +
          "/" +
          (currPage - 1).toString() +
          "",
        {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        }
      )
      .then((res) => {
        setRows(res.data);
        setCopyRows(res.data);
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

  const [durationAsc, setDuration] = useState({
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
    setDuration({ asc: true, counter: -1 });
    setPrice({ asc: true, counter: -1 });

    if (firstNameAsc.asc === true) setFirstNameAsc({ counter: 0, asc: false });
    else setFirstNameAsc({ counter: 0, asc: true });

    axios
      .put(
        "http://localhost:8080/api/appointment/sortByPatientFirstName/" +
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
      });
  };

  const sortByLastName = () => {
    setFirstNameAsc({ asc: true, counter: -1 });
    setEmail({ asc: true, counter: -1 });
    setStartTime({ asc: true, counter: -1 });
    setDuration({ asc: true, counter: -1 });
    setPrice({ asc: true, counter: -1 });

    if (lastNameAsc.asc === true) setLastNameAsc({ counter: 0, asc: false });
    else setLastNameAsc({ counter: 0, asc: true });

    axios
      .put(
        "http://localhost:8080/api/appointment/sortByPatientLastName/" +
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
      });
  };

  const sortByEmail = () => {
    setFirstNameAsc({ asc: true, counter: -1 });
    setLastNameAsc({ asc: true, counter: -1 });
    setStartTime({ asc: true, counter: -1 });
    setDuration({ asc: true, counter: -1 });
    setPrice({ asc: true, counter: -1 });

    if (emailAsc.asc === true) setEmail({ counter: 0, asc: false });
    else setEmail({ counter: 0, asc: true });

    axios
      .put(
        "http://localhost:8080/api/appointment/sortByPatientEmail/" +
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
      });
  };

  const sortByStartTime = () => {
    setFirstNameAsc({ asc: true, counter: -1 });
    setLastNameAsc({ asc: true, counter: -1 });
    setEmail({ asc: true, counter: -1 });
    setDuration({ asc: true, counter: -1 });
    setPrice({ asc: true, counter: -1 });

    if (startTimeAsc.asc === true) setStartTime({ counter: 0, asc: false });
    else setStartTime({ counter: 0, asc: true });

    axios
      .put(
        "http://localhost:8080/api/appointment/sortByAppointmentStartTime/" +
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
      });
  };

  const sortByDuration = () => {
    setFirstNameAsc({ asc: true, counter: -1 });
    setLastNameAsc({ asc: true, counter: -1 });
    setEmail({ asc: true, counter: -1 });
    setStartTime({ asc: true, counter: -1 });
    setPrice({ asc: true, counter: -1 });

    if (durationAsc.asc === true) setDuration({ counter: 0, asc: false });
    else setDuration({ counter: 0, asc: true });

    axios
      .put(
        "http://localhost:8080/api/pharmacistAppointment/sortByAppointmentDuration/" +
          (durationAsc.asc ? "asc" : "desc"),
        rows,
        {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        }
      )
      .then((res) => {
        setRows(res.data);
      });
  };

  const sortByPrice = () => {
    setFirstNameAsc({ asc: true, counter: -1 });
    setLastNameAsc({ asc: true, counter: -1 });
    setEmail({ asc: true, counter: -1 });
    setStartTime({ asc: true, counter: -1 });
    setDuration({ asc: true, counter: -1 });

    if (priceAsc.asc === true) setPrice({ counter: 0, asc: false });
    else setPrice({ counter: 0, asc: true });

    axios
      .put(
        "http://localhost:8080/api/appointment/sortByAppointmentPrice/" +
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

  const nextPage = () => {
    axios
      .get(
        "http://localhost:8080/api/pharmacistAppointment/allPastAppointment/" +
          userId +
          "/" +
          currPage.toString() +
          "",
        {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        }
      )
      .then((res) => {
        if (res.data.length > 0) {
          setCurrPage(currPage + 1);
          setRows(res.data);
        } else {
          setHaveNextPage(false);
        }
      });
  };

  const beforePage = () => {
    axios
      .get(
        "http://localhost:8080/api/pharmacistAppointment/allPastAppointment/" +
          userId +
          "/" +
          (currPage - 2).toString() +
          "",
        {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        }
      )
      .then((res) => {
        setHaveNextPage(true);
        if (res.data.length > 0) {
          setCurrPage(currPage - 1);
          setRows(res.data);
        }
      });
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
        <TableCell className={classes.hederCell} onClick={sortByDuration}>
          Duration (min){" "}
          {durationAsc.asc && durationAsc.counter !== -1 && <ArrowDropDown />}{" "}
          {!durationAsc.asc && durationAsc.counter !== -1 && <ArrowDropUp />}
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
          <TableCell>{row.appointmentDuration}</TableCell>
          <TableCell>{row.appointmentPrice}</TableCell>
        </TableRow>
      ))}
    </TableBody>
  );

  const SearchPart = (
    <Grid container spacing={1} className={classes.table}>
      <Grid item xs={2} />
      <Grid item xs={8} style={{ margin: "auto", textAlign: "right" }}>
        <TextField
          id="outlined-search"
          label="Search patient"
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
          <Table>
            {TableHeader}
            {TableContent}
          </Table>
        </Grid>
        <Grid item xs={2}></Grid>
      </Grid>
      <Grid container spacing={1} className={classes.table}>
        <Grid item xs={2} />
        <Grid item xs={8} container spacing={1}>
          <Grid item xs={2}>
            {currPage > 1 && (
              <NavigateBefore
                className={classes.icons}
                fontSize="large"
                onClick={beforePage}
              />
            )}
          </Grid>
          <Grid item xs={8}>
            Current Page {currPage}
          </Grid>
          <Grid item xs={2}>
            {haveNextPage && (
              <NavigateNext
                className={classes.icons}
                fontSize="large"
                onClick={nextPage}
              />
            )}
          </Grid>
        </Grid>
        <Grid item xs={2} />
      </Grid>
    </div>
  );
};

export default ExaminedPatients;
