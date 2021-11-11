package br.com.avaliacao.spring.domain.dto;

import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.avaliacao.spring.services.validation.FaturaAdicionar;

@FaturaAdicionar
public class FaturaAdicionarDTO {

	@NotNull(message = "Preenchimento obrigatório!")
	private Long cartaoCreditoId;

	@NotNull(message = "Preenchimento obrigatório!")
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private Date dataFechamento;

	@NotNull(message = "Preenchimento obrigatório!")
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private Date dataVencimento;

	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private Date dataPagamento;

	private BigDecimal valorPago;

	public FaturaAdicionarDTO() {

	}

	public FaturaAdicionarDTO(Long cartaoCreditoId, Date dataFechamento, Date dataVencimento, Date dataPagamento, BigDecimal valorPago) {
		this.cartaoCreditoId = cartaoCreditoId;
		this.dataFechamento = dataFechamento;
		this.dataVencimento = dataVencimento;
		this.dataPagamento = dataPagamento;
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

	public BigDecimal getValorPago() {
		return valorPago;
	}

	public void setValorPago(BigDecimal valorPago) {
		this.valorPago = valorPago;
	}

}
