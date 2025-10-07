package com.InkSpace.app.controller;

import com.InkSpace.app.dto.LivroRequestDTO;
import com.InkSpace.app.model.Livro;
import com.InkSpace.app.service.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/livros")
public class LivroApiController {

	@Autowired
	private LivroService livroService;

	@GetMapping
	public List<Livro> listarTodosLivros() {
		return livroService.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Livro> buscarLivroPorId(@PathVariable Integer id) {
		return livroService.findById(id)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Livro criarLivro(@RequestBody LivroRequestDTO dto) {
		return livroService.criarLivro(dto);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletarLivro(@PathVariable Integer id) {
		livroService.deleteById(id);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Livro> atualizarLivro(@PathVariable Integer id, @RequestBody Livro livroAtualizado) {
		return livroService.findById(id)
				.map(livroExistente -> {
					livroAtualizado.setId(id);
					Livro livroSalvo = livroService.save(livroAtualizado);
					return ResponseEntity.ok(livroSalvo);
				})
				.orElse(ResponseEntity.notFound().build());
	}

	@PostMapping("/batch")
	@ResponseStatus(HttpStatus.CREATED)
	public List<Livro> criarVariosLivros(@RequestBody List<LivroRequestDTO> dtos) {
		return livroService.criarVariosLivros(dtos);
	}
}