package com.mitocode.controller;

import java.util.ArrayList;
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

import com.mitocode.model.entity.Doctor;
import com.mitocode.model.entity.Specialty;
import com.mitocode.service.impl.DoctorService;
import com.mitocode.service.impl.SpecialtyService;

@Controller
@SessionAttributes("doctor")
@RequestMapping("/doctors")

public class DoctorController {
	@Autowired
	private SpecialtyService specialtyService;

	@Autowired
	private DoctorService doctorService;

	@Secured("ROLE_ADMIN")
	@GetMapping(value = "/list")
	public String getAllDoctors(Model model) {
		try {
			List<Doctor> doctors = doctorService.findDoctorByIdWithSpecialty();

			model.addAttribute("title", "Doctores");
			model.addAttribute("doctors", doctors);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "doctor/doctor";
	}

	@Secured("ROLE_ADMIN")
	@PostMapping(value = "/save")
	public String saveDoctor(@Valid Doctor doctor, BindingResult result, Model model, RedirectAttributes flash,
			SessionStatus status) {

		try {
			List<Specialty> specialties = specialtyService.getAll();

			if (result.hasErrors()) {
				model.addAttribute("title", "Guardar Doctor");
				model.addAttribute("specialties", specialties);
				return "doctor/form";
			}

			String mensajeFlash = (doctor.getId() != null) ? "Doctor editado correctamente!"
					: "Doctor registrado correctamente!";

			if (doctor.getSpecialty().getId() == null || doctor.getSpecialty().getId() == 0) {
				model.addAttribute("title", "Guardar Doctor");
				model.addAttribute("specialties", specialties);
				return "doctor/form";
			}

			doctorService.saveOrUpdate(doctor);
			status.setComplete();
			flash.addFlashAttribute("success", mensajeFlash);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "redirect:/doctors/list";
	}

	@Secured("ROLE_ADMIN")
	@GetMapping(value = "/edit/{id}")
	public String editDoctor(@PathVariable(value = "id") Long id, Model model, RedirectAttributes flash) {

		Optional<Doctor> doctor = null;

		try {
			List<Specialty> specialties = specialtyService.getAll();
			if (id > 0) {
				doctor = doctorService.getOne(id);
				if (!doctor.isPresent()) {
					flash.addFlashAttribute("error", "El codigo del doctor no existe!");
					return "redirect:/doctors/list";
				}
			} else {
				flash.addFlashAttribute("error", "El codigo del doctor no puede ser cero");
				return "redirect:/doctors/list";
			}
			model.addAttribute("title", "Editar Doctor");
			model.addAttribute("doctor", doctor);		
			model.addAttribute("specialties", specialties);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "doctor/form";
	}

	@Secured("ROLE_ADMIN")
	@GetMapping(value = "/new")
	public String newDoctor(Model model) {

		try {
			List<Specialty> specialties = specialtyService.getAll();

			Doctor doctor = new Doctor();
			
			model.addAttribute("title", "Nuevo Doctor");
			model.addAttribute("doctor", doctor);
			model.addAttribute("specialties", specialties);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "doctor/form";
	}

	@GetMapping(value = "/specialties")
	public String getAllSpecialties(Model model) {
		try {
			List<Specialty> specialties = specialtyService.getAll();
			model.addAttribute("title", "Especialidades");
			model.addAttribute("specialties", specialties);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "doctor/specialty_rpt";
	}

	@GetMapping(value = "/specialty/{id}")
	public String doctorsBySpeciality(@PathVariable(value = "id") Long specialtyId, Model model,
			RedirectAttributes flash) {

		Optional<Specialty> specialty;
		List<Doctor> doctors = new ArrayList<>();

		try {
			specialty = specialtyService.getOne(specialtyId);

			if (!specialty.isPresent()) {
				flash.addFlashAttribute("error", "La especialidad no existe");
				return "redirect:/doctors/specialties/list";
			} else {
				doctors = doctorService.findDoctorsBySpecialtyId(specialtyId);
				model.addAttribute("specialty", specialty.get());
				model.addAttribute("doctors", doctors);
				model.addAttribute("title", "Especialidad");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "doctor/doctorspecialty";
	}

}
