package br.com.sousuperseguro.enums;

public enum TipoCobranca {
	
	BOLETOBANCARIO_BOLETOBANCARIO(1),
	BOLETOBANCARIO_CONTACORRENTE(3);
	
	private Integer tipoCobranca;
	 
	private TipoCobranca(Integer tipoCobranca) {
		 this.tipoCobranca = tipoCobranca;
	}

	public Integer getTipoCobranca() {
		return tipoCobranca;
	}
}
