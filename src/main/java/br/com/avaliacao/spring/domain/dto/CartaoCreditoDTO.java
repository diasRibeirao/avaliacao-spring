package br.com.avaliacao.spring.domain.dto;

import java.math.BigDecimal;
import java.time.YearMonth;

import com.fasterxml.jackson.annotation.JsonFormat;

public class CartaoCreditoDTO {

	private Long id;

	private long alunoId;

	private String numero;

	private String nome;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM/yyyy")
	private YearMonth vencimento;

	private BigDecimal limite;

	private BigDecimal valorDisponivel;

	private String codigoSeguranca;

	private Integer melhorDiaCompra;

	private Boolean ativo;

	public CartaoCreditoDTO() {

	}

	public CartaoCreditoDTO(Long id, long alunoId, String numero, String nome, YearMonth vencimento,
			BigDecimal valorDisponivel, BigDecimal limite, String codigoSeguranca, Integer melhorDiaCompra,
			Boolean ativo) {
		this.id = id;
		this.alunoId = alunoId;
		this.numero = numero;
		this.nome = nome;
		this.vencimento = vencimento;
		this.limite = limite;
		this.valorDisponivel = valorDisponivel;
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

	public BigDecimal getValorDisponivel() {
		return valorDisponivel;
	}

	public void setValorDisponivel(BigDecimal valorDisponivel) {
		this.valorDisponivel = valorDisponivel;
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
}
