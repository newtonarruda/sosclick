package br.com.fiap.sosclick.util;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;

/**
 * Classe de Sms, prepara envio da mensagem
 * @author Mauricio F. Jr
 * @version 1.0
 * @since Sprint 1
 */
public class SmsSosClick 
{
	/**
	 * Envia mensagem para o socorrista
	 * @param context Context que está a tela
	 * @param destino String que recebe o número do telefone do destinatário
	 * @param mensagem String que recebe a mensagem que será enviado para o destinatário
	 */
	public void enviarSms( Context context, String destino, String mensagem )
	{
		Log.d( LogStmt.CATEGORIA_SMS_SOS_CLICK, "SmsSosClick.enviarSms: Preparando envio" ) ;
		
		try
		{
			SmsManager smsManager = SmsManager.getDefault( ) ;
			PendingIntent pIntent = PendingIntent.getBroadcast( context, 0, new Intent( ), 0 ) ;
			smsManager.sendTextMessage( destino, null, mensagem, pIntent, null ) ;
		}
		catch (Exception e)
		{
			Log.e( LogStmt.CATEGORIA_SMS_SOS_CLICK, "Erro ao enviar o SMS: " + e.getMessage( ), e );
			//TODO Inserção de log para Database
		}
	}
	
	/**
	 * Lê uma mensagem da Intent. A Intent é recebida por um IntentFilter
	 * configurado para a ação "android.provider.Telephony.SMS_RECEIVED"
	 * @param intent
	 * @return
	 */
	public SmsMessage receberMensagem( Intent intent )
	{
		SmsMessage[ ] mensagens = getMessagesFromIntent( intent ) ;
		if (mensagens != null)
		{
			return mensagens[ 0 ] ;
		}
		return null ;
	}
	
	private SmsMessage[ ] getMessagesFromIntent( Intent intent )
	{
		Log.d( LogStmt.CATEGORIA_SMS_SOS_CLICK, "SmsSosClick.getMessagesFromIntent: " + intent.getAction( ) ) ;

		Object messages[] = ( Object[ ] ) ( Object[ ] ) intent.getSerializableExtra( "pdus" ) ;

		if (messages == null)
		{
			return null ;
		}

		byte pduObjs[][] = new byte[ messages.length ][ ] ;

		for (int i = 0; i < messages.length; i++)
			pduObjs[ i ] = ( byte[ ] ) ( byte[ ] ) messages[ i ] ;

		byte pdus[][] = new byte[ pduObjs.length ][ ] ;

		int pduCount = pdus.length ;

		if (pduCount == 0)
		{
			return null ;
		}

		SmsMessage msgs[] = new SmsMessage[ pduCount ] ;
		for (int i = 0; i < pduCount; i++)
		{
			pdus[ i ] = pduObjs[ i ] ;
			msgs[ i ] = SmsMessage.createFromPdu( pdus[ i ] ) ;

			String celular = msgs[ 0 ].getDisplayOriginatingAddress( ) ;
			String mensagem = msgs[ 0 ].getDisplayMessageBody( ) ;

			Log.d( LogStmt.CATEGORIA_SMS_SOS_CLICK, "Sms.Mensagem: " + celular + " -> " + mensagem ) ;
			//TODO Inserção de log para Database
		}

		return msgs ;
	}
}
