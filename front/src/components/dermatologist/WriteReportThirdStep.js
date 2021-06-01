import {
  List,
  Grid,
  ListItem,
  ListItemText,
  Card,
  CardContent,
  Typography,
  Box,
  ListItemSecondaryAction,
  IconButton,
  TextareaAutosize,
  Button,
} from "@material-ui/core";

import LiveHelp from "@material-ui/icons/LiveHelp";
import { useState, useEffect } from "react";
import { makeStyles } from "@material-ui/core/styles";

import RecipeAddItemDialog from "./RecipeAddItemDialog.js";
import {URL} from "../other/components"


import axios from "axios";

const useStyles = makeStyles({
  cart: {
    fontSize: 15,
    backgroundColor: "#bed5e7",
    width: "80%",
    margin: "auto",
    marginTop: "5%",
  },
});

const WriteReportThirdStep = ({
  appointment,
  setOpenAlertSuccsess,
  setOpenAlertUnsuccses,
  recipeItems,
  setRecipeItems,
}) => {
  const token = localStorage.getItem("token");
  const userId = localStorage.getItem("userId");

  const classes = useStyles();

  const [selectedMedicine, setSelectedMedicine] = useState(null);
  const [showIngredients, setShowIngredients] = useState(false);
  const [showNotes, setShowNotes] = useState(false);
  const [openDialog, setOpenDialog] = useState(false);
  const [isAlternative, setIsAlternative] = useState(false);
  const [medicinesInPharmacy, setMedicinesInPharmacy] = useState([]);

  useEffect(() => {
    axios
      .get(
        URL + "/api/pharmacyStorageItem/all/" +
          appointment.PharmacyId,
        {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        }
      )
      .then((res) => {
        setMedicinesInPharmacy(res.data);
      });
  }, []);

  const getMedicineInfo = (medicine) => {
    setSelectedMedicine(medicine);
    setShowIngredients(false);
    setShowNotes(false);
  };

  const changeShowNotes = () => {
    setShowNotes(!showNotes);
  };

  const changeShowIngredients = () => {
    setShowIngredients(!showIngredients);
  };

  const addMedicineItemToReport = () => {
    setOpenDialog(true);
  };

  const seeAllMedicinesInPharmacy = () => {
    axios
      .get(
        URL + "/api/pharmacyStorageItem/all/" +
          appointment.PharmacyId,
        {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        }
      )
      .then((res) => {
        setMedicinesInPharmacy(res.data);
        setIsAlternative(false);
      });
  };

  return (
    <div>
      <Grid
        container
        spacing={0}
        style={{ marginTop: "2%", width: "80%", margin: "auto" }}
      >
        <Grid item xs={3}>
          {isAlternative === true && (
            <>
              <Typography>
                <b>Alternative medicine</b>
              </Typography>
              <Button
                variant="contained"
                color="primary"
                onClick={seeAllMedicinesInPharmacy}
              >
                SEE ALL MEDICINE IN PHARMACY
              </Button>
            </>
          )}
          {isAlternative === false && (
            <Typography>
              <b>Medicine in pharmacy</b>
            </Typography>
          )}
          <Box
            border={1}
            borderRadius={5}
            style={{ marginTop: "8%", borderColor: "#b8b8b8" }}
          >
            <List
              style={{
                maxHeight: "300px",
                overflow: "auto",
              }}
            >
              {medicinesInPharmacy.map((medicine) => (
                <li key={medicine.itemId}>
                  <ul>
                    <ListItem key={medicine.itemId}>
                      <ListItemText primary={`${medicine.medicineName}`} />
                      <ListItemSecondaryAction>
                        <IconButton onClick={() => getMedicineInfo(medicine)}>
                          <LiveHelp />
                        </IconButton>
                      </ListItemSecondaryAction>
                    </ListItem>
                  </ul>
                </li>
              ))}
            </List>
          </Box>
        </Grid>
        <Grid item xs={5}>
          <Typography>
            <b>Medcine info</b>
          </Typography>
          <Card className={classes.cart} variant="outlined">
            <CardContent>
              {selectedMedicine === null && (
                <Typography>
                  Select some medicine to see info about them
                </Typography>
              )}
              {selectedMedicine !== null && (
                <>
                  <table style={{ margin: "auto" }}>
                    <tbody>
                      <tr>
                        <td>
                          <Typography>Medicine : </Typography>
                        </td>
                        <td>
                          <Typography>
                            {selectedMedicine.medicineName}
                          </Typography>
                        </td>
                      </tr>
                      <tr>
                        <td style={{ paddingTop: "3%" }}>
                          <Typography>Type : </Typography>
                        </td>
                        <td>
                          <Typography>
                            {selectedMedicine.typeOfMedicine}
                          </Typography>
                        </td>
                      </tr>
                      <tr>
                        <td style={{ paddingTop: "3%" }}>
                          <Typography>Form of medicine : </Typography>
                        </td>
                        <td>
                          <Typography>
                            {selectedMedicine.formOfMedicine}
                          </Typography>
                        </td>
                      </tr>
                      <tr>
                        <td style={{ paddingTop: "3%" }}>
                          <Typography>Ingredients : </Typography>
                        </td>
                        <td>
                          <Button onClick={changeShowIngredients}>
                            {showIngredients === false ? "Show" : "Hide"}
                          </Button>
                        </td>
                      </tr>
                      <>
                        {showIngredients === true &&
                          selectedMedicine.ingredients.length === 0 && (
                            <tr>
                              <td colSpan="2">No ingredients</td>
                            </tr>
                          )}
                      </>
                      <>
                        {showIngredients === true &&
                          selectedMedicine.ingredients.length !== 0 && (
                            <>
                              {selectedMedicine.ingredients.map((medicine) => (
                                <tr key={medicine.id}>
                                  <td style={{ fontSize: "13px" }}>
                                    {medicine.name}
                                  </td>
                                </tr>
                              ))}
                            </>
                          )}
                      </>
                      <tr>
                        <td style={{ paddingTop: "3%" }}>
                          <Typography>Notes : </Typography>
                        </td>
                        <td>
                          <Button onClick={changeShowNotes}>
                            {showNotes === false ? "Show" : "Hide"}
                          </Button>
                        </td>
                      </tr>
                      {showNotes === true && (
                        <tr>
                          <td colSpan="2">
                            <TextareaAutosize
                              rowsMax={4}
                              rowsMin={4}
                              disabled={true}
                              defaultValue={selectedMedicine.notes}
                              style={{
                                resize: "none",
                                background: "#bed5e7",
                                color: "black",
                                width: "100%",
                                margin: "auto",
                              }}
                            />
                          </td>
                        </tr>
                      )}
                      <tr>
                        <td style={{ paddingTop: "3%" }}>
                          <Typography>Amount in pharmacy : </Typography>
                        </td>
                        <td>
                          <Typography>
                            {selectedMedicine.medicineAmount}
                          </Typography>
                        </td>
                      </tr>
                    </tbody>
                  </table>
                  <Button
                    onClick={() => addMedicineItemToReport()}
                    variant="contained"
                    color="primary"
                    style={{ marginTop: "2%" }}
                  >
                    Add to recipe items
                  </Button>
                </>
              )}
            </CardContent>
          </Card>
        </Grid>
        <Grid item xs={3}>
          <Typography>
            <b>Recipe items</b>
          </Typography>

          <Box
            border={1}
            borderRadius={5}
            style={{
              marginTop: "8%",
              borderColor: "#b8b8b8",
              overflow: "auto",
              maxHeight: "300px",
              minHeight: "62px",
            }}
          >
            {recipeItems.length === 0 && (
              <Typography style={{ marginTop: "5%" }}>
                Add some medicine from pharmacy
              </Typography>
            )}
            {recipeItems.length !== 0 && (
              <>
                <table
                  style={{
                    margin: "auto",
                  }}
                >
                  <thead>
                    <th>Name</th>
                    <th>Amount</th>
                  </thead>
                  <tbody>
                    {recipeItems.map((reportItem, index) => (
                      <tr key={index}>
                        <td>{reportItem.medicineName}</td>
                        <td>{reportItem.medicineAmount}</td>
                      </tr>
                    ))}
                  </tbody>
                </table>
              </>
            )}
          </Box>
        </Grid>
      </Grid>
      <RecipeAddItemDialog
        openDialog={openDialog}
        setOpenDialog={setOpenDialog}
        selectedMedicine={selectedMedicine}
        setMedicinesInPharmacy={setMedicinesInPharmacy}
        setIsAlternative={setIsAlternative}
        appointment={appointment}
        setOpenAlertSuccsess={setOpenAlertSuccsess}
        setOpenAlertUnsuccses={setOpenAlertUnsuccses}
        recipeItems={recipeItems}
        setRecipeItems={setRecipeItems}
      />
    </div>
  );
};

export default WriteReportThirdStep;
