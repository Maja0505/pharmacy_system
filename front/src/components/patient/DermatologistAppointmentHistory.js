//SORTIRANJE
//Otkazivanje
import {
    Table,
    TableBody,
    TableCell,
    TableHead,
    TableRow,
    Grid,
    Button,
    Link,
    TableContainer
  } from "@material-ui/core";
  import {
    ArrowDropDown,
    ArrowDropUp,
  } from "@material-ui/icons";
import { useState, useEffect } from "react";
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
    
  const DermatologistAppointmentHistory = () => {

    const classes = useStyles();
    const [rows, setRows] = useState([]);
    const [copyRows, setCopyRows] = useState({});
    const [currPage, setCurrPage] = useState(1);
    const [openPharmacyDialog, setOpenPharmacyDialog] = useState(false);
    const [openDermatologistDialog, setOpenDermatologistDialog] = useState(false);
    const [openReportDialog, setOpenReportDialog] = useState(false);
    const [pharmacy,setPharmacy] = useState({})
    const [dermatologist,setDermatologist] = useState({})
    const [report,setReport] = useState({})
    const token = localStorage.getItem("token");
    const userId = localStorage.getItem("userId");
    const config = {
      headers: { Authorization: `Bearer ${token}` }
  };


    useEffect(() => {
      axios
        .get(
          "http://localhost:8080/api/dermatologistReport/all/patient/" + userId,config)
        .then((res) => {
          setRows(res.data);
          setCopyRows(res.data);
          console.log(res)
        });
    }, []);

    const handleClickOpenPharmacyDialog = (pharmacyId) => {
        
        axios.get( "http://localhost:8080/api/pharmacy/" + pharmacyId,config).then(
            (res)=>{
                setPharmacy(res.data)
                setOpenPharmacyDialog(true)
            }
        )
    };
    const handleClosePharmacyDialog = () => {
        setOpenPharmacyDialog(false);
    };

    const handleClickOpenDermatologistDialog = (dermatologistId) => {
        
        axios.get( "http://localhost:8080/api/dermatologist/" + dermatologistId,config).then(
            (res)=>{
                setDermatologist(res.data)
                setOpenDermatologistDialog(true)
            }
        )
    };
    const handleCloseDermatologistDialog = () => {
        setOpenDermatologistDialog(false);
    };

    const handleClickOpenViewReport = (report) => {
        setReport(report)
        setOpenReportDialog(true)
    }

    const handleClickCloseViewReport = () => {
        setOpenReportDialog(false)
    }

    const [dateAsc, setDateAsc] = useState({
      counter: -1,
      asc: true,
    });

    const [durationAsc, setDurationAsc] = useState({
      counter: -1,
      asc: true,
    });
  
    const [priceAsc, setPriceAsc] = useState({
      counter: -1,
      asc: true,
    });

    const sortByDate = () => {

      setDurationAsc({ asc: true, counter: -1 });
      setPriceAsc({ asc: true, counter: -1 });

  
      if (dateAsc.asc === true) setDateAsc({ counter: 0, asc: false });
      else setDateAsc({ counter: 0, asc: true });
  
      axios
        .put(
          "http://localhost:8080/api/dermatologistReport/sortByDate/" +
            (dateAsc.asc ? "asc" : "desc"),
          rows
        ,config)
        .then((res) => {
          setRows(res.data);
        });
  };

  const sortByDuration = () => {

    setDateAsc({ asc: true, counter: -1 });
    setPriceAsc({ asc: true, counter: -1 });


    if (durationAsc.asc === true) setDurationAsc({ counter: 0, asc: false });
    else setDurationAsc({ counter: 0, asc: true });

    axios
      .put(
        "http://localhost:8080/api/dermatologistReport/sortByDuration/" +
          (durationAsc.asc ? "asc" : "desc"),
        rows
      ,config)
      .then((res) => {
        setRows(res.data);
      });
};

const sortByPrice = () => {

  setDateAsc({ asc: true, counter: -1 });
  setDurationAsc({ asc: true, counter: -1 });


  if (priceAsc.asc === true) setPriceAsc({ counter: 0, asc: false });
  else setPriceAsc({ counter: 0, asc: true });

  axios
    .put(
      "http://localhost:8080/api/dermatologistReport/sortByPrice/" +
        (priceAsc.asc ? "asc" : "desc"),
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
           Pharmacy
          </TableCell>
          <TableCell className={classes.hederCell} >
          Dermatologist
          </TableCell>
          <TableCell className={classes.hederCell} onClick={sortByDate}>
          Start time{" "}
          {dateAsc.asc && dateAsc.counter !== -1 && <ArrowDropDown />}{" "}
          {!dateAsc.asc && dateAsc.counter !== -1 && <ArrowDropUp />}
          </TableCell>
          <TableCell className={classes.hederCell} >
         End time
          </TableCell>
          <TableCell className={classes.hederCell} >
          Points
          </TableCell>
          <TableCell className={classes.hederCell} onClick={sortByDuration}> 
          Duration(min){" "}
          {durationAsc.asc && durationAsc.counter !== -1 && <ArrowDropDown />}{" "}
          {!durationAsc.asc && durationAsc.counter !== -1 && <ArrowDropUp />}
          </TableCell>
          <TableCell className={classes.hederCell} onClick={sortByPrice} >
          Price{" "}
          {priceAsc.asc && priceAsc.counter !== -1 && <ArrowDropDown />}{" "}
          {!priceAsc.asc && priceAsc.counter !== -1 && <ArrowDropUp />}
          </TableCell>
          <TableCell className={classes.hederCell} >
          Report
          </TableCell>
        </TableRow>
      </TableHead>
    );
  
    const TableContent = (
      <TableBody>
        {rows.map((row, index) => (
          <TableRow key={index}>
            <TableCell onClick = {() => handleClickOpenPharmacyDialog(row.pharmacyId)}><Link>{row.pharmacyName}</Link></TableCell>
            <TableCell onClick = {() => handleClickOpenDermatologistDialog(row.dermatologistId)}><Link>{row.dermatologistFirstName} {row.dermatologistLastName}</Link></TableCell>
         
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
            <TableCell> {row.appointmentPoints}</TableCell>
            <TableCell> {row.durationOfAppointment}</TableCell>
            <TableCell> {row.appointmentPrice}</TableCell>
            <TableCell> <Button style={{backgroundColor:'gray',color:'white'}} onClick={() => handleClickOpenViewReport(row.reportInfo)}>View</Button></TableCell>
          </TableRow>
          
        ))}
      </TableBody>
    );
  
    const CreatePharmacyDialog = (
        <div>
            <Dialog onClose={handleClosePharmacyDialog} aria-labelledby="customized-dialog-title" open={openPharmacyDialog} fullWidth='true'
          maxWidth='sm'>
              <DialogTitle id="customized-dialog-title" onClose={handleClosePharmacyDialog}>
                Pharmacy
              </DialogTitle>
              <DialogContent dividers>
                  <Typography gutterBottom>
                  name: {pharmacy.pharmacyName}
                  </Typography>
                  <Typography gutterBottom>
                      
                  </Typography>
                  
                  <Typography gutterBottom>
                rating: {pharmacy.pharmacyAverageRating}
                  </Typography>
                
              </DialogContent>
              <DialogActions>
                  <Button onClick={handleClosePharmacyDialog} autoFocus color="primary">
                  Close
                  </Button>
              </DialogActions>
            </Dialog>
           
        </div>
    )

    const CreateDermatologistDialog = (
        <div>
            <Dialog onClose={handleCloseDermatologistDialog} aria-labelledby="customized-dialog-title" open={openDermatologistDialog} fullWidth='true'
          maxWidth='sm'>
              <DialogTitle id="customized-dialog-title" onClose={handleCloseDermatologistDialog}>
                Dermatologist
              </DialogTitle>
              <DialogContent dividers>
                  <Typography gutterBottom>
                  name: {dermatologist.firstName} {dermatologist.lastName}
                  </Typography>
                  <Typography gutterBottom>
                  phoneNumber : {dermatologist.phoneNumber}
                  </Typography>
                  <Typography gutterBottom>
                  phoneNumber : {dermatologist.email}
                  </Typography>
              </DialogContent>
              <DialogActions>
                  <Button  onClick={handleCloseDermatologistDialog} autoFocus color="primary">
                  Close
                  </Button>
              </DialogActions>
            </Dialog>
           
        </div>
    )
  

    const CreateReportDialog = (
        <div>
            <Dialog onClose={handleClickCloseViewReport} aria-labelledby="customized-dialog-title" open={openReportDialog} fullWidth='true'
          maxWidth='sm'>
              <DialogTitle id="customized-dialog-title" onClose={handleClickCloseViewReport}>
                Report
              </DialogTitle>
              <DialogContent dividers>
                  <Typography gutterBottom>
                      {report}
                  </Typography>
              </DialogContent>
              <DialogActions>
                  <Button onClick={handleClickCloseViewReport} autoFocus color="primary">
                  Close
                  </Button>
              </DialogActions>
            </Dialog>
        </div>
    )
    return (
      <div>
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
        {CreatePharmacyDialog}
        {CreateDermatologistDialog}
        {CreateReportDialog}
      </div>
    );
  };
  
  export default DermatologistAppointmentHistory;
