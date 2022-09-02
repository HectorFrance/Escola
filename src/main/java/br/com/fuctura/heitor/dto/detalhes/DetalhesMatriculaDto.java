package br.com.fuctura.heitor.dto.detalhes;

import java.time.LocalDateTime;

import br.com.fuctura.heitor.model.Aluno;
import br.com.fuctura.heitor.model.Matricula;
import br.com.fuctura.heitor.model.Turma;
import lombok.Data;

@Data
public class DetalhesMatriculaDto {

	private Long id;
	private Turma turma;
	private Aluno aluno;
	private LocalDateTime dataMatricula;

	public DetalhesMatriculaDto(Matricula matricula) {
		this.id = matricula.getId();
		this.aluno = matricula.getAluno();
		this.turma = matricula.getTurma();
		this.dataMatricula = matricula.getDataMatricula();
	}
}
