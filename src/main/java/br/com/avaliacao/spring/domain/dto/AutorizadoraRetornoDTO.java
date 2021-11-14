package br.com.avaliacao.spring.domain.dto;

import lombok.Data;

@Data
public class AutorizadoraRetornoDTO {

	private Long cartaoCreditoId;

	private Long alunoId;
	
	public AutorizadoraRetornoDTO() {
		
	}

	public AutorizadoraRetornoDTO(Long cartaoCreditoId, Long alunoId) {
		super();
		this.cartaoCreditoId = cartaoCreditoId;
		this.alunoId = alunoId;
	}

}
