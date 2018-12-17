package persistence.persistentModel;

public class Amministratore extends Persona {
	
private int ID;
	
	public Amministratore(int ID,String nome,String cognome,String email,Password password) {
		super(nome, cognome, email, password);
		this.ID = ID;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

}
