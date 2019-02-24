package tests;

import java.util.ArrayList;

import persistence.daoManage.DataSource;
import persistence.daoManage.PostgresDAOFactory;
import persistence.daoManage.jdbcDao.FermataDaoJDBC;
import persistence.daoManage.jdbcDao.TrattoLineaDaoJDBC;
import persistence.daoManage.jdbcDao.TrattoToLineaAdder;
import persistence.persistentModel.Fermata;
import persistence.persistentModel.Linea;
import persistence.persistentModel.TrattoLinea;

public class InsertTratti {

	public static void main(String[] args) {
		PostgresDAOFactory remoto = new PostgresDAOFactory();
		DataSource ds = null;
		PostgresDAOFactory.setDs(ds);
		
		FermataDaoJDBC fDao = (FermataDaoJDBC) remoto.getFermataDAO();
		
		TrattoLineaDaoJDBC tDao = (TrattoLineaDaoJDBC) remoto.getTrattoLineaDAO();
		
		Linea l = new Linea("linea1");
		
		Fermata cubo42 = (Fermata) fDao.findByPrimaryKey("Dimes_Cubo_42"),
		cubo32 = (Fermata) fDao.findByPrimaryKey("Demacs_Cubo_32"),
		uniClub = (Fermata) fDao.findByPrimaryKey("University_Club_Cubo_24"),
		cubo7 = (Fermata) fDao.findByPrimaryKey("Aula_Magna_B.Andreatta_Cubo_7"),
		cubo2 = (Fermata) fDao.findByPrimaryKey("Desf_Cubo_2"),
		pensiline = (Fermata) fDao.findByPrimaryKey("Pensiline"),
		poli = (Fermata) fDao.findByPrimaryKey("Polifunzionale"),
		cannataro = (Fermata) fDao.findByPrimaryKey("Baker_Cannataro"),
		svincolo = (Fermata) fDao.findByPrimaryKey("Svincolo_A3"),
		quattroMiglia = (Fermata) fDao.findByPrimaryKey("Quattromiglia"),
		girasoli = (Fermata) fDao.findByPrimaryKey("Complesso_Girasoli"),
		giorcelli = (Fermata) fDao.findByPrimaryKey("Parco_Giorcelli"),
		castiglione = (Fermata) fDao.findByPrimaryKey("Castiglione_Cs._Stazione_FS"),
		borromeo = (Fermata) fDao.findByPrimaryKey("Borromeo");
		
		
		ArrayList<Fermata> fermate = new ArrayList<Fermata>();
		
		fermate.add(cubo42);
		fermate.add(cubo32);
		fermate.add(uniClub);
		fermate.add(cubo7);
		fermate.add(cubo2);
		fermate.add(pensiline);
		fermate.add(poli);
		fermate.add(cannataro);
		fermate.add(svincolo);
		fermate.add(quattroMiglia);
		fermate.add(girasoli);
		fermate.add(giorcelli);
		fermate.add(castiglione);
		fermate.add(borromeo);
		
		ArrayList<TrattoLinea> tratti  = new ArrayList<TrattoLinea>();
		
		for(int i=0;i<fermate.size()-1;i++) {
			String f1 = fermate.get(i).getNome()+" "+fermate.get(i).getLatitudine()+" "+fermate.get(i).getLongitudine();
			String f2  = fermate.get(i+1).getNome()+" "+fermate.get(i+1).getLatitudine()+" "+fermate.get(i+1).getLongitudine();
			
			System.out.println("From "+f1+" to "+f2);
			
			TrattoLinea nuovo = new TrattoLinea(fermate.get(i), fermate.get(i+1), 5.0, 5.0);
			
			tratti.add(nuovo);
		}
		
		for(int i = fermate.size()-1; i > 0 ; i--) {
			String f1 = fermate.get(i).getNome()+" "+fermate.get(i).getLatitudine()+" "+fermate.get(i).getLongitudine();
			String f2  = fermate.get(i-1).getNome()+" "+fermate.get(i-1).getLatitudine()+" "+fermate.get(i-1).getLongitudine();
			
			System.out.println("From "+f1+" to "+f2);
			
			TrattoLinea nuovo = new TrattoLinea(fermate.get(i), fermate.get(i-1), 5.0, 5.0);
			
			tratti.add(nuovo);
		}
		
		for(TrattoLinea t : tratti) {
			String t1 = t.getPartenza().getNome()+" -> "+t.getArrivo().getNome();
			System.out.println(t1);
			
			tDao.save(t);
			
			TrattoToLineaAdder.getInstance().add(t, l, ds);
		}
	}

}
