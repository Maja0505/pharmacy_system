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
  } from "@material-ui/core";
  import {
    NavigateNext,
    NavigateBefore,
  } from "@material-ui/icons";
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
import Typography from '@material-ui/core/Typography';
import Autocomplete from '@material-ui/lab/Autocomplete';
import DateFnsUtils from '@date-io/date-fns';
import {
    MuiPickersUtilsProvider,
    KeyboardDatePicker,
  } from '@material-ui/pickers';

import axios from "axios";
import moment from "moment";
  
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
    const [currPage, setCurrPage] = useState(1);
    const [open, setOpen] = React.useState(false);
    const [selectedDate, setSelectedDate] = React.useState();


    const handleDateChange = (date) => {
      setSelectedDate(date);
    };

    const handleSaveNewReservation = () => {
        var medicine = selectedMedicine.medicineId
        var pharmacy = selectedPharmacy.id
        var date = moment(selectedDate).format('YYYY-MM-DD')
        axios.post("http://localhost:8080/api/medicineReservation/create/1/" + medicine +"/" + pharmacy,{ dateOfTakingMedicine: date }).
        then((res) => {
            if(res.data){
                axios
                .get(
                  "http://localhost:8080/api/patient/1/medicineReservation/" +
                  (currPage - 1).toString() +
                  ""
                   
                )
                .then((res) => {
                  setRows(res.data);
                  setCopyRows(res.data);
                  handleClose()
                })
                }else{
                    console.log('nema ga')
                }
            }
        )
    }

    const HandleCancelReservation = (row) => {

        axios.put('http://localhost:8080/api/medicineReservation/cancel', row).then(
            (res)=> {
                if(res.data){
                    console.log('jej')
                    row.statusOfMedicineReservation = 'CANCELED'
                    setRows(rows.map(el=> (el.reservationId === row.reservationId ? Object.assign({}, el, {row}) : el)))
                }else{
                    console.log('njeeee')
                }
            }
        )
    }

    useEffect(() => {
      axios
        .get(
          "http://localhost:8080/api/patient/1/medicineReservation/" +
          (currPage - 1).toString() +
          ""
           
        )
        .then((res) => {
          setRows(res.data);
          setCopyRows(res.data);
        });
    }, []);

    const handleClickOpen = () => {
        setOpen(true);
        axios
        .get(
          "http://localhost:8080/api/medicine/all/short"
        )
        .then((res) => {
          setMedicines(res.data)
        });
        axios
        .get(
          "http://localhost:8080/api/pharmacy/all"
        )
        .then((res) => {
            setPharmacies(res.data)
        });
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
                <Button autoFocus onClick={handleSaveNewReservation} color="primary">
                Save changes
                </Button>
            </DialogActions>
            </Dialog>
           
        </div>
    )
  
    return (
      <div>
          {SearchPart}
        <Button variant="outlined" color="primary" onClick={handleClickOpen}>
       CREATE RESERVATION
      </Button>
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
        {CreateReservationDialog}
      </div>
    );
  };
  
  export default MedicineReservations;
  