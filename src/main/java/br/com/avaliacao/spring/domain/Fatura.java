package br.com.avaliacao.spring.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.avaliacao.spring.domain.enums.SituacaoTransacao;
import br.com.avaliacao.spring.utils.Utils;

@Entity
@Table(name = "FATURA")
public class Fatura implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "CARTAO_CREDITO_ID")
	private CartaoCredito cartaoCredito;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DATA_FECHAMENTO")
	private Date dataFechamento;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DATA_VENCIMENTO")
	private Date dataVencimento;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DATA_PAGAMENTO")
	private Date dataPagamento;

	@Column(name = "VALOR_PAGO", precision = 8, scale = 2)
	private BigDecimal valorPago;

	@JsonIgnore
	@OneToMany(mappedBy = "fatura")
	private List<Transacao> transacoes = new ArrayList<Transacao>();

	public Fatura() {

	}

	public Fatura(Long id) {
		this.id = id;
	}

	public Fatura(Long id, CartaoCredito cartaoCredito, Date dataFechamento, Date dataVencimento, Date dataPagamento,
			BigDecimal valorPago) {
		this(cartaoCredito, dataFechamento, dataVencimento, dataPagamento, valorPago);
		this.id = id;
	}

	public Fatura(CartaoCredito cartaoCredito, Date dataFechamento, Date dataVencimento, Date dataPagamento,
			BigDecimal valorPago) {
		this.cartaoCredito = cartaoCredito;
		this.dataFechamento = dataFechamento;
		this.dataVencimento = dataVencimento;
		this.dataPagamento = dataPagamento;
		this.valorPago = valorPago;
	}

	public Fatura(CartaoCredito cartaoCredito) {
		this.cartaoCredito = cartaoCredito;
		this.dataFechamento = Utils.dataFechamentoCartaoCredito(cartaoCredito.getMelhorDiaCompra());
		this.dataVencimento = Utils.dataVencimento(this.dataFechamento);
		this.dataPagamento = null;
		this.valorPago = null;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public CartaoCredito getCartaoCredito() {
		return cartaoCredito;
	}

	public void setCartaoCredito(CartaoCredito cartaoCredito) {
		this.cartaoCredito = cartaoCredito;
	}

	public Date getDataFechamento() {
		return dataFechamento;
	}

	public void setDataFechamento(Date dataFechamento) {
		this.dataFechamento = dataFechamento;
	}

	public Date getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public BigDecimal getValorTotal() {
		if (this.transacoes.isEmpty()) {
			return new BigDecimal(0);
		}

		BigDecimal valorTotal = new BigDecimal(0);

		for (Transacao transacao : this.transacoes) {
			if (transacao.getSituacao().equals(SituacaoTransacao.ATIVA.getCod())) {
				valorTotal = valorTotal.add(transacao.getValor());
			}
		}

		return valorTotal;
	}

	public Date getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

	public BigDecimal getValorPago() {
		return valorPago;
	}

	public void setValorPago(BigDecimal valorPago) {
		this.valorPago = valorPago;
	}

	public List<Transacao> getTransacoes() {
		return transacoes;
	}

	public void setTransacoes(List<Transacao> transacoes) {
		this.transacoes = transacoes;
	}

	public Boolean isVencida() {
		if (this.dataPagamento != null) {
			return false;
		}

		return Calendar.getInstance().getTime().after(this.dataVencimento);
	}

}
