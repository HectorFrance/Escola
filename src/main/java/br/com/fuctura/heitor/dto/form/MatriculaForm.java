package br.com.fuctura.heitor.dto.form;

import java.time.LocalDateTime;

import org.springframework.lang.Nullable;

import br.com.fuctura.heitor.model.Aluno;
import br.com.fuctura.heitor.model.Matricula;
import br.com.fuctura.heitor.model.Turma;
import lombok.Data;

@Data
public class MatriculaForm {

	@Nullable
	private Turma turma;

	@Nullable
	private Aluno aluno;

	@Nullable
	private LocalDateTime dataMatricula;

	public Matricula converterDto() {
		Matricula matricula = new Matricula(turma, aluno, dataMatricula);
		return matricula;
	}
}
