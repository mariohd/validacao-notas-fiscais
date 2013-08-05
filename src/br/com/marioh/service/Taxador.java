package br.com.marioh.service;

import java.util.List;

import br.com.marioh.model.NotaFiscal;
import br.com.marioh.service.io.IO;
import br.com.marioh.service.regras.Regras;

public class Taxador {
	
	public static void main(String[] args) {
		Taxador t = new Taxador();
		t.calcularImpostos("src/br/com/marioh/service/io/datasource/NotasFiscais.txt", "src/br/com/marioh/service/io/datasource/NotasFiscaisResultado.txt");
	}
	
	public void calcularImpostos(String filepath, String targetFile){
		List<NotaFiscal> notas = lerNotaFiscais(filepath);
		for(NotaFiscal nf : notas){
			nf.setImpostoFinal(Regras.calculoDeImposto(nf));
		}
		escreverArquivoFinal(notas,targetFile);
	}

	private List<NotaFiscal> lerNotaFiscais(String filepath) {
		return IO.notasFiscais(filepath);
	}
	
	private void escreverArquivoFinal(List<NotaFiscal> notas, String targetFile) {
		IO.escreverResultados(notas, targetFile);
	}
}
