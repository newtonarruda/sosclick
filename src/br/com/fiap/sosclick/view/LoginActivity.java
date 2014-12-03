package br.com.fiap.sosclick.view;

import java.util.Calendar;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import br.com.fiap.sosclick.R;
import br.com.fiap.sosclick.dao.UsuarioDAO;
import br.com.fiap.sosclick.util.LogStmt;
import br.com.fiap.sosclick.vo.Usuario;

public class LoginActivity extends Activity {

	Intent intentMyService;
	ComponentName service;

	EditText etUsuario;
	EditText etSenha;
	Button btEntrar;
	Button btLogin;
	Button btNovoUsuario;

	UsuarioDAO dao;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

		etUsuario = (EditText) findViewById(R.id.etUsuario);
		etSenha = (EditText) findViewById(R.id.etSenha);

		btEntrar = (Button) findViewById(R.id.btLogin);
		btEntrar.setOnClickListener(new ClickerEntrar());

		btLogin = (Button) findViewById(R.id.btEntrar);
		btLogin.setOnClickListener(new ClickerEntrar());

		btNovoUsuario = (Button) findViewById(R.id.btNovoUsuario);
		btNovoUsuario.setOnClickListener(new ClickerEntrar());

		if (dao == null) {
			dao = new UsuarioDAO(this);
		}

		if (dao.isOpenDb() && dao.selectUsuarioAtivo() == null) {
			btNovoUsuario.setEnabled(true);
		} else {
			btNovoUsuario.setEnabled(false);
		}

		executaCargaInicial();

	}

	private class ClickerEntrar implements OnClickListener {

		@Override
		public void onClick(View view) {

			switch (view.getId()) {

			case R.id.btEntrar:
				// Tela MenuActivity
				Intent intentFacebook = new Intent(getBaseContext(),
						MenuActivity.class);
				startActivity(intentFacebook);

				Log.d(LogStmt.CATEGORIA_LOGIN_ACTIVITY,
						"LoginActivity.ClickerEntrar.onClick: Encerrando o Login,"
								+ " chamando MenuActivity");
				break;

			case R.id.btLogin:
				// Tela MenuActivity
				// Intent intentLogin = new Intent(getBaseContext(),
				// MenuActivity.class);
				// startActivity(intentLogin);

				/*
				 * Usuario usuario = null; try { usuario = dao.selectLogin(new
				 * Usuario(null, null, etUsuario.getText().toString(),
				 * etSenha.getText() .toString(), null, null, null, false, null,
				 * false, null, false, null, null, false)); } catch
				 * (ParseException e) { // TODO Implementar tratamento de erro
				 * e.printStackTrace(); }
				 * 
				 * if (usuario != null) { if (usuario.isFlagAtivo()) {
				 * 
				 * Intent intentLoginToMenu = new Intent( LoginActivity.this,
				 * MenuActivity.class);
				 * 
				 * Bundle myData = new Bundle();
				 * 
				 * myData.putSerializable("usuario", usuario);
				 * intentLoginToMenu.putExtras(myData);
				 * 
				 * startActivity(intentLoginToMenu);
				 * 
				 * } else { trace("O usuário '" + usuario.getUsuario() +
				 * "' está BLOQUEADO."); }
				 * 
				 * } else { trace("Usuário ou senha inválidos."); }
				 */
				Log.d(LogStmt.CATEGORIA_LOGIN_ACTIVITY,
						"LoginActivity.ClickerEntrar.onClick: Encerrando o Login,"
								+ " chamando MenuActivity");
				Usuario usuario = dao.selectLogin(new Usuario(null, null,
						etUsuario.getText().toString(), etSenha.getText()
								.toString(), null, null, null, false, null,
						false, null, false, null, null, false));

				if (usuario != null) {
					if (usuario.isFlagAtivo()) {

						Intent intentLoginToMenu = new Intent(
								LoginActivity.this, MenuActivity.class);

						Bundle myData = new Bundle();

						myData.putSerializable("usuario", usuario);
						intentLoginToMenu.putExtras(myData);

						startActivity(intentLoginToMenu);

					} else {
						toast("O usuário '" + usuario.getUsuario()
								+ "' está BLOQUEADO.");
					}
				} else {
					toast("Usuário ou senha inválidos.");
				}

				// Log.d( LogStmt.CATEGORIA_LoginActivity,
				// "LoginActivity.ClickerEntrar.onClick: Encerrando o Login," +
				// " chamando MenuActivity" );
				break;

			case R.id.btNovoUsuario:

				Usuario novoUsuario = new Usuario(null, null, etUsuario
						.getText().toString(), etSenha.getText().toString(),
						null, null, null, false, null, false, null, false,
						null, null, false);

				Intent intentLoginToNovoUsuario = new Intent(getBaseContext(),
						CadastroUsuarioActivity.class);

				Bundle myData = new Bundle();

				myData.putSerializable("novoUsuario", novoUsuario);
				intentLoginToNovoUsuario.putExtras(myData);

				startActivity(intentLoginToNovoUsuario);

				break;
			}

		}
	}

	private void executaCargaInicial() {
		Usuario admin = new Usuario(1, "Admin", "admin", "1234",
				"newton.arruda@gmail.com", "976898587", Calendar.getInstance()
						.getTime(), true, "Alergia 1", true, "Medicação 1",
				true, 1, "Descrição Usuário.", true);

		if (dao.selectLogin(admin) == null) {
			dao.insert(admin);
			toast("A carga inicial foi executada com sucesso!");
		}

	}

	public void toast(String msg) {
		Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
	}

}
