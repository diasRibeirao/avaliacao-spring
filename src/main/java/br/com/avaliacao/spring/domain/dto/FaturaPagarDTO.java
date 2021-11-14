package br.com.avaliacao.spring.domain.dto;

import java.math.BigDecimal;

public class FaturaPagarDTO {

	private BigDecimal valorPago;

	public FaturaPagarDTO() {

	}

	public FaturaPagarDTO(BigDecimal valorPago) {
		this.valorPago = valorPago;
	}

	public BigDecimal getValorPago() {
		return valorPago;
	}

	public void setValorPago(BigDecimal valorPago) {
		this.valorPago = valorPago;
	}

}
