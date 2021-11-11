package br.com.avaliacao.spring.domain.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.avaliacao.spring.services.validation.CartaoCreditoAtualizar;

@CartaoCreditoAtualizar
public class CartaoCreditoDTO {

	private Long id;

	private long alunoId;

	private String numero;

	private String nome;

	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private Date vencimento;

	private BigDecimal limite;
	
	public CartaoCreditoDTO() {
		
	}

	public CartaoCreditoDTO(Long id, long alunoId, String numero, String nome, Date vencimento, BigDecimal limite) {
		this.id = id;
		this.alunoId = alunoId;
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
