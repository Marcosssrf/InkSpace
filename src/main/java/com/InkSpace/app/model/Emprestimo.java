package com.InkSpace.app.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table (name = "emprestimo")
public class Emprestimo {

	@Id
	@GeneratedValue
	private Integer id;
	private Integer livro_id;
	private Integer membro_id;
	private Date data_emprestimo;
	private Date data_devolucao;

}
