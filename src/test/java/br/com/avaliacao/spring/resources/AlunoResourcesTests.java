package br.com.avaliacao.spring.resources;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest
@AutoConfigureMockMvc
class AlunoResourcesTests {

	@Autowired
    private MockMvc mvc;
	
	@Test
    void testSecurity() throws Exception {
		mvc.perform(get("/v1/alunos")).andExpect(status().is(HttpStatus.UNAUTHORIZED.value()));
    }
	
	@Test
	@WithMockUser(username = "fiap", password = "asd123qwe!",  authorities = { "ROLE_USER" })
	void listarAlunos() throws Exception {
		System.out.println("teste");
		mvc.perform(MockMvcRequestBuilders.get("/v1/alunos")
					.contentType(MediaType.APPLICATION_JSON)
				)
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[0].nome", is("AARON FELIPE GRASSMANN")));
	}

	@Test
	@WithMockUser(username = "fiap", password = "asd123qwe!",  authorities = { "ROLE_USER" })
	void addAluno() throws Exception {
		System.out.println("teste");
		mvc.perform(MockMvcRequestBuilders.post("/v1/aluno")
					.contentType(MediaType.APPLICATION_JSON)
					.content("{\"matricula\":\"9998883 160-26\",\"nome\":\"Emerson Dias de Oliveira\"}")
				)
		        .andExpect(status().isOk());
	}
	
	@Test
	@WithMockUser(username = "fiap", password = "asd123qwe!",  authorities = { "ROLE_USER" })
	void findAlunoById() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/v1/aluno/2")
					.contentType(MediaType.APPLICATION_JSON)
				)
		        .andExpect(status().isOk())
				.andExpect(jsonPath("$.matricula", is("8610833 160-26")))
				.andExpect(jsonPath("$.nome", is("AARON PAPA DE MORAIS")));
	}
	
	
	
	
}
