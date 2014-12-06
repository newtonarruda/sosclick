package br.com.fiap.sosclick.vo;

import java.io.Serializable;

public class PlanoSaude implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2130149166894824524L;
	private Integer idPlanoSaude;
	private Integer idUsuario;
	private String convenio;
	private String plano;
	private String telefone;
	private String descricao;
	private boolean flagAtivo;

	public PlanoSaude(){}
	
	public PlanoSaude(Integer idPlanoSaude, Integer idUsuario, String convenio,
			String plano, String telefone, String descricao, boolean flagAtivo) {
		super();
		this.idPlanoSaude = idPlanoSaude;
		this.idUsuario = idUsuario;
		this.convenio = convenio;
		this.plano = plano;
		this.telefone = telefone;
		this.descricao = descricao;
		this.flagAtivo = flagAtivo;
	}

	public Integer getIdPlanoSaude() {
		return idPlanoSaude;
	}

	public void setIdPlanoSaude(Integer idPlanoSaude) {
		this.idPlanoSaude = idPlanoSaude;
	}

	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getConvenio() {
		return convenio;
	}

	public void setConvenio(String convenio) {
		this.convenio = convenio;
	}

	public String getPlano() {
		return plano;
	}

	public void setPlano(String plano) {
		this.plano = plano;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public boolean isFlagAtivo() {
		return flagAtivo;
	}

	public void setFlagAtivo(boolean flagAtivo) {
		this.flagAtivo = flagAtivo;
	}

}
