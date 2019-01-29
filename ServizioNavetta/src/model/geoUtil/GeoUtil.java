package model.geoUtil;

import java.util.ArrayList;
import java.util.List;

import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.alg.shortestpath.KShortestSimplePaths;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;

import persistence.daoManage.DAOFactory;
import persistence.daoManage.DatabaseManager;
import persistence.daoManage.jdbcDao.TrattoLineaDaoJDBC;
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
		DAOFactory df = DatabaseManager.getInstance().getDaoFactory();
		TrattoLineaDaoJDBC trattoDao = (TrattoLineaDaoJDBC) df.getTrattoLineaDAO();
		ArrayList<TrattoLinea> tuttiTratti = (ArrayList<TrattoLinea>) trattoDao.findAll();

		Graph<Fermata, DefaultWeightedEdge> grafo = new SimpleDirectedWeightedGraph<>(DefaultWeightedEdge.class); 

		for(TrattoLinea t : tuttiTratti) {
			grafo.addVertex(t.getArrivo());
			grafo.addVertex(t.getPartenza());
			DefaultWeightedEdge edge = grafo.addEdge(t.getPartenza(), t.getArrivo());
			grafo.setEdgeWeight(edge, t.getDistanzaKM());
		}
		GraphPath<Fermata, DefaultWeightedEdge> shortestPath = DijkstraShortestPath.findPathBetween(grafo, partenza, arrivo);
		/**/
		int maxHops = 5;
		int kNumPaths = 5;
		KShortestSimplePaths<Fermata, DefaultWeightedEdge> paths = new KShortestSimplePaths<>(grafo,maxHops);
		List<GraphPath<Fermata, DefaultWeightedEdge>> alternatives = paths.getPaths(partenza, arrivo, kNumPaths);
		/**/


		
		alternatives.add(shortestPath);
		ArrayList<ArrayList<TrattoLinea> > ret = new ArrayList<ArrayList<TrattoLinea>>(alternatives.size());
		int indexPath = 0;
		for(GraphPath<Fermata, DefaultWeightedEdge> g : alternatives) {
			ArrayList<Fermata> fermate = (ArrayList<Fermata>) g.getVertexList();
			ArrayList<DefaultWeightedEdge> archi = (ArrayList<DefaultWeightedEdge>) g.getEdgeList();
			
			for(int j=0;j<fermate.size()-1;j++)
				ret.get(indexPath).add(new TrattoLinea(fermate.get(j), fermate.get(j+1), 0.0, grafo.getEdgeWeight(archi.get(j))));
			indexPath++;
		}
		
		return ret;
	}

}
