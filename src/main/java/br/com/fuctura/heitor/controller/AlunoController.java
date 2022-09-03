package br.com.fuctura.heitor.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
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

import com.lowagie.text.DocumentException;

import br.com.fuctura.heitor.dto.AlunoDto;
import br.com.fuctura.heitor.dto.detalhes.DetalhesAlunoDto;
import br.com.fuctura.heitor.dto.form.AlunoForm;
import br.com.fuctura.heitor.dto.form.atualizacao.AtualizacaoAlunoForm;
import br.com.fuctura.heitor.model.Aluno;
import br.com.fuctura.heitor.report.AlunoPDFExporter;
import br.com.fuctura.heitor.repository.AlunoRepository;
import br.com.fuctura.heitor.service.AlunoServices;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/aluno")
public class AlunoController {

	@Autowired
	private AlunoRepository alunoRepository;

	@Autowired
	private AlunoServices alunoServices;

	@GetMapping("/todos")
	public List<AlunoDto> listaAlunos() {
		List<Aluno> Alunos = alunoRepository.findAll();
		return AlunoDto.converter(Alunos);
	}

	@GetMapping
	@Cacheable(value = "listaDeAlunos")
	@Operation(summary = "listarAlunos", description = "listar os alunos da escola")
	public Page<AlunoDto> listaAlunos(@RequestParam(required = false) String nomeAluno,
			@PageableDefault(sort = "id", direction = Direction.ASC, page = 0, size = 10) Pageable paginacao) {

		if (nomeAluno == null) {
			Page<Aluno> Alunos = alunoRepository.findAll(paginacao);
			return AlunoDto.converter(Alunos);
		} else {
			Page<Aluno> Alunos = alunoRepository.findByNome(nomeAluno, paginacao);
			return AlunoDto.converter(Alunos);
		}
	}

	@GetMapping("/{id}")
	@Operation(summary = "detalhar", description = "detalha um aluno de acordo com o Id")
	public ResponseEntity<DetalhesAlunoDto> detalhar(@PathVariable Long id) {
		Optional<Aluno> Aluno = alunoRepository.findById(id);
		if (Aluno.isPresent()) {
			return ResponseEntity.ok(new DetalhesAlunoDto(Aluno.get()));
		}

		return ResponseEntity.notFound().build();
	}

	@PostMapping
	@Transactional
	@CacheEvict(value = "listaDeAlunos", allEntries = true)
	@Operation(summary = "cadastrar", description = "Cadastra um aluno na escola")
	public ResponseEntity<AlunoDto> cadastrar(@RequestBody @Valid AlunoForm form) {
		Aluno aluno = form.converterDTO();
		alunoRepository.save(aluno);

		return new ResponseEntity<AlunoDto>(new AlunoDto(aluno), HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	@Transactional
	@CacheEvict(value = "listaDeAlunos", allEntries = true)
	@Operation(summary = "atualizar", description = "Atualiza os dados de um aluno pelo id")
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
	@CacheEvict(value = "listaDeAlunos", allEntries = true)
	@Operation(summary = "Remover", description = "Remove um Aluno por id")
	public ResponseEntity<?> remover(@PathVariable Long id) {
		Optional<Aluno> optional = alunoRepository.findById(id);
		if (optional.isPresent()) {
			alunoRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}

		return ResponseEntity.notFound().build();
	}

	@GetMapping("/relatorio-pdf")
	public void exportarRelatorioPDF(HttpServletResponse response) throws DocumentException, IOException {
		response.setContentType("application/pdf");
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormatter.format(new Date());

		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=alunos_relatorio" + currentDateTime + ".pdf";
		response.setHeader(headerKey, headerValue);

		List<Aluno> listaAlunos = alunoServices.listarTodosAlunos();

		AlunoPDFExporter exporter = new AlunoPDFExporter(listaAlunos);
		exporter.export(response);
	}
}
