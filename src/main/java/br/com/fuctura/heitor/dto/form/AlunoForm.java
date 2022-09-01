package br.com.fuctura.heitor.dto.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.lang.Nullable;

import br.com.fuctura.heitor.model.Aluno;
import lombok.Data;

@Data
public class AlunoForm {

	@NotNull @NotEmpty @Length(min = 11, max = 11)
	private String cpf;

	@NotNull @NotEmpty  @Length(min = 5)
	private String nome;

	@Nullable
	private String email;

	@Nullable
	private String fone;

	@Nullable
	private String tipo;

	// ... getters/setters
	public Aluno converterDTO() {
		Aluno aluno = new Aluno(cpf, nome, email, fone, tipo); 
		return aluno;
	}

}