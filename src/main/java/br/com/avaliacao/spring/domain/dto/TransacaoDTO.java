package br.com.avaliacao.spring.domain.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.avaliacao.spring.domain.enums.SituacaoTransacao;

public class TransacaoDTO {

	private Long id;

	private Long faturaId;

	private String descricao;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
	private Date data;

	private BigDecimal valor;

	@JsonIgnore
	private Integer situacaoId;

	public TransacaoDTO() {

	}

	public TransacaoDTO(Long id, Long faturaId, String descricao, Date data, BigDecimal valor, Integer situacaoId) {
		this.id = id;
		this.faturaId = faturaId;
		this.descricao = descricao;
		this.data = data;
		this.valor = valor;
		this.situacaoId = situacaoId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getFaturaId() {
		return faturaId;
	}

	public void setFaturaId(Long faturaId) {
		this.faturaId = faturaId;
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

	public Integer getSituacaoId() {
		return situacaoId;
	}

	public void setSituacaoId(Integer situacaoId) {
		this.situacaoId = situacaoId;
	}

	public String getSituacao() {
		return SituacaoTransacao.toEnum(this.situacaoId).getDescricao();
	}
}
