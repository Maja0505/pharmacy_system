//SORTIRANJE
//Otkazivanje
//NAMESTITI DA TABELA BUDE FIKSNA

import {
    Table,
    TableBody,
    TableCell,
    TableHead,
    TableRow,
    Grid,
    TextField,
    Button,
    TableContainer,
    

  } from "@material-ui/core";
import Icon from '@material-ui/core/Icon';
import { useState, useEffect } from "react";
import { makeStyles } from "@material-ui/core/styles";
import React from 'react';
import { withStyles } from '@material-ui/core/styles';
import Dialog from '@material-ui/core/Dialog';
import MuiDialogTitle from '@material-ui/core/DialogTitle';
import MuiDialogContent from '@material-ui/core/DialogContent';
import MuiDialogActions from '@material-ui/core/DialogActions';
import IconButton from '@material-ui/core/IconButton';
import CloseIcon from '@material-ui/icons/Close';
import AddCircleTwoToneIcon from '@material-ui/icons/AddCircleTwoTone';
import Typography from '@material-ui/core/Typography';
import Autocomplete from '@material-ui/lab/Autocomplete';
import DateFnsUtils from '@date-io/date-fns';
import Alert from "@material-ui/lab/Alert";
import Snackbar from "@material-ui/core/Snackbar";
import {URL} from "../other/components"
import Fab from '@material-ui/core/Fab';
import AddIcon from '@material-ui/icons/Add';
import {Redirect} from "react-router-dom"

import {
    MuiPickersUtilsProvider,
    KeyboardDatePicker,
  } from '@material-ui/pickers';

