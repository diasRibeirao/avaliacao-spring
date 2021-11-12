package br.com.avaliacao.spring.resources;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.avaliacao.spring.domain.dto.AutorizadoraDTO;
import br.com.avaliacao.spring.domain.dto.AutorizadoraRetornoDTO;
import br.com.avaliacao.spring.services.AutorizadoraService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value = "Autorizadora", description = "APIs Autorizadora", tags = { "Autorizadora" })
@RequestMapping(value = "/v1/autorizadora")
public class AutorizadoraResource {
	
	public AutorizadoraResource() {
		System.out.println("AutorizadoraResource");
	}
	
	@Autowired
	private AutorizadoraService autorizadoraService;
	
	@ApiOperation(value = "Autorizar transação", tags = { "Autorizadora" })
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<AutorizadoraRetornoDTO> autorizar(@Valid @RequestBody AutorizadoraDTO dados) {
		AutorizadoraRetornoDTO obj = autorizadoraService.autorizar(dados);
		return ResponseEntity.ok().body(obj);
	}
	
	
}
