package com.isa.pharmacies_system.domain.schedule;

import static javax.persistence.InheritanceType.JOINED;

import java.time.LocalDate;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name="vacation_requests")
@Inheritance(strategy=JOINED)
public class VacationRequest {
	
	@Id
	@SequenceGenerator(name = "mySeqGenVacationRequest",sequenceName = "mySeqGenVacationRequest",initialValue = 1,allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE , generator = "mySeqGenVacationRequest")
	private long id;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern ="yyyy-MM-dd", timezone = "UTC")
	private LocalDate vacationStartDate;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern ="yyyy-MM-dd", timezone = "UTC")
	private LocalDate vacationEndDate;
	
	@Enumerated(EnumType.ORDINAL)
	private TypeOfVacation typeOfVacation;
	
	@Enumerated(EnumType.ORDINAL)
	private StatusOfVacationRequest statusOfVacationRequest;
	
	@Column(name="vacation_request_notes", unique=false, nullable=true)
	private String vacationRequestNotes;
	
	@Enumerated(EnumType.ORDINAL)
	private TypeOfVacationRequest typeOfVacationRequest;
	
	public VacationRequest(){
		
	}

	public VacationRequest(long id, LocalDate vacationStartDate, LocalDate vacationEndDate, TypeOfVacation typeOfVacation, StatusOfVacationRequest statusOfVacationRequest, String vacationRequestNotes, TypeOfVacationRequest typeOfVacationRequest) {
		this.id = id;
		this.vacationStartDate = vacationStartDate;
		this.vacationEndDate = vacationEndDate;
		this.typeOfVacation = typeOfVacation;
		this.statusOfVacationRequest = statusOfVacationRequest;
		this.vacationRequestNotes = vacationRequestNotes;
		this.typeOfVacationRequest = typeOfVacationRequest;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setVacationStartDate(LocalDate vacationStartDate) {
		this.vacationStartDate = vacationStartDate;
	}

	public void setVacationEndDate(LocalDate vacationEndDate) {
		this.vacationEndDate = vacationEndDate;
	}

	public LocalDate getVacationStartDate() {
		return vacationStartDate;
	}

	public LocalDate getVacationEndDate() {
		return vacationEndDate;
	}

	public TypeOfVacation getTypeOfVacation() {
		return typeOfVacation;
	}

	public void setTypeOfVacation(TypeOfVacation typeOfVacation) {
		this.typeOfVacation = typeOfVacation;
	}

	public StatusOfVacationRequest getStatusOfVacationRequest() {
		return statusOfVacationRequest;
	}

	public void setStatusOfVacationRequest(StatusOfVacationRequest statusOfVacationRequest) {
		this.statusOfVacationRequest = statusOfVacationRequest;
	}

	public String getVacationRequestNotes() {
		return vacationRequestNotes;
	}

	public void setVacationRequestNotes(String vacationRequestNotes) {
		this.vacationRequestNotes = vacationRequestNotes;
	}



	public TypeOfVacationRequest getTypeOfVacationRequest() {
		return typeOfVacationRequest;
	}


	public void setTypeOfVacationRequest(TypeOfVacationRequest typeOfVacationRequest) {
		this.typeOfVacationRequest = typeOfVacationRequest;
	}
	
	
}
