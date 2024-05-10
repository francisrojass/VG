package ej_4;

import us.lsi.graphs.virtual.SimpleEdgeAction;

public record EmparejamientoEdge(EmparejamientoVertex source, EmparejamientoVertex target, Integer action, Double weight) 
	implements SimpleEdgeAction<EmparejamientoVertex, Integer> {
	
	public static EmparejamientoEdge of(EmparejamientoVertex v1, EmparejamientoVertex v2, Integer a) {
		
		double peso = 0.;

		if (v1.index() == 0) {
			peso = FactoriaEmparejamiento.getAfinidad(0, a);
			
		} else {
			peso =( v1.index()%2 == 0 ? FactoriaEmparejamiento.getAfinidad(v1.ultima(), a) : 0);
		}
		return new EmparejamientoEdge(v1, v2, a, peso);
	}

}
