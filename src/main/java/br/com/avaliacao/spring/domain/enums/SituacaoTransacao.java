package br.com.avaliacao.spring.domain.enums;

public enum SituacaoTransacao {

	ATIVA(1, "Ativa"), CANCELADA(2, "Cancelada");

	private Integer cod;
	private String descricao;

	private SituacaoTransacao(Integer cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}

	public Integer getCod() {
		return cod;
	}

	public String getDescricao() {
		return descricao;
	}

	public static SituacaoTransacao toEnum(Integer cod) {
		if (cod == null) {
			return null;
		}

		for (SituacaoTransacao situacao : SituacaoTransacao.values()) {
			if (cod.equals(situacao.getCod())) {
				return situacao;
			}
		}

		throw new IllegalArgumentException("Id inválido: " + cod);
	}
}