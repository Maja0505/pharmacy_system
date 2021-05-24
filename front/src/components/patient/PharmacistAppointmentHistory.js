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
    Link
  } from "@material-ui/core";
  import {
    NavigateNext,
    NavigateBefore,
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
    const config = {
      headers: { Authorization: `Bearer ${token}` }
  };
  
    useEffect(() => {
      axios
        .get(
          "http://localhost:8080/api/pharmacistReport/all/patient/" + userId + "/" +
          (currPage - 1).toString() +
          "",config)
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
          "http://localhost:8080/api/pharmacistReport/all/patient/" + userId + "/" +
            currPage.toString() +
            ""
        ,config)
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
          "http://localhost:8080/api/pharmacistReport/all/patient/" + userId + "/" +
            (currPage - 2).toString() +
            ""
        ,config)
        .then((res) => {
          setHaveNextPage(true);
          if (res.data.length > 0) {
            setCurrPage(currPage - 1);
            setRows(res.data);
          }
        });
    };

    const [pharmacy,setPharmacy] = useState({})
    const [pharmacist,setPharmacist] = useState({})
    const [report,setReport] = useState({})

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

    const handleClickOpenPharmacistDialog = (pharmacistId) => {
        
        axios.get( "http://localhost:8080/api/pharmacist/" + pharmacistId,config).then(
            (res)=>{
                setPharmacist(res.data)
                setOpenPharmacistDialog(true)
            }
        )
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
  
    const TableHeader = (
      <TableHead>
        <TableRow className={classes.hederRow}>
         
          <TableCell className={classes.hederCell} >
           Pharmacy
          </TableCell>
          <TableCell className={classes.hederCell} >
          pharmacist
          </TableCell>
          <TableCell className={classes.hederCell} >
          Start time
          </TableCell>
          <TableCell className={classes.hederCell} >
         Duration
          </TableCell>
          <TableCell className={classes.hederCell} >
          Points
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
        {CreatePharmacyDialog}
        {CreatePharmacistDialog}
        {CreateReportDialog}
      </div>
    );
  };
  
  export default PharmacistAppointmentHistory;
