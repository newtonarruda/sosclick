package br.com.fiap.sosclick.view;

import java.text.ParseException;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import br.com.fiap.sosclick.R;
import br.com.fiap.sosclick.dao.UsuarioDAO;
import br.com.fiap.sosclick.util.Utils;
import br.com.fiap.sosclick.vo.Usuario;

public class CadastroUsuarioActivity extends Activity {

	String origem;
	Usuario usuario;

	Button btSalvar;

	TextView tvLogin;
	EditText etNome;
	EditText etUsuario;
	EditText etSenha;
	EditText etEmail;
	EditText etTelefone;
	EditText etDataNascimento;

	UsuarioDAO dao;

	private StringBuffer mensagens = new StringBuffer();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cadastro_usuario);

		tvLogin = (TextView) findViewById(R.id.tvLogin);
		etNome = (EditText) findViewById(R.id.etNome);
		etUsuario = (EditText) findViewById(R.id.etUsuario);
		etSenha = (EditText) findViewById(R.id.etSenha);
		etEmail = (EditText) findViewById(R.id.etEmail);
		etTelefone = (EditText) findViewById(R.id.etTelefone);
		etDataNascimento = (EditText) findViewById(R.id.etDataNascimento);

		btSalvar = (Button) findViewById(R.id.btSalvar);
		btSalvar.setOnClickListener(new ClickerCadastrar());

		if (dao == null) {
			dao = new UsuarioDAO(this);
		}

		Intent intentLoginToNovoUsuario = getIntent();

		// Implementar Intente de ida e volta
		Bundle bundle = intentLoginToNovoUsuario.getExtras();

		origem = (String) bundle.getSerializable("origem");
		usuario = (Usuario) bundle.getSerializable("usuario");
		
		if("Login".equalsIgnoreCase(origem)){
			tvLogin.setText(R.string.usuario);
			etUsuario.setText(usuario.getUsuario());
		}else if("Menu".equalsIgnoreCase(origem)){
			tvLogin.setText(R.string.cadastro_usuario);
			fillForm();
		}
	}

	private class ClickerCadastrar implements OnClickListener {

		@Override
		public void onClick(View view) {
			switch (view.getId()) {

			case R.id.btSalvar:
				try {
					fillEntity();
				} catch (ParseException e) {
					trace("Erro ao preencher entidade.");
					break;
				}

				if (!validate()) {
					trace(mensagens.toString());
					break;
				}
				if("Login".equalsIgnoreCase(origem)){
					if (dao.insert(usuario) == -1) {
						trace("Não foi possível cadatrar o Usuário.");
					} else {
						trace("Usuário cadastrado com sucesso!");
					}
				} else if("Menu".equalsIgnoreCase(origem)){
					if (dao.updateUsuario(usuario) == -1) {
						trace("Não foi possível salvar o Usuário.");
					} else {
						trace("Usuário salvo com sucesso!");
					}
				}

				Intent intentCadatroUsuarioToMenu = new Intent(
						getBaseContext(), MenuActivity.class);

				Bundle myData = new Bundle();

				myData.putSerializable("usuario", usuario);
				intentCadatroUsuarioToMenu.putExtras(myData);

				startActivity(intentCadatroUsuarioToMenu);

				break;
			}
		}
	}

	private void fillEntity() throws ParseException {
		//usuario = new Usuario();
		usuario.setNome(etNome.getText().toString());
		usuario.setUsuario(etUsuario.getText().toString());
		usuario.setSenha(etSenha.getText().toString());
		usuario.setEmail(etEmail.getText().toString());
		usuario.setTelefone(etTelefone.getText().toString()); // .isEmpty() ?
																// null :
																// Double.valueOf(etTelefone.getText().toString()));
		usuario.setDataNascimento(Utils.stringToDate(etDataNascimento.getText()
				.toString()));

		usuario.setFlagAtivo(true);
	}

	private void fillForm() {

		etNome.setText(usuario.getNome());
		etUsuario.setText(usuario.getUsuario());
		etSenha.setText(usuario.getSenha());
		etEmail.setText(usuario.getEmail());

		etTelefone.setText(usuario.getTelefone()); // == null ? "" :
													// usuario.getTelefone().toString());
		etDataNascimento.setText(Utils.dateToString(
				usuario.getDataNascimento(), Utils.DD_MM_YYYY));

	}

	private boolean validate() {
		boolean retorno = true;
		mensagens = new StringBuffer();

		// TODO Implementar validação

		// if(usuario.getNome() == null || usuario.getNome().isEmpty()){
		// mensagens.append("Informe o Nome do Usuário\n");
		// retorno = false;
		// }
		// if(usuario.getUsuario() == null || usuario.getUsuario().isEmpty()){
		// mensagens.append("Informe o Login Usuário\n");
		// retorno = false;
		// }
		// if(usuario.getSenha() == null || usuario.getSenha().isEmpty()){
		// mensagens.append("Informe a Senha do Usuário\n");
		// retorno = false;
		// }
		// if(usuario.getEmail() == null || usuario.getEmail().isEmpty()){
		// mensagens.append("Informe o E-mail do Usuário\n");
		// retorno = false;
		// }
		// if(usuario.getTelefone() == null || usuario.getTelefone().isEmpty()){
		// mensagens.append("Informe o Telefone do Usuário\n");
		// retorno = false;
		// }
		//
		// if(mensagens.toString().isEmpty()){
		// Usuario usuatioTemp = dao.selectLogin(usuario);
		// if(usuatioTemp != null){
		// mensagens.append("Este Usuário já está cadastrado no sistema. Informe outro Usuário\n");
		// retorno = false;
		// }
		// }
		/*
		 * else{ mensagens = new StringBuffer(); mensagens.append(
		 * "Este Usuário já está cadastrado no sistema. Informe outro Usuário/n"
		 * ); retorno = false; }
		 */
		return retorno;
	}

	public void toast(String msg) {
		Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
	}

	private void trace(String msg) {
		toast(msg);
	}

}
