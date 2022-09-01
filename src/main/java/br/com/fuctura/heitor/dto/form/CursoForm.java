package br.com.fuctura.heitor.dto.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.lang.Nullable;

import br.com.fuctura.heitor.model.Curso;
import lombok.Data;

@Data
public class CursoForm {
	
	@NotNull
	@NotEmpty
	@Length(min = 5)
	private String nome;
	
	@Nullable
	private String requisitos;
	
	@Nullable
	private Integer cargaHoraria;  
	
	@NotNull
	@NotEmpty
	private Float preco;
	
	public Curso converterDTO() {
		Curso curso = new Curso (nome, requisitos, cargaHoraria, preco);
		return curso;
	}
}
