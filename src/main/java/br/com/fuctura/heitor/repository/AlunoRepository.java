package br.com.fuctura.heitor.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.fuctura.heitor.model.Aluno;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {

	Page<Aluno> findByNome(String nomeAluno, Pageable paginacao);

	Aluno findByNome(String nome);
	
	@Query("select a from Aluno a where a.nome = ?1")
	List<Aluno> findByNome2 (String nome);
}
