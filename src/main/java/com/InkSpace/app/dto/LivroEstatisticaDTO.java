package com.InkSpace.app.dto;

import com.InkSpace.app.model.Livro;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class LivroEstatisticaDTO {
	private Livro livro;
	private long totalEmprestimos;
}