package com.InkSpace.app.service;

import com.InkSpace.app.model.Categoria;
import com.InkSpace.app.model.Livro;
import com.InkSpace.app.repository.LivroRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LivroService {

	private final LivroRepository livroRepository;

	public LivroService(LivroRepository livroRepository) {
		this.livroRepository = livroRepository;
	}


	//DELETE
	public void delete(Integer id){
		livroRepository.deleteById(id);
	}

	public List<Livro> buscarTodos() {
		return livroRepository.findAll();
	}

	@Transactional
	public Livro criarLivro(Livro livro) {
		return livroRepository.save(livro);
	}

}
