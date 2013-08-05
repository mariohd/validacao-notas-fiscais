package br.com.marioh.model;

public enum Operacao {
	VENDA, TRANSFERENCIA, DOACAO, TROCA, EMPRESTIMO;
	
	public static Operacao identify(String operacao){
		if (operacao.equals("VENDA")) {
			return VENDA;
		} else if (operacao.equals("TRANSFERENCIA")) {
			return TRANSFERENCIA;
		} else if (operacao.equals("DOACAO")) {
			return DOACAO;
		} else if (operacao.equals("TROCA")) {
			return TROCA;
		} else
			return EMPRESTIMO;
	}
}
