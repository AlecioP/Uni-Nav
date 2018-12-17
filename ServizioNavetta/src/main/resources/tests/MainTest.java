package tests;

import java.util.ArrayList;

import persistence.daoManage.DAOFactory;
import persistence.daoManage.PostgresDAOFactory;
import persistence.daoManage.jdbcDao.StudenteDaoJDBC;
import persistence.persistentModel.Password;
import persistence.persistentModel.Studente;

public class MainTest {

	public static void main(String[] args) {
		DAOFactory df = PostgresDAOFactory.getDAOFactory(DAOFactory.POSTGRESQL);
		StudenteDaoJDBC dao = (StudenteDaoJDBC) df.getStudenteDAO();
		Studente stud = dao.findByPrimaryKey("7891011");
		if(stud!=null)
			System.out.println("STUDENTE : "+stud.getMatricola()+" "+stud.getNome()+" "+stud.getCognome()+" "+stud.getEmail());
		else
			System.out.println("Studente non trovato !");
		
		Password pass2 = new Password();
		pass2.password = "pass2";
		Studente s2 = new Studente(7891012, 0, "Antonio", "Antonii", "mail@mail.mail", pass2);
		dao.save(s2);
		
		ArrayList<Studente> studenti = dao.findAll();
		
		for(Studente sI:studenti) {
			System.out.println("STUDENTE : "+sI.getMatricola()+" "+sI.getNome()+" "+sI.getCognome()+" "+sI.getEmail()+" "+sI.getFlag());
		}
		
		Studente s3 = new Studente(7891011, 0, "Rocco", "Co", "franco@co.mail", pass2);
		dao.update(s3);

	}

}
