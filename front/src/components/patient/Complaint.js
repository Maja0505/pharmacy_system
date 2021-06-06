import React from 'react'
import { ComboBoxComponent } from '@syncfusion/ej2-react-dropdowns';
import { useState } from "react";
import axios from 'axios';
import {
    Table,
    TableBody,
    TableCell,
    TableHead,
    TableRow,
    Grid,
    Button,
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
import { getConfig } from '@testing-library/dom';
import {URL} from '../other/components'
import {REACT_URL} from '../other/components'
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



const Complaint = () => {

    const [typeList, setTypeList] = useState(['Dermatologist_complaint','Pharmacy_complaint','Pharmacist_complaint'])
    const [selectedType, setSelectedType] = useState()
    const [staffs, setStaffs] = useState([])
    const [medicines, setMedicines] = useState([])
    const [pharmacies, setPharmacies] = useState([])
    const [openComplaintDialog, setOpenComplaintDialog] = useState(false)
    const [radioValue, setRadioValue] = React.useState('female');
    const [selectedRow, setSelectedRow] = useState()
    const classes = useStyles();
    const token = localStorage.getItem("token");
    const userId = localStorage.getItem("userId");
    const [redirection,setRedirection] = useState(false)
    const config = {
      headers: { Authorization: `Bearer ${token}`, consumes:'application/json' }
  };

    const handleClickOpenComplaintDialog= (row) => {
        setSelectedRow(row)
        setOpenComplaintDialog(true)
    }
    const handleClickCloseComplaintDialog = () => {
        setRadioValue()
        setOpenComplaintDialog(false);

    };
    const handleClickSaveComplaint = () => {
      window.location.href =  REACT_URL + "/patient/home2";
    }


    const HandleClickComplaintType = (type) => {
        if(type == 'Dermatologist_complaint'){
            axios.get(URL + '/api/patient/' + userId +  '/dermatologist/expired',config)
            .then((res) => {
                setStaffs(res.data)
            }).catch((error) => {
              if(error.response.status === 401){
                setRedirection(true)
              }
            });
        }else if(type == 'Pharmacy_complaint'){
            axios.get(URL + '/api/patient/' + userId + '/pharmacy',config)
            .then((res) => {
                setPharmacies(res.data)
            }).catch((error) => {
              if(error.response.status === 401){
                setRedirection(true)
              }
            });

        }else{
            axios.get(URL + '/api/patient/' + userId + '/pharmacist/expired',config)
            .then((res) => {
                setStaffs(res.data)
            }).catch((error) => {
              if(error.response.status === 401){
                setRedirection(true)
              }
            });

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
              <TableCell><Button onClick={() => handleClickOpenComplaintDialog(row)}>Write complaint</Button></TableCell>

            </TableRow>
          ))}
        </TableBody>
      );
    

    const TableForDermatologistAndPharmacist = (
        <div>
        <Grid container spacing={1} style={{marginTop:'10%'}}>
          <Grid item xs={2} />
          <Grid item xs={8}>
            <Table>
              {TableHeaderForStaff}
              {TableContentForStaff}
            </Table>
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
              <TableCell><Button onClick={() => handleClickOpenComplaintDialog(row)}>Write complaint</Button></TableCell>

            </TableRow>
          ))}
        </TableBody>
      );

      const TableForMedicine = (
        <div>
        <Grid container spacing={1} style={{marginTop:'10%'}}>
          <Grid item xs={2} />
          <Grid item xs={8}>
            <Table>
              {TableHeaderForMedicine}
              {TableContentForMedicine}
            </Table>
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
              <TableCell>{row.pharmacyAverageComplaint}</TableCell>
              <TableCell><Button onClick={() => handleClickOpenComplaintDialog(row)}>Write complaint</Button></TableCell>
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


      const CreateComplaintDialog = (
        <div>
            <Dialog onClose={handleClickCloseComplaintDialog} aria-labelledby="customized-dialog-title" open={openComplaintDialog} fullWidth='true'
          maxWidth='sm'>
              <DialogTitle id="customized-dialog-title" onClose={handleClickCloseComplaintDialog}>
                
              </DialogTitle>
              <DialogContent dividers>
             
                  <Typography gutterBottom>
     
                    <TextField
                    style={{width:"70%"}}
                      id="outlined-multiline-static"
                      label="Complaint"
                      multiline
                      rows={20}
                      variant="outlined"
                    />
                  </Typography>
              
    
              </DialogContent>
              <DialogActions>
                  <Button onClick={handleClickCloseComplaintDialog} autoFocus color="primary">
                  Close
                  </Button>
                  <Button onClick={handleClickSaveComplaint} autoFocus color="primary">
                  Save
                  </Button>
              </DialogActions>
            </Dialog>
        </div>
    
      )
    return (
        <div>
            {redirection === true && <Redirect to="/login"></Redirect>}
            <h2>Complaint</h2>
            {CreateComplaintDialog} 
            <ComboBoxComponent 
                width= "20%"
                style={{width:"100%"}}
                id="diacritics"
                ignoreAccent={true}
                allowFiltering={true}
                dataSource={typeList}
                placeholder="Select complaint type"
                change={(e) => HandleClickComplaintType(e.value)}
                  />
               {selectedType === 'Pharmacy_complaint' && TableForPharmacy}
              {(selectedType === 'Dermatologist_complaint' || selectedType === 'Pharmacist_complaint')  && TableForDermatologistAndPharmacist}         
        </div>
    )


}

export default Complaint
