package br.com.fuctura.heitor.dto.form.atualizacao;

import java.time.LocalDateTime;
import java.util.Optional;

import br.com.fuctura.heitor.model.Aluno;
import br.com.fuctura.heitor.model.Matricula;
import br.com.fuctura.heitor.model.Turma;
import br.com.fuctura.heitor.repository.MatriculaRepository;
import lombok.Data;

@Data
public class AtualizacaoMatriculaForm {

	private Turma turma;
	private Aluno aluno;
	private LocalDateTime dataMatricula;

	public Matricula atualizar(Long id, MatriculaRepository matriculaRepository) {
		Optional<Matricula> matricula = matriculaRepository.findById(id);
		if (matricula.isPresent()) {
			matricula.get().setAluno(this.aluno);
			matricula.get().setDataMatricula(this.dataMatricula);
			matricula.get().setTurma(this.turma);
			return matricula.get();
		}
		return null;
	}
}
