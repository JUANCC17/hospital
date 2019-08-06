package com.mitocode.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.mitocode.model.entity.Doctor;
import com.mitocode.model.entity.MedicalConsultation;
import com.mitocode.model.entity.Patient;
import com.mitocode.service.impl.DoctorService;
import com.mitocode.service.impl.MedicalConsultationService;
import com.mitocode.service.impl.PatientService;

@Controller
@SessionAttributes("medicalconsultation")
@RequestMapping("/medical_consultations")
public class MedicalConsultationController {

	@Autowired
	private MedicalConsultationService medicalConsultationService;

	@Autowired
	private PatientService patientService;

	@Autowired
	private DoctorService doctorService;

	@GetMapping(value = "/list")
	public String getAllPatient(Model model) {
		try {
			List<Patient> patients = patientService.getAll();

			model.addAttribute("title", "Pacientes");
			model.addAttribute("patients", patients);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "medical_consultation/medical_consultation";
	}

	@GetMapping("/form/{patientId}")
	public String newMedicalConsultation(@PathVariable(value = "patientId") Long patientId, Model model) {
		try {
			Optional<Patient> patient = patientService.getOne(patientId);
			List<Doctor> doctors = doctorService.getAll();

			if (doctors.size() == 0) {
				model.addAttribute("info", "No existe doctores registrados.");
				return "redirect:/medical_consultations/list";
			} else {
				if (!patient.isPresent()) {
					model.addAttribute("info", "Paciente no existe");
					return "redirect:/medical_consultations/list";
				} else {
					MedicalConsultation medical_consultation = new MedicalConsultation();
					medical_consultation.setPatient(patient.get());
					model.addAttribute("medical_consultation", medical_consultation);
					model.addAttribute("doctors", doctors);
					model.addAttribute("title", "Consulta Medica");
				}
			}
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "medical_consultation/form";
	}

}
