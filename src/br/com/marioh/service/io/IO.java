package br.com.marioh.service.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import br.com.marioh.model.Classificacao;
import br.com.marioh.model.NotaFiscal;
import br.com.marioh.model.Operacao;

public class IO {

	public static List<NotaFiscal> notasFiscais(String filepath){
		List<NotaFiscal> notas = new ArrayList<NotaFiscal>();
			try {
				File file = new File(filepath);
				FileReader fr = new FileReader(file);
				BufferedReader leitor = new BufferedReader(fr);
				leitor.readLine();
				String linha = leitor.readLine();
				while (linha != null){
					notas.add(convertStringToNotaFiscal(linha));
					linha = leitor.readLine();
				}
				leitor.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return notas;
		}

	
	private static NotaFiscal convertStringToNotaFiscal(String linha) {
		String[] split = linha.split(",");
		NotaFiscal nf = new NotaFiscal();
		nf.setNumero(Long.valueOf(split[0]));
		nf.setOperacao(Operacao.identify(split[1]));
		nf.setClassificacao(Classificacao.identify(split[2]));
		nf.setValor(Double.valueOf(split[3]));
		nf.setImpostoEsperado(Double.valueOf(split[4]));
		return nf;
	}


	public static void escreverResultados(List<NotaFiscal> notas,String filepath){
		try {
			PrintWriter writer = new PrintWriter(filepath, "UTF-8");
			writer.println("NUMERO,REGRA,CORRETO");
			for(NotaFiscal nf : notas){
				String resultado = "S";
				if(nf.getImpostoEsperado() != nf.getImpostoFinal())
					resultado = "N";
				writer.println(String.format("%03d",nf.getNumero()) + "," + nf.getRegraDeImposto() + ", " + resultado);
			}
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
