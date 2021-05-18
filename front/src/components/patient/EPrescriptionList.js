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
  TextField
} from "@material-ui/core";
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
  


const EPrescriptionList = () => {

  const classes = useStyles();
  const [rows,setRows] = useState([])
  const [ePrescriptionItemList,setEPrescriptionList] = useState([])
  const [openEPrescriptionItemListDialog,setOpenEPrescriptionItemListDialog] = useState(false)
  const [selectedEPrescription, setSelectedEPrescription] = useState()

  useEffect(() => {
    axios
      .get(
        "http://localhost:8080/api/patient/1/ePrescription" 
      )
      .then((res) => {
        setRows(res.data);
      });
  }, []);

  const handleClickOpenEPrescriptionItemDialog= (ePrescription) => {
        
    axios.get( "http://localhost:8080/api/ePrescription/" + ePrescription.id + "/ePrescriptionItems").then(
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
 
  const TableHeader = (
    <TableHead>
      <TableRow className={classes.hederRow}>
        <TableCell className={classes.hederCell} >
         Id
        </TableCell>
        <TableCell className={classes.hederCell} >
         creationDate
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
          <TableCell>{row.creationDate}</TableCell>
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
            <Table>
              {TableHeader}
              {TableContent}
            </Table>
          </Grid>
          <Grid item xs={2}></Grid>
        </Grid>
        {CreateEPrescriptionItemDialog}
        </div>
    )
}

export default EPrescriptionList
