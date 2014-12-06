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
import br.com.fiap.sosclick.dao.PlanoSaudeDAO;
import br.com.fiap.sosclick.dao.SocorristaDAO;
import br.com.fiap.sosclick.vo.PlanoSaude;
import br.com.fiap.sosclick.vo.Socorrista;
import br.com.fiap.sosclick.vo.Usuario;

public class CadastroSocorristaActivity extends Activity {

	String origem;
	Usuario usuario;
	Socorrista socorrista;

	Button btSalvar;

	TextView tvSocorrista;
	EditText etNome;
	EditText etEmail;
	EditText etTelefone;

	SocorristaDAO dao;

	private StringBuffer mensagens = new StringBuffer();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cadastro_socorrista);

		tvSocorrista = (TextView) findViewById(R.id.tvSocorrista);
		etNome = (EditText) findViewById(R.id.etNome);
		etEmail = (EditText) findViewById(R.id.etEmail);
		etTelefone = (EditText) findViewById(R.id.etTelefone);

		btSalvar = (Button) findViewById(R.id.btSalvar);
		btSalvar.setOnClickListener(new ClickerCadastrar());

		if (dao == null) {
			dao = new SocorristaDAO(this);
		}

		Intent intentMenuToSocorrista = getIntent();

		// Implementar Intente de ida e volta
		Bundle bundle = intentMenuToSocorrista.getExtras();

		origem = (String) bundle.getSerializable("origem");
		usuario = (Usuario) bundle.getSerializable("usuario");

		if ("Menu".equalsIgnoreCase(origem)) {
			socorrista = dao.selectAtivo();
			if(socorrista == null){
				socorrista = new Socorrista();
				socorrista.setIdUsuario(usuario.getIdUsuario());
			}else{
				fillForm();
			}
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
				
				if (dao.selectAtivo() == null) {
					if (dao.insert(socorrista) == -1) {
						trace("Não foi possível salvar o Plano de Saúde.");
					} else {
						trace("Plano de Saúde salvo com sucesso!");
					}
				}else{
					if (dao.update(socorrista) == -1) {
						trace("Não foi possível salvar o Plano de Saúde.");
					} else {
						trace("Plano de Saúde salvo com sucesso!");
					}
				}

				Intent intentCadatroPlanoSaudeToMenu = new Intent(
						getBaseContext(), MenuActivity.class);
				Bundle myData = new Bundle();
				myData.putSerializable("usuario", usuario);
				intentCadatroPlanoSaudeToMenu.putExtras(myData);
				startActivity(intentCadatroPlanoSaudeToMenu);

				break;
			}
		}
	}

	private void fillEntity() throws ParseException {
		socorrista.setNome(etNome.getText().toString());
		socorrista.setEmail(etEmail.getText().toString());
		socorrista.setTelefone(etTelefone.getText().toString());
		socorrista.setFlagAtivo(true);
	}

	private void fillForm() {

		etNome.setText(socorrista.getNome());
		etEmail.setText(socorrista.getEmail());
		etTelefone.setText(socorrista.getTelefone());

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
