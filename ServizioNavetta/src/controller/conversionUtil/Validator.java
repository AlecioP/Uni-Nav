package controller.conversionUtil;

import persistence.persistentModel.Prenotazione;

/**Servlet??
 * Singleton??
 * #1 controllare che l'istanza di prenotazione esista (not null)
 * #2 controllare se e' entrata o uscita
 * #3 controllare che fermataCorrente == fermataPrenotazioneEntrata o fermataPrenotazioneUscita
 * #4 cambiare attributo
 * #5 se uscita inviare feedback
 * 
 * @author Francesco Cugliari
 *
 */
public class Validator {
	public Validator() {
		
	}
	
	public boolean validate(Prenotazione prenotation) {
		if(prenotation==null) {
			return false;
		}
		if(prenotation.isObliteratoEntrata()==false) {
			//controllare che fermatacorrente== prenotation.getTratto().getPartenza()
			//in caso positivo
			// prenotation.setObliteratoEntrata(true);return true;
			//altrimenti
			//return false;
		}else {
			//caso prenotation.isObliteratoEntrata()==true
			//controllare che fermata== prenotation.getTratto().getArrivo()
			//in caso positivo
			//prenotation.setObliteratoUscita(true);return true;
			//invia feedback all utente
			//altrimenti
			//return false;
		}
		
		return false;
	}
}
