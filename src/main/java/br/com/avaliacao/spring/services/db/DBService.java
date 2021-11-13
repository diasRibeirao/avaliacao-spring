package br.com.avaliacao.spring.services.db;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.time.YearMonth;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import br.com.avaliacao.spring.domain.Aluno;
import br.com.avaliacao.spring.domain.CartaoCredito;
import br.com.avaliacao.spring.domain.Fatura;
import br.com.avaliacao.spring.domain.Transacao;
import br.com.avaliacao.spring.domain.enums.SituacaoTransacao;
import br.com.avaliacao.spring.repositories.AlunoRepository;
import br.com.avaliacao.spring.repositories.CartaoCreditoRepository;
import br.com.avaliacao.spring.repositories.FaturaRepository;
import br.com.avaliacao.spring.repositories.TransacaoRepository;

@Service
public class DBService {

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

	public void instantiateTestDatabase() throws Exception {
		List<Aluno> alunos = getListAlunosFromFile();
		alunoRepository.saveAll(alunos);
		
		List<CartaoCredito> cartoes = getListCartoesCredito(alunos);
		cartaoCreditoRepository.saveAll(cartoes);
		
		List<Fatura> faturas = getListFaturas(cartoes);
		faturaRepository.saveAll(faturas);

		List<Transacao> transacoes = getListTransacoes(faturas);
		transacaoRepository.saveAll(transacoes);
	}

	private List<Aluno> getListAlunosFromFile() throws Exception {
		List<Aluno> retorno = new ArrayList<Aluno>();

		try (BufferedReader br = new BufferedReader(new InputStreamReader(this.resourceFile.getInputStream()))) {
			String linha;
			while ((linha = br.readLine()) != null) {
				if (linha.startsWith("---------------------------") || linha.length() < 5) {
					continue;
				}
				retorno.add(new Aluno(linha.substring(41, 55).trim(), linha.substring(0, 41).trim()));
			}
		}

		return retorno;
	}
	
	private List<CartaoCredito> getListCartoesCredito(List<Aluno> alunos) throws Exception {
		List<CartaoCredito> retorno = new ArrayList<CartaoCredito>();
		
		for (int i = 0; i < 220; i++) {			
			Aluno aluno = alunos.get(ThreadLocalRandom.current().nextInt(1, 1000));
			
			String numero = "";
			for (int j = 0; j < 4; j++) {
				numero += String.format("%04d", ThreadLocalRandom.current().nextInt(10000));
			}
						
			retorno.add(new CartaoCredito(
					aluno, 
					numero, 
					aluno.getNome(), 
					getVencimento(), 
					new BigDecimal(ThreadLocalRandom.current().nextFloat() * 10000),
					 String.format("%03d", ThreadLocalRandom.current().nextInt(1000)), 
					 ThreadLocalRandom.current().nextInt(10, 15),
					 true
			));
		}
		
		return retorno;
	}
	
	private List<Fatura> getListFaturas(List<CartaoCredito> cartoes) throws Exception {
		List<Fatura> retorno = new ArrayList<Fatura>();
		
		for (int i = 0; i < 10; i++) {		
			CartaoCredito cartaoCredito = cartoes.get(ThreadLocalRandom.current().nextInt(1, 220));
			retorno.add(new Fatura(
					cartaoCredito, 
					getData(ThreadLocalRandom.current().nextInt(10, 15)), 
					getData(ThreadLocalRandom.current().nextInt(20, 30)), 
					null, 
					null));
		}		
		
		return retorno;
	}
	
	private List<Transacao> getListTransacoes(List<Fatura> faturas) throws Exception {
		List<Transacao> retorno = new ArrayList<Transacao>();
		
		String[] descricoes = { 
				"Ebanx*Airbnb", "Lj Clovis Calçados", "Netflix.Com", "Amazon Prime", "Submarino", "Americanas", "Posto Combustível 2 Irmãos", 
				"Localiza Rentcar", "Pronto Frio Carapicúiba", "Padaria Pão de Mel", "Big Hipermercado", "Extra Osasco", "B2 Magazine", 
				"Tudo Aqui Produtos Nat.", "Churrasbom Lapa", "Mundial Calçados", "Riachuelo*União Osasc.", "Restaurante Chão de Minas", "Supermecardo Eudes", "Uber*Trip" 
			};
		
		for (int i = 0; i < 20; i++) {		
			Fatura fatura = faturas.get(ThreadLocalRandom.current().nextInt(1, 10));
			
			int limite = ThreadLocalRandom.current().nextInt(1, 4);
			for (int j = 0; j < limite; j++) {		
				retorno.add(new Transacao(
						fatura, 
						descricoes[ThreadLocalRandom.current().nextInt(0, 20)], 
						getData(ThreadLocalRandom.current().nextInt(1, 9)), 
						new BigDecimal(ThreadLocalRandom.current().nextFloat() * 100),
						SituacaoTransacao.ATIVA));
			}
		}
		
		return retorno;
	}
	
	private YearMonth getVencimento() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.MILLISECOND, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.HOUR, 0);
        
        calendar.set(Calendar.AM_PM, Calendar.AM);
        
        calendar.set(Calendar.DAY_OF_MONTH, ThreadLocalRandom.current().nextInt(1, 30));
        calendar.set(Calendar.MONTH, ThreadLocalRandom.current().nextInt(0, 11));
        calendar.set(Calendar.YEAR, ThreadLocalRandom.current().nextInt(2025, 2035));
        		
		return YearMonth.from(calendar.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
		 
	}
	
	private Date getData(int dia) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.MILLISECOND, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.HOUR, 0);
        
        calendar.set(Calendar.AM_PM, Calendar.AM);
        
        calendar.set(Calendar.DAY_OF_MONTH, dia);
		
		return calendar.getTime();
	}
}
