package br.com.fuctura.heitor.controller;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fuctura.heitor.dto.AlunoDto;
import br.com.fuctura.heitor.model.Aluno;
import br.com.fuctura.heitor.model.TipoAluno;

@RestController
@RequestMapping("/alunos")
public class PrimeiroController {

	@GetMapping("/listar")
	public List<Aluno> listar() {
		List<Aluno> listaAlunos;
		Aluno a1 = new Aluno(1l, "1234567901", "Luan");
		Aluno a2 = new Aluno(2l, "1234567902", "Ruan");
		Aluno a3 = new Aluno(3l, "1234567903", "Juan");
		Aluno a4 = new Aluno(2l, "123567902", "Ruan");
		listaAlunos = Arrays.asList(a1, a2, a3, a4);
		return listaAlunos;
	}

	@GetMapping("/listar1")
	public List<Aluno> listarAlunos1() {
		Aluno aluno1 = new Aluno("11111111111", "Aluno 1", "aluno1@escola.com", "81 1234-5678",
				TipoAluno.CONVENCIONAL.toString());
		Aluno aluno2 = new Aluno("22222222222", "Aluno 2", "aluno2@escola.com", "81 1234-5678",
				TipoAluno.CONVENCIONAL.toString());
		Aluno aluno3 = new Aluno("33333333333", "Aluno 3", "aluno3@escola.com", "81 1234-5678",
				TipoAluno.MONITOR.toString());

		return Arrays.asList(aluno1, aluno2, aluno3);
	}

	@GetMapping("/listar2")
	public List<AlunoDto> listarAlunos2() {
		Aluno aluno1 = new Aluno("11111111111", "Aluno 1", "aluno1@escola.com", "81 1234-5678",
				TipoAluno.CONVENCIONAL.toString());
		Aluno aluno2 = new Aluno("22222222222", "Aluno 2", "aluno2@escola.com", "81 1234-5678",
				TipoAluno.CONVENCIONAL.toString());
		Aluno aluno3 = new Aluno("33333333333", "Aluno 3", "aluno3@escola.com", "81 1234-5678",
				TipoAluno.MONITOR.toString());

		List<Aluno> listaAlunos = Arrays.asList(aluno1, aluno2, aluno3);

		List<AlunoDto> listaAlunosDTO = listaAlunos.stream().map(AlunoDto::new).collect(Collectors.toList());

		return listaAlunosDTO;
	}

}
