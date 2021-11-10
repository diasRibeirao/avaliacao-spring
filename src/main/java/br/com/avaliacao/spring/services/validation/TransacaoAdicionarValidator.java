package br.com.avaliacao.spring.services.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.avaliacao.spring.domain.Fatura;
import br.com.avaliacao.spring.domain.dto.TransacaoAdicionarDTO;
import br.com.avaliacao.spring.repositories.FaturaRepository;
import br.com.avaliacao.spring.resources.exceptions.FieldMessage;

public class TransacaoAdicionarValidator implements ConstraintValidator<TransacaoAdicionar, TransacaoAdicionarDTO> {

	@Autowired
	private FaturaRepository faturaRepository;

	@Override
	public void initialize(TransacaoAdicionar ann) {
	}

	@Override
	public boolean isValid(TransacaoAdicionarDTO transacaoAdicionarDTO, ConstraintValidatorContext context) {

		List<FieldMessage> list = new ArrayList<>();

		Optional<Fatura> fatura = faturaRepository.findById(transacaoAdicionarDTO.getFaturaId());		
		if (fatura.isEmpty()) {
			list.add(new FieldMessage("faturaId", "Objeto não encontrado! Id: " + transacaoAdicionarDTO.getFaturaId() + ", Tipo: " + Fatura.class.getName()));
		}
		 
		// verificar aqui se possui limite

		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName()).addConstraintViolation();
		}

		return list.isEmpty();
	}
}
