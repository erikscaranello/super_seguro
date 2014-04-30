package br.com.sousuperseguro.enums;

public enum Parentesco {
	PAI ("01"),
	MAE ("02"),
	FILHA ("03"),
	FILHO ("04"),
	CONJUGE ("05"),
	COMPANHEIRO ("06"),
	AGREGADO ("07"),
	IRMAO ("08"),
	IRMA ("09"),
	SOBRINHO ("10"),
	NETO ("11"),
	AVO ("12"),
	TIO ("13"),
	SOGRO ("14"),
	SOGRA ("15"),
	CUNHADO ("16"),
	TUTELADO ("17"),
	OUTRO ("18");
	 
	private String parentesco;
	 
	 
	private Parentesco(String parentesco) {
		 this.parentesco = parentesco;
	}

	public String getParentesco() {
		return parentesco;
	}
}
