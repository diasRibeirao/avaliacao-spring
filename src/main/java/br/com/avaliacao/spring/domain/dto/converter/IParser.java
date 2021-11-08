package br.com.avaliacao.spring.domain.dto.converter;

import java.util.List;

public interface IParser<O, D> {

	D Parse(O origin);

	List<D> Parse(List<O> origin);

}
