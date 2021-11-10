package br.com.avaliacao.spring.resources;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.avaliacao.spring.domain.Transacao;
import br.com.avaliacao.spring.domain.dto.TransacaoAdicionarDTO;
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


	@ApiOperation(value = "Adicionar uma transação", tags = { "Transações" })
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody TransacaoAdicionarDTO transacaoAdicionarDTO) {
		Transacao transacao = converter.ParseAdicionarDTO(transacaoAdicionarDTO);
		transacao = transacaoService.insert(transacao);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(transacao.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@ApiOperation(value = "Atualizar um aluno", tags = { "Transações"})
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody TransacaoDTO objDto, @PathVariable Long id) {
		Transacao obj = converter.ParseDTO(objDto);
		obj.setId(id); 
		obj = transacaoService.update(obj);
		return ResponseEntity.noContent().build();
	}	

	@ApiOperation(value = "Deletar uma transação", tags = { "Transações"})
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		transacaoService.delete(id);
		return ResponseEntity.noContent().build();
	}

}
