import React, { useEffect, useState } from "react";
import PharmacistAppointmentHistory from './PharmacistAppointmentHistory'
import DermatologistAppointmentHistory from './DermatologistAppointmentHistory'
import DermatologistReservaedAppointment from './DermatologistReservaedAppointment'
import PharmacistReservedAppointment from './PharmacistReservedAppointment'
import SchedulePharmacistAppointment from './SchedulePharmacistAppointment'

import { withStyles } from '@material-ui/core/styles';
import Button from '@material-ui/core/Button';
import Menu from '@material-ui/core/Menu';
import MenuItem from '@material-ui/core/MenuItem';
import ListItemIcon from '@material-ui/core/ListItemIcon';
import ListItemText from '@material-ui/core/ListItemText';
import InboxIcon from '@material-ui/icons/MoveToInbox';
import DraftsIcon from '@material-ui/icons/Drafts';
import SendIcon from '@material-ui/icons/Send';

const StyledMenu = withStyles({
    paper: {
      border: '1px solid #d3d4d5',
    },
  })((props) => (
    <Menu
      elevation={0}
      getContentAnchorEl={null}
      anchorOrigin={{
        vertical: 'bottom',
        horizontal: 'center',
      }}
      transformOrigin={{
        vertical: 'top',
        horizontal: 'center',
      }}
      {...props}
    />
  ));
  
  const StyledMenuItem = withStyles((theme) => ({
    root: {
      '&:focus': {
        backgroundColor: theme.palette.primary.main,
        '& .MuiListItemIcon-root, & .MuiListItemText-primary': {
          color: theme.palette.common.white,
        },
      },
    },
  }))(MenuItem);



const AppointmentsHome = () => {
    const [anchorEl, setAnchorEl] = React.useState(null);
    const [anchorEl2, setAnchorEl2] = React.useState(null);


    const handleClick = (event) => {
      setAnchorEl(event.currentTarget);
    };

    const handleClick2 = (event) => {
          setAnchorEl2(event.currentTarget);
        };
      
  
    const handleClose = () => {
      setAnchorEl(null);
    };

    const handleClose2 = () => {
        setAnchorEl2(null);
      };
  

    const [pharmacistHistory,setPharmacistHistory] = useState(false)
    const [dermatologistHistory,setDermatologistHistory] = useState(false)
    const [pharmacistReserved,setPharmacistReserved] = useState(false)
    const [dermatologistReserved,setDermatologistReserved] = useState(false)
    const [pharmacistCreate,setPharmacistCreate] = useState(false)


    const HandlePharmacistHistoryClickButton = () => {
      if(!pharmacistHistory){
        setPharmacistHistory(true)
        setDermatologistHistory(false)
        setPharmacistReserved(false)
        setDermatologistReserved(false)
        setPharmacistCreate(false)
      }
      handleClose()
    }

    const HandleDermatologistHistoryClickButton = () => {
        if(!dermatologistHistory){
            setPharmacistHistory(false)
            setDermatologistHistory(true)
            setPharmacistReserved(false)
            setDermatologistReserved(false)
            setPharmacistCreate(false)

          }
          handleClose2()
      }
    
      const HandlePharmacistReservedClickButton = () => {
        if(!pharmacistReserved){
        setPharmacistHistory(false)
        setDermatologistHistory(false)
        setPharmacistReserved(true)
        setDermatologistReserved(false)
        setPharmacistCreate(false)


        }
        handleClose()
      }
      const HandleDermatologistReservedClickButton = () => {
        if(!dermatologistReserved){
            setPharmacistHistory(false)
            setDermatologistHistory(false)
            setPharmacistReserved(false)
            setDermatologistReserved(true)
            setPharmacistCreate(false)


        }
        handleClose2()
      }

      const HandlePharmacistCreateClickButton = () => {
        if(!pharmacistCreate){
            setPharmacistHistory(false)
            setDermatologistHistory(false)
            setPharmacistReserved(false)
            setDermatologistReserved(false)
            setPharmacistCreate(true)


        }
        handleClose()
      }
 

    const PharmacistAppointmentHistoryy = (
        <PharmacistAppointmentHistory></PharmacistAppointmentHistory>
    )

    const DermatologistAppointmentHistoryy = (
        <DermatologistAppointmentHistory></DermatologistAppointmentHistory>
    )

    const PharmacistReservedAppointmentt = (
        < PharmacistReservedAppointment></PharmacistReservedAppointment>
    )

    const DermatologistReservaedAppointmentt = (
        < DermatologistReservaedAppointment></ DermatologistReservaedAppointment>
    )

    const CreatePharmacistApp = (
        < SchedulePharmacistAppointment></ SchedulePharmacistAppointment>
    )




    return (
      
             <div style={{marginTop:"5%"}}>
                <Button  variant= {pharmacistHistory || pharmacistCreate || pharmacistReserved ? "outlined" : "contained"} size="large" color="primary"  onClick={handleClick}>
                Pharmacist appointments
                </Button>
        <StyledMenu
        id="customized-menu"
        anchorEl={anchorEl}
        keepMounted
        open={Boolean(anchorEl)}
        onClose={handleClose}
      >
        <StyledMenuItem selected={pharmacistHistory} onClick={HandlePharmacistHistoryClickButton}>
          <ListItemIcon>
            <SendIcon  fontSize="small" />
          </ListItemIcon>
          <ListItemText primary="History" />
        </StyledMenuItem>
        <StyledMenuItem selected={pharmacistReserved} onClick={HandlePharmacistReservedClickButton}>
          <ListItemIcon>
            <DraftsIcon fontSize="small" />
          </ListItemIcon>
          <ListItemText primary="Reserved" />
        </StyledMenuItem>
        <StyledMenuItem selected={pharmacistCreate} onClick={HandlePharmacistCreateClickButton}>
          <ListItemIcon>
            <InboxIcon fontSize="small" />
          </ListItemIcon>
          <ListItemText primary="Create" />
        </StyledMenuItem>
      </StyledMenu>
                <Button variant= {dermatologistHistory || dermatologistReserved ? "outlined" : "contained"}  color="primary" size="large" onClick={handleClick2}>
                Dermatologist appointments
                </Button>
        <StyledMenu
        id="customized-menu"
        anchorEl={anchorEl2}
        keepMounted
        open={Boolean(anchorEl2)}
        onClose={handleClose2}
      >
        <StyledMenuItem selected={dermatologistHistory} onClick={HandleDermatologistHistoryClickButton}>
          <ListItemIcon>
            <SendIcon  fontSize="small" />
          </ListItemIcon>
          <ListItemText primary="History" />
        </StyledMenuItem>
        <StyledMenuItem selected={dermatologistReserved} onClick={HandleDermatologistReservedClickButton}>
          <ListItemIcon>
            <DraftsIcon fontSize="small" />
          </ListItemIcon>
          <ListItemText primary="Reserved" />
        </StyledMenuItem>
        <StyledMenuItem>
          <ListItemIcon>
            <InboxIcon fontSize="small" />
          </ListItemIcon>
          <ListItemText primary="Create" />
        </StyledMenuItem>
      </StyledMenu>
                {pharmacistHistory && PharmacistAppointmentHistoryy}
                {dermatologistHistory && DermatologistAppointmentHistoryy}
                {pharmacistReserved && PharmacistReservedAppointmentt}
                {dermatologistReserved && DermatologistReservaedAppointmentt}
                {pharmacistCreate && CreatePharmacistApp}
            </div>

     
      
    )
}

export default AppointmentsHome
