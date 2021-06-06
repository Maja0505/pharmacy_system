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
    
  const PharmacistAppointmentHistory = () => {


    const classes = useStyles();
  
    const [rows, setRows] = useState([]);
  
    const [copyRows, setCopyRows] = useState({});
  
    const [currPage, setCurrPage] = useState(1);

    const [openPharmacyDialog, setOpenPharmacyDialog] = useState(false);
    const [openPharmacistDialog, setOpenPharmacistDialog] = useState(false);
    const [openReportDialog, setOpenReportDialog] = useState(false);
    const token = localStorage.getItem("token");
    const userId = localStorage.getItem("userId");
    const [redirection,setRedirection] = useState(false)
    const config = {
      headers: { Authorization: `Bearer ${token}` }
  };
  
    useEffect(() => {
      axios
        .get(
          URL + "/api/pharmacistReport/all/patient/" + userId,config)
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

    const [pharmacy,setPharmacy] = useState({})
    const [pharmacist,setPharmacist] = useState({})
    const [report,setReport] = useState({})

    const handleClickOpenPharmacyDialog = (pharmacyId) => {
        
        axios.get( URL + "/api/pharmacy/" + pharmacyId,config).then(
            (res)=>{
                setPharmacy(res.data)
                setOpenPharmacyDialog(true)
            }
        ).catch((error) => {
          if(error.response.status === 401){
            setRedirection(true)
          }
        });
    };
    const handleClosePharmacyDialog = () => {
        setOpenPharmacyDialog(false);
    };

    const handleClickOpenPharmacistDialog = (pharmacistId) => {
        
        axios.get( URL + "/api/pharmacist/" + pharmacistId,config).then(
            (res)=>{
                setPharmacist(res.data)
                setOpenPharmacistDialog(true)
            }
        ).catch((error) => {
          if(error.response.status === 401){
            setRedirection(true)
          }
        });
    };
    const handleClosePharmacistDialog = () => {
        setOpenPharmacistDialog(false);
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
          URL + "/api/pharmacistReport/sortByDate/" +
            (dateAsc.asc ? "asc" : "desc"),
          rows
        ,config)
        .then((res) => {
          setRows(res.data);
        }).catch((error) => {
          if(error.response.status === 401){
            setRedirection(true)
          }
        });
  };

  const sortByDuration = () => {

    setDateAsc({ asc: true, counter: -1 });
    setPriceAsc({ asc: true, counter: -1 });


    if (durationAsc.asc === true) setDurationAsc({ counter: 0, asc: false });
    else setDurationAsc({ counter: 0, asc: true });

    axios
      .put(
        URL + "/api/pharmacistReport/sortByDuration/" +
          (durationAsc.asc ? "asc" : "desc"),
        rows
      ,config)
      .then((res) => {
        setRows(res.data);
      }).catch((error) => {
        if(error.response.status === 401){
          setRedirection(true)
        }
      });
};

const sortByPrice = () => {

  setDateAsc({ asc: true, counter: -1 });
  setDurationAsc({ asc: true, counter: -1 });


  if (priceAsc.asc === true) setPriceAsc({ counter: 0, asc: false });
  else setPriceAsc({ counter: 0, asc: true });

  axios
    .put(
      URL + "/api/pharmacistReport/sortByPrice/" +
        (priceAsc.asc ? "asc" : "desc"),
      rows
    ,config)
    .then((res) => {
      setRows(res.data);
    }).catch((error) => {
      if(error.response.status === 401){
        setRedirection(true)
      }
    });
};
  
    const TableHeader = (
      <TableHead>
        <TableRow className={classes.hederRow}>
         
          <TableCell className={classes.hederCell} >
           Pharmacy
          </TableCell>
          <TableCell className={classes.hederCell} >
          pharmacist
          </TableCell>
          <TableCell className={classes.hederCell} onClick={sortByDate}>
          Start time{" "}
          {dateAsc.asc && dateAsc.counter !== -1 && <ArrowDropDown />}{" "}
          {!dateAsc.asc && dateAsc.counter !== -1 && <ArrowDropUp />}
          </TableCell>
          <TableCell className={classes.hederCell} onClick={sortByDuration}> 
          Duration(min){" "}
          {durationAsc.asc && durationAsc.counter !== -1 && <ArrowDropDown />}{" "}
          {!durationAsc.asc && durationAsc.counter !== -1 && <ArrowDropUp />}
          </TableCell>
          <TableCell className={classes.hederCell} >
          Points
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
            <TableCell onClick = {() => handleClickOpenPharmacistDialog(row.pharmacistId)}><Link>{row.pharmacistFirstName} {row.pharmacistLastName}</Link></TableCell>
         
            <TableCell> {row.pharmacistAppointmentStartTime.split("T")[0] +
              " " +
              row.pharmacistAppointmentStartTime.split("T")[1].split(":")[0] +
              ":" +
              row.pharmacistAppointmentStartTime.split("T")[1].split(":")[1]}</TableCell>
           <TableCell>{row.duration}</TableCell>
            <TableCell> {row.appointmentPoints}</TableCell>
            <TableCell> {row.appointmentPrice}</TableCell>
            <TableCell> <Button style={{backgroundColor:'gray',color:'white'}} onClick={() => handleClickOpenViewReport(row.reportInfo)}>View</Button></TableCell>
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

    const CreatePharmacistDialog = (
        <div>
            <Dialog onClose={handleClosePharmacistDialog} aria-labelledby="customized-dialog-title" open={openPharmacistDialog} fullWidth='true'
        maxWidth='sm'>
            <DialogTitle id="customized-dialog-title" onClose={handleClosePharmacistDialog}>
               pharmacist
            </DialogTitle>
            <DialogContent dividers>
                <Typography gutterBottom>
                name: {pharmacist.firstName} {pharmacist.lastName}
                </Typography>
                <Typography gutterBottom>
                phoneNumber : {pharmacist.phoneNumber}
                </Typography>
                <Typography gutterBottom>
                phoneNumber : {pharmacist.email}
                </Typography>
            </DialogContent>
            <DialogActions>
                <Button  onClick={handleClosePharmacistDialog} autoFocus color="primary">
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
      {redirection === true && <Redirect to="/login"></Redirect>}

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
        {CreatePharmacistDialog}
        {CreateReportDialog}
      </div>
    );
  };
  
  export default PharmacistAppointmentHistory;
