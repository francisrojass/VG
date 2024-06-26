package ej_1;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.jgrapht.GraphPath;

import us.lsi.graphs.alg.PDR;
import us.lsi.graphs.virtual.EGraph;
import us.lsi.graphs.virtual.EGraph.Type;
import us.lsi.path.EGraphPath.PathType;

public class TestPDR {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String filename="ficheros/Ejercicio1DatosEntrada1.txt";
		FactoriaHuertos.iniDatos(filename);
		
		HuertoVertex verticeInicial = HuertoVertex.initial();
		
		Predicate<HuertoVertex> goal = HuertoVertex.goal();
		
		//No se si en el path type hay que ponerlo a sum
		EGraph<HuertoVertex, HuertoEdge> graph= EGraph.virtual(verticeInicial,goal,PathType.Sum,Type.Max)
				.goalHasSolution(HuertoVertex.goalHasSolution())
				.heuristic(HuertoHeuristic::heuristic)
				.build();
		
		//Este es AStar, hay que decirle cuales son correctas
		PDR<HuertoVertex, HuertoEdge, SolucionHuerto> PDR_alg=PDR.of(graph);
		
		GraphPath<HuertoVertex, HuertoEdge> path= PDR_alg.search().get();
		/*
		 * Sacamos las actions de las aristas para darsela tipo cromosoma a nuestra clase solucion y sea visible al printear
		 */
		List<Integer> gp_actions = path.getEdgeList().stream().map(x->x.action()).collect(Collectors.toList());
		SolucionHuerto sol = SolucionHuerto.of(gp_actions);
		System.out.println(sol);
	}

}
