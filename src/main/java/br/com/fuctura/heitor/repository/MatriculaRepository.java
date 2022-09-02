package br.com.fuctura.heitor.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fuctura.heitor.model.Aluno;
import br.com.fuctura.heitor.model.Matricula;

public interface MatriculaRepository extends JpaRepository<Matricula, Long>{
	
	Page<Matricula> findByAluno(Aluno aluno, Pageable paginacao);
	
	
}
