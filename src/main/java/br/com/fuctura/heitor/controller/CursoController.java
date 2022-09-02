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

import br.com.fuctura.heitor.dto.CursoDto;
import br.com.fuctura.heitor.dto.detalhes.DetalhesCruso;
import br.com.fuctura.heitor.dto.form.CursoForm;
import br.com.fuctura.heitor.dto.form.atualizacao.AtualizacaoCursoForm;
import br.com.fuctura.heitor.model.Curso;
import br.com.fuctura.heitor.repository.CursoRepository;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/curso")
public class CursoController {

	@Autowired
	private CursoRepository cursoRepository;

	@GetMapping
	@Operation(summary = "listaCursos", description = "Lista os cursos da descola")
	public Page<CursoDto> listaCursos(@RequestParam(required = false) String nomeCurso,
			@PageableDefault(sort = "id", direction = Direction.ASC, page = 0, size = 10) Pageable paginacao) {
		if (nomeCurso == null) {
			Page<Curso> cursos = cursoRepository.findAll(paginacao);
			return CursoDto.converter(cursos);
		} else {
			Page<Curso> cursos = cursoRepository.findByNome(nomeCurso, paginacao);
			return CursoDto.converter(cursos);
		}
	}

	@GetMapping("/{id}")
	@Operation(summary = "detalhar", description = "detalha um curso de acordo com o Id")
	public ResponseEntity<DetalhesCruso> detalhar(@PathVariable Long id) {
		Optional<Curso> curso = cursoRepository.findById(id);
		if (curso.isPresent()) {
			return ResponseEntity.ok(new DetalhesCruso(curso.get()));
		}
		return ResponseEntity.notFound().build();
	}

	@PostMapping
	@Transactional
	@Operation(summary = "cadastrar", description = "Cadastra um novo curso")
	public ResponseEntity<CursoDto> cadastrar(@RequestBody @Valid CursoForm form) {
		Curso curso = form.converterDTO();
		cursoRepository.save(curso);
		return new ResponseEntity<CursoDto>(new CursoDto(curso), HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	@Transactional
	@Operation(summary = "atualizar", description = "Altera os dados de um cruso pelo id")
	public ResponseEntity<CursoDto> atualizar(@PathVariable Long id, @RequestBody @Valid AtualizacaoCursoForm form) {
		Optional<Curso> opt = cursoRepository.findById(id);
		if (opt.isPresent()) {
			Curso curso = form.atualizar(id, cursoRepository);
			return ResponseEntity.ok(new CursoDto(curso));
		}
		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	@Transactional
	@Operation(summary = "remover", description = "remove um curso pelo id")
	public ResponseEntity<?> remover(@PathVariable Long id) {
		Optional<Curso> opt = cursoRepository.findById(id);
		if (opt.isPresent()) {
			cursoRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}
}
