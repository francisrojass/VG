package ej_2;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.jgrapht.GraphPath;

import us.lsi.graphs.alg.BT;
import us.lsi.graphs.alg.PDR;
import us.lsi.graphs.virtual.EGraph;
import us.lsi.graphs.virtual.EGraph.Type;
import us.lsi.path.EGraphPath.PathType;

public class TestPDR2 {

	public static void main(String[] args) {
		String filename="ficheros/Ejercicio2DatosEntrada3.txt";
		
		FactoriaCesta.iniDatos(filename);
		CestaVertex inicial= CestaVertex.initial();
		Predicate<CestaVertex> goal = CestaVertex.goal();
		
		//No se si en el path type hay que ponerlo a sum
		EGraph<CestaVertex, CestaEdge> graph= EGraph.virtual(inicial,goal,PathType.Sum,Type.Min)
				.goalHasSolution(CestaVertex.goalHasSolution())
				.heuristic(CestaHeuristic::heuristic)
				.build();
		
		//Este es AStar, hay que decirle cuales son correctas
		PDR<CestaVertex, CestaEdge, SolucionCesta> PDR_alg = PDR.of(graph);
		
		GraphPath<CestaVertex, CestaEdge> path= PDR_alg.search().get();
		/*
		 * Sacamos las actions de las aristas para darsela tipo cromosoma a nuestra clase solucion y sea visible al printear
		 */
		List<Integer> gp_actions = path.getEdgeList().stream().map(x->x.action()).collect(Collectors.toList());
		SolucionCesta sol = SolucionCesta.create(gp_actions);
		System.out.println(sol);

	}

}
