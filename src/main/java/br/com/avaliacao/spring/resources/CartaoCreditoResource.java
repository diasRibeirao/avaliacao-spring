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

import br.com.avaliacao.spring.domain.CartaoCredito;
import br.com.avaliacao.spring.domain.dto.CartaoCreditoAdicionarDTO;
import br.com.avaliacao.spring.domain.dto.CartaoCreditoDTO;
import br.com.avaliacao.spring.domain.dto.converter.CartaoCreditoConverter;
import br.com.avaliacao.spring.services.CartaoCreditoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value = "Cartões de Crédito", description = "APIs Cartões de Crédito", tags = { "Cartões de Crédito" })
@RequestMapping(value = "/v1/cartoes")
public class CartaoCreditoResource {

	@Autowired
	private CartaoCreditoService cartaoCreditoService;

	@Autowired
	private CartaoCreditoConverter converter;

	@ApiOperation(value = "Listar todos as cartões de crédito", tags = { "Cartões de Crédito" })
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<CartaoCreditoDTO>> findAll() {
		List<CartaoCreditoDTO> list = converter.Parse(cartaoCreditoService.findAll());
		return ResponseEntity.ok().body(list);
	}

	@ApiOperation(value = "Buscar o cartão de crédito pelo id", tags = { "Cartões de Crédito" })
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<CartaoCreditoDTO> find(@PathVariable Long id) {
		CartaoCreditoDTO obj = converter.Parse(cartaoCreditoService.find(id));
		return ResponseEntity.ok().body(obj);
	}
	
	@ApiOperation(value = "Buscar o cartão de crédito pelo nome", tags = { "Cartões de Crédito" })
	@RequestMapping(value = "/nome/{nome}", method = RequestMethod.GET)
	public ResponseEntity<List<CartaoCreditoDTO>> findByNome(@PathVariable String nome) {
		List<CartaoCreditoDTO> list = converter.Parse(cartaoCreditoService.findByNome(nome));
		return ResponseEntity.ok().body(list);
	}
	
	@ApiOperation(value = "Buscar o cartão de crédito pelo número", tags = { "Cartões de Crédito" })
	@RequestMapping(value = "/numero/{numero}", method = RequestMethod.GET)
	public ResponseEntity<CartaoCreditoDTO> findByNumero(@PathVariable String numero) {
		CartaoCreditoDTO obj = converter.Parse(cartaoCreditoService.findByNumero(numero));
		return ResponseEntity.ok().body(obj);
	}

	@ApiOperation(value = "Adicionar um cartão de crédito", tags = { "Cartões de Crédito" })
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody CartaoCreditoAdicionarDTO cartaoCreditoAdicionarDTO) {
		CartaoCredito cartaoCredito = converter.ParseAdicionarDTO(cartaoCreditoAdicionarDTO);
		cartaoCredito = cartaoCreditoService.insert(cartaoCredito);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(cartaoCredito.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@ApiOperation(value = "Atualizar um cartão de crédito", tags = { "Cartões de Crédito"})
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody CartaoCreditoDTO objDto, @PathVariable Long id) {
		CartaoCredito obj = converter.ParseDTO(objDto);
		obj.setId(id); 
		obj = cartaoCreditoService.update(obj);
		return ResponseEntity.noContent().build();
	}	

	@ApiOperation(value = "Deletar um cartão de crédito", tags = { "Cartões de Crédito"})
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		cartaoCreditoService.delete(id);
		return ResponseEntity.noContent().build();
	}

}
