package persistence.utility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import persistence.daoManage.DataSource;
import persistence.daoManage.PersistenceException;
import persistence.daoManage.PostgresDAOFactory;

public class IdProvider {
	
	private static IdProvider instance=null;
	
	private IdProvider() {}
	
	public static IdProvider getInstance() {
		if(instance==null)
			instance = new IdProvider();
		return instance;
	}
	
	public int getNextId(String table) {
		/**
		 * Parameter "table" must be the exact name of a table in the DB.
		 * The table "table" must have an integer as primary id
		 * Otherwise the method returns -1;
		 */
		table = table.toLowerCase();
		
		DataSource ds = PostgresDAOFactory.getDS();
		Connection con = ds.getConnection();
		
		String query = "";
		
		switch(table) {
		case "persona":{
			query = "select min(s.i)" + 
					"from generate_series(0,(select max(P1.\"ID\")from \"Persona\" as P1)+1) s(i)" + 
					"where not exists (select P.\"ID\" from \"Persona\" as P where P.\"ID\" = s.i)";
			break;
		}
		case "prenotazione":{
			query = "select min(s.i)" + 
					"from generate_series(0,(select max(P1.\"ID\")from \"Prenotazione\" as P1)+1) s(i)" + 
					"where not exists (select P.\"ID\" from \"Prenotazione\" as P where P.\"ID\" = s.i)";
			break;
		}
		case "domanda_riabilitazione":
		case "domandariabilitazione":
		case "domanda riabilitazione":{
			query = "select min(s.i)" + 
					"from generate_series(0,(select max(P1.\"ID\")from \"Domanda_Riabilitazione\" as P1)+1) s(i)" + 
					"where not exists (select P.\"ID\" from \"Domanda_Riabilitazione\" as P where P.\"ID\" = s.i)";
			break;
		}
		case "feedback":{
			query = "select min(s.i)" + 
					"from generate_series(0,(select max(P1.\"Prenotazione_ID\")from \"Feedback\" as P1)+1) s(i)" + 
					"where not exists (select P.\"Prenotazione_ID\" from \"Feedback\" as P where P.\"ID\" = s.i)";
			break;
		}
		case "navetta":{
			query = "select min(s.i)" + 
					"from generate_series(0,(select max(P1.\"ID\")from \"Navetta\" as P1)+1) s(i)" + 
					"where not exists (select P.\"ID\" from \"Navetta\" as P where P.\"ID\" = s.i)";
			break;
		}
		default :{
			return -1;
		}
		}
		
		try {
			PreparedStatement statement = con.prepareStatement(query);
			ResultSet res = statement.executeQuery();
			if(res.next()) {
				int ret = res.getInt(1);
				return ret;
			}
			return -1;
		} catch (SQLException e) {
			throw new PersistenceException(e.getMessage());
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				throw new PersistenceException(e.getMessage());
			}
		}
	}

}
