package com.InkSpace.app.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "categoria")
public class Categoria {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name="nome_categoria")
	private String nomeCategoria;

	@ManyToMany(mappedBy = "categorias")
//	@JsonBackReference
	@JsonIgnoreProperties("categorias")
	private Set<Livro> livros = new HashSet<>();

	public Categoria(String nomeCategoria) {
		this.nomeCategoria = nomeCategoria;
	}
}
