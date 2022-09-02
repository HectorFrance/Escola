package br.com.fuctura.heitor.dto.form.atualizacao;

import java.util.Optional;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.lang.Nullable;

import br.com.fuctura.heitor.model.Curso;
import br.com.fuctura.heitor.model.Professor;
import br.com.fuctura.heitor.model.Turma;
import br.com.fuctura.heitor.repository.TurmaRepository;
import lombok.Data;

@Data
public class AtualizacaoTurmaForm {

	@NotNull @NotEmpty  @Length(min = 5)
	private String nome;
	
	@Nullable
	private Professor professor;
	
	@Nullable
	private Curso curso;
	
	@NotEmpty
	@NotNull
	private Integer cargaHoraria;
	
	public Turma atualizar(Long id, TurmaRepository turmaRepository) {
		Optional<Turma> turma = turmaRepository.findById(id);
		
		if(turma.isPresent()) {
			turma.get().setCargaHoraria(this.cargaHoraria);
			turma.get().setCurso(this.curso);
			turma.get().setNome(this.nome);
			turma.get().setProfessor(this.professor);

			return turma.get();
		}
		return null;
	}
}
