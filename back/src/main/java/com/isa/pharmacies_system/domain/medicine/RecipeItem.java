package com.isa.pharmacies_system.domain.medicine;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
@Table(name="recipe_items")
public class RecipeItem extends Item{

    @JsonBackReference
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Recipe recipe;

    @Column
    private String recommendedDailyIntake;

    public RecipeItem() {

    }

    public String getRecommendedDailyIntake() {
        return recommendedDailyIntake;
    }

    public void setRecommendedDailyIntake(String recommendedDailyIntake) {
        this.recommendedDailyIntake = recommendedDailyIntake;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public RecipeItem(Recipe recipe) {
        this.recipe = recipe;
    }
}
