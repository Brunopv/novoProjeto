package br.com.prova.controller;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Estabelecimento {

	// simulador para retornar o percentual de cashback, de acordo com dia da
	// semana
	BigDecimal retornarPercentualCashback(Date dataCompra) {
		BigDecimal percentual = BigDecimal.ZERO;
		Calendar c = new GregorianCalendar();
		// Date dataCompra = new Date();
		c.setTime(dataCompra);

		int dia = c.get(c.DAY_OF_WEEK);
		switch (dia) {
		case Calendar.SUNDAY:
			percentual = new BigDecimal("5");
			break;
		case Calendar.MONDAY:
			percentual = new BigDecimal("10");
			;
			break;
		case Calendar.TUESDAY:
			percentual = new BigDecimal("15");
			;
			break;
		case Calendar.WEDNESDAY:
			percentual = new BigDecimal("20");
			;
			break;
		case Calendar.THURSDAY:
			percentual = new BigDecimal("25");
			;
			break;
		case Calendar.FRIDAY:
			percentual = new BigDecimal("30");
			;
			break;
		case Calendar.SATURDAY:
			percentual = new BigDecimal("35");
			;
			break;
		}

		return percentual;
	}
}
