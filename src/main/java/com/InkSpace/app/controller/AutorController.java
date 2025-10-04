package com.InkSpace.app.controller;

import com.InkSpace.app.model.Autor;
import com.InkSpace.app.service.AutorService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/autores")
@RequiredArgsConstructor
public class AutorController {

	private final AutorService autorService;

//	@GetMapping
//	public ResponseEntity<List<Autor>> getAll(){
//		List<Autor> autores = new ArrayList<>();
//		autores = autorService.getAll();
//		return ResponseEntity.ok(autores);
//	}
//
//	@PostMapping
//	public ResponseEntity<Autor> create(@RequestBody Autor autor){
//		autorService.save(autor);
//		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(autor.getId()).toUri();
//		return ResponseEntity.created(uri).body(autor);
//	}
//
//	@DeleteMapping("/{id}")
//	@ResponseStatus(HttpStatus.NO_CONTENT)
//	public void delete(@PathVariable Integer id){
//		autorService.delete(id);
//	}
//
//	@PutMapping("/{id}")
//	public ResponseEntity<Autor> update(@PathVariable Integer id, @RequestBody Autor autor){
//		Autor autor1 = autorService.update(autor, id);
//		return ResponseEntity.ok(autor1);
//	}

		// GET /api/autores -> Retorna todos os autores e, para cada um, a lista de livros que escreveu
		@GetMapping
		public ResponseEntity<List<Autor>> listarTodos() {
			return ResponseEntity.ok(autorService.buscarTodos());
		}

		// GET /api/autores/{id} -> Retorna um autor específico e seus livros
		@GetMapping("/{id}")
		public ResponseEntity<Autor> buscarPorId(@PathVariable Integer id) {
			return autorService.buscarPorId(id)
					.map(ResponseEntity::ok)
					.orElse(ResponseEntity.notFound().build());
		}

		// POST /api/autores -> Cria um novo autor.
		// O JSON do body só precisa de nome e sobrenome.
		// Ex: { "nome": "Clarice", "sobrenome": "Lispector" }
		@PostMapping
		public ResponseEntity<Autor> criarAutor(@RequestBody Autor autor) {
			Autor novoAutor = autorService.criarAutor(autor);
			return new ResponseEntity<>(novoAutor, HttpStatus.CREATED);
		}
	}


