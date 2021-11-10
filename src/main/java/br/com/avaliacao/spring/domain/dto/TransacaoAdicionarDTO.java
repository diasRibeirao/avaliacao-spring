package br.com.avaliacao.spring.domain.dto;

import java.math.BigDecimal;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.avaliacao.spring.services.validation.TransacaoAdicionar;

@TransacaoAdicionar
public class TransacaoAdicionarDTO {

	@NotNull(message = "Preenchimento obrigatório!")
	private Long faturaId;

	@NotEmpty(message = "Preenchimento obrigatório!")
	@Size(min = 5, max = 400, message = "A descrição deve possuir entre {min} e {max} caracteres!")
	private String descricao;

	@NotNull(message = "Preenchimento obrigatório!")
	private BigDecimal valor;

	public TransacaoAdicionarDTO() {

	}

	public TransacaoAdicionarDTO(Long faturaId, String descricao, BigDecimal valor) {
		this.faturaId = faturaId;
		this.descricao = descricao;
		this.valor = valor;
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

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

}
