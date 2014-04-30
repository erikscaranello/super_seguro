package br.com.sousuperseguro.enums;

public enum Categoria {
	TITULAR(1),
	DEPENDENTES(2);
	
	
	private Integer categoria;
	
	private Categoria(Integer categoria) {
		this.categoria = categoria;
	}
	
	public Integer getCategoria() {
		return categoria;
	}
}
