package com.InkSpace.app.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "status_reserva")
@Getter
@Setter
public class StatusReserva {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "valor_status")
	private String descricao;
}