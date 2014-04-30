package br.com.sousuperseguro.entities;

import java.io.Serializable;
import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="proposta")
public class Proposta implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6808134470797903027L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private BigInteger id;
	
	@Column(name="proposta_pronta")
	private String propostaPronta;

	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "id_recebido_sou_super_seguro", referencedColumnName = "id")
	private RecebidoSouSuperSeguro idRecebidoSouSuperSeguro;
	
	public BigInteger getId() {
		return id;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}

	public String getPropostaPronta() {
		return propostaPronta;
	}

	public void setPropostaPronta(String propostaPronta) {
		this.propostaPronta = propostaPronta;
	}

	public RecebidoSouSuperSeguro getIdRecebidoSouSuperSeguro() {
		return idRecebidoSouSuperSeguro;
	}

	public void setIdRecebidoSouSuperSeguro(RecebidoSouSuperSeguro idRecebidoSouSuperSeguro) {
		this.idRecebidoSouSuperSeguro = idRecebidoSouSuperSeguro;
	}

	public void insert(Proposta propostaNova) {
		// TODO Auto-generated method stub
		
	}
}
