package br.com.joelamalio.ranking.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Piloto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8009404655070855217L;
	private String codigo;
	private String nome;
	private List<Volta> voltas = new ArrayList<Volta>();
	private long tempoTotalVoltas;
	private boolean completouProva;

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Volta> getVoltas() {
		return Collections.unmodifiableList(voltas);
	}
	
	public boolean isCompletouProva() {
		return completouProva;
	}

	public void setVoltas(List<Volta> voltas) {
		this.voltas = voltas;
	}
	
	public void adicionar(Volta volta) {
		voltas.add(volta);

		if (voltas.size() == volta.getCorrida().getNumeroVoltas()) {
			completouProva = true;
		}
	}
	
	public Long getTempoTotalVoltas() {
		if (tempoTotalVoltas == 0) {
			for (Volta volta : voltas) {
				tempoTotalVoltas += volta.getTempoVoltaEmNanosegundos();
			}
		}
		return tempoTotalVoltas;
	}
	
	public Volta getMelhorVolta() {
		Volta melhorVolta = null;
		for (Volta volta : voltas) {
			if (melhorVolta == null || volta.getTempoVoltaEmNanosegundos() < melhorVolta.getTempoVoltaEmNanosegundos()) {
				melhorVolta = volta;
			}
		}
		
		return melhorVolta;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
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
		Piloto other = (Piloto) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

}
