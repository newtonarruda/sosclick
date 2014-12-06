package br.com.fiap.sosclick.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import br.com.fiap.sosclick.vo.PlanoSaude;

@SuppressWarnings("unused")
public class PlanoSaudeDAO extends DataSource {

	private SQLiteStatement insertStmt;
	private static final String INSERT = "insert into " + TABLE_PLANO_SAUDE
			+ "(id_usuario, convenio, plano, telefone_convenio, descricao, flag_ativo) "
			+ "values (?, ?, ?, ?, ?, ?)";

	private SQLiteStatement updateStmt;
	private static final String UPDATE = "update "
			+ TABLE_PLANO_SAUDE
			+ " set convenio = ?, plano = ?, telefone_convenio = ?, descricao = ?" 
			+ " where id_plano_saude = ? and id_usuario = ?";

	private SQLiteStatement deleteStmt;
	private static final String DELETE = "delete from " + TABLE_PLANO_SAUDE
			+ " where id_plano_saude = ? and id_usuario = ?";

	private SQLiteStatement selectStmt;
	private static final String SELECT = "select id_plano_saude, id_usuario, " +
			"convenio, plano, telefone_convenio, descricao, flag_ativo" +
			" from " + TABLE_PLANO_SAUDE + " where cast(id_plano_saude as text) = ? and cast(id_usuario as text) = ?";

	private static final String SELECT_ATIVO = "select id_plano_saude, id_usuario, " +
			"convenio, plano, telefone_convenio, descricao, flag_ativo" +
			" from " + TABLE_PLANO_SAUDE + " where flag_ativo = 'S'";

	public PlanoSaudeDAO(Context context) {
		super(context);
		this.insertStmt = this.db.compileStatement(INSERT);
		this.updateStmt = this.db.compileStatement(UPDATE);
		this.deleteStmt = this.db.compileStatement(DELETE);
		this.selectStmt = this.db.compileStatement(SELECT);
	}

	public long insert(PlanoSaude planoSaude) {
		this.insertStmt.bindLong(1, planoSaude.getIdUsuario());
		this.insertStmt.bindString(2, planoSaude.getConvenio());
		this.insertStmt.bindString(3, planoSaude.getPlano());
		this.insertStmt.bindString(4, planoSaude.getTelefone());
		this.insertStmt.bindString(5, planoSaude.getDescricao());
		this.insertStmt.bindString(6, planoSaude.isFlagAtivo() ? "S" : "N");
		
		return this.insertStmt.executeInsert();
	}

	public long update(PlanoSaude planoSaude) {
		this.updateStmt.bindString(1, planoSaude.getConvenio());
		this.updateStmt.bindString(2, planoSaude.getPlano());
		this.updateStmt.bindString(3, planoSaude.getTelefone());
		this.updateStmt.bindString(4, planoSaude.getDescricao());
		this.updateStmt.bindString(5, planoSaude.isFlagAtivo() ? "S" : "N");
		this.updateStmt.bindLong(6, planoSaude.getIdPlanoSaude());
		this.updateStmt.bindLong(7, planoSaude.getIdUsuario());
		
		return this.updateStmt.executeUpdateDelete();
	}

	public PlanoSaude select(PlanoSaude planoSaude) {
		PlanoSaude resultado = null;

		Cursor c = db.rawQuery(SELECT,
				new String[] { planoSaude.getIdPlanoSaude().toString(), planoSaude.getIdUsuario().toString() });

		if (c.moveToFirst()) {
			resultado = new PlanoSaude(
					c.getInt(c.getColumnIndex("id_plano_saude")), 
					c.getInt(c.getColumnIndex("id_usuario")), 
					c.getString(c.getColumnIndex("convenio")), 
					c.getString(c.getColumnIndex("plano")), 
					c.getString(c.getColumnIndex("telefone_convenio")), 
					c.getString(c.getColumnIndex("descricao")),
					c.getString(c.getColumnIndex("flag_ativo")).equalsIgnoreCase("S") ? true : false);
		}

		c.close();

		return resultado;
	}

	public PlanoSaude selectAtivo(PlanoSaude planoSaude) {
		PlanoSaude resultado = null;

		Cursor c = db.rawQuery(SELECT_ATIVO,
				new String[] { });

		if (c.moveToFirst()) {
			resultado = new PlanoSaude(
					c.getInt(c.getColumnIndex("id_plano_saude")), 
					c.getInt(c.getColumnIndex("id_usuario")), 
					c.getString(c.getColumnIndex("convenio")), 
					c.getString(c.getColumnIndex("plano")), 
					c.getString(c.getColumnIndex("telefone_convenio")), 
					c.getString(c.getColumnIndex("descricao")),
					c.getString(c.getColumnIndex("flag_ativo")).equalsIgnoreCase("S") ? true : false);
		}

		c.close();

		return resultado;
	}

	public boolean isOpenDb() {
		return db.isOpen();
	}
}
