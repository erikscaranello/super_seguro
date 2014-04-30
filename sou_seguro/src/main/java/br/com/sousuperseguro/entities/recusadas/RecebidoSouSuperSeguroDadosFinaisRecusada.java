package br.com.sousuperseguro.entities.recusadas;

import java.io.Serializable;
import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="recebido_sou_super_seguro_dados_finais_recusada")
public class RecebidoSouSuperSeguroDadosFinaisRecusada implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6958989243430796016L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private BigInteger id;
	
	@Column(name="tp_negoc", nullable=true, length=1)
	private Integer tpNegoc;
	
	@Column(name="tp_comissao", nullable=true, length=2)
	private Integer tpComissao;
	
	@Column(name="c_tipo_registro", nullable=true, length=1)
	private String cTipoRegistro;
	
	@Column(name="nr_total_reg", nullable=true, length=10)
	private Integer nrTotalReg;
	
	@Column(name="filler", nullable=true, length=1140)
	private String filler;
	
	public BigInteger getId() {
		return id;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}

	public Integer getTpNegoc() {
		return tpNegoc;
	}

	public void setTpNegoc(Integer tpNegoc) {
		this.tpNegoc = tpNegoc;
	}

	public Integer getTpComissao() {
		return tpComissao;
	}

	public void setTpComissao(Integer tpComissao) {
		this.tpComissao = tpComissao;
	}

	public String getcTipoRegistro() {
		return cTipoRegistro;
	}

	public void setcTipoRegistro(String cTipoRegistro) {
		this.cTipoRegistro = cTipoRegistro;
	}

	public Integer getNrTotalReg() {
		return nrTotalReg;
	}

	public void setNrTotalReg(Integer nrTotalReg) {
		this.nrTotalReg = nrTotalReg;
	}

	public String getFiller() {
		return filler;
	}

	public void setFiller(String filler) {
		this.filler = filler;
	}

}
