package br.com.avaliacao.spring.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.avaliacao.spring.utils.Utils;

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

	@Column(name = "VENCIMENTO")
	private YearMonth vencimento;

	@Column(name = "LIMITE", precision = 8, scale = 2)
	private BigDecimal limite;

	@Column(name = "CODIGO_SEGURANCA")
	private String codigoSeguranca;

	@Column(name = "MELHOR_DIA_COMPRA")
	private Integer melhorDiaCompra;

	@Column(name = "ATIVO")
	private Boolean ativo;

	@JsonIgnore
	@OneToMany(mappedBy = "cartaoCredito",fetch = FetchType.EAGER)
	private List<Fatura> faturas = new ArrayList<Fatura>();

	public CartaoCredito() {

	}

	public CartaoCredito(Long id) {
		this.id = id;
	}

	public CartaoCredito(Long id, Aluno aluno, String numero, String nome, YearMonth vencimento, BigDecimal limite,
			String codigoSeguranca, Integer melhorDiaCompra, Boolean ativo) {
		this(aluno, numero, nome, vencimento, limite, codigoSeguranca, melhorDiaCompra, ativo);
		this.id = id;
	}

	public CartaoCredito(Aluno aluno, String numero, String nome, YearMonth vencimento, BigDecimal limite,
			String codigoSeguranca, Integer melhorDiaCompra, Boolean ativo) {
		this.aluno = aluno;
		this.numero = numero;
		this.nome = nome;
		this.vencimento = vencimento;
		this.limite = limite;
		this.codigoSeguranca = codigoSeguranca;
		this.melhorDiaCompra = melhorDiaCompra;
		this.ativo = ativo;
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

	public YearMonth getVencimento() {
		return vencimento;
	}

	public void setVencimento(YearMonth vencimento) {
		this.vencimento = vencimento;
	}

	public BigDecimal getLimite() {
		return limite;
	}

	public void setLimite(BigDecimal limite) {
		this.limite = limite;
	}
		
	public BigDecimal getValorDisponivel() {
		if (this.faturas.isEmpty()) {
			return this.limite;
		}
		
		BigDecimal valorFaturasAbertas = new BigDecimal(0);
		
		for (Fatura fatura : this.faturas) {
			if (fatura.getDataPagamento() == null) {
				valorFaturasAbertas = valorFaturasAbertas.add(fatura.getValorTotal());
			}
		}
		
		return this.limite.subtract(valorFaturasAbertas);
	}

	public List<Fatura> getFaturas() {
		return faturas;
	}

	public void setFaturas(List<Fatura> faturas) {
		this.faturas = faturas;
	}

	public String getCodigoSeguranca() {
		return codigoSeguranca;
	}

	public void setCodigoSeguranca(String codigoSeguranca) {
		this.codigoSeguranca = codigoSeguranca;
	}

	public Integer getMelhorDiaCompra() {
		return melhorDiaCompra;
	}

	public void setMelhorDiaCompra(Integer melhorDiaCompra) {
		this.melhorDiaCompra = melhorDiaCompra;
	}

	public Boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public Boolean isBloqueado() {
		return !ativo;
	}

	public String getLimiteFormatado() {
		return Utils.formatReal(this.limite);
	}

	public String getValorDisponivelFormatado() {
		return Utils.formatReal(this.getValorDisponivel());
	}

	public String getVencimentoFormatado() {
		return this.vencimento.toString();
	}
}
