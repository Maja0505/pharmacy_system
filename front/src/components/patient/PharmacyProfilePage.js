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
    Link
  } from "@material-ui/core";
  import {
    NavigateNext,
    NavigateBefore,
    TramRounded,
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
import { useParams } from "react-router-dom";
import Alert from "@material-ui/lab/Alert";
import Snackbar from "@material-ui/core/Snackbar";
import { set } from "date-fns";
import {URL} from "../other/components"



  
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
    
  const PharmacyProfilePage = () => {

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
    const { id } = useParams()
    const [dermatologistAppointmentPart,setDermatologistAppointmentPart] = useState(false)
    const [dermatologistAppointment,setDermatologistAppointment] = useState([])
    const [alertTextErrorPenalty, setAlertTextErrorPenalty] = useState('')
    const [openAlertErrorPenalty, setOpenAlertErrorPenalty] = useState(false)
    const [openAlertSuccess, setOpenAlertSuccess] = useState(false)
    const [alertTextSuccess, setAlertTextSuccess] = useState('')
    const [ openAlertInfo,  setOpenAlertInfo] = useState(false)
    const [ alertTextInfo,  setAlertTextInfo] = useState('')
    const [penalty,setPenalty] = useState(0)

    const token = localStorage.getItem("token");
    const userId = localStorage.getItem("userId");
    const config = {
      headers: { Authorization: `Bearer ${token}`, consumes:'application/json' }
  };

    useEffect(() => {
      axios
        .get(
          URL + "/api/pharmacy/" + id, config)
        .then((res) => {
          setPharmacy(res.data)
        });
        axios.get(URL + '/api/patient/' + userId +'/additionalInfo',config)
        .then((res)=> {
          setPenalty(res.data.penalty)
        }) 
    }, []);

    const handleCloseAlertErrorPenalty = (event, reason) => {
      if (reason === 'clickaway') {
        return;
      }
      setOpenAlertErrorPenalty(false);
    };
  

    
    const handleCloseAlertInfo = (event, reason) => {
      if (reason === 'clickaway') {
        return;
      }
       setOpenAlertInfo(false);
    };

    const handleCloseAlertSuccess = (event, reason) => {
      if (reason === 'clickaway') {
        return;
      }
      setOpenAlertSuccess(false);
    };


    const HandleClickDermatologistAppointment = () => {
      if(!dermatologistAppointmentPart){
        axios
        .get(
          URL + "/api/dermatologistAppointment/all/open/" + id, config)
        .then((res) => {
          if(res.data.length != 0){
            setDermatologistAppointment(res.data)
            setDermatologistAppointmentPart(true)
          }else{
             setAlertTextInfo('There are no open appointments')
             setOpenAlertInfo(true)
          }
          
        });
      }else{
        setDermatologistAppointmentPart(false)
      }
    }


    const [haveNextPage, setHaveNextPage] = useState(true);
  
    const nextPage = () => {
      axios
        .get(
          URL + "/api/pharmacy/all/" +
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
          URL + "/api/pharmacy/all/" +
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
   
    const openProfile = (row) => {
      console.log(row)
    }

    const HandleClickScheduleDermatologistAppointment = (row) => {
      if(penalty < 3){
        axios
        .put(
          URL + "/api/dermatologistAppointment/book/" + row.id + "/" + id,{},config)
        .then((res) => {
          axios
          .get(
            URL + "/api/dermatologistAppointment/all/open/" + id,config)
          .then((res) => {
            setDermatologistAppointment(res.data)
            setDermatologistAppointmentPart(true)
            setAlertTextSuccess("Success reservation")
            setOpenAlertSuccess(true)
          });
        });
      }else{
        setAlertTextErrorPenalty("You can't reserve medicine because you have more than 3 penalties")
        setOpenAlertErrorPenalty(true)
      }
      
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
          <TableCell className={classes.hederCell} >
           
          </TableCell>
        </TableRow>
      </TableHead>
    );
  
    const TableContent = (
      <TableBody>
        {dermatologistAppointment.map((row, index) => (
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
            <TableCell><Button onClick={() => HandleClickScheduleDermatologistAppointment(row)}>Schedule</Button></TableCell>
          </TableRow>
        ))}
      </TableBody>
    );
    

    const scheduleDermatologistAppointment = (
      <>
      <Grid container  style={{marginTop:"3%"}}>
      <Grid item xs={2} />
      <Grid item xs={8}>
        <Table>
          {TableHeader}
          {TableContent}
        </Table>
      </Grid>
      <Grid item xs={2}></Grid>
    </Grid>
    </>
    );

    return (

      <div>
        <h2>{pharmacy.pharmacyName}</h2>
       
        <Grid container>
        <Grid item xs={2}/>
            <Grid item xs={3}>
                <Grid style={{textAlign:"right"}}><h4>Info:</h4></Grid>
                <Grid style={{textAlign:"right"}}><h4>Address:</h4></Grid>
                <Grid style={{textAlign:"right"}}><h4>Rating:</h4></Grid>
                <Grid style={{textAlign:"right"}}><h4>Dermatologist price:</h4></Grid>
                <Grid style={{textAlign:"right"}}><h4>Dermatologist price:</h4></Grid>
                <Grid></Grid>
            </Grid>
            <Grid item xs={1}></Grid>

            <Grid item xs={4}>
            <Grid style={{textAlign:"left"}}><h4>{pharmacy.pharmacyDescription}</h4></Grid>
                <Grid style={{textAlign:"left"}}><h4>{pharmacy.streetName} {pharmacy.streetNumber},{pharmacy.cityForPharmacy},{pharmacy.country}</h4></Grid>
                <Grid style={{textAlign:"left"}}><h4>{pharmacy.pharmacyAverageRating}</h4></Grid>
                <Grid style={{textAlign:"left"}}><h4>{pharmacy.dermatologistPerHour} </h4></Grid>
                <Grid style={{textAlign:"left"}}><h4>{pharmacy.pharmacistPerHour}</h4></Grid>
                <Grid style={{textAlign:"left"}}><Button style={{backgroundColor:"green"}} onClick = {HandleClickDermatologistAppointment}>Show dermatologist appointment</Button></Grid>
            </Grid>
            <Grid item xs={2}></Grid>

        </Grid>
        <div style={{visibility: dermatologistAppointmentPart ? 'visible' : 'hidden'}}>
          {dermatologistAppointment.length != 0 && scheduleDermatologistAppointment}
        </div>
        <Snackbar open={ openAlertInfo} autoHideDuration={1500} onClose={handleCloseAlertInfo}>
        <Alert severity="warning">
          { alertTextInfo}
        </Alert>
      </Snackbar>
      <Snackbar open={openAlertErrorPenalty} autoHideDuration={1500} onClose={handleCloseAlertErrorPenalty}>
        <Alert severity="error">
          {alertTextErrorPenalty}
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
  
  export default PharmacyProfilePage;
