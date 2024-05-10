package ej_3;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.jgrapht.GraphPath;

import us.lsi.graphs.alg.AStar;
import us.lsi.graphs.virtual.EGraph;
import us.lsi.graphs.virtual.EGraph.Type;
import us.lsi.path.EGraphPath.PathType;

public class TestAstar3 {

	public static void main(String[] args) {
		String filename="ficheros/Ejercicio3DatosEntrada1.txt";
		FactoriaDistribuidor.iniDatos(filename);
		
		DistribuidorVertex verticeInicial = DistribuidorVertex.initial();
		
		Predicate<DistribuidorVertex> goal = DistribuidorVertex.goal();
		
		
		EGraph<DistribuidorVertex, DistribuidorEdge> graph= EGraph.virtual(verticeInicial,goal,PathType.Sum,Type.Min)
				.goalHasSolution(DistribuidorVertex.goalHasSolution())
				//.heuristic(DistribuidorHeuristic::heuristic)
				.build();
		
		AStar<DistribuidorVertex, DistribuidorEdge, SolucionDistribuidor> Astar_alg = AStar.of(graph);
		
		GraphPath<DistribuidorVertex, DistribuidorEdge> path = Astar_alg.search().get();
		
		List<Integer> gp_actions = path.getEdgeList().stream().map(x->x.action()).collect(Collectors.toList());
		SolucionDistribuidor sol = SolucionDistribuidor.of_Range(gp_actions);
		System.out.println(sol);

	}

}
