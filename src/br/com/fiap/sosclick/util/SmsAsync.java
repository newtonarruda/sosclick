package br.com.fiap.sosclick.util;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

/**
 * Classe de SmsAsync, para envio de Sms Asíncronos
 * @author Mauricio F. Jr
 * @version 1.0
 * @since Sprint 1
 */
public class SmsAsync extends AsyncTask<Object, String, Void>
{
	
	String _telefoneW = "", _telefoneA = "", _telefoneF = "", _telefoneF2 = "", _mensagem = ""; // Para testes

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
		
		Log.d( LogStmt.CATEGORIA_SMS_ASYNC, "SmsAsync.doInBackground: Enviar Sms" );
		
		Context context = (Context) params[0];
		
		try
		{
			sendMenssage = new SmsSosClick( );
			
			
			_telefoneW = "11973978982"; // TESTE PARA CAIO 
			_telefoneA = "11976898587"; // TESTE PARA NEWTON
			_telefoneF = "11972901266"; // TESTE PARA MAURICIO
			_telefoneF2 = "11999301012"; // TESTE PARA RONALDO
			_mensagem = " URGENTE: SOCORRER O SEU XXXXX"; // TESTE
			
			sendMenssage.enviarSms( context, _telefoneW, _mensagem ); // VAI PRO CAIO
			sendMenssage.enviarSms( context, _telefoneA, _mensagem ); // VAI PRO NEWTON 
			sendMenssage.enviarSms( context, _telefoneF, _mensagem ); // VAI PRO MAURICIO
			sendMenssage.enviarSms( context, _telefoneF2, _mensagem ); // VAI PRO RONALDO
			Log.d( LogStmt.CATEGORIA_SMS_ASYNC, "SmsAsync.doInBackground: Sms enviado para " +
					_telefoneW + 
					_mensagem );
		}
		catch( Exception e )
		{
			Log.e( LogStmt.CATEGORIA_SMS_ASYNC, "SmsAsync.doInBackground: Ocorreu um erro ao enviar o Sms - " 
					+ e.getMessage( ) );
			//TODO Inserção de log no Database
		}
		
		return null;
	}
	
}
