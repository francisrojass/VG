package ej_2;

import us.lsi.graphs.virtual.SimpleEdgeAction;

public record CestaEdge(CestaVertex source, CestaVertex target, Integer action, Double weight) 
	implements SimpleEdgeAction<CestaVertex, Integer> {

	public static CestaEdge of(CestaVertex v1, CestaVertex v2, Integer a) {
		return new CestaEdge(v1, v2, a, (double) a);
	}

}
