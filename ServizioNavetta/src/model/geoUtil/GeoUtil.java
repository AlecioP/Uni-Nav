package model.geoUtil;

import java.util.ArrayList;

import persistence.persistentModel.Fermata;
import persistence.persistentModel.TrattoLinea;

public class GeoUtil {
	
	private GeoUtil() {}
	
	public static double distance(double p1LAT,double p1LNG, double p2LAT, double p2LNG) {
		double d2r = Math.PI / 180;
		
		try{
		    double dlong = (p2LNG - p1LNG) * d2r;
		    double dlat = (p2LAT - p1LAT) * d2r;
		    double a =
		        Math.pow(Math.sin(dlat / 2.0), 2)
		            + Math.cos(p1LAT * d2r)
		            * Math.cos(p2LAT * d2r)
		            * Math.pow(Math.sin(dlong / 2.0), 2);
		    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
		    double d = 6367 * c;

		    return d;

		} catch(Exception e){
		    e.printStackTrace();
		    return -1;
		}
	}
	
	public static ArrayList<ArrayList<TrattoLinea> > computeRoutes(Fermata partenza, Fermata arrivo){
		return null;
	}

}
