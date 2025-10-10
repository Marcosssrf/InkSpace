package com.InkSpace.app.controller;

import com.InkSpace.app.service.MultaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/multas")
public class MultaController {

	@Autowired
	private MultaService multaService;

	@GetMapping("/lista")
	public String listarMultas(Model model) {
		model.addAttribute("multas", multaService.findAll());
		return "multas/lista-multas";
	}

	@PostMapping("/pagar/{id}")
	public String pagarMulta(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
		try {
			multaService.pagarMulta(id);
			redirectAttributes.addFlashAttribute("sucesso", "Pagamento da multa registrado com sucesso!");
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("erro", e.getMessage());
		}
		return "redirect:/multas/lista";
	}
}