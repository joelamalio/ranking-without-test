package br.com.joelamalio.ranking.util;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class DataUtil {

	private static final DateTimeFormatter FORMATTER_HH_MM_SS_SSS = DateTimeFormatter.ofPattern("HH:mm:ss.SSS");

	public static long converterHorarioEmNanosegundos(String texto) {
		LocalTime tempo = LocalTime.parse(formatarTempo(texto), FORMATTER_HH_MM_SS_SSS);
		return tempo.toNanoOfDay();
	}

	public static String converterNanosegundosEmHorario(long tempo) {
		return LocalTime.ofNanoOfDay(tempo).format(FORMATTER_HH_MM_SS_SSS);
	}

	private static String formatarTempo(String texto) {
		if (texto.split(":").length == 2) {
			return "00:0" + texto;
		}
		return texto;
	}

}
