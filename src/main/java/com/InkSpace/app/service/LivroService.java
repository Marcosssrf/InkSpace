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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class LivroService {

	@Autowired
	private LivroRepository livroRepository;
	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private AutorRepository autorRepository;


	@Transactional
	public List<Livro> criarVariosLivros(List<LivroRequestDTO> dtos) {
		Map<String, Autor> autorCache = new HashMap<>();
		Map<String, Categoria> categoriaCache = new HashMap<>();

		return dtos.stream()
				.map(dto -> criarLivroComCache(dto, autorCache, categoriaCache))
				.collect(Collectors.toList());
	}


	@Transactional
	public Livro criarLivro(LivroRequestDTO dto) {
		return criarLivroComCache(dto, new HashMap<>(), new HashMap<>());
	}


	private Livro criarLivroComCache(LivroRequestDTO dto, Map<String, Autor> autorCache, Map<String, Categoria> categoriaCache) {
		Livro novoLivro = new Livro();
		novoLivro.setTitulo(dto.getTitulo());
		novoLivro.setDataPublicacao(dto.getDataPublicacao());
		novoLivro.setCopias(dto.getCopias());

		Set<Autor> autoresDoLivro = new HashSet<>();
		if (dto.getAutores() != null) {
			for (AutorDTO autorDto : dto.getAutores()) {
				final String nomeCompleto = autorDto.getNome() + " " + autorDto.getSobrenome();

				Autor autor = autorCache.computeIfAbsent(nomeCompleto, k ->
						autorRepository.findByNomeAndSobrenome(autorDto.getNome(), autorDto.getSobrenome())
								.orElseGet(() -> new Autor(autorDto.getNome(), autorDto.getSobrenome()))
				);
				autoresDoLivro.add(autor);
			}
		}
		novoLivro.setAutores(autoresDoLivro);

		Set<Categoria> categoriasDoLivro = new HashSet<>();
		if (dto.getCategorias() != null) {
			for (CategoriaDTO categoriaDto : dto.getCategorias()) {
				final String nomeDaCategoria = categoriaDto.getNomeCategoria();

				Categoria categoria = categoriaCache.computeIfAbsent(nomeDaCategoria, k ->
						categoriaRepository.findByNomeCategoria(k)
								.orElseGet(() -> new Categoria(k))
				);
				categoriasDoLivro.add(categoria);
			}
		}
		novoLivro.setCategorias(categoriasDoLivro);

		return livroRepository.save(novoLivro);
	}

	public Livro save(Livro livro) { return livroRepository.save(livro); }
	public List<Livro> findAll() { return livroRepository.findAll(); }
	public Optional<Livro> findById(Integer id) { return livroRepository.findById(id); }
	public void deleteById(Integer id) { livroRepository.deleteById(id); }
}