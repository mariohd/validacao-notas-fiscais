package br.com.marioh.service.regras;

import br.com.marioh.model.NotaFiscal;

public class Regras {

	public static double calculoDeImposto(NotaFiscal notaFiscal) {
		if (notaFiscal.getOperacao() != null && notaFiscal.getClassificacao() != null) {
			return aplicarRegra(notaFiscal);
		}
		throw new RuntimeException("Nota Fiscal incorreta. Nota fiscal sem operacao ou classificacao.");
	}

	private static double aplicarRegra(NotaFiscal notaFiscal) {
		switch (notaFiscal.getOperacao()) {
		case VENDA:
			return regraDeVenda(notaFiscal);
		case TRANSFERENCIA:
			return regraDeTransferencia(notaFiscal);
		case DOACAO:
			return regraDeDoacao(notaFiscal);
		default:
			return regraGeral(notaFiscal);
		}
	}

	private static double regraDeVenda(NotaFiscal notaFiscal) {
		switch (notaFiscal.getClassificacao()) {
		case A:
			notaFiscal.setRegraDeImposto(1);
			return notaFiscal.getValor() * 0.18;
		case B:
			notaFiscal.setRegraDeImposto(2);
			return notaFiscal.getValor() * 0.10;
		case C:
			notaFiscal.setRegraDeImposto(3);
			return notaFiscal.getValor() * 0.10;
		default:
			return regraGeral(notaFiscal);
		}
	}

	private static double regraDeTransferencia(NotaFiscal notaFiscal) {
		notaFiscal.setRegraDeImposto(4);
		return notaFiscal.getValor() * 0.15;
	}
	
	private static double regraDeDoacao(NotaFiscal notaFiscal) {
		notaFiscal.setRegraDeImposto(5);
		return 0;
	}
	
	private static double regraGeral(NotaFiscal notaFiscal) {
		notaFiscal.setRegraDeImposto(6);
		switch (notaFiscal.getClassificacao()) {
		case D:
			return notaFiscal.getValor() * 0.015;
		default: 
			throw new RuntimeException("Nota Fiscal incorreta. Classificacao incorreta.");
		}
	}
}
