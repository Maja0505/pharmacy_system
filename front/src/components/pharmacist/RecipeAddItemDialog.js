import Button from "@material-ui/core/Button";
import Dialog from "@material-ui/core/Dialog";
import DialogTitle from "@material-ui/core/DialogTitle";
import DialogContent from "@material-ui/core/DialogContent";
import DialogActions from "@material-ui/core/DialogActions";

import { TextField, Grid } from "@material-ui/core";

import { useState } from "react";

import axios from "axios";

const RecipeAddItemDialog = ({
  openDialog,
  setOpenDialog,
  selectedMedicine,
  report,
  setReport,
  setMedicinesInPharmacy,
  setIsAlternative,
  appointment,
  setOpenAlertSuccsess,
  setOpenAlertUnsuccses,
}) => {
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
        "http://localhost:8080/api/pharmacyStorageItem/check/" +
          selectedMedicine.itemId +
          "/" +
          medicineAmount +
          "/" +
          appointment.Id
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
      });
  };

  const addToRecipeItems = () => {
    setReport({
      ...report,
      recipeItemsDTO: report.recipeItemsDTO.concat({
        pharmacyItemId: selectedMedicine.itemId,
        medicineItemId: selectedMedicine.medicineId,
        medicineAmount: medicineAmount,
        recommendedDailyIntake: recomendeDailyIntake,
        medicineName: selectedMedicine.medicineName,
      }),
    });
    closeDialog();
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