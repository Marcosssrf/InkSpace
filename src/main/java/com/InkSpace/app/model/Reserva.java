package com.InkSpace.app.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "reserva")
@Getter
@Setter
public class Reserva {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "livro_id", nullable = false)
	private Livro livro;

	@ManyToOne
	@JoinColumn(name = "membro_id", nullable = false)
	private Membro membro;

	@Column(name = "data_reserva", nullable = false)
	private LocalDate dataReserva;

	@ManyToOne
	@JoinColumn(name = "status_reserva_id", nullable = false)
	private StatusReserva status;
}