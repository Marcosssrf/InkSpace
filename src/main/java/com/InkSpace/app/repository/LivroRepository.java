package com.InkSpace.app.repository;

import com.InkSpace.app.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Integer> {
	List<Livro> findByCopiasGreaterThan(Integer quantidade);
}
