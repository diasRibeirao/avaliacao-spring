package br.com.avaliacao.spring.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import br.com.avaliacao.spring.domain.dto.converter.AlunoConverter;
import br.com.avaliacao.spring.services.AlunoService;

@SpringBootTest
@AutoConfigureMockMvc
public class AlunoResourcesTests {

	@Autowired
    private MockMvc mockMvc;
	
	@Autowired
	private AlunoService alunoService;

	@Autowired
	private AlunoConverter converter;
	
	
	
	
}
