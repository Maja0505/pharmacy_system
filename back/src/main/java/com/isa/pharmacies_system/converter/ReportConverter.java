package com.isa.pharmacies_system.converter;

import com.isa.pharmacies_system.DTO.RecipeItemDTO;
import com.isa.pharmacies_system.DTO.ReportForPatientDTO;
import com.isa.pharmacies_system.domain.medicine.Recipe;
import com.isa.pharmacies_system.domain.medicine.RecipeItem;
import com.isa.pharmacies_system.domain.medicine.TypeOfItem;
import com.isa.pharmacies_system.domain.report.DermatologistReport;
import com.isa.pharmacies_system.service.iService.IMedicineService;

import java.util.HashSet;
import java.util.Set;

public class ReportConverter {

    public DermatologistReport convertReportDTOToDermatologistReport(ReportForPatientDTO reportForPatientDTO, IMedicineService medicineService){
        DermatologistReport dermatologistReport = new DermatologistReport();
        Recipe recipe = new Recipe();
        dermatologistReport.setReportInfo(reportForPatientDTO.getReportInfo());
        recipe.setCreationDate(reportForPatientDTO.getCreationDate());
        recipe.setRecipeItems(convertReportDTORecipeItemsToRecipeItems(reportForPatientDTO,medicineService,recipe));
        dermatologistReport.setRecipeForDermatologistReport(recipe);
        return dermatologistReport;
    }

    private Set<RecipeItem> convertReportDTORecipeItemsToRecipeItems(ReportForPatientDTO reportForPatientDTO,IMedicineService medicineService,Recipe recipe) {
        Set<RecipeItem> recipeItems = new HashSet<>();
        for (RecipeItemDTO recipeItemDTO:
             reportForPatientDTO.getRecipeItemsDTO()) {
            recipeItems.add(convertRecipeItemDTOToRecipeItem(recipeItemDTO,medicineService,recipe));
        }
        return recipeItems;
    }

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
