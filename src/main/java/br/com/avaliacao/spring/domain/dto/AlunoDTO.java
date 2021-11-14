package br.com.avaliacao.spring.domain.dto;

import lombok.Data;

@Data
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

}
