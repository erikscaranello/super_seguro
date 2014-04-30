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
import javax.validation.constraints.NotNull;

@Entity
@Table(name="users")
public class Users implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private BigInteger id;
	
	@NotNull
	@Column(length=25, unique = true)
	private String username;
	
	@Column(length=140, nullable=true)
	private String password;
	
	@OneToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinColumn(name = "id_role", referencedColumnName = "id", updatable=true)
	private Role role;

	@OneToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinColumn(name = "id_infos_pessoais", referencedColumnName = "id" , updatable=true)
	private InfosPessoais infosPessoais;

	private Boolean enabled;
	
	private Boolean repassword;

	public BigInteger getId() {
		return id;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public InfosPessoais getInfosPessoais() {
		return infosPessoais;
	}

	public void setInfosPessoais(InfosPessoais infosPessoais) {
		this.infosPessoais = infosPessoais;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public Boolean getRepassword() {
		return repassword;
	}

	public void setRepassword(Boolean repassword) {
		this.repassword = repassword;
	}
	
}
