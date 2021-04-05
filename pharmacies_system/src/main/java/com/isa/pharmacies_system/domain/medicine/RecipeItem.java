package com.isa.pharmacies_system.domain.medicine;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
@Table(name="recipe_items")
public class RecipeItem extends Item{

    @JsonBackReference
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Recipe recipe;

    public RecipeItem() {

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
