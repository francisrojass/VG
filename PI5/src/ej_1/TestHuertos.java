package ej_1;

import java.util.List;

import org.jgrapht.GraphPath;

import us.lsi.graphs.alg.AStar;
import us.lsi.graphs.virtual.EGraph;
import us.lsi.graphs.virtual.EGraph.Type;
import us.lsi.path.EGraphPath.PathType;

public class TestHuertos {

	public static void main(String[] args) {
		FactoriaHuertos.iniDatos("ficheros/Ejercicio1DatosEntrada1.txt");
		//testAstar();
		//testBT();
		//testPDR();
	}
	/*
	 * private static EGraph<HuertoVertex, HuertoEdge> createGrafo(){
		var grafo = EGraph.virtual(
				HuertoVertex.initial(),
				HuertoVertex::goal,
				PathType.Sum,
				Type.Max)
				.goalHasSolution(HuertoVertex::goalHasSolution)
				.heuristic(HuertoHeuristic::heuristic1)
				.build();
	}
	 */
	
	/*
	 * Si cogemos las alternativa del camino optimo del grafo, tendriamos el cromosoma.
	 */
	private static List<Integer> transform(GraphPath<HuertoVertex, HuertoEdge> gp) {
		return gp.getEdgeList().stream().map(e->e.action()).toList();
	}
	/*
	private static void testAstar() {
		EGraph<HuertoVertex, HuertoEdge> g = createGrafo();
		var alg = AStar.of(g);
		var opt=alg.search();
		if (opt.isPresent()) {
			List<Integer> ls = transform(opt.get());
			System.out.print(SolucionHuerto.of(ls));
		}
	}
	

	private static void testBT() {
		EGraph<HuertoVertex, HuertoEdge> g = createGrafo();
	}

	private static void testPDR() {
		EGraph<HuertoVertex, HuertoEdge> g = createGrafo();
	}
	*/
}
