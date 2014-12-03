package br.com.fiap.sosclick.util;

/**
 * Classe que armazena Statments de Log
 * Útil para catalogação de logs.
 * Objetivo: Verificar e analisar o retorno em database e Logcat.
 * @author Mauricio F. Jr
 * @version 1.0
 * @since Sprint 1
 */
public final class LogStmt 
{
	// .utils
	public static final String CATEGORIA_GMAILSENDER = "UTIL_GMAIL_SENDER";
	public static final String CATEGORIA_JSSE_PROVIDER = "UTIL_JSSE_PROVIDER";
	public static final String CATEGORIA_SMS_SOS_CLICK = "UTIL_SMS_SOS_CLICK";
	public static final String CATEGORIA_MAIL_BODY_SENDER = "UTIL_MAIL_BODY_SENDER";
	public static final String CATEGORIA_SMS_ASYNC = "UTIL_SMS_ASYNC";
	public static final String CATEGORIA_UTILS = "UTIL_UTILS";
	
	//.view
	public static final String CATEGORIA_CADASTRO_USUARIO_ACTIVITY = "ACTIVITY_CADASTRO_USUARIO";
	public static final String CATEGORIA_INFORMACOES_ACTIVITY = "ACTIVITY_INFORMACOES";
	public static final String CATEGORIA_LOGIN_ACTIVITY = "ACTIVITY_LOGIN";
	public static final String CATEGORIA_MENU_ACTIVITY = "ACTIVITY_MENU";
	public static final String CATEGORIA_SPLASH_SCREEN_ACTIVITY = "ACTIVITY_SPLASH_SCREEN";
	
	//.vo
	public static final String CATEGORIA_MAIL = "VO_MAIL";
	public static final String CATEGORIA_USUARIO = "VO_USUARIO";
}