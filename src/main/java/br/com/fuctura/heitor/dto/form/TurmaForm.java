package br.com.fuctura.heitor.dto.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.lang.Nullable;

import br.com.fuctura.heitor.model.Curso;
import br.com.fuctura.heitor.model.Professor;
import br.com.fuctura.heitor.model.Turma;
import lombok.Data;

@Data
public class TurmaForm {

	@NotNull
	@NotEmpty
	@Length(min = 11, max = 11)
	private String nome;

	@Nullable
	private Professor professor;

	@NotNull
	@NotEmpty
	private Curso curso;

	@NotNull
	@NotEmpty
	private Integer cargaHoraria;
	
	public Turma converterDto() {
		Turma turma = new Turma(nome, professor, curso, cargaHoraria);
		return turma;
	}
}
