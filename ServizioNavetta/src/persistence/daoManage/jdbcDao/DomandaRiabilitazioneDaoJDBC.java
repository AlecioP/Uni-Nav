package persistence.daoManage.jdbcDao;

import java.sql.Connection;
import java.sql.JDBCType;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import persistence.daoManage.DataSource;
import persistence.daoManage.PersistenceException;
import persistence.daoManage.crud.Crud;
import persistence.daoManage.crud.CrudTag;
import persistence.persistentModel.Amministratore;
import persistence.persistentModel.DomandaRiabilitazione;
import persistence.persistentModel.Studente;

public class DomandaRiabilitazioneDaoJDBC implements Crud {

	private DataSource ds;

	public DomandaRiabilitazioneDaoJDBC(DataSource ds) {
		this.ds = ds;
	}

	@Override
	public void save(CrudTag obj) {
		DomandaRiabilitazione domanda = (DomandaRiabilitazione) obj;
		Connection con = ds.getConnection();
		String query = "insert into \"Domanda_Riabilitazione\""
				+ "values (?,?,?,?,?)";
		try {
			PreparedStatement smt = con.prepareStatement(query);
			smt.setInt(1, domanda.getID());
			LocalDate date = domanda.getDateTime().toLocalDate();
			java.sql.Date dataSQL=java.sql.Date.valueOf(date);
			LocalTime time = domanda.getDateTime().toLocalTime();

			java.sql.Time timeSQL=java.sql.Time.valueOf(time);
			smt.setObject(2, dataSQL);
			smt.setObject(3, timeSQL);

			smt.setInt(4, domanda.getStudente().getMatricola());
			smt.setInt(5, domanda.getAmministratore().getID());

			smt.executeUpdate();
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

	@Override
	public CrudTag findByPrimaryKey(String pKey) {
		int key = 0;
		try {
			key = Integer.parseInt(pKey);
		} catch (NumberFormatException e) {
			return null;
		}

		Connection con = ds.getConnection();

		String query = "select * from \"Domanda_Riabilitazione\" where \"ID\" = ?";
		try {
			PreparedStatement smt = con.prepareStatement(query);
			smt.setInt(1, key);
			ResultSet res = smt.executeQuery();

			if (res.next()) {
				LocalDateTime dateTime = res.getTimestamp("Data").toLocalDateTime();
				Studente stud = new StudenteDaoJDBC(ds).findByPrimaryKey(res.getInt("Studente_ID") + "");
				Amministratore admin = new AmministratoreDaoJDBC(ds)
						.findByPrimaryKey(res.getInt("Amministratore_ID") + "");

				return new DomandaRiabilitazione(key, dateTime, stud, admin);
			}
			return null;
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

	@Override
	public List<? extends CrudTag> findAll() {
		ArrayList<DomandaRiabilitazione> domande = new ArrayList<DomandaRiabilitazione>();
		Connection con = ds.getConnection();
		String query = "select * from \"Domanda_Riabilitazione\" ";
		try {
			PreparedStatement smt = con.prepareStatement(query);

			ResultSet res = smt.executeQuery();
			while (res.next()) {
				int key = res.getInt("ID");

				LocalDateTime dateTime = res.getTimestamp("Data").toLocalDateTime();
				Studente stud = new StudenteDaoJDBC(ds).findByPrimaryKey(res.getInt("Studente_ID") + "");
				int id = res.getInt(5);
				Amministratore admin = new AmministratoreDaoJDBC(ds).findByPrimaryKey(id + "");

				domande.add(new DomandaRiabilitazione(key, dateTime, stud, admin));
			}
			return domande;
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

	@Override
	public void update(CrudTag obj) {
		DomandaRiabilitazione domanda = (DomandaRiabilitazione) obj;
		Connection con = ds.getConnection();
		String query = "update \"Domanda_Riabilitazione\""
				+ "set (\"Data\",\"Ora\",\"Studente_ID\",\"Amministratore_ID\") = (?,?,?,?)" + "where \"ID\" = ?";
		try {
			PreparedStatement smt = con.prepareStatement(query);
			LocalDate date = domanda.getDateTime().toLocalDate();
			LocalTime time = domanda.getDateTime().toLocalTime();

			smt.setObject(1, date, JDBCType.DATE);
			smt.setObject(2, time, JDBCType.TIME);

			smt.setInt(3, domanda.getStudente().getMatricola());
			smt.setInt(4, domanda.getAmministratore().getID());
			smt.setInt(5, domanda.getID());

			smt.executeUpdate();
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

	@Override
	public void delete(CrudTag obj) {
		DomandaRiabilitazione domanda = (DomandaRiabilitazione) obj;
		Connection con = ds.getConnection();
		String query = "delete from \"Domanda_Riabilitazione\" where \"ID\" = ?";
		try {
			PreparedStatement smt = con.prepareStatement(query);
			smt.setInt(1, domanda.getID());
			smt.executeUpdate();
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

	public ArrayList<DomandaRiabilitazione> findByAdmin(String fkey) {
		ArrayList<DomandaRiabilitazione> domande = new ArrayList<DomandaRiabilitazione>();
		int key = 0;
		try {
			key = Integer.parseInt(fkey);
		} catch (NumberFormatException e) {
			return null;
		}
		Connection con = ds.getConnection();
		String query = "select * from \"Domanda_Riabilitazione\" where \"Amministratore_ID\" = ?";
		try {
			PreparedStatement smt = con.prepareStatement(query);
			smt.setInt(1, key);
			ResultSet res = smt.executeQuery();

			while (res.next()) {
				LocalDateTime dateTime = res.getTimestamp("Data").toLocalDateTime();
				Studente stud = new StudenteDaoJDBC(ds).findByPrimaryKey(res.getInt("Studente_ID") + "");
				Amministratore admin = new AmministratoreDaoJDBC(ds)
						.findByPrimaryKey(res.getInt("Amministratore_ID") + "");

				domande.add(new DomandaRiabilitazione(key, dateTime, stud, admin));
			}
			return domande;
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