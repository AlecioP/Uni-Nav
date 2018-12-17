package persistence.persistentModel;

import java.util.ArrayList;

import persistence.daoManage.DataSource;

public class LineaProxy extends Linea{
	
	private DataSource ds;
	
	public LineaProxy(String nome,DataSource ds) {
		super(nome);
		this.ds = ds;
	}
	
	@Override
	public ArrayList<TrattoLinea> getTratti() {
		// TODO Auto-generated method stub
		return null;
	}

}
