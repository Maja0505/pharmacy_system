import {
    Table,
    TableBody,
    TableCell,
    TableHead,
    TableRow,
    TextField,
    Button,
    Grid,
    TableContainer
  } from "@material-ui/core";
  import { makeStyles } from '@material-ui/core/styles';
  import Autocomplete from '@material-ui/lab/Autocomplete';
  import React, { useEffect, useState ,useRef,createRef} from "react";
  import axios from "axios";
  import CloseIcon from '@material-ui/icons/Close';
  import IconButton from '@material-ui/core/IconButton';
  import {URL} from "../other/components"






  const useStyles = makeStyles((theme) => ({ //style za paper deo
    textField: {
        marginLeft: theme.spacing(1),
        marginRight: theme.spacing(1),
        width: '25ch',
      },
      table: {
        marginTop: "5%",
      },
      hederRow: {
        background: "#e57373",
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
      TableCell: {
       
            backgroundColor: '#e57373',
            color: "white" 
        
      },
     
  
  }));




const Allergies = ({allergies,setAllergies,patientId}) => {

    const classes = useStyles()
    const token = localStorage.getItem("token");
    const userId = localStorage.getItem("userId");
    const config = {
      headers: { Authorization: `Bearer ${token}` }
  };

    useEffect(() => {
        getMedicine()
    },[])

    const [medicines,setMedicines] = useState([]);
    const [selectedMedicine, setSelectedMedicine] = useState(null)

    const getMedicine = async () => {
        axios.get('http://localhost:8080/api/medicine/all/short', config)
        .then((res)=>{
            setMedicines(res.data)
        }).catch((err) => {
            console.log(err);
        });
       
    }

    const HandleAddButton = () => {
      if(selectedMedicine != undefined && selectedMedicine != null){
        axios.put(URL + "/api/patient/" + userId + "/addMedicineAllergies/" + selectedMedicine.medicineId,{},config)
        .then((res) => {
            if(res.data){
              setAllergies([...allergies,selectedMedicine])
              setSelectedMedicine({})  
            }
          }
        )
      }
    }

    const HandleClickRemoveAllerge = (row) => {
      axios.put(URL + "/api/patient/" + userId + "/removeMedicineAllergies/" + row.medicineId,{},config)
        .then((res) => {
            if(res.data){
              var index = allergies.indexOf(row)
              if(index != -1){
                allergies.splice(index,1)
              }
              setSelectedMedicine({})  
            }
          }
        )
    }

    const TableHeader = (
        <TableHead>
          <TableRow >
            <TableCell className={classes.TableCell}>
              Allergies
            </TableCell>
            <TableCell className={classes.TableCell}> </TableCell>
          </TableRow>
        </TableHead>
      );
    
      const TableContent = (
        <TableBody>
            {allergies.map((allergie, index) => (
            <TableRow key={index}>
              <TableCell >{allergie.medicineName}</TableCell>
              <TableCell>
                <IconButton aria-label="close" className={classes.closeButton} onClick={() => HandleClickRemoveAllerge(allergie)}>
                <CloseIcon />
              </IconButton>
              </TableCell>
            </TableRow>
             ))}
        </TableBody>
      );
    

    return (
        <div>
            <Grid container spacing={1} >
                <Grid container justify="center" >
                    <Grid  item xs={20}>
                    <Autocomplete
                      className = "autocoplete"
                          id="combo-box-demo"
                          options={medicines}
                          getOptionLabel={(option) => option.medicineName}
                          style={{ width: 200}}
                          renderInput={(params) => <TextField value ={selectedMedicine} {...params} label="add allergies" variant="outlined" />}
                          onChange={(event, value) => setSelectedMedicine(value)} 
                      />
                    </Grid>
                    <Grid item>
                    <Button onClick={HandleAddButton} variant="contained" color="primary" style={{height:'100%'}}>Add</Button>
                    </Grid>
                </Grid>
            </Grid>
            <br></br>
            <TableContainer style={{ height: "450px", marginTop: "2%" }}>
              <Table stickyHeader aria-label="sticky table">
                  {TableHeader}
                  {TableContent}
              </Table>
            </TableContainer>
            
        </div>
    )
}

export default Allergies
