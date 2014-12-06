package br.com.fiap.sosclick.vo;

import java.io.Serializable;

public class Socorrista implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4563626222470900361L;
	private Integer idSocorrista;
	private Integer idUsuario;
	private String nome;
	private String email;
	private String telefone;
	private boolean flagAtivo;

	public Socorrista(){}
	
	public Socorrista(Integer idSocorrista, Integer idUsuario, String nome,
			String email, String telefone, boolean flagAtivo) {
		super();
		this.idSocorrista = idSocorrista;
		this.idUsuario = idUsuario;
		this.nome = nome;
		this.email = email;
		this.telefone = telefone;
		this.flagAtivo = flagAtivo;
	}

	public Integer getIdSocorrista() {
		return idSocorrista;
	}

	public void setIdSocorrista(Integer idSocorrista) {
		this.idSocorrista = idSocorrista;
	}

	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public boolean isFlagAtivo() {
		return flagAtivo;
	}

	public void setFlagAtivo(boolean flagAtivo) {
		this.flagAtivo = flagAtivo;
	}

}
