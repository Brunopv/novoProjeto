package br.com.prova.util;

import java.util.Calendar;

public class Util {
	
	 public static Calendar montaData(int ano, int mes, int dia, int hora, int minuto, int segundo) {
	        Calendar result = Calendar.getInstance();
	        result.clear();
	        result.set(ano, mes - 1, dia, hora, minuto, segundo);
	        return result;
	    }

}
