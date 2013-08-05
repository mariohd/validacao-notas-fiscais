package br.com.marioh.service;

import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.Test;

public class TaxadorTest {
	
	private String readfile = "test/br/com/marioh/service/io/datasource/NotasFiscaisTest.txt";
	private String writefile = "test/br/com/marioh/service/io/datasource/NotasFiscaisResultadoTest.txt";
	
	@Test
	public void deveCriarArquivoComOResultadoDoCalculo(){
		Taxador tx = getTaxador();
		tx.calcularImpostos(readfile, writefile);
		File file = new File(writefile);
		assertTrue("Arquivo nao foi criado.", file.exists());
		file.deleteOnExit();
	}
	
	private Taxador getTaxador(){
		return new Taxador();
	}
}
