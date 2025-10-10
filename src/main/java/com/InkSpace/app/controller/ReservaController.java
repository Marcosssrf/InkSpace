package com.InkSpace.app.controller;

import com.InkSpace.app.model.Livro;
import com.InkSpace.app.repository.LivroRepository;
import com.InkSpace.app.repository.MembroRepository;
import com.InkSpace.app.repository.ReservaRepository;
import com.InkSpace.app.service.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/reservas")
public class ReservaController {

	@Autowired
	private ReservaService reservaService;
	@Autowired
	private LivroRepository livroRepository;
	@Autowired
	private MembroRepository membroRepository;
	@Autowired
	private ReservaRepository reservaRepository;

	@GetMapping("/novo/{livroId}")
	public String mostrarFormularioDeReserva(@PathVariable Integer livroId, Model model) {
		Livro livro = livroRepository.findById(livroId)
				.orElseThrow(() -> new IllegalArgumentException("Livro inválido ID:" + livroId));

		model.addAttribute("livro", livro);
		model.addAttribute("todosOsMembros", membroRepository.findAll());

		model.addAttribute("reservaRequest", new Object());

		return "reservas/form-reserva";
	}


	@PostMapping("/criar")
	public String criarReserva(@RequestParam Integer livroId, @RequestParam Integer membroId, RedirectAttributes redirectAttributes) {
		try {
			reservaService.criarReserva(livroId, membroId);
			redirectAttributes.addFlashAttribute("sucesso", "Reserva realizada com sucesso!");
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("erro", "Não foi possível realizar a reserva: " + e.getMessage());
			return "redirect:/reservas/novo/" + livroId;
		}
		return "redirect:/reservas/lista";
	}


	@GetMapping("/lista")
	public String listarReservas(Model model) {
		model.addAttribute("reservas", reservaRepository.findAll());
		return "reservas/lista-reservas";
	}

	@PostMapping("/finalizar/{id}")
	public String finalizarReserva(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
		try {
			reservaService.finalizarReservaComEmprestimo(id);
			redirectAttributes.addFlashAttribute("sucesso", "Empréstimo realizado a partir da reserva com sucesso!");
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("erro", "Erro ao processar reserva: " + e.getMessage());
		}
		return "redirect:/reservas/lista";
	}

}