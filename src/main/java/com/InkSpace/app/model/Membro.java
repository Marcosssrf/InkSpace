package com.InkSpace.app.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "membro")
public class Membro {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nome;
	private String sobrenome;
	private LocalDate dataEntrada;
	@ManyToOne
	@JoinColumn(name = "status_id", nullable = false)
	private StatusMembro status;


}
