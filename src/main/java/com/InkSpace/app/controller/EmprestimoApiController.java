package com.InkSpace.app.controller;

import com.InkSpace.app.dto.EmprestimoRequestDTO;
import com.InkSpace.app.model.Emprestimo;
import com.InkSpace.app.service.EmprestimoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/emprestimos")
public class EmprestimoApiController {

	@Autowired
	private EmprestimoService emprestimoService;

	@GetMapping
	public List<Emprestimo> listarTodos() {
		return emprestimoService.findAll();
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Emprestimo criarEmprestimo(@RequestBody EmprestimoRequestDTO dto) {
		return emprestimoService.realizarEmprestimo(dto);
	}

	@PutMapping("/{id}/devolver")
	public ResponseEntity<Emprestimo> devolverLivro(@PathVariable Integer id) {
		try {
			Emprestimo emprestimoDevolvido = emprestimoService.realizarDevolucao(id);
			return ResponseEntity.ok(emprestimoDevolvido);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(null);
		}
	}
}