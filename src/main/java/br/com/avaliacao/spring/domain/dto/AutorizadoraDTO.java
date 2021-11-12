package br.com.avaliacao.spring.domain.dto;

import java.math.BigDecimal;
import java.time.YearMonth;

import com.fasterxml.jackson.annotation.JsonFormat;

public class AutorizadoraDTO {

	private String numeroCartaoCredito;

	private String nomeImpresssoCartaoCredito;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM/yyyy")
	private YearMonth vencimentoCartaoCredito;

	private String codigoSegurancaCartaoCredito;

	private BigDecimal valorCompra;
	
	public AutorizadoraDTO() {
		
	}

	public AutorizadoraDTO(String numeroCartaoCredito, String nomeImpresssoCartaoCredito,
			YearMonth vencimentoCartaoCredito, String codigoSegurancaCartaoCredito, BigDecimal valorCompra) {
		this.numeroCartaoCredito = numeroCartaoCredito;
		this.nomeImpresssoCartaoCredito = nomeImpresssoCartaoCredito;
		this.vencimentoCartaoCredito = vencimentoCartaoCredito;
		this.codigoSegurancaCartaoCredito = codigoSegurancaCartaoCredito;
		this.valorCompra = valorCompra;
	}

	public String getNumeroCartaoCredito() {
		return numeroCartaoCredito;
	}

	public void setNumeroCartaoCredito(String numeroCartaoCredito) {
		this.numeroCartaoCredito = numeroCartaoCredito;
	}

	public String getNomeImpresssoCartaoCredito() {
		return nomeImpresssoCartaoCredito;
	}

	public void setNomeImpresssoCartaoCredito(String nomeImpresssoCartaoCredito) {
		this.nomeImpresssoCartaoCredito = nomeImpresssoCartaoCredito;
	}

	public YearMonth getVencimentoCartaoCredito() {
		return vencimentoCartaoCredito;
	}

	public void setVencimentoCartaoCredito(YearMonth vencimentoCartaoCredito) {
		this.vencimentoCartaoCredito = vencimentoCartaoCredito;
	}

	public String getCodigoSegurancaCartaoCredito() {
		return codigoSegurancaCartaoCredito;
	}

	public void setCodigoSegurancaCartaoCredito(String codigoSegurancaCartaoCredito) {
		this.codigoSegurancaCartaoCredito = codigoSegurancaCartaoCredito;
	}

	public BigDecimal getValorCompra() {
		return valorCompra;
	}

	public void setValorCompra(BigDecimal valorCompra) {
		this.valorCompra = valorCompra;
	}

}
