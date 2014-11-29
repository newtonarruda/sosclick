package br.com.fiap.sosclick.view;

import br.com.fiap.sosclick.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;

public class SplashScreenActivity extends Activity {

	private Thread threadSplash;
	private boolean clicouTela = false;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splashscreen);

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
					// Log: Implementar log de sistema em arquivo
				}

				if (clicouTela) {
					// se clicou na tela fecha a splash screen
					finish();

					// se clicou na tela inicia a activity de Login
					Intent i = new Intent();
					i.setClass(SplashScreenActivity.this, LoginActivity.class);
					startActivity(i);
				}
			}
		};

		threadSplash.start();
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
