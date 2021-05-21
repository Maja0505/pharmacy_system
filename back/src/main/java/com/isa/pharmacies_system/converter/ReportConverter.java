package com.isa.pharmacies_system.converter;

import com.isa.pharmacies_system.DTO.RecipeItemDTO;
import com.isa.pharmacies_system.DTO.ReportForPatientDTO;
import com.isa.pharmacies_system.domain.medicine.*;
import com.isa.pharmacies_system.domain.report.DermatologistReport;
import com.isa.pharmacies_system.domain.report.PharmacistReport;
import com.isa.pharmacies_system.service.iService.IMedicineService;

import java.util.HashSet;
import java.util.Set;

public class ReportConverter {

    //Nemanja
    public DermatologistReport convertReportDTOToDermatologistReport(ReportForPatientDTO reportForPatientDTO, IMedicineService medicineService){
        DermatologistReport dermatologistReport = new DermatologistReport();
        dermatologistReport.setReportInfo(reportForPatientDTO.getReportInfo());
        dermatologistReport.setRecipeForDermatologistReport(createRecipeForReport(reportForPatientDTO,medicineService));
        return dermatologistReport;
    }

    //Nemanja
    public PharmacistReport convertReportDTOTOPharmacistReport(ReportForPatientDTO reportForPatientDTO, IMedicineService medicineService) {
        PharmacistReport pharmacistReport = new PharmacistReport();
        pharmacistReport.setReportInfo(reportForPatientDTO.getReportInfo());
        pharmacistReport.setRecipeForPharmacistReport(createRecipeForReport(reportForPatientDTO,medicineService));
        return pharmacistReport;
    }

    //Nemanja
    private Recipe createRecipeForReport(ReportForPatientDTO reportForPatientDTO, IMedicineService medicineService) {
        Recipe recipe = new Recipe();
        recipe.setCreationDate(reportForPatientDTO.getCreationDate());
        recipe.setRecipeItems(convertReportDTORecipeItemsToRecipeItems(reportForPatientDTO,medicineService,recipe));
        return recipe;
    }

    //Nemanja
    private Set<RecipeItem> convertReportDTORecipeItemsToRecipeItems(ReportForPatientDTO reportForPatientDTO,IMedicineService medicineService,Recipe recipe) {
        Set<RecipeItem> recipeItems = new HashSet<>();
        for (RecipeItemDTO recipeItemDTO:
             reportForPatientDTO.getRecipeItemsDTO()) {
            recipeItems.add(convertRecipeItemDTOToRecipeItem(recipeItemDTO,medicineService,recipe));
        }
        return recipeItems;
    }

    //Nemanja
    private RecipeItem convertRecipeItemDTOToRecipeItem(RecipeItemDTO recipeItemDTO,IMedicineService medicineService,Recipe recipe) {
        RecipeItem recipeItem = new RecipeItem();
        recipeItem.setRecommendedDailyIntake(recipeItemDTO.getRecommendedDailyIntake());
        recipeItem.setMedicineAmount(recipeItemDTO.getMedicineAmount());
        recipeItem.setTypeOfItem(TypeOfItem.Recipe_item);
        recipeItem.setMedicineItem(medicineService.findOne(recipeItemDTO.getMedicineItemId()));
        recipeItem.setRecipe(recipe);
        return recipeItem;
    }


}
