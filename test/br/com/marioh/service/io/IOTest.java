package br.com.marioh.service.io;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.util.List;

import org.junit.Test;

import br.com.marioh.model.NotaFiscal;
import br.com.marioh.service.regras.Regras;

public class IOTest {
	
	private String readfile = "test/br/com/marioh/service/io/datasource/NotasFiscaisTest.txt";
	private String writefile = "test/br/com/marioh/service/io/datasource/NotasFiscaisResultadoTest.txt";

	@Test
	public void deveriaCarregarTodasAsNotasFiscais(){
		List<NotaFiscal> notas = IO.notasFiscais(readfile);
		assertEquals("deveria carregar as 16 notas.", 16, notas.size());
		for(NotaFiscal nf : notas){
			if(nf.getNumero() == 0){
				fail();
			}
		}
	}
	
	@Test
	public void deveriaCriarOArquivoDeResultado(){
		List<NotaFiscal> notas = IO.notasFiscais(readfile);
		for(NotaFiscal nf : notas){
			nf.setImpostoFinal(Regras.calculoDeImposto(nf));
		}
		IO.escreverResultados(notas, writefile);
		File file = new File(writefile);
		assertTrue("Arquivo nao foi criado.", file.exists());
		file.deleteOnExit();
	}
}
