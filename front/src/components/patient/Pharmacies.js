//SORTIRANJE PO GRADU DODATI
import {
    Table,
    TableBody,
    TableCell,
    TableHead,
    TableRow,
    Grid,
    TextField,
  } from "@material-ui/core";
  import {
    ArrowDropDown,
    ArrowDropUp,
    NavigateNext,
    NavigateBefore,
  } from "@material-ui/icons";
  import { useState, useEffect } from "react";
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
  
  const Pharmacies = () => {
    const classes = useStyles();
  
    const [rows, setRows] = useState([]);
  
    const [copyRows, setCopyRows] = useState({});
  
    const [currPage, setCurrPage] = useState(1);
  
    useEffect(() => {
      axios
        .get(
          "http://localhost:8080/api/pharmacy/all/" +
            (currPage - 1).toString() +
            ""
        )
        .then((res) => {
          setRows(res.data);
          setCopyRows(res.data);
        });
    }, []);
  
    const [pharmacyNameAsc, setPharmacyNameAsc] = useState({
      counter: -1,
      asc: true,
    });
  
    const [ratingAsc, setRatingAsc] = useState({
      counter: -1,
      asc: true,
    });
  
  
    const sortByPharmacyName = () => {
      setRatingAsc({ asc: true, counter: -1 });
  
      if (pharmacyNameAsc.asc === true) setPharmacyNameAsc({ counter: 0, asc: false });
      else setPharmacyNameAsc({ counter: 0, asc: true });
  
      axios
        .put(
          "http://localhost:8080/api/pharmacy/sortByName/" +
            (pharmacyNameAsc.asc ? "asc" : "desc"),
          rows
        )
        .then((res) => {
          setRows(res.data);
        });
    };
  
    const sortByRating = () => {
      setPharmacyNameAsc({ asc: true, counter: -1 });
    
      if (ratingAsc.asc === true) setRatingAsc({ counter: 0, asc: false });
      else setRatingAsc({ counter: 0, asc: true });
  
      axios
        .put(
          "http://localhost:8080/api/pharmacy/sortByRating/" +
            (ratingAsc.asc ? "asc" : "desc"),
          rows
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
  
    const [haveNextPage, setHaveNextPage] = useState(true);
  
    const nextPage = () => {
      axios
        .get(
          "http://localhost:8080/api/pharmacy/all/" +
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
          "http://localhost:8080/api/pharmacy/all/" +
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
  
    const TableHeader = (
      <TableHead>
        <TableRow className={classes.hederRow}>
          <TableCell className={classes.hederCell} onClick={sortByPharmacyName}>
           Pharmacy name{" "}
           {pharmacyNameAsc.asc && pharmacyNameAsc.counter !== -1 && <ArrowDropDown />}{" "}
          {!pharmacyNameAsc.asc && pharmacyNameAsc.counter !== -1 && <ArrowDropUp />}
          </TableCell>
          <TableCell className={classes.hederCell} >
           Address{" "}
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
      </div>
    );
  };
  
  export default Pharmacies;
  