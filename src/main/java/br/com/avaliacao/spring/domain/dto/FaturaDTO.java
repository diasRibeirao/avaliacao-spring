package br.com.avaliacao.spring.domain.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Boolean getVencida() {
		return vencida;
	}

	public void setVencida(Boolean vencida) {
		this.vencida = vencida;
	}

}
