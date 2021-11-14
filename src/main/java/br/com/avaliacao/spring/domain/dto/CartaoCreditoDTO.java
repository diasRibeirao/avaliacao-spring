package br.com.avaliacao.spring.domain.dto;

import java.math.BigDecimal;
import java.time.YearMonth;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
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


	public Boolean isAtivo() {
		return ativo;
	}

}
