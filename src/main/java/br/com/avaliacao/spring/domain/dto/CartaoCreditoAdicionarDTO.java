package br.com.avaliacao.spring.domain.dto;

import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.avaliacao.spring.services.validation.CartaoCreditoAdicionar;

@CartaoCreditoAdicionar
public class CartaoCreditoAdicionarDTO {

	@NotNull(message = "Preenchimento obrigatório!")
	private long alunoId;

	@NotEmpty(message = "Preenchimento obrigatório!")
	@Size(min = 16, max = 16, message = "O nome deve possuir {max} caracteres!")
	private String numero;

	@NotEmpty(message = "Preenchimento obrigatório!")
	@Size(min = 2, max = 120, message = "O nome deve possuir entre {min} e {max} caracteres!")
	private String nome;

	@NotNull(message = "Preenchimento obrigatório!")
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private Date vencimento;

	@NotNull(message = "Preenchimento obrigatório!")
	private BigDecimal limite;

	public CartaoCreditoAdicionarDTO() {
		
	}
	
	public CartaoCreditoAdicionarDTO(long alunoId, String numero, String nome, Date vencimento, BigDecimal limite) {
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

}
