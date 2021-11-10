package br.com.avaliacao.spring.domain.dto;

import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

public class TransacaoDTO {

	private Long id;

	@NotNull(message = "Preenchimento obrigatório!")
	private Long faturaId;

	private String descricao;

	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private Date data;

	private BigDecimal valor;

	public TransacaoDTO() {

	}

	public TransacaoDTO(Long id, Long faturaId, String descricao, Date data, BigDecimal valor) {
		this.id = id;
		this.faturaId = faturaId;
		this.descricao = descricao;
		this.data = data;
		this.valor = valor;
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

}
