package br.com.fuctura.heitor.dto;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;
import org.springframework.lang.Nullable;

import br.com.fuctura.heitor.model.Aluno;
import br.com.fuctura.heitor.model.Matricula;
import br.com.fuctura.heitor.model.Turma;
import lombok.Data;

@Data
public class MatriculaDto {

	@Nullable
	private Turma turma;

	@Nullable
	private Aluno aluno;

	@Nullable
	private LocalDateTime dataMatricula;

	public MatriculaDto(Matricula matricula) {
		this.turma = matricula.getTurma();
		this.aluno = matricula.getAluno();
		this.dataMatricula = matricula.getDataMatricula();
	}

	public static Page<MatriculaDto> converter(Page<Matricula> matriculas) {
		return matriculas.map(MatriculaDto::new);
	}
}
