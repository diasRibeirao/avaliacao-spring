package br.com.avaliacao.spring.services.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import br.com.avaliacao.spring.domain.CartaoCredito;
import br.com.avaliacao.spring.domain.dto.CartaoCreditoAdicionarDTO;
import br.com.avaliacao.spring.repositories.CartaoCreditoRepository;
import br.com.avaliacao.spring.resources.exceptions.FieldMessage;

public class CartaoCreditoAtualizarValidator implements ConstraintValidator<CartaoCreditoAdicionar, CartaoCreditoAdicionarDTO> {

	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private CartaoCreditoRepository cartaoCreditoRepository;

	@Override
	public void initialize(CartaoCreditoAdicionar ann) {
	}

	@Override
	public boolean isValid(CartaoCreditoAdicionarDTO cartaoCreditoAdicionarDTO, ConstraintValidatorContext context) {

		List<FieldMessage> list = new ArrayList<>();
		
		@SuppressWarnings("unchecked")
		Map<String, String> map = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		Long uriId = Long.parseLong(map.get("id"));
		
		Optional<CartaoCredito> aux = cartaoCreditoRepository.findByNumero(cartaoCreditoAdicionarDTO.getNumero());
		if (aux.isEmpty() && !aux.get().getId().equals(uriId)) {
			list.add(new FieldMessage("numero", "Número já existente"));
		}		 

		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName()).addConstraintViolation();
		}

		return list.isEmpty();
	}
}