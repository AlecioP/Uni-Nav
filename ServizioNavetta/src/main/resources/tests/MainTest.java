package tests;

import persistence.daoManage.DAOFactory;
import persistence.daoManage.PostgresDAOFactory;
import persistence.daoManage.jdbcDao.StudenteDaoJDBC;
import persistence.persistentModel.Studente;

public class MainTest {

	public static void main(String[] args) {
		DAOFactory df = PostgresDAOFactory.getDAOFactory(DAOFactory.POSTGRESQL);
		StudenteDaoJDBC dao = (StudenteDaoJDBC) df.getStudenteDAO();
		Studente stud = dao.findByPrimaryKey("123456");
		if(stud!=null)
			System.out.println("STUDENTE : "+stud.getMatricola()+" "+stud.getNome()+" "+stud.getCognome()+" "+stud.getEmail());
		else
			System.out.println("Studente non trovato !");

	}

}
