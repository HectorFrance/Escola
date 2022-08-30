package br.com.fuctura.heitor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.fuctura.heitor.dto.ProfessorDto;
import br.com.fuctura.heitor.model.Professor;
import br.com.fuctura.heitor.repository.ProfessorRepository;

@RestController
@RequestMapping("/professor")
public class ProfessorController {

	@Autowired
	private ProfessorRepository professorRepository;

	public Page<ProfessorDto> listaProfessor(@RequestParam(required = false) String nomeProfessor,
			@PageableDefault(sort = "id", direction = Direction.ASC, page = 0, size = 10) Pageable paginacao) {
		
		if (nomeProfessor == null) {
			Page<Professor> professores = professorRepository.findAll(paginacao);
			return ProfessorDto.converter(professores);
		}else {
			Page<Professor> professores = professorRepository.findBynome();
		}
	}
}
