package br.com.fiap.sosclick.view;

import br.com.fiap.sosclick.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


public class LoginActivity extends Activity{
	
	Button btLogin;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		

    btLogin = (Button) findViewById(R.id.btEntrar);
	btLogin.setOnClickListener(new ClickerEntrar());
		
	
	}
	
		
	private class ClickerEntrar implements OnClickListener {

		@Override
		public void onClick(View view) {
			// Tela MenuActivity
			Intent intent = new Intent(getBaseContext(),
					MenuActivity.class);
			startActivity(intent);
		}
	}
	
	
}
