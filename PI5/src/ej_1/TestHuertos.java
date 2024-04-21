package ej_1;

import java.util.List;

import org.jgrapht.GraphPath;

import us.lsi.graphs.alg.AStar;
import us.lsi.graphs.alg.BT;
import us.lsi.graphs.alg.PDR;
import us.lsi.graphs.virtual.EGraph;
import us.lsi.graphs.virtual.EGraph.Type;
import us.lsi.path.EGraphPath.PathType;

public class TestHuertos {

	public static void main(String[] args) {
		FactoriaHuertos.iniDatos("ficheros/Ejercicio1DatosEntrada1.txt");
		testAstar();
		testBT();
		testPDR();
	}
	
	private static EGraph<HuertoVertex, HuertoEdge> createGrafo(){
		return EGraph.virtual(
				HuertoVertex.initial(),
				HuertoVertex.goal(),
				PathType.Sum,
				Type.Max)
				.goalHasSolution(HuertoVertex.goalHasSolution())
				.heuristic(HuertoHeuristic::heuristic)
				.build();
	}
	
	private static List<Integer> transform(GraphPath<HuertoVertex, HuertoEdge> gp) {
		return gp.getEdgeList().stream().map(e->e.action()).toList();
	}
	
	private static void testAstar() {
		System.out.println("===================== A* ================");
		EGraph<HuertoVertex, HuertoEdge> g = createGrafo();
		var alg = AStar.of(g);
		var opt = alg.search();
		
		if (opt.isPresent()) {
			List<Integer> ls = transform(opt.get());
			System.out.print(SolucionHuerto.of(ls));
		}
	}
	private static void testBT() {
		System.out.println("===================== BT ================");
		EGraph<HuertoVertex, HuertoEdge> g = createGrafo();
		var alg = BT.of(g);
		var opt = alg.search();
		
		if (opt.isPresent()) {
			List<Integer> ls = transform(opt.get());
			System.out.print(SolucionHuerto.of(ls));
		}
	}
	private static void testPDR() {
		System.out.println("===================== PDR ================");
		EGraph<HuertoVertex, HuertoEdge> g = createGrafo();
		var alg = PDR.of(g);
		var opt = alg.search();
		
		if (opt.isPresent()) {
			List<Integer> ls = transform(opt.get());
			System.out.print(SolucionHuerto.of(ls));
		}
	}
	
}
