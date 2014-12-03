package br.com.fiap.sosclick.vo;

import java.util.ArrayList;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;
import br.com.fiap.sosclick.util.GMailSender;
import br.com.fiap.sosclick.util.LogStmt;
import br.com.fiap.sosclick.util.MailAsync;
import br.com.fiap.sosclick.util.Utils;

/**
 * Classe com as propriedades do e-mail
 * @author Mauricio F. Jr
 * @version 1.0
 * @since Sprint 1
 */
public class Mail 
{
	String username, password, mailto, subject, body, sendingMessage,sendingMessageSuccess;
	boolean processVisibility = true;
	ArrayList<String> attachments = new ArrayList<String>();
	Context mContext;
	
	public Mail( Context context )
	{
		this.mContext = context;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getMailto() {
		return mailto;
	}

	public String getSubject() {
		return subject;
	}

	public String getBody() {
		return body;
	}

	public String getSendingMessage() {
		return sendingMessage;
	}

	public String getSendingMessageSuccess() {
		return sendingMessageSuccess;
	}

	public boolean isProcessVisibility() {
		return processVisibility;
	}

	public ArrayList<String> getAttachments() {
		return attachments;
	}

	public Context getmContext() {
		return mContext;
	}

	public void setGmailUserName(String user) {
		this.username = user;
	}

	public void setGmailPassword(String pass) {
		this.password = pass;
	}

	public void setProcessVisibility(boolean state) {
		this.processVisibility = state;
	}

	public void setMailTo(String mail) {
		this.mailto = mail;
	}

	public void setFormSubject(String sub) {
		this.subject = sub;
	}

	public void setFormBody(String form) {
		this.body = form;
	}

	public void setSendingMessage(String sendMessage) {
		this.sendingMessage = sendMessage;
	}

	public void setSendingMessageSuccess(String sendMessageSucess) {
		this.sendingMessageSuccess = sendMessageSucess;

	}
	
	public void setAttachment(String attachments) {
		this.attachments.add(attachments);

	}

	public void send() {
		boolean valid = true;
		if (username == null && username.isEmpty()) {
			Log.e( LogStmt.CATEGORIA_MAIL, "Mail.send(): Voc� n�o inseriu o seu usu�rio do Gmail!");
			valid = false;
		}
		if (password == null && password.isEmpty()) {
			Log.e( LogStmt.CATEGORIA_MAIL, "Mail.send(): Voc� n�o inseriu a senha do Gmail!");
			valid = false;
		}
		if (mailto == null && mailto.isEmpty()) {
			Log.e( LogStmt.CATEGORIA_MAIL, "Mail.send(): Voc� n�o inseriu o remetente!");
			valid = false;
		}
		if (Utils.isNetworkAvailable(mContext) == false) {
			Log.e( LogStmt.CATEGORIA_MAIL, "Mail.send(): Usu�rio n�o possui conex�o com a internet!");
			valid = false;
		}
		if (valid == true) {
			// Isso � INNERCLASS
			// new startSendingEmail().execute();
			// Vamos tentar assim
			new MailAsync( mContext ).execute( );
		}
	}

	public class startSendingEmail extends AsyncTask<String, Void, String> {
		ProgressDialog pd;

		@Override
		protected void onPreExecute() {
			if (processVisibility != false) {
				pd = new ProgressDialog(mContext);
				if (sendingMessage != null && !sendingMessage.isEmpty()) {
					pd.setMessage(sendingMessage);
				} else {
//					Log.d( LogStmt.CATEGORIA_MAIL, "We dont have sending message so we use generic");
					Log.d( LogStmt.CATEGORIA_MAIL, "Mail.startSendingEmail.onPreExecute(): N�s n�o " 
							+ "temos o envio de mensagem, ent�o iremos utilizar um gen�rico");
					pd.setMessage("Carregando...");
				}
				pd.setCancelable(false);
				pd.show();
			}
			super.onPreExecute();
		}

		@Override
		protected String doInBackground(String... arg0) {
			try {
				GMailSender sender = new GMailSender(username, password);
				if(!attachments.isEmpty()){
					for (int i = 0; i < attachments.size(); i++) {
							if(!attachments.get(i).isEmpty()){
								sender.addAttachment(attachments.get(i));
							}
					}
				}
				sender.sendMail(subject, body, username, mailto);
			}
			catch (Exception e) 
			{
				Log.e( LogStmt.CATEGORIA_MAIL, "Mail.startSendingEmail.doInBackground(): " 
						+ e.getMessage().toString());
			}
			return null;
		}

		@Override
		protected void onPostExecute(String result) {
			if (processVisibility != false) {
				pd.dismiss();
				if (sendingMessageSuccess != null
						&& !sendingMessageSuccess.isEmpty()) {
					Toast.makeText(mContext, sendingMessageSuccess,
							Toast.LENGTH_SHORT).show(); // USANDO DE TESTE... N�O FAREMOS TOAST EM PRODU��O
				} 
				else 
				{
					Log.d( LogStmt.CATEGORIA_MAIL ,
							"Mail.startSendingEmail.onPostExecute(): Sua mensagem foi enviada com sucesso!");
					Toast.makeText(mContext,
							"Sua mensagem foi enviada com sucesso!",
							Toast.LENGTH_SHORT).show(); // USANDO DE TESTE... N�O FAREMOS TOAST EM PRODU��O
				}
			}
			super.onPostExecute(result);
		}
	}
	
}
