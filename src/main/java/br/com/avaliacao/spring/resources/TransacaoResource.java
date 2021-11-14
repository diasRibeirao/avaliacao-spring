package br.com.avaliacao.spring.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.avaliacao.spring.domain.dto.TransacaoDTO;
import br.com.avaliacao.spring.domain.dto.converter.TransacaoConverter;
import br.com.avaliacao.spring.services.TransacaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(value = "/v1/transacoes")
@Tag(name = "Transacoes")
public class TransacaoResource {

	@Autowired
	private TransacaoService transacaoService;

	@Autowired
	private TransacaoConverter converter;

	@Operation(summary = "Listar todos as transações", tags = { "Transações" })
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<TransacaoDTO>> findAll() {
		List<TransacaoDTO> list = converter.Parse(transacaoService.findAll());
		return ResponseEntity.ok().body(list);
	}

	@Operation(summary = "Buscar a transação pelo id", tags = { "Transações" })
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<TransacaoDTO> find(@PathVariable Long id) {
		TransacaoDTO obj = converter.Parse(transacaoService.find(id));
		return ResponseEntity.ok().body(obj);
	}

	@Operation(summary = "Buscar transações pelo id da fatura", tags = { "Transações" })
	@RequestMapping(value = "/faturas/{faturaId}", method = RequestMethod.GET)
	public ResponseEntity<List<TransacaoDTO>> findByFaturaId(@PathVariable Long faturaId) {
		List<TransacaoDTO> list = converter.Parse(transacaoService.findByFaturaId(faturaId));
		return ResponseEntity.ok().body(list);
	}
	
	@Operation(summary = "Cancelar uma transação")
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<String> cancelar(@PathVariable Long id) {
		transacaoService.cancelar(id);
		return ResponseEntity.ok().body("Transação cancelada com sucesso.");
	}

}
