package br.com.avaliacao.spring.domain.dto;

import java.math.BigDecimal;
import java.time.YearMonth;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
@Data
public class TransacaoCartaoCreditoDTO {

	private String numeroCartaoCredito;

	private String nomeImpresssoCartaoCredito;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM/yyyy")
	private YearMonth vencimentoCartaoCredito;

	private String codigoSegurancaCartaoCredito;

	private String descricaoCompra;

	private BigDecimal valorCompra;

	public TransacaoCartaoCreditoDTO() {

	}

	public TransacaoCartaoCreditoDTO(String numeroCartaoCredito, String nomeImpresssoCartaoCredito,
			YearMonth vencimentoCartaoCredito, String codigoSegurancaCartaoCredito, String descricaoCompra,
			BigDecimal valorCompra) {
		this.numeroCartaoCredito = numeroCartaoCredito;
		this.nomeImpresssoCartaoCredito = nomeImpresssoCartaoCredito;
		this.vencimentoCartaoCredito = vencimentoCartaoCredito;
		this.codigoSegurancaCartaoCredito = codigoSegurancaCartaoCredito;
		this.descricaoCompra = descricaoCompra;
		this.valorCompra = valorCompra;
	}

}
