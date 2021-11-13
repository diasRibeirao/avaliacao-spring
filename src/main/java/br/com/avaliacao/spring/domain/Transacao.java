package br.com.avaliacao.spring.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;

import br.com.avaliacao.spring.domain.dto.TransacaoCartaoCreditoDTO;
import br.com.avaliacao.spring.domain.enums.SituacaoTransacao;

@Entity
@Table(name = "TRANSACAO")
public class Transacao implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "FATURA_ID")
	private Fatura fatura;

	@Column(name = "DESCRICAO")
	private String descricao;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DATA")
	private Date data;

	@Column(name = "VALOR", precision = 8, scale = 2)
	private BigDecimal valor;

	@Column(name = "SITUACAO")
	private Integer situacao;

	public Transacao() {

	}

	public Transacao(Long id, Fatura fatura, String descricao, Date data, BigDecimal valor, SituacaoTransacao situacao) {
		this(fatura, descricao, data, valor, situacao);
		this.id = id;
	}

	public Transacao(Fatura fatura, String descricao, Date data, BigDecimal valor, SituacaoTransacao situacao) {
		this.fatura = fatura;
		this.descricao = descricao;
		this.data = data;
		this.valor = valor;
		this.situacao = (situacao == null) ? null : situacao.getCod();
	}

	public Transacao(@Valid TransacaoCartaoCreditoDTO objDTO, Fatura fatura) {
		this.fatura = fatura;
		this.descricao = objDTO.getDescricaoCompra();
		this.data = Calendar.getInstance().getTime();
		this.valor = objDTO.getValorCompra();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Fatura getFatura() {
		return fatura;
	}

	public void setFatura(Fatura fatura) {
		this.fatura = fatura;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public Integer getSituacao() {
		return situacao;
	}

	public void setSituacao(Integer situacao) {
		this.situacao = situacao;
	}

}
