package com.InkSpace.app.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "pagamento_multa")
@Getter
@Setter
public class PagamentoMulta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "membro_id", nullable = false)
	private Membro membro;

	@ManyToOne
	@JoinColumn(name = "multa_id", nullable = false)
	private Multa multa;

	@Column(name = "data_pagamento", nullable = false)
	private LocalDate dataPagamento;

	@Column(name = "valor_pagamento", nullable = false)
	private BigDecimal valorPagamento;
}