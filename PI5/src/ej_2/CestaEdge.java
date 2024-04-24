package ej_2;

import us.lsi.graphs.virtual.SimpleEdgeAction;

public record CestaEdge(CestaVertex source, CestaVertex target, Integer action, Double weight) 
	implements SimpleEdgeAction<CestaVertex, Integer> {

	public static CestaEdge of(CestaVertex v1, CestaVertex v2, Integer a) 
	{
		double peso = a == 1? FactoriaCesta.getPrecio(v1.index()):0;
		
		return new CestaEdge(v1, v2, a, peso);
	}

}
