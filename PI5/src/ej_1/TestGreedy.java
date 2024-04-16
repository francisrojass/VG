package ej_1;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.jgrapht.GraphPath;

import us.lsi.graphs.alg.GreedyOnGraph;
import us.lsi.graphs.virtual.EGraph;
import us.lsi.graphs.virtual.EGraph.Type;
import us.lsi.path.EGraphPath.PathType;

public class TestGreedy {

	public static void main(String[] args) {
		String filename="ficheros/Ejercicio1DatosEntrada2.txt";
		FactoriaHuertos.iniDatos(filename);
		
		HuertoVertex verticeInicial = HuertoVertex.initial();
		
		Predicate<HuertoVertex> goal = HuertoVertex.goal();
		
		//No se si en el path type hay que ponerlo a sum
		EGraph<HuertoVertex, HuertoEdge> graph = EGraph.virtual(verticeInicial,goal,PathType.Last,Type.Max).build();
		/*
		 * Este algoritmo es Boraz
		 */
		GreedyOnGraph<HuertoVertex, HuertoEdge> gg=GreedyOnGraph.of(graph);
		GraphPath<HuertoVertex, HuertoEdge> path=gg.path();
		/*
		 * Sacamos las actions de las aristas para darsela tipo cromosoma a nuestra clase solucion y sea visible al printear
		 */
		List<Integer> gp_actions = path.getEdgeList().stream().map(x->x.action()).collect(Collectors.toList());
		System.out.println(gp_actions);
		//SolucionHuerto sol = SolucionHuerto.of(gp_actions);
		//System.out.println(sol);
	}

}
