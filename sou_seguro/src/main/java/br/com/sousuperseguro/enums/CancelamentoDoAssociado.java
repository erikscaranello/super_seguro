package br.com.sousuperseguro.enums;

public enum CancelamentoDoAssociado {
	ROMPIMENTO_DO_CONTRATO_POR_INICIATIVA_DO_BENEFICIARIO(1),
	TERMINO_DA_RELACAO_DE_VINCULADO_A_UM_BENEFICIARIO_TITULAR(2),
	DESLIGAMENTO_DA_EMPRESA(3),
	INADIMPLENCIA(4),
	OBITO(5);
	
	private Integer cancelamentoDoAssociado;
	
	private CancelamentoDoAssociado(Integer cancelamentoDoAssociado) {
		 this.cancelamentoDoAssociado = cancelamentoDoAssociado;
	}

	public Integer getCancelamentoDoAssociado() {
		return cancelamentoDoAssociado;
	}
}
