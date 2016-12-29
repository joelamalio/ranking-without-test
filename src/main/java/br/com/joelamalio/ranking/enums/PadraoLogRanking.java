package br.com.joelamalio.ranking.enums;

public enum PadraoLogRanking {

	HORA(0, 18),
	PILOTO_CODIGO(18,3),
	PILOTO_NOME(24, 20),
	NUMERO_VOLTA(44, 6),
	TEMPO_VOLTA(50, 20),
	VELOCIDADE_MEDIA(70, null);
	
	private PadraoLogRanking(Integer posicaoInicial, Integer tamanho) {
		this.posicaoInicial = posicaoInicial;
		if (tamanho != null) {
			this.posicaoFinal = posicaoInicial + tamanho;			
		}
	}
	
	private Integer posicaoInicial;
	private Integer posicaoFinal;

	public Integer getPosicaoInicial() {
		return posicaoInicial;
	}

	public Integer getPosicaoFinal() {
		return posicaoFinal;
	}
	
}
