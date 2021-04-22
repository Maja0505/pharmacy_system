package com.isa.pharmacies_system.domain.medicine;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.isa.pharmacies_system.domain.user.Patient;

import javax.persistence.*;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="recipes")
public class Recipe {

    @Id
    @SequenceGenerator(name = "mySeqGenRecipe", sequenceName = "mySeqRecipe", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mySeqGenRecipe")
    private long id;

    @JsonBackReference
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Patient patientForRecipe;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern ="yyyy-MM-dd HH:mm", timezone = "UTC")
    private LocalDateTime creationDate;

    //spisak lekova sa kolicinama
    @JsonManagedReference
    @OneToMany(mappedBy = "recipe", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<RecipeItem> recipeItems = new HashSet<RecipeItem>();

    public Recipe() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Patient getPatientForRecipe() {
        return patientForRecipe;
    }

    public void setPatientForRecipe(Patient patientForRecipe) {
        this.patientForRecipe = patientForRecipe;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public Set<RecipeItem> getRecipeItems() {
        return recipeItems;
    }

    public void setRecipeItems(Set<RecipeItem> recipeItems) {
        this.recipeItems = recipeItems;
    }
}
