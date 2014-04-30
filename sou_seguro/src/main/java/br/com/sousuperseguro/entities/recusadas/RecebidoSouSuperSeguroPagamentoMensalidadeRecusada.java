package br.com.sousuperseguro.entities.recusadas;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.sousuperseguro.enums.Banco;
import br.com.sousuperseguro.enums.Parentesco;
import br.com.sousuperseguro.enums.TipoCobranca;

@Entity
@Table(name="recebido_sou_super_seguro_mensalidade_recusada")
public class RecebidoSouSuperSeguroPagamentoMensalidadeRecusada implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6507590530390703541L;

	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private BigInteger id;
	
	@Enumerated(EnumType.STRING)
	@Column(name="nro_banco", nullable=true)
	private Banco nroBanco;
	
	@Column(name="nro_agencia", nullable=true)
	private String nroAgencia;
	
	@Column(name="dv_agencia", nullable=true)
	private String dvAgencia;
	
	@Column(name="c_corrente", nullable=true)
	private String cCorrente;

	@Column(name="dv_conta", nullable=true)
	private String dvConta;
	
	@Enumerated(EnumType.STRING)
	@Column(name="tp_cobr", nullable=false)
	private TipoCobranca tpCobr;
	
	@Column(name="nm_tit_corrente", nullable=false)
	private String nmTitCorrente;
	
	@Column(name="cpf_tit_corrente", nullable=false)
	private String cpfTitCorrente;
	
	@Enumerated(EnumType.STRING)
	@Column(name="c_parentesco_cobr", nullable=false)
	private Parentesco cParentescoCobr;
	
	@Column(nullable=true)
	private BigDecimal valor;

	public BigInteger getId() {
		return id;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}

	public Banco getNroBanco() {
		return nroBanco;
	}

	public void setNroBanco(Banco nroBanco) {
		this.nroBanco = nroBanco;
	}

	public String getNroAgencia() {
		return nroAgencia;
	}

	public void setNroAgencia(String nroAgencia) {
		this.nroAgencia = nroAgencia;
	}

	public String getDvAgencia() {
		return dvAgencia;
	}

	public void setDvAgencia(String dvAgencia) {
		this.dvAgencia = dvAgencia;
	}

	public String getcCorrente() {
		return cCorrente;
	}

	public void setcCorrente(String cCorrente) {
		this.cCorrente = cCorrente;
	}

	public String getDvConta() {
		return dvConta;
	}

	public void setDvConta(String dvConta) {
		this.dvConta = dvConta;
	}

	public TipoCobranca getTpCobr() {
		return tpCobr;
	}

	public void setTpCobr(TipoCobranca tpCobr) {
		this.tpCobr = tpCobr;
	}

	public String getNmTitCorrente() {
		return nmTitCorrente;
	}

	public void setNmTitCorrente(String nmTitCorrente) {
		this.nmTitCorrente = nmTitCorrente;
	}

	public String getCpfTitCorrente() {
		return cpfTitCorrente;
	}

	public void setCpfTitCorrente(String cpfTitCorrente) {
		this.cpfTitCorrente = cpfTitCorrente;
	}

	public Parentesco getcParentescoCobr() {
		return cParentescoCobr;
	}

	public void setcParentescoCobr(Parentesco cParentescoCobr) {
		this.cParentescoCobr = cParentescoCobr;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	
}
