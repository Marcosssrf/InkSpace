package com.InkSpace.app.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "multa")
@Getter
@Setter
public class Multa {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@OneToOne
	@JoinColumn(name = "emprestimo_id", nullable = false)
	private Emprestimo emprestimo;

	@Column(name = "data_multa", nullable = false)
	private LocalDate dataMulta;

	@Column(name = "valor_multa", nullable = false)
	private BigDecimal valorMulta;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private MultaStatus status;

	@OneToMany(mappedBy = "multa", cascade = CascadeType.ALL)
	private List<PagamentoMulta> pagamentos;


}