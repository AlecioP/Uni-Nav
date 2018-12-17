package persistence.persistentModel;

import java.time.LocalDateTime;

public class Prenotazione {

	private boolean obliteratoUscita=false,obliteratoEntrata=false;
	private int ID;
	private int giro;
	private Navetta navetta;
	private TrattoLinea tratto;
	private LocalDateTime dateTime;
	private Autista autista;
	
	public Prenotazione(int ID,int giro,Navetta navetta,TrattoLinea tratto,LocalDateTime dateTime,Autista autista) {
		this.ID = ID;
		this.giro = Math.abs(giro);
		this.navetta = navetta;
		this.tratto = tratto;
		this.dateTime = dateTime;
		this.autista = autista;
	}

	public boolean isObliteratoUscita() {
		return obliteratoUscita;
	}

	public void setObliteratoUscita(boolean obliteratoUscita) {
		this.obliteratoUscita = obliteratoUscita;
	}

	public boolean isObliteratoEntrata() {
		return obliteratoEntrata;
	}

	public void setObliteratoEntrata(boolean obliteratoEntrata) {
		this.obliteratoEntrata = obliteratoEntrata;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public int getGiro() {
		return giro;
	}

	public void setGiro(int giro) {
		this.giro = giro;
	}

	public Navetta getNavetta() {
		return navetta;
	}

	public void setNavetta(Navetta navetta) {
		this.navetta = navetta;
	}

	public TrattoLinea getTratto() {
		return tratto;
	}

	public void setTratto(TrattoLinea tratto) {
		this.tratto = tratto;
	}

	public LocalDateTime getDateTime() {
		return dateTime;
	}

	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}

	public Autista getAutista() {
		return autista;
	}

	public void setAutista(Autista autista) {
		this.autista = autista;
	}
	
	
}
