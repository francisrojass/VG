package ej_2;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.jgrapht.GraphPath;

import us.lsi.graphs.alg.AStar;
import us.lsi.graphs.virtual.EGraph;
import us.lsi.graphs.virtual.EGraph.Type;
import us.lsi.path.EGraphPath.PathType;

public class TestAstar2 {

	public static void main(String[] args) {
		String filename="ficheros/Ejercicio2DatosEntrada3.txt";
		
		FactoriaCesta.iniDatos(filename);
		CestaVertex inicial= CestaVertex.initial();
		Predicate<CestaVertex> goal = CestaVertex.goal();
		
		/*
		 * Para darle los parametros bien. PathType se le da Sum o Last, 
		 * esto se refiere al valor de la arista si quiere que vayamos sumando o 
		 * nos quedemos con la ultima. Type.min o max se refiere a si queremos minimizar o maximizar.
		 */
		
		EGraph<CestaVertex, CestaEdge> graph= EGraph.virtual(inicial,goal,PathType.Sum,Type.Min)
				.goalHasSolution(CestaVertex.goalHasSolution())
				.heuristic(CestaHeuristic::heuristic)
				.build();
		
		//Este es AStar, hay que decirle cuales son correctas
		AStar<CestaVertex, CestaEdge, SolucionCesta> Astar_alg = AStar.of(graph);
		
		GraphPath<CestaVertex, CestaEdge> path= Astar_alg.search().get();
	
		List<Integer> gp_actions = path.getEdgeList().stream().map(x->x.action()).collect(Collectors.toList());
		SolucionCesta sol = SolucionCesta.create(gp_actions);
		System.out.println(sol);
	}

}
