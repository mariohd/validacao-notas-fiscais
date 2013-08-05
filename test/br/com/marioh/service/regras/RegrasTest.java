package br.com.marioh.service.regras;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import br.com.marioh.model.Classificacao;
import br.com.marioh.model.NotaFiscal;
import br.com.marioh.model.Operacao;
import br.com.marioh.service.regras.Regras;

public class RegrasTest {
	
	@Test
	public void deveCalcularCorretamenteImpostoDeVendaClassificacaoA(){
		NotaFiscal notaFiscal = getNotaFiscalDeVenda();
		notaFiscal.setClassificacao(Classificacao.A);
		notaFiscal.setValor(1000);
		assertEquals("Calculo de imposto errado. Deveria retornar 180.0", 180.0, Regras.calculoDeImposto(notaFiscal),0);
	}
	
	@Test
	public void deveCalcularCorretamenteImpostoDeVendaClassificacaoB(){
		NotaFiscal notaFiscal = getNotaFiscalDeVenda();
		notaFiscal.setClassificacao(Classificacao.B);
		notaFiscal.setValor(1000);
		assertEquals("Calculo de imposto errado. Deveria retornar 100.0", 100.0, Regras.calculoDeImposto(notaFiscal),0);
	}
	
	@Test
	public void deveCalcularCorretamenteImpostoDeVendaClassificacaoC(){
		NotaFiscal notaFiscal = getNotaFiscalDeVenda();
		notaFiscal.setClassificacao(Classificacao.C);
		notaFiscal.setValor(1000);
		assertEquals("Calculo de imposto errado. Deveria retornar 100.0", 100.0, Regras.calculoDeImposto(notaFiscal),0);
	}
	
	@Test
	public void naoDeveCalcularImpostoDeVendaClassificacaoD(){
		NotaFiscal notaFiscal = getNotaFiscalDeVenda();
		notaFiscal.setClassificacao(Classificacao.D);
		notaFiscal.setValor(1000);
		assertEquals("Calculo de imposto errado. Deveria retornar 15.0", 15.0, Regras.calculoDeImposto(notaFiscal),0);
	}
	
	@Test(expected=RuntimeException.class)
	public void naoDeveCalcularImpostoDeVendaClassificacaoAsterisc(){
		NotaFiscal notaFiscal = getNotaFiscalDeVenda();
		notaFiscal.setClassificacao(Classificacao.F);
		notaFiscal.setValor(1000);
		Regras.calculoDeImposto(notaFiscal);
	}
	
	@Test
	public void deveCalcularImpostoFixoDeTransferenciaComQualquerClassificacaoA(){
		NotaFiscal notaFiscal = getNotaFiscalDeTransferencia();
		notaFiscal.setClassificacao(Classificacao.A);
		notaFiscal.setValor(1500);
		assertEquals("Calculo de imposto errado. Deveria retornar 225.0", 225.0, Regras.calculoDeImposto(notaFiscal),0);
	}
	
	@Test
	public void deveCalcularImpostoFixoDeTransferenciaComQualquerClassificacaoG(){
		NotaFiscal notaFiscal = getNotaFiscalDeTransferencia();
		notaFiscal.setClassificacao(Classificacao.G);
		notaFiscal.setValor(2500);
		assertEquals("Calculo de imposto errado. Deveria retornar 375.0", 375.0, Regras.calculoDeImposto(notaFiscal),0);
	}
	
	@Test
	public void deveSerZeroOValorDoImposto(){
		NotaFiscal notaFiscal = getNotaFiscalDeDoacao();
		notaFiscal.setClassificacao(Classificacao.B);
		notaFiscal.setValor(10000);
		assertEquals("Nao existe imposto sobre doacoes", 0.0, Regras.calculoDeImposto(notaFiscal),0);
	}
	
	@Test
	public void deveCalcularImpostoParaOutrasOperacoesComClassificacaoD(){
		NotaFiscal notaFiscal = getNotaFiscalDeEmprestimo();
		notaFiscal.setValor(5000);
		notaFiscal.setClassificacao(Classificacao.D);
		assertEquals("Deveria calcular o valor do imposto", 75.0, Regras.calculoDeImposto(notaFiscal),0);
	}
	
	@Test
	public void verificarRegraDeImposto(){
		NotaFiscal notaFiscal = getNotaFiscalDeEmprestimo();
		notaFiscal.setValor(5000);
		notaFiscal.setClassificacao(Classificacao.D);
		Regras.calculoDeImposto(notaFiscal);
		assertEquals("Deveria dizer qual regra de imposto foi usada.", 6, notaFiscal.getRegraDeImposto());
	}
	
	@Test(expected=RuntimeException.class)
	public void naoDeveCalcularImpostoParaOutrasOperacoesClassificacaoDiferenteD(){
		NotaFiscal notaFiscal = getNotaFiscalDeEmprestimo();
		notaFiscal.setValor(5000);
		notaFiscal.setClassificacao(Classificacao.F);
		Regras.calculoDeImposto(notaFiscal);
	}
	
	@Test(expected=RuntimeException.class)
	public void naoDeveCalcularImpostoSobreNotaSemOperacao(){
		NotaFiscal notaFiscal = new NotaFiscal();
		notaFiscal.setOperacao(Operacao.VENDA);
		Regras.calculoDeImposto(notaFiscal);
	}
	
	@Test(expected=RuntimeException.class)
	public void naoDeveCalcularImpostoSobreNotaSemClassificacao(){
		NotaFiscal notaFiscal = new NotaFiscal();
		notaFiscal.setClassificacao(Classificacao.C);
		Regras.calculoDeImposto(notaFiscal);
	}
	
	@Test(expected=RuntimeException.class)
	public void naoDeveCalcularImpostoSobreNotaSemClassificacaoEOperacao(){
		NotaFiscal notaFiscal = new NotaFiscal();
		Regras.calculoDeImposto(notaFiscal);
	}
	
	private NotaFiscal getNotaFiscalDeVenda(){
		NotaFiscal notaFiscal = new NotaFiscal();
		notaFiscal.setOperacao(Operacao.VENDA);
		return notaFiscal;
	}
	
	private NotaFiscal getNotaFiscalDeTransferencia(){
		NotaFiscal notaFiscal = new NotaFiscal();
		notaFiscal.setOperacao(Operacao.TRANSFERENCIA);
		return notaFiscal;
	}
	
	private NotaFiscal getNotaFiscalDeDoacao(){
		NotaFiscal notaFiscal = new NotaFiscal();
		notaFiscal.setOperacao(Operacao.DOACAO);
		return notaFiscal;
	}
	
	private NotaFiscal getNotaFiscalDeEmprestimo(){
		NotaFiscal notaFiscal = new NotaFiscal();
		notaFiscal.setOperacao(Operacao.EMPRESTIMO);
		return notaFiscal;
	}
}
