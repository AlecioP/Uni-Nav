package model;

import java.util.Date;
import java.util.HashMap;

import persistence.daoManage.DAOFactory;
import persistence.daoManage.DatabaseManager;
import persistence.daoManage.crud.Crud;
import persistence.persistentModel.Autista;

public class RegistroAttivitaNavette {
	
	private Date data;
	
	
	/**
	 *  The register for one Date is basically an HashMap, where the key 
	 *  is the ID of the Driver which drives the bus, and the value is
	 * 	an instance of "LineaRegistroNavette" which contains the following data :
	 * 	
	 * 	- "NavettA" "navettA" (Bus which is Driver is driving)
	 * 	- "inT" "giriCompletati" (Tours completed since start of shift)
	 *  - "AutistA" "autistA"	(Driver of the Bus)
	 *  - "TrattoLinea" "posizionE" (Current position of the Bus)
	 *  - "LineA" "lineA" (Bus line in which Bus is working)
	 * 
	 * */
	private HashMap<Integer,LineaRegistroNavette> linee;
	
	public RegistroAttivitaNavette(Date data) {
		this.data = data;
	}

	public boolean addLinea(int AutistaId) {
		DAOFactory factory = DatabaseManager.getInstance().getDaoFactory();
		Crud autistaDao = factory.getAutistaDAO();
		Autista autista = (Autista) autistaDao.findByPrimaryKey(AutistaId+"");
		if(autista==null)
			return false;
		linee.put(Integer.valueOf(AutistaId), new LineaRegistroNavette());
		return true;
	}
	
	public LineaRegistroNavette getLineaRegistro(int AutistaId) {
		return linee.get(Integer.valueOf(AutistaId));
	}

	public Date getData() {
		return data;
	}

}
