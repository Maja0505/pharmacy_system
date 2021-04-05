package com.isa.pharmacies_system.domain.rating;

import static javax.persistence.InheritanceType.JOINED;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.isa.pharmacies_system.domain.storage.TypeOfStorage;
import com.isa.pharmacies_system.domain.user.Patient;

@Entity
@Table(name="ratings")
@Inheritance(strategy=JOINED)
public class Rating {
	@Id
	@SequenceGenerator(name = "mySeqGenRating", sequenceName = "mySeqRating", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mySeqGenRating")
	private long id;
	
	@Column(name="grade", unique=false, nullable=false)
	private RatingScale grade;
	
	@ManyToOne(cascade = {CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH,CascadeType.REMOVE}, fetch = FetchType.EAGER)
	private Patient patientWithRating;
	
	@Enumerated(EnumType.ORDINAL)
	private TypeOfRating typeOfRating;
	
	public Rating() {
		// TODO Auto-generated constructor stub
	}

	
	public Rating(long id, RatingScale grade, Patient patientWithRating, TypeOfRating typeOfRating) {
		super();
		this.id = id;
		this.grade = grade;
		this.patientWithRating = patientWithRating;
		this.typeOfRating = typeOfRating;
	}


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public RatingScale getGrade() {
		return grade;
	}

	public void setGrade(RatingScale grade) {
		this.grade = grade;
	}

	public Patient getPatientWithRating() {
		return patientWithRating;
	}

	public void setPatientWithRating(Patient patientWithRating) {
		this.patientWithRating = patientWithRating;
	}


	public TypeOfRating getTypeOfRating() {
		return typeOfRating;
	}


	public void setTypeOfRating(TypeOfRating typeOfRating) {
		this.typeOfRating = typeOfRating;
	}
	
	
}


