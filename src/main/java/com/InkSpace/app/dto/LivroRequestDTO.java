package com.InkSpace.app.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class LivroRequestDTO {
	private String titulo;
	private LocalDate dataPublicacao;
	private Integer copias;
	private List<AutorDTO> autores;
	private List<CategoriaDTO> categorias;



}

