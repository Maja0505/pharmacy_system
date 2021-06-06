import Button from "@material-ui/core/Button";
import Dialog from "@material-ui/core/Dialog";
import DialogTitle from "@material-ui/core/DialogTitle";
import DialogContent from "@material-ui/core/DialogContent";
import DialogActions from "@material-ui/core/DialogActions";

import { TextField, Grid } from "@material-ui/core";
import {Redirect} from "react-router-dom"
import { useState } from "react";

import axios from "axios";
import {URL} from "../other/components"


const RecipeAddItemDialog = ({
  openDialog,
  setOpenDialog,
  selectedMedicine,
  setMedicinesInPharmacy,
  setIsAlternative,
  appointment,
  setOpenAlertSuccsess,
  setOpenAlertUnsuccses,
  recipeItems,
  setRecipeItems,
}) => {
  const token = localStorage.getItem("token");
  const userId = localStorage.getItem("userId");
  const [redirection,setRedirection] = useState(false)
  const [recomendeDailyIntake, setRecomendedDailyIntake] = useState("");
  const [medicineAmount, setMedicineAmount] = useState(1);
  const [haveEnough, setHaveEnought] = useState(null);
  const [alternativeMedicines, setAlternativeMedicines] = useState([]);

  const closeDialog = () => {
    setOpenDialog(false);
    setHaveEnought(null);
    setRecomendedDailyIntake("");
    setMedicineAmount(1);
  };

  const writeDailyIntake = (text) => {
    setRecomendedDailyIntake(text);
  };

  const medicineAmountChange = (number) => {
    setMedicineAmount(number);
  };

  const checkAvailability = () => {
    axios
      .get(
        URL + "/api/pharmacyStorageItem/check/" +
          selectedMedicine.itemId +
          "/" +
          medicineAmount +
          "/" +
          appointment.Id,
        {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        }
      )
      .then((res) => {
        if (res.status === 202) {
          setHaveEnought(false);
          setAlternativeMedicines(res.data);
          setOpenAlertUnsuccses(true);
        } else {
          setOpenAlertSuccsess(true);
          setHaveEnought(true);
        }
      }).catch((error) => {
        if(error.response.status === 401){
          setRedirection(true)
        }
      });
  };

  const addToRecipeItems = async () => {
    if (recipeItems.length === 0) {
      addNewItem();
    }
    for (var i = 0; i < recipeItems.length; i++) {
      if (recipeItems[i].pharmacyItemId === selectedMedicine.itemId) {
        addExistingItem();
        break;
      }
      if (i === recipeItems.length - 1) {
        addNewItem();
      }
    }
    closeDialog();
  };

  const addExistingItem = () => {
    setRecipeItems(
      recipeItems.map((i) =>
        i.pharmacyItemId === selectedMedicine.itemId
          ? {
              ...i,
              medicineAmount: Number(i.medicineAmount) + Number(medicineAmount),
            }
          : i
      )
    );
  };

  const addNewItem = () => {
    setRecipeItems((oldArray) => [
      ...oldArray,
      {
        pharmacyItemId: selectedMedicine.itemId,
        medicineItemId: selectedMedicine.medicineId,
        medicineAmount: Number(medicineAmount),
        recommendedDailyIntake: recomendeDailyIntake,
        medicineName: selectedMedicine.medicineName,
      },
    ]);
  };

  const seeAlternativesMedicine = () => {
    setMedicinesInPharmacy(alternativeMedicines);
    setIsAlternative(true);
    closeDialog();
  };

  return (
    <Dialog
      onClose={closeDialog}
      aria-labelledby="customized-dialog-title"
      open={openDialog}
    >
      <DialogTitle
        id="customized-dialog-title"
        onClose={closeDialog}
        style={{ color: "#1a237e", margin: "auto" }}
      >
        RECIPE ITEM
      </DialogTitle>
      <DialogContent>
        <Grid container>
          <Grid item xs={12} style={{ margin: "auto" }}>
            <TextField
              id="outlined-multiline-static"
              label="Recomended daily intake"
              variant="outlined"
              multiline
              rows={4}
              onChange={(e) => writeDailyIntake(e.target.value)}
            />
          </Grid>
        </Grid>
        <Grid container>
          <Grid item xs={12} style={{ margin: "auto", marginTop: "10%" }}>
            <TextField
              variant="outlined"
              label="Medicine amount for patient"
              type="number"
              defaultValue={1}
              InputProps={{ inputProps: { min: 1 } }}
              onChange={(e) => medicineAmountChange(e.target.value)}
            />
          </Grid>
        </Grid>
        {haveEnough !== null && (
          <>
            <Grid container>
              <Grid item xs={12} style={{ margin: "auto", marginTop: "10%" }}>
                {haveEnough === false && alternativeMedicines.length !== 0 && (
                  <Button
                    autoFocus
                    color="primary"
                    onClick={seeAlternativesMedicine}
                  >
                    SEE ALTERNATIVES MEDICINE
                  </Button>
                )}
                {haveEnough === false && alternativeMedicines.length === 0 && (
                  <div style={{ margin: "auto" }}>
                    <div>
                      <label>There are no alternative medicine</label>
                    </div>
                    <div>
                      <label>in storage with a selected amount</label>
                    </div>
                    <div>
                      <label>
                        for medicine {selectedMedicine.medicineName}
                      </label>
                    </div>
                  </div>
                )}
                {haveEnough !== false && (
                  <Button autoFocus color="primary" onClick={addToRecipeItems}>
                    ADD TO RECIPE
                  </Button>
                )}
              </Grid>
            </Grid>
          </>
        )}
      </DialogContent>
      <DialogActions>
        <Button autoFocus color="secondary" onClick={closeDialog}>
          CLOSE
        </Button>
        <Button
          autoFocus
          color="primary"
          onClick={checkAvailability}
          disabled={recomendeDailyIntake.trim() === ""}
        >
          CHECK FOR MEDICINE
        </Button>
      </DialogActions>
    </Dialog>
  );
};

RecipeAddItemDialog.defaultProps = {
  openDialog: false,
};

export default RecipeAddItemDialog;
