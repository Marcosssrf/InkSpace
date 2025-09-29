package com.InkSpace.app.controller;

import com.InkSpace.app.model.Categoria;
import com.InkSpace.app.model.Livro;
import com.InkSpace.app.service.LivroService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@RequestMapping("/livros")
public class LivroController {

	private final LivroService livroService;

	@GetMapping
	public ResponseEntity<List<Livro>> getAll(){
		List<Livro> livros = new ArrayList<>();
		livros = livroService.getAll();
		return ResponseEntity.ok(livros);
	}

	@PostMapping
	public ResponseEntity<Livro> create(@RequestBody Livro livros){
		livroService.save(livros);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(livros.getId()).toUri();
		return ResponseEntity.created(uri).body(livros);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Integer id){
		livroService.delete(id);
	}

}
