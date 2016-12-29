package br.com.joelamalio.ranking.service;

import java.util.ArrayList;
import java.util.List;

import br.com.joelamalio.ranking.model.Corrida;
import br.com.joelamalio.ranking.model.Piloto;
import br.com.joelamalio.ranking.model.Volta;

public class VoltaService {
	
	public Volta obterMelhorVoltaPorCorrida(Corrida corrida) {
		Volta melhorVolta = null;
		for (Piloto piloto : corrida.getPilotos()) {
			if (melhorVolta == null || piloto.getMelhorVolta().getTempoVoltaEmNanosegundos() < melhorVolta.getTempoVoltaEmNanosegundos()) {
				melhorVolta = piloto.getMelhorVolta();
			}
		}
		
		return melhorVolta;
	}
	
	public List<Volta> obterMelhorVoltaPorPiloto(Corrida corrida) {
		List<Volta> melhoresVoltas = new ArrayList<>();
		for (Piloto piloto : corrida.getPilotos()) {
			melhoresVoltas.add(piloto.getMelhorVolta());
		}
		
		return melhoresVoltas;
	}

}
