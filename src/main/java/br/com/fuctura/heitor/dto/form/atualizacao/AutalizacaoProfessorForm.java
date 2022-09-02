package br.com.fuctura.heitor.dto.form.atualizacao;

import java.util.Optional;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.lang.Nullable;

import br.com.fuctura.heitor.model.Professor;
import br.com.fuctura.heitor.repository.ProfessorRepository;
import lombok.Data;

@Data
public class AutalizacaoProfessorForm {
	@NotNull
	@NotEmpty
	@Length(min = 5)
	private String nome;

	@Nullable
	private String email;

	@Nullable
	private String tipo;

	@Nullable
	private Float valorHora;

	@Nullable
	private String certificados;

	public Professor atualizar(Long id, ProfessorRepository professorRepository) {
		Optional<Professor> professor = professorRepository.findById(id);

		if (professor.isPresent()) {
			professor.get().setCertificados(certificados);
			professor.get().setEmail(email);
			professor.get().setNome(nome);
			professor.get().setTipo(tipo);
			professor.get().setValorHora(valorHora);

			return professor.get();
		}
		return null;
	}
}
