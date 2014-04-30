package br.com.sousuperseguro.enums;

public enum Status {
	I("inclusao"),
	A("alteracao"),
	E("exclusao");
	
	private String status;
	
	private Status(String status) {
		this.status = status;
	}
	
	public String getStatus() {
		return status;
	}
}
