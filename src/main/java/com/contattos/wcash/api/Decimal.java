package com.contattos.wcash.api;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.Locale;

public class Decimal {

	private static final NumberFormat NUMBER_FORMAT = new DecimalFormat("#,##0", new DecimalFormatSymbols(new Locale("pt", "BR")));
	private static final String[] SUFFIXES = new String[] {"", "K", "M", "B", "T", "Q", "QQ", "S", "SS", "O", "N", "D", "U", "DD", "UD", "TD", "QD", "QQD", "SD", "SSD", "OD", "ND", "V", "W", "X", "Y", "Z", "AA", "BB", "CC", "DD", "EE", "FF", "GG", "HH", "II", "JJ", "KK", "LL", "MM", "NN"};
	private static final int MAX_LENGTH = 4;
	private static final double MAX_VALUE = Math.pow(1000, SUFFIXES.length);

	public static String formatSimbolo(double number) {
		if (number < 0 || number >= MAX_VALUE) {
			return NUMBER_FORMAT.format(number);
		}

		int suffixIndex = 0;
		while (number >= 1000 && suffixIndex < SUFFIXES.length - 1) {
			number /= 1000;
			suffixIndex++;
		}

		String formattedNumber = NUMBER_FORMAT.format(number);
		String suffix = SUFFIXES[suffixIndex];

		if (formattedNumber.length() > MAX_LENGTH) {
			formattedNumber = formattedNumber.substring(0, MAX_LENGTH);
		}

		return formattedNumber + suffix;
	}

	public static double parseSimbolo(String str) throws NumberFormatException {
		String suffix = "";
		int i = str.length() - 1;
		while (i >= 0 && Character.isLetter(str.charAt(i))) {
			suffix = str.charAt(i) + suffix;
			i--;
		}
		String numberStr = str.substring(0, i + 1).replace(",", "");
		double number = Double.parseDouble(numberStr);
		if (!suffix.isEmpty()) {
			int suffixIndex = Arrays.asList(SUFFIXES).indexOf(suffix.toUpperCase());
			if (suffixIndex < 0) {
				throw new NumberFormatException("Invalid suffix: " + suffix);
			}
			number *= Math.pow(1000, suffixIndex);
		}
		return number;
	}

	public static String formatPorcen(double porcentagem, int casasDecimais) {
		DecimalFormat df = new DecimalFormat();
		df.setMaximumFractionDigits(casasDecimais);
		return df.format(porcentagem);
	}

	public static String newProgress(double valorAtual, double valorMaximo, int tamanhoDaBarra) {
		StringBuilder barraDeProgresso = new StringBuilder();
		int progresso = (int) (valorAtual / valorMaximo * tamanhoDaBarra);
		for (int i = 0; i < tamanhoDaBarra; i++) {
			if (i < progresso) {
				barraDeProgresso.append("§a§l▌");
			} else {
				barraDeProgresso.append("§7§l▌");
			}
		}
		return barraDeProgresso.toString();
	}

}
