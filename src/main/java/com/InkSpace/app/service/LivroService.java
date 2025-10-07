package com.InkSpace.app.service;

import com.InkSpace.app.dto.AutorDTO;
import com.InkSpace.app.dto.CategoriaDTO;
import com.InkSpace.app.dto.LivroRequestDTO;
import com.InkSpace.app.model.Autor;
import com.InkSpace.app.model.Categoria;
import com.InkSpace.app.model.Livro;
import com.InkSpace.app.repository.AutorRepository;
import com.InkSpace.app.repository.CategoriaRepository;
import com.InkSpace.app.repository.LivroRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class LivroService {

	@Autowired
	private final LivroRepository livroRepository;
	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private AutorRepository autorRepository;

	public LivroService(LivroRepository livroRepository) {
		this.livroRepository = livroRepository;
	}

	public Livro save(Livro livro) {
		return livroRepository.save(livro);
	}

	public List<Livro> findAll() {
		return livroRepository.findAll();
	}

	public Optional<Livro> findById(Integer id) {
		return livroRepository.findById(id);
	}

	public void deleteById(Integer id) {
		livroRepository.deleteById(id);
	}


	@Transactional
	public Livro criarLivro(LivroRequestDTO dto) {
		Livro novoLivro = new Livro();
		novoLivro.setTitulo(dto.getTitulo());
		novoLivro.setDataPublicacao(dto.getDataPublicacao());
		novoLivro.setCopias(dto.getCopias());

		Set<Autor> autoresDoLivro = new HashSet<>();
		for (AutorDTO autorDto : dto.getAutores()) {
			final String primeiroNome = autorDto.getNome();
			final String ultimoSobrenome = autorDto.getSobrenome();

			Optional<Autor> autorExistente = autorRepository.findByNomeAndSobrenome(primeiroNome, ultimoSobrenome);
			Autor autor = autorExistente.orElseGet(() -> new Autor(primeiroNome, ultimoSobrenome));
			autoresDoLivro.add(autor);
		}
		novoLivro.setAutores(autoresDoLivro);

		Set<Categoria> categoriasDoLivro = new HashSet<>();
		for (CategoriaDTO categoriaDto : dto.getCategorias()) {
			String nomeDaCategoria = categoriaDto.getNome_categoria();

			Optional<Categoria> categoriaExistente = categoriaRepository.findByNomeCategoria(nomeDaCategoria);
			Categoria categoria = categoriaExistente.orElseGet(() -> new Categoria(nomeDaCategoria));
			categoriasDoLivro.add(categoria);
		}
		novoLivro.setCategorias(categoriasDoLivro);

		return livroRepository.save(novoLivro);
	}

	@Transactional
	public List<Livro> criarVariosLivros(List<LivroRequestDTO> dtos) {
		return dtos.stream()
				.map(this::criarLivro)
				.collect(Collectors.toList());
	}

}
