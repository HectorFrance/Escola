package br.com.fuctura.heitor.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.fuctura.heitor.dto.AlunoDto;
import br.com.fuctura.heitor.model.types.TipoAluno;

@Table
@Entity
public class Aluno implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, name = "CPF")
	private String cpf;

	@Column(nullable = false, name = "NOME")
	private String nome;

	@Column(nullable = true, name = "EMAIL")
	private String email;

	@Column(nullable = true, name = "FONE")
	private String fone;

	@Column(nullable = false, name = "TIPO")
	private String tipo = TipoAluno.CONVENCIONAL.toString();

	// Contrutores
	public Aluno(Long id, String cpf, String nome, String email, String fone, String tipo) {
		super();
		this.id = id;
		this.cpf = cpf;
		this.nome = nome;
		this.email = email;
		this.fone = fone;
		this.tipo = tipo;
	}

	public Aluno(String cpf, String nome, String email, String fone, String tipo) {
		super();
		this.cpf = cpf;
		this.nome = nome;
		this.email = email;
		this.fone = fone;
		this.tipo = tipo;
	}

	public Aluno(AlunoDto alunoDto) {
		super();
		this.cpf = alunoDto.getCpf();
		this.nome = alunoDto.getNome();
		this.email = alunoDto.getEmail();
		this.fone = alunoDto.getFone();
		this.tipo = alunoDto.getTipo();
	}

	public Aluno(Long id, String cpf, String nome) {
		super();
		this.id = id;
		this.cpf = cpf;
		this.nome = nome;
	}

	public Aluno() {
		//
	}

	// Getters and Setters

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFone() {
		return fone;
	}

	public void setFone(String fone) {
		this.fone = fone;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	// To sring, Equals e Hashcode
	@Override
	public String toString() {
		return "Aluno => [id=" + id + ", cpf=" + cpf + ", nome=" + nome + ", email=" + email + ", fone=" + fone
				+ ", tipo=" + tipo + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(cpf, email, fone, id, nome, tipo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Aluno other = (Aluno) obj;
		return Objects.equals(cpf, other.cpf) && Objects.equals(email, other.email) && Objects.equals(fone, other.fone)
				&& Objects.equals(id, other.id) && Objects.equals(nome, other.nome) && Objects.equals(tipo, other.tipo);
	}

}
