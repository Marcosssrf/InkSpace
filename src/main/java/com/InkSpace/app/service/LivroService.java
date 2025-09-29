package com.InkSpace.app.service;

import com.InkSpace.app.model.Categoria;
import com.InkSpace.app.model.Livro;
import com.InkSpace.app.repository.LivroRepository;

import java.util.List;

public class LivroService {

	private final LivroRepository livroRepository;

	public LivroService(LivroRepository livroRepository) {
		this.livroRepository = livroRepository;
	}

	//GET
	public List<Livro> getAll(){
		return livroRepository.findAll();
	}

	//POST
	public Livro save(Livro livro){
		return livroRepository.save(livro);
	}

	//DELETE
	public void delete(Integer id){
		livroRepository.deleteById(id);
	}

}
