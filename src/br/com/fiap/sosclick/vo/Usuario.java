package br.com.fiap.sosclick.vo;

import java.io.Serializable;
import java.util.Date;

import br.com.fiap.sosclick.util.Utils;

public class Usuario implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9134036759982277819L;
	private Integer idUsuario;
	private String nome;
	private String usuario;
	private String senha;
	private String email;
	private String telefone;
	private Date dataNascimento;
	private boolean flagAlergia;
	private String descricaoAlergia;
	private boolean flagMedicacao;
	private String descricaoMedicacao;
	private boolean flagDiabetes;
	private Integer bitPressao;
	private String descricaoUsuario;
	private boolean flagAtivo;

	public Usuario(Integer idUsuario, String nome, String usuario,
			String senha, String email, String telefone, Date dataNascimento, 
			boolean flagAlergia, String descricaoAlergia, 
			boolean flagMedicacao, String descricaoMedicacao, 
			boolean flagDiabetes, Integer bitPressao, 
			String descricaoUsuario, boolean flagAtivo) {
		super();
		this.idUsuario = idUsuario;
		this.nome = nome;
		this.usuario = usuario;
		this.senha = senha;
		this.email = email;
		this.telefone = telefone;
		this.dataNascimento = dataNascimento;
		this.flagAlergia = flagAlergia;
		this.descricaoAlergia = descricaoAlergia;
		this.flagMedicacao = flagMedicacao;
		this.descricaoMedicacao = descricaoMedicacao;
		this.flagDiabetes = flagDiabetes;
		this.bitPressao = bitPressao;
		this.descricaoUsuario = descricaoUsuario;
		this.flagAtivo = flagAtivo;
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

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
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

	public String getDataNascimentoStr(String formato) {
		return Utils.dateToString(dataNascimento, formato);
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public boolean isFlagAlergia() {
		return flagAlergia;
	}

	public void setFlagAlergia(boolean flagAlergia) {
		this.flagAlergia = flagAlergia;
	}

	public String getDescricaoAlergia() {
		return descricaoAlergia;
	}

	public void setDescricaoAlergia(String descricaoAlergia) {
		this.descricaoAlergia = descricaoAlergia;
	}

	public boolean isFlagMedicacao() {
		return flagMedicacao;
	}

	public void setFlagMedicacao(boolean flagMedicacao) {
		this.flagMedicacao = flagMedicacao;
	}

	public String getDescricaoMedicacao() {
		return descricaoMedicacao;
	}

	public void setDescricaoMedicacao(String descricaoMedicacao) {
		this.descricaoMedicacao = descricaoMedicacao;
	}

	public boolean isFlagDiabetes() {
		return flagDiabetes;
	}

	public void setFlagDiabetes(boolean flagDiabetes) {
		this.flagDiabetes = flagDiabetes;
	}

	public Integer getBitPressao() {
		return bitPressao;
	}

	public void setBitPressao(Integer bitPressao) {
		this.bitPressao = bitPressao;
	}

	public String getDescricaoUsuario() {
		return descricaoUsuario;
	}

	public void setDescricaoUsuario(String descricaoUsuario) {
		this.descricaoUsuario = descricaoUsuario;
	}

	public boolean isFlagAtivo() {
		return flagAtivo;
	}

	public void setFlagAtivo(boolean flagAtivo) {
		this.flagAtivo = flagAtivo;
	}

}
