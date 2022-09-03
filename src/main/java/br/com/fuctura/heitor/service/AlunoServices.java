package br.com.fuctura.heitor.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.fuctura.heitor.model.Aluno;
import br.com.fuctura.heitor.repository.AlunoRepository;

@Service
@Transactional
public class AlunoServices {

	@Autowired
	private AlunoRepository repositorioAlunos;

	public List<Aluno> listarTodosAlunos() {
		return repositorioAlunos.findAll(Sort.by("id").ascending());
	}

}