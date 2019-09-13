package com.mitocode.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mitocode.model.entity.MedicalConsultation;
import com.mitocode.model.entity.Patient;
import com.mitocode.service.impl.MedicalConsultationService;
import com.mitocode.service.impl.PatientService;

@Controller
@RequestMapping("/historiasclinicas")
public class HistoriaClinicaController {

	@Autowired
	private PatientService patientService;
	
	@Autowired
	private MedicalConsultationService medicalConsultationService;

	@GetMapping(value = "/list")
	public String historiaClinica(Model model) {
		try {
			List<Patient> patients = patientService.getAll();

			model.addAttribute("title", "Pacientes");
			model.addAttribute("patients", patients);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "historiaclinica/historiaclinica";
	}

	@GetMapping(value = "/patient/{patientId}")
	public String patientHistoriaClinica(@PathVariable(value = "patientId") Long patientId, Model model,
			RedirectAttributes flash) {

		Optional<Patient> patient;
		List<MedicalConsultation> historiaclinica = new ArrayList<>();
		try {
			patient = patientService.getOne(patientId);
			if (!patient.isPresent()) {
				flash.addFlashAttribute("error", "El paciente no existe");
				return "redirect:/historiasclinicas/list";
			} else {
				historiaclinica = medicalConsultationService.findHistoriaClinicaByPatientId(patientId);
				model.addAttribute("patient",patient.get());
				model.addAttribute("historiaclinica", historiaclinica);
				model.addAttribute("title", "Historia Clinica");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "historiaclinica/patienthistoriaclinica";
	}
	
	@GetMapping("/detail/{id}")
	public String detailMedicalConsultation(@PathVariable(value = "id") Long medicalConsultationId, Model model) {

		try {
			Optional<MedicalConsultation> medicalConsultation = medicalConsultationService
					.findMedicalConsultationByIdWithPatientWithDoctorWithItemMedicalConsultation(medicalConsultationId);

			if (!medicalConsultation.isPresent()) {
				model.addAttribute("error", "Consulta Medica no existe");
				return "redirect:/medical_consultations/list";
			}

			model.addAttribute("medical_consultation", medicalConsultation.get());
			model.addAttribute("title", "Consulta Medica");
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}

		return "historiaclinica/detail_historiaclinica";
	}

}
