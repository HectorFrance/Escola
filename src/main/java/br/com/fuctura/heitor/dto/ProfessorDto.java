package br.com.fuctura.heitor.dto;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.data.domain.Page;
import org.springframework.lang.Nullable;

import br.com.fuctura.heitor.model.Professor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfessorDto {

	@NotNull
	@NotEmpty
	@Length(min = 9, max = 11)
	private String cpf;

	@NotNull
	@NotEmpty
	@Length(min = 5)
	private String nome;

	@Nullable
	private String email;

	@Nullable
	private String tipo;

	public ProfessorDto(Professor p) {

		this.cpf = p.getCpf();
		this.nome = p.getNome();
		this.email = p.getEmail();
		this.tipo = p.getTipo();
	}

	public static List<ProfessorDto> converter(List<Professor> professores) {
		return professores.stream().map(ProfessorDto::new).collect(Collectors.toList());
	}

	public static Page<ProfessorDto> converter(Page<Professor> professores) {
		return professores.map(ProfessorDto::new);
	}
}
