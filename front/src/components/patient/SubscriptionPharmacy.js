//KAKO DA JE ZAPRATIIII???

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
import { makeStyles } from "@material-ui/core/styles";
import axios from "axios";
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

  


const SubscriptionPharmacy = () => {

  const classes = useStyles();
  const [rows,setRows] = useState([]);
  const token = localStorage.getItem("token");
  const userId = localStorage.getItem("userId");
  const [redirection,setRedirection] = useState(false)

  const config = {
    headers: { Authorization: `Bearer ${token}` }
};

  useEffect(() => {
    axios
      .get(
        URL + "/api/patient/" + userId + "/subscription" 
      ,config)
      .then((res) => {
        setRows(res.data);
      }).catch((error) => {
        if(error.response.status === 401){
          setRedirection(true)
        }
      });
  }, []);
 
  const TableHeader = (
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
        <TableCell className={classes.hederCell} >
          Price for dermatologist(per hour)
        </TableCell>
        <TableCell className={classes.hederCell} >
        Price for farmacist(per hour)
        </TableCell>
      </TableRow>
    </TableHead>
  );

  const TableContent = (
    <TableBody>
      {rows.map((row, index) => (
        <TableRow key={index}>
          <TableCell>{row.pharmacyName}</TableCell>
          <TableCell>{row.pharmacyAddress.streetName} {row.pharmacyAddress.streetNumber},{row.pharmacyAddress.city}</TableCell>
          <TableCell>{row.pharmacyAverageRating}</TableCell>
          <TableCell>{row.priceListForAppointmentDTO.dermatologistAppointmentPricePerHour}</TableCell>
          <TableCell>{row.priceListForAppointmentDTO.pharmacistAppointmentPricePerHour}</TableCell>
        </TableRow>
      ))}
    </TableBody>
  );

    return (
        <div>
        {redirection === true && <Redirect to="/login"></Redirect>}
        <h3>Followed pharmacies</h3>
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
     
        </div>
    )
}

export default SubscriptionPharmacy
