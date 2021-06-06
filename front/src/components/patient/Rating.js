import React from 'react'
import { ComboBoxComponent } from '@syncfusion/ej2-react-dropdowns';
import { useState, useEffect } from "react";
import axios from 'axios';
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
import { makeStyles } from "@material-ui/core/styles";
import Dialog from '@material-ui/core/Dialog';
import MuiDialogTitle from '@material-ui/core/DialogTitle';
import MuiDialogContent from '@material-ui/core/DialogContent';
import MuiDialogActions from '@material-ui/core/DialogActions';
import IconButton from '@material-ui/core/IconButton';
import CloseIcon from '@material-ui/icons/Close';
import Typography from '@material-ui/core/Typography';
import { withStyles } from '@material-ui/core/styles';
import Radio from '@material-ui/core/Radio';
import RadioGroup from '@material-ui/core/RadioGroup';
import FormControlLabel from '@material-ui/core/FormControlLabel';
import FormControl from '@material-ui/core/FormControl';
import FormLabel from '@material-ui/core/FormLabel';
import Alert from "@material-ui/lab/Alert";
import Snackbar from "@material-ui/core/Snackbar";
import {URL} from '../other/components'
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



const Rating = () => {

    const [typeList, setTypeList] = useState(['Dermatologist_rating','Medicine_rating','Pharmacy_rating','Pharmacist_rating'])
    const [selectedType, setSelectedType] = useState()
    const [staffs, setStaffs] = useState([])
    const [medicines, setMedicines] = useState([])
    const [pharmacies, setPharmacies] = useState([])
    const [openRatingDialog, setOpenRatingDialog] = useState(false)
    const [radioValue, setRadioValue] = React.useState('female');
    const [selectedRow, setSelectedRow] = useState()
    const classes = useStyles();
    const [openAlertSuccess, setOpenAlertSuccess] = useState(false)
    const [alertTextSuccess, setAlertTextSuccess] = useState('')
    const token = localStorage.getItem("token");
    const userId = localStorage.getItem("userId");
    const [redirection,setRedirection] = useState(false)
    const config = {
      headers: { Authorization: `Bearer ${token}` }
  };
    const handleClickOpenRatingDialog= (row) => {
        setSelectedRow(row)
        setOpenRatingDialog(true)
    }
    const handleClickCloseRatingDialog = () => {
        setRadioValue()
        setOpenRatingDialog(false);

    };

    const handleCloseAlertSuccess = (event, reason) => {
      if (reason === 'clickaway') {
        return;
      }
      setOpenAlertSuccess(false);
    };

    const handleClickSaveRating = () => {
        
        if(selectedType === 'Dermatologist_rating' || selectedType === 'Pharmacist_rating'){
            var staffDTO = {
                id:0,
                grade:radioValue,
                patientId:userId,
                typeOfRating:selectedType,
                staffId:selectedRow.id,
                staffFirstName:selectedRow.firstName,
                staffLastName:selectedRow.lastName,
                staffEmail:selectedRow.email


            }
            axios.post(URL + '/api/rating/staff',staffDTO,config)
            .then((res)=> {
                setAlertTextSuccess('Success set rating')
                setOpenAlertSuccess(true)
                setRadioValue()
                setOpenRatingDialog(false);
            }).catch(error => {

            })
            
        }
        if(selectedType === 'Medicine_rating'){
            var staffDTO = {
                id:0,
                grade:radioValue,
                patientId:userId,
                typeOfRating:selectedType,
                medicineId:selectedRow.medicineId,
                medicineName:selectedRow.medicineName
            }
            axios.post(URL + '/api/rating/medicine',staffDTO,config)
            .then((res)=> {
                setAlertTextSuccess('Success set rating')
                setOpenAlertSuccess(true)
                setRadioValue()
                setOpenRatingDialog(false);
            }).catch(error => {
              
            })
            
        }
        if(selectedType === 'Pharmacy_rating'){
            var staffDTO = {
                id:0,
                grade:radioValue,
                patientId:userId,
                typeOfRating:selectedType,
                pharmacyId:selectedRow.id,
                pharmacyName:selectedRow.pharmacyName
            }
            axios.post(URL + '/api/rating/pharmacy',staffDTO,config)
            .then((res)=> {
                setAlertTextSuccess('Success set rating')
                setOpenAlertSuccess(true)
                setRadioValue()
                setOpenRatingDialog(false);
            }).catch(error => {
              
            })
            
        }
    }


    const HandleClickRatingType = (type) => {
        if(type == 'Dermatologist_rating'){
            axios.get(URL + '/api/patient/' + userId + '/dermatologist/expired',config)
            .then((res) => {
                setStaffs(res.data)
            }).catch(error => {
              
            })
        }else if(type == 'Medicine_rating'){
            axios.get(URL + '/api/patient/' + userId + '/medicine',config)
            .then((res) => {
                setMedicines(res.data)
            }).catch(error => {
              
            })

        }else if(type == 'Pharmacy_rating'){
            axios.get(URL + '/api/patient/' + userId + '/pharmacy',config)
            .then((res) => {
                setPharmacies(res.data)
            }).catch(error => {
              
            })

        }else{
            axios.get(URL + '/api/patient/' + userId + '/pharmacist/expired',config)
            .then((res) => {
                setStaffs(res.data)
            }).catch(error => {
              
            })

        }
        setSelectedType(type)

    }


    const TableHeaderForStaff = (
        <TableHead>
          <TableRow className={classes.hederRow}>
            <TableCell className={classes.hederCell} >
             First name
            </TableCell>
            <TableCell className={classes.hederCell} >
             Last name
            </TableCell>
            <TableCell className={classes.hederCell} >
              Email
            </TableCell>
            <TableCell></TableCell>
          </TableRow>
        </TableHead>
      );
    
      const TableContentForStaff = (
        <TableBody>
          {staffs.map((row, index) => (
            <TableRow key={index}>
              <TableCell>{row.firstName}</TableCell>
              <TableCell>{row.lastName}</TableCell>
              <TableCell>{row.email}</TableCell>
              <TableCell><Button onClick={() => handleClickOpenRatingDialog(row)}>Set new rating</Button></TableCell>

            </TableRow>
          ))}
        </TableBody>
      );
    

    const TableForDermatologistAndPharmacist = (
        <div>
        <Grid container spacing={1} style={{marginTop:'10%'}}>
          <Grid item xs={2} />
          <Grid item xs={8}>
          <TableContainer style={{ height: "450px", marginTop: "2%" }}>
            <Table>
              {TableHeaderForStaff}
              {TableContentForStaff}
            </Table>
          </TableContainer>
          </Grid>
          <Grid item xs={2}></Grid>
        </Grid>
        </div>
    )

    const TableHeaderForMedicine= (
        <TableHead>
          <TableRow className={classes.hederRow}>
            <TableCell className={classes.hederCell} >
             Id
            </TableCell>
            <TableCell className={classes.hederCell} >
             Medicine Name
            </TableCell>
            <TableCell></TableCell>
          </TableRow>
        </TableHead>
      );
    
      const TableContentForMedicine = (
        <TableBody>
          {medicines.map((row, index) => (
            <TableRow key={index}>
              <TableCell>{row.medicineId}</TableCell>
              <TableCell>{row.medicineName}</TableCell>
              <TableCell><Button onClick={() => handleClickOpenRatingDialog(row)}>Set new rating</Button></TableCell>

            </TableRow>
          ))}
        </TableBody>
      );

      const TableForMedicine = (
        <div>
        <Grid container spacing={1} style={{marginTop:'10%'}}>
          <Grid item xs={2} />
          <Grid item xs={8}>
          <TableContainer style={{ height: "450px", marginTop: "2%" }}>
            <Table>
              {TableHeaderForMedicine}
              {TableContentForMedicine}
            </Table>
            </TableContainer>
          </Grid>
          <Grid item xs={2}></Grid>
        </Grid>
        </div>
    )

    const TableHeaderPharmacy = (
        <TableHead>
          <TableRow className={classes.hederRow}>
            <TableCell className={classes.hederCell} >
             Pharmacy name{" "}
            </TableCell>
            <TableCell className={classes.hederCell} >
             Address{" "}
            </TableCell>
            <TableCell className={classes.hederCell} >
              Rating {" "}
            </TableCell>
            <TableCell></TableCell>
          </TableRow>
        </TableHead>
      );
    
      const TableContentPharmacy = (
        <TableBody>
          {pharmacies.map((row, index) => (
            <TableRow key={index}>
              <TableCell>{row.pharmacyName}</TableCell>
              <TableCell>{row.pharmacyAddress.streetName} {row.pharmacyAddress.streetNumber},{row.pharmacyAddress.city}</TableCell>
              <TableCell>{row.pharmacyAverageRating}</TableCell>
              <TableCell><Button onClick={() => handleClickOpenRatingDialog(row)}>Set new rating</Button></TableCell>
            </TableRow>
          ))}
        </TableBody>
      );

      const TableForPharmacy = (
        <div>
            <Grid container spacing={1} style={{marginTop:'10%'}}>
                <Grid item xs={2} />
                <Grid item xs={8}>
                <TableContainer style={{ height: "450px", marginTop: "2%" }}>
                    <Table>
                    {TableHeaderPharmacy}
                    {TableContentPharmacy}
                    </Table>
                  </TableContainer>
                </Grid>
                <Grid item xs={2}></Grid>
            </Grid>
        </div>

      )

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

      const handleChange = (event) => {
        setRadioValue(event.target.value);
      };


      const CreateRatingDialog = (
        <div>
            <Dialog onClose={handleClickCloseRatingDialog} aria-labelledby="customized-dialog-title" open={openRatingDialog} fullWidth='true'
          maxWidth='sm'>
              <DialogTitle id="customized-dialog-title" onClose={handleClickCloseRatingDialog}>
                
              </DialogTitle>
              <DialogContent dividers>
                  <Typography gutterBottom>
                    <FormControl component="fieldset">
                      <RadioGroup aria-label="gender" name="gender1" value={radioValue} onChange={handleChange}>
                          <FormControlLabel value="One" control={<Radio />} label="One" />
                          <FormControlLabel value="Two" control={<Radio />} label="Two" />
                          <FormControlLabel value="Three" control={<Radio />} label="Three" />
                          <FormControlLabel value="Four"  control={<Radio />} label="Four" />
                          <FormControlLabel value="Five"  control={<Radio />} label="Five" />
                      </RadioGroup>
                      </FormControl>
                  </Typography>
              </DialogContent>
              <DialogActions>
                  <Button onClick={handleClickCloseRatingDialog} autoFocus color="primary">
                  Close
                  </Button>
                  <Button onClick={handleClickSaveRating} autoFocus color="primary">
                  Save
                  </Button>
              </DialogActions>
            </Dialog>
        </div>
    
      )
    return (
        <div>
            <h2>Rating form</h2>
            {CreateRatingDialog} 
            <ComboBoxComponent 
                width= "20%"
                style={{width:"100%"}}
                id="diacritics"
                ignoreAccent={true}
                allowFiltering={true}
                dataSource={typeList}
                placeholder="Select rating type"
                change={(e) => HandleClickRatingType(e.value)}
                  />
               {selectedType === 'Pharmacy_rating' && TableForPharmacy}
              {(selectedType === 'Dermatologist_rating' || selectedType === 'Pharmacist_rating')  && TableForDermatologistAndPharmacist}
              {selectedType === 'Medicine_rating' && TableForMedicine}             
              <Snackbar open={openAlertSuccess} autoHideDuration={1500} onClose={handleCloseAlertSuccess}>
                <Alert severity="success">
                  {alertTextSuccess}
                </Alert>
              </Snackbar>
        </div>
    )


}

export default Rating
