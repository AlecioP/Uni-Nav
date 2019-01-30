package controller;

import java.util.Calendar;
import java.util.Date;

import persistence.persistentModel.Prenotazione;

public class Convertitor {
	public Convertitor() {
		
	}
	
	
	public Prenotazione getPrenotazione(String code) {
		return null;
		 
	}
	
	public String getCode(Prenotazione prenotazione) {
		Calendar calendar = prenotazione.getDateTime();
		Date time = calendar.getTime();	
		String codePrenString =  String.valueOf(prenotazione.getID());
		int n = (int)  (Math.random()*100000);
		String codeRandom = String.valueOf(n);
		String code = calendar.get(Calendar.HOUR_OF_DAY) + "-" +calendar.get(Calendar.MINUTE) + codeRandom + "-" + codePrenString;
		return code; 
	}
}
