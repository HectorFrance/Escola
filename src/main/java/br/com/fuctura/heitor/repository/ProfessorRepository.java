package br.com.fuctura.heitor.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fuctura.heitor.model.Professor;

public interface ProfessorRepository extends JpaRepository<Professor, Long> {

	Page<Professor> findByNome(String nomeProfessor, Pageable pagincao);
}
