package br.com.avaliacao.spring.domain.dto;

import java.math.BigDecimal;
import java.time.YearMonth;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.avaliacao.spring.services.validation.CartaoCreditoAtualizar;

@CartaoCreditoAtualizar
public class CartaoCreditoAtualizarDTO {

	private long alunoId;

	@Size(min = 16, max = 16, message = "O nome deve possuir {max} caracteres!")
	private String numero;

	@Size(min = 2, max = 120, message = "O nome deve possuir entre {min} e {max} caracteres!")
	private String nome;

	@NotNull(message = "Preenchimento obrigatório!")
	@JsonFormat(pattern = "MM/yyyy")
	private YearMonth vencimento;

	@NotNull(message = "Preenchimento obrigatório!")
	private BigDecimal limite;
	
	public CartaoCreditoAtualizarDTO() {
		
	}

	public CartaoCreditoAtualizarDTO(long alunoId, String numero, String nome, YearMonth vencimento, BigDecimal limite) {
		this.alunoId = alunoId;
		this.numero = numero;
		this.nome = nome;
		this.vencimento = vencimento;
		this.limite = limite;
	}

	public long getAlunoId() {
		return alunoId;
	}

	public void setAlunoId(long alunoId) {
		this.alunoId = alunoId;
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

}
