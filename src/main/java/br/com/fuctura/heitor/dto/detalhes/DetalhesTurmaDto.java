package br.com.fuctura.heitor.dto.detalhes;

import br.com.fuctura.heitor.model.Curso;
import br.com.fuctura.heitor.model.Professor;
import br.com.fuctura.heitor.model.Turma;
import lombok.Data;

@Data
public class DetalhesTurmaDto {

	private Long id;
	private String nome;
	private Professor professor;
	private Curso curso;
	private Integer cargaHoraria;

	public DetalhesTurmaDto(Turma turma) {
		this.id = turma.getId();
		this.nome = turma.getNome();
		this.professor = turma.getProfessor();
		this.curso = turma.getCurso();
		this.cargaHoraria = turma.getCargaHoraria();
	}
}
