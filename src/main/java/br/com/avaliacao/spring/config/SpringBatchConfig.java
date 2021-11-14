package br.com.avaliacao.spring.config;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.Resource;

import br.com.avaliacao.spring.domain.Aluno;
import br.com.avaliacao.spring.domain.CartaoCredito;
import br.com.avaliacao.spring.domain.Fatura;
import br.com.avaliacao.spring.domain.Transacao;
import br.com.avaliacao.spring.domain.enums.SituacaoTransacao;
import br.com.avaliacao.spring.repositories.AlunoRepository;
import br.com.avaliacao.spring.repositories.CartaoCreditoRepository;
import br.com.avaliacao.spring.repositories.FaturaRepository;
import br.com.avaliacao.spring.repositories.TransacaoRepository;
import br.com.avaliacao.spring.utils.Utils;

@Configuration
@EnableBatchProcessing
@Profile("test")
public class SpringBatchConfig {

	@Autowired
	private AlunoRepository alunoRepository;

	@Autowired
	private CartaoCreditoRepository cartaoCreditoRepository;

	@Autowired
	private FaturaRepository faturaRepository;

	@Autowired
	private TransacaoRepository transacaoRepository;

	@Value("classpath:data/lista_alunos.txt")
	private Resource resourceFile;

	@Bean
	public void init() throws Exception {
		// Cadastro inicial dos potenciais clientes
		List<Aluno> alunos = buildAlunos();
		alunoRepository.saveAll(alunos);
		
		Aluno emerson = new Aluno("9999999 999-99", "Emerson Dias de Oliveira");
		Aluno marco = new Aluno("8888888 888-88", "Marco Santos");
		alunoRepository.saveAll(Arrays.asList(emerson, marco));
		
		// Cadastro inicial de cartões de crédito
		CartaoCredito cartaoEmerson = buildCartaoCredito(emerson, "1234567891234567", new BigDecimal(10000), "123");
		CartaoCredito cartaoMarco = buildCartaoCredito(marco, "2234567891234568", new BigDecimal(12000), "321");
		cartaoCreditoRepository.saveAll(Arrays.asList(cartaoEmerson, cartaoMarco));
		
		// Cadastro inicial de fatura
		Fatura faturaEmerson = buildCartaoCredito(cartaoEmerson, Utils.dataFechamentoCartaoCredito(cartaoEmerson.getMelhorDiaCompra()));
		Fatura faturaMarco = buildCartaoCredito(cartaoMarco, Utils.dataFechamentoCartaoCredito(cartaoMarco.getMelhorDiaCompra()));
		faturaRepository.saveAll(Arrays.asList(faturaEmerson, faturaMarco));

		// Cadastro inicial de transações
		Transacao tEmerson1 = buildTransacao(faturaEmerson, "Ebanx*Airbnb", new BigDecimal(98));
		Transacao tEmerson2 = buildTransacao(faturaEmerson, "Mundial Calçados", new BigDecimal(24.08));
		Transacao tEmerson3 = buildTransacao(faturaEmerson, "Riachuelo*União Osasc.", new BigDecimal(157.7));
		Transacao tMarco1 = buildTransacao(faturaMarco, "Submarino.com", new BigDecimal(278.89));
		Transacao tMarco2 = buildTransacao(faturaMarco, "Amazon Prime", new BigDecimal(10));
		transacaoRepository.saveAll(Arrays.asList(tEmerson1, tEmerson2, tEmerson3, tMarco1, tMarco2));
	}
	
	private List<Aluno> buildAlunos() throws IOException {
		List<Aluno> alunos = new ArrayList<Aluno>();

		try (BufferedReader br = new BufferedReader(new InputStreamReader(this.resourceFile.getInputStream()))) {
			String linha;
			while ((linha = br.readLine()) != null) {
				if (linha.startsWith("---------------------------") || linha.length() < 5) {
					continue;
				}
				alunos.add(new Aluno(linha.substring(41, 55).trim(), linha.substring(0, 41).trim()));
			}
		}
		
		return alunos;
	}
	
	private CartaoCredito buildCartaoCredito(Aluno aluno, String numero, BigDecimal limite, String codigoSeguranca) {
		return new CartaoCredito(aluno, numero, aluno.getNome(), YearMonth.of(2023, 2), limite,
				codigoSeguranca, 10, Boolean.TRUE);
	}
	
	private Fatura buildCartaoCredito(CartaoCredito cartaoCredito, Date dataFechamento) {
		return new Fatura(cartaoCredito, dataFechamento, Utils.dataVencimento(dataFechamento), null, null);
	}

	private Transacao buildTransacao(Fatura fatura, String descricao, BigDecimal valor) {
		return new Transacao(fatura, descricao, new Date(), valor, SituacaoTransacao.ATIVA);
	}
	
}
