package br.com.fuctura.heitor.controller;

import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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

import br.com.fuctura.heitor.dto.MatriculaDto;
import br.com.fuctura.heitor.dto.detalhes.DetalhesMatriculaDto;
import br.com.fuctura.heitor.dto.form.MatriculaForm;
import br.com.fuctura.heitor.dto.form.atualizacao.AtualizacaoMatriculaForm;
import br.com.fuctura.heitor.model.Aluno;
import br.com.fuctura.heitor.model.Matricula;
import br.com.fuctura.heitor.repository.AlunoRepository;
import br.com.fuctura.heitor.repository.MatriculaRepository;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/matricula")
public class MatriculaController {

	@Autowired
	private MatriculaRepository matriculaRepository;

	@Autowired
	AlunoRepository alunoRepository;

	@GetMapping
	@Operation(summary = "listaMatriculas", description = "Lista as matriculas da escola")
	public Page<MatriculaDto> listaMatriculas(@RequestParam(required = false) String nomeAluno,
			@PageableDefault(sort = "id", direction = Direction.ASC, page = 0, size = 10) Pageable paginacao) {
		if (nomeAluno == null) {
			Page<Matricula> matriculas = matriculaRepository.findAll(paginacao);
			return MatriculaDto.converter(matriculas);
		} else {
			Aluno a = alunoRepository.findByNome(nomeAluno);
			Page<Matricula> matriculas = matriculaRepository.findByAluno(a, paginacao);
			return MatriculaDto.converter(matriculas);
		}
	}

	@GetMapping("/{id}")
	@Operation(summary = "detalhar", description = "Detalha uma matricula de acordo com o id")
	public ResponseEntity<DetalhesMatriculaDto> detalhar(@PathVariable Long id) {
		Optional<Matricula> matricula = matriculaRepository.findById(id);
		if (matricula.isPresent()) {
			return ResponseEntity.ok(new DetalhesMatriculaDto(matricula.get()));
		}
		return ResponseEntity.notFound().build();
	}

	@PostMapping
	@Transactional
	@Operation(summary = "cadastrar", description = "Realiza uma nova matricula")
	public ResponseEntity<MatriculaDto> cadastrar(@RequestBody @Valid MatriculaForm form) {
		Matricula matricula = form.converterDto();
		matriculaRepository.save(matricula);

		return new ResponseEntity<MatriculaDto>(new MatriculaDto(matricula), HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	@Transactional
	@Operation(summary = "Atulizar", description = "Atualiza os dados da matricula")
	public ResponseEntity<MatriculaDto> atualizar(@PathVariable Long id,
			@RequestBody @Valid AtualizacaoMatriculaForm form) {
		Optional<Matricula> opt = matriculaRepository.findById(id);
		if (opt.isPresent()) {
			Matricula matricula = form.atualizar(id, matriculaRepository);
			return ResponseEntity.ok(new MatriculaDto(matricula));
		}
		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	@Transactional
	@Operation(summary = "remover", description = "Remove matricula por id")
	public ResponseEntity<?> remover(@PathVariable Long id) {
		Optional<Matricula> opt = matriculaRepository.findById(id);
		if (opt.isPresent()) {
			matriculaRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}
}
