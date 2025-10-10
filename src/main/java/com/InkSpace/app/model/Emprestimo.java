package com.InkSpace.app.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@Entity
@Table (name = "emprestimo")
public class Emprestimo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "livro_id", nullable = false)
	private Livro livro;

	@ManyToOne
	@JoinColumn(name = "membro_id", nullable = false)
	private Membro membro;

	@Column(name = "data_emprestimo", nullable = false)
	private LocalDate dataEmprestimo;

	@Column(name = "data_prevista_devolucao", nullable = false)
	private LocalDate dataPrevistaDevolucao;

	@Column(name = "data_devolucao")
	private LocalDate dataDevolucao;

	@OneToOne(mappedBy = "emprestimo", cascade = CascadeType.ALL)
	private Multa multa;

}

