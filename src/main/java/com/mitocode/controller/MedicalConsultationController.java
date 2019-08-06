package com.mitocode.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.mitocode.model.entity.Patient;
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

}
