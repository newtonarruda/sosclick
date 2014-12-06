package br.com.fiap.sosclick.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DataSource extends SQLiteOpenHelper {

	protected static final String DATABASE_SOS = "sosclick.db";
	protected static final String TABLE_USUARIO = "tb_usuario";
	protected static final String TABLE_PLANO_SAUDE = "tb_plano_saude";
	protected static final String TABLE_SOCORRISTA = "tb_socorrista";
	private static final int DATABASE_VERSION = 2;

	Context context;
	SQLiteDatabase db;

	public DataSource(Context context) {

		super(context, DATABASE_SOS, null, DATABASE_VERSION);
		this.context = context;
		try {
			this.db = getWritableDatabase();
		} catch (Exception e) {
			e.printStackTrace();
		}
		// onUpgrade(db, 2, DATABASE_VERSION);
		onUpgrade(db, 2, 1);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("CREATE TABLE " + TABLE_USUARIO
				+ " (id_usuario INTEGER PRIMARY KEY AUTOINCREMENT, "
				+ "nome TEXT, " + "usuario TEXT, " + "senha TEXT, "
				+ "email TEXT, " + "telefone TEXT, " + "data_nascimento TEXT, "
				+ "flag_alergia VARCHAR, " + "descricao_alergia TEXT, "
				+ "flag_medicacao VARCHAR, " + "descricao_medicacao TEXT, "
				+ "flag_diabetes VARCHAR, " + "bit_pressao INTEGER, "
				+ "descricao_usuario TEXT, " + "flag_ativo VARCHAR)");

		db.execSQL("CREATE TABLE " + TABLE_PLANO_SAUDE
				+ " (id_plano_saude INTEGER PRIMARY KEY AUTOINCREMENT, "
				+ "id_usuario INTEGER, "
				+ "convenio TEXT, " + "plano TEXT, " + "telefone_convenio TEXT, "
				+ "descricao TEXT, " + "flag_ativo VARCHAR)");
		
		db.execSQL("CREATE TABLE " + TABLE_SOCORRISTA
				+ " (id_socorrista INTEGER PRIMARY KEY AUTOINCREMENT, "
				+ "id_usuario INTEGER, "
				+ "nome TEXT, " + "email TEXT, " + "telefone TEXT, " 
				+ "flag_ativo VARCHAR)");
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.w("SOSClick",
				"Upgrading database, this will drop tables and recreate.");
		if (newVersion > oldVersion) {
			db.execSQL("DROP TABLE IF EXISTS " + TABLE_PLANO_SAUDE);
			db.execSQL("DROP TABLE IF EXISTS " + TABLE_SOCORRISTA);
			db.execSQL("DROP TABLE IF EXISTS " + TABLE_USUARIO);
			onCreate(db);
		}
	}

	public void close() {
		this.db.close();
	}

}
