package br.com.sousuperseguro.entities;

import java.io.Serializable;
import java.math.BigInteger;

import javax.persistence.CascadeType;
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
@Table(name="numero_documento")
public class NumeroDocumento implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3758708817939624091L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private BigInteger id;
	
	@Column(name="numero_documento", length = 10, nullable = false)
	private String numeroDocumento;
	
	@Column(name="nosso_numero", length = 16, nullable = false)
	private String nossoNumero;

	
	@OneToOne 
    @JoinColumn(name="id_recebido_sou_super_seguro") 
	private RecebidoSouSuperSeguro idRecebidoSouSuperSeguro;
	
	
	public BigInteger getId() {
		return id;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}

	public String getNumeroDocumento() {
		return numeroDocumento;
	}

	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	public String getNossoNumero() {
		return nossoNumero;
	}

	public void setNossoNumero(String nossoNumero) {
		this.nossoNumero = nossoNumero;
	}

	public RecebidoSouSuperSeguro getIdRecebidoSouSuperSeguro() {
		return idRecebidoSouSuperSeguro;
	}

	public void setIdRecebidoSouSuperSeguro(RecebidoSouSuperSeguro idRecebidoSouSuperSeguro) {
		this.idRecebidoSouSuperSeguro = idRecebidoSouSuperSeguro;
	}
}
