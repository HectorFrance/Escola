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

import br.com.fuctura.heitor.dto.ProfessorDto;
import br.com.fuctura.heitor.dto.detalhes.DetalhesProfessorDto;
import br.com.fuctura.heitor.dto.form.ProfessorForm;
import br.com.fuctura.heitor.dto.form.atualizacao.AutalizacaoProfessorForm;
import br.com.fuctura.heitor.model.Professor;
import br.com.fuctura.heitor.repository.ProfessorRepository;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/professor")
public class ProfessorController {

	@Autowired
	private ProfessorRepository professorRepository;

	@GetMapping
	@Operation(summary = "listaProfessores", description = "Lista os professores da escola")
	public Page<ProfessorDto> listaProfessores(@RequestParam(required = false) String nomeProfessor,
			@PageableDefault(sort = "id", direction = Direction.ASC, page = 0, size = 10) Pageable paginacao) {

		if (nomeProfessor == null) {
			Page<Professor> professores = professorRepository.findAll(paginacao);
			return ProfessorDto.converter(professores);
		} else {
			Page<Professor> professores = professorRepository.findByNome(nomeProfessor, paginacao);
			return ProfessorDto.converter(professores);
		}
	}

	@GetMapping("/{id}")
	@Operation(summary = "detalhar", description = "detalha um professor de acordo com o Id")
	public ResponseEntity<DetalhesProfessorDto> detalhar(@PathVariable Long id) {
		Optional<Professor> professor = professorRepository.findById(id);
		if (professor.isPresent()) {
			return ResponseEntity.ok(new DetalhesProfessorDto(professor.get()));
		}
		return ResponseEntity.notFound().build();
	}

	@PostMapping
	@Transactional
	@Operation(summary = "cadastrar", description = "Cadastra um novo professor")
	public ResponseEntity<ProfessorDto> cadastrar(@RequestBody @Valid ProfessorForm form) {
		Professor professor = form.converterDTO();
		professorRepository.save(professor);
		return new ResponseEntity<ProfessorDto>(new ProfessorDto(professor), HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	@Transactional
	@Operation(summary = "Atualizar", description = "Atualiza os dados de um professor pelo id")
	public ResponseEntity<ProfessorDto> atualizar(@PathVariable Long id,
			@RequestBody @Valid AutalizacaoProfessorForm form) {
		Optional<Professor> professor = professorRepository.findById(id);
		if (professor.isPresent()) {
			Professor p = form.atualizar(id, professorRepository);
			return ResponseEntity.ok(new ProfessorDto(p));
		}
		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	@Transactional
	@Operation(summary = "remover", description = "Remove um professor pelo ID")
	public ResponseEntity<?> remover(@PathVariable Long id) {
		Optional<Professor> professor = professorRepository.findById(id);
		if (professor.isPresent()) {
			professorRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}
}
