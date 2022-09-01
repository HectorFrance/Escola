package br.com.fuctura.heitor.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fuctura.heitor.model.Curso;

public interface CursoRepository extends JpaRepository<Curso, Long> {

	Page<Curso> findByNome(String nomeCurso, Pageable paginacao);
}
