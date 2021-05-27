import React from 'react'
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

  


const PharmacistsTableForSchedulePharmacistAppointment = ({pharmacists,setSelectedPharmacist}) => {

  const classes = useStyles();
 
  const TableHeader = (
    <TableHead>
      <TableRow className={classes.hederRow}>
        <TableCell className={classes.hederCell} >
         First name{" "}
        
        </TableCell>
        <TableCell className={classes.hederCell} >
         Last name{" "}
        </TableCell>
        <TableCell className={classes.hederCell} >
          Rating {" "}
       
        </TableCell>
      </TableRow>
    </TableHead>
  );

  const HandleClickSelectPharmacist = (row) => {
    setSelectedPharmacist(row)
  }

  const TableContent = (
    <TableBody>
      {pharmacists.map((row, index) => (
        <TableRow key={index} onClick = {() => HandleClickSelectPharmacist(row)}>
          <TableCell>{row.firstName}</TableCell>
          <TableCell>{row.lastName}</TableCell>
          <TableCell>{row.rating}</TableCell>
        </TableRow>
      ))}
    </TableBody>
  );

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
     
        </div>
    )
}

export default PharmacistsTableForSchedulePharmacistAppointment
