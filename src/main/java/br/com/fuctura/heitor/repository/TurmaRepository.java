package br.com.fuctura.heitor.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fuctura.heitor.model.Turma;

public interface TurmaRepository extends JpaRepository<Turma, Long> {

	Page<Turma> findByNome(String nomeTurma, Pageable paginacao);
}
