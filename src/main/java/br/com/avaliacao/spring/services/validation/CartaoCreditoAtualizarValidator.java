package br.com.avaliacao.spring.services.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import br.com.avaliacao.spring.domain.Aluno;
import br.com.avaliacao.spring.domain.CartaoCredito;
import br.com.avaliacao.spring.domain.dto.CartaoCreditoAtualizarDTO;
import br.com.avaliacao.spring.repositories.AlunoRepository;
import br.com.avaliacao.spring.repositories.CartaoCreditoRepository;
import br.com.avaliacao.spring.resources.exceptions.FieldMessage;

public class CartaoCreditoAtualizarValidator implements ConstraintValidator<CartaoCreditoAtualizar, CartaoCreditoAtualizarDTO> {

	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private AlunoRepository alunoRepository;
	
	@Autowired
	private CartaoCreditoRepository cartaoCreditoRepository;

	@Override
	public void initialize(CartaoCreditoAtualizar ann) {
	}

	@Override
	public boolean isValid(CartaoCreditoAtualizarDTO cartaoCreditoAtualizarDTO, ConstraintValidatorContext context) {

		List<FieldMessage> list = new ArrayList<>();
		
		@SuppressWarnings("unchecked")
		Map<String, String> map = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		Long uriId = Long.parseLong(map.get("id"));
		
		if (cartaoCreditoAtualizarDTO.getAlunoId() != null) {
			Optional<Aluno> aluno = alunoRepository.findById(cartaoCreditoAtualizarDTO.getAlunoId());
			if (aluno.isEmpty()) {
				list.add(new FieldMessage("alunoId", "Objeto não encontrado! Id: " + cartaoCreditoAtualizarDTO.getAlunoId() + ", Tipo: " + Aluno.class.getName()));
			}
		}
		
		if (!StringUtils.isBlank(cartaoCreditoAtualizarDTO.getNumero())) {
			Optional<CartaoCredito> aux = cartaoCreditoRepository.findByNumero(cartaoCreditoAtualizarDTO.getNumero());
			if (aux.isPresent() && !aux.get().getId().equals(uriId)) {
				list.add(new FieldMessage("numero", "Número já existente"));
			}		 
		}
		
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName()).addConstraintViolation();
		}

		return list.isEmpty();
	}
}
