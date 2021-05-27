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
    Button,
    TableContainer
  } from "@material-ui/core";
  import {
    NavigateNext,
    NavigateBefore,
  } from "@material-ui/icons";
  import { useState, useEffect } from "react";
  import { makeStyles } from "@material-ui/core/styles";
  import axios from "axios";
  import Alert from "@material-ui/lab/Alert";
  import Snackbar from "@material-ui/core/Snackbar";
  

const styles = (theme) => ({
  root: {
    margin: 0,
    padding: theme.spacing(2),
  },
  closeButton: {
    position: 'absolute',
    right: theme.spacing(1),
    top: theme.spacing(1),
    color: theme.palette.grey[500],
    
  },
});
  
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
  
  const DermatologistReservaedAppointment = () => {

    const classes = useStyles();
    const [rows, setRows] = useState([]);
    const [copyRows, setCopyRows] = useState({});
    const [currPage, setCurrPage] = useState(1);
    const [alertTextError, setAlertTextError] = useState('')
    const [openAlertError, setOpenAlertError] = useState(false)
    const [openAlertSuccess, setOpenAlertSuccess] = useState(false)
    const [alertTextSuccess, setAlertTextSuccess] = useState('')
    const token = localStorage.getItem("token");
    const userId = localStorage.getItem("userId");
    const config = {
      headers: { Authorization: `Bearer ${token}`, consumes:'application/json' }
  };
  
    useEffect(() => {
      axios
        .get(
          "http://localhost:8080/api/patient/" + userId + "/dermatologistAppointment/all/reserved/" +
          (currPage - 1).toString() +
          "",config)
        .then((res) => {
          setRows(res.data);
          setCopyRows(res.data);
          console.log(res)
        });
    }, []);

    const handleCloseAlertError = (event, reason) => {
      if (reason === 'clickaway') {
        return;
      }
      setOpenAlertError(false);
    };
  
    const handleCloseAlertSuccess = (event, reason) => {
      if (reason === 'clickaway') {
        return;
      }
      setOpenAlertSuccess(false);
    };
  
    const searchDermatologistAppointment = (e) => {
      e = e.trim();
      setRows(
        copyRows.filter(
          (row) =>
            row.appointmentPrice.toString().toLowerCase().includes(e.toLowerCase()) ||
            row.dermatologistForAppointment.firstName.toLowerCase().includes(e.toLowerCase()) ||
            row.dermatologistForAppointment.lastName.toLowerCase().includes(e.toLowerCase()) ||
            row.pharmacyForDermatologistAppointment.pharmacyName.toLowerCase().includes(e.toLowerCase())
        )
      );
    };

    const HandleClickCancelDermatologistAppointment = (row) => {
      axios
      .put(
        "http://localhost:8080/api/dermatologistAppointment/cancel", row,config)
      .then((res) => {
        if(res.data){
          axios
          .get(
            "http://localhost:8080/api/patient/" + userId + "/dermatologistAppointment/all/reserved/" +
            (currPage - 1).toString() +
            "",config)
          .then((res) => {
            setRows(res.data);
            setCopyRows(res.data);
            console.log(res)
          });
          setAlertTextSuccess('Success cancel reservation')
          setOpenAlertSuccess(true)

        }else{
          setAlertTextError("You cant't cancel reservation");
          setOpenAlertError(true);
        }
        
      }).catch(error => {
        setAlertTextError("You cant't cancel reservation");
        setOpenAlertError(true);
      });
    }
  
  
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
            End time
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
          <TableCell></TableCell>
        </TableRow>
      </TableHead>
    );
  
    const TableContent = (
      <TableBody>
        {rows.map((row, index) => (
          <TableRow key={index}>
            <TableCell>{row.id}</TableCell>
            <TableCell> {row.dermatologistAppointmentStartTime.split("T")[0] +
              " " +
              row.dermatologistAppointmentStartTime.split("T")[1].split(":")[0] +
              ":" +
              row.dermatologistAppointmentStartTime.split("T")[1].split(":")[1]}</TableCell>
            <TableCell> {row.dermatologistAppointmentEndTime.split("T")[0] +
              " " +
              row.dermatologistAppointmentEndTime.split("T")[1].split(":")[0] +
              ":" +
              row.dermatologistAppointmentEndTime.split("T")[1].split(":")[1]}</TableCell>
            <TableCell>{row.dermatologistForAppointment.firstName} {row.dermatologistForAppointment.lastName}</TableCell>
            <TableCell>{row.pharmacyForDermatologistAppointment.pharmacyName}</TableCell>
            <TableCell>{row.appointmentPrice}</TableCell>
            <TableCell><Button onClick={() => HandleClickCancelDermatologistAppointment(row)}>Cancel</Button></TableCell>
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
            label="Search dermatologist appointment"
            type="search"
            size="small"
            variant="outlined"
            style={{ width: "60%" }}
            onChange={(e) => searchDermatologistAppointment(e.target.value)}
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
          <TableContainer style={{ height: "450px", marginTop: "2%" }}>
            <Table>
              {TableHeader}
              {TableContent}
            </Table>
          </TableContainer>
          </Grid>
          <Grid item xs={2}></Grid>
        </Grid>
        <Snackbar open={openAlertError} autoHideDuration={1500} onClose={handleCloseAlertError}>
        <Alert severity="error">
          {alertTextError}
        </Alert>
      </Snackbar>
      <Snackbar open={openAlertSuccess} autoHideDuration={1500} onClose={handleCloseAlertSuccess}>
        <Alert severity="success">
          {alertTextSuccess}
        </Alert>
      </Snackbar>
      </div>
    );
  };
  
  export default DermatologistReservaedAppointment;
  