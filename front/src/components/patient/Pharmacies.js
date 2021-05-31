//SORTIRANJE PO GRADU DODATI
import {
    Table,
    TableBody,
    TableCell,
    TableHead,
    TableRow,
    Grid,
    TextField,
    Button,
    TableContainer
  } from "@material-ui/core";
  import {
    ArrowDropDown,
    ArrowDropUp,
  } from "@material-ui/icons";
  import { useState, useEffect } from "react";
  import { makeStyles } from "@material-ui/core/styles";
  import axios from "axios";
  import { BrowserRouter as Link } from "react-router-dom";

  
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
  
  const Pharmacies = () => {
    const classes = useStyles();
  
    const [rows, setRows] = useState([]);
  
    const [copyRows, setCopyRows] = useState({});
  
    const token = localStorage.getItem("token");
    const userId = localStorage.getItem("userId");
    const config = {
      headers: { Authorization: `Bearer ${token}`, consumes:'application/json' }
  };

   
    
  
    useEffect(() => {
      axios
        .get(
          "http://localhost:8080/api/pharmacy/all"
        ,config)
        .then((res) => {
          setRows(res.data);
          setCopyRows(res.data);
        });
    }, []);

  
    const [pharmacyNameAsc, setPharmacyNameAsc] = useState({
      counter: -1,
      asc: true,
    });

    const [pharmacyCityAsc, setPharmacyCityAsc] = useState({
      counter: -1,
      asc: true,
    });
  
    const [ratingAsc, setRatingAsc] = useState({
      counter: -1,
      asc: true,
    });
  
  
    const sortByPharmacyName = () => {

        setRatingAsc({ asc: true, counter: -1 });
        setPharmacyCityAsc({ asc: true, counter: -1 });
  
    
        if (pharmacyNameAsc.asc === true) setPharmacyNameAsc({ counter: 0, asc: false });
        else setPharmacyNameAsc({ counter: 0, asc: true });
    
        axios
          .put(
            "http://localhost:8080/api/pharmacy/sortByName/" +
              (pharmacyNameAsc.asc ? "asc" : "desc"),
            rows
          ,config)
          .then((res) => {
            setRows(res.data);
          });
    };

    const sortByPharmacyCity = () => {
      setRatingAsc({ asc: true, counter: -1 });
      setPharmacyNameAsc({ asc: true, counter: -1 });

  
      if (pharmacyCityAsc.asc === true) setPharmacyCityAsc({ counter: 0, asc: false });
      else setPharmacyCityAsc({ counter: 0, asc: true });
      
  
      axios
        .put(
          "http://localhost:8080/api/pharmacy/sortByCity/" +
            (pharmacyCityAsc.asc ? "asc" : "desc"),
          rows
        ,config)
        .then((res) => {
          setRows(res.data);
        });
    };
  
    const sortByRating = () => {
      setPharmacyNameAsc({ asc: true, counter: -1 });
      setPharmacyCityAsc({ asc: true, counter: -1 });

      
    
      if (ratingAsc.asc === true) setRatingAsc({ counter: 0, asc: false });
      else setRatingAsc({ counter: 0, asc: true });
  
      axios
        .put(
          "http://localhost:8080/api/pharmacy/sortByRating/" +
            (ratingAsc.asc ? "asc" : "desc"),
          rows,config
        )
        .then((res) => {
          setRows(res.data);
        });
    };
  
  
    const searchPharmacy = (e) => {
      e = e.trim();
      setRows(
        copyRows.filter(
          (row) =>
            row.pharmacyName.toLowerCase().includes(e.toLowerCase()) ||
            row.pharmacyAddress.streetName.toLowerCase().includes(e.toLowerCase()) ||
            row.pharmacyAddress.city.toLowerCase().includes(e.toLowerCase()) ||
            row.pharmacyAddress.country.toLowerCase().includes(e.toLowerCase()) ||
            row.pharmacyAverageRating.toString().toLowerCase().includes(e.toLowerCase()) ||
            row.priceListForAppointmentDTO.dermatologistAppointmentPricePerHour.toString().toLowerCase().includes(e.toLowerCase()) ||
            row.priceListForAppointmentDTO.pharmacistAppointmentPricePerHour.toString() .toLowerCase().includes(e.toLowerCase())
        )
      );
    };

    const openProfile = (row) => {
      console.log(row)
    }

    const TableHeader = (
      <TableHead>
        <TableRow className={classes.hederRow}>
          <TableCell className={classes.hederCell} onClick={sortByPharmacyName}>
           Pharmacy name{" "}
           {pharmacyNameAsc.asc && pharmacyNameAsc.counter !== -1 && <ArrowDropDown />}{" "}
          {!pharmacyNameAsc.asc && pharmacyNameAsc.counter !== -1 && <ArrowDropUp />}
          </TableCell>
          <TableCell className={classes.hederCell} onClick={sortByPharmacyCity} >
           Address{" "}
           {pharmacyCityAsc.asc && pharmacyCityAsc.counter !== -1 && <ArrowDropDown />}{" "}
          {!pharmacyCityAsc.asc && pharmacyCityAsc.counter !== -1 && <ArrowDropUp />}
          </TableCell>
          <TableCell className={classes.hederCell} onClick={sortByRating}>
            Rating {" "}
           {ratingAsc.asc && ratingAsc.counter !== -1 && <ArrowDropDown />}{" "}
          {!ratingAsc.asc && ratingAsc.counter !== -1 && <ArrowDropUp />}
          </TableCell>
          <TableCell className={classes.hederCell} >
            Price for dermatologist(per hour)
          </TableCell>
          <TableCell className={classes.hederCell} >
          Price for farmacist(per hour)
          </TableCell>
          <TableCell className={classes.hederCell} >
            Profile
          </TableCell>
        </TableRow>
      </TableHead>
    );
  
    const TableContent = (
      <TableBody>
        {rows.map((row, index) => (
          <TableRow key={index} >
            <TableCell>{row.pharmacyName}</TableCell>
            <TableCell>{row.pharmacyAddress.city},{row.pharmacyAddress.streetName} {row.pharmacyAddress.streetNumber}</TableCell>
            <TableCell>{row.pharmacyAverageRating}</TableCell>
            <TableCell>{row.priceListForAppointmentDTO.dermatologistAppointmentPricePerHour}</TableCell>
            <TableCell>{row.priceListForAppointmentDTO.pharmacistAppointmentPricePerHour}</TableCell>
            <TableCell><Button  component={Link}  to={"/patient/HomePage/pharmacyProfilePage/" + row.id} onClick={() => openProfile(row)}>Open</Button></TableCell>
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
            label="Search pharmacy"
            type="search"
            size="small"
            variant="outlined"
            style={{ width: "60%" }}
            onChange={(e) => searchPharmacy(e.target.value)}
          />
        </Grid>
        <Grid item xs={2}></Grid>
        <Grid item xs={2} />
      </Grid>
    );
  
    return (
      <div>
        {SearchPart}
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
    );
  };
  
  export default Pharmacies;
  