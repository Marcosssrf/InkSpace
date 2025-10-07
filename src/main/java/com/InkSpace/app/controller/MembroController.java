package com.InkSpace.app.controller;

import com.InkSpace.app.model.Membro;
import com.InkSpace.app.service.MembroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/membros")
public class MembroController {

	@Autowired
	private MembroService membroService;

	@GetMapping("/novo")
	public String mostrarFormularioDeCadastro(Model model) {
		model.addAttribute("membro", new Membro());
		return "membros/form-membro";
	}

	@PostMapping("/salvar")
	public String salvarMembro(@ModelAttribute("membro") Membro membro) {
		membroService.save(membro);
		return "redirect:/membros/lista";
	}

	@GetMapping("/lista")
	public String listarMembros(Model model) {
		model.addAttribute("membros", membroService.findAll());
		return "membros/lista-membros";
	}

	@GetMapping("/deletar/{id}")
	public String deletarMembro(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
		membroService.deleteById(id);
		redirectAttributes.addFlashAttribute("sucesso", "Membro deletado com sucesso!");
		return "redirect:/membros/lista";
	}

	@GetMapping("/editar/{id}")
	public String mostrarFormularioDeEdicao(@PathVariable Integer id, Model model) {
		Optional<Membro> membroOptional = membroService.findById(id);

		if (membroOptional.isPresent()) {
			model.addAttribute("membro", membroOptional.get());
			return "membros/form-membro";
		}
		return "redirect:/membros/lista";
	}

}


