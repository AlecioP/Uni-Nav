package persistence.daoManage.jdbcDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import persistence.daoManage.DataSource;
import persistence.daoManage.PersistenceException;
import persistence.daoManage.crud.Crud;
import persistence.daoManage.crud.CrudTag;
import persistence.persistentModel.Studente;
import persistence.utility.Utility;

public class StudenteDaoJDBC implements Crud {
	
	private DataSource ds;
	
	public StudenteDaoJDBC(DataSource ds) {
		this.ds = ds;
	}

	@Override
	public void save(CrudTag obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public Studente findByPrimaryKey(String pKey) {
		int key = Integer.parseInt(pKey);
		Studente retrived = null;
		/**/
		Connection connection = ds.getConnection();
		PreparedStatement statement;
		String query = "select * from \"Studente\" where \"Matricola\" = ?";
		try {
			statement = connection.prepareStatement(query);
			statement.setInt(1, key);
			ResultSet result = statement.executeQuery();
			if(result.next()) {
				int flag = result.getInt("Flag_attuali");
				String nome = Utility.deleteArrayElements(result.getString("Nome"));
				String cognome = Utility.deleteArrayElements(result.getString("Cognome"));
				String email = Utility.deleteArrayElements(result.getString("Email"));
				retrived = new Studente(key, flag, nome, cognome, email, null);
			}
		}catch (SQLException e) {
			throw new PersistenceException(e.getMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new PersistenceException(e.getMessage());
			}
		}
		/**/
		return retrived;
	}

	@Override
	public ArrayList<Studente> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(CrudTag obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(CrudTag obj) {
		// TODO Auto-generated method stub

	}

}
