package br.com.sousuperseguro.enums;

public enum EstadoCivil {
	CASADO (1),
	DESQUITADO (2),
	DIVORCIADO (3),
	SEPARADO (5),
	SOLTEIRO (6),
	VIUVO (7);
	
	private Integer estadoCivil;
	 
	 
	private EstadoCivil(Integer estadoCivil) {
		 this.estadoCivil = estadoCivil;
	}

	public Integer getEstadoCivil() {
		return estadoCivil;
	}
}
