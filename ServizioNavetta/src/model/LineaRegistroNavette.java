package model;

import persistence.persistentModel.Autista;
import persistence.persistentModel.TrattoLinea;

public class LineaRegistroNavette {
	
	private int navettaId;
	private int giriCompletati;
	private Autista autista;
	private TrattoLinea posizione;
	
	public LineaRegistroNavette() {}
	
	public int getNavettaId() {
		return navettaId;
	}
	public void setNavettaId(int navettaId) {
		this.navettaId = navettaId;
	}
	public int getGiriCompletati() {
		return giriCompletati;
	}
	public void setGiriCompletati(int giriCompletati) {
		this.giriCompletati = giriCompletati;
	}
	public Autista getAutista() {
		return autista;
	}
	public void setAutista(Autista autista) {
		this.autista = autista;
	}
	public TrattoLinea getPosizione() {
		return posizione;
	}
	public void setPosizione(TrattoLinea posizione) {
		this.posizione = posizione;
	}
	
	

}
