package br.com.marioh.model;

public class NotaFiscal {
	
	private long numero;
	private Operacao operacao;
	private Classificacao classificacao;
	private double valor;
	private double impostoFinal;
	private double impostoEsperado;
	private int regraDeImposto;
	
	public long getNumero() {
		return numero;
	}
	public void setNumero(long numero) {
		this.numero = numero;
	}
	public Operacao getOperacao() {
		return operacao;
	}
	public void setOperacao(Operacao operacao) {
		this.operacao = operacao;
	}
	public Classificacao getClassificacao() {
		return classificacao;
	}
	public void setClassificacao(Classificacao classificacao) {
		this.classificacao = classificacao;
	}
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	public double getImpostoFinal() {
		return impostoFinal;
	}
	public void setImpostoFinal(double imposto) {
		this.impostoFinal = imposto;
	}
	public int getRegraDeImposto() {
		return regraDeImposto;
	}
	public void setRegraDeImposto(int regraDeImposto) {
		this.regraDeImposto = regraDeImposto;
	}
	public double getImpostoEsperado() {
		return impostoEsperado;
	}
	public void setImpostoEsperado(double impostoEsperado) {
		this.impostoEsperado = impostoEsperado;
	}
}
