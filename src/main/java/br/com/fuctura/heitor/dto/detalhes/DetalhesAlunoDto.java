package br.com.fuctura.heitor.dto.detalhes;

import br.com.fuctura.heitor.model.Aluno;
import lombok.Data;

@Data
public class DetalhesAlunoDto {

	private Long id;
	private String cpf;
	private String nome;
	private String email;
	private String fone;
	private String tipo;

	public DetalhesAlunoDto(Aluno aluno) {
		this.id = aluno.getId();
		this.cpf = aluno.getCpf();
		this.nome = aluno.getNome();
		this.email = aluno.getEmail();
		this.fone = aluno.getFone();
		this.tipo = aluno.getTipo().toString();
	}

	// cria apenas os métodos getters
}