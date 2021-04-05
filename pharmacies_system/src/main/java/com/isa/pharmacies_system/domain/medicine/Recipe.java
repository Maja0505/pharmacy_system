package com.isa.pharmacies_system.domain.medicine;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.isa.pharmacies_system.domain.user.Patient;

import javax.persistence.*;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="recipes")
public class Recipe {

    @Id
    @SequenceGenerator(name = "mySeqGenRecipe", sequenceName = "mySeqRecipe", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mySeqGenRecipe")
    private long id;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Patient patientForRecipe;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Instant creationDate;

    //spisak lekova sa kolicinama
    @OneToMany(mappedBy = "recipe", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<RecipeItem> recipeItems = new HashSet<RecipeItem>();

    public Recipe() {

    }

    public void setId(long id) {
        this.id = id;
    }

    public void setPatientForRecipe(Patient patientForRecipe) {
        this.patientForRecipe = patientForRecipe;
    }

    public void setCreationDate(Instant creationDate) {
        this.creationDate = creationDate;
    }

    public void setRecipeItems(Set<RecipeItem> recipeItems) {
        this.recipeItems = recipeItems;
    }

    public long getId() {
        return id;
    }

    public Patient getPatientForRecipe() {
        return patientForRecipe;
    }

    public Instant getCreationDate() {
        return creationDate;
    }

    public Set<RecipeItem> getRecipeItems() {
        return recipeItems;
    }

    public Recipe(long id, Patient patientForRecipe, Instant creationDate, Set<RecipeItem> recipeItems) {
        this.id = id;
        this.patientForRecipe = patientForRecipe;
        this.creationDate = creationDate;
        this.recipeItems = recipeItems;
    }
}
