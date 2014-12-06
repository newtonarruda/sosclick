package br.com.fiap.sosclick.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import br.com.fiap.sosclick.vo.Socorrista;

@SuppressWarnings("unused")
public class SocorristaDAO extends DataSource {

	private SQLiteStatement insertStmt;
	private static final String INSERT = "insert into " + TABLE_SOCORRISTA
			+ "(id_usuario, nome, email, telefone, flag_ativo) "
			+ "values (?, ?, ?, ?, ?)";

	private SQLiteStatement updateStmt;
	private static final String UPDATE = "update "
			+ TABLE_SOCORRISTA
			+ " set nome = ?, email = ?, telefone = ?" 
			+ " where id_socorrista = ? and id_usuario = ?";

	private SQLiteStatement deleteStmt;
	private static final String DELETE = "delete from " + TABLE_SOCORRISTA
			+ " where id_socorrista = ? and id_usuario = ?";

	private SQLiteStatement selectStmt;
	private static final String SELECT = "select id_socorrista, id_usuario, " +
			"nome, email, telefone, flag_ativo" +
			" from " + TABLE_SOCORRISTA + " where cast(id_socorrista as text) = ? and cast(id_usuario as text) = ?";

	private static final String SELECT_ATIVO = "select id_socorrista, id_usuario, " +
			"nome, email, telefone, flag_ativo" +
			" from " + TABLE_SOCORRISTA + " where flag_ativo = ?";

	public SocorristaDAO(Context context) {
		super(context);
		this.insertStmt = this.db.compileStatement(INSERT);
		this.updateStmt = this.db.compileStatement(UPDATE);
		this.deleteStmt = this.db.compileStatement(DELETE);
		this.selectStmt = this.db.compileStatement(SELECT);
	}

	public long insert(Socorrista socorrista) {
		this.insertStmt.bindLong(1, socorrista.getIdUsuario());
		this.insertStmt.bindString(2, socorrista.getNome());
		this.insertStmt.bindString(3, socorrista.getEmail());
		this.insertStmt.bindString(4, socorrista.getTelefone());
		this.insertStmt.bindString(5, socorrista.isFlagAtivo() ? "S" : "N");
		
		return this.insertStmt.executeInsert();
	}

	public long update(Socorrista socorrista) {
		this.updateStmt.bindString(1, socorrista.getNome());
		this.updateStmt.bindString(2, socorrista.getEmail());
		this.updateStmt.bindString(3, socorrista.getTelefone());
		this.updateStmt.bindLong(4, socorrista.getIdSocorrista());
		this.updateStmt.bindLong(5, socorrista.getIdUsuario());
		
		return this.updateStmt.executeUpdateDelete();
	}

	public Socorrista select(Socorrista socorrista) {
		Socorrista resultado = null;

		Cursor c = db.rawQuery(SELECT,
				new String[] { socorrista.getIdSocorrista().toString(), socorrista.getIdUsuario().toString() });

		if (c.moveToFirst()) {
			resultado = new Socorrista(
					c.getInt(c.getColumnIndex("id_socorrista")), 
					c.getInt(c.getColumnIndex("id_usuario")), 
					c.getString(c.getColumnIndex("nome")), 
					c.getString(c.getColumnIndex("email")), 
					c.getString(c.getColumnIndex("telefone")), 
					c.getString(c.getColumnIndex("flag_ativo")).equalsIgnoreCase("S") ? true : false);
		}

		c.close();

		return resultado;
	}

	public Socorrista selectAtivo() {
		Socorrista resultado = null;

		Cursor c = db.rawQuery(SELECT_ATIVO,
				new String[] { "S" });

		if (c.moveToFirst()) {
			resultado = new Socorrista(
					c.getInt(c.getColumnIndex("id_socorrista")), 
					c.getInt(c.getColumnIndex("id_usuario")), 
					c.getString(c.getColumnIndex("nome")), 
					c.getString(c.getColumnIndex("email")), 
					c.getString(c.getColumnIndex("telefone")), 
					c.getString(c.getColumnIndex("flag_ativo")).equalsIgnoreCase("S") ? true : false);
		}

		c.close();

		return resultado;
	}

	public boolean isOpenDb() {
		return db.isOpen();
	}
}
