package com.InkSpace.app.service;

import com.InkSpace.app.model.Autor;
import com.InkSpace.app.repository.AutorRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class AutorService {

	private final AutorRepository autorRepository;

	public AutorService(AutorRepository autorRepository) {
		this.autorRepository = autorRepository;
	}
//	//UPDATE
//	public Autor findPorId(Integer id) {
//		return autorRepository.findPorId(id);
//	}
//	public Autor update(Autor autor, Integer id) {
//		Autor autor1 = findPorId(id);
//		if (autor1 == null) {
//			throw new EntityNotFoundException("Usuario não encontrado!");
//		}
//		if (autor.getNome() != null){
//			autor1.setNome(autor.getNome());
//		}
//		if(autor.getSobrenome() != null){
//			autor1.setSobrenome(autor.getSobrenome());
//		}
//		return autorRepository.save(autor1);
//}

	public List<Autor> buscarTodos() {
		return autorRepository.findAll();
	}

	public Optional<Autor> buscarPorId(Integer id) {
		return autorRepository.findById(id);
	}

	public Autor criarAutor(Autor autor) {
		return autorRepository.save(autor);
	}

	public void deletarAutor(Integer id) {
		autorRepository.deleteById(id);
	}

}
