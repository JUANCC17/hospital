package com.mitocode.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mitocode.model.entity.Patient;
import com.mitocode.service.impl.PatientService;

@Controller
@SessionAttributes("patient")
@RequestMapping("/patients")

public class PatientController {

	@Autowired
	private PatientService patientService;

	@Secured("ROLE_ADMIN")
	@GetMapping(value = "/list")
	public String getAllPatients(Model model) {
		try {
			List<Patient> patients = patientService.getAll();

			model.addAttribute("title", "Pacientes");

			model.addAttribute("patients", patients);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "patient/patient";
	}

	@Secured("ROLE_ADMIN")
	@PostMapping(value = "/save")
	public String savePatient(@Valid Patient patient, BindingResult result, Model model, RedirectAttributes flash,
			SessionStatus status) {

		try {
			if (result.hasErrors()) {
				model.addAttribute("title", "Guardar Paciente");
				return "patient/form";
			}

			String mensajeFlash = (patient.getId() != null) ? "Paciente editado correctamente!"
					: "Paciente registrado correctamente!";

			patientService.saveOrUpdate(patient);
			status.setComplete();
			flash.addFlashAttribute("success", mensajeFlash);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "redirect:/patients/list";
	}

	@Secured("ROLE_ADMIN")
	@GetMapping(value = "/edit/{id}")
	public String editPatient(@PathVariable(value = "id") Long id, Model model, RedirectAttributes flash) {

		Optional<Patient> patient = null;

		try {
			if (id > 0) {
				patient = patientService.getOne(id);
				if (!patient.isPresent()) {
					flash.addFlashAttribute("error", "El codigo del paciente no existe!");
					return "redirect:/patients/list";
				}
			} else {
				flash.addFlashAttribute("error", "El codigo del paciente no puede ser cero");
				return "redirect:/patients/list";
			}
			model.addAttribute("patient", patient);
			model.addAttribute("title", "Editar Paciente");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "patient/form";
	}

	@Secured("ROLE_ADMIN")
	@GetMapping(value = "/new")
	public String newPatient(Model model) {

		Patient patient = new Patient();
		model.addAttribute("patient", patient);
		model.addAttribute("title", "Nuevo Paciente");
		return "patient/form";
	}



}
