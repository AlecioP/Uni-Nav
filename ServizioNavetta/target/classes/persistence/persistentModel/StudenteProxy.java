package persistence.persistentModel;

import java.util.ArrayList;

import persistence.daoManage.DataSource;

public class StudenteProxy extends Studente{
	
	private DataSource ds;
	
	public StudenteProxy(int _matricola,int flag,String nome,String cognome,String email,Password password,DataSource ds) {
		super(_matricola, flag, nome, cognome, email, password);
		this.ds = ds;
	}
	
	@Override
	public ArrayList<Prenotazione> getPrenotazioni() {
		// TODO Auto-generated method stub
		return null;
	}

}
