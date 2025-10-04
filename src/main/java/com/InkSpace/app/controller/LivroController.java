package com.InkSpace.app.controller;

import com.InkSpace.app.model.Livro;
import com.InkSpace.app.service.LivroService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/livros")
public class LivroController {

	private final LivroService livroService;

	@GetMapping
	public ResponseEntity<List<Livro>> listarTodos() {
		List<Livro> livros = livroService.buscarTodos();
		return ResponseEntity.ok(livros);
	}

	@PostMapping
	public ResponseEntity<Livro> criarLivro(@RequestBody Livro livro) {
		Livro novoLivro = livroService.criarLivro(livro);
		return new ResponseEntity<>(novoLivro, HttpStatus.CREATED);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Integer id){
		livroService.delete(id);
	}
}
