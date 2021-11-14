package br.com.avaliacao.spring.domain.dto;

import java.math.BigDecimal;
import java.time.YearMonth;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.avaliacao.spring.services.validation.CartaoCreditoAdicionar;
import lombok.Data;

@CartaoCreditoAdicionar
@Data
public class CartaoCreditoAdicionarDTO {

	@NotNull(message = "Preenchimento obrigatório!")
	private Long alunoId;

	@Size(min = 16, max = 16, message = "O número deve possuir {max} caracteres!")
	private String numero;

	@Size(min = 2, max = 120, message = "O nome deve possuir entre {min} e {max} caracteres!")
	private String nome;

	@NotNull(message = "Preenchimento obrigatório!")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM/yyyy")
	private YearMonth vencimento;

	@NotNull(message = "Preenchimento obrigatório!")
	private BigDecimal limite;

	@Size(min = 3, max = 3, message = "O código de segurança deve possuir {max} caracteres!")
	private String codigoSeguranca;
	
	@NotNull(message = "Preenchimento obrigatório!")
	private Integer melhorDiaCompra;

	@NotNull(message = "Preenchimento obrigatório!")
	private Boolean ativo;

	public CartaoCreditoAdicionarDTO() {

	}

	public CartaoCreditoAdicionarDTO(Long alunoId, String numero, String nome, YearMonth vencimento, BigDecimal limite,
			String codigoSeguranca, Integer melhorDiaCompra, Boolean ativo) {
		this.alunoId = alunoId;
		this.numero = numero;
		this.nome = nome;
		this.vencimento = vencimento;
		this.limite = limite;
		this.codigoSeguranca = codigoSeguranca;
		this.melhorDiaCompra = melhorDiaCompra;
		this.ativo = ativo;
	}

	
	public Boolean isAtivo() {
		return ativo;
	}

}
