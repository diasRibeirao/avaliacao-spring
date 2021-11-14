package br.com.avaliacao.spring.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Utils {

	private static final Format FORMATTER_DATA = new SimpleDateFormat("dd/MM/yyyy");
	private static final Format FORMATTER_REAL = new DecimalFormat("#,###,##0.00");

	public static Date dataFechamentoCartaoCredito(Integer melhorDiaPagamento) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.MILLISECOND, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.HOUR, 0);
		calendar.set(Calendar.AM_PM, Calendar.AM);

		if (calendar.get(Calendar.DAY_OF_MONTH) >= melhorDiaPagamento) {
			calendar.add(Calendar.MONTH, 1);
		}

		calendar.set(Calendar.DAY_OF_MONTH, melhorDiaPagamento);

		return calendar.getTime();
	}

	public static Date dataVencimento(Date dataFechamento) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.MILLISECOND, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.HOUR, 0);
		calendar.set(Calendar.AM_PM, Calendar.AM);
		calendar.setTime(dataFechamento);
		calendar.add(Calendar.DAY_OF_MONTH, Constantes.QTD_DIAS_APOS_FECHAMENTO);

		return calendar.getTime();
	}

	public static Date dataAtual() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.MILLISECOND, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.HOUR, 0);
		calendar.set(Calendar.AM_PM, Calendar.AM);
		return calendar.getTime();
	}

	public static String dateToString(Date data) {
		if (data == null) {
			return "";
		}

		return FORMATTER_DATA.format(data);
	}

	public static String formatReal(BigDecimal valor) {
		if (valor == null) {
			return "";
		}
		return FORMATTER_REAL.format(valor);
	}
}
