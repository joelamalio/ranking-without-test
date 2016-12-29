package br.com.joelamalio.ranking.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import br.com.joelamalio.ranking.enums.PadraoLogRanking;
import br.com.joelamalio.ranking.model.Corrida;
import br.com.joelamalio.ranking.model.Piloto;
import br.com.joelamalio.ranking.model.Volta;
import br.com.joelamalio.ranking.util.DataUtil;

public class CorridaService {

	public Corrida processarRegistros(List<String> linhas, int numeroVoltas) throws Exception {
		Corrida corrida = new Corrida(numeroVoltas);
		Piloto piloto;
		Volta volta;
		for (String linha : linhas) {
			piloto = new Piloto();
			piloto.setCodigo(recortarTrecho(linha, PadraoLogRanking.PILOTO_CODIGO));
			piloto.setNome(recortarTrecho(linha, PadraoLogRanking.PILOTO_NOME));
			piloto = corrida.adicionar(piloto);
			
			volta = new Volta();
			volta.setCorrida(corrida);
			volta.setPiloto(piloto);
			volta.setHorarioVolta(recortarTrecho(linha, PadraoLogRanking.HORA));
			volta.setNumero(recortarTrecho(linha, PadraoLogRanking.NUMERO_VOLTA));
			volta.setTempoVolta(recortarTrecho(linha, PadraoLogRanking.TEMPO_VOLTA));
			volta.setVelocidadeMedia(recortarTrecho(linha, PadraoLogRanking.VELOCIDADE_MEDIA));
			
			piloto.adicionar(volta);
		}
		
		corrida.ordernarPilotosPorTempoFinal();

		return corrida;
	}
	
	public String obterVelocidadeMediaPorPiloto(Piloto piloto) {
		BigDecimal velocidadeMedia = BigDecimal.ZERO;
		
		for (Volta volta: piloto.getVoltas()) {
			velocidadeMedia = velocidadeMedia.add(new BigDecimal(volta.getVelocidadeMedia().replaceAll(",", ".")));
		}
		
		if (velocidadeMedia.compareTo(BigDecimal.ZERO) > 0) {
			return velocidadeMedia.divide(new BigDecimal(piloto.getVoltas().size()), RoundingMode.HALF_UP).toString().replaceAll("\\.", ",");
		}
		
		return "";
	}
	
	public Volta obterMelhorVoltaDaCorrida(Corrida corrida) {
		Volta melhorVolta = null;

		for (Piloto piloto : corrida.getPilotos()) {
			if (melhorVolta == null || piloto.getMelhorVolta().getTempoVoltaEmNanosegundos() < melhorVolta.getTempoVoltaEmNanosegundos()) {
				melhorVolta = piloto.getMelhorVolta();
			}
		}

		return melhorVolta;
	}
	
	public List<String> obterTempoDaChegada(Corrida corrida) {
		List<String> chegadas = new ArrayList<String>();
		int ultimaVolta = corrida.getNumeroVoltas() - 1;
		Piloto campeao = obterCampeao(corrida);
		String chegada;
		long horarioChegadaCampeao = DataUtil.converterHorarioEmNanosegundos(campeao.getVoltas().get(ultimaVolta).getHorarioVolta());
		for (Piloto piloto : corrida.getPilotos()) {
			if (!piloto.equals(campeao)) {
				if (piloto.isCompletouProva()) {
					long horarioChegadaPiloto = DataUtil.converterHorarioEmNanosegundos(piloto.getVoltas().get(ultimaVolta).getHorarioVolta()) - horarioChegadaCampeao;
					chegada = String.format("+%s - %s", DataUtil.converterNanosegundosEmHorario(horarioChegadaPiloto), piloto.getNome());
				} else {
					chegada = String.format("+%s - %s", "??:??:??.???", piloto.getNome());
				}	
				chegadas.add(chegada);
			}
		}
		
		return chegadas;
	}
	
	private Piloto obterCampeao(Corrida corrida) {
		Piloto campeao = null;
		for (Piloto piloto : corrida.getPilotos()) {
			if (campeao == null || (piloto.isCompletouProva() && piloto.getTempoTotalVoltas() < campeao.getTempoTotalVoltas())) {
				campeao = piloto;
			}
		}
		
		return campeao;
	}

	private String recortarTrecho(String linha, PadraoLogRanking padrao) {
		if (padrao.getPosicaoFinal() != null) {
			return linha.substring(padrao.getPosicaoInicial(), padrao.getPosicaoFinal()).trim();
		}

		return linha.substring(padrao.getPosicaoInicial()).trim();
	}
	
}
