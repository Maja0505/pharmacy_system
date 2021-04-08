package com.isa.pharmacies_system.domain.medicine;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.CascadeType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="ingredient")
public class Ingredient {
	@Id 
	@SequenceGenerator(name = "mySeqGenIngredient", sequenceName = "mySeqIngredient", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mySeqGenIngredient")
	private long id;
	
	@Column(name="name_of_ingredient", unique=true, nullable=false)
	private String nameOfIngredient;
	
	

	public Ingredient() {
		// TODO Auto-generated constructor stub
	}

	
	public Ingredient(long id, String nameOfIngredient) {
		super();
		this.id = id;
		this.nameOfIngredient = nameOfIngredient;
		
	}


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return nameOfIngredient;
	}

	public void setName(String nameOfIngredient) {
		this.nameOfIngredient = nameOfIngredient;
	}
	
	
	
	
}
