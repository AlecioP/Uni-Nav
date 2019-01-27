package model;

import java.util.Date;
import java.util.HashMap;

import persistence.daoManage.DAOFactory;
import persistence.daoManage.DatabaseManager;
import persistence.daoManage.crud.Crud;
import persistence.persistentModel.Navetta;

public class RegistroAttivitaNavette {
	
	private Date data;
	
	private HashMap<Integer,LineaRegistroNavette> linee;
	
	public RegistroAttivitaNavette(Date data) {
		this.data = data;
	}

	public void addLinea(int NavettaId) {
		DAOFactory factory = DatabaseManager.getInstance().getDaoFactory();
		Crud navettaDao = factory.getNavettaDAO();
		Navetta navetta = (Navetta) navettaDao.findByPrimaryKey(NavettaId+"");
		if(navetta==null)
			return;
		linee.put(Integer.valueOf(NavettaId), new LineaRegistroNavette());
	}
	
	public LineaRegistroNavette getLineaRegistro(int NavettaId) {
		return linee.get(Integer.valueOf(NavettaId));
	}

	public Date getData() {
		return data;
	}

}
