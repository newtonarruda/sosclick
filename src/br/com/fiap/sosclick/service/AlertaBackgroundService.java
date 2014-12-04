package br.com.fiap.sosclick.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.IBinder;
import android.os.Vibrator;
import android.util.Log;
import android.widget.Toast;
import br.com.fiap.sosclick.util.LogStmt;
import br.com.fiap.sosclick.view.LoginActivity;

public class AlertaBackgroundService extends Service implements
		SensorEventListener {

	private static final int TIPO_SENSOR = Sensor.TYPE_ACCELEROMETER;
	private SensorManager sensorManager;
	private Sensor sensor;
	private float mAccel; // aceleração sem gravidade já com filtro
	private float mAccelCurrent; // aceleração com gravidade
	private float mAccelLast; // última aceleração
	private boolean ativo;
	private Vibrator vibrator;

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Toast.makeText(getApplicationContext(), "OnStartCommand",
				Toast.LENGTH_SHORT).show();

		sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

		sensor = sensorManager.getDefaultSensor(TIPO_SENSOR);
		if (sensor != null) {
			sensorManager.registerListener(this, sensor,
					SensorManager.SENSOR_DELAY_FASTEST);
		}

		vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
		// toastTeste();
		ativo = true;
		return Service.START_NOT_STICKY;
	}

	@Override
	public IBinder onBind(Intent intent) {
		// TODO for communication return IBinder implementation
		return null;
	}

	public void toastTeste() {

		Thread th = new Thread() {

			int timer = 0;

			@Override
			public void run() {
				try {
					for (timer = 0; timer < 100; timer++) {
						Thread.sleep(3000);
						Log.i("---------->>", "Mensagem de numero: " + timer);
						if (timer == 10) {
							Intent intent = new Intent(getBaseContext(),
									LoginActivity.class);
							intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

							Log.d(LogStmt.CATEGORIA_LOGIN_ACTIVITY,
									"Vou abrir o login dessa jossa!");
							startActivity(intent);
						}
					}
				} catch (InterruptedException e) {
				}

			}
		};
		th.start();

	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		if (ativo) {
			float x = event.values[0];
			float y = event.values[1];
			float z = event.values[2];

			// calcula a aceleração
			mAccelLast = mAccelCurrent;
			mAccelCurrent = (float) Math.sqrt((double) (x * x + y * y + z * z));

			// verifica a diferença
			float delta = mAccelCurrent - mAccelLast;

			// Aplica o filtro (low-cut filter)
			mAccel = mAccel * 0.9f + delta;
			if (mAccel > 14) {
				Log.i("Delta:", "" + delta);
				Log.i("Aceleracao:", "" + mAccel);

				vibrator.vibrate(10000);
				
				// TODO Trocar essa activity pela do alerta 
				Intent intent = new Intent(getBaseContext(),
						LoginActivity.class);
				intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

				Log.d(LogStmt.CATEGORIA_LOGIN_ACTIVITY,
						"Vou abrir o login dessa jossa!");
				startActivity(intent);
			}
		}

	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub

	}

}
