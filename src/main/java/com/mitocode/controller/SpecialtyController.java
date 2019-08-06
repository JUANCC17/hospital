package com.mitocode.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.mitocode.model.entity.Specialty;
import com.mitocode.service.impl.SpecialtyService;

@Controller
@SessionAttributes("specialty")
@RequestMapping("/specialties")
public class SpecialtyController {

	@Autowired
	private SpecialtyService specialtyService;

	@GetMapping(value = "/list")
	public String getAllSpecialties(Model model) {
		try {
			List<Specialty> specialties = specialtyService.getAll();
			model.addAttribute("title", "Especialidades");
			model.addAttribute("specialties", specialties);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "specialty/specialty";
	}

	@PostMapping(value = "/save")
	public String saveSpecialty(@Valid Specialty specialty, BindingResult result, Model model, RedirectAttributes flash,
			SessionStatus status) {

		try {
			if (result.hasErrors()) {
				model.addAttribute("title", "Guardar Especialidad");
				return "specialty/form";
			}

			String mensajeFlash = (specialty.getId() != null) ? "Especialidad editado correctamente!"
					: "Especialidad registrado correctamente!";

			specialtyService.saveOrUpdate(specialty);
			status.setComplete();
			flash.addFlashAttribute("success", mensajeFlash);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "redirect:/specialties/list";
	}

	@GetMapping(value = "/edit/{id}")
	public String editSpecialty(@PathVariable(value = "id") Long id, Model model, RedirectAttributes flash) {

		Optional<Specialty> specialty = null;

		try {
			if (id > 0) {
				specialty = specialtyService.getOne(id);
				if (!specialty.isPresent()) {
					flash.addFlashAttribute("error", "El codigo de la especialidad no existe!");
					return "redirect:/specialties/list";
				}
			} else {
				flash.addFlashAttribute("error", "El codigo de la especialidad no puede ser cero");
				return "redirect:/specialties/list";
			}
			model.addAttribute("specialty", specialty);
			model.addAttribute("title", "Editar Especialidad");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "specialty/form";
	}

	@GetMapping(value = "/new")
	public String newSpecialty(Model model) {

		Specialty specialty = new Specialty();
		model.addAttribute("title", "Nueva Especialidad");
		model.addAttribute("specialty", specialty);

		return "specialty/form";
	}



}
