package br.com.sousuperseguro.enums;

public enum Banco {
	BRADESCO(237),
	ITAU(341),
	BANCO_DO_BRASIL(001);
	
	private Integer banco;
	 
	 
	private Banco(Integer banco) {
		 this.banco = banco;
	}

	public Integer getBanco() {
		return banco;
	}
}
