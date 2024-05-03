package ej_4;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.jgrapht.GraphPath;

import us.lsi.graphs.alg.AStar;
import us.lsi.graphs.virtual.EGraph;
import us.lsi.graphs.virtual.EGraph.Type;
import us.lsi.path.EGraphPath.PathType;

public class TestAstar4 {

	public static void main(String[] args) {
		String filename="ficheros/Ejercicio4DatosEntrada1.txt";
		FactoriaEmparejamiento.iniDatos(filename);
		
		EmparejamientoVertex verticeInicial = EmparejamientoVertex.initial();
		
		Predicate<EmparejamientoVertex> goal = EmparejamientoVertex.goal();
		
		/*
		 * Para darle los parametros bien. PathType se le da Sum o Last, 
		 * esto se refiere al valor de la arista si quiere que vayamos sumando o 
		 * nos quedemos con la ultima. Type.min o max se refiere a si queremos minimizar o maximizar.
		 */
		EGraph<EmparejamientoVertex, EmparejamientoEdge> graph= EGraph.virtual(verticeInicial,goal,PathType.Sum,Type.Max)
				.goalHasSolution(EmparejamientoVertex.goalHasSolution())
				//.heuristic(EmparejamientoHeuristic::heuristic)
				.build();
		
		//Este es AStar, hay que decirle cuales son correctas
		AStar<EmparejamientoVertex, EmparejamientoEdge, SolucionEmparejamiento> Astar_alg = AStar.of(graph);
		
		GraphPath<EmparejamientoVertex, EmparejamientoEdge> path= Astar_alg.search().get();
		/*
		 * Sacamos las actions de las aristas para darsela tipo cromosoma a nuestra clase solucion y sea visible al printear
		 */
		List<Integer> gp_actions = path.getEdgeList().stream().map(x->x.action()).collect(Collectors.toList());
		SolucionEmparejamiento sol = SolucionEmparejamiento.of(gp_actions);
		System.out.println(sol);

	}

}
