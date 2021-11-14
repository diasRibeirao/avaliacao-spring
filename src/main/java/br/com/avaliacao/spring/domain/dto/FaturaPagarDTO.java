package br.com.avaliacao.spring.domain.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class FaturaPagarDTO {

	private BigDecimal valorPago;

	public FaturaPagarDTO() {

	}

	public FaturaPagarDTO(BigDecimal valorPago) {
		this.valorPago = valorPago;
	}

}
