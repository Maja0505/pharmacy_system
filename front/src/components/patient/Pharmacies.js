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
import { BrowserRouter as Router, Route, Link } from "react-router-dom";
import Dialog from '@material-ui/core/Dialog';
import MuiDialogTitle from '@material-ui/core/DialogTitle';
import MuiDialogContent from '@material-ui/core/DialogContent';
import MuiDialogActions from '@material-ui/core/DialogActions';
import IconButton from '@material-ui/core/IconButton';
import CloseIcon from '@material-ui/icons/Close';
import Typography from '@material-ui/core/Typography';
import { withStyles } from '@material-ui/core/styles';

import FormControlLabel from '@material-ui/core/FormControlLabel';
import FormControl from '@material-ui/core/FormControl';
import FormLabel from '@material-ui/core/FormLabel';
import FormGroup from '@material-ui/core/FormGroup';
import FormHelperText from '@material-ui/core/FormHelperText';
import Checkbox from '@material-ui/core/Checkbox';
import {URL} from "../other/components"

  
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
    formControl: {
      margin: theme.spacing(3),
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
  
  const Pharmacies = () => {
    const classes = useStyles();
  
    const [rows, setRows] = useState([]);
    const [searchList, setSearchList] = useState([]);
    const [filterList, setFilterList] = useState([]);
  
    const [copyRows, setCopyRows] = useState({});
    const [resetFilterAndSearch, setFilterAndSearch] = useState(false)
  
    const token = localStorage.getItem("token");
    const userId = localStorage.getItem("userId");
    const config = {
      headers: { Authorization: `Bearer ${token}`, consumes:'application/json' }
  };

    const [stateRating, setStateRating] = useState({
      one: false,
      two: false,
      three: false,
      four: false,
    });

    const [stateCopyRating, setStateCopyRating] = useState({
      one: false,
      two: false,
      three: false,
      four: false,
    });

   

    const [statePharmaciesAppointmentPerHour, setStatePharmaciesAppointmentPerHour] = useState({
      lessThen10P: false,
      lessThen20P: false,
      lessThen30P: false,
      lessThen40P: false,
      moreThen40P: false
    });

    const [stateCopyPharmaciesAppointmentPerHour, setStateCopyPharmaciesAppointmentPerHour] = useState({
      lessThen10P: false,
      lessThen20P: false,
      lessThen30P: false,
      lessThen40P: false,
      moreThen40P: false
    });

    const [stateDermatologistAppointmentPerHour, setStateDermatologistAppointmentPerHour] = useState({
      lessThen10D: false,
      lessThen20D: false,
      lessThen30D: false,
      lessThen40D: false,
      moreThen40D: false
    });

    const [stateCopyDermatologistAppointmentPerHour, setCopyStateDermatologistAppointmentPerHour] = useState({
      lessThen10D: false,
      lessThen20D: false,
      lessThen30D: false,
      lessThen40D: false,
      moreThen40D: false
    });

    const [searchValue, setSearchValue] = useState('');
    const [openFilterDialog, setOpenFilterDialog] = useState(false)
    const { one, two, three, four} = stateRating;
    const { lessThen10P,lessThen20P,lessThen30P,lessThen40P,moreThen40P} = statePharmaciesAppointmentPerHour;
    const { lessThen10D,lessThen20D,lessThen30D,lessThen40D,moreThen40D} = stateDermatologistAppointmentPerHour;

    const handleChangeRating = (event) => {
      setStateRating({ ...stateRating, [event.target.name]: event.target.checked });
    };
    const handleChangePharmaciesAppointmentPerHour = (event) => {
      setStatePharmaciesAppointmentPerHour({ ...statePharmaciesAppointmentPerHour, [event.target.name]: event.target.checked });
    };
    const handleChangeDermatologistAppointmentPerHour = (event) => {
      setStateDermatologistAppointmentPerHour({ ...stateDermatologistAppointmentPerHour, [event.target.name]: event.target.checked });
    };
    
  
    useEffect(() => {
      axios
        .get(
          URL + "/api/pharmacy/all"
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
            URL + "/api/pharmacy/sortByName/" +
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
          URL + "/api/pharmacy/sortByCity/" +
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
          URL + "/api/pharmacy/sortByRating/" +
            (ratingAsc.asc ? "asc" : "desc"),
          rows,config
        )
        .then((res) => {
          setRows(res.data);
        });
    };
  
  
    const searchPharmacy = (e) => {
      e = e.trim();
      setSearchValue(e);
    };

    const openProfile = (row) => {
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
          {userId != null && <TableCell className={classes.hederCell} >
            Profile
          </TableCell>}
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
            {userId != null && <TableCell><Button 
            variant="text"
            color="secondary"
             onClick={() => openProfile(row)}>              
                  <Link
                     to={"/patient/pharmacyProfilePage/" + row.id}
                    style={{ textDecoration: "none", color: "gray" }}
                  >
                   Open
                  </Link>
              </Button></TableCell>}
          </TableRow>
        ))}
      </TableBody>
    );

    const HandleClickSearchPharmacy = () => {
      if(searchValue !== '' && searchValue !== undefined){
        var listForSearch;
        if( !one && !two && !three && !four && !lessThen10P && !lessThen20P && !lessThen30P && !lessThen40P && !moreThen40P && !lessThen10D && !lessThen20D && !lessThen30D && !lessThen40D && !moreThen40D){
          listForSearch = copyRows
        }else{
          listForSearch = filterList
        }

          axios.put(URL + "/api/pharmacy/search/" + searchValue,listForSearch)
          .then(
            (res) => {
              setRows(res.data)
              setSearchList(res.data)
              setFilterAndSearch(true)

            }
          )
      }else{
        setRows(copyRows)
      }
    }



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

    const handleClickOpenFilterDialog= (row) => {
      setOpenFilterDialog(true)
    }
    const handleClickCloseFilterDialog = () => {
       
        setStateRating(stateCopyRating)
        setStatePharmaciesAppointmentPerHour(stateCopyPharmaciesAppointmentPerHour)
        setStateDermatologistAppointmentPerHour(stateCopyDermatologistAppointmentPerHour)
        setOpenFilterDialog(false);

    };

    const handleClickOpenFilterPharmacies = () => {
      if( !one && !two && !three && !four && !lessThen10P && !lessThen20P && !lessThen30P && !lessThen40P && !moreThen40P && !lessThen10D && !lessThen20D && !lessThen30D && !lessThen40D && !moreThen40D){
        HandleClickSearchPharmacy()
        setStateCopyRating(stateRating)
        setCopyStateDermatologistAppointmentPerHour(stateDermatologistAppointmentPerHour)
        setStateCopyPharmaciesAppointmentPerHour(statePharmaciesAppointmentPerHour)
        setOpenFilterDialog(false);

      }else{
        var ratings = [one,two,three,four];
        var perHourPharmacist = [lessThen10P,lessThen20P,lessThen30P,lessThen40P,moreThen40P];
        var perHourDermatologist = [lessThen10D,lessThen20D,lessThen30D,lessThen40D,moreThen40D];
        var listForFilter;
        if(searchValue !== '' && searchValue !== undefined){
            listForFilter = searchList
        }else{

            listForFilter = copyRows
        }
        var filterDTO = {
          filterListForRating: ratings,
          filterListForPharmacistAppointmentPricePerHour: perHourPharmacist,
          filterListForDermatologistAppointmentPricePerHour: perHourDermatologist,
          pharmacyDTOS: listForFilter
        }
  
        axios.put(URL + "/api/pharmacy/filter",filterDTO)
        .then((res) => {
          setRows(res.data)
          setFilterList(res.data)
          setStateCopyRating(stateRating)
          setCopyStateDermatologistAppointmentPerHour(stateDermatologistAppointmentPerHour)
          setStateCopyPharmaciesAppointmentPerHour(statePharmaciesAppointmentPerHour)
          setOpenFilterDialog(false);
          setFilterAndSearch(true)

        }).catch(error => {
          console.log(error)
        })
      }
     


    }

    const handleClickReset = () => {
      setStateRating({one:false,two:false,three:false,four:false})
      setStatePharmaciesAppointmentPerHour({lessThen10P: false,lessThen20P: false,lessThen30P: false,lessThen40P: false,moreThen40P: false})
      setStateDermatologistAppointmentPerHour({ lessThen10D: false,lessThen20D: false,lessThen30D: false,lessThen40D: false,moreThen40D: false})
      setStateCopyRating({one:false,two:false,three:false,four:false})
      setStateCopyPharmaciesAppointmentPerHour({lessThen10P: false,lessThen20P: false,lessThen30P: false,lessThen40P: false,moreThen40P: false})
      setCopyStateDermatologistAppointmentPerHour({ lessThen10D: false,lessThen20D: false,lessThen30D: false,lessThen40D: false,moreThen40D: false})
      setSearchValue("")
      setRows(copyRows)
      setFilterAndSearch(false)

    }

    const CreateFilterDialog = (
      <div>
          <Dialog onClose={handleClickCloseFilterDialog} aria-labelledby="customized-dialog-title" open={openFilterDialog} fullWidth='true'
        maxWidth='md'>
            <DialogTitle id="customized-dialog-title" onClose={handleClickCloseFilterDialog}>
              
            </DialogTitle>
            <DialogContent dividers>
           
                <Typography gutterBottom>
                <FormControl component="fieldset" className={classes.formControl}>
                  <FormLabel component="legend">Rating</FormLabel>
                  <FormGroup>
                    <FormControlLabel
                      control={<Checkbox checked={one} onChange={handleChangeRating} name="one" />}
                      label="0-2"
                    />
                    <FormControlLabel
                      control={<Checkbox checked={two} onChange={handleChangeRating} name="two" />}
                      label="2-3"
                    />
                    <FormControlLabel
                      control={<Checkbox checked={three} onChange={handleChangeRating} name="three" />}
                      label="3-4"
                    />
                     <FormControlLabel
                      control={<Checkbox checked={four} onChange={handleChangeRating} name="four" />}
                      label=">4"
                    />
                     
                  </FormGroup>
                  <FormHelperText>Be careful</FormHelperText>
                </FormControl>
                <FormControl  component="fieldset2" className={classes.formControl}>
                  <FormLabel component="legend2">Pharmacist appointment per hour</FormLabel>
                  <FormGroup>
                    <FormControlLabel
                      control={<Checkbox checked={lessThen10P} onChange={handleChangePharmaciesAppointmentPerHour} name="lessThen10P" />}
                      label="<10"
                    />
                    <FormControlLabel
                      control={<Checkbox checked={lessThen20P} onChange={handleChangePharmaciesAppointmentPerHour} name="lessThen20P" />}
                      label="<20"
                    />
                    <FormControlLabel
                      control={<Checkbox checked={lessThen30P} onChange={handleChangePharmaciesAppointmentPerHour} name="lessThen30P" />}
                      label="<30"
                    />
                    <FormControlLabel
                      control={<Checkbox checked={lessThen40P} onChange={handleChangePharmaciesAppointmentPerHour} name="lessThen40P" />}
                      label="<40"
                    />
                    <FormControlLabel
                      control={<Checkbox checked={moreThen40P} onChange={handleChangePharmaciesAppointmentPerHour} name="moreThen40P" />}
                      label=">40"
                    />
                  </FormGroup>
                  <FormHelperText>You can display an error</FormHelperText>
                </FormControl>

                <FormControl  component="fieldset2" className={classes.formControl}>
                  <FormLabel component="legend2">Dermatologist appointment per hour</FormLabel>
                  <FormGroup>
                    <FormControlLabel
                      control={<Checkbox checked={lessThen10D} onChange={handleChangeDermatologistAppointmentPerHour} name="lessThen10D" />}
                      label="<10"
                    />
                    <FormControlLabel
                      control={<Checkbox checked={lessThen20D} onChange={handleChangeDermatologistAppointmentPerHour} name="lessThen20D" />}
                      label="<20"
                    />
                    <FormControlLabel
                      control={<Checkbox checked={lessThen30D} onChange={handleChangeDermatologistAppointmentPerHour} name="lessThen30D" />}
                      label="<30"
                    />
                    <FormControlLabel
                      control={<Checkbox checked={lessThen40D} onChange={handleChangeDermatologistAppointmentPerHour} name="lessThen40D" />}
                      label="<40"
                    />
                    <FormControlLabel
                      control={<Checkbox checked={moreThen40D} onChange={handleChangeDermatologistAppointmentPerHour} name="moreThen40D" />}
                      label=">40"
                    />
                  </FormGroup>
                  <FormHelperText>You can display an error</FormHelperText>
                </FormControl>
                </Typography>
            
  
            </DialogContent>
            <DialogActions>
                <Button onClick={handleClickCloseFilterDialog} autoFocus color="primary">
                Close
                </Button>
                <Button onClick={handleClickOpenFilterPharmacies} autoFocus color="primary">
                Save
                </Button>
            </DialogActions>
          </Dialog>
      </div>
  
    )
  
    const SearchPart = (
      <Grid container spacing={1} className={classes.table}>
        <Grid item xs={2} />
        <Grid item xs={8} style={{ margin: "auto", textAlign: "right" }}>
        {resetFilterAndSearch && <Button style={{backgroundColor:"red", marginRight:"auto"}} onClick={handleClickReset}>Reset</Button>}

        <Button  style={{backgroundColor:"gray",marginLeft:"3%"}}  onClick={handleClickOpenFilterDialog}>Filter</Button>
          <TextField
            id="outlined-search"
            label="Search pharmacy"
            type="search"
            size="small"
            variant="outlined"
            value={searchValue}
            style={{ width: "60%" }}
            onChange={(e) => searchPharmacy(e.target.value)}
          />
          <Button  style={{backgroundColor:"gray"}} onClick={HandleClickSearchPharmacy}>Search</Button>
        </Grid>
        <Grid item xs={2}></Grid>
        <Grid item xs={2} />
      </Grid>
    );
  
    return (
      <div>
        <h3>All pharmacies</h3>
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
        {CreateFilterDialog}
      </div>
    );
  };
  
  export default Pharmacies;
  