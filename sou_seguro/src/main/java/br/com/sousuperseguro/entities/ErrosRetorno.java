package br.com.sousuperseguro.entities;

import java.io.Serializable;
import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="erros_retorno")
public class ErrosRetorno implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5061466770150152832L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private BigInteger id;
	
	@Column(name="descricao")
	private String descricao;
	
	@Column(name="numero_erro", length = 4)
	private String numeroErro;

	public BigInteger getId() {
		return id;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getNumeroErro() {
		return numeroErro;
	}

	public void setNumeroErro(String numeroErro) {
		this.numeroErro = numeroErro;
	}
	
	
}
