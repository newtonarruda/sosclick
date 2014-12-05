package br.com.fiap.sosclick.view;

import java.text.ParseException;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;
import br.com.fiap.sosclick.R;
import br.com.fiap.sosclick.dao.UsuarioDAO;
import br.com.fiap.sosclick.vo.Usuario;

public class CadastroSaudeUsuarioActivity extends Activity {

	String origem;
	Usuario usuario;
	
	Button btSalvar;
	
	CheckBox cbFlagAlergia;
	EditText etDescricaoAlergia;
	CheckBox cbFlagMedicacao;
	EditText etDescricaoMedicacao;
	CheckBox cbFlagDiabete;
	RadioGroup rgPressao;
	EditText etDescricaoUsuario;
	
	UsuarioDAO dao;
	
	private StringBuffer mensagens = new StringBuffer();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cadastro_saude_usuario);

		cbFlagAlergia = (CheckBox) findViewById(R.id.cbFlagAlergia);
		etDescricaoAlergia = (EditText) findViewById(R.id.etDescricaoAlergia);
		cbFlagMedicacao = (CheckBox) findViewById(R.id.cbFlagMedicacao);
		etDescricaoMedicacao = (EditText) findViewById(R.id.etDescricaoMedicacao);
		cbFlagDiabete = (CheckBox) findViewById(R.id.cbFlagDiabete);
		rgPressao = (RadioGroup) findViewById(R.id.rgPressao);
		etDescricaoUsuario = (EditText) findViewById(R.id.etDescricaoUsuario);

		btSalvar = (Button) findViewById(R.id.btSalvar);
		btSalvar.setOnClickListener(new ClickerCadastrar());
		

		if(dao == null){
			dao = new UsuarioDAO(this);	
		}

		Intent intentMenuToSaudeUsuario = getIntent();

		//Implementar Intente de ida e volta
		Bundle bundle = intentMenuToSaudeUsuario.getExtras();

		origem = (String) bundle.getSerializable("origem");
		usuario = (Usuario) bundle.getSerializable("usuario");
		fillForm();
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
				
				if(!validate()){
					trace(mensagens.toString());
					break;
				}
				
				if (dao.updateSaudeUsuario(usuario) == -1) {
					trace("Não foi possível cadatrar as informações de Saúde do Usuário.");
				}else{
					trace("Informações de Saúde do Usuário cadastrads com sucesso!");
				}

				Intent intentCadastroSaudeUsuarioToMenu = new Intent(
						getBaseContext(), MenuActivity.class);

				Bundle myData = new Bundle();

				myData.putSerializable("origem", "Saude");
				myData.putSerializable("usuario", usuario);
				intentCadastroSaudeUsuarioToMenu.putExtras(myData);

				startActivity(intentCadastroSaudeUsuarioToMenu);

				break;
			}
		}
	}
	
	private void fillEntity() throws ParseException{
		usuario.setFlagAlergia(cbFlagAlergia.isChecked()); 
		usuario.setDescricaoAlergia(etDescricaoAlergia.getText().toString()); 
		usuario.setFlagMedicacao(cbFlagMedicacao.isChecked()); 
		usuario.setDescricaoMedicacao(etDescricaoMedicacao.getText().toString()); 
		usuario.setFlagDiabetes(cbFlagDiabete.isChecked()); 
		//usuario.setBitPressao(rgPressao.getCheckedRadioButtonId() == -1 ? null : rgPressao.getCheckedRadioButtonId()); 
		usuario.setDescricaoUsuario(etDescricaoUsuario.getText().toString()); 
	}
	
	private void fillForm(){

		cbFlagAlergia.setChecked(usuario.isFlagAlergia()); 
		etDescricaoAlergia.setText(usuario.getDescricaoAlergia()); 
		cbFlagMedicacao.setChecked(usuario.isFlagMedicacao()); 
		etDescricaoMedicacao.setText(usuario.getDescricaoMedicacao()); 
		cbFlagDiabete.setChecked(usuario.isFlagDiabetes());  
		//rgPressao.get usuario.setBitPressao(rgPressao.getCheckedRadioButtonId() == -1 ? null : rgPressao.getCheckedRadioButtonId()); TODO Implementar preencimento dos valores dos Radios
		etDescricaoUsuario.setText(usuario.getDescricaoUsuario()); 
	}
	
	private boolean validate(){
		boolean retorno = true;
		mensagens = new StringBuffer();
		
		// TODO Implementar validação
		
//		if(usuario.getNome() == null || usuario.getNome().isEmpty()){
//			mensagens.append("Informe o Nome do Usuário\n");
//			retorno = false;
//		}
//		if(usuario.getUsuario() == null || usuario.getUsuario().isEmpty()){
//			mensagens.append("Informe o Login Usuário\n");
//			retorno = false;
//		}
//		if(usuario.getSenha() == null || usuario.getSenha().isEmpty()){
//			mensagens.append("Informe a Senha do Usuário\n");
//			retorno = false;
//		}
//		if(usuario.getEmail() == null || usuario.getEmail().isEmpty()){
//			mensagens.append("Informe o E-mail do Usuário\n");
//			retorno = false;
//		}
//		if(usuario.getTelefone() == null || usuario.getTelefone().isEmpty()){
//			mensagens.append("Informe o Telefone do Usuário\n");
//			retorno = false;
//		}
//		
//		if(mensagens.toString().isEmpty()){
//			Usuario usuatioTemp = dao.selectLogin(usuario);
//			if(usuatioTemp != null){
//				mensagens.append("Este Usuário já está cadastrado no sistema. Informe outro Usuário\n");
//				retorno = false;
//			}
//		}
/*		else{
			mensagens = new StringBuffer();
			mensagens.append("Este Usuário já está cadastrado no sistema. Informe outro Usuário/n");
			retorno = false;
		}
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
