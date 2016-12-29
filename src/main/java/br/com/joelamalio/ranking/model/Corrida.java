package br.com.joelamalio.ranking.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Corrida implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3460089582703992432L;
	private int numeroVoltas;
	private List<Piloto> pilotos = new ArrayList<Piloto>();

	public Corrida() {
	}

	public Corrida(int numeroVoltas) {
		super();
		this.numeroVoltas = numeroVoltas;
	}

	public int getNumeroVoltas() {
		return numeroVoltas;
	}

	public void setNumeroVoltas(int numeroVoltas) {
		this.numeroVoltas = numeroVoltas;
	}

	public List<Piloto> getPilotos() {
		return Collections.unmodifiableList(pilotos);
	}

	public void setPilotos(List<Piloto> pilotos) {
		this.pilotos = pilotos;
	}

	public Piloto adicionar(Piloto piloto) {
		if (!pilotos.contains(piloto)) {
			pilotos.add(piloto);
			return piloto;
		}

		for (Piloto p : pilotos) {
			if (p.equals(piloto)) {
				return p;
			}
		}

		return null;
	}

	public void ordernarPilotosPorTempoFinal() {
		Collections.sort(pilotos, new Comparator<Piloto>() {

			@Override
			public int compare(Piloto o1, Piloto o2) {
				return o1.getTempoTotalVoltas().compareTo(o2.getTempoTotalVoltas());
			}
		});
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + numeroVoltas;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Corrida other = (Corrida) obj;
		if (numeroVoltas != other.numeroVoltas)
			return false;
		return true;
	}

}
