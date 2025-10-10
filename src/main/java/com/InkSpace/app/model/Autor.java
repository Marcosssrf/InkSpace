package com.InkSpace.app.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "autor")
public class Autor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nome;
	private String sobrenome;

	@ManyToMany(mappedBy = "autores")
	@JsonIgnoreProperties
	private Set<Livro> livros = new HashSet<>();

	public Autor(String nome, String sobrenome) {
		this.nome = nome;
		this.sobrenome = sobrenome;
	}

}
