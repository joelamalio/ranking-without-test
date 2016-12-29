package br.com.joelamalio.ranking;

import java.io.File;
import java.util.List;

import br.com.joelamalio.ranking.model.Corrida;
import br.com.joelamalio.ranking.model.Piloto;
import br.com.joelamalio.ranking.model.Volta;
import br.com.joelamalio.ranking.service.CorridaService;
import br.com.joelamalio.ranking.service.VoltaService;
import br.com.joelamalio.ranking.util.DataUtil;
import br.com.joelamalio.ranking.util.FileUtil;

public class Main {

	public static void main(String[] args) throws Exception {
		File arquivoLog = FileUtil.obterArquivoDoProjeto("resultado.log");
		List<String> linhas = FileUtil.converterArquivoEmLista(arquivoLog, true);
		int numeroVoltas = 4;
		CorridaService corridaService = new CorridaService();
		Corrida corrida = corridaService.processarRegistros(linhas, numeroVoltas);
		
		imprimirResultado(corrida);
		
		imprimirBonus(corrida);
	}
	
	public static void imprimirResultado(Corrida corrida) {
		System.out.println("-----> RESULTADO DA CORRIDA");
		for (int i = 0; i < corrida.getPilotos().size(); ) {
			Piloto piloto = corrida.getPilotos().get(i++);
			System.out.println(String.format("Posição Chegada: %d - Código Piloto: %s - Nome Piloto: %s - Qtde Voltas Completadas: %d - Tempo Total de Prova: %s", i, piloto.getCodigo(), piloto.getNome(), piloto.getVoltas().size(), DataUtil.converterNanosegundosEmHorario(piloto.getTempoTotalVoltas())));
		}
		System.out.println("\n");
	}
	
	public static void imprimirBonus(Corrida corrida) {
		CorridaService corridaService = new CorridaService();
		VoltaService voltaService = new VoltaService();
		System.out.println("-----> BONUS");
		System.out.println("-> MELHOR VOLTA POR PILOTO");
		for (Volta volta : voltaService.obterMelhorVoltaPorPiloto(corrida)) {
			System.out.println(String.format("%s - %s - %s", volta.getPiloto().getNome(), volta.getNumero(), DataUtil.converterNanosegundosEmHorario(volta.getTempoVoltaEmNanosegundos())));
		}
		
		System.out.println("\n-> MELHOR VOLTA DA CORRIDA");
		Volta melhorVoltaDaCorrida =  voltaService.obterMelhorVoltaPorCorrida(corrida);
		System.out.println(String.format("%s - %s - %s", melhorVoltaDaCorrida.getPiloto().getNome(), melhorVoltaDaCorrida.getNumero(), DataUtil.converterNanosegundosEmHorario(melhorVoltaDaCorrida.getTempoVoltaEmNanosegundos())));
		
		System.out.println("\n-> VELOCIDADE MEDIA POR PILOTO");
		for (Piloto piloto : corrida.getPilotos()) {
			System.out.println(String.format("%s - %s", piloto.getNome(), corridaService.obterVelocidadeMediaPorPiloto(piloto)));
		}
		
		System.out.println("\n-> QUANDO TEMPO APOS O VENCEDOR");
		corridaService.obterTempoDaChegada(corrida).forEach(System.out::println);
	
	}
	
}
