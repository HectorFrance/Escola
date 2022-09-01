package br.com.fuctura.heitor.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.data.domain.Page;

import br.com.fuctura.heitor.model.Curso;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CursoDto {

	@NotNull
	@NotEmpty
	@Length(min = 5)
	private String nome;

	@NotNull
	@NotEmpty
	private Float preco;

	public CursoDto(Curso curso) {
		this.nome = curso.getNome();
		this.preco = curso.getPreco();
	}

	public static Page<CursoDto> converter(Page<Curso> curso) {
		return curso.map(CursoDto::new);
	}
}
