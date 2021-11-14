package br.com.avaliacao.spring.domain.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class FaturaDTO {

	private Long id;

	private Long cartaoCreditoId;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
	private Date dataFechamento;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
	private Date dataVencimento;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
	private Date dataPagamento;

	private BigDecimal valorTotal;

	private BigDecimal valorPago;

	private Boolean vencida;

	public FaturaDTO() {

	}

	public FaturaDTO(Long id, Long cartaoCreditoId, Date dataFechamento, Date dataVencimento,
			Date dataPagamento, BigDecimal valorTotal, BigDecimal valorPago, Boolean vencida) {
		this.id = id;
		this.cartaoCreditoId = cartaoCreditoId;
		this.dataFechamento = dataFechamento;
		this.dataVencimento = dataVencimento;
		this.dataPagamento = dataPagamento;
		this.valorTotal = valorTotal;
		this.valorPago = valorPago;
		this.vencida = vencida;
	}


}
