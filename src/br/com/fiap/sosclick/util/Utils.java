package br.com.fiap.sosclick.util;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.util.Base64;
import android.util.Log;

@SuppressLint("SimpleDateFormat")
/**
 * Classe de utilidades
 * @author Newton Arruda
 * @version 1.0
 * @since Sprint 1.0
 */
public class Utils 
{
	private static String cryptoPass = "Thi$IsMyhAsH";
	
	public static String dateToString(Date data){
		SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
		
		return f.format(data);
	}

	public static String dateToString(Date data, String formato){
		SimpleDateFormat f = new SimpleDateFormat(formato);
		
		return f.format(data);
	}

	public static Date stringToDate(String data) throws ParseException{
		SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
		
		return new Date(f.parse(data).getTime());
	}

	public static Date stringToDate(String data, String formato) throws ParseException{
		SimpleDateFormat f = new SimpleDateFormat(formato);
		
		return new Date(f.parse(data).getTime());
	}

	public static boolean isNetworkAvailable(Context context) {
		return ((ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE))
				.getActiveNetworkInfo() != null;
	}

	public static String encryptIt(String value) {
	    try {
	        DESKeySpec keySpec = new DESKeySpec(cryptoPass.getBytes("UTF8"));
	        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
	        SecretKey key = keyFactory.generateSecret(keySpec);

	        byte[] clearText = value.getBytes("UTF8");
	        // Cipher is not thread safe
	        Cipher cipher = Cipher.getInstance("DES");
	        cipher.init(Cipher.ENCRYPT_MODE, key);

	        String encrypedValue = Base64.encodeToString(cipher.doFinal(clearText), Base64.DEFAULT);
	        Log.d( LogStmt.CATEGORIA_UTILS , "Criptografado: " + value + " -> " + encrypedValue);
	        return encrypedValue;

	    } catch (InvalidKeyException e) {
	        e.printStackTrace();
	    } catch (UnsupportedEncodingException e) {
	        e.printStackTrace();
	    } catch (InvalidKeySpecException e) {
	        e.printStackTrace();
	    } catch (NoSuchAlgorithmException e) {
	        e.printStackTrace();
	    } catch (BadPaddingException e) {
	        e.printStackTrace();
	    } catch (NoSuchPaddingException e) {
	        e.printStackTrace();
	    } catch (IllegalBlockSizeException e) {
	        e.printStackTrace();
	    }
	    return value;
	}; 
	public static String decryptIt(String value) {
	    try {
	        DESKeySpec keySpec = new DESKeySpec(cryptoPass.getBytes("UTF8"));
	        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
	        SecretKey key = keyFactory.generateSecret(keySpec);

	        byte[] encrypedPwdBytes = Base64.decode(value, Base64.DEFAULT);
	        // cipher is not thread safe
	        Cipher cipher = Cipher.getInstance("DES");
	        cipher.init(Cipher.DECRYPT_MODE, key);
	        byte[] decrypedValueBytes = (cipher.doFinal(encrypedPwdBytes));

	        String decrypedValue = new String(decrypedValueBytes);
	        Log.d( LogStmt.CATEGORIA_UTILS, "Descriptografado: " + value + " -> " + decrypedValue);
	        return decrypedValue;

	    } catch (InvalidKeyException e) {
	        e.printStackTrace();
	    } catch (UnsupportedEncodingException e) {
	        e.printStackTrace();
	    } catch (InvalidKeySpecException e) {
	        e.printStackTrace();
	    } catch (NoSuchAlgorithmException e) {
	        e.printStackTrace();
	    } catch (BadPaddingException e) {
	        e.printStackTrace();
	    } catch (NoSuchPaddingException e) {
	        e.printStackTrace();
	    } catch (IllegalBlockSizeException e) {
	        e.printStackTrace();
	    }
	    return value;
	} 
	
}
