package br.com.avaliacao.spring.domain.dto;

import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.avaliacao.spring.services.validation.FaturaAdicionar;
import lombok.Data;

@FaturaAdicionar
@Data
public class FaturaAdicionarDTO {

	@NotNull(message = "Preenchimento obrigatório!")
	private Long cartaoCreditoId;

	@NotNull(message = "Preenchimento obrigatório!")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private Date dataFechamento;

	@NotNull(message = "Preenchimento obrigatório!")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private Date dataVencimento;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
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


}
