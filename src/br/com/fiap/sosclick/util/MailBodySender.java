package br.com.fiap.sosclick.util;

import br.com.fiap.sosclick.vo.Mail;
import android.content.Context;
import android.util.Log;

public class MailBodySender 
{
	
	
	public MailBodySender( Context context )
	{
		// DELETE THIS LINE AFTER YOU FIND YOUR ENCRPYTED PASSWORD
		Log.d( LogStmt.CATEGORIA_MAIL_BODY_SENDER , "MailBodySender(context): " + Utils.encryptIt("W@fF_S0sCl1cK") );
	
		Mail mail = new Mail( context );
		mail.setGmailUserName("waff.sosclick@gmail.com");
		mail.setGmailPassword("Ulenkchp1HVHuL3QESJPAQ=="); // Inserir a senha criptogragada

//		mail.setMailTo(""); // PARA QUEM?
		mail.setMailTo("m.frasson.jr@gmail.com");
		//		mail.setMailTo("cwaquil@gmail.com,newton.arruda@gmail.com,ronfaraone@gmail.com," 
//				+ "ronfaraone@hotmail.com,m.frasson.jr@gmail.com");
		
		mail.setFormSubject("SOS CLICK - HELPER USUARIO XXXXX"); // ASSUNTO
		mail.setFormBody("Favor, socorrer o usuário XXXXX que está na R.SALVE SE FOR CAPAZ, 1111"); // CORPO DO EMAIL
		
		// Esses métodos são opcionais
		// bm.setSendingMessage("Loading...);
		// bm.setSendingMessageSuccess("Your message was sent successfully.");
		// bm.setProcessVisibility(false);
		// bm.setAttachment(Environment.getExternalStorageDirectory().getPath()+File.pathSeparator+"somefile.txt");
		
		mail.send();
		
	}
}
