package br.com.fiap.sosclick.util;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

public class SmsAsync extends AsyncTask<Object, String, Void>
{

	SmsSosClick sendMenssage;
	
	public SmsAsync( Context context )
	{
		doInBackground( new Object[] { context } );
	}
	
//	protected Void doInBackground( user, Object... params )
//	{
		//TODO aguardar a implementação do Enviar Socorro
//	}
	
	// Classe para utilizar como teste
	@Override
	protected Void doInBackground(Object... params) {
		// Ignore this, unusefull, only for test
		
		Log.d( LogStmt.CATEGORIA_SmsAsync, "SmsAsync.doInBackground: Enviar Sms" );
		
		Context context = (Context) params[0];
		
		try
		{
			sendMenssage = new SmsSosClick( );
			
			sendMenssage.enviarSms( context, "", "" );
		}
		catch( Exception e )
		{
			Log.e( LogStmt.CATEGORIA_SmsAsync, "SmsAsync.doInBackground: Ocorreu um erro ao enviar o Sms - " 
					+ e.getMessage( ) );
			//TODO Inserção de log no Database
		}
		
		return null;
	}
	
}
