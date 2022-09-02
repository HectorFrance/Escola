package br.com.fuctura.heitor.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.data.domain.Page;
import org.springframework.lang.Nullable;

import br.com.fuctura.heitor.model.Curso;
import br.com.fuctura.heitor.model.Turma;
import lombok.Data;

@Data
public class TurmaDto {

	@NotNull
	@NotEmpty
	@Length(min = 5)
	private String nome;

	@NotEmpty
	@NotNull
	private Integer caragaHoraria;

	@Nullable
	private Curso curso;
	
	public TurmaDto(Turma turma) {
		this.nome = turma.getNome();
		this.caragaHoraria = turma.getCargaHoraria();
		this.curso=turma.getCurso();
	}
	
	public static Page<TurmaDto> converter(Page<Turma> turmas){
		return turmas.map(TurmaDto::new);
	}
}
