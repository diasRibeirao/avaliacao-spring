package br.com.avaliacao.spring.domain.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.avaliacao.spring.services.validation.AlunoAtualizar;

@AlunoAtualizar
public class AlunoDTO {

	@NotNull(message = "Preenchimento obrigatório!")
	private Long id;

	@NotEmpty(message = "Preenchimento obrigatório!")
	@Size(min = 14, max = 14, message = "Matrícula deve possuir {max} caracteres!")
	private String matricula;

	@NotEmpty(message = "Preenchimento obrigatório!")
	@Size(min = 5, max = 400, message = "O nome deve possuir entre {min} e {max} caracteres!")
	private String nome;

	public AlunoDTO() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
