package br.com.avaliacao.spring.domain.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.avaliacao.spring.domain.enums.SituacaoTransacao;
import lombok.Data;

@Data
public class TransacaoDTO {

	private Long id;

	private Long faturaId;

	private String descricao;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
	private Date data;

	private BigDecimal valor;

	@JsonIgnore
	private Integer situacaoId;

	public TransacaoDTO() {

	}

	public TransacaoDTO(Long id, Long faturaId, String descricao, Date data, BigDecimal valor, Integer situacaoId) {
		this.id = id;
		this.faturaId = faturaId;
		this.descricao = descricao;
		this.data = data;
		this.valor = valor;
		this.situacaoId = situacaoId;
	}


	public String getSituacao() {
		return SituacaoTransacao.toEnum(this.situacaoId).getDescricao();
	}
}
