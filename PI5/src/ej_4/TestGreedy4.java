package ej_4;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.jgrapht.GraphPath;

import us.lsi.graphs.alg.GreedyOnGraph;
import us.lsi.graphs.virtual.EGraph;
import us.lsi.graphs.virtual.EGraph.Type;
import us.lsi.path.EGraphPath.PathType;

public class TestGreedy4 {

	public static void main(String[] args) {
		String filename="ficheros/Ejercicio4DatosEntrada1.txt";
		FactoriaEmparejamiento.iniDatos(filename);
		
		EmparejamientoVertex verticeInicial = EmparejamientoVertex.initial();
		
		Predicate<EmparejamientoVertex> goal = EmparejamientoVertex.goal();
		
		EGraph<EmparejamientoVertex, EmparejamientoEdge> graph= EGraph.virtual(verticeInicial,goal,PathType.Sum,Type.Max)
				.goalHasSolution(EmparejamientoVertex.goalHasSolution())
				//.heuristic(EmparejamientoHeuristic::heuristic)
				.build();
		
		
		GreedyOnGraph<EmparejamientoVertex, EmparejamientoEdge> gg = GreedyOnGraph.of(graph);
		
		GraphPath<EmparejamientoVertex, EmparejamientoEdge> path= gg.search().get();
		
		List<Integer> gp_actions = path.getEdgeList().stream().map(x->x.action()).collect(Collectors.toList());
		System.out.println(gp_actions);
		SolucionEmparejamiento sol = SolucionEmparejamiento.of(gp_actions);
		System.out.println(sol);

	}

}
