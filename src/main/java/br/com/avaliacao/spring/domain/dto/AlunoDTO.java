package br.com.avaliacao.spring.domain.dto;

public class AlunoDTO {

	private Long id;

	private String matricula;

	private String nome;

	public AlunoDTO() {

	}

	public AlunoDTO(Long id, String matricula, String nome) {
		this.id = id;
		this.matricula = matricula;
		this.nome = nome;
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
