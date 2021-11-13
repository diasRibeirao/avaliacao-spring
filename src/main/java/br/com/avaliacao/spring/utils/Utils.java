package br.com.avaliacao.spring.utils;

import java.util.Calendar;
import java.util.Date;

public class Utils {

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
}
