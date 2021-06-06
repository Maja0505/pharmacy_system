import React from 'react'
import { useState, useEffect } from "react";
import { TimePickerComponent, DatePickerComponent } from "@syncfusion/ej2-react-calendars";
import { ComboBoxComponent } from '@syncfusion/ej2-react-dropdowns';
import {
  Table,
  TableBody,
  TableCell,
  TableHead,
  TableRow,
  Grid,
  Button,
  Link,
  TextField,
  TableContainer
} from "@material-ui/core";
import {
  ArrowDropDown,
  ArrowDropUp,
} from "@material-ui/icons";

import { makeStyles } from "@material-ui/core/styles";
import axios from "axios";
import Dialog from '@material-ui/core/Dialog';
import MuiDialogTitle from '@material-ui/core/DialogTitle';
import MuiDialogContent from '@material-ui/core/DialogContent';
import MuiDialogActions from '@material-ui/core/DialogActions';
import IconButton from '@material-ui/core/IconButton';
import CloseIcon from '@material-ui/icons/Close';
import Typography from '@material-ui/core/Typography';
import { withStyles } from '@material-ui/core/styles';
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
  


const EPrescriptionList = () => {

  const classes = useStyles();
  const [rows,setRows] = useState([])
  const [ePrescriptionItemList,setEPrescriptionList] = useState([])
  const [openEPrescriptionItemListDialog,setOpenEPrescriptionItemListDialog] = useState(false)
  const [selectedEPrescription, setSelectedEPrescription] = useState()
  const token = localStorage.getItem("token");
  const userId = localStorage.getItem("userId");
  const [redirection,setRedirection] = useState(false)
  const config = {
    headers: { Authorization: `Bearer ${token}` }
};

  useEffect(() => {
    axios
      .get(
        URL + "/api/patient/" + userId + "/ePrescription", config)
      .then((res) => {
        setRows(res.data);
      });
  }, []);

  const handleClickOpenEPrescriptionItemDialog= (ePrescription) => {
        
    axios.get( URL + "/api/ePrescription/" + ePrescription.id + "/ePrescriptionItems",config).then(
        (res)=>{
            setEPrescriptionList(res.data)
            setOpenEPrescriptionItemListDialog(true)
            setSelectedEPrescription(ePrescription.id)
        }
    )
};
const handleClickCloseEPrescriptionItemDialog = () => {
    setOpenEPrescriptionItemListDialog(false);
    setSelectedEPrescription()

};

const [dateAsc, setDateAsc] = useState({
  counter: -1,
  asc: true,
});

const [statusAsc, setStatusAsc] = useState({
  counter: -1,
  asc: true,
});


const sortByDate = () => {

  setStatusAsc({ asc: true, counter: -1 });


  if (dateAsc.asc === true) setDateAsc({ counter: 0, asc: false });
  else setDateAsc({ counter: 0, asc: true });

  axios
    .put(
      URL + "/api/ePrescription/sortByDate/" +
        (dateAsc.asc ? "asc" : "desc"),
      rows
    ,config)
    .then((res) => {
      setRows(res.data);
    });
};

const sortByStatus = () => {

  setDateAsc({ asc: true, counter: -1 });


  if (statusAsc.asc === true) setStatusAsc({ counter: 0, asc: false });
  else setStatusAsc({ counter: 0, asc: true });

  axios
    .put(
      URL + "/api/ePrescription/sortByStatus/" +
        (statusAsc.asc ? "asc" : "desc"),
      rows
    ,config)
    .then((res) => {
      setRows(res.data);
    });
};

 
  const TableHeader = (
    <TableHead>
      <TableRow className={classes.hederRow}>
        <TableCell className={classes.hederCell} >
         Id
        </TableCell>
        <TableCell className={classes.hederCell} onClick={sortByDate}>
         Creation date{" "}
          {dateAsc.asc && dateAsc.counter !== -1 && <ArrowDropDown />}{" "}
          {!dateAsc.asc && dateAsc.counter !== -1 && <ArrowDropUp />}
          </TableCell>
        <TableCell className={classes.hederCell}  onClick={sortByStatus}>
         Status{" "}
          {statusAsc.asc && statusAsc.counter !== -1 && <ArrowDropDown />}{" "}
          {!statusAsc.asc && statusAsc.counter !== -1 && <ArrowDropUp />}
        </TableCell>
        <TableCell></TableCell>
      </TableRow>
    </TableHead>
  );

  const TableContent = (
    <TableBody>
      {rows.map((row, index) => (
        <TableRow key={index} >
          <TableCell>{row.id}</TableCell>
          <TableCell>{row.localDateTime.split("T")[0] +
              " " +
              row.localDateTime.split("T")[1].split(":")[0] +
              ":" +
              row.localDateTime.split("T")[1].split(":")[1]}</TableCell>
          <TableCell>{row.statusOfEPrescription}</TableCell>
          <TableCell><Button onClick = {() => handleClickOpenEPrescriptionItemDialog(row)}>View medicines</Button></TableCell>
        </TableRow>
      ))}
    </TableBody>
  );

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


  const CreateEPrescriptionItemDialog = (
    <div>
        <Dialog onClose={handleClickCloseEPrescriptionItemDialog} aria-labelledby="customized-dialog-title" open={openEPrescriptionItemListDialog} fullWidth='true'
      maxWidth='sm'>
          <DialogTitle id="customized-dialog-title" onClose={handleClickCloseEPrescriptionItemDialog}>
            Medicines for ePrescription with id {selectedEPrescription}
          </DialogTitle>
          <DialogContent dividers>
          {ePrescriptionItemList.map((row, index) => (
              <Typography gutterBottom>
                  {row.medicineAmount} x {row.medicineName}
              </Typography>
           ))}

          </DialogContent>
          <DialogActions>
              <Button onClick={handleClickCloseEPrescriptionItemDialog} autoFocus color="primary">
              Close
              </Button>
          </DialogActions>
        </Dialog>
    </div>
)


    return (
        <div>
            <h3>E-Prescriptions</h3>
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
        {CreateEPrescriptionItemDialog}
        </div>
    )
}

export default EPrescriptionList
