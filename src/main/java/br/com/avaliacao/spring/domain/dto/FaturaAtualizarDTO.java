package br.com.avaliacao.spring.domain.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.avaliacao.spring.services.validation.FaturaAtualizar;
import lombok.Data;

@FaturaAtualizar
@Data
public class FaturaAtualizarDTO {

	private Long cartaoCreditoId;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
	private Date dataFechamento;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
	private Date dataVencimento;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
	private Date dataPagamento;

	private BigDecimal valorPago;

	public FaturaAtualizarDTO() {

	}

	public FaturaAtualizarDTO(Long cartaoCreditoId, Date dataFechamento, Date dataVencimento, Date dataPagamento, BigDecimal valorPago) {
		this.cartaoCreditoId = cartaoCreditoId;
		this.dataFechamento = dataFechamento;
		this.dataVencimento = dataVencimento;
		this.dataPagamento = dataPagamento;
		this.valorPago = valorPago;
	}

}
