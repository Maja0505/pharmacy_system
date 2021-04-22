package com.isa.pharmacies_system.DTO;

public class RecipeItemDTO {

    private Long medicineItemId;
    private Long medicineAmount;
    private String recommendedDailyIntake;

    public RecipeItemDTO() {
    }

    public Long getMedicineItemId() {
        return medicineItemId;
    }

    public void setMedicineItemId(Long medicineItemId) {
        this.medicineItemId = medicineItemId;
    }

    public Long getMedicineAmount() {
        return medicineAmount;
    }

    public void setMedicineAmount(Long medicineAmount) {
        this.medicineAmount = medicineAmount;
    }

    public String getRecommendedDailyIntake() {
        return recommendedDailyIntake;
    }

    public void setRecommendedDailyIntake(String recommendedDailyIntake) {
        this.recommendedDailyIntake = recommendedDailyIntake;
    }
}
