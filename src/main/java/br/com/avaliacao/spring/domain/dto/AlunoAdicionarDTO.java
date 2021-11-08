package br.com.avaliacao.spring.domain.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import br.com.avaliacao.spring.services.validation.AlunoAdicionar;

@AlunoAdicionar
public class AlunoAdicionarDTO {

	@NotEmpty(message = "Preenchimento obrigatório!")
	@Size(min = 14, max = 14, message = "Matrícula deve possuir {max} caracteres!")
	private String matricula;

	@NotEmpty(message = "Preenchimento obrigatório!")
	@Size(min = 5, max = 400, message = "O nome deve possuir entre {min} e {max} caracteres!")
	private String nome;

	public AlunoAdicionarDTO() {
		super();
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
