package br.com.sousuperseguro.entities.recusadas;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Calendar;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import br.com.sousuperseguro.enums.CancelamentoDoAssociado;
import br.com.sousuperseguro.enums.Categoria;
import br.com.sousuperseguro.enums.EstadoCivil;
import br.com.sousuperseguro.enums.Parentesco;
import br.com.sousuperseguro.enums.Sexo;
import br.com.sousuperseguro.enums.Status;

@Entity
@Table(name="recebido_sou_super_seguro_recusada")
public class RecebidoSouSuperSeguroRecusada implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8020907625971157815L;
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private BigInteger id;
	
	@Column(name="c_tipo_registro", nullable=true)
	private String cTipoRegistro;
	
	@Column(name="c_origem", nullable=true)
	private String cOrigem;
	
	@Column(name="c_destino", nullable=true)
	private String cDestino;
	
	@Column(name="dt_arquivo", nullable=true)
	private Calendar dtArquivo;
	
	@Column(name="nr_sequencial", nullable=true)
	private BigInteger nrSequencial;
	
	@Column(name="filler", nullable=true)
	private String filler;
	
	@Column(name="contrato", nullable=true)
	private String contrato;
	
	@Enumerated(EnumType.STRING)
	@Column(name="c_status", nullable=true)
	private Status cStatus;
	
	@Enumerated(EnumType.STRING)
	@Column(name="c_categoria", nullable=true)
	private Categoria cCategoria;
	
	@Column(name="nro_proposta", nullable=true)
	private String nroProposta;
	
	@Column(name="cd_matricula", nullable=true)
	private String cdMatricula;
	
	@Enumerated(EnumType.STRING)
	@Column(name="c_parentesco", nullable=true)
	private Parentesco cParentesco;
	
	@Column(name="nome", nullable=true)
	private String nome;
	
	@Column(name="dt_nascimento", nullable=true)
	private Calendar dtNascimento;
	
	@Enumerated(EnumType.STRING)
	@Column(name="c_sexo", nullable=true)
	private Sexo cSexo;
	
	@Column(name="cpf", nullable=true)
	private String cpf;
	
	@Column(name="pis_pasep_nit", nullable=true)
	private String pisPasepNit;
	
	@Column(name="cns", nullable=true)
	private String cns;
	
	@Column(name="nome_mae", nullable=true)
	private String nomeMae;
	
	@Column(name="dnv", nullable=true)
	private String dnv;
	
	@Enumerated(EnumType.STRING)
	@Column(name="c_est_civil", nullable=true)
	private EstadoCivil cEstCivil;
	
	@Column(name="r_logradores", nullable=true)
	private String rLogradores;
	
	@Column(name="r_numero_res", nullable=true)
	private String rNumeroRes;
	
	@Column(name="comp_res", nullable=true)
	private String compRes;
	
	@Column(name="bairro_res", nullable=true)
	private String bairroRes;
	
	@Column(name="cidade_res", nullable=true)
	private String cidadeRes;
	
	@Column(name="id_cidade_res", nullable=true)
	private BigInteger idCidadeRes;
	
	@Column(name="uf_res", nullable=true)
	private String ufRes;
	
	@Column(name="cep_res", nullable=true)
	private String cepRes;
	
	@Column(name="referencia_res", nullable=true)
	private String referenciaRes;
	
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
	
	@Column(name="c_fone2", nullable=true, insertable=false, updatable=false)
	private String cFone2;
	
	@Column(name="d_fone3", nullable=true)
	private String dFone3;
	
	@Column(name="n_fone3", nullable=true)
	private String nFone3;
	
	@Column(name="c_fone2", nullable=true)
	private String cFone3;
	
	@Column(name="email", nullable=true)
	private String email;
	
	@Column(name="c_plano", nullable=true)
	private String cPlano;
	
	@Column(name="dt_adesao", nullable=true)
	private Calendar dtAdesao;
	
	@Column(name="dt_cancelamento", nullable=true)
	private Calendar dtCancelamento;
	
	@Enumerated(EnumType.STRING)
	@Column(name="c_motivo_can", nullable=true)
	private CancelamentoDoAssociado cMotivoCan;

	@OneToOne(fetch=FetchType.EAGER,cascade = CascadeType.ALL)
	@JoinColumn(name = "id_recebido_sou_super_seguro_cobranca", referencedColumnName = "id", nullable=true)
	private RecebidoSouSuperSeguroCobrancaRecusada recebidoSouSuperSeguroCobranca;
	
	@OneToOne(fetch=FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "id_recebido_sou_super_seguro_pagamento_mensalidade", referencedColumnName = "id", nullable=true)
	private RecebidoSouSuperSeguroPagamentoMensalidadeRecusada recebidoSouSuperSeguroPagamentoMensalidade;
	
	@OneToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinColumn(name = "id_recebido_sou_super_seguro_dados_finais", referencedColumnName = "id", nullable=true)
	private RecebidoSouSuperSeguroDadosFinaisRecusada recebidoSouSuperSeguroDadosFinais;

	@Column(name="recebido_bradesco", nullable=true)
	private boolean recebidoBradesco;
	
	@Column(name="codigo_erro", nullable=true)
	private String codigoErro;
	
	
	public BigInteger getId() {
		return id;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}

	public String getcTipoRegistro() {
		return cTipoRegistro;
	}

	public void setcTipoRegistro(String cTipoRegistro) {
		this.cTipoRegistro = cTipoRegistro;
	}

	public String getcOrigem() {
		return cOrigem;
	}

	public void setcOrigem(String cOrigem) {
		this.cOrigem = cOrigem;
	}

	public String getcDestino() {
		return cDestino;
	}

	public void setcDestino(String cDestino) {
		this.cDestino = cDestino;
	}

	public Calendar getDtArquivo() {
		return dtArquivo;
	}

	public void setDtArquivo(Calendar dtArquivo) {
		this.dtArquivo = dtArquivo;
	}

	public BigInteger getNrSequencial() {
		return nrSequencial;
	}

	public void setNrSequencial(BigInteger nrSequencial) {
		this.nrSequencial = nrSequencial;
	}

	public String getFiller() {
		return filler;
	}

	public void setFiller(String filler) {
		this.filler = filler;
	}

	public String getContrato() {
		return contrato;
	}

	public void setContrato(String contrato) {
		this.contrato = contrato;
	}

	public Status getcStatus() {
		return cStatus;
	}

	public void setcStatus(Status cStatus) {
		this.cStatus = cStatus;
	}

	public Categoria getcCategoria() {
		return cCategoria;
	}

	public void setcCategoria(Categoria cCategoria) {
		this.cCategoria = cCategoria;
	}

	public String getNroProposta() {
		return nroProposta;
	}

	public void setNroProposta(String nroProposta) {
		this.nroProposta = nroProposta;
	}

	public String getCdMatricula() {
		return cdMatricula;
	}

	public void setCdMatricula(String cdMatricula) {
		this.cdMatricula = cdMatricula;
	}

	public Parentesco getcParentesco() {
		return cParentesco;
	}

	public void setcParentesco(Parentesco cParentesco) {
		this.cParentesco = cParentesco;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Calendar getDtNascimento() {
		return dtNascimento;
	}

	public void setDtNascimento(Calendar dtNascimento) {
		this.dtNascimento = dtNascimento;
	}

	public Sexo getcSexo() {
		return cSexo;
	}

	public void setcSexo(Sexo cSexo) {
		this.cSexo = cSexo;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getPisPasepNit() {
		return pisPasepNit;
	}

	public void setPisPasepNit(String pisPasepNit) {
		this.pisPasepNit = pisPasepNit;
	}

	public String getCns() {
		return cns;
	}

	public void setCns(String cns) {
		this.cns = cns;
	}

	public String getNomeMae() {
		return nomeMae;
	}

	public void setNomeMae(String nomeMae) {
		this.nomeMae = nomeMae;
	}

	public String getDnv() {
		return dnv;
	}

	public void setDnv(String dnv) {
		this.dnv = dnv;
	}

	public EstadoCivil getcEstCivil() {
		return cEstCivil;
	}

	public void setcEstCivil(EstadoCivil cEstCivil) {
		this.cEstCivil = cEstCivil;
	}

	public String getrLogradores() {
		return rLogradores;
	}

	public void setrLogradores(String rLogradores) {
		this.rLogradores = rLogradores;
	}

	public String getrNumeroRes() {
		return rNumeroRes;
	}

	public void setrNumeroRes(String rNumeroRes) {
		this.rNumeroRes = rNumeroRes;
	}

	public String getCompRes() {
		return compRes;
	}

	public void setCompRes(String compRes) {
		this.compRes = compRes;
	}

	public String getBairroRes() {
		return bairroRes;
	}

	public void setBairroRes(String bairroRes) {
		this.bairroRes = bairroRes;
	}

	public String getCidadeRes() {
		return cidadeRes;
	}

	public void setCidadeRes(String cidadeRes) {
		this.cidadeRes = cidadeRes;
	}

	public BigInteger getIdCidadeRes() {
		return idCidadeRes;
	}

	public void setIdCidadeRes(BigInteger idCidadeRes) {
		this.idCidadeRes = idCidadeRes;
	}

	public String getUfRes() {
		return ufRes;
	}

	public void setUfRes(String ufRes) {
		this.ufRes = ufRes;
	}

	public String getCepRes() {
		return cepRes;
	}

	public void setCepRes(String cepRes) {
		this.cepRes = cepRes;
	}

	public String getReferenciaRes() {
		return referenciaRes;
	}

	public void setReferenciaRes(String referenciaRes) {
		this.referenciaRes = referenciaRes;
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

	public String getcPlano() {
		return cPlano;
	}

	public void setcPlano(String cPlano) {
		this.cPlano = cPlano;
	}

	public Calendar getDtAdesao() {
		return dtAdesao;
	}

	public void setDtAdesao(Calendar dtAdesao) {
		this.dtAdesao = dtAdesao;
	}

	public Calendar getDtCancelamento() {
		return dtCancelamento;
	}

	public void setDtCancelamento(Calendar dtCancelamento) {
		this.dtCancelamento = dtCancelamento;
	}

	public CancelamentoDoAssociado getcMotivoCan() {
		return cMotivoCan;
	}

	public void setcMotivoCan(CancelamentoDoAssociado cMotivoCan) {
		this.cMotivoCan = cMotivoCan;
	}

	public RecebidoSouSuperSeguroCobrancaRecusada getRecebidoSouSuperSeguroCobranca() {
		return recebidoSouSuperSeguroCobranca;
	}

	public void setRecebidoSouSuperSeguroCobranca(
			RecebidoSouSuperSeguroCobrancaRecusada recebidoSouSuperSeguroCobranca) {
		this.recebidoSouSuperSeguroCobranca = recebidoSouSuperSeguroCobranca;
	}

	public RecebidoSouSuperSeguroPagamentoMensalidadeRecusada getRecebidoSouSuperSeguroPagamentoMensalidade() {
		return recebidoSouSuperSeguroPagamentoMensalidade;
	}

	public void setRecebidoSouSuperSeguroPagamentoMensalidade(
			RecebidoSouSuperSeguroPagamentoMensalidadeRecusada recebidoSouSuperSeguroPagamentoMensalidade) {
		this.recebidoSouSuperSeguroPagamentoMensalidade = recebidoSouSuperSeguroPagamentoMensalidade;
	}

	public RecebidoSouSuperSeguroDadosFinaisRecusada getRecebidoSouSuperSeguroDadosFinais() {
		return recebidoSouSuperSeguroDadosFinais;
	}

	public void setRecebidoSouSuperSeguroDadosFinais(
			RecebidoSouSuperSeguroDadosFinaisRecusada recebidoSouSuperSeguroDadosFinais) {
		this.recebidoSouSuperSeguroDadosFinais = recebidoSouSuperSeguroDadosFinais;
	}

	public boolean isRecebidoBradesco() {
		return recebidoBradesco;
	}

	public void setRecebidoBradesco(boolean recebidoBradesco) {
		this.recebidoBradesco = recebidoBradesco;
	}

	public String getCodigoErro() {
		return codigoErro;
	}

	public void setCodigoErro(String codigoErro) {
		this.codigoErro = codigoErro;
	}
}
