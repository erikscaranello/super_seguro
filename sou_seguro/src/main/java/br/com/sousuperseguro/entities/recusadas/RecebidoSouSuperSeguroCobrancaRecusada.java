package br.com.sousuperseguro.entities.recusadas;

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
@Table(name="recebido_sou_super_seguro_cobranca_recusada")
public class RecebidoSouSuperSeguroCobrancaRecusada implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 488817968384944715L;

	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private BigInteger id;
	
	@Column(name="nm_cobr", nullable=true)
	private String nmCobr;
	
	@Column(name="dt_nasc_cobr", nullable=true)
	private Calendar dtNascCobr;
	
	@Column(name="cpf_cobr", nullable=true)
	private String cpfCobr;
	
	@Column(name="r_lograd_cobr", nullable=true)
	private String rLogradCobr;
	
	@Column(name="r_numero_cobr", nullable=true)
	private String rNumeroCobr;
	
	@Column(name="comp_cobr", nullable=true)
	private String compCobr;
	
	@Column(name="bairro_cobr", nullable=true)
	private String bairroCobr;
	
	@Column(name="cidade_cobr", nullable=true)
	private String cidadeCobr;
	
	@Column(name="id_cidade_cobr", nullable=true)
	private BigInteger idCidadeCobr;
	
	@Column(name="uf_cobr", nullable=true)
	private String ufCobr;
	
	@Column(name="cep_cobr", nullable=true)
	private String cepCobr;
	
	@Column(name="d_fone1", nullable=true)
	private String dFone1;
	
	@Column(name="n_fone1", nullable=true)
	private String nFone1;
	
	@Column(name="c_fone1", nullable=true)
	private String cFone1;
	
	@Column(name="d_fone2", nullable=true)
	private String dFone2;
	
	@Column(name="n_fone2", nullable=true)
	private String nFone2;
	
	@Column(name="c_fone2", nullable=true)
	private String cFone2;
	
	@Column(name="d_fone3", nullable=true)
	private String dFone3;
	
	@Column(name="n_fone3", nullable=true)
	private String nFone3;
	
	@Column(name="c_fone3", nullable=true)
	private String cFone3;
	
	@Column(name="email", nullable=true)
	private String email;
	
	@Column(name="dia_venc", nullable=true)
	private Calendar diaVenc;
	
	@Column(name="data_inicio_cobr", nullable=true)
	private Calendar dataInicioCobr;

	public BigInteger getId() {
		return id;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}

	public String getNmCobr() {
		return nmCobr;
	}

	public void setNmCobr(String nmCobr) {
		this.nmCobr = nmCobr;
	}

	public Calendar getDtNascCobr() {
		return dtNascCobr;
	}

	public void setDtNascCobr(Calendar dtNascCobr) {
		this.dtNascCobr = dtNascCobr;
	}

	public String getCpfCobr() {
		return cpfCobr;
	}

	public void setCpfCobr(String cpfCobr) {
		this.cpfCobr = cpfCobr;
	}

	public String getrLogradCobr() {
		return rLogradCobr;
	}

	public void setrLogradCobr(String rLogradCobr) {
		this.rLogradCobr = rLogradCobr;
	}

	public String getrNumeroCobr() {
		return rNumeroCobr;
	}

	public void setrNumeroCobr(String rNumeroCobr) {
		this.rNumeroCobr = rNumeroCobr;
	}

	public String getCompCobr() {
		return compCobr;
	}

	public void setCompCobr(String compCobr) {
		this.compCobr = compCobr;
	}

	public String getBairroCobr() {
		return bairroCobr;
	}

	public void setBairroCobr(String bairroCobr) {
		this.bairroCobr = bairroCobr;
	}

	public String getCidadeCobr() {
		return cidadeCobr;
	}

	public void setCidadeCobr(String cidadeCobr) {
		this.cidadeCobr = cidadeCobr;
	}

	public BigInteger getIdCidadeCobr() {
		return idCidadeCobr;
	}

	public void setIdCidadeCobr(BigInteger idCidadeCobr) {
		this.idCidadeCobr = idCidadeCobr;
	}

	public String getUfCobr() {
		return ufCobr;
	}

	public void setUfCobr(String ufCobr) {
		this.ufCobr = ufCobr;
	}

	public String getCepCobr() {
		return cepCobr;
	}

	public void setCepCobr(String cepCobr) {
		this.cepCobr = cepCobr;
	}

	public String getdFone1() {
		return dFone1;
	}

	public void setdFone1(String dFone1) {
		this.dFone1 = dFone1;
	}

	public String getnFone1() {
		return nFone1;
	}

	public void setnFone1(String nFone1) {
		this.nFone1 = nFone1;
	}

	public String getcFone1() {
		return cFone1;
	}

	public void setcFone1(String cFone1) {
		this.cFone1 = cFone1;
	}

	public String getdFone2() {
		return dFone2;
	}

	public void setdFone2(String dFone2) {
		this.dFone2 = dFone2;
	}

	public String getnFone2() {
		return nFone2;
	}

	public void setnFone2(String nFone2) {
		this.nFone2 = nFone2;
	}

	public String getcFone2() {
		return cFone2;
	}

	public void setcFone2(String cFone2) {
		this.cFone2 = cFone2;
	}

	public String getdFone3() {
		return dFone3;
	}

	public void setdFone3(String dFone3) {
		this.dFone3 = dFone3;
	}

	public String getnFone3() {
		return nFone3;
	}

	public void setnFone3(String nFone3) {
		this.nFone3 = nFone3;
	}

	public String getcFone3() {
		return cFone3;
	}

	public void setcFone3(String cFone3) {
		this.cFone3 = cFone3;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Calendar getDiaVenc() {
		return diaVenc;
	}

	public void setDiaVenc(Calendar diaVenc) {
		this.diaVenc = diaVenc;
	}

	public Calendar getDataInicioCobr() {
		return dataInicioCobr;
	}

	public void setDataInicioCobr(Calendar dataInicioCobr) {
		this.dataInicioCobr = dataInicioCobr;
	}
}
