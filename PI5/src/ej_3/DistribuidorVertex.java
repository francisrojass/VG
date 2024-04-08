package ej_3;

import java.util.List;

import us.lsi.graphs.virtual.VirtualVertex;

public record DistribuidorVertex(Integer index, Integer z, List<Integer> unidadesRestantes, List<Integer> demandasRestantes) 
	implements VirtualVertex<DistribuidorVertex, DistribuidorEdge, Integer> {
	
	public static DistribuidorVertex of(Integer i, Integer zi, List<Integer> u, List<Integer> d) {
		return new DistribuidorVertex(i, zi, u, d);
	}
	@Override
	public List<Integer> actions() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DistribuidorVertex neighbor(Integer a) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DistribuidorEdge edge(Integer a) {
		// TODO Auto-generated method stub
		return null;
	}

}
