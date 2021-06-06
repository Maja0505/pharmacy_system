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

import { useState, useEffect } from "react";
import { makeStyles } from "@material-ui/core/styles";

import axios from "axios";
import { URL } from "../other/components";

const useStyles = makeStyles((theme) => ({
  table: {
    marginTop: "3%",
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

  const classes = useStyles();

  const [data, setData] = useState([]);
  const [firstAndLastName, setFirstAndLastName] = useState("");
  const [emptyTable, setEmptyTable] = useState(false);

  const [pharmacistDoesNotHave, setPharmacistDoesNotHave] = useState(false);

  useEffect(() => {
    axios
      .get(URL + "/api/pharmacistAppointment/allFutureReserved/" + userId, {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      })
      .then((res) => {
        if (res.data.length === 0) {
          setEmptyTable(true);
          setPharmacistDoesNotHave(true);
        } else {
          setEmptyTable(false);
          setData(res.data);
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
            "/api/pharmacistAppointment/searchAllFutureReservedByPatient/" +
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
        });
    } else {
      alert("Invalid format of input type.\nValid (example) is : `Pera Peric`");
    }
  };

  const showAll = () => {
    axios
      .get(URL + "/api/pharmacistAppointment/allFutureReserved/" + userId, {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      })
      .then((res) => {
        if (res.data.length === 0) {
          setEmptyTable(true);
          setData([]);
        } else {
          setEmptyTable(false);
          setData(res.data);
        }
      });
  };

  const setToMissed = (appointment) => {
    axios
      .put(
        URL +
          "/api/pharmacistAppointment/changeStatusToMissed/" +
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
        alert(
          "Appointment not start yet.\nYou can only set status missed for appointment which is started and not finished yet!"
        );
      });
  };

  const writeReport = (appointment) => {
    localStorage.setItem(
      "PatientForPharmacistReport",
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
        AppointmentStartTime: appointment.startTime,
        AppointmentEndTime: appointment.endTime,
      })
    );
  };

  const EmptyTable = (
    <Grid container style={{ marginLeft: "25%", marginTop: "2%" }}>
      <Grid item xs={6}>
        {pharmacistDoesNotHave === false && firstAndLastName.trim() !== "" && (
          <Typography>
            Searched patient doesn't have future examinations.
          </Typography>
        )}
        {pharmacistDoesNotHave === true && (
          <Typography>
            Pharmacist doesn't have any future examinations.
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
            {row.startTime.split("T")[0].split("-")[2] +
              "." +
              row.startTime.split("T")[0].split("-")[1] +
              "." +
              row.startTime.split("T")[0].split("-")[0] +
              ". at " +
              row.startTime.split("T")[1]}
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
                    to="/pharmacist/writeReport"
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
