package br.univel.url;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class LeitorProdutoUrl {
	
	public List<Produto> lerPrudutos(String strUrl) throws Exception{
			List<Produto> lista = new ArrayList<>();
			URL url = new URL(strUrl);
			URLConnection urlCon = url.openConnection(); //url.openConnection() abre conexao
			
			try(InputStream is = urlCon.getInputStream(); //inputstream é linhas de conexao
				InputStreamReader isr = new InputStreamReader(is); //inputreader leitura
					BufferedReader in = new BufferedReader(isr)){ //buffer = memoria
				
				String linha;
				while((linha = in.readLine()) != null){
					//System.out.println(linha);
					
					if(linha.matches("[0-9]+.*")){
						Produto p = lerProduto(linha);
						lista.add(p);
					}
				}
			}
			return lista;
		}
	
	private Pattern pattern = Pattern.compile("([0-9]+)(.*)US\\$ (.*)");

	private Produto lerProduto(String linha) {
		
		Matcher mat = pattern.matcher(linha);
		
		Produto produto = new Produto();
		if(mat.matches()){
			
			String strId = mat.group(1).trim();
			produto.setId(Long.parseLong(strId));
			
			String desc = mat.group(2).trim();
			produto.setDescricao(desc);	
			
			String strValorORG = mat.group(3).trim();
			String strValorSEMP = strValorORG.replaceAll("\\.", "");
			produto.setValorReal(new BigDecimal(strValorSEMP.replaceAll(",", ".")));	//REGULAR , VIRGULA PARA PONTO 	
			//produto.setValorDolar(new BigDecimal(strValor.replaceAll("\\.", ","))); // PONTO PARA VIRGULA
			
		}else{
			throw new RuntimeException("Linha invalida: " + linha);
		}
		
		return produto;
	}
	
	public static void main(String[] args) throws Exception {
		String url = "http://www.master10.com.py/lista-txt/dowload";
		LeitorProdutoUrl lpu = new LeitorProdutoUrl();
		List<Produto> lista = lpu.lerPrudutos(url);
		lista.forEach((e) -> System.out.println(e.toString()));
		
	}
	
		
	}
	
