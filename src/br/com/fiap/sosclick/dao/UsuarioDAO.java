package br.com.fiap.sosclick.dao;

import java.text.ParseException;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import br.com.fiap.sosclick.util.Utils;
import br.com.fiap.sosclick.vo.Usuario;

@SuppressWarnings("unused")
public class UsuarioDAO extends DataSource {

	private SQLiteStatement insertStmt;
	private static final String INSERT = "insert into " + TABLE_USUARIO
			+ "(nome, usuario, senha, email, telefone, "
			+ "data_nascimento, flag_alergia, descricao_alergia, "
			+ "flag_medicacao, descricao_medicacao, flag_diabetes, "
			+ "bit_pressao, descricao_usuario, flag_ativo) "
			+ "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

	private SQLiteStatement updateStmt;
	private static final String UPDATE = "update "
			+ TABLE_USUARIO
			+ " set nome = ?, usuario = ?, senha = ?, email = ?, telefone = ?, "
			+ "data_nascimento = ?, flag_alergia = ?, descricao_alergia = ?, "
			+ "flag_medicacao = ?, descricao_medicacao = ?, flag_diabetes = ?, "
			+ "bit_pressao = ?, descricao_usuario = ?, flag_ativo = ? where id_usuario = ?";

	private SQLiteStatement deleteStmt;
	private static final String DELETE = "delete from " + TABLE_USUARIO
			+ " where id_usuario = ?";

	private SQLiteStatement selectLoginStmt;
	private static final String SELECT_LOGIN = "select id_usuario, nome, usuario, senha, email, telefone, "
			+ "data_nascimento, flag_alergia, descricao_alergia, "
			+ "flag_medicacao, descricao_medicacao, flag_diabetes, "
			+ "bit_pressao, descricao_usuario, flag_ativo from "
			+ TABLE_USUARIO + " where usuario = ? and senha = ?";

	private static final String SELECT_USUARIO_ATIVO = "select id_usuario, nome, usuario, senha, email, telefone, "
			+ "data_nascimento, flag_alergia, descricao_alergia, "
			+ "flag_medicacao, descricao_medicacao, flag_diabetes, "
			+ "bit_pressao, descricao_usuario, flag_ativo from "
			+ TABLE_USUARIO + " where flag_ativo = ?";

	public UsuarioDAO(Context context) {
		super(context);
		this.insertStmt = this.db.compileStatement(INSERT);
		this.updateStmt = this.db.compileStatement(UPDATE);
		this.deleteStmt = this.db.compileStatement(DELETE);
		this.selectLoginStmt = this.db.compileStatement(SELECT_LOGIN);
	}

	public long insert(Usuario usuario) {
		this.insertStmt.bindString(1, usuario.getNome());
		this.insertStmt.bindString(2, usuario.getUsuario());
		this.insertStmt.bindString(3, usuario.getSenha());
		this.insertStmt.bindString(4, usuario.getEmail());
		this.insertStmt.bindString(6, usuario.getTelefone());
		this.insertStmt.bindString(7,
				Utils.dateToString(usuario.getDataNascimento()));
		this.insertStmt.bindString(8, usuario.isFlagAlergia() ? "S" : "N");
		this.insertStmt.bindString(9, usuario.getDescricaoAlergia());
		this.insertStmt.bindString(10, usuario.isFlagMedicacao() ? "S" : "N");
		this.insertStmt.bindString(11, usuario.getDescricaoMedicacao());
		this.insertStmt.bindString(12, usuario.isFlagDiabetes() ? "S" : "N");
		this.insertStmt.bindLong(13, usuario.getBitPressao());
		this.insertStmt.bindString(14, usuario.getDescricaoUsuario());
		this.insertStmt.bindString(15, usuario.isFlagAtivo() ? "S" : "N");
		return this.insertStmt.executeInsert();
	}

	public Usuario selectLogin(Usuario usuario) {
		Usuario resultado = null;

		Cursor c = db.rawQuery(SELECT_LOGIN,
				new String[] { usuario.getUsuario(), usuario.getSenha() });

		try {
			if (c.moveToFirst()) {
				resultado = new Usuario(
						c.getInt(c.getColumnIndex("id_usuario")), c.getString(c
								.getColumnIndex("nome")), c.getString(c
								.getColumnIndex("usuario")), c.getString(c
								.getColumnIndex("senha")), c.getString(c
								.getColumnIndex("email")), c.getString(c
								.getColumnIndex("telefone")),
						Utils.stringToDate(c.getString(c
								.getColumnIndex("data_nascimento"))), c
								.getString(c.getColumnIndex("flag_alergia"))
								.equalsIgnoreCase("S") ? true : false,
						c.getString(c.getColumnIndex("descricao_alergia")), c
								.getString(c.getColumnIndex("flag_medicacao"))
								.equalsIgnoreCase("S") ? true : false,
						c.getString(c.getColumnIndex("descricao_medicacao")), c
								.getString(c.getColumnIndex("flag_diabetes"))
								.equalsIgnoreCase("S") ? true : false,
						c.getInt(c.getColumnIndex("bit_pressao")),
						c.getString(c.getColumnIndex("descricao_usuario")), c
								.getString(c.getColumnIndex("flag_ativo"))
								.equalsIgnoreCase("S") ? true : false);
			}
		} catch (ParseException e) {
			resultado = null;
		}
		c.close();

		return resultado;
	}

	public Usuario selectUsuarioAtivo() {

		Usuario resultado = null;

		Cursor c = db.rawQuery(SELECT_USUARIO_ATIVO, new String[] { "S" });

		try {
			if (c.moveToFirst()) {
				resultado = new Usuario(
						c.getInt(c.getColumnIndex("id_usuario")), c.getString(c
								.getColumnIndex("nome")), c.getString(c
								.getColumnIndex("usuario")), c.getString(c
								.getColumnIndex("senha")), c.getString(c
								.getColumnIndex("email")), c.getString(c
								.getColumnIndex("telefone")),
						Utils.stringToDate(c.getString(c
								.getColumnIndex("data_nascimento"))), c
								.getString(c.getColumnIndex("flag_alergia"))
								.equalsIgnoreCase("S") ? true : false,
						c.getString(c.getColumnIndex("descricao_alergia")), c
								.getString(c.getColumnIndex("flag_medicacao"))
								.equalsIgnoreCase("S") ? true : false,
						c.getString(c.getColumnIndex("descricao_medicacao")), c
								.getString(c.getColumnIndex("flag_diabetes"))
								.equalsIgnoreCase("S") ? true : false,
						c.getInt(c.getColumnIndex("bit_pressao")),
						c.getString(c.getColumnIndex("descricao_usuario")), c
								.getString(c.getColumnIndex("flag_ativo"))
								.equalsIgnoreCase("S") ? true : false);
			}
		} catch (ParseException e) {
			resultado = null;
		}
		c.close();

		return resultado;
	}

	public boolean isOpenDb() {
		return db.isOpen();
	}
}
