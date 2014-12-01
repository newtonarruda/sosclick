package br.com.fiap.sosclick.util;

import android.annotation.SuppressLint;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@SuppressLint("SimpleDateFormat")
public class Utils {

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

}
