package br.com.avaliacao.spring.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "CARTAO_CREDITO")
public class CartaoCredito implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "ALUNO_ID")
	private Aluno aluno;

	@Column(name = "NUMERO", unique = true)
	private String numero;

	@Column(name = "NOME")
	private String nome;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "VENCIMENTO")
	private Date vencimento;

	@Column(name = "LIMITE", precision = 8, scale = 2)
	private BigDecimal limite;

	@JsonIgnore
	@OneToMany(mappedBy = "cartaoCredito")
	private List<Fatura> faturas = new ArrayList<Fatura>();

	public CartaoCredito() {

	}

	public CartaoCredito(Long id) {
		this.id = id;
	}

	public CartaoCredito(Long id, Aluno aluno, String numero, String nome, Date vencimento, BigDecimal limite) {
		this(aluno, numero, nome, vencimento, limite);
		this.id = id;
	}

	public CartaoCredito(Aluno aluno, String numero, String nome, Date vencimento, BigDecimal limite) {
		this.aluno = aluno;
		this.numero = numero;
		this.nome = nome;
		this.vencimento = vencimento;
		this.limite = limite;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getVencimento() {
		return vencimento;
	}

	public void setVencimento(Date vencimento) {
		this.vencimento = vencimento;
	}

	public BigDecimal getLimite() {
		return limite;
	}

	public void setLimite(BigDecimal limite) {
		this.limite = limite;
	}

	public List<Fatura> getFaturas() {
		return faturas;
	}

	public void setFaturas(List<Fatura> faturas) {
		this.faturas = faturas;
	}

}
