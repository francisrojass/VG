package ej_2;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.jgrapht.GraphPath;

import ej_1.SolucionHuerto;
import us.lsi.graphs.alg.GreedyOnGraph;
import us.lsi.graphs.virtual.EGraph;
import us.lsi.graphs.virtual.EGraph.Type;
import us.lsi.path.EGraphPath.PathType;

public class TestGreedy2 {
	public static void main(String[] args) {
		String filename="ficheros/Ejercicio2DatosEntrada2.txt";
		FactoriaCesta.iniDatos(filename);
		
		CestaVertex verticeInicial = CestaVertex.initial();
		
		Predicate<CestaVertex> goal = CestaVertex.goal();
		
		//No se si en el path type hay que ponerlo a sum
		EGraph<CestaVertex, CestaEdge> graph = EGraph.virtual(verticeInicial,goal,PathType.Sum,Type.Min)
				.goalHasSolution(CestaVertex.goalHasSolution())
				.build();
		/*
		 * Este algoritmo es Boraz
		 */
		GreedyOnGraph<CestaVertex, CestaEdge> gg = GreedyOnGraph.of(graph);
		GraphPath<CestaVertex, CestaEdge> path = gg.path();
		/*
		 * Sacamos las actions de las aristas para darsela tipo cromosoma a nuestra clase solucion y sea visible al printear
		 */
		List<Integer> gp_actions = path.getEdgeList().stream().map(x->x.action()).collect(Collectors.toList());
		System.out.println(gp_actions);
		SolucionCesta sol = SolucionCesta.create(gp_actions);
		System.out.println(sol);
	}
}
