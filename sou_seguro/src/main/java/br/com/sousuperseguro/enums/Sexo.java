package br.com.sousuperseguro.enums;

public enum Sexo {
	Masculino("M"),
	Feminino("F");
	
	private String sexo;
	
	private Sexo (String sexo) {
		this.sexo = sexo;
	}
	
	public String getSexo() {
		return sexo;
	}
}
