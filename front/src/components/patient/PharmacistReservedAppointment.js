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
  
  const PharmacistReservaedAppointment = () => {
    const classes = useStyles();
  
    const [rows, setRows] = useState([]);
  
    const [copyRows, setCopyRows] = useState({});
  
    const [alertTextError, setAlertTextError] = useState('')
    const [openAlertError, setOpenAlertError] = useState(false)
    const [openAlertSuccess, setOpenAlertSuccess] = useState(false)
    const [alertTextSuccess, setAlertTextSuccess] = useState('')
    const [redirection,setRedirection] = useState(false)

    const token = localStorage.getItem("token");
    const userId = localStorage.getItem("userId");
    const config = {
      headers: { Authorization: `Bearer ${token}` }
  };
  
  
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
  
    useEffect(() => {
      axios
        .get(
          URL + "/api/patient/" + userId + "/pharmacistAppointment/all/reserved",config)
        .then((res) => {
          setRows(res.data);
          setCopyRows(res.data);
        }).catch((error) => {
          if(error.response.status === 401){
            setRedirection(true)
          }
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
  
    const HandleClickCancelPharmacistAppointment = (row) => {
      axios
      .put(
        URL + "/api/pharmacistAppointment/cancel/" + row.id,{},config
      )
      .then((res) => {
        if(res.data){
          axios
          .get(
            URL + "/api/patient/"+ userId + "/pharmacistAppointment/all/reserved" ,config)
          .then((res) => {
            setRows(res.data);
            setCopyRows(res.data);
          }).catch((error) => {
            if(error.response.status === 401){
              setRedirection(true)
            }
          })
          setAlertTextSuccess('Success cancel reservation')
          setOpenAlertSuccess(true)
        }else{
          setAlertTextError("You cant't cancel reservation");
          setOpenAlertError(true);
        }
      
      }).catch(error => {
          if(error.response.status === 401){
            setRedirection(true)
          }
        setAlertTextError("You cant't cancel reservation");
        setOpenAlertError(true);
      })
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
          <TableCell></TableCell>

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
            <TableCell><Button onClick={() => HandleClickCancelPharmacistAppointment(row)}>Cancel</Button></TableCell>
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
        {redirection === true && <Redirect to="/login"></Redirect>}
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
  
  export default PharmacistReservaedAppointment;
  