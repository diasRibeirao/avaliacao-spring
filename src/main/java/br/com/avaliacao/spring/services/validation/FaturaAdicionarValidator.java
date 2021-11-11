package br.com.avaliacao.spring.services.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.avaliacao.spring.domain.CartaoCredito;
import br.com.avaliacao.spring.domain.dto.FaturaAdicionarDTO;
import br.com.avaliacao.spring.repositories.CartaoCreditoRepository;
import br.com.avaliacao.spring.resources.exceptions.FieldMessage;

public class FaturaAdicionarValidator implements ConstraintValidator<FaturaAdicionar, FaturaAdicionarDTO> {

	@Autowired
	private CartaoCreditoRepository cartaoCreditoRepository;
	
	@Override
	public void initialize(FaturaAdicionar ann) {
	}

	@Override
	public boolean isValid(FaturaAdicionarDTO faturaAdicionarDTO, ConstraintValidatorContext context) {

		List<FieldMessage> list = new ArrayList<>();
		
		Optional<CartaoCredito> cartaoCredito = cartaoCreditoRepository.findById(faturaAdicionarDTO.getCartaoCreditoId());		
		if (cartaoCredito.isEmpty()) {
			list.add(new FieldMessage("cartaoCreditoId", "Objeto não encontrado! Id: " + faturaAdicionarDTO.getCartaoCreditoId() + ", Tipo: " + CartaoCredito.class.getName()));
		} 

		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName()).addConstraintViolation();
		}

		return list.isEmpty();
	}
}
