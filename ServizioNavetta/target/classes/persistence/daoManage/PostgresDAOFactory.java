package persistence.daoManage;



import persistence.daoManage.crud.Crud;
import persistence.daoManage.jdbcDao.StudenteDaoJDBC;

public class PostgresDAOFactory extends DAOFactory {

	
	
	private static  DataSource dataSource;
	

	// --------------------------------------------

	static {
		try {
			Class.forName("org.postgresql.Driver").newInstance();
			dataSource=new DataSource("jdbc:postgresql://localhost:5432/ServizioNavetta","postgres","postgres");
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	// --------------------------------------------
	
	@Override
	public Crud getStudenteDAO() {
		return new StudenteDaoJDBC(dataSource);
	}

	
}
