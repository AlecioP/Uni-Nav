package tests;

import persistence.daoManage.DatabaseManager;
import persistence.daoManage.jdbcDao.StudenteDaoJDBC;
import persistence.persistentModel.Password;
import persistence.persistentModel.Studente;
import persistence.utility.IdProvider;

public class InsertStudentTest {

	
	public static void main(String[] args) {
		int _matricola = IdProvider.getInstance().getNextId("persona");
		int flag=0;
		Password password = new Password();
		password.password = "password1";
		Studente s = new Studente(_matricola, flag, "Mario", "Rossi", "mail@mat.it", password);
		StudenteDaoJDBC sDao = (StudenteDaoJDBC) DatabaseManager.getInstance().getDaoFactory().getStudenteDAO();
		sDao.save(s);
	}
}
