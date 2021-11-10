package br.com.avaliacao.spring.domain.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class FaturaDTO {

	private Long cartaoCreditoId;

	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private Date dataFechamento;

	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private Date dataVencimento;

	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private Date dataPagamento;

	private BigDecimal valorTotal;

	private BigDecimal valorPago;

	public FaturaDTO() {

	}

	public FaturaDTO(Long cartaoCreditoId, Date dataFechamento, Date dataVencimento, Date dataPagamento,
			BigDecimal valorTotal, BigDecimal valorPago) {
		this.cartaoCreditoId = cartaoCreditoId;
		this.dataFechamento = dataFechamento;
		this.dataVencimento = dataVencimento;
		this.dataPagamento = dataPagamento;
		this.valorTotal = valorTotal;
		this.valorPago = valorPago;
	}

	public Long getCartaoCreditoId() {
		return cartaoCreditoId;
	}

	public void setCartaoCreditoId(Long cartaoCreditoId) {
		this.cartaoCreditoId = cartaoCreditoId;
	}

	public Date getDataFechamento() {
		return dataFechamento;
	}

	public void setDataFechamento(Date dataFechamento) {
		this.dataFechamento = dataFechamento;
	}

	public Date getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public Date getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

	public BigDecimal getValorPago() {
		return valorPago;
	}

	public void setValorPago(BigDecimal valorPago) {
		this.valorPago = valorPago;
	}

}
