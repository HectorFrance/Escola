package br.com.fuctura.heitor.dto.form.atualizacao;

import java.util.Optional;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.lang.Nullable;

import br.com.fuctura.heitor.model.Curso;
import br.com.fuctura.heitor.repository.CursoRepository;

public class AtualizacaoCursoForm {
	
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
	
	public Curso atualizar(Long id, CursoRepository cursoRepository) {
		Optional<Curso> curso = cursoRepository.findById(id);
		if(curso.isPresent()){
			curso.get().setNome(nome);
			curso.get().setRequisitos(requisitos);
			curso.get().setCargaHoraria(cargaHoraria);
			curso.get().setPreco(preco);
			return curso.get();
		}
		return null;
	}
}
