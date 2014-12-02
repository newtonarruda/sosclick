package br.com.fiap.sosclick.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import br.com.fiap.sosclick.R;
import br.com.fiap.sosclick.util.LogStmt;
import br.com.fiap.sosclick.util.SmsAsync;

public class MenuActivity extends Activity {

	ImageView ivUser;
	ImageView ivPlanodesaude;
	ImageView ivSaude;
	ImageView ivSocorristas;
	ImageView ivInformacoes;
	
	SmsAsync senderSms; // TESTS
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu);
		
		Log.d( LogStmt.CATEGORIA_MENU_ACTIVITY, "MenuActivity.onCreate: Carregando activity" );
		
		ivUser = (ImageView) findViewById(R.id.ivUser);
		ivUser.setOnClickListener(new ClickerUsuario());
		
		ivPlanodesaude = (ImageView) findViewById(R.id.ivPlanodesaude);
		ivPlanodesaude.setOnClickListener(new ClickerPlanodesaude());
		
		ivSaude = (ImageView) findViewById(R.id.ivSaude);
		ivSaude.setOnClickListener(new ClickerSaude());
		
		ivSocorristas = (ImageView) findViewById(R.id.ivSocorristas);
		ivSocorristas.setOnClickListener(new ClickerSocorristas());
		
		ivInformacoes = (ImageView) findViewById(R.id.ivInfo);
		ivInformacoes.setOnClickListener(new ClickerInformacoes());
		
		// TESTS
		// senderSms = new SmsAsync( MenuActivity.this ); --> THIS WORKS!!!
		
	}

	private class ClickerUsuario implements OnClickListener {

		@Override
		public void onClick(View view) {
			// Tela UsuarioActivity
			Intent intent = new Intent(getBaseContext(),
					UsuarioActivity.class);
			Log.d( LogStmt.CATEGORIA_MENU_ACTIVITY, "MenuActivity.onClick: Opção do menu: Usuário" );
			startActivity(intent);
		}
	}
	
	private class ClickerPlanodesaude implements OnClickListener {

		@Override
		public void onClick(View view) {
			// Tela PlanodesaudeActivity
			Intent intent = new Intent(getBaseContext(),
					PlanodesaudeActivity.class);
			Log.d( LogStmt.CATEGORIA_MENU_ACTIVITY, "MenuActivity.onClick: Opção do menu: Plano de Saúde" );
			startActivity(intent);
		}
	}
	
	private class ClickerSaude implements OnClickListener {

		@Override
		public void onClick(View view) {
			// Tela SaudeActivity
			Intent intent = new Intent(getBaseContext(),
					SaudeActivity.class);
			Log.d( LogStmt.CATEGORIA_MENU_ACTIVITY, "MenuActivity.onClick: Opção do menu: Saúde" );
			startActivity(intent);
		}
	}
	
	private class ClickerSocorristas implements OnClickListener {

		@Override
		public void onClick(View view) {
			// Tela SocorristasActivity
			Intent intent = new Intent(getBaseContext(),
					SocorristasActivity.class);
			Log.d( LogStmt.CATEGORIA_MENU_ACTIVITY, "MenuActivity.onClick: Opção do menu: Socorrista" );
			startActivity(intent);
		}
	}
	
	private class ClickerInformacoes implements OnClickListener {

		@Override
		public void onClick(View view) {
			// Tela InformacoesActivity
			Intent intent = new Intent(getBaseContext(),
					InformacoesActivity.class);
			Log.d( LogStmt.CATEGORIA_MENU_ACTIVITY, "MenuActivity.onClick: Opção do menu: Informações" );
			startActivity(intent);
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
