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
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value = "Transações", description = "APIs Transações", tags = { "Transações" })
@RequestMapping(value = "/v1/transacoes")
public class TransacaoResource {

	@Autowired
	private TransacaoService transacaoService;

	@Autowired
	private TransacaoConverter converter;

	@ApiOperation(value = "Listar todos as transações", tags = { "Transações" })
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<TransacaoDTO>> findAll() {
		List<TransacaoDTO> list = converter.Parse(transacaoService.findAll());
		return ResponseEntity.ok().body(list);
	}

	@ApiOperation(value = "Buscar a transação pelo id", tags = { "Transações" })
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<TransacaoDTO> find(@PathVariable Long id) {
		TransacaoDTO obj = converter.Parse(transacaoService.find(id));
		return ResponseEntity.ok().body(obj);
	}

	@ApiOperation(value = "Buscar transações pelo id da fatura", tags = { "Transações" })
	@RequestMapping(value = "/faturas/{faturaId}", method = RequestMethod.GET)
	public ResponseEntity<List<TransacaoDTO>> findByFaturaId(@PathVariable Long faturaId) {
		List<TransacaoDTO> list = converter.Parse(transacaoService.findByFaturaId(faturaId));
		return ResponseEntity.ok().body(list);
	}
	
	@ApiOperation(value = "Cancelar uma transação", tags = { "Transações"})
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<String> cancelar(@PathVariable Long id) {
		transacaoService.cancelar(id);
		return ResponseEntity.ok().body("Transação cancelada com sucesso.");
	}

}
