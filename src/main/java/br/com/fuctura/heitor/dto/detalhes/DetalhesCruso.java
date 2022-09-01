package br.com.fuctura.heitor.dto.detalhes;

import br.com.fuctura.heitor.model.Curso;
import lombok.Data;

@Data
public class DetalhesCruso {
	
	private Long id;
	private String nome;  
	private String requisitos;  
	private Integer cargaHoraria;    
	private Float preco;
	
	public DetalhesCruso(Curso curso) {
		this.id = curso.getId();
		this.nome = curso.getNome();
		this.requisitos = curso.getRequisitos();
		this.cargaHoraria = curso.getCargaHoraria();
		this.preco = curso.getPreco();
	}
}
