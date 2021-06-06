import React, { useEffect, useState } from "react";
import Button from "@material-ui/core/Button";
import Grid from '@material-ui/core/Grid';
import Paper from "@material-ui/core/Paper";
import TextField from "@material-ui/core/TextField";
import Snackbar from "@material-ui/core/Snackbar";
import Alert from "@material-ui/lab/Alert";
import { makeStyles } from '@material-ui/core/styles';
import Allergies from './Allergies'
import PasswordDialog from './PasswordDialog'
import axios from "axios";
import {URL} from "../other/components"



const useStyles = makeStyles((theme) => ({ //style za paper deo
    paper: {
      padding: theme.spacing(2),
      margin: theme.spacing('5%','20%'),
      maxWidth: 1000,
      backgroundColor : 'white'
    },
    h2:{
        fontFamily:  "Lucida Console"
    
    },
  
  }));

const HomePage = () => {

    const [user, setUser] = useState ({
        id : -1,
        firstName : '',
        lastName : '',
        address : {},
        phoneNumber : '',
        email : '',
        patientPoints : '',
        categoryOfPatient : '',
        allergies : {},
        penalty:''

    })

    const [userCopy, setUserCopy] = useState ({
        id : -1,
        firstName : '',
        lastName : '',
        address : {},
        phoneNumber : '',
        email : '',
        patientPoints : '',
        categoryOfPatient : '',
        penalty:''

    })

    const classes = useStyles()
    const [editState, setEditState ] = useState(false)
    const [openAlert, setOpenAlert] = useState(false) //alert za uspesno sacuvanu lozinku
    const [openDialog, setOpenDialog] = useState(false) //dialog za promenu lozinke
    const [alertText, setAlertText] = useState('')
    const [address, setAddress] = useState({
        streetName : '',
        streetNumber : '',
        city : '',
        country : '',
        longitude : '',
        latitude : ''
    })
    const [allergies, setAllergies] = useState([])
    const [validateInput, setValidateInput] = useState({
        firstName : true,
        lastName : true,
        phoneNumber : true,
        streetName : true,
        city : true,
        country : true,
    })
    const token = localStorage.getItem("token");
    const userId = localStorage.getItem("userId");
    const config = {
      headers: { Authorization: `Bearer ${token}`, consumes:'application/json' }
  };


    useEffect(() => {
        getUser()
    },[])

  
    const getUser = async () => {
        const res = await axios.get(URL + '/api/patient/' + userId +'/additionalInfo',config)
        let patient = res.data
        setUser({id : patient.id,firstName : patient.firstName, lastName : patient.lastName, address : patient.address, phoneNumber : patient.phoneNumber, email : patient.email, patientPoints : patient.patientPoints, categoryOfPatient : patient.categoryOfPatient, allergies : patient.allergies, penalty : patient.penalty})
        setUserCopy({id : patient.id,firstName : patient.firstName, lastName : patient.lastName, address : patient.address, phoneNumber : patient.phoneNumber, email : patient.email, patientPoints : patient.patientPoints, categoryOfPatient : patient.categoryOfPatient, penalty : patient.penalty})
        setAddress({streetName: patient.address.streetName, streetNumber: patient.address.streetNumber, city: patient.address.city, country: patient.address.country, longitude : patient.address.longitude, latitude : patient.address.latitude })
        setAllergies(patient.medicineForAllergiesDTO)
    }

    const handleEditButton = () => {
        setEditState(true)
    }

    const handleChangePasswordButton = () => {
        setOpenDialog(true)
    }

    const handleSaveButton =  () => {
        var updateUser = {
            id : user.id,
            firstName : user.firstName,
            lastName : user.lastName,
            address : address,
            phoneNumber : user.phoneNumber,
            email : user.email,
            patientPoints : user.patientPoints,
            categoryOfPatient : user.categoryOfPatient,
            penalty : user.penalty
        }
         
        if(validate(updateUser)){
            axios.put(URL + "/api/patient/update", updateUser,config)
            .then((res) => {
                setUserCopy({id : updateUser.id,firstName : updateUser.firstName, lastName : updateUser.lastName, address : updateUser.address, phoneNumber : updateUser.phoneNumber, email : updateUser.email, patientPoints : updateUser.patientPoints, categoryOfPatient : updateUser.categoryOfPatient})
                setAddress({streetName: updateUser.address.streetName, streetNumber: updateUser.address.streetNumber, city: updateUser.address.city, country: updateUser.address.country, longitude : updateUser.address.longitude, latitude : updateUser.address.latitude })
                setAlertText('Success update!')
                setOpenAlert(true)

            })
            .catch((err) => {
                console.log(err);
            });
            setEditState(false)
        }
      
       
        
    }

    const validate = (updateUser) => {
        var firstName = true
        var lastName = true
        var phoneNumber = true
        var streetName = true
        var city = true
        var country = true
        if(updateUser.firstName === ''){
            firstName = false
        }
        if(updateUser.lastName === ''){
            lastName = false
        }
        if(updateUser.phoneNumber === ''){
             phoneNumber = false
        }
        if(updateUser.address.streetName === ''){
            streetName = false
        }
        if(updateUser.address.city === ''){
           city = false

        }
        if(updateUser.address.country === ''){
           country = false

        }

        setErrorInput(firstName, lastName, phoneNumber, streetName, city, country)

        return firstName && lastName && phoneNumber && streetName && city && country
         
    }

    const setErrorInput = (firstName, lastName, phoneNumber, streetName, city, country) => {
        setValidateInput({firstName: firstName, lastName: lastName,phoneNumber: phoneNumber,streetName: streetName,city: city,country: country})
    }

    const handleCancelButton = () => {
        setUser({...user, firstName: userCopy.firstName, lastName: userCopy.lastName, phoneNumber: userCopy.phoneNumber})
        setAddress({...address, streetName: userCopy.address.streetName, streetNumber: userCopy.address.streetNumber, city: userCopy.address.city, country: userCopy.address.country})
        setErrorInput(true,true,true,true,true,true,true)
        setEditState(false)
    }

    const handleCloseAlert = (event, reason) => {
        if (reason === 'clickaway') {
          return;
        }
        setOpenAlert(false);
      };


    return (
    <>
    <div className={classes.root}>
        <Paper className={classes.paper}>
            <h2 className={classes.h2}>PROFILE</h2>
            <Grid container spacing={1} >
                <Grid container justify="center" spacing={10} >
                    <Grid  item xs={4}>
                        <table>
                            <tbody>
                            <tr><th>Personal info</th></tr>
                            <tr>
                                <td>Frist name: </td>
                                <td><TextField error = {!validateInput.firstName} value={user.firstName} disabled = {!editState} onChange= {(e) => setUser({...user, firstName: e.target.value})}></TextField></td>
                            </tr>

                            <tr>
                                <td>Last name: </td>
                                <td><TextField error = {!validateInput.lastName} value={user.lastName} disabled = {!editState } onChange= {(e) => setUser({...user, lastName: e.target.value})}></TextField></td>
                            </tr>

                            <tr>
                                <td>Email: </td>
                                <td><TextField value={user.email} readOnly disabled = {true}></TextField></td>
                            </tr>

                            <tr>
                                <td>Phone number: </td>
                                <td><TextField error = {!validateInput.phoneNumber} value={user.phoneNumber} disabled = {!editState} onChange= {(e) => setUser({...user, phoneNumber: e.target.value})}></TextField></td>
                            </tr>

                            <tr><th>Address</th></tr>
                            <tr>
                                <td>Street name: </td>
                                <td><TextField  error = {!validateInput.streetName} value={address.streetName} disabled = {!editState} onChange= {(e) => setAddress({...address, streetName: e.target.value})}></TextField></td>

                            </tr>
                            <tr>
                                <td>Street number: </td>
                                <td><TextField  value={address.streetNumber} disabled = {!editState} onChange= {(e) => setAddress({...address, streetNumber: e.target.value})}></TextField></td>

                            </tr>
                            <tr>
                                <td>City: </td>
                                <td><TextField error = {!validateInput.city} value={address.city} disabled = {!editState} onChange= {(e) => setAddress({...address, city: e.target.value})}></TextField></td>

                            </tr>
                            <tr>
                                <td>Country: </td>
                                <td><TextField error = {!validateInput.country} value={address.country} disabled = {!editState} onChange= {(e) => setAddress({...address, country: e.target.value})}></TextField></td>

                            </tr>
                            <tr><th>Addittional info</th></tr>
                            <tr>
                                <td>Points: </td>
                                <td>{user.patientPoints}</td>

                            </tr>
                            <tr>
                                <td>Category: </td>
                                <td>{user.categoryOfPatient}</td>

                            </tr>
                            <tr>
                                <td>Penalty: </td>
                                <td>{user.penalty}</td>

                            </tr>

                            <tr>
                                <td>
                                    {!editState ?
                                    (<Button onClick={handleEditButton} variant="contained" color="primary">Edit</Button>)
                                    : null}
                                    
                                </td>
                            </tr>
                            <tr>
                                
                                <td>
                                    {editState ?
                                    (<Button onClick={handleSaveButton} variant="contained" color="primary">Save</Button>)
                                    : null
                                }
                                </td>
                                <td>
                                    {editState ?
                                    (<Button onClick={handleCancelButton} variant="contained" color="primary">Cancel</Button>)
                                    : null
                                    }
                                </td>
                            </tr>
                            </tbody>
                        </table>
                        <br></br>
                        <table>
                            <tbody>
                                <tr>
                                    <td>
                                        <Button onClick={handleChangePasswordButton} variant="contained" color="primary" disabled = {editState}>Change password</Button>
                                        
                                    </td>
                                </tr>      
                            </tbody>
                        </table>
                    </Grid>
                    <Grid item>
                       <Allergies allergies={allergies} setAllergies={setAllergies} patientId = {user.id}></Allergies>
                    </Grid>
                </Grid>
            </Grid>
        </Paper>
        <PasswordDialog openDialog={openDialog}  id={user.id} setOpenAlert={setOpenAlert} setOpenDialog = {setOpenDialog} setAlertText = {setAlertText}></PasswordDialog>
    </div>

    
    <Snackbar open={openAlert} autoHideDuration={1500} onClose={handleCloseAlert}>
      <Alert severity="success">
        {alertText}
      </Alert>
    </Snackbar>
    </>

        
        
    )
}

export default HomePage
