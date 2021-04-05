package com.isa.pharmacies_system.domain.schedule;

import java.time.Instant;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name="working_hours")
public class WorkingHours {
	
	@Id
	@SequenceGenerator(name = "mySeqGenWorkingHours", sequenceName = "mySeqWorkingHours", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mySeqGenWorkingHours")
	private long id;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern ="yyyy-MM-dd HH:mm", timezone = "UTC")
	private LocalDateTime workingStartTime;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern ="yyyy-MM-dd HH:mm", timezone = "UTC")
	private LocalDateTime workingEndTime;
	
	@Enumerated(EnumType.ORDINAL)
	private StatusOfWorkingHours statusOfWorkingHours;

	@JsonBackReference
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private WorkerSchedule workerSchedule;

	public WorkingHours() {
	
	}
	
	

	public WorkingHours(long id, LocalDateTime workingStartTime, LocalDateTime workingEndTime,
			StatusOfWorkingHours statusOfWorkingHours, WorkerSchedule workerSchedule) {
		super();
		this.id = id;
		this.workingStartTime = workingStartTime;
		this.workingEndTime = workingEndTime;
		this.statusOfWorkingHours = statusOfWorkingHours;
		this.workerSchedule = workerSchedule;
	}



	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	

	public LocalDateTime getWorkingStartTime() {
		return workingStartTime;
	}



	public void setWorkingStartTime(LocalDateTime workingStartTime) {
		this.workingStartTime = workingStartTime;
	}



	public LocalDateTime getWorkingEndTime() {
		return workingEndTime;
	}



	public void setWorkingEndTime(LocalDateTime workingEndTime) {
		this.workingEndTime = workingEndTime;
	}



	public StatusOfWorkingHours getStatusOfWorkingHours() {
		return statusOfWorkingHours;
	}

	public void setStatusOfWorkingHours(StatusOfWorkingHours statusOfWorkingHours) {
		this.statusOfWorkingHours = statusOfWorkingHours;
	}



	public WorkerSchedule getWorkerSchedule() {
		return workerSchedule;
	}



	public void setWorkerSchedule(WorkerSchedule workerSchedule) {
		this.workerSchedule = workerSchedule;
	}
	
	
	
	

}
