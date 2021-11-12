package br.com.avaliacao.spring.services.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.avaliacao.spring.domain.Fatura;
import br.com.avaliacao.spring.domain.dto.TransacaoAtualizarDTO;
import br.com.avaliacao.spring.repositories.FaturaRepository;
import br.com.avaliacao.spring.resources.exceptions.FieldMessage;

public class TransacaoAtualizarValidator implements ConstraintValidator<TransacaoAtualizar, TransacaoAtualizarDTO> {

	@Autowired
	private FaturaRepository faturaRepository;

	@Override
	public void initialize(TransacaoAtualizar ann) {
	}

	@Override
	public boolean isValid(TransacaoAtualizarDTO transacaoAtualizarDTO, ConstraintValidatorContext context) {

		List<FieldMessage> list = new ArrayList<>();

		Optional<Fatura> fatura = faturaRepository.findById(transacaoAtualizarDTO.getFaturaId());		
		if (fatura.isEmpty()) {
			list.add(new FieldMessage("faturaId", "Objeto não encontrado! Id: " + transacaoAtualizarDTO.getFaturaId() + ", Tipo: " + Fatura.class.getName()));
		} else {
			// verificar aqui se possui limite
			// fatura valor total + transacao valor <= cartão de credito limite
		}
		 

		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName()).addConstraintViolation();
		}

		return list.isEmpty();
	}
}
