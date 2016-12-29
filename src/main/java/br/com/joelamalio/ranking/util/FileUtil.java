package br.com.joelamalio.ranking.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;
import java.util.stream.Collectors;

public class FileUtil {
	
	public static File obterArquivoDoProjeto(String nomeArquivo) {
		ClassLoader classLoader = FileUtil.class.getClassLoader();
		return new File(classLoader.getResource(nomeArquivo).getFile());
	}
	
	public static List<String> converterArquivoEmLista(File arquivo, boolean removerCabecalho) throws IOException {
		if (!arquivo.exists()) {
			throw new IOException("O arquivo não existe.");
		}
		
		BufferedReader br = Files.newBufferedReader(arquivo.toPath(), StandardCharsets.ISO_8859_1);
		List<String> linhas = br.lines().collect(Collectors.toList());
		if (removerCabecalho) {
			linhas.remove(0);
		}
		return linhas;
	}
	
}
