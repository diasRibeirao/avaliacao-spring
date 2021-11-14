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
import br.com.avaliacao.spring.domain.dto.CartaoCreditoAtualizarDTO;
import br.com.avaliacao.spring.domain.dto.CartaoCreditoDTO;
import br.com.avaliacao.spring.domain.dto.TransacaoCartaoCreditoDTO;
import br.com.avaliacao.spring.domain.dto.TransacaoDTO;
import br.com.avaliacao.spring.domain.dto.converter.CartaoCreditoConverter;
import br.com.avaliacao.spring.domain.dto.converter.TransacaoConverter;
import br.com.avaliacao.spring.services.CartaoCreditoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(value = "/v1/cartoes")
@Tag(name = "Cartoes")
public class CartaoCreditoResource {

	@Autowired
	private CartaoCreditoService cartaoCreditoService;

	@Autowired
	private CartaoCreditoConverter converter;
	
	@Autowired
	private TransacaoConverter transacaoConverter;

	@Operation(summary = "Listar todos as cartões de crédito")
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<CartaoCreditoDTO>> findAll() {
		List<CartaoCreditoDTO> list = converter.Parse(cartaoCreditoService.findAll());
		return ResponseEntity.ok().body(list);
	}

	@Operation(summary = "Buscar o cartão de crédito pelo id")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<CartaoCreditoDTO> find(@PathVariable Long id) {
		CartaoCreditoDTO obj = converter.Parse(cartaoCreditoService.find(id));
		return ResponseEntity.ok().body(obj);
	}

	@Operation(summary = "Buscar o cartão de crédito pelo nome")
	@RequestMapping(value = "/nome/{nome}", method = RequestMethod.GET)
	public ResponseEntity<List<CartaoCreditoDTO>> findByNome(@PathVariable String nome) {
		List<CartaoCreditoDTO> list = converter.Parse(cartaoCreditoService.findByNome(nome));
		return ResponseEntity.ok().body(list);
	}

	@Operation(summary = "Buscar o cartão de crédito pelo número")
	@RequestMapping(value = "/numero/{numero}", method = RequestMethod.GET)
	public ResponseEntity<CartaoCreditoDTO> findByNumero(@PathVariable String numero) {
		CartaoCreditoDTO obj = converter.Parse(cartaoCreditoService.findByNumero(numero));
		return ResponseEntity.ok().body(obj);
	}

	@Operation(summary = "Buscar cartões de crédito pelo id do aluno")
	@RequestMapping(value = "/alunos/{alunoId}", method = RequestMethod.GET)
	public ResponseEntity<List<CartaoCreditoDTO>> findByAlunoId(@PathVariable Long alunoId) {
		List<CartaoCreditoDTO> list = converter.Parse(cartaoCreditoService.findByAlunoId(alunoId));
		return ResponseEntity.ok().body(list);
	}

	@Operation(summary = "Adicionar um cartão de crédito")
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody CartaoCreditoAdicionarDTO cartaoCreditoAdicionarDTO) {
		CartaoCredito cartaoCredito = converter.ParseAdicionarDTO(cartaoCreditoAdicionarDTO);
		cartaoCredito = cartaoCreditoService.insert(cartaoCredito);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(cartaoCredito.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@Operation(summary = "Realizar uma transação pelo cartão de crédito")
	@RequestMapping(value = "/transacao", method = RequestMethod.POST)
	public ResponseEntity<TransacaoDTO> transacao(@Valid @RequestBody TransacaoCartaoCreditoDTO transacaoCartaoCreditoDTO) {		
		TransacaoDTO obj = transacaoConverter.Parse(cartaoCreditoService.transacao(transacaoCartaoCreditoDTO));
		return ResponseEntity.ok().body(obj);
	}

	@Operation(summary = "Atualizar um cartão de crédito")
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody CartaoCreditoAtualizarDTO objDto, @PathVariable Long id) {
		CartaoCredito obj = converter.ParseAtualizarDTO(objDto);
		obj.setId(id);
		obj = cartaoCreditoService.update(obj);
		return ResponseEntity.noContent().build();
	}

	@Operation(summary = "Deletar um cartão de crédito")
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		cartaoCreditoService.delete(id);
		return ResponseEntity.noContent().build();
	}

}
