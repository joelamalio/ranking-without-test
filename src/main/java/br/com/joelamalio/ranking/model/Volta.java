package br.com.joelamalio.ranking.model;

import java.io.Serializable;

import br.com.joelamalio.ranking.util.DataUtil;

public class Volta implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2483219569614305611L;
	private Corrida corrida;
	private Piloto piloto;
	private String numero;
	private String tempoVolta;
	private String horarioVolta;
	private String velocidadeMedia;

	public Corrida getCorrida() {
		return corrida;
	}

	public void setCorrida(Corrida corrida) {
		this.corrida = corrida;
	}

	public Piloto getPiloto() {
		return piloto;
	}

	public void setPiloto(Piloto piloto) {
		this.piloto = piloto;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getTempoVolta() {
		return tempoVolta;
	}

	public void setTempoVolta(String tempoVolta) {
		this.tempoVolta = tempoVolta;
	}

	public String getHorarioVolta() {
		return horarioVolta;
	}

	public void setHorarioVolta(String horarioVolta) {
		this.horarioVolta = horarioVolta;
	}

	public String getVelocidadeMedia() {
		return velocidadeMedia;
	}

	public void setVelocidadeMedia(String velocidadeMedia) {
		this.velocidadeMedia = velocidadeMedia;
	}

	public long getTempoVoltaEmNanosegundos() {
		return DataUtil.converterHorarioEmNanosegundos(tempoVolta);
	}

	public long getHorarioVoltaEmNanosegundos() {
		return DataUtil.converterHorarioEmNanosegundos(horarioVolta);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((corrida == null) ? 0 : corrida.hashCode());
		result = prime * result + ((numero == null) ? 0 : numero.hashCode());
		result = prime * result + ((piloto == null) ? 0 : piloto.hashCode());
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
		Volta other = (Volta) obj;
		if (corrida == null) {
			if (other.corrida != null)
				return false;
		} else if (!corrida.equals(other.corrida))
			return false;
		if (numero == null) {
			if (other.numero != null)
				return false;
		} else if (!numero.equals(other.numero))
			return false;
		if (piloto == null) {
			if (other.piloto != null)
				return false;
		} else if (!piloto.equals(other.piloto))
			return false;
		return true;
	}

}
