//SORTIRANJE
//Otkazivanje

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
  
  const PharmacistReservaedAppointment = () => {
    const classes = useStyles();
  
    const [rows, setRows] = useState([]);
  
    const [copyRows, setCopyRows] = useState({});
  
    const [currPage, setCurrPage] = useState(1);
  
    useEffect(() => {
      axios
        .get(
          "http://localhost:8080/api/patient/1/pharmacistAppointment/all/reserved/" +
          (currPage - 1).toString() +
          ""
           
        )
        .then((res) => {
          setRows(res.data);
          setCopyRows(res.data);
          console.log(res)
        });
    }, []);
  
 
    const searchPharmacistAppointment = (e) => {
      e = e.trim();
      setRows(
        copyRows.filter(
          (row) =>
            row.appointmentPrice.toString().toLowerCase().includes(e.toLowerCase()) ||
            row.pharmacistForAppointment.firstName.toLowerCase().includes(e.toLowerCase()) ||
            row.pharmacistForAppointment.lastName.toLowerCase().includes(e.toLowerCase()) ||
            row.pharmacyForPharmacistAppointment.pharmacyName.toLowerCase().includes(e.toLowerCase())
        )
      );
    };
  
    const [haveNextPage, setHaveNextPage] = useState(true);
  
    const nextPage = () => {
      axios
        .get(
          "http://localhost:8080/api/pharmacy/all/" +
            currPage.toString() +
            ""
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
          "http://localhost:8080/api/pharmacy/all/" +
            (currPage - 2).toString() +
            ""
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
          <TableCell className={classes.hederCell}>
           ID
          </TableCell>
          <TableCell className={classes.hederCell} >
           Start time
          </TableCell>
          <TableCell className={classes.hederCell} >
            Duration
          </TableCell>
          <TableCell className={classes.hederCell} >
          Dermatologist
          </TableCell>
          <TableCell className={classes.hederCell} >
          Pharmacy
          </TableCell>
          <TableCell className={classes.hederCell} >
           Appointment price
          </TableCell>
        </TableRow>
      </TableHead>
    );
  
    const TableContent = (
      <TableBody>
        {rows.map((row, index) => (
          <TableRow key={index}>
            <TableCell>{row.id}</TableCell>
            <TableCell> {row.pharmacistAppointmentStartTime.split("T")[0] +
              " " +
              row.pharmacistAppointmentStartTime.split("T")[1].split(":")[0] +
              ":" +
              row.pharmacistAppointmentStartTime.split("T")[1].split(":")[1]}</TableCell>
            <TableCell> {row.pharmacistAppointmentDuration}</TableCell>
            <TableCell>{row.pharmacistForAppointment.firstName} {row.pharmacistForAppointment.lastName}</TableCell>
            <TableCell>{row.pharmacyForPharmacistAppointment.pharmacyName}</TableCell>
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
            label="Search pharmacist appointment"
            type="search"
            size="small"
            variant="outlined"
            style={{ width: "60%" }}
            onChange={(e) => searchPharmacistAppointment(e.target.value)}
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
  
  export default PharmacistReservaedAppointment;
  