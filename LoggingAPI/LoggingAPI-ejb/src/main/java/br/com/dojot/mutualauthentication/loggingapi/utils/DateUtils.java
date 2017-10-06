package br.com.dojot.mutualauthentication.loggingapi.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.ejb.Singleton;

@Singleton
public class DateUtils {

    public String convertFormatDateInString(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String newDate = sdf.format(date);
        return newDate;
    }
    
    public Date convertFormatStringInDate(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date newDate = null;
		try {
			newDate = sdf.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
        return newDate;
    }
    
}
