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
  import {Link} from "react-router-dom";
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
  
  const Medicines = () => {
    const classes = useStyles();
  
    const [rows, setRows] = useState([]);
    const [redirection,setRedirection] = useState(false)

    const [copyRows, setCopyRows] = useState({});
  
    const token = localStorage.getItem("token");
    const userId = localStorage.getItem("userId");
    const config = {
      headers: { Authorization: `Bearer ${token}`, consumes:'application/json' }
  };

   
    
  
    useEffect(() => {
      axios
        .get(
          URL + "/api/medicine/all"
        ,config)
        .then((res) => {
          setRows(res.data);
          setCopyRows(res.data);
        }).catch((error) => {
          if(error.response.status === 401){
            setRedirection(true)
          }
        });
    }, []);
  
    const searchPharmacy = (e) => {
      e = e.trim();
      setRows(
        copyRows.filter(
          (row) =>
            row.name.toLowerCase().includes(e.toLowerCase()) ||
            row.code.toLowerCase().includes(e.toLowerCase()) ||
            row.type.toLowerCase().includes(e.toLowerCase()) ||
            row.form.toLowerCase().includes(e.toLowerCase()) ||
            row.manufacturerName.toLowerCase().includes(e.toLowerCase()) 
        )
      );
    };

   
    const TableHeader = (
      <TableHead>
        <TableRow className={classes.hederRow}>
          <TableCell className={classes.hederCell}>
          name{" "}
         
          </TableCell>
          <TableCell className={classes.hederCell}  >
          code{" "}
          
          </TableCell>
          <TableCell className={classes.hederCell}>
          type {" "}
         
          </TableCell>
          <TableCell className={classes.hederCell} >
          form          </TableCell>
          <TableCell className={classes.hederCell} >
          manufacturerName   
        </TableCell>
        </TableRow>
      </TableHead>
    );
  
    const TableContent = (
      <TableBody>
        {rows.map((row, index) => (
          <TableRow key={index} >
            <TableCell>{row.name}</TableCell>
            <TableCell>{row.code}</TableCell>
            <TableCell>{row.type}</TableCell>
            <TableCell>{row.form}</TableCell>
            <TableCell>{row.manufacturerName}</TableCell>
      
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
        {redirection === true && <Redirect to="/login"></Redirect>}
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
  
  export default Medicines;
  