import axios from "axios";
import moment from "moment";
import setDate from "date-fns/setDate";
  
  const useStyles = makeStyles((theme) => ({
    table: {
      marginTop: "1%",
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
  
  const DialogTitle = withStyles(styles)((props) => {
    const { children, classes, onClose, ...other } = props;
    return (
      <MuiDialogTitle disableTypography className={classes.root} {...other}>
        <Typography variant="h6">{children}</Typography>
        {onClose ? (
          <IconButton aria-label="close" className={classes.closeButton} onClick={onClose}>
            <CloseIcon />
          </IconButton>
        ) : null}
      </MuiDialogTitle>
    );
  });
  
  const DialogContent = withStyles((theme) => ({
    root: {
      padding: theme.spacing(2),
    },
  }))(MuiDialogContent);
  
  const DialogActions = withStyles((theme) => ({
    root: {
      margin: 0,
      padding: theme.spacing(1),
    },
  }))(MuiDialogActions);
  
  const MedicineReservations = () => {


    const classes = useStyles();
    const [rows, setRows] = useState([]);
    const [copyRows, setCopyRows] = useState({});
    const [medicines, setMedicines] = useState([])
    const [pharmacies, setPharmacies] = useState([])
    const [selectedMedicine, setSelectedMedicine] = useState(null)
    const [selectedPharmacy, setSelectedPharmacy] = useState(null)
    const [open, setOpen] = React.useState(false);
    const [selectedDate, setSelectedDate] = React.useState();
    const [alertTextError, setAlertTextError] = useState('')
    const [openAlertError, setOpenAlertError] = useState(false)
    const [alertTextErrorPenalty, setAlertTextErrorPenalty] = useState('')
    const [openAlertErrorPenalty, setOpenAlertErrorPenalty] = useState(false)
    const [openAlertSuccess, setOpenAlertSuccess] = useState(false)
    const [alertTextSuccess, setAlertTextSuccess] = useState('')
    const [penalty,setPenalty] = useState(0)
    const token = localStorage.getItem("token");
    const userId = localStorage.getItem("userId");
    const [redirection,setRedirection] = useState(false)
    const config = {
      headers: { Authorization: `Bearer ${token}` }
  };
  
  
    const handleCloseAlertError = (event, reason) => {
      if (reason === 'clickaway') {
        return;
      }
      setOpenAlertError(false);
    };

    const handleCloseAlertErrorPenalty = (event, reason) => {
      if (reason === 'clickaway') {
        return;
      }
      setOpenAlertErrorPenalty(false);
    };
  
    const handleCloseAlertSuccess = (event, reason) => {
      if (reason === 'clickaway') {
        return;
      }
      setOpenAlertSuccess(false);
    };
  


    const handleDateChange = (date) => {
      setSelectedDate(date);
    };

    const handleSaveNewReservation = () => {
        var medicine = selectedMedicine.medicineId
        var pharmacy = selectedPharmacy.id
        var date = moment(selectedDate).format('YYYY-MM-DD')
        axios.post(URL + "/api/medicineReservation/create/" + userId + "/" + medicine +"/" + pharmacy,{ dateOfTakingMedicine: date },config).
        then((res) => {
            if(res.data){
                axios
                .get(
                  URL + "/api/patient/" + userId + "/medicineReservation"
                ,config)
                .then((res) => {
                  setRows(res.data);
                  setCopyRows(res.data);
                  handleClose()
                }).catch(error => {

                })
             
                }else{
                  handleClose()
                }
                setAlertTextSuccess('Success create medicine reservation')
                setOpenAlertSuccess(true)
                setSelectedMedicine()
                setSelectedPharmacy()
                setSelectedDate(new Date())
            }
        ).catch(error => {
          
        })
    }

    const HandleCancelReservation = (row) => {

        axios.put(URL + '/api/medicineReservation/cancel', row,config)
        .then(
            (res)=> {
             
                if(res.data){
                    row.statusOfMedicineReservation = 'CANCELED'
                    setRows(rows.map(el=> (el.reservationId === row.reservationId ? Object.assign({}, el, {row}) : el)))
                }else{
                  setAlertTextError("You cant't cancel reservation");
                  setOpenAlertError(true);
                }
            }
        ).catch(error => {

        })
    }


    useEffect(() => {
      axios
        .get(
          URL + "/api/patient/" + userId + "/medicineReservation",config)
        .then((res) => {
          setRows(res.data);
          setCopyRows(res.data);
        }).catch(error => {

        })
      axios.get(URL + '/api/patient/' + userId +'/additionalInfo',config)
        .then((res)=> {
          setPenalty(res.data.penalty)
        }) 
    }, []);

    const handleClickOpen = () => {
      if (penalty < 3){
        setOpen(true);
        axios
        .get(
          URL + "/api/medicine/all/short",config)
        .then((res) => {
          setMedicines(res.data)
        }).catch(error => {

        })
        axios
        .get(
          URL + "/api/pharmacy/all",config)
        .then((res) => {
            setPharmacies(res.data)
        }).catch(error => {

        })
      }else{
        setAlertTextErrorPenalty("You can't reserve medicine because you have more than 3 penalties")
        setOpenAlertErrorPenalty(true)
      }
      
    };
    const handleClose = () => {
        setOpen(false);
    };
  
  
    const searchReservedMedicines = (e) => {
      e = e.trim();
      setRows(
        copyRows.filter(
          (row) =>
            row.reservationId.toString().toLowerCase().includes(e.toLowerCase()) ||
            row.medicineName.toLowerCase().includes(e.toLowerCase()) ||
            row.pharmacyName.toLowerCase().includes(e.toLowerCase()) ||
            row.statusOfMedicineReservation.toLowerCase().includes(e.toLowerCase())
        )
      );
    };
  
  
    const TableHeader = (
      <TableHead>
        <TableRow className={classes.hederRow}>
          <TableCell className={classes.hederCell}>
           ID
          </TableCell>
          <TableCell className={classes.hederCell} >
            Date for taking
          </TableCell>
          <TableCell className={classes.hederCell} >
           Medicine
          </TableCell>
          <TableCell className={classes.hederCell} >
          Pharmacy
          </TableCell>
          <TableCell className={classes.hederCell} >
          Status
          </TableCell>
          <TableCell className={classes.hederCell} >
          Cancel reservation
          </TableCell>
        </TableRow>
      </TableHead>
    );
  
    const TableContent = (
      <TableBody>
        {rows.map((row, index) => (
          <TableRow key={index}>
            <TableCell>{row.reservationId}</TableCell>
            <TableCell> {row.takingDate}</TableCell>
            <TableCell> {row.medicineName}</TableCell>
            <TableCell>{row.pharmacyName}</TableCell>
            <TableCell style={{color: row.statusOfMedicineReservation === 'CREATED' ? 'green' : 'black'}}>{row.statusOfMedicineReservation}</TableCell>
            <TableCell><Button style={{backgroundColor:'red', visibility: row.statusOfMedicineReservation != 'CREATED' ? 'hidden' : 'visible'}} onClick={() => HandleCancelReservation(row)}>Cancel</Button></TableCell>
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
            label="Search reserved medicines"
            type="search"
            size="small"
            variant="outlined"
            style={{ width: "60%" }}
            onChange={(e) => searchReservedMedicines(e.target.value)}
          />
        </Grid>
        <Grid item xs={2}></Grid>
        <Grid item xs={2} />
      </Grid>
    );

    const CreateReservationDialog = (
        <div>
            <Dialog onClose={handleClose} aria-labelledby="customized-dialog-title" open={open} fullWidth='true'
        maxWidth='sm'>
            <DialogTitle id="customized-dialog-title" onClose={handleClose}>
                Medicine reservation
            </DialogTitle>
            <DialogContent dividers>
                <Typography gutterBottom>
                Choose medicine:
                </Typography>
                <Autocomplete
                  className = "autocoplete"
                  id="combo-box-demo"
                  options={medicines}
                  getOptionLabel={(option) => option.medicineName}
                  style={{ width: 200}}
                  renderInput={(params) => <TextField value={selectedMedicine} {...params} label="add medicine" variant="outlined" />}
                  onChange={(event, value) => setSelectedMedicine(value)}
                 />
                <Typography gutterBottom>
                Choose pharmacy:
                </Typography>
                <Autocomplete
                  className = "autocoplete"
                  id="combo-box-demo"
                  options={pharmacies}
                  getOptionLabel={(option) => option.pharmacyName}
                  style={{ width: 200}}
                  renderInput={(params) => <TextField value={selectedPharmacy} {...params} label="add pharmacy" variant="outlined" />}
                  onChange={(event, value) => setSelectedPharmacy(value)}
                 />
                <Typography gutterBottom>
                Choose taking date:
                </Typography>
                <MuiPickersUtilsProvider utils={DateFnsUtils}>
                    <KeyboardDatePicker
                      disablePast
                      disableToolbar
                      variant="inline"
                      format="MM/dd/yyyy"
                      margin="normal"
                      id="date-picker-inline"
                      label="Date picker inline"
                      value={selectedDate}
                      onChange={handleDateChange}
                      KeyboardButtonProps={{
                          'aria-label': 'change date',
                        }}
                    />
                </MuiPickersUtilsProvider>
               
            </DialogContent>
            <DialogActions>
                <Button disabled = {selectedMedicine == undefined || selectedPharmacy == undefined} autoFocus onClick={handleSaveNewReservation} color="primary">
                Save changes
                </Button>
            </DialogActions>
            </Dialog>
           
        </div>
    )
  
    return (
      <div>
      
       <Fab color="primary" aria-label="add" onClick={handleClickOpen}  style={{marginTop:"2%", marginLeft:"50%"}}>
        <AddIcon />
      </Fab>
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
        {CreateReservationDialog}
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
      <Snackbar open={openAlertErrorPenalty} autoHideDuration={1500} onClose={handleCloseAlertErrorPenalty}>
        <Alert severity="error">
          {alertTextErrorPenalty}
        </Alert>
      </Snackbar>
      </div>
    );
  };
  
  export default MedicineReservations;
  