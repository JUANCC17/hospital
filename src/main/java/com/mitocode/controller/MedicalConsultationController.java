package com.mitocode.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.mitocode.model.entity.DetailConsultation;
import com.mitocode.model.entity.Doctor;
import com.mitocode.model.entity.MedicalConsultation;
import com.mitocode.model.entity.Patient;
import com.mitocode.service.impl.DoctorService;
import com.mitocode.service.impl.MedicalConsultationService;
import com.mitocode.service.impl.PatientService;

@Controller
@SessionAttributes("medical_consultation")
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

			List<Doctor> doctors = doctorService.getAll();
			Optional<Patient> patient = patientService.getOne(patientId);

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

	@PostMapping("/save")
	public String saveMedicalConsultation(MedicalConsultation medical_consultation, Model model,
			
			@RequestParam(name = "diagnostic[]", required = true) String[] diagnostic,
			@RequestParam(name = "treatment[]", required = true) String[] treatment, SessionStatus status) {
		try {

			if (diagnostic == null || diagnostic.length == 0) {
				model.addAttribute("info", "La consulta medica no tiene diagnosticos.");
				return "medical_consultation/form";
			}

			for (int i = 0; i < diagnostic.length; i++) {

				DetailConsultation itemLine = new DetailConsultation();
				itemLine.setDiagnostic(diagnostic[i]);
				itemLine.setTreatment(treatment[i]);
				medical_consultation.addItemConsultation(itemLine);

			}

			medicalConsultationService.saveOrUpdate(medical_consultation);
			status.setComplete();
			model.addAttribute("success", "Consulta Medica Generada");
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}

		// return "redirect:/invoices/detail/" + invoice.getCustomer().getId();
		return "medical_consultation/medical_consultation";
	}

}
