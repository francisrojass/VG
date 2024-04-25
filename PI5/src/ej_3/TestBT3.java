package ej_3;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.jgrapht.GraphPath;

import us.lsi.graphs.alg.BT;
import us.lsi.graphs.virtual.EGraph;
import us.lsi.graphs.virtual.EGraph.Type;
import us.lsi.path.EGraphPath.PathType;

public class TestBT3 {

	public static void main(String[] args) {
		String filename="ficheros/Ejercicio3DatosEntrada1.txt";
		FactoriaDistribuidor.iniDatos(filename);
		
		DistribuidorVertex verticeInicial = DistribuidorVertex.initial();
		
		Predicate<DistribuidorVertex> goal = DistribuidorVertex.goal();
		
		/*
		 * Para darle los parametros bien. PathType se le da Sum o Last, 
		 * esto se refiere al valor de la arista si quiere que vayamos sumando o 
		 * nos quedemos con la ultima. Type.min o max se refiere a si queremos minimizar o maximizar.
		 */
		EGraph<DistribuidorVertex, DistribuidorEdge> graph= EGraph.virtual(verticeInicial,goal,PathType.Sum,Type.Min)
				.goalHasSolution(DistribuidorVertex.goalHasSolution())
				//.heuristic(DistribuidorHeuristic::heuristic)
				.build();
		
		//Este es AStar, hay que decirle cuales son correctas
		BT<DistribuidorVertex, DistribuidorEdge, SolucionDistribuidor> BT_alg = BT.of(graph);
		
		GraphPath<DistribuidorVertex, DistribuidorEdge> path= BT_alg.search().get();
		/*
		 * Sacamos las actions de las aristas para darsela tipo cromosoma a nuestra clase solucion y sea visible al printear
		 */
		List<Integer> gp_actions = path.getEdgeList().stream().map(x->x.action()).collect(Collectors.toList());
		SolucionDistribuidor sol = SolucionDistribuidor.of_Range(gp_actions);
		System.out.println(sol);

	}

}
