package br.com.marioh.model;

public enum Classificacao {
	A, B, C, D, F, G;

	public static Classificacao identify(String classificacao) {
		if (classificacao.equals("A")) {
			return A;
		} else if (classificacao.equals("B")) {
			return B;
		} else if (classificacao.equals("C")) {
			return C;
		} else if (classificacao.equals("D")) {
			return D;
		} else if (classificacao.equals("F")) {
			return F;
		} else 
			return G;
	}
}
