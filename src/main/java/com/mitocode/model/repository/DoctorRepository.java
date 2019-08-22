package com.mitocode.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mitocode.model.entity.Doctor;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
	
	
	@Query(value = "SELECT * FROM doctors d JOIN specialties s on s.id = d.specialty_id",nativeQuery = true)
	List<Doctor> findDoctorByIdWithSpecialty();
	
	@Query(value = "SELECT * FROM doctors d WHERE d.specialty_id=?1",nativeQuery = true)
	List<Doctor> findDoctorsBySpecialtyId(Long specialtyId);
}
