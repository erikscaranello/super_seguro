package br.com.sousuperseguro.entities;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="arquivos_envio")
public class ArquivosEnvio implements Serializable { 

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private BigInteger id;
	
	@Column(name="nome_arquivo")
	private String nomeArquivo;
	
	@Column(name="data_arquivo")
	private Calendar dataArquivo;
	
	@Column(name="recebido_erro")
	private boolean recebidoErro;
	
	
	public BigInteger getId() {
		return id;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}

	public String getNomeArquivo() {
		return nomeArquivo;
	}

	public void setNomeArquivo(String nomeArquivo) {
		this.nomeArquivo = nomeArquivo;
	}

	public Calendar getDataArquivo() {
		return dataArquivo;
	}

	public void setDataArquivo(Calendar dataArquivo) {
		this.dataArquivo = dataArquivo;
	}

	public boolean isRecebidoErro() {
		return recebidoErro;
	}

	public void setRecebidoErro(boolean recebidoErro) {
		this.recebidoErro = recebidoErro;
	}
}
