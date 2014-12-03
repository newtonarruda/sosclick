package br.com.fiap.sosclick.util;

import br.com.fiap.sosclick.vo.Mail;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

/**
 * Classe que irei utilizar para repor inner class (.vo.Mail)
 * @author Mauricio F. Jr
 * @version 1.0
 * @since Sprint 1
 */
public class MailAsync extends AsyncTask<String, Void, String>
{
	ProgressDialog pd;
	Mail _mail;
	
	public MailAsync( Context context )
	{
		_mail = new Mail( context );
		pd = new ProgressDialog( context );
		
		onPreExecute();
		doInBackground();
	}
	
	@Override
	protected void onPreExecute() {
		if (_mail.isProcessVisibility() != false) {
			
			if (_mail.getSendingMessage() != null && !_mail.getSendingMessage().isEmpty()) {
				pd.setMessage( _mail.getSendingMessage() );
			} else {
//				Log.d( LogStmt.CATEGORIA_MAIL, "We dont have sending message so we use generic");
				Log.d( LogStmt.CATEGORIA_MAIL, "Mail.startSendingEmail.onPreExecute(): Nós não " 
						+ "temos o envio de mensagem, então iremos utilizar um genérico");
				pd.setMessage("Carregando...");
			}
			pd.setCancelable(false);
			pd.show();
		}
		super.onPreExecute();
	}
	
	@Override
	protected String doInBackground(String... params) {
		try {
			GMailSender sender = new GMailSender(_mail.getUsername(), _mail.getPassword() );
			if(!_mail.getAttachments().isEmpty() )
			{
				for (int i = 0; i < _mail.getAttachments().size(); i++) 
				{
						if(!_mail.getAttachments().get( i ).isEmpty() )
						{
							sender.addAttachment(_mail.getAttachments().get( i ) );
						}
				}
			}
			
			sender.sendMail(_mail.getSubject(), _mail.getBody(), _mail.getUsername(), _mail.getMailto() );
		}
		catch (Exception e) 
		{
			Log.e( LogStmt.CATEGORIA_MAIL, "MailAsync.doInBackground(): " 
					+ e.getMessage().toString());
		}
		return null;
	}
	
	@Override
	protected void onPostExecute(String result) {
		if (_mail.isProcessVisibility() != false) 
		{
			pd.dismiss();
			if (_mail.getSendingMessage() != null
					&& ! _mail.getSendingMessageSuccess().isEmpty() )
			{
				Log.d("PASSOU", "MENSAGEM ENVIADA????");
//				Toast.makeText(mContext, sendingMessageSuccess,
//						Toast.LENGTH_SHORT).show(); // USANDO DE TESTE... NÃO FAREMOS TOAST EM PRODUÇÃO
			} 
			else 
			{
				Log.d( LogStmt.CATEGORIA_MAIL ,
						"Mail.startSendingEmail.onPostExecute(): Sua mensagem foi enviada com sucesso!");
//				Toast.makeText(mContext,
//						"Sua mensagem foi enviada com sucesso!",
//						Toast.LENGTH_SHORT).show(); // USANDO DE TESTE... NÃO FAREMOS TOAST EM PRODUÇÃO
			}
		}
		super.onPostExecute(result);
	}
	
}
