package com.mitocode.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mitocode.model.entity.Patient;
import com.mitocode.service.impl.PatientService;

@Controller
@RequestMapping("/historiasclinicas")
public class HistoriaClinicaController {

	@Autowired
	private PatientService patientService;

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

	/*
	 * @GetMapping(value = "/historiaclinica/patient/{patientId}") public String
	 * patientHistoriaClinica(@PathVariable(value = "patientId") Long patientId,
	 * Model model, RedirectAttributes flash) {
	 * 
	 * Optional<Patient> patient; List<MedicalConsultation> historiaclinica = new
	 * ArrayList<>(); try { patient = patientService.getOne(patientId); if
	 * (!patient.isPresent()) { flash.addFlashAttribute("error",
	 * "El paciente no existe"); return
	 * "redirect:/medical_consultations/historiaclinica"; } else { historiaclinica =
	 * medicalConsultationService.findHistoriaClinicaByPatientId(patientId);
	 * 
	 * model.addAttribute("historiaclinica", historiaclinica);
	 * model.addAttribute("title", "Historia Clinica"); } } catch (Exception e) {
	 * e.printStackTrace(); }
	 * 
	 * return "historiaclinica/patienthistoriaclinica"; }
	 */

}
