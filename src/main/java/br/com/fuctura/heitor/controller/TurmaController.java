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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.fuctura.heitor.dto.TurmaDto;
import br.com.fuctura.heitor.dto.detalhes.DetalhesTurmaDto;
import br.com.fuctura.heitor.dto.form.TurmaForm;
import br.com.fuctura.heitor.dto.form.atualizacao.AtualizacaoTurmaForm;
import br.com.fuctura.heitor.model.Turma;
import br.com.fuctura.heitor.repository.TurmaRepository;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/turma")
public class TurmaController {

	@Autowired
	private TurmaRepository turmaRepository;

	@GetMapping
	@Operation(summary = "listaTurmas", description = "Lista todos as turmas da escola")
	public Page<TurmaDto> listaTurmas(@RequestParam(required = false) String nomeTurma,
			@PageableDefault(sort = "id", direction = Direction.ASC, page = 0, size = 10) Pageable paginacao) {

		if (nomeTurma == null) {
			Page<Turma> turma = turmaRepository.findAll(paginacao);
			return TurmaDto.converter(turma);
		} else {
			Page<Turma> turma = turmaRepository.findByNome(nomeTurma, paginacao);
			return TurmaDto.converter(turma);
		}
	}

	@GetMapping("/{id}")
	@Operation(summary = "detalhar", description = "Detalha um curso pelo id")
	public ResponseEntity<DetalhesTurmaDto> detalhar(@PathVariable Long id) {
		Optional<Turma> turma = turmaRepository.findById(id);
		if (turma.isPresent()) {
			return ResponseEntity.ok(new DetalhesTurmaDto(turma.get()));
		}
		return ResponseEntity.notFound().build();
	}

	@PostMapping
	@Transactional
	@Operation(summary = "Cadastrar", description = "Cadastra uma nova turma")
	public ResponseEntity<TurmaDto> cadastrar(@RequestBody @Valid TurmaForm form) {
		Turma turma = form.converterDto();
		turmaRepository.save(turma);

		return new ResponseEntity<TurmaDto>(new TurmaDto(turma), HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	@Transactional
	@Operation(summary = "atualizar", description = "Atualiza os dados de uma turma pelo id")
	public ResponseEntity<TurmaDto> atualizar(@PathVariable Long id, @RequestBody @Valid AtualizacaoTurmaForm form) {
		Optional<Turma> opt = turmaRepository.findById(id);
		if (opt.isPresent()) {
			Turma turma = form.atualizar(id, turmaRepository);
			return ResponseEntity.ok(new TurmaDto(turma));
		}
		return ResponseEntity.notFound().build();
	}

	public ResponseEntity<?> remover(@PathVariable Long id) {
		Optional<Turma> opt = turmaRepository.findById(id);
		if (opt.isPresent()) {
			turmaRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}
}
