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
import br.com.fiap.sosclick.util.Utils;
import br.com.fiap.sosclick.vo.Usuario;

public class CadastroUsuarioActivity extends Activity {

	Usuario usuario;
	
	Button btSalvar;
	
	EditText etNome;
	EditText etUsuario;
	EditText etSenha;
	EditText etEmail;
	EditText etTelefone;
	EditText etDataNascimento;
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
		setContentView(R.layout.activity_cadastro_usuario);

		etNome = (EditText) findViewById(R.id.etNome);
		etUsuario = (EditText) findViewById(R.id.etUsuario);
		etSenha = (EditText) findViewById(R.id.etSenha);
		etEmail = (EditText) findViewById(R.id.etEmail);
		etTelefone = (EditText) findViewById(R.id.etTelefone);
		etDataNascimento = (EditText) findViewById(R.id.etDataNascimento);
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

		Intent intentLoginToNovoUsuario = getIntent();

		//Implementar Intente de ida e volta
		Bundle bundle = intentLoginToNovoUsuario.getExtras();

		usuario = (Usuario) bundle.getSerializable("novoUsuario");
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
				
				if (dao.insert(usuario) == -1) {
					trace("Não foi possível cadatrar o Usuário.");
				}else{
					trace("Usuário cadastrado com sucesso!");
				}

				Intent intentLoginToMenu = new Intent(
						CadastroUsuarioActivity.this, MenuActivity.class);

				Bundle myData = new Bundle();

				myData.putSerializable("usuario", usuario);
				intentLoginToMenu.putExtras(myData);

				startActivity(intentLoginToMenu);

				break;
			}
		}
	}
	
	private void fillEntity() throws ParseException{
		
		usuario.setNome(etNome.getText().toString());
		usuario.setUsuario(etUsuario.getText().toString());
		usuario.setSenha(etSenha.getText().toString());
		usuario.setEmail(etEmail.getText().toString());
		usuario.setTelefone(etTelefone.getText().toString()); //  .isEmpty() ? null : Double.valueOf(etTelefone.getText().toString()));
		usuario.setDataNascimento(Utils.stringToDate(etDataNascimento.getText().toString())); 
		usuario.setFlagAlergia(cbFlagAlergia.isChecked()); 
		usuario.setDescricaoAlergia(etDescricaoAlergia.getText().toString()); 
		usuario.setFlagMedicacao(cbFlagMedicacao.isChecked()); 
		usuario.setDescricaoMedicacao(etDescricaoMedicacao.getText().toString()); 
		usuario.setFlagDiabetes(cbFlagDiabete.isChecked()); 
		usuario.setBitPressao(rgPressao.getCheckedRadioButtonId() == -1 ? null : rgPressao.getCheckedRadioButtonId()); 
		usuario.setDescricaoUsuario(etDescricaoUsuario.getText().toString()); 
		usuario.setFlagAtivo(true);
	}
	
	private void fillForm(){

		etNome.setText(usuario.getNome());
		etUsuario.setText(usuario.getUsuario());
		etSenha.setText(usuario.getSenha());
		etEmail.setText(usuario.getEmail());
		etTelefone.setText(usuario.getTelefone()); // == null ? "" : usuario.getTelefone().toString());
		etDataNascimento.setText(Utils.dateToString(usuario.getDataNascimento(), "dd/MM/yyyy")); 
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
