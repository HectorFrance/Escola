package br.com.fuctura.heitor.dto.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.lang.Nullable;

import br.com.fuctura.heitor.model.Professor;

public class ProfessorForm {
	
	@NotNull @NotEmpty @Length(min = 11, max = 11)
	private String cpf;

	@NotNull @NotEmpty  @Length(min = 5)
	private String nome;

	@Nullable
	private String email;

	@Nullable
	private String certificados;
	
	@Nullable
	private Float valorHora;

	@Nullable
	private String tipo;
	
	public Professor converterDTO() {
		Professor p = new Professor(cpf, nome, email, valorHora, certificados, tipo);
		return p;
	}
}
