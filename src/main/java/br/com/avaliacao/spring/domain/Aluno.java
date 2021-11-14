package br.com.avaliacao.spring.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Data
@Table(name = "ALUNO")
public class Aluno implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

	@Column(name = "MATRICULA")
	private String matricula;

	@Column(name = "NOME")
	private String nome;

	@JsonIgnore
	@OneToMany(mappedBy = "aluno", fetch = FetchType.EAGER)
	private List<CartaoCredito> cartoes = new ArrayList<CartaoCredito>();

	public Aluno() {

	}

	public Aluno(Long id) {
		this.id = id;
	}

	public Aluno(Long id, String matricula, String nome) {
		this(matricula, nome);
		this.id = id;
	}

	public Aluno(String matricula, String nome) {
		this.matricula = matricula;
		this.nome = nome;
	}

	

}
