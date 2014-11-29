package br.com.fiap.sosclick.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import br.com.fiap.sosclick.R;

public class MenuActivity extends Activity {

	
	ImageView ivUser;
	ImageView ivPlanodesaude;
	ImageView ivSaude;
	ImageView ivSocorristas;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		
		ivUser = (ImageView) findViewById(R.id.ivUser);
		ivUser.setOnClickListener(new ClickerUser());
		
		ivPlanodesaude = (ImageView) findViewById(R.id.ivPlanodesaude);
		ivPlanodesaude.setOnClickListener(new ClickerUser());
		
		ivSaude = (ImageView) findViewById(R.id.ivSaude);
		ivSaude.setOnClickListener(new ClickerUser());
		
		ivSocorristas = (ImageView) findViewById(R.id.ivSocorristas);
		ivSocorristas.setOnClickListener(new ClickerUser());
		
	}

	private class ClickerUser implements OnClickListener {

		@Override
		public void onClick(View view) {
			// Tela UserActivity
			Intent intent = new Intent(getBaseContext(),
					UserActivity.class);
			startActivity(intent);
		}
	}
	
	private class ClickerPlanodesaude implements OnClickListener {

		@Override
		public void onClick(View view) {
			// Tela PlanodesaudeActivity
			Intent intent = new Intent(getBaseContext(),
					PlanodesaudeActivity.class);
			startActivity(intent);
		}
	}
	
	private class ClickerSaude implements OnClickListener {

		@Override
		public void onClick(View view) {
			// Tela SaudeActivity
			Intent intent = new Intent(getBaseContext(),
					SaudeActivity.class);
			startActivity(intent);
		}
	}
	
	private class ClickerSocorristas implements OnClickListener {

		@Override
		public void onClick(View view) {
			// Tela SocorristasActivity
			Intent intent = new Intent(getBaseContext(),
					SocorristasActivity.class);
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
