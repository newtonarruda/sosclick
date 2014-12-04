package br.com.fiap.sosclick.view;

import br.com.fiap.sosclick.R;
import br.com.fiap.sosclick.service.AlertaBackgroundService;
import br.com.fiap.sosclick.util.LogStmt;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;

public class SplashScreenActivity extends Activity {

	private Thread threadSplash;
	private boolean clicouTela = false;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splashscreen);

		Log.d(LogStmt.CATEGORIA_SPLASH_SCREEN_ACTIVITY,
				"SplashScreenActivity.onCreate: Início do " + R.string.app_name);

		// Cria a thread para exibir a tela de splash
		threadSplash = new Thread() {
			@Override
			public void run() {
				try {
					synchronized (this) {
						// Aguarda 5 segundos ou sai quando a tela for tocada
						wait(5000);
						clicouTela = true;
					}
				} catch (InterruptedException ex) {
					Log.e(LogStmt.CATEGORIA_SPLASH_SCREEN_ACTIVITY,
							"SplashScreenActivity.onCreate: " + ex.getMessage());
					// Log: Implementar log de sistema em arquivo
					// Inserção de log em database
				}

				if (clicouTela) {
					// se clicou na tela fecha a splash screen
					finish();

					// se clicou na tela inicia a activity de Login
					Intent i = new Intent();
					i.setClass(SplashScreenActivity.this, LoginActivity.class);
					Log.d(LogStmt.CATEGORIA_SPLASH_SCREEN_ACTIVITY,
							"SplashScreenActivity.onCreate: Encerrado, chamando LoginActivity");
					startActivity(i);
				}
			}
		};

		threadSplash.start();

		startServicosBackground();
	}

	private void startServicosBackground() {

		Context context = getApplicationContext();

		// use this to start and trigger a service
		Intent i = new Intent(context, AlertaBackgroundService.class);
		// potentially add data to the intent
		i.putExtra("KEY1",
				"XXXXXXXXXXX - Value to be used by the service - XXXXXXXXXXX");
		context.startService(i);

	}

	@Override
	public void onPause() {
		super.onPause();

		// finaliza a thread quando o botão voltar for clicado, o sistema
		// finaliza a thread Splash
		threadSplash.interrupt();
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			synchronized (threadSplash) {
				clicouTela = true;

				// encerra o tempo de espera da tela de Splash
				threadSplash.notifyAll();
			}
		}
		return true;
	}

}
