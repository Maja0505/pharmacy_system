import {
  Table,
  TableBody,
  TableCell,
  TableHead,
  TableRow,
  Grid,
  TextField,
  Button,
  Typography,
  TableContainer,
} from "@material-ui/core";

import { Link } from "react-router-dom";
import { Redirect } from "react-router-dom";

import { useState, useEffect } from "react";
import { makeStyles } from "@material-ui/core/styles";
import { URL } from "../other/components";

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
    position: "sticky",
    top: 0,
    background: "#4051bf",
  },
  icons: {
    cursor: "pointer",
  },
}));

const FutureExaminations = () => {
  const token = localStorage.getItem("token");
  const userId = localStorage.getItem("userId");
  const [redirection, setRedirection] = useState(false);
  const classes = useStyles();

  const [data, setData] = useState([]);
  const [firstAndLastName, setFirstAndLastName] = useState("");
  const [emptyTable, setEmptyTable] = useState(false);

  const [darmatologistDoesNotHave, setDarmatologistDoesNotHave] =
    useState(false);

  useEffect(() => {
    axios
      .get(
        URL +
          "/api/dermatologistAppointment/allFutureReserveByDermatologist/" +
          userId,
        {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        }
      )
      .then((res) => {
        if (res.data.length === 0) {
          setEmptyTable(true);
          setDarmatologistDoesNotHave(true);
        } else {
          setEmptyTable(false);
          setData(res.data);
        }
      })
      .catch((error) => {
        if (error.response.status === 401) {
          setRedirection(true);
        }
      });
  }, []);

  const changeFirstAndLastNameOfSearchPatient = (text) => {
    setFirstAndLastName(text);
  };

  const searchByFirstAndLastName = async () => {
    let first_lastName = firstAndLastName.trim().split(" ");
    if (first_lastName.length === 2) {
      axios
        .get(
          URL +
            "/api/dermatologistAppointment/searchAllFutureReservedByPatient/" +
            userId +
            "/" +
            first_lastName[0] +
            "/" +
            first_lastName[1],
          {
            headers: {
              Authorization: `Bearer ${token}`,
            },
          }
        )
        .then((res) => {
          if (res.data.length === 0) {
            setData([]);
            setEmptyTable(true);
          } else {
            setEmptyTable(false);
            setData(res.data);
          }
        })
        .catch((error) => {
          if (error.response.status === 401) {
            setRedirection(true);
          }
        });
    } else {
      alert("Invalid format of input type.\nValid (example) is : `Pera Peric`");
    }
  };

  const showAll = () => {
    axios
      .get(
        URL +
          "/api/dermatologistAppointment/allFutureReserveByDermatologist/" +
          userId,
        {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        }
      )
      .then((res) => {
        if (res.data.length === 0) {
          setEmptyTable(true);
          setData([]);
        } else {
          setEmptyTable(false);
          setData(res.data);
        }
      })
      .catch((error) => {
        if (error.response.status === 401) {
          setRedirection(true);
        }
      });
  };

  const setToMissed = (appointment) => {
    axios
      .put(
        URL +
          "/api/dermatologistAppointment/changeStatusToMissed/" +
          appointment.id,
        {},
        {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        }
      )
      .then((res) => {
        setData(
          data.map((a) => (a.id === appointment.id ? { ...a, colorId: 1 } : a))
        );
      })
      .catch((error) => {
        if (error.response.status === 401) {
          setRedirection(true);
        }

        alert(
          "Appointment not start yet.\nYou can only set status missed for appointment which is started and not finished yet!"
        );
      });
  };

  const writeReport = (appointment) => {
    localStorage.setItem(
      "PatientForDermatologistReport",
      JSON.stringify({
        Id: appointment.patientId,
        FirstName: appointment.subject.split(" ")[0],
        LastName: appointment.subject.split(" ")[1],
        AppointmentId: appointment.id,
        Email: appointment.patientEmail,
        PhoneNumber: appointment.patientPhoneNumber,
        PharmacyId: appointment.pharmacyId,
        PharmacyName: appointment.pharmacyName,
        PharamcyLocation: appointment.location,
        AppointmentStartTime: appointment.dermatologistAppointmentStartTime,
        AppointmentEndTime: appointment.dermatologistAppointmentEndTime,
      })
    );
  };

  const EmptyTable = (
    <Grid container style={{ marginLeft: "25%", marginTop: "2%" }}>
      <Grid item xs={6}>
        {darmatologistDoesNotHave === false &&
          firstAndLastName.trim() !== "" && (
            <Typography>
              Searched patient doesn't have future examinations.
            </Typography>
          )}
        {darmatologistDoesNotHave === true && (
          <Typography>
            Dermatologist doesn't have any future examinations.
          </Typography>
        )}
      </Grid>
    </Grid>
  );

  const TableHeader = (
    <TableHead>
      <TableRow className={classes.hederRow}>
        <TableCell className={classes.hederCell}>First Name</TableCell>
        <TableCell className={classes.hederCell}>Last Name</TableCell>
        <TableCell className={classes.hederCell}>Email</TableCell>
        <TableCell className={classes.hederCell}>Start date</TableCell>
        <TableCell
          className={classes.hederCell}
          style={{ textAlign: "center" }}
        >
          Report
        </TableCell>
      </TableRow>
    </TableHead>
  );

  const TableContent = (
    <TableBody>
      {data.map((row, index) => (
        <TableRow key={index}>
          <TableCell>{row.patientFirstName}</TableCell>
          <TableCell>{row.patientLastName}</TableCell>
          <TableCell>{row.patientEmail}</TableCell>
          <TableCell>
            {row.dermatologistAppointmentStartTime.split("T")[0].split("-")[2] +
              "." +
              row.dermatologistAppointmentStartTime
                .split("T")[0]
                .split("-")[1] +
              "." +
              row.dermatologistAppointmentStartTime
                .split("T")[0]
                .split("-")[0] +
              ". at " +
              row.dermatologistAppointmentStartTime.split("T")[1]}
          </TableCell>
          <TableCell style={{ textAlign: "center" }}>
            {row.colorId !== 1 && (
              <>
                <Button
                  variant="text"
                  color="secondary"
                  onClick={() => setToMissed(row)}
                >
                  Set to missed
                </Button>{" "}
                <Button
                  variant="contained"
                  color="primary"
                  onClick={() => writeReport(row)}
                >
                  <Link
                    to="/dermatologist/writeReport"
                    style={{ textDecoration: "none", color: "white" }}
                  >
                    Write report
                  </Link>
                </Button>{" "}
              </>
            )}
            {row.colorId === 1 && "MISSED"}
          </TableCell>
        </TableRow>
      ))}
    </TableBody>
  );

  const SearchPart = (
    <Grid container className={classes.table}>
      <Grid item xs={6} style={{ margin: "auto", textAlign: "right" }}>
        <TextField
          label="Enter first and last name of patient"
          size="small"
          variant="outlined"
          style={{ width: "75%" }}
          onChange={(e) =>
            changeFirstAndLastNameOfSearchPatient(e.target.value)
          }
        />
      </Grid>
      <Grid item xs={1}>
        <Button
          variant="contained"
          color="primary"
          disabled={firstAndLastName.trim() === ""}
          onClick={searchByFirstAndLastName}
        >
          Search
        </Button>
      </Grid>
      <Grid item xs={1}>
        <Button onClick={showAll}>Show all</Button>
      </Grid>
      <Grid item xs={2} />
    </Grid>
  );

  return (
    <div>
      {redirection === true && <Redirect to="/login"></Redirect>}
      {SearchPart}
      <Grid container>
        <Grid item xs={2} />
        <Grid item xs={8}>
          {emptyTable === false && (
            <TableContainer style={{ height: "450px", marginTop: "2%" }}>
              <Table>
                {TableHeader}
                {TableContent}
              </Table>
            </TableContainer>
          )}
        </Grid>
        <Grid item xs={2}></Grid>
      </Grid>
      {emptyTable && EmptyTable}
    </div>
  );
};

export default FutureExaminations;
