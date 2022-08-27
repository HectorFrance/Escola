package br.com.fuctura.heitor.controller;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.fuctura.heitor.controller.form.AlunoForm;
import br.com.fuctura.heitor.controller.form.AtualizacaoAlunoForm;
import br.com.fuctura.heitor.dto.AlunoDto;
import br.com.fuctura.heitor.dto.DetalhesAlunoDto;
import br.com.fuctura.heitor.model.Aluno;
import br.com.fuctura.heitor.repository.AlunoRepository;

@RestController
@RequestMapping("/aluno")
public class AlunoController {

	@Autowired
	private AlunoRepository alunoRepository;

	@GetMapping("/todos")
	public List<AlunoDto> listaAlunos() {
		List<Aluno> Alunos = alunoRepository.findAll();
		return AlunoDto.converter(Alunos);
	}
	
	@GetMapping
	@Cacheable(value = "listaDeAlunos")
	public Page<AlunoDto> listaAlunos(@RequestParam(required = false) String nomeAluno, 
			@PageableDefault(sort = "id", direction = Direction.ASC, page = 0, size = 10) Pageable paginacao){
		
	  if (nomeAluno == null) {
	    Page<Aluno> Alunos = alunoRepository.findAll(paginacao);
	    return AlunoDto.converter(Alunos);
	  } else {
	    Page<Aluno> Alunos = alunoRepository.findByNome(nomeAluno, paginacao);
	    return AlunoDto.converter(Alunos);
	  }
	}

	@GetMapping("/{id}")
	public ResponseEntity<DetalhesAlunoDto> detalhar(@PathVariable Long id) {
		System.out.println("Iniciando");
		Optional<Aluno> Aluno = alunoRepository.findById(id);
		if (Aluno.isPresent()) {
			return ResponseEntity.ok(new DetalhesAlunoDto(Aluno.get()));

		}

		return ResponseEntity.notFound().build();
	}

	@PostMapping
	@Transactional
	public ResponseEntity<AlunoDto> cadastrar(@RequestBody @Valid AlunoForm form) {
		Aluno aluno = form.converterDTO();
		alunoRepository.save(aluno);
		
		return new ResponseEntity<AlunoDto>(new AlunoDto(aluno), HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<AlunoDto> atualizar(@PathVariable Long id, @RequestBody @Valid AtualizacaoAlunoForm form) {
		Optional<Aluno> optional = alunoRepository.findById(id);
		if (optional.isPresent()) {
			Aluno Aluno = form.atualizar(id, alunoRepository);
			return ResponseEntity.ok(new AlunoDto(Aluno));
		}

		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> remover(@PathVariable Long id) {
		Optional<Aluno> optional = alunoRepository.findById(id);
		if (optional.isPresent()) {
			alunoRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}

		return ResponseEntity.notFound().build();
	}
}