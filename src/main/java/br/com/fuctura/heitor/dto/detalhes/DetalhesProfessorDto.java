package br.com.fuctura.heitor.dto.detalhes;

import br.com.fuctura.heitor.model.Professor;
import lombok.Data;

@Data
public class DetalhesProfessorDto {

	private Long id;
	private String cpf;
	private String nome;
	private String email;
	private Float valorHora;
	private String certificados;
	private String tipo;

	public DetalhesProfessorDto(Professor p) {
		this.id = p.getId();
		this.cpf = p.getCpf();
		this.nome = p.getNome();
		this.email = p.getEmail();
		this.valorHora = p.getValorHora();
		this.certificados = p.getCertificados();
		this.tipo = p.getTipo().toString();
	}
}
