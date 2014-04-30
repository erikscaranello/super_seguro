package br.com.sousuperseguro.serviceImpl;

import org.springframework.stereotype.Service;

import br.com.sousuperseguro.enums.Banco;
import br.com.sousuperseguro.enums.CancelamentoDoAssociado;
import br.com.sousuperseguro.enums.Categoria;
import br.com.sousuperseguro.enums.Parentesco;
import br.com.sousuperseguro.enums.Sexo;
import br.com.sousuperseguro.enums.TipoCobranca;
import br.com.sousuperseguro.service.VerificarEnums;

@Service
public class VerificarEnumsImpl implements VerificarEnums {

	@Override
	public Categoria verificarCategoria(String stringRecebida) {
		Categoria retorno = null;
		switch (new Integer(stringRecebida)) {
			case 1:
				retorno = Categoria.TITULAR;
				break;
			
			case 2:
				retorno = Categoria.DEPENDENTES;
				break;
		}
		return retorno;
	}

	@Override
	public Parentesco verificarParentesco(String parentesco) {
		Parentesco retorno = null;

		switch (new Integer(parentesco)) {
			case 1:
				retorno = Parentesco.PAI;
				break;
			case 2:
				retorno = Parentesco.MAE;
				break;
			case 3:
				retorno = Parentesco.FILHA;
				break;
			case 4:
				retorno = Parentesco.FILHO;
				break;
			case 5:
				retorno = Parentesco.CONJUGE;
				break;
			case 6:
				retorno = Parentesco.COMPANHEIRO;
				break;
			case 7:
				retorno = Parentesco.AGREGADO;
				break;
			case 8:
				retorno = Parentesco.IRMAO;
				break;
			case 9:
				retorno = Parentesco.IRMA;
				break;
			case 10:
				retorno = Parentesco.SOBRINHO;
				break;
			case 11:
				retorno = Parentesco.NETO;
				break;
			case 12:
				retorno = Parentesco.AVO;
				break;
			case 13:
				retorno = Parentesco.TIO;
				break;
			case 14:
				retorno = Parentesco.SOGRO;
				break;
			case 15:
				retorno = Parentesco.SOGRA;
				break;
			case 16:
				retorno = Parentesco.CUNHADO;
				break;
			case 17:
				retorno = Parentesco.TUTELADO;
				break;
			case 18:
				retorno = Parentesco.OUTRO;
				break;	
		}
		
		return retorno;
	}

	@Override
	public CancelamentoDoAssociado verificarCancelamentoDoAssociado(
			String stringRecebida) {
		CancelamentoDoAssociado retorno = null;
		switch (new Integer(stringRecebida)) {
			case 1:
				retorno = CancelamentoDoAssociado.ROMPIMENTO_DO_CONTRATO_POR_INICIATIVA_DO_BENEFICIARIO;
				break;
			case 2:
				retorno = CancelamentoDoAssociado.TERMINO_DA_RELACAO_DE_VINCULADO_A_UM_BENEFICIARIO_TITULAR;
				break;
			case 3:
				retorno = CancelamentoDoAssociado.DESLIGAMENTO_DA_EMPRESA;
				break;
			case 4:
				retorno = CancelamentoDoAssociado.INADIMPLENCIA;
				break;	
			case 5:
				retorno = CancelamentoDoAssociado.OBITO;
				break;
		}
		return retorno;
	}

	@Override
	public Banco verificarBanco(String stringRecebida) {
		Banco retorno = null;
		switch (new Integer(stringRecebida)) {
			case 237:
				retorno = Banco.BRADESCO;
				break;
			case 341:
				retorno = Banco.ITAU;
				break;
			case 001:
				retorno = Banco.BANCO_DO_BRASIL;
				break;	
		}
		
		return retorno;
	}

	@Override
	public TipoCobranca verificarTipoCobranca(String stringRecebida) {
		TipoCobranca retorno = null;
		switch (new Integer(stringRecebida)) {
			case 1:
				retorno = TipoCobranca.BOLETOBANCARIO_BOLETOBANCARIO;
				break;
			case 3:
				retorno = TipoCobranca.BOLETOBANCARIO_CONTACORRENTE;
				break;
		}
		return retorno;
	}

	@Override
	public Sexo verificarSexo(String stringRecebida) {
		Sexo retorno = null;
		if(stringRecebida.equals("Masculino")) {
			retorno  = Sexo.Masculino;
		} else {
			retorno  = Sexo.Feminino;
		}
		return retorno;
	}

	
	
}
