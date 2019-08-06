package com.mitocode.model.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "specialties")
public class Specialty {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotEmpty(message = "Ingrese el nombre de la especialidad")
	@Column(name = "name", nullable = false)
	private String name;

	@OneToMany(mappedBy = "specialty", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Doctor> doctors;
	
	public Specialty() {
		this.doctors = new ArrayList<>();
	}

	public Long getId() {
		return id;
	}

	public List<Doctor> getDoctors() {
		return doctors;
	}

	public void setDoctors(List<Doctor> doctors) {
		this.doctors = doctors;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void addDoctor(Doctor doctor) {
		this.doctors.add(doctor);
	}

}
