import {
  Grid,
  Card,
  CardContent,
  Typography,
  Button,
  TextField,
} from "@material-ui/core";

import { makeStyles } from "@material-ui/core/styles";
import { Redirect } from "react-router-dom";
import { useState, useEffect } from "react";

import axios from "axios";
import { URL, REACT_URL } from "../other/components";

const useStyles = makeStyles({
  cart: {
    fontSize: 15,
    backgroundColor: "#bed5e7",
    width: "80%",
    margin: "auto",
  },
  button: {
    margin: "auto",
  },
});

const MedicineReservation = () => {
  const token = localStorage.getItem("token");
  const userId = localStorage.getItem("userId");
  const [pharamcyId, setPharmacyId] = useState();

  const [redirection, setRedirection] = useState(false);

  const classes = useStyles();
  const [medicineReservationId, setMedicineReservationId] = useState("");
  const [haveReservation, setHaveReservation] = useState(null);
  const [medicineReservation, setMedicineReservation] = useState({});

  useEffect(() => {
    axios
      .get(URL + "/api/pharmacist/getPharmacyId/" + userId, {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      })
      .then((res) => {
        setPharmacyId(res.data);
      })
      .catch((error) => {
        if (error.response.status === 401) {
          setRedirection(true);
        }
      });
  }, []);

  const changeMedicineReservationId = (text) => {
    setMedicineReservationId(text);
  };

  const searchReservation = () => {
    axios
      .get(
        URL +
          "/api/medicineReservation/get/" +
          medicineReservationId +
          "/" +
          pharamcyId,
        {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        }
      )
      .then((res) => {
        setMedicineReservation(res.data);
        setHaveReservation(true);
      })
      .catch((error) => {
        if (error.response.status === 401) {
          setRedirection(true);
        }
        setHaveReservation(false);
      });
  };

  const changeStatusOfReservation = () => {
    axios
      .put(
        URL + "/api/medicineReservation/finish/" + medicineReservationId,
        {},
        {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        }
      )
      .then((res) => {
        setMedicineReservation({
          ...medicineReservation,
          statusOfMedicineReservation: "FINISHED",
        });
      })
      .catch((error) => {
        if (error.response.status === 401) {
          setRedirection(true);
        }
      });
  };

  const ContentWhenHaveReservation = (
    <>
      {haveReservation !== null && haveReservation === true && (
        <>
          <Grid container>
            <Grid item xs={2} />
            <Grid item xs={6} style={{ margin: "auto", marginTop: "2%" }}>
              <Card className={classes.cart} variant="outlined">
                <CardContent>
                  <Typography>
                    <b>Patient personal info</b>
                  </Typography>
                  <Typography>
                    First name : {medicineReservation.patientFirstName}
                  </Typography>
                  <Typography>
                    Last name : {medicineReservation.patientLastName}
                  </Typography>
                  <Typography>
                    Email : {medicineReservation.patientEmail}
                  </Typography>
                  <Typography>
                    Phone number : {medicineReservation.patientPhoneNumber}
                  </Typography>
                </CardContent>
              </Card>
            </Grid>
            <Grid item xs={2} />
          </Grid>
          <Grid container>
            <Grid item xs={2} />
            <Grid item xs={6} style={{ margin: "auto", marginTop: "1%" }}>
              <Card className={classes.cart} variant="outlined">
                <CardContent>
                  <Typography>
                    <b>Medicine reservation info</b>
                  </Typography>
                  <Typography>
                    Medicine : {medicineReservation.medicineName}
                  </Typography>
                  <Typography>
                    Code : {medicineReservation.medicineCode}
                  </Typography>
                  <Typography>
                    Type : {medicineReservation.typeOfMedicine}
                  </Typography>
                  <Typography>
                    Form : {medicineReservation.formOfMedicine}
                  </Typography>
                  <Typography>
                    Manufacturer : {medicineReservation.manufacturerOfMedicine}
                  </Typography>
                  <Typography>
                    Last date of taking :{" "}
                    {medicineReservation.dateOfTakingMedicine.split("-")[2] +
                      "." +
                      medicineReservation.dateOfTakingMedicine.split("-")[1] +
                      "." +
                      medicineReservation.dateOfTakingMedicine.split("-")[0]}
                  </Typography>
                  <Typography>
                    Reservation status :{" "}
                    {medicineReservation.statusOfMedicineReservation}
                  </Typography>
                </CardContent>
              </Card>
            </Grid>
            <Grid item xs={2} />
          </Grid>
          {medicineReservation.statusOfMedicineReservation === "CREATED" && (
            <Button
              variant="contained"
              color="primary"
              style={{ width: "15%", margin: "auto", marginTop: "1%" }}
              onClick={changeStatusOfReservation}
            >
              Dispose reservation
            </Button>
          )}
        </>
      )}
    </>
  );
  const ContentWhenNotHaveReservation = (
    <>
      {haveReservation !== null && haveReservation === false && (
        <>
          <Grid container>
            <Grid item xs={2} />
            <Grid item xs={6} style={{ margin: "auto", marginTop: "2%" }}>
              <Card className={classes.cart} variant="outlined">
                <CardContent>
                  <Typography>
                    <b>Number of reservation is not valid</b>
                  </Typography>
                  <Typography>Possible reasons :</Typography>
                  <Typography> 1. Medicine reservation id </Typography>
                  <Typography> is wrong or doesn't exist</Typography>
                  <Typography> 2. Deadline for taking medicine</Typography>
                  <Typography> is less than 24 hours</Typography>
                </CardContent>
              </Card>
            </Grid>
            <Grid item xs={2} />
          </Grid>
        </>
      )}
    </>
  );

  return (
    <div>
      {redirection === true && <Redirect to="/login"></Redirect>}
      <Grid container>
        <Grid item xs={2} />
        <Grid item xs={6} style={{ margin: "auto", marginTop: "5%" }}>
          <TextField
            variant="outlined"
            size="small"
            style={{ width: "60%" }}
            label="Medicine reservation id"
            onChange={(e) => changeMedicineReservationId(e.target.value)}
          ></TextField>
          <Button
            variant="contained"
            color="primary"
            style={{ width: "20%", marginLeft: "5%" }}
            disabled={medicineReservationId.trim() === ""}
            onClick={searchReservation}
          >
            Search
          </Button>
        </Grid>
        <Grid item xs={2} />
      </Grid>
      {ContentWhenHaveReservation}
      {ContentWhenNotHaveReservation}
    </div>
  );
};

export default MedicineReservation;
