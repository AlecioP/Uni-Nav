package persistence.persistentModel;

import persistence.daoManage.crud.CrudTag;

public class Fermata implements CrudTag{

	private String nome;
	private Double latitudine;
	private Double longitudine;
	private static final double LAT_MAX_VALUE = 90;
	private static final double LNG_MAX_VALUE = 90;
	
	public Fermata(String nome,double lat,double lng) {
		this.nome=nome;
		setLatitudine(lat);
		setLongitudine(lng);
		this.longitudine = lng;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Double getLatitudine() {
		return latitudine;
	}

	public void setLatitudine(double latitudine) {
		
		this.latitudine = latitudine%LAT_MAX_VALUE;
	}

	public Double getLongitudine() {
		return longitudine;
	}

	public void setLongitudine(double longitudine) {
		this.longitudine = longitudine%LNG_MAX_VALUE;
	}
	
}
