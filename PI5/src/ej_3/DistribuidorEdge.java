package ej_3;

import us.lsi.graphs.virtual.SimpleEdgeAction;

public record DistribuidorEdge(DistribuidorVertex source, DistribuidorVertex target, Integer action, Double weight) 
	implements SimpleEdgeAction<DistribuidorVertex, Integer> {
	
	public static DistribuidorEdge of (DistribuidorVertex v1, DistribuidorVertex v2, Integer a) {
		double peso	= a * FactoriaDistribuidor.getAlmacenamientoCoste(DistribuidorVertex.saberProducto(v1.index()), DistribuidorVertex.saberDestino(v1.index())) ;
		return new DistribuidorEdge(v1, v2, a, peso);
	}
}
