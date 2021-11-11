package br.com.avaliacao.spring.domain.dto.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import br.com.avaliacao.spring.domain.CartaoCredito;
import br.com.avaliacao.spring.domain.Fatura;
import br.com.avaliacao.spring.domain.dto.FaturaAdicionarDTO;
import br.com.avaliacao.spring.domain.dto.FaturaDTO;

@Service
public class FaturaConverter {

	public Fatura ParseDTO(FaturaDTO origin) {
		if (origin == null)
			return null;

		return new Fatura(new CartaoCredito(origin.getCartaoCreditoId()), origin.getDataFechamento(), origin.getDataVencimento(), origin.getDataPagamento(), origin.getValorPago());
	}

	public List<Fatura> ParseDTO(List<FaturaDTO> origin) {
		if (origin == null)
			return null;

		return origin.stream().map(obj -> ParseDTO(obj)).collect(Collectors.toList());
	}
	
	public Fatura ParseAdicionarDTO(FaturaAdicionarDTO origin) {
		if (origin == null)
			return null;

		return new Fatura(new CartaoCredito(origin.getCartaoCreditoId()), origin.getDataFechamento(), origin.getDataVencimento(), origin.getDataPagamento(), origin.getValorPago());
	}

	public List<Fatura> ParseAdicionarDTO(List<FaturaAdicionarDTO> origin) {
		if (origin == null)
			return null;

		return origin.stream().map(obj -> ParseAdicionarDTO(obj)).collect(Collectors.toList());
	}

	public FaturaDTO Parse(Fatura origin) {
		if (origin == null)
			return null;

		return new FaturaDTO(origin.getCartaoCredito().getId(), origin.getDataFechamento(), origin.getDataVencimento(), origin.getDataPagamento(), origin.getValorTotal(), origin.getValorPago());
	}

	public List<FaturaDTO> Parse(List<Fatura> origin) {
		if (origin == null)
			return null;

		return origin.stream().map(obj -> Parse(obj)).collect(Collectors.toList());
	}

}
