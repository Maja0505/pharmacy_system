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
  TextField
} from "@material-ui/core";
import { makeStyles } from "@material-ui/core/styles";
import axios from "axios";


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

  


const SubscriptionPharmacy = () => {

  const classes = useStyles();
  const [rows,setRows] = useState([]);
  const token = localStorage.getItem("token");
  const userId = localStorage.getItem("userId");
  const config = {
    headers: { Authorization: `Bearer ${token}` }
};

  useEffect(() => {
    axios
      .get(
        "http://localhost:8080/api/patient/" + userId + "/subscription" 
      ,config)
      .then((res) => {
        setRows(res.data);
      }).catch(error => {

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
     
        </div>
    )
}

export default SubscriptionPharmacy
