package br.com.avaliacao.spring.domain.dto;

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

	public Long getCartaoCreditoId() {
		return cartaoCreditoId;
	}

	public void setCartaoCreditoId(Long cartaoCreditoId) {
		this.cartaoCreditoId = cartaoCreditoId;
	}

	public Long getAlunoId() {
		return alunoId;
	}

	public void setAlunoId(Long alunoId) {
		this.alunoId = alunoId;
	}

}